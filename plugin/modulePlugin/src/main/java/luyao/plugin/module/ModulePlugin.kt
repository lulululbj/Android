package luyao.plugin.module

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.utils.`is`
import java.io.File
import java.util.*

/**
 * Description:
 * Author: luyao
 * Date: 2022/9/16 10:11
 */
class ModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val properties = Properties()
        val propertiesFile = File(project.rootDir, "gradle.properties")
        println(propertiesFile.path)
        properties.load(propertiesFile.inputStream())

        val isEnabled = properties.getOrDefault(GAV_ENABLE, false).toString().toBoolean()
        if (!isEnabled) {
            println("Gav2project disabled")
            return
        }

        val aarModules =
            properties.getProperty(GAV_AAR_MODULES)?.toString()?.split(",") ?: arrayListOf()
        project.afterEvaluate {
            val gavMap = hashMapOf<String, Project>()
            project.rootProject.allprojects {
                var buildFile = File(it.projectDir, "build.gradle")
                if (!buildFile.exists()) {
                    buildFile = File(it.projectDir, "build.gradle.kts")
                }
                if (!buildFile.exists()) return@allprojects
                val gav = getGAV(buildFile.readText())
                if (gav != null) {
                    gavMap[gav.groupId + gav.artifactId] = it
                }
            }

            if (gavMap.isEmpty()) return@afterEvaluate

            val configurationName = arrayListOf<String>().apply {
                add("api")
                add("implementation")
                add("runtimeOnly")
                add("compileOnly")
            }

            if (it.plugins.hasPlugin("com.android.application")) {
                project.extensions.getByType(AppExtension::class.java).productFlavors.forEach { flavor ->
                    val productFlavor = flavor::class.java.getMethod("getName").invoke(flavor)
                    configurationName.run {
                        add("${productFlavor}Api")
                        add("${productFlavor}Implementation")
                        add("${productFlavor}RuntimeOnly")
                        add("${productFlavor}CompileOnly")
                    }
                }
            }

            configurationName.map { project.configurations.findByName(it) }
                .filterNotNull()
                .forEach { configuration ->
                    configuration.dependencies.filterNotNull()
                        .forEach { dependency ->
                            val p = gavMap[dependency.group + dependency.name]
                            if (aarModules.contains(dependency.name)) {
                                project.logger.info("保持使用依赖: ${configuration.name} ${it.group}:${it.name}:${it.version}")
                            } else {
                                if (p != null && !dependency.group.isNullOrEmpty() && !dependency.name.isNullOrEmpty()) {
                                    val excludeMap = hashMapOf<String, String>().apply {
                                        put("group", dependency.group!!)
                                        put("module", dependency.name)
                                    }
                                    configuration.exclude(excludeMap)
                                    project.logger.info("排除依赖:${configuration.name} ${it.group}:${it.name}:${it.version}")
                                    project.dependencies.add(configuration.name, p)
                                    project.logger.info("替换为本地模块:${configuration.name} project(:${p.name})")
                                }
                            }
                        }
                }
        }
    }
}
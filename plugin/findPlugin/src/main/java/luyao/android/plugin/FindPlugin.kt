package luyao.android.plugin

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.AppPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Description:
 * Author: luyao
 * Date: 2023/8/14 17:23
 */
@Suppress("UnstableApiUsage")
class FindPlugin : Plugin<Project> {

    companion object {
        const val EXTENSION_NAME = "findMethod"
        const val TASK_NAME = "findTask"
    }

    override fun apply(project: Project) {
        if (!project.plugins.hasPlugin(AppPlugin::class.java)) return
        project.extensions.create(EXTENSION_NAME, FindExtension::class.java)
        project.task(TASK_NAME)

        val variants = mutableListOf<String>()
        val androidCom = project.extensions.getByType(AndroidComponentsExtension::class.java)
        androidCom.onVariants { variant ->
            val name = variant.name
            variants.add(name)
            variant.instrumentation.apply {
                transformClassesWith(
                    FindTransform::class.java,
                    InstrumentationScope.PROJECT,
                ) { params ->
                    params.buildType.set(name)
                }
                setAsmFramesComputationMode(FramesComputationMode.COPY_FRAMES)
            }
        }

        project.afterEvaluate {
            if (variants.isEmpty()) return@afterEvaluate
            val extension = (project.properties[EXTENSION_NAME] as FindExtension)
            MethodFindUtils.initConfig(extension)
            variants.forEach { type ->
                val taskName = String.format("transform%sClassesWithAsm", type.capitalize())
                project.tasks.getByName(taskName).doLast {
                    MethodFindUtils.end(type)
                }
            }
        }
    }
}
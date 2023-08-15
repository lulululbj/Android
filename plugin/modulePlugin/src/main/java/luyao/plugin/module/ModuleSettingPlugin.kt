package luyao.plugin.module

import org.gradle.api.Plugin
import org.gradle.api.initialization.ProjectDescriptor
import org.gradle.api.initialization.Settings
import java.io.File
import java.util.*

/**
 * Description:
 * Author: luyao
 * Date: 2022/9/16 10:11
 */
class ModuleSettingPlugin : Plugin<Settings> {

    override fun apply(settings: Settings) {
        val properties = Properties()
        val propertiesFile = File(settings.rootDir, "gradle.properties")
        properties.load(propertiesFile.inputStream())

        val isEnabled = properties.getOrDefault(GAV_ENABLE, false).toString().toBoolean()
        if (!isEnabled) {
            println("Gav2project disabled")
            return
        }

        val aarModules = properties.getProperty(GAV_AAR_MODULES)?.toString()?.split(",")
        val newChildren = arrayListOf<ProjectDescriptor>()

        if (aarModules.isNullOrEmpty()) return

        try {

        } catch (e: Exception) {
            println(e.message)
        }
        settings.rootProject.children.run {
            forEach {
                if (aarModules.contains(it.name)) {
                    println("exclude ${it.name}")
                } else {
                    println("include ${it.name}")
                    newChildren.add(it)
                }
            }
            clear()
            println("clear")
            addAll(newChildren)
            println("addAll")
        }
    }
}
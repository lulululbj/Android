package luyao.plugin.upload

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.tasks.bundling.Jar


fun Project.emptySourcesJar(): Task = tasks.create("emptySourcesJar", Jar::class.java)

fun Project.androidSourcesJar(): Task {
    return tasks.create("androidSourcesJar", Jar::class.java) {
        val androidExtension = extensions.getByType(LibraryExtension::class.java)

        val set = androidExtension.sourceSets.filter {
            it.name == "main"
        }.map {
            it.java.srcDirs
        }.toSet()

        it.archiveClassifier.set("sources")
        it.archiveVersion.set("")
        it.from(set)
    }
}

fun Project.javaSourcesJar(): Task {
    return tasks.create("javaSourcesJar", Jar::class.java) {
        val javaPlugin = convention.getPlugin(JavaPluginConvention::class.java)

        val set = javaPlugin.sourceSets.filter {
            it.name == "main"
        }.map {
            it.java.srcDirs
        }.toSet()

        it.archiveClassifier.set("sources")
        it.archiveVersion.set("")
        it.from(set)
    }
}

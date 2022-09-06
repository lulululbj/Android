package luyao.android.plugin

import groovy.util.Node
import groovy.xml.XmlParser
import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class ManifestCheckTask : DefaultTask() {

    @get:InputFile
    abstract val mergedManifest: RegularFileProperty

    @get:OutputFile
    abstract val updatedManifest: RegularFileProperty

    @TaskAction
    fun taskAction() {
        val outputFile = File(project.rootDir, "/permissionCheck")
        if (outputFile.exists()) outputFile.delete()
        val manifest = mergedManifest.asFile.get().readText()
        updatedManifest.get().asFile.writeText(manifest)
        XmlParser(false, false).parse(mergedManifest.asFile.get()).children()
            .map { it as Node }.filter { it.name() == "uses-permission" }
            .forEach { permissionNode ->
                outputFile.appendText("${permissionNode.attributes()["android:name"]}\n")
            }
        println("Manifest Check result: ${outputFile.absolutePath}")
    }
}
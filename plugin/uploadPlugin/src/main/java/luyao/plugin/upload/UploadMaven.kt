package luyao.plugin.upload

import org.gradle.api.Project
import java.util.Properties

/**
 * Description:
 * Author: luyao
 * Date: 2022/9/7 14:59
 */
class UploadMaven : BaseUploadPlugin() {

    private val PARAM_URL = "url"
    private val PARAM_NAME = "name"
    private val PARAM_PSW = "psw"

    override fun isSupportUpload(uploadConfig: UploadConfig, project: Project): Boolean {
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        val nexusUrl = properties.getProperty("nexusURL") ?: ""
        if (nexusUrl.isNotEmpty()) {
            uploadConfig.nexusURL = nexusUrl
        }
        val nexusName = properties.getProperty("nexusName") ?: ""
        if (nexusUrl.isNotEmpty()) {
            uploadConfig.nexusName = nexusName
        }

        val nexusPsw = properties.getProperty("nexusPsw") ?: ""
        if (nexusUrl.isNotEmpty()) {
            uploadConfig.nexusPsw = nexusPsw
        }


        // ./gradlew upload -Pname=admin -Ppsw=admin -Purl=http://localhost:8081/repository/android/
        val params = project.gradle.startParameter.projectProperties

        if (params.containsKey(PARAM_NAME)) {
            uploadConfig.nexusName = params[PARAM_NAME] ?: ""
        }
        if (params.containsKey(PARAM_PSW)) {
            uploadConfig.nexusPsw = params[PARAM_PSW] ?: ""
        }
        if (params.containsKey(PARAM_URL)) {
            uploadConfig.nexusURL = params[PARAM_URL] ?: ""
        }

        if (uploadConfig.nexusURL.isEmpty() ||
            uploadConfig.nexusName.isEmpty() ||
            uploadConfig.nexusPsw.isEmpty()
        ) {
            println("nexus 参数配置错误: nexusURL=${uploadConfig.nexusURL} nexusName=${uploadConfig.nexusName} nexusPsw=${uploadConfig.nexusPsw}")
            println("将降级发布到本地 LocalRepo 目录")
        }
        return true
    }

    override fun isCredentials(uploadConfig: UploadConfig): Boolean {
        if (uploadConfig.nexusURL.startsWith("http")
            || uploadConfig.nexusURL.startsWith("https")
        ) {
            return true
        }
        return false
    }

    override fun uploadComplete(uploadConfig: UploadConfig, mavenUrl: String, project: Project) {
        if (uploadConfig.nexusURL.isEmpty() ||
            uploadConfig.nexusName.isEmpty() ||
            uploadConfig.nexusPsw.isEmpty()
        ) {
            project.logger.error(
                """
                已发布到本地目录：${project.file(mavenUrl).absolutePath}
                可添加依赖使用:    implementation '${uploadConfig.groupId}:${uploadConfig.artifactId}:${uploadConfig.version}'
            """.trimIndent()
            )
        } else {
            project.logger.error(
                """
                已上传到仓库, maven 镜像源为:
                maven{
                   url "$mavenUrl"
                }
                可添加依赖使用:    implementation '${uploadConfig.groupId}:${uploadConfig.artifactId}:${uploadConfig.version}'
            """.trimIndent()
            )
        }

    }
}
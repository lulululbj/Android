package luyao.plugin.module

import org.gradle.api.Project

/**
 * Description:
 * Author: luyao
 * Date: 2022/9/9 13:10
 */

const val GAV_ENABLE = "gav2project.enable"
const val GAV_AAR_MODULES = "gav2project.aarModules"

fun isEnabled(project: Project): Boolean {
    if (project.rootProject.hasProperty(GAV_ENABLE)) {
        return project.rootProject.properties[GAV_ENABLE].toString().toBoolean()
    }
    return true
}

fun getAarModules(project: Project): List<String> {
    if (project.rootProject.hasProperty(GAV_AAR_MODULES)) {
        return project.rootProject.properties[GAV_AAR_MODULES].toString().split(",")
    }
    return arrayListOf()
}

fun getGAV(text: String): GAV? {
    var groupId: String? = null
    var artifactId: String? = null
    var version: String? = null
    Regex(
        "upload\\s+\\{.+?groupId\\s*=\\s*\"(.+?)\".+?\\}",
        RegexOption.DOT_MATCHES_ALL
    )
        .find(text)?.groupValues?.let {
            groupId = it[1]
        }

    Regex(
        "upload\\s+\\{.+?artifactId\\s*=\\s*\"(.+?)\".+?\\}",
        RegexOption.DOT_MATCHES_ALL
    )
        .find(text)?.groupValues?.let {
            artifactId = it[1]
        }

    Regex(
        "upload\\s+\\{.+?version\\s*=\\s*\"(.+?)\".+?\\}",
        RegexOption.DOT_MATCHES_ALL
    )
        .find(text)?.groupValues?.let {
            version = it[1]
        }

    if (groupId.isNullOrEmpty() || artifactId.isNullOrEmpty() || version.isNullOrEmpty()) {
        return null
    }

    return GAV(groupId!!, artifactId!!, version!!)
}

data class GAV(val groupId: String, val artifactId: String, val version: String)
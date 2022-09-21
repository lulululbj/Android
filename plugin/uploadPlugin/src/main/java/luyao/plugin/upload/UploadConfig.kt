package luyao.plugin.upload

open class UploadConfig {
    var version: String = ""
    var artifactId: String = ""
    var groupId: String = ""
    var sourceJar: Boolean = true
    var hasPomDepend: Boolean = true



    // nexus 相关
    var nexusURL: String = ""
    var nexusName: String = ""
    var nexusPsw: String = ""

    override fun toString(): String {
        return "UploadConfig(version='$version', artifactId='$artifactId', groupId='$groupId', sourceJar=$sourceJar, hasPomDepend=$hasPomDepend, nexusURL='$nexusURL', nexusName='$nexusName', nexusPsw='$nexusPsw')"
    }

}
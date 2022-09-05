package luyao.android.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Description:
 * Author: luyao
 * Date: 2022/9/5 20:17
 */
class TestPlugin : Plugin<Project> {

    override fun apply(p0: Project) {
        println("test plugin")
    }
}
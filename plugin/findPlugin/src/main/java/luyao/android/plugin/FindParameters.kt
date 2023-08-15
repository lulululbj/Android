package luyao.android.plugin

import com.android.build.api.instrumentation.InstrumentationParameters
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input

/**
 * Description:
 * Author: luyao
 * Date: 2023/8/14 20:03
 */
interface FindParameters : InstrumentationParameters {
    @get:Input
    val buildType: Property<String>
}
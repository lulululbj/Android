package luyao.android.plugin

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import org.objectweb.asm.ClassVisitor

/**
 * Description:
 * Author: luyao
 * Date: 2023/8/14 20:03
 */
abstract class FindTransform : AsmClassVisitorFactory<FindParameters> {

    override fun createClassVisitor(
        classContext: ClassContext,
        nextClassVisitor: ClassVisitor
    ): ClassVisitor {
        val buildType = parameters.get().buildType.get()
        return FindClassVisitor(nextClassVisitor, buildType)
    }

    override fun isInstrumentable(classData: ClassData): Boolean {
        return !classData.className.contains("androidx.") && !classData.className.contains(".R")
    }
}
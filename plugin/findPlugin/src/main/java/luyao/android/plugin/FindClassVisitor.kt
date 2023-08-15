package luyao.android.plugin

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * Description:
 * Author: luyao
 * Date: 2023/8/14 20:12
 */
class FindClassVisitor(cv: ClassVisitor?, private val buildType: String) :
    ClassVisitor(Opcodes.ASM9, cv) {

    private var className: String = ""

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        super.visit(version, access, name, signature, superName, interfaces)
        this.className = name ?: ""
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        var mv = super.visitMethod(access, name, descriptor, signature, exceptions)
        val isAbstractMethod = (access and Opcodes.ACC_ABSTRACT) != 0
        val isNativeMethod = (access and Opcodes.ACC_NATIVE) != 0
        if (!isAbstractMethod && !isNativeMethod) {
            mv = FindMethodVisitor(
                mv,
                className,
            ).apply {
                buildType = this@FindClassVisitor.buildType
            }
        }
        return  mv
    }
}
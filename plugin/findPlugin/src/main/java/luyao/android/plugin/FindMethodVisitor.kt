package luyao.android.plugin

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.MethodNode

/**
 * Description:
 * Author: luyao
 * Date: 2023/8/14 20:22
 */
class FindMethodVisitor(
    methodVisitor: MethodVisitor,
    private val className: String,
) : MethodVisitor(Opcodes.ASM9, methodVisitor) {

    var buildType: String = ""

    override fun visitMethodInsn(
        opcodeAndSource: Int,
        owner: String,
        name: String,
        descriptor: String?,
        isInterface: Boolean
    ) {
        super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface)
        MethodFindUtils.filterAndAddMethod(buildType, className, name, owner, name)
    }
}
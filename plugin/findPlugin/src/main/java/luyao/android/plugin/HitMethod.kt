package luyao.android.plugin

/**
 * Description:
 * Author: luyao
 * Date: 2023/8/14 20:29
 */
data class HitMethod(
    val currentClass: String,
    val currentMethod: String,
    val invokeClassName: String,
    val invokeMethod: String
)
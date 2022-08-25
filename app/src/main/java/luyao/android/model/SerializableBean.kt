package luyao.android.model

import java.io.Serializable

/**
 * Description:
 * Author: luyao
 * Date: 2022/8/22 11:13
 */
data class SerializableBean(
    val name: String,
    val age: Int
) : Serializable
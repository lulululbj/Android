package luyao.android.ext

import android.app.Activity
import android.content.Intent

/**
 * Description:
 * Author: luyao
 * Date: 2022/8/5 17:19
 */

inline fun <reified T : Activity> Activity.startActivity(flag: Int? = null) {
    startActivity(Intent(this, T::class.java).apply {
        flag?.let { addFlags(it) }
    })
}

inline fun <reified T : Activity> Activity.startActivity(flags: List<Int>) {
    startActivity(Intent(this, T::class.java).apply {
        flags.forEach { addFlags(it) }
    })
}
package luyao.android.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.text.DecimalFormat

/**
 * Created by luyao
 * on 2020/6/1 11:08
 */
inline fun <reified T : Activity> Activity.start() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T : Activity> Activity.start(flag:Int) {
    startActivity(Intent(this, T::class.java).apply { addFlags(flag) })
}

fun getFormatFileSize(size: Long, unit: Int = 1000): String {
    val formatter = DecimalFormat("####.00")
    return when {
        size < 0 -> "0 B"
        size < unit -> "$size B"
        size < unit * unit -> "${formatter.format(size.toDouble() / unit)} KB"
        size < unit * unit * unit -> "${formatter.format(size.toDouble() / unit / unit)} MB"
        else -> "${formatter.format(size.toDouble() / unit / unit / unit)} GB"
    }
}
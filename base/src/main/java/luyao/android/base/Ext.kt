package luyao.android.base

import android.app.Activity
import android.content.Intent

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
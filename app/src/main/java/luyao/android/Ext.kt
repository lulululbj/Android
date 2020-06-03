package luyao.android

import android.app.Activity
import android.content.Intent

/**
 * Created by luyao
 * on 2020/6/1 11:08
 */
inline fun <reified T : Activity> Activity.start() {
    startActivity(Intent(this, T::class.java))
}
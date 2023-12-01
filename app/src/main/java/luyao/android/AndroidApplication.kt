package luyao.android

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

/**
 * Description:
 * Author: luyao
 * Date: 2023/8/21 17:13
 */
class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(CustomActivityLifecycleCallbacks())
    }
}
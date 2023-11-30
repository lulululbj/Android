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

        registerActivityLifecycleCallbacks(object: ActivityLifecycleCallbacks{
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.e("AndroidApplication", "onActivityCreated: ${activity.javaClass.simpleName}")
            }

            override fun onActivityStarted(activity: Activity) {
                Log.e("AndroidApplication", "onActivityStarted: ${activity.javaClass.simpleName}")
            }

            override fun onActivityResumed(activity: Activity) {
                Log.e("AndroidApplication", "onActivityResumed: ${activity.javaClass.simpleName}")
            }

            override fun onActivityPaused(activity: Activity) {
                Log.e("AndroidApplication", "onActivityPaused: ${activity.javaClass.simpleName}")
            }

            override fun onActivityStopped(activity: Activity) {
                Log.e("AndroidApplication", "onActivityStopped: ${activity.javaClass.simpleName}")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                Log.e("AndroidApplication", "onActivitySaveInstanceState: ${activity.javaClass.simpleName}")
            }

            override fun onActivityDestroyed(activity: Activity) {
                Log.e("AndroidApplication", "onActivityDestroyed: ${activity.javaClass.simpleName}")
            }

        })
    }
}
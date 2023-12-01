package luyao.android

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import android.util.Log

/**
 * Description:
 * Author: luyao
 * Date: 2023/11/30 14:55
 */
class CustomActivityLifecycleCallbacks : ActivityLifecycleCallbacks {

    private val tag = "AndroidApplication"

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Log.e(tag, "onActivityCreated: ${activity.javaClass.simpleName}")
    }

    override fun onActivityStarted(activity: Activity) {
        Log.e(tag, "onActivityStarted: ${activity.javaClass.simpleName}")
    }

    override fun onActivityResumed(activity: Activity) {
        Log.e(tag, "onActivityResumed: ${activity.javaClass.simpleName}")
    }

    override fun onActivityPaused(activity: Activity) {
        Log.e(tag, "onActivityPaused: ${activity.javaClass.simpleName}")
    }

    override fun onActivityStopped(activity: Activity) {
        Log.e(tag, "onActivityStopped: ${activity.javaClass.simpleName}")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Log.e(tag, "onActivitySaveInstanceState: ${activity.javaClass.simpleName}")
    }

    override fun onActivityDestroyed(activity: Activity) {
        Log.e(tag, "onActivityDestroyed: ${activity.javaClass.simpleName}")
    }


    /*
     * 把 bundle 序列化为二进制，当大小超过 1024 Bytes 时创建 UUID 和 Bundle 映射关系
     * 替换 Bundle 数据为 UUID，从而减小 Binder 看到的数据量
     */
    override fun onActivityPostSaveInstanceState(activity: Activity, outState: Bundle) {
        BundleUtil.putBundle(outState)
    }

    /*
     * 从 Bundle 中取出 UUID，并从内存 Map 中查找出映射的 Bundle，还原 ID 为 Bundle 数据
     */
    override fun onActivityPreCreated(activity: Activity, savedInstanceState: Bundle?) {
        BundleUtil.getBundle(savedInstanceState)
    }

}
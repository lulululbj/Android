package luyao.android.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by luyao
 * on 2020/6/1 10:01
 */
open class BaseLifecycleActivity() : AppCompatActivity(){

    private val TAG = "lifecycle"
    private val CLAZZ  = javaClass.simpleName

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e(TAG,"${CLAZZ}: onNewIntent")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG,"${CLAZZ}: onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG,"${CLAZZ}: onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG,"${CLAZZ}: onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG,"${CLAZZ}: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG,"${CLAZZ}: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG,"${CLAZZ}: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG,"${CLAZZ}: onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e(TAG,"${CLAZZ}: onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e(TAG,"${CLAZZ}: onRestoreInstanceState")
    }
}
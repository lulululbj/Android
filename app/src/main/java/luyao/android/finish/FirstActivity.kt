package luyao.android.finish

import android.os.Bundle
import android.os.Looper
import android.util.Log
import luyao.android.base.BaseLifecycleActivity
import luyao.android.base.start
import luyao.android.databinding.ActivityFirstBinding

/**
 * @author: luyao
 * @date：  2020/9/22 17:10
 */
class FirstActivity : BaseLifecycleActivity() {

    private val binding by lazy { ActivityFirstBinding.inflate(layoutInflater) }
    var startTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setPrinter()

        binding.goToSecond.setOnClickListener {
            start<SecondActivity>()
            finish()
            startTime = System.currentTimeMillis()
        }
    }

    override fun onPause() {
        super.onPause()
        Log.e("finish","onPause() 距离 finish() ：${System.currentTimeMillis() - startTime} ms")
    }

    override fun onStop() {
        super.onStop()
        Log.e("finish","onStop() 距离 finish() ：${System.currentTimeMillis() - startTime} ms")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("finish","onDestroy() 距离 finish() ：${System.currentTimeMillis() - startTime} ms")
    }

    private fun setPrinter(){
        Looper.getMainLooper().setMessageLogging { x: String ->
            Log.e("messageLogging",x)
        }
    }


}
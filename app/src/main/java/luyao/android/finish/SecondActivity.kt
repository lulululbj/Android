package luyao.android.finish

import android.os.Bundle
import luyao.android.base.BaseLifecycleActivity
import luyao.android.databinding.ActivitySecondBinding

/**
 * @author: luyao
 * @date：  2020/9/22 17:13
 */
class SecondActivity : BaseLifecycleActivity() {

    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        requestedOrientation
        postMessage()
    }

    private fun postMessage() {
        binding.secondBt.post {
            Thread.sleep(10)
            postMessage()
        }
    }
}
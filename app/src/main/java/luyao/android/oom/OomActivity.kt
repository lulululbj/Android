package luyao.android.oom

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import luyao.android.base.getFormatFileSize
import luyao.android.databinding.ActivityOomBinding

/**
 * @author: luyao
 * @date：  2020/9/2 11:19
 */
class OomActivity : AppCompatActivity() {

    private val binding by lazy { ActivityOomBinding.inflate(layoutInflater) }
    private var bytes = ByteArray(1024)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        refreshMemory()
    }

    private fun initView() {

        binding.allocate.setOnClickListener {
            try {
                bytes = ByteArray(bytes.size + 1024 * 1024 * 20)
                refreshMemory()
            } catch (e: OutOfMemoryError) {
                binding.oomError.text = "Catch OOM : \n ${e.message}"
                Log.e("oom", "Catch OOM : \n ${e.message}")
            }
        }

        binding.gc.setOnClickListener {
            Runtime.getRuntime().gc()
            System.runFinalization()
            lifecycleScope.launch {
                delay(500)
                refreshMemory()
            }
        }
    }

    private fun refreshMemory() {
        val totalMem = getFormatFileSize(Runtime.getRuntime().totalMemory())
        val availMem = getFormatFileSize(Runtime.getRuntime().freeMemory())
        binding.usageRam.text = "可用内存：$availMem/$totalMem"
    }
}
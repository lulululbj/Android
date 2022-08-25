package luyao.android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import luyao.android.adapter.MenuAdapter
import luyao.android.databinding.ActivityMainBinding
import luyao.android.model.Menu
import luyao.android.model.SerializableBean
import luyao.android.ui.AidlActivity
import luyao.android.ui.SerializeActivity
import luyao.android.ui.biometric.BiometricActivity
import luyao.android.ui.task.TaskActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val menuAdapter by lazy {
        MenuAdapter().apply {
            setItemClickListener {
                if (it.name == "序列化") {
                    startActivity(Intent(this@MainActivity, it.clazz).apply {
                        putExtra("serial", SerializableBean("Allen", 18))
                    })
                } else {
                    startActivity(Intent(this@MainActivity, it.clazz))
                }
            }
        }
    }

    private val menuList = arrayListOf(
        Menu("任务栈，返回栈，启动模式", TaskActivity::class.java),
        Menu("序列化", SerializeActivity::class.java),
        Menu("AIDL", AidlActivity::class.java),
        Menu("生物认证", BiometricActivity::class.java),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.menuRv.adapter = menuAdapter
        menuAdapter.submitList(menuList)
    }

    override fun onResume() {
        super.onResume()
        binding.taskId.text = "Task $taskId\n${getClassName()}"
    }

    private fun getClassName(): String {
        val name = this.toString()
        return name.substring(name.lastIndexOf(".") + 1)
    }
}
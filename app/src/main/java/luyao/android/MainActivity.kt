package luyao.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import luyao.android.adapter.MenuAdapter
import luyao.android.databinding.ActivityMainBinding
import luyao.android.model.Menu
import luyao.android.ui.task.TaskActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val menuAdapter by lazy {
        MenuAdapter().apply {
            setItemClickListener { startActivity(Intent(this@MainActivity, it.clazz)) }
        }
    }

    private val menuList = arrayListOf(
        Menu("任务栈，返回栈，启动模式", TaskActivity::class.java)
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
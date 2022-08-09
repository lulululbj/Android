package luyao.android.ui.task

import android.annotation.SuppressLint
import android.content.Intent
import com.hi.dhl.binding.viewbind
import luyao.android.base.BaseActivity
import luyao.android.databinding.ActivityTaskBinding
import luyao.android.ext.startActivity

/**
 * Description:
 * Author: luyao
 * Date: 2022/8/5 16:47
 */
open class TaskActivity : BaseActivity() {

    private val binding: ActivityTaskBinding by viewbind()

    @SuppressLint("SetTextI18n")
    override fun initView() {
        title = javaClass.simpleName
        binding.run {
            taskTv.text = "Task $taskId"
            activityTv.text = getClassName()

            // standard
            standard1.setOnClickListener { startActivity<StandTaskActivity1>(getFlags()) }
            standard2.setOnClickListener { startActivity<StandTaskActivity2>(getFlags()) }

            // standard + taskAffinity
            standardAndTaskAffinity.setOnClickListener {
                startActivity<StandardAndTaskAffinityActivity>(
                    getFlags()
                )
            }

            // singleTop
            singleTop.setOnClickListener { startActivity<SingleTopActivity>(getFlags()) }

            // singleTop + taskAffinity
            singleTopAndTaskAffinity.setOnClickListener {
                startActivity<SingleTopAndTaskAffinityActivity>(
                    getFlags()
                )
            }

            // singleTask
            singleTask.setOnClickListener { startActivity<SingleTaskActivity>(getFlags()) }

            // singleTask + taskAffinity
            singleTaskAndTaskAffinityX.setOnClickListener {
                startActivity<SingleTaskAndTaskAffinityActivityX>(
                    getFlags()
                )
            }
            singleTaskAndTaskAffinityY.setOnClickListener {
                startActivity<SingleTaskAndTaskAffinityActivityY>(
                    getFlags()
                )
            }

            // singleInstance
            singleInstance.setOnClickListener { startActivity<SingleInstanceActivity>(getFlags()) }

            // singleInstancePerTask
            singleInstancePerTask.setOnClickListener {
                startActivity<SingleInstancePerTaskActivity>(
                    getFlags()
                )
            }

        }
    }

    override fun initData() {
    }

    private fun getFlags(): ArrayList<Int> {
        binding.run {
            val flags = arrayListOf<Int>()
            if (newTaskCb.isChecked) {
                flags.add(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            if (singleTopCb.isChecked) {
                flags.add(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            if (clearTopCb.isChecked) {
                flags.add(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
            return flags
        }
    }

    private fun getClassName(): String {
        val name = this.toString()
        return name.substring(name.lastIndexOf(".") + 1)
    }
}
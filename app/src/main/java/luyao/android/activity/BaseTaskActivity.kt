package luyao.android.activity

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import luyao.android.base.BaseLifecycleActivity
import luyao.android.base.start
import luyao.android.databinding.ActivityTaskBinding

/**
 * Created by luyao
 * on 2020/6/1 14:36
 */
open class BaseTaskActivity : BaseLifecycleActivity() {

    private val binding by lazy { ActivityTaskBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var activity = this.toString()
        activity = activity.substring(
            activity.lastIndexOf(".") + 1,
            activity.length
        )
        binding.taskId.text = "taskId: $taskId \n$activity"
        initListener()
    }

    private fun initListener() {
        binding.run {
            standardA.setOnClickListener { start<StandardActivityA>() }
            standardB.setOnClickListener { start<StandardActivityB>() }
            singleTaskC.setOnClickListener { start<SingleTaskActivityC>() }
            singleTaskD.setOnClickListener { start<SingleTaskActivityD>() }
            singleTop.setOnClickListener { start<SingleTopActivity>() }
            singleInstance.setOnClickListener { start<SingleInstanceActivity>() }
            singleTaskY.setOnClickListener {
                val intent = Intent().apply {
                    component = ComponentName("luyao.android2", "luyao.android2.SingleTaskActivityY")
                }
                startActivity(intent)
            }

            // flag
            newTaskWithDefaultTaskAffinity.setOnClickListener {
                start<NewTaskWithDefaultTaskAffinityActivity>(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                )
            }

            newTaskWithAnotherTaskAffinity.setOnClickListener {
                start<NewTaskWithAnotherTaskAffinityActivity>(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                )
            }

            newTaskApp2.setOnClickListener {
                startActivity(Intent().apply {
                    component = ComponentName("luyao.android2", "luyao.android2.SingleTaskActivityY")
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }

            intentFlag.setOnClickListener {
                start<StandardActivityA>(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }

            allowTaskReparenting.setOnClickListener {
                startActivity(Intent().apply {
                    component = ComponentName("luyao.android2", "luyao.android2.AllowTaskReparentingActivity")
                })
            }
        }
    }
}
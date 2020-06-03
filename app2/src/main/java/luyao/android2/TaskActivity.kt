package luyao.android2

import android.os.Bundle
import luyao.android.base.BaseLifecycleActivity
import luyao.android.base.start
import luyao.android2.databinding.ActivityTask2Binding

/**
 * Created by luyao
 * on 2020/6/2 15:15
 */
open class TaskActivity : BaseLifecycleActivity() {

    private val binding by lazy { ActivityTask2Binding.inflate(layoutInflater) }

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
            standardE.setOnClickListener { start<StandardActivityE>() }
            standardF.setOnClickListener { start<StandardActivityF>() }
            singleTaskX.setOnClickListener { start<SingleTaskActivityX>() }
            singleTaskY.setOnClickListener { start<SingleTaskActivityY>() }
        }
    }

}
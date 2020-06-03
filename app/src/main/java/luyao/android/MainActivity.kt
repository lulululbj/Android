package luyao.android

import android.os.Bundle
import luyao.android.activity.StandardActivityA
import luyao.android.base.BaseLifecycleActivity
import luyao.android.base.start
import luyao.android.databinding.ActivityMainBinding

class MainActivity : BaseLifecycleActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListener()
    }

    private fun initListener(){
        binding.activity.setOnClickListener { start<StandardActivityA>() }
    }

}
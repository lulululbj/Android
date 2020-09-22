package luyao.android

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import luyao.android.activity.StandardActivityA
import luyao.android.base.BaseLifecycleActivity
import luyao.android.base.start
import luyao.android.context.ContextActivity
import luyao.android.databinding.ActivityMainBinding
import luyao.android.navigation.NavigationActivity
import luyao.android.oom.OomActivity

class MainActivity : BaseLifecycleActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListener()
    }

    private fun initListener() {
        binding.run {

            activity.setOnClickListener { start<StandardActivityA>() }

            dialog.setOnClickListener {
                AlertDialog.Builder(this@MainActivity).setTitle("Title").setView(R.layout.dialog)
                    .show()
            }

            navigation.setOnClickListener { start<NavigationActivity>() }
            context.setOnClickListener { start<ContextActivity>() }
            oom.setOnClickListener { start<OomActivity>() }
        }
    }
}
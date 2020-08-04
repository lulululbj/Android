package luyao.android.fragment

import android.os.Bundle
import androidx.fragment.app.commit
import luyao.android.base.BaseLifecycleActivity
import luyao.android.fragment.databinding.ActivityContainerBinding

class ContainerActivity : BaseLifecycleActivity() {

    private var tag = 0
    private val binding by lazy { ActivityContainerBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        SimpleFragment.newInstance(1)

        /**
         * addToBackStack 会把前一个 fragment 加入返回栈中。用户返回时，可导航到上一个 fragment。
         * 如果未调用，返回时会直接销毁上一个 fragment 。
         */
        binding.add.setOnClickListener {
            supportFragmentManager.commit {
                add(R.id.container, SimpleFragment.newInstance(tag++))
                if (binding.backStack.isChecked) addToBackStack(null)
            }
        }

        /**
         * add 新的 fragment 并不会影响上一个 fragment
         * replace 新的 fragment，上一个 fragment 回调 onDestroyView，但不会回调 onDestroy
         */
        binding.replace.setOnClickListener {
            supportFragmentManager.commit {
                replace(R.id.container, SimpleFragment.newInstance(tag++))
                if (binding.backStack.isChecked) addToBackStack(null)
            }
        }

        binding.pop.setOnClickListener { supportFragmentManager.popBackStack() }
    }
}
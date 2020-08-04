package luyao.android.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import luyao.android.databinding.ActivityNavigationBinding
import luyao.android.navigation.bottomNavigation.BottomNavigationActivity
import luyao.android.navigation.normalNavigation.NormalNavigationActivity
import luyao.android.start

class NavigationActivity : AppCompatActivity() {

    private val binding by lazy { ActivityNavigationBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {
            // 测试同层级 Navigation 的情况
            bottomNavigation.setOnClickListener { start<BottomNavigationActivity>() }

            normalNavigation.setOnClickListener { start<NormalNavigationActivity>() }
        }
    }
}
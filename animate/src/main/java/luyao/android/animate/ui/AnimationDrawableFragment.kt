package luyao.android.animate.ui

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import luyao.android.animate.R
import luyao.android.animate.databinding.FragmentAnimatedrawableBinding
import luyao.android.base.viewBindings

/**
 * Created by luyao
 * on 2020/6/9 9:45
 */
class AnimationDrawableFragment : Fragment(R.layout.fragment_animatedrawable) {

    private val binding by viewBindings(FragmentAnimatedrawableBinding::bind)
    private lateinit var animationDrawable: AnimationDrawable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.animateDrawableIcon.run {
            setBackgroundResource(R.drawable.animate_drawable)
            animationDrawable = background as AnimationDrawable
        }

        /**
         * 注意不能在 onCreate() 方法中调用 start() 方法
         * 因为此时 AnimationDrawable 还没 attach 到 window 上
         */
        binding.start.setOnClickListener { animationDrawable.start() }
        binding.stop.setOnClickListener { animationDrawable.stop() }
    }
}
package luyao.android.animate.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import luyao.android.animate.R
import luyao.android.animate.databinding.FragmentAnimatedvectordrawableBinding
import luyao.android.base.viewBindings

/**
 * Created by luyao
 * on 2020/6/9 11:02
 */
class AnimatedVectorDrawableFragment : Fragment(R.layout.fragment_animatedvectordrawable) {

    private val binding by viewBindings(FragmentAnimatedvectordrawableBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animationVector =
            AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.animatorvectordrawable)

        binding.run {
            animatedVectorDrawableIcon.setImageDrawable(animationVector)
            start.setOnClickListener { animationVector?.start() }
            stop.setOnClickListener { animationVector?.stop() }
        }
    }
}
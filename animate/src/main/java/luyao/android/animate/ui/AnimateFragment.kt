package luyao.android.animate.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import luyao.android.animate.R
import luyao.android.animate.databinding.FragmentAnimateBinding
import luyao.android.base.start
import luyao.android.base.viewBindings

/**
 * Created by luyao
 * on 2020/6/9 10:11
 */
class AnimateFragment : Fragment(R.layout.fragment_animate) {

    private val binding by viewBindings(FragmentAnimateBinding::bind)

    private val animationDrawableFragment by lazy { AnimationDrawableFragment() }
    private val animatedVectorDrawableFragment by lazy { AnimatedVectorDrawableFragment() }
    private val propertyAnimationFragment by lazy { PropertyAnimationFragment() }
    private val crossFadeFragment by lazy { CrossFadeFragment() }
    private val moveViewFragment by lazy { MoveViewFragment() }
    private val zoomAnimationFragment by lazy { ZoomAnimationFragment() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        binding.run {
            animateDrawable.setOnClickListener { navigateTo(animationDrawableFragment) }
            animatedVectorDrawable.setOnClickListener { navigateTo(animatedVectorDrawableFragment) }
            propertyAnimation.setOnClickListener { navigateTo(propertyAnimationFragment) }
            crossFade.setOnClickListener { navigateTo(crossFadeFragment) }
            cardFlip.setOnClickListener { activity?.start<CardFlipActivity>()}
            moveView.setOnClickListener { navigateTo(moveViewFragment) }
            zoomAnimation.setOnClickListener { navigateTo(zoomAnimationFragment) }
        }
    }

    private fun navigateTo(fragment: Fragment) {
        activity?.supportFragmentManager?.commit {
            replace(R.id.animateMainContainer, fragment)
            addToBackStack(null)
        }
    }

}
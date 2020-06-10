package luyao.android.animate.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import luyao.android.animate.R
import luyao.android.animate.databinding.FragmentCrossfadeBinding
import luyao.android.base.viewBindings

/**
 * Created by luyao
 * on 2020/6/10 10:26
 */
class CrossFadeFragment : Fragment(R.layout.fragment_crossfade) {

    private val binding by viewBindings(FragmentCrossfadeBinding::bind)

    private  var duration: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        duration = resources.getInteger(android.R.integer.config_mediumAnimTime)
        binding.content.visibility = View.GONE

        crossFade()
    }

    private fun crossFade() {
        binding.content.apply {
            alpha = 0f
            visibility = View.VISIBLE

            animate().alpha(1f).setDuration(duration.toLong()).setListener(null)
        }

        binding.loadingSpinner.animate().alpha(0f).setDuration(duration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    binding.loadingSpinner.visibility = View.GONE
                }
            })
    }
}
package luyao.android.animate.ui

import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.view.animation.PathInterpolator
import androidx.fragment.app.Fragment
import luyao.android.animate.R
import luyao.android.animate.databinding.FragmentMoveViewBinding
import luyao.android.base.viewBindings

/**
 * Created by luyao
 * on 2020/6/10 13:25
 */
class MoveViewFragment : Fragment(R.layout.fragment_move_view) {

    private val binding by viewBindings(FragmentMoveViewBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.move.setOnClickListener {
//            ObjectAnimator.ofFloat(binding.animationView,"translationX",100f).apply {
//                duration = 2000
//                start()
//            }

            val path = Path().apply {
                arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true)
            }
            ObjectAnimator.ofFloat(binding.animationView,View.X,View.Y,path).apply {
                duration = 2000
                start()
            }
        }
    }
}
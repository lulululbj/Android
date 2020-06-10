package luyao.android.animate.ui

import android.animation.*
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment
import luyao.android.animate.R
import luyao.android.animate.databinding.FragmentPropertyAnimationBinding
import luyao.android.base.viewBindings

/**
 * Created by luyao
 * on 2020/6/9 14:39
 */
class PropertyAnimationFragment : Fragment(R.layout.fragment_property_animation) {

    private lateinit var valueAnimator: ValueAnimator
    private lateinit var objectAnimator: ValueAnimator
    private val binding by viewBindings(FragmentPropertyAnimationBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        valueAnimator = ValueAnimator.ofFloat(0f, 100f).apply {
            duration = 300
            addUpdateListener { value ->
                binding.animationView.translationX = value.animatedValue as Float
            }
        }

        /**
         * 1. 属性必须有 set/get 方法
         * 2. 参数列表只有一个值的话，默认被认为是结束值
         * 3. 有些情况下需要手动 invalidate() 刷新页面
         */
        objectAnimator =
            ObjectAnimator.ofFloat(binding.animationView, "translationX", 100f, 400f).apply {
                duration = 600
                interpolator = AccelerateInterpolator()
            }

        binding.valueAnimator.setOnClickListener {
            valueAnimator.start()
        }
        binding.objectAnimator.setOnClickListener {
            objectAnimator.start()
        }

        binding.animatorSet.setOnClickListener {
            val fadeAnim = ObjectAnimator.ofFloat(binding.animationView, "alpha", 1f, 0f).apply {
                duration = 600
            }

            AnimatorSet().apply {
                play(objectAnimator).after(valueAnimator)
                play(objectAnimator).with(fadeAnim)
                start()
            }
        }

        binding.keyFrame.setOnClickListener {
            val kf0 = Keyframe.ofFloat(0f, 0f)
            val kf1 = Keyframe.ofFloat(0.2f, 180f)
            val kf2 = Keyframe.ofFloat(1f, 360f)
            val pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2)

            ObjectAnimator.ofPropertyValuesHolder(binding.animationView, pvhRotation).apply {
                duration = 2000
                start()
            }
        }

        binding.viewPropertyAnimator.setOnClickListener {
//            val animX = ObjectAnimator.ofFloat(binding.animationView, "x", 50f)
//            val animY = ObjectAnimator.ofFloat(binding.animationView, "y", 100f)
//            AnimatorSet().apply {
//                playTogether(animX, animY)
//                start()
//            }

//            val pvhX = PropertyValuesHolder.ofFloat("x", 50f)
//            val pvhY = PropertyValuesHolder.ofFloat("y", 100f)
//            ObjectAnimator.ofPropertyValuesHolder(binding.animationView, pvhX, pvhY).start()

            binding.animationView.animate().x(50f).y(100f).start()
        }

        binding.xmlAnimation.setOnClickListener {
            (AnimatorInflater.loadAnimator(context,R.animator.xml_animation) as AnimatorSet).apply {
                setTarget(binding.animationView)
                start()
            }
        }
    }
}
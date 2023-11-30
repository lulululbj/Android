package luyao.android.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.TouchDelegate
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import luyao.android.databinding.ActivityTouchdelegateBinding

/**
 * Description:
 * Author: luyao
 * Date: 2023/11/6 16:57
 */
class TouchDelegateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTouchdelegateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTouchdelegateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.view1.setOnClickListener {
            Snackbar.make(binding.root, "view1", Snackbar.LENGTH_SHORT).show()
        }

        binding.view2.setOnClickListener {
            Snackbar.make(binding.root, "view2", Snackbar.LENGTH_SHORT).show()
        }

        binding.run {
            root.post {

                val rect1 = Rect()
                view1.isEnabled = true
                view1.getHitRect(rect1)
                rect1.top -= 100
                val touchDelegate1 = TouchDelegate(rect1, view1)

                val rect2 = Rect()
                view2.isEnabled = true
                view2.getHitRect(rect2)
                rect2.top -= 100
                val touchDelegate2 = TouchDelegate(rect2, view2)

                val touchDelegateComposite = TouchDelegateComposite(view1.parent as View)
                touchDelegateComposite.addDelegate(touchDelegate1)
                touchDelegateComposite.addDelegate(touchDelegate2)

                (view1.parent as View).touchDelegate = touchDelegateComposite
//                (view2.parent as View).touchDelegate = touchDelegateComposite

            }
        }
    }


}
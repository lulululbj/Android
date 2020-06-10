package luyao.android.animate.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import luyao.android.animate.R

/**
 * Created by luyao
 * on 2020/6/10 10:47
 */
class CardFlipActivity : AppCompatActivity(R.layout.fragment_card_flip) {

    private var showBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, CardFrontFragment())
                .commit()
        }

        val view = findViewById<Button>(R.id.flip)
        view.setOnClickListener { flipCard() }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val cx = view.width / 2
            val cy = view.height / 2

            val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

            view.post {
                val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius)
                view.visibility = View.VISIBLE
                anim.start()
            }
        }
    }

    private fun flipCard() {
        if (showBack) {
            showBack = false
            supportFragmentManager.popBackStack()
            return
        }

        showBack = true

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.animator.card_flip_right_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_left_in,
                R.animator.card_flip_left_out
            )
            .replace(R.id.container, CardBackFragment())
            .addToBackStack(null)
            .commit()
    }

    /**
     * A fragment representing the front of the card.
     */
    class CardFrontFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View = inflater.inflate(R.layout.fragment_card_front, container, false)
    }

    /**
     * A fragment representing the back of the card.
     */
    class CardBackFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View = inflater.inflate(R.layout.fragment_card_back, container, false)
    }
}
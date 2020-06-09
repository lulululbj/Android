package luyao.android.animate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow

class AnimateActivity : AppCompatActivity(R.layout.activity_animate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            supportFragmentManager.commitNow {
                replace(R.id.animateMainContainer,AnimateFragment())
            }
    }
}

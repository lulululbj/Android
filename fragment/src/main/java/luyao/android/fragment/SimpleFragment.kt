package luyao.android.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main.fragment_simple.*
import luyao.android.base.BaseLifecycleFragment

class SimpleFragment : BaseLifecycleFragment(R.layout.fragment_simple) {

    private val tag by lazy { arguments?.getInt("tag") }
    override val CLAZZ: String
        get() = "${javaClass.simpleName} ${tag}"

    companion object {
        fun newInstance(tag:Int):SimpleFragment{
            return SimpleFragment().apply {
                arguments = bundleOf("tag" to tag)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        simpleBt.text = "Fragment $tag"
    }
}
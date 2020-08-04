package luyao.android.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open class BaseLifecycleFragment(layoutId: Int) : Fragment(layoutId){

    private val TAG = "lifecycle"
    open val CLAZZ  = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG,"${CLAZZ}: onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(TAG,"${CLAZZ}: onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(TAG,"${CLAZZ}: onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG,"${CLAZZ}: onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e(TAG,"${CLAZZ}: onActivityCreated")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG,"${CLAZZ}: onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG,"${CLAZZ}: onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(TAG,"${CLAZZ}: onDetach")
    }
}
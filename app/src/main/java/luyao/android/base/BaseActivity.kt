package luyao.android.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


/**
 * Description:
 * Author: luyao
 * Date: 2022/4/21 20:39
 */
abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
        observe()
    }

    abstract fun initView()
    abstract fun initData()
    open fun observe() {}

}
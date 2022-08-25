package luyao.android.ui

import android.animation.ObjectAnimator
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Process
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hi.dhl.binding.viewbind
import luyao.android.IRemoteService
import luyao.android.R
import luyao.android.databinding.ActivityAidlBinding

/**
 * Description:
 * Author: luyao
 * Date: 2022/8/23 15:42
 */
class AidlActivity : AppCompatActivity() {

    private val binding: ActivityAidlBinding by viewbind()
    private var iRemoteService: IRemoteService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.run {

            bindService.setOnClickListener {
                bindService(Intent().apply {
                    component = ComponentName("luyao.android2", "luyao.android2.RemoteService")
                }, object : ServiceConnection {
                    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                        iRemoteService = IRemoteService.Stub.asInterface(service)
                    }

                    override fun onServiceDisconnected(name: ComponentName?) {
                        iRemoteService = null
                    }
                }, Context.BIND_AUTO_CREATE)
            }

            getRemotePid.setOnClickListener {
                iRemoteService?.let {
                    result.text = "current pid : ${Process.myPid()}\nRemote pid : ${it.pid}"
                }
            }

        }
    }
}
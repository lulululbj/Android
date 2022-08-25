package luyao.android2

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process
import luyao.android.IRemoteService

/**
 * Description:
 * Author: luyao
 * Date: 2022/8/23 15:38
 */
class RemoteService : Service() {

    private val binder = object : IRemoteService.Stub() {
        override fun getPid() = Process.myPid()

        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {

        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
    }


}
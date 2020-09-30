package luyao.android.context

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import luyao.android.finish.FirstActivity

/**
 * @author: luyao
 * @date：  2020/9/29 16:16
 */
class ContextBroadCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        context.startActivity(Intent(context,FirstActivity::class.java).apply {

        })
    }
}
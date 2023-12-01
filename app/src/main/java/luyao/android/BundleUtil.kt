package luyao.android

import android.os.Bundle
import android.os.Parcel
import android.util.Log
import java.util.UUID

/**
 * Description:
 * Author: luyao
 * Date: 2023/11/30 15:02
 */
object BundleUtil {

    private val bundleMap = mutableMapOf<String, Bundle>()

    fun putBundle(outState: Bundle) {

        val bytes = bundleToBytes(outState)
        if (bytes != null && bytes.size > 1024) {
            val uuid = UUID.randomUUID().toString()
            bundleMap[uuid] = Bundle(outState)
            outState.clear()
            outState.putString("bundle_uuid", uuid)
            Log.e("BundleUtil", "putBundle: $uuid size: ${bytes.size}")
        }
    }

    fun getBundle(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val uuid = savedInstanceState.getString("bundle_uuid")
            if (uuid != null) {
                val bundle = bundleMap[uuid]
                if (bundle != null) {
                    Log.e("BundleUtil", "getBundle: $uuid")
                    savedInstanceState.clear()
                    savedInstanceState.putAll(bundle)
                }
            }
        }
    }

    private fun bundleToBytes(outState: Bundle): ByteArray? {
        val parcel = Parcel.obtain()
        try {
            parcel.writeBundle(outState)
            return parcel.marshall()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            parcel.recycle()
        }
        return null
    }
}
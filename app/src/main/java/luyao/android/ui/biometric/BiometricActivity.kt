package luyao.android.ui.biometric

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.hi.dhl.binding.viewbind
import luyao.android.databinding.ActivityBiometricBinding

/**
 * Description:
 * Author: luyao
 * Date: 2022/8/25 10:48
 */
class BiometricActivity : AppCompatActivity() {

    private val TAG = "biometric"
    private val binding: ActivityBiometricBinding by viewbind()

    private lateinit var biometricPrompt: BiometricPrompt
    private val biometricHelper by lazy { BiometricHelper(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biometricPrompt = createBiometricPrompt()
        initView()
    }

    private fun initView() {
        binding.run {
            biometricSupport.text = "Support ${biometricHelper.getBiometricType()}"
            biometric.setOnClickListener {
                if (biometricHelper.biometricEnable()
                ) {
                    biometricPrompt.authenticate(createPromptInfo())
                } else {
                    Snackbar.make(root, "不支持生物识别", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun createBiometricPrompt(): BiometricPrompt {
        val executor = ContextCompat.getMainExecutor(this)
        val callback =
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Log.e(TAG, "$errorCode $errString")
                    if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                        Snackbar.make(binding.root, "使用密码登录", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Log.e(TAG, "onAuthenticationFailed")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Snackbar.make(binding.root, "解锁成功", Toast.LENGTH_SHORT).show()
                }
            }
        return BiometricPrompt(this, executor, callback)
    }

    private fun createPromptInfo(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle("解锁")
            .setSubtitle("Subtitle")
            .setDescription("Description")
            .setConfirmationRequired(false)
            .setNegativeButtonText("密码登录")
            .build()
    }


}
package luyao.android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import luyao.android.databinding.ActivitySerializeBinding
import luyao.android.model.SerializableBean
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectOutputStream

/**
 * Description:
 * Author: luyao
 * Date: 2022/8/22 10:55
 */
class SerializeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySerializeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySerializeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        (intent?.getSerializableExtra("serial") as SerializableBean?)?.let {
            binding.serializeTv.text = it.toString()
        }
    }

    private fun initView() {
        binding.run {
            val serializableBean = SerializableBean("test", 18)
            serializableToMemory.setOnClickListener {
                val baos = ByteArrayOutputStream()
                try {
                    val oos = ObjectOutputStream(baos)
                    oos.writeObject(serializableBean)
                    oos.close()
                    binding.serializeTv.text = String(baos.toByteArray())
                } catch (ioe: IOException) {
                   binding.serializeTv.text = ioe.message
                }
            }

            parcelableToMemory.setOnClickListener {

            }

            serializableToFile.setOnClickListener {

            }

            parcelableToFile.setOnClickListener {

            }
        }
    }
}
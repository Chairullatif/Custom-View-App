package com.khoirullatif.customviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.khoirullatif.customviewapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private lateinit var btnSubmit: Button
//    private lateinit var edtName: EditText

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMyButtonEnable()

        binding.edtName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                setMyButtonEnable()
            }

            override fun afterTextChanged(p0: Editable?) {
//                TODO("Not yet implemented")
            }

        })
        
        binding.btnSubmit.setOnClickListener { Toast.makeText(this@MainActivity, binding.edtName.text, Toast.LENGTH_SHORT).show() }
    }

    private fun setMyButtonEnable() {
        val result = binding.edtName.text
        binding.btnSubmit.isEnabled = result != null && result.toString().isNotEmpty()
    }
}
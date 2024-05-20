package com.example.modul_2_11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.example.modul_2_11.databinding.ActivityMainBinding
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = Repository(this).getText()

        binding.save.setOnClickListener {
            Repository(this).saveText(binding.inputText.text.toString())
            binding.textView.text = Repository(this).getText()
        }
        binding.clear.setOnClickListener {
            Repository(this).clearText()
        }

    }

}
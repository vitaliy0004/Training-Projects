package com.example.m_1_11_immersed_shared_preference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.m_1_11_immersed_shared_preference.databinding.ActivityMainBinding

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
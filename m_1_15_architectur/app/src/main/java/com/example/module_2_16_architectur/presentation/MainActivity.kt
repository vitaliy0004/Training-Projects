package com.example.module_2_16_architectur.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.module_2_16_architectur.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
package com.example.m_1_16_recycler_view.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.m_1_16_recycler_view.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
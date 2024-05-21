package com.example.m_2_3_paging_library.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.m_2_3_paging_library.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
package com.example.module_2_13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.module_2_13.ui.main.HelperFragment
import com.example.module_2_13_JSON.R

class HelperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helper)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HelperFragment.newInstance())
                .commitNow()
        }
    }
}
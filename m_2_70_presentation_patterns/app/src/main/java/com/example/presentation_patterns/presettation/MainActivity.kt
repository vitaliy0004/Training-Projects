package com.example.presentation_patterns.presettation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.presentation_patterns.R
import com.example.presentation_patterns.di.AppComponent
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var appComponent: AppComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent = (applicationContext as App).appComponent
        appComponent.inject(this)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val navController = findNavController(R.id.fragmentController)
        bottomNavigation.setupWithNavController(navController)
    }
}
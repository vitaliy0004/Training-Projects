package com.example.dependency_injection.presentation


import android.graphics.Color
import com.example.dependency_injection.App
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.dependency_injection.R
import com.example.dependency_injection.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    var koin = false

    @Inject
    lateinit var factory: Factory
    private val viewModelDagger: MainViewModel by viewModels { factory }
    private val viewModelKoin by viewModel<MainViewModel>()

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (applicationContext as App).appComponent.inject(this)
        binding.dagger.setOnClickListener {
            koin = false
            viewModelDagger.createBike(getString(R.string.dagger), Color.BLACK, (0..99999).random())
        }
        binding.koin.setOnClickListener {
            koin = true
            viewModelKoin.createBike(getString(R.string.koin), Color.BLUE, (0..99999).random())
        }
        lifecycleScope.launch {
            val viewModel = if (koin) viewModelDagger
            else viewModelKoin
            viewModel.bikes.collect { bike ->
                Toast.makeText(
                    applicationContext,
                    "Номер велосипеда:${bike.logo},\n" +
                            "Номер рамы:${bike.frame.number},\n" +
                            "номер первого колеса:${bike.wheels[0].wheel}\n" +
                            "номер второго колеса:${bike.wheels[1].wheel}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
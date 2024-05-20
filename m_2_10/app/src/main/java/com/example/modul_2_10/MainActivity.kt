package com.example.modul_2_10

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.modul_2_10.databinding.ActivityMainBinding
import com.google.android.material.slider.Slider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.Key
import kotlin.concurrent.thread
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var countdown = 100
    var chak = false
    val KEY_COUNTDOWN ="kye1"
    val KEY_MAX ="kye2"
    val KEY_PROGRESS ="kye3"
    val KEY_BOOLEAN ="kye4"
    @SuppressLint("SuspiciousIndentation")
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState?.let {bundl->

            binding.progress.max = bundl.getInt(KEY_MAX,binding.ferst.value.toInt())
            binding.progress.progress = bundl.getInt(KEY_PROGRESS,60)
            chak = bundl.getBoolean(KEY_BOOLEAN, chak)
            countdown  = bundl.getInt(KEY_COUNTDOWN, 0)
            binding.textProgress.text = binding.progress.progress.toString()
            Log.d(TAG,"countdown")
            lifecycleScope.launch { timerControl()}
            Log.d(TAG,"countdown")
        }



        binding.button.setOnClickListener {
            chak = chak != true
            lifecycleScope.launch {
                timerControl()
            }
        }
        binding.ferst.addOnChangeListener { slider, value, fromUser ->

             if (countdown == 100) {
                 binding.textProgress.text = binding.ferst.value.toInt().toString()
                 binding.progress.max = binding.ferst.value.toInt()
                 binding.progress.progress = binding.ferst.value.toInt()
             }
        }

        }

    private suspend fun timerControl(){


        while (binding.progress.progress != 0) {
            if (binding.progress.progress != binding.progress.max)    binding.ferst.isEnabled = false
            countdown = 0
            if (!chak) break
            binding.progress.progress -= 1
            if (binding.progress.progress != binding.progress.max)    binding.ferst.isEnabled = false
            binding.textProgress.text = binding.progress.progress.toString()
            delay(1000)
        }
        if (binding.progress.progress == 0) {
            binding.progress.progress = binding.progress.max
            binding.textProgress.text = binding.progress.progress.toString()
            binding.ferst.isEnabled = true
            countdown = 100
            chak = false
        }
}

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_COUNTDOWN,countdown )
        outState.putInt(KEY_MAX,binding.ferst.value.toInt())
        outState.putInt(KEY_PROGRESS,binding.progress.progress)
        outState.putBoolean(KEY_BOOLEAN,chak)
        super.onSaveInstanceState(outState)

    }
}





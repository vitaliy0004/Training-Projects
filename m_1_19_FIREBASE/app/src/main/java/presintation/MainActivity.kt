package presintation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.module_2_19_firebase.R
import com.example.module_2_19_firebase.databinding.ActivityMainBinding
import com.google.firebase.crashlytics.FirebaseCrashlytics
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
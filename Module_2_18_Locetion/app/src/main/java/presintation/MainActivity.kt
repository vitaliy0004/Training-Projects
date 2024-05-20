package presintation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.module_2_18_locetion.R
import com.example.module_2_18_locetion.databinding.ActivityMainBinding
import com.google.android.gms.maps.SupportMapFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        }
    }

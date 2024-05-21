package presintation


import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.module_2_19_firebase.R
import com.example.module_2_19_firebase.databinding.FragmentBinding
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import java.lang.Exception

class Fragment : Fragment() {
    private lateinit var binding:FragmentBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            createNotification()
            try {
                FirebaseCrashlytics.getInstance().log("ошибка")
                throw Exception("Error")
            }catch (e:Exception){
                FirebaseCrashlytics.getInstance().recordException(e)
            }
        }
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            Log.d("registration token:  ", it.result)
        }
    }

    @SuppressLint("MissingPermission")
   fun createNotification(){
       val intent = Intent(requireContext(),MainActivity::class.java)
       val pendingIntent = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
           PendingIntent.getActivity(requireContext(),0,intent,PendingIntent.FLAG_IMMUTABLE)
       else
           PendingIntent.getActivity(requireContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
       val notification = NotificationCompat.Builder(requireContext(),App.NOTIFICATION_CHANNEL_ID)
           .setSmallIcon(R.drawable.baseline_notifications_active_24)
           .setContentTitle("My first")
           .setContentText("Description")
           .setPriority(NotificationCompat.PRIORITY_DEFAULT)
           .setContentIntent(pendingIntent)
           .setAutoCancel(true)
           .build()

       NotificationManagerCompat.from(requireContext()).notify(NOTIFICATION_ID,notification)
   }
    companion object{
        private const val NOTIFICATION_ID = 1000
    }

}
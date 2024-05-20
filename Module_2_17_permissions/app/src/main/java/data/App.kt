package data

import android.app.Application
import androidx.room.Room

class App:Application() {
    lateinit var db: Database
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "db"
        ).build()
    }
}


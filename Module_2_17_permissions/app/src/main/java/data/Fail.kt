package data

import android.provider.ContactsContract.RawContacts.Data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fail")
data class Fail(
    @PrimaryKey
    @ColumnInfo(name = "failKay")
    val fail:String,
    @ColumnInfo(name = "data")
    val data: String
)

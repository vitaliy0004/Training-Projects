package data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FailDao {
    @Query("SELECT * FROM `fail`")
    fun getFail(): Flow<List<Fail>>
    @Insert
    suspend fun addFail(fail:Fail)
    @Delete
    suspend fun deleteFail(fail:List<Fail>)
    @Update
    suspend fun update(nowFail: Fail)
}
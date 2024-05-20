package com.example.modul_2__15.ui.main

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsDao {
    //для получения всего списка
    @Query("SELECT * FROM words")
    //Flow чтобы следить за изменениями в таблице
    fun  getWords(): Flow<List<Words>>

    // вставлять значения
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWord(words: Words)
    // удалять
    @Delete
    suspend fun delete(words: List<Words>)
    // обновлять
    @Update
    suspend fun update(words: Words)
}
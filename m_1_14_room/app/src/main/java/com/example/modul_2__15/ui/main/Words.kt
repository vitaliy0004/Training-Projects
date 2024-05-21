package com.example.modul_2__15.ui.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//название таблицы ели его не указать в Entity, то он будет как и класс, но это не рекомендованно делать
@Entity(tableName = "words")
data class Words(
    @PrimaryKey
    //сохраняет только уникальные ключи; autoGenerate хранит количество значений(id:1; id:2 ...)
    @ColumnInfo(name= "word")
    val word:String,
    @ColumnInfo(name= "number")
    val number:Int
)

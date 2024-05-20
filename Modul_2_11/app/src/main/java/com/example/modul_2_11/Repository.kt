package com.example.modul_2_11

import android.content.Context
import android.content.SharedPreferences

import java.io.FileOutputStream

private  val PREFS_NAME  = "prefsName"
private  val SHARED_PREFS_KEY  = "sharedPrefsKey"
 private var localVal = ""

class Repository(context: Context){

    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//будет доставать значение из SharedPreference
   fun getDataFromSharedPreference(): String?{

       return sharedPref.getString(SHARED_PREFS_KEY ," ")
    }
    // будет доставать значение, возвращать значение локальной переменной
    fun getDataFromLocalVariable(): String?{
        return localVal
    }


// будет записывать значения и в SharedPreference, и в локальную переменную.
  fun saveText(text: String){
       val  editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(SHARED_PREFS_KEY,text).apply()
        localVal = text
    }


//clearText() — будет очищать значение и в SharedPreference, и в локальной переменной.
    fun clearText(){
        val  editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
        localVal = ""
    }

    //getText(): String — будет доставать значение из источников: сначала попытается взять
    // значение локальной переменной; если оно null, то попытаемся взять значение из SharedPreference.
    fun getText(): String{
       return when{
           getDataFromLocalVariable() != null ->  getDataFromLocalVariable()!!
           getDataFromSharedPreference() != null -> getDataFromSharedPreference()!!
           else -> "нет данных"
       }
    }


}
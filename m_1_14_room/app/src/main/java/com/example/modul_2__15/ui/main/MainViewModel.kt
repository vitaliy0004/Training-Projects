package com.example.modul_2__15.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel (private  val wordsDao: WordsDao) : ViewModel() {
    val allWords = this.wordsDao.getWords().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun addButton(text: String) {
        viewModelScope.launch {
            wordsDao.addWord(Words(word = text, number =  1))
        }
        allWords.value.forEach {
            if (text == it.word) {
                viewModelScope.launch {
                    wordsDao.update(it.copy(number = it.number + 1))
                }
            }
        }
    }

    fun onDelete() {
        viewModelScope.launch{
            allWords.value.let { wordsDao.delete(it)}

        }
    }

}
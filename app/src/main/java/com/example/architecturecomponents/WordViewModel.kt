package com.example.architecturecomponents

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.architecturecomponents.Model.Word
import com.example.architecturecomponents.Repositories.WordRepository
import com.example.architecturecomponents.Room.WordRoomDatabase
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application): AndroidViewModel(application) {

    private val repository : WordRepository
    val allWords : LiveData<List<Word>>

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }
    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(word)
    }
}
package com.example.architecturecomponents.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.architecturecomponents.Dao.wordDao
import com.example.architecturecomponents.Model.Word

class WordRepository(private val wordDao: wordDao){

    val allWords : LiveData<List<Word>> = wordDao.getAllWords()

    @WorkerThread
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }

}
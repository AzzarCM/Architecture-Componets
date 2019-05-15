package com.example.architecturecomponents.Dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import com.example.architecturecomponents.Model.Word

interface wordDao {

     @Query("SELECT * FROM word_table ORDER BY word ASC")
     fun getAllWords(): LiveData<List<Word>>

     @Insert
     suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()

}
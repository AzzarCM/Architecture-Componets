package com.example.architecturecomponents.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.architecturecomponents.Dao.wordDao
import com.example.architecturecomponents.Model.Word
import java.security.AccessControlContext

@Database(entities = [Word::class],version = 1)
public abstract class WordRoomDatabase : RoomDatabase(){
    abstract fun wordDao() : wordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}
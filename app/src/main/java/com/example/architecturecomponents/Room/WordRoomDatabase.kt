package com.example.architecturecomponents.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.architecturecomponents.Dao.WordDao
import com.example.architecturecomponents.Model.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Word::class],version = 1)
public abstract class WordRoomDatabase : RoomDatabase(){

    abstract fun wordDao() : WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
        ): WordRoomDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ): RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO){
                        populateDatabase(database.wordDao())
                    }
                }
            }
        }
        suspend fun populateDatabase(wordDao: WordDao){
            wordDao.deleteAll()
            var word = Word("hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)
            word = Word("Karen")
            wordDao.insert(word)
        }
    }

}
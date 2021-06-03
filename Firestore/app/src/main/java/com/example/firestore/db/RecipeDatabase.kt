package com.example.firestore.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firestore.models.data.RecipeItem

@Database(entities = [RecipeItem::class],version = 1)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun getArticleDao(): RecipeDao

    companion object {
        var instance: RecipeDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it}
        }
        private fun createDatabase(context: Context)=
                Room.databaseBuilder(context.applicationContext,
                        RecipeDatabase::class.java,
                        "recipe_db.db")
                        .build()
    }
}
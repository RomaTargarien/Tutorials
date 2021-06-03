package com.example.firestore.models.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "recipes")
data class RecipeItem(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val image: String,
        val likes: Int,
        val title: String
) : Serializable
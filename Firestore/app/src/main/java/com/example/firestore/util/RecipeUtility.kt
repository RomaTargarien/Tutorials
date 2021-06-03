package com.example.firestore.util

import java.util.*

object RecipeUtility {
    fun getFormatedQuery(list: List<String>): String {
        var query = ""
        for (i in 0..list.size-1){
            if (i == list.size -1){
                query += list[i]
            } else {
                query += "${list[i]},+"
            }
        }
        return query.toLowerCase(Locale.ROOT)
    }
}
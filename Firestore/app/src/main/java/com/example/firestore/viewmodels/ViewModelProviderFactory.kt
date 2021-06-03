package com.example.firestore.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firestore.repository.RecipeRepository

class ViewModelProviderFactory(val app: Application,val recipeRepository: RecipeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductsViewModel(app,recipeRepository) as T
    }

}
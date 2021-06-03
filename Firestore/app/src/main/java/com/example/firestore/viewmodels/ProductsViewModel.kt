package com.example.firestore.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.firestore.models.Product
import com.example.firestore.models.data.RecipeItem
import com.example.firestore.models.data.RecipeTutorial
import com.example.firestore.models.data.Recipes
import com.example.firestore.repository.RecipeRepository
import com.example.firestore.util.Resource
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_recipe.*
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductsViewModel(val app: Application,val recipeRepository: RecipeRepository) : AndroidViewModel(app){
    val listProducts = MutableLiveData<List<Product>>()
    val productsForRecipe = mutableListOf<Product>()
    val count = MutableLiveData<Int>()
    val productsForRecipeLive = MutableLiveData<MutableList<Product>>()
    val productsClicked = mutableListOf<String>()

    val searchedRecipes = MutableLiveData<Resource<Recipes>>()
    var searchedRecipeRespose: Recipes? = null

    val recipeTutorial = MutableLiveData<Resource<RecipeTutorial>>()
    var recipeTutorialResponse: RecipeTutorial? = null

    init {
        count.postValue(0)
    }

    fun productsFromCloud(collectionPath: String) {
        val productRef = Firebase.firestore.collection(collectionPath)
        val list = mutableListOf<Product>()

        productRef.get()
            .addOnSuccessListener(OnSuccessListener<QuerySnapshot> { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val listDocuments = queryDocumentSnapshots.documents
                    for (d in listDocuments) {
                        val p = d.toObject(Product::class.java)
                        if (p != null) {
                            list.add(p)
                            listProducts.postValue(list)
                        }
                    }
                }
            })
    }
    fun getRecipesByIngridient(query: String) = viewModelScope.launch {
        safeSearchByIngridients(query)
    }
    fun getRecipeTutorial(id: Int) = viewModelScope.launch {
        safeSearchRecipeTutorial(id)
    }

    private suspend fun safeSearchByIngridients(query: String) {
        searchedRecipes.postValue(Resource.Loading())
        val response = recipeRepository.getRecipesByIngridients(query)
        searchedRecipes.postValue(handleSearchedResponce(response))
    }
    private suspend fun safeSearchRecipeTutorial(id: Int){
        recipeTutorial.postValue(Resource.Loading())
        val response = recipeRepository.getRecipeTutorial(id)
        Log.d("TAG",response.body().toString())
        recipeTutorial.postValue(handleRecipeTutorial(response))
    }

    private suspend fun handleSearchedResponce(response: Response<Recipes>): Resource<Recipes> {
        if (response.isSuccessful){
            response.body()?.let {resultResponse ->
                searchedRecipeRespose = resultResponse
                return Resource.Success(searchedRecipeRespose?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private suspend fun handleRecipeTutorial(response: Response<RecipeTutorial>): Resource<RecipeTutorial>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                recipeTutorialResponse = resultResponse
                return Resource.Success(recipeTutorialResponse?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun upsert(recipeItem: RecipeItem) = viewModelScope.launch {
        recipeRepository.upsert(recipeItem)
    }
    fun deleteRecipe(recipeItem: RecipeItem) = viewModelScope.launch {
        recipeRepository.deleteRecipe(recipeItem)
    }
    fun getAllRecipes() = recipeRepository.getAllRecipes()

}
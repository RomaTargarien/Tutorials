package com.example.firestore.ui


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firestore.R
import com.example.firestore.adapters.ProductsTypeAdapter
import com.example.firestore.db.RecipeDatabase
import com.example.firestore.models.Product
import com.example.firestore.repository.RecipeRepository
import com.example.firestore.ui.fragments.search.ProductsTypeFragment
import com.example.firestore.viewmodels.ProductsViewModel
import com.example.firestore.viewmodels.ViewModelProviderFactory
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_food.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val TAG = "FoodActivity"
private const val KEY_NAME = "name"
private const val KEY_FAMILY_NAME = "family"

class FoodActivity : AppCompatActivity() {

    lateinit var viewModel: ProductsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
        supportActionBar?.hide()
        val recipeRepository = RecipeRepository(RecipeDatabase(this))
        val viewModelProviderFactory = ViewModelProviderFactory(application,recipeRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(ProductsViewModel::class.java)
        bottomNavigationView.setupWithNavController(recipesNaviHostFragment.findNavController())

        recipesNaviHostFragment.findNavController().addOnDestinationChangedListener{_,destination, _->
            when (destination.id) {
                R.id.randomRecipesFragment, R.id.listRecipesFragment,
                R.id.productsTypeFragment, R.id.savedRecipes->
                    bottomNavigationView.visibility = View.VISIBLE
                else -> bottomNavigationView.visibility = View.GONE
            }
        }

//        val apple = Product("Apple","https://cdn.pixabay.com/photo/2014/04/02/16/28/apple-307356_960_720.png",47)
//        productRef.document(apple.name).set(apple)

    }
}
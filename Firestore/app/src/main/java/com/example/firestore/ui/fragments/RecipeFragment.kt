package com.example.firestore.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.firestore.R
import com.example.firestore.models.data.RecipeItem
import com.example.firestore.ui.FoodActivity
import com.example.firestore.util.Resource
import com.example.firestore.viewmodels.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_list_recipes.*
import kotlinx.android.synthetic.main.fragment_recipe.*

class RecipeFragment : Fragment(R.layout.fragment_recipe){

    val args: RecipeFragmentArgs by navArgs()
    private lateinit var viewModel: ProductsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as FoodActivity).viewModel
        val recipe = args.recipe
        viewModel.getRecipeTutorial(recipe.id)
        viewModel.recipeTutorial.observe(viewLifecycleOwner, Observer {response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    webView.apply {
                        webViewClient = WebViewClient()
                        loadUrl(response.data!!.sourceUrl)
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(activity,"An error ocurred", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
        fab.setOnClickListener {
            viewModel.upsert(recipe)
        }
    }

    private fun showProgressBar(){
        paginationProgressBarRecipe.visibility = View.VISIBLE
    }
    private fun hideProgressBar(){
        paginationProgressBarRecipe.visibility = View.INVISIBLE
    }

}
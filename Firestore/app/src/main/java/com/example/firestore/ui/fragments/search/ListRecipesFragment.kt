package com.example.firestore.ui.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firestore.R
import com.example.firestore.adapters.RecipeAdapter
import com.example.firestore.ui.FoodActivity
import com.example.firestore.util.RecipeUtility
import com.example.firestore.util.Resource
import com.example.firestore.viewmodels.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_list_recipes.*

class ListRecipesFragment : Fragment(R.layout.fragment_list_recipes) {

    private lateinit var viewModel: ProductsViewModel
    private var recipeAdapter = RecipeAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as FoodActivity).viewModel
        val q = RecipeUtility.getFormatedQuery(viewModel.productsClicked)
        viewModel.getRecipesByIngridient(q)
        recipeAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                Log.d("TAG","Got")
                putSerializable("recipe",it)
            }
            findNavController().navigate(R.id.action_listRecipesFragment_to_recipeFragment,bundle)
        }
        viewModel.searchedRecipes.observe(viewLifecycleOwner, Observer {response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        rvSearchRecipe.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                        it.sortByDescending { it.likes }
                        rvSearchRecipe.adapter = recipeAdapter
                        recipeAdapter.differ.submitList(it)
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
    }
    private fun showProgressBar(){
        paginationProgressBar.visibility = View.VISIBLE
    }
    private fun hideProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
    }
}
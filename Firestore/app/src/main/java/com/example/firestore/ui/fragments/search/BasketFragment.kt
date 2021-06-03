package com.example.firestore.ui.fragments.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firestore.R
import com.example.firestore.adapters.ProductsForRecipeAdapter
import com.example.firestore.ui.FoodActivity
import com.example.firestore.viewmodels.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_basket.*

class BasketFragment : Fragment(R.layout.fragment_basket) {

    private lateinit var viewModel: ProductsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as FoodActivity).viewModel
        if (viewModel.productsForRecipe.isEmpty()){
            tvAddIfListISEmpty.visibility = View.VISIBLE
        }
        rvProductsForRecipe.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            viewModel.productsForRecipeLive.observe(viewLifecycleOwner, Observer {
                adapter = ProductsForRecipeAdapter(it,viewModel)
                if (it.isEmpty()){
                    tvAddIfListISEmpty.visibility = View.VISIBLE
                    bnGo.visibility = View.GONE
                }
                if (!it.isEmpty()){
                    bnGo.visibility = View.VISIBLE
                }
            })
        }
        viewModel.count.observe(viewLifecycleOwner, Observer {
            tvCount.text = "Total amount: $it"
        })
        bnGo.setOnClickListener {
            findNavController().navigate(R.id.action_basketFragment_to_listRecipesFragment)
        }
    }
}
package com.example.firestore.ui.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firestore.R
import com.example.firestore.adapters.ProductsTypeAdapter
import com.example.firestore.models.listProductType
import com.example.firestore.ui.FoodActivity
import com.example.firestore.viewmodels.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.fragment_products_type.*

class ProductsTypeFragment : Fragment(R.layout.fragment_products_type){

    private lateinit var productsTypeAdapter: ProductsTypeAdapter
    private lateinit var viewModel: ProductsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as FoodActivity).viewModel
        initRecycler()
        productsTypeAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putString("product",it)
            }
            findNavController().navigate(R.id.action_productsTypeFragment_to_productsFragment,bundle)
        }
        viewModel.count.observe(viewLifecycleOwner, Observer {
            bnCount.text = it.toString()
        })
        bnBasket.setOnClickListener {
            findNavController().navigate(R.id.action_productsTypeFragment_to_basketFragment)
        }
    }

    private fun initRecycler(){
        productsTypeAdapter = ProductsTypeAdapter(listProductType)
        rvProductType.apply {
            layoutManager = GridLayoutManager(activity,2)
            adapter = productsTypeAdapter
        }
    }
}
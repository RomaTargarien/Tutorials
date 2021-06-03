package com.example.firestore.ui.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firestore.R
import com.example.firestore.adapters.ProductsAdapter
import com.example.firestore.ui.FoodActivity
import com.example.firestore.viewmodels.ProductsViewModel
import io.grpc.NameResolver
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : Fragment(R.layout.fragment_products) {

    private lateinit var viewModel: ProductsViewModel
    val args: ProductsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as FoodActivity).viewModel
        viewModel.productsFromCloud(args.product)
        rvProducts.layoutManager = GridLayoutManager(context,3)
        viewModel.listProducts.observe(viewLifecycleOwner, Observer {
            rvProducts.adapter = ProductsAdapter(it,viewModel)
        })
        viewModel.count.observe(viewLifecycleOwner, Observer {
            bnCount2.text = it.toString()
        })
        bnBasket2.setOnClickListener {
            findNavController().navigate(R.id.action_productsFragment_to_basketFragment)
        }
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.listProducts.postValue(emptyList())
    }
}
package com.example.firestore.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firestore.R
import com.example.firestore.models.Product
import com.example.firestore.models.ProductType
import com.example.firestore.viewmodels.ProductsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext
var count = 0
class ProductsAdapter(val list: List<Product>,val viewModel: ProductsViewModel): RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {
    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view){
        private var ivProduct = view.findViewById<ImageView>(R.id.ivProduct)
        private var tvProduct = view.findViewById<TextView>(R.id.tvProduct)
        private var bnAdd = view.findViewById<ImageView>(R.id.bnAdd)
        private var cardProduct = view.findViewById<CardView>(R.id.card_view)
        fun bind(product: Product,viewModel: ProductsViewModel){
            if (product.name in viewModel.productsClicked){
                cardProduct.alpha = 0.5f
            }
            draw(product.imageUrl)
            tvProduct.setText(product.name)
            bnAdd.setOnClickListener {
                if (!(product.name in viewModel.productsClicked)){
                    addToList(viewModel,product)
                    cardProduct.alpha = 0.5f
                }
            }
        }
        private fun draw(imageUrl: String){
            Glide.with(itemView).load(imageUrl).into(ivProduct)
        }
        private fun addToList(viewModel: ProductsViewModel,product: Product){
            viewModel.productsForRecipe.add(product)
            viewModel.productsForRecipeLive.postValue(viewModel.productsForRecipe)
            viewModel.productsClicked.add(product.name)
            count = viewModel.count.value!! + 1
            viewModel.count.value = count
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(list[position],viewModel)
    }
}
package com.example.firestore.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firestore.R
import com.example.firestore.models.Product
import com.example.firestore.models.ProductType
import com.example.firestore.viewmodels.ProductsViewModel

class ProductsForRecipeAdapter(val list: List<Product>,val viewModel: ProductsViewModel): RecyclerView.Adapter<ProductsForRecipeAdapter.ProductForRecipeViewHolder>() {
    class ProductForRecipeViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val ivProductForRecipe = view.findViewById<ImageView>(R.id.ivProductForRecipe)
        private val tvProductForRecipe = view.findViewById<TextView>(R.id.tvProductForRecipe)
        private val ivDelete = view.findViewById<ImageView>(R.id.ivDelete)
        fun bind(product: Product,viewModel: ProductsViewModel){
            draw(product.imageUrl)
            tvProductForRecipe.setText(product.name)
            ivDelete.setOnClickListener {
                deleteProductInList(viewModel,product)
            }
        }
        private fun draw(imageUrl: String){
            Glide.with(itemView).load(imageUrl).into(ivProductForRecipe)
        }
        private fun deleteProductInList(viewModel: ProductsViewModel,product: Product){
            viewModel.productsForRecipe.remove(product)
            viewModel.productsForRecipeLive.value = viewModel.productsForRecipe
            viewModel.productsClicked.remove(product.name)
            val count = viewModel.count.value!! - 1
            viewModel.count.postValue(count)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductForRecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_for_recipe_item,parent,false)
        return ProductForRecipeViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ProductForRecipeViewHolder, position: Int) {
        holder.bind(list[position],viewModel)
    }
}
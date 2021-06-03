package com.example.firestore.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firestore.R
import com.example.firestore.models.Product
import com.example.firestore.models.ProductType
import com.example.firestore.models.data.RecipeItem

class RecipeAdapter: RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view){
        private var ivRecipe = view.findViewById<ImageView>(R.id.ivRecipe)
        private var tvRecipeName = view.findViewById<TextView>(R.id.tvRecipeName)
        private var tvLikes = view.findViewById<TextView>(R.id.tvLikes)
        fun bind(recipe: RecipeItem){
            draw(recipe.image)
            tvRecipeName.text = recipe.title
            tvLikes.text = recipe.likes.toString()
        }
        private fun draw(imageUrl: String){
            Glide.with(itemView).load(imageUrl).into(ivRecipe)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item,parent,false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(differ.currentList[position]) }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<RecipeItem>(){
        override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    private var onItemClickListener: ((RecipeItem)-> Unit)? = null

    fun setOnItemClickListener(listener: (RecipeItem) -> Unit){
        onItemClickListener = listener
    }
}
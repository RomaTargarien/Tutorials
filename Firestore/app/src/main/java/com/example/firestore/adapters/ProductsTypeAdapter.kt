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

class ProductsTypeAdapter(val list: List<ProductType>): RecyclerView.Adapter<ProductsTypeAdapter.ProductTypeViewHolder>() {
    class ProductTypeViewHolder(view: View): RecyclerView.ViewHolder(view){
        private var ivProductType = view.findViewById<ImageView>(R.id.ivProductType)
        private var tvProductType = view.findViewById<TextView>(R.id.tvProductType)
        fun bind(productType: ProductType){
            ivProductType.setImageResource(productType.id)
            tvProductType.setText(productType.name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductTypeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.products_type_item,parent,false)
        return ProductTypeViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ProductTypeViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(list[position].name) }
        }
    }

    private var onItemClickListener: ((String)-> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit){
        onItemClickListener = listener
    }
}
package com.example.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(var items: List<ShoppingItem>,private val viewModel: ShoppingViewModel) :
        RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>(){
    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item,parent,false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val shoppingItem = items[position]

        holder.itemView.tvName.text = shoppingItem.name
        holder.itemView.tvAmount.text = "${shoppingItem.amount}"
        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(shoppingItem)
        }
        holder.itemView.ivPlus.setOnClickListener {
            shoppingItem.amount++
            viewModel.upsert(shoppingItem)
        }
        holder.itemView.ivMinus.setOnClickListener {
            if (shoppingItem.amount > 0){
                shoppingItem.amount--
                viewModel.upsert(shoppingItem)
            }
        }


    }
}
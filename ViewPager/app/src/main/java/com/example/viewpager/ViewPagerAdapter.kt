package com.example.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view_pager.view.*

class ViewPagerAdapter(val images: List<Int>) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    inner class ViewPagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager,parent,false)
        return ViewPagerHolder(view)
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.itemView.ivImage.setImageResource(images[position])
    }
}
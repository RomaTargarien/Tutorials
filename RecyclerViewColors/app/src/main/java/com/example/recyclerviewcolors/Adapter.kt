package com.example.recyclerviewcolors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ColorsAdapter(private val colors: List<Int>,
                    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<ColorsAdapter.ColorViewHolder>() {

    class ColorViewHolder(view: View, private val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(view){
        var root: View = view.findViewById(R.id.color)
        fun bind(color: Int){
            root.setBackgroundColor(color)
            root.setOnClickListener {
                onClick(color)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
        return ColorViewHolder(view,onClick)
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position])
    }

}
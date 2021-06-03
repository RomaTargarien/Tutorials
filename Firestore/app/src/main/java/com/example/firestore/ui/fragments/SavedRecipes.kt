package com.example.firestore.ui.fragments

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firestore.R
import com.example.firestore.adapters.RecipeAdapter
import com.example.firestore.ui.FoodActivity
import com.example.firestore.viewmodels.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_saved_recipes.*

class SavedRecipes : Fragment(R.layout.fragment_saved_recipes) {

    private lateinit var viewModel: ProductsViewModel
    private var adapterSavedRecipes = RecipeAdapter()
    private val paint = Paint()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as FoodActivity).viewModel

        viewModel.getAllRecipes().observe(viewLifecycleOwner, Observer {
            rvSavedRecipes.apply {
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                adapter = adapterSavedRecipes
                adapterSavedRecipes.differ.submitList(it)
                adapterSavedRecipes.setOnItemClickListener {
                    val bundle = Bundle().apply {
                        putSerializable("recipe",it)
                    }
                    findNavController().navigate(R.id.action_savedRecipes_to_recipeFragment,bundle)
                }
            }
        })
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val recipeItem = adapterSavedRecipes.differ.currentList[position]
                viewModel.deleteRecipe(recipeItem)
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
                    val itemView = viewHolder.itemView
                    if (dX < 0 ){
                        paint.setColor(Color.parseColor("#D32F2F"))
                        val background = RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                        c.drawRect(background,paint)
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        val simpleItemTouchHelper = ItemTouchHelper(itemTouchHelper)
        simpleItemTouchHelper.attachToRecyclerView(rvSavedRecipes)
    }
}
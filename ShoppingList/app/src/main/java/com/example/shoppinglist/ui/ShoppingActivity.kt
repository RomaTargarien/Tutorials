package com.example.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.ShoppingDatabase
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.data.repositories.ShoppingRepository
import com.example.shoppinglist.other.ShoppingItemAdapter
import com.example.shoppinglist.ui.shoppinglist.AddDialogListener
import com.example.shoppinglist.ui.shoppinglist.AddShoppingItemDialog
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModelFactory
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(),KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val viewModel = ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)

        val adpater = ShoppingItemAdapter(listOf(),viewModel)
        shopping_items.layoutManager = LinearLayoutManager(this)
        shopping_items.adapter = adpater

        viewModel.gelAllSgoppingItems().observe(this, Observer {
            adpater.items = it
            adpater.notifyDataSetChanged()
        })
        fab.setOnClickListener{
            AddShoppingItemDialog(this,object : AddDialogListener {
                override fun addButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}
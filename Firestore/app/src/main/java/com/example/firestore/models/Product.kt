package com.example.firestore.models

import com.example.firestore.R


data class Product(var name: String="",var imageUrl: String="")

data class ProductType(var name: String, var id: Int)

val listProductType = listOf<ProductType>(
    ProductType("FRUITS", R.drawable.fruits),
    ProductType("VEGETABLES", R.drawable.vegetables))

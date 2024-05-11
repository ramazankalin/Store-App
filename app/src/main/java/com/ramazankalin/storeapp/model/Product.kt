package com.ramazankalin.storeapp.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("title")
    val title : String,
    @SerializedName("price")
    val price : Float,
    @SerializedName("description")
    val description : String,
    @SerializedName("category")
    val category : String,
    @SerializedName("image")
    val imageUrl: String
)

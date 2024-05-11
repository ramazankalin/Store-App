package com.ramazankalin.storeapp.service

import com.ramazankalin.storeapp.model.Product
import retrofit2.http.GET
import retrofit2.Call

interface ProductAPI {
    @GET("products")
    fun getProducts() : Call<List<Product>>
}
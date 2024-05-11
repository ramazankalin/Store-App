package com.ramazankalin.storeapp.service

import com.ramazankalin.storeapp.model.Product
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductAPIService {

    private val BASE_URL = "https://fakestoreapi.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductAPI::class.java)

    fun getData(): Call<List<Product>> {
        return api.getProducts()
    }
}
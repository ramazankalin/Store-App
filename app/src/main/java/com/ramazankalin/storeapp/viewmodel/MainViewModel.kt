package com.ramazankalin.storeapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramazankalin.storeapp.model.Product
import com.ramazankalin.storeapp.service.ProductAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val productAPI = ProductAPIService()

    val productData = MutableLiveData<List<Product>>()
    val productLoad = MutableLiveData<Boolean>()
    val productError = MutableLiveData<Boolean>()

    fun getDataFromAPI(){
        productLoad.value = true

        productAPI.getData().enqueue(object: Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                productData.value = response.body()
                productLoad.value = false
                productError.value = false
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                productLoad.value = false
                productError.value = true
                Log.e("RetrofitError",t.message.toString())
            }
        })
    }
}
package com.ramazankalin.storeapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramazankalin.storeapp.R
import com.ramazankalin.storeapp.adapter.ProductAdapter
import com.ramazankalin.storeapp.databinding.ActivityMainBinding
import com.ramazankalin.storeapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel
    private lateinit var binding : ActivityMainBinding
    private var productAdapter =  ProductAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.productRV.adapter = productAdapter
        binding.productRV.layoutManager = LinearLayoutManager(this)
        viewModel.getDataFromAPI()
        setObservers()
    }
    private fun setObservers(){
        viewModel.productData.observe(this) { list ->
            productAdapter.updateList(list)
        }

        viewModel.productLoad.observe(this) { loading ->
            if (loading) {
                binding.loadingPB.visibility = View.VISIBLE
            } else {
                binding.loadingPB.visibility = View.GONE
            }
        }

        viewModel.productError.observe(this) { error ->
            if (error) {
                binding.errorTV.visibility = View.VISIBLE
            } else {
                binding.errorTV.visibility = View.GONE
            }
        }
    }
}
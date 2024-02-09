package com.example.kotlinpractisedate04022024.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Entity
import com.example.kotlinpractisedate04022024.adapter.ListAdapter
import com.example.kotlinpractisedate04022024.app.MyApplication
import com.example.kotlinpractisedate04022024.databinding.ActivityMainBinding
import com.example.kotlinpractisedate04022024.di.mvvm.model.Products
import com.example.kotlinpractisedate04022024.di.mvvm.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
@Entity
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var list: List<Products>
    private lateinit var adapter: ListAdapter
    private lateinit var viewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        binding.progress.visibility = View.VISIBLE
        viewModelData()
        getDBData()
    }

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("NotifyDataSetChanged")
    private fun viewModelData() {
        viewModel.products.observe(
            this@MainActivity
        ) { productList ->
            binding.progress.visibility = View.GONE
            list = productList.products


            lifecycleScope.launch {
                MyApplication.database.productDao().insert(list)
            }

            adapter = ListAdapter(this@MainActivity, list)
            binding.recyclerview.adapter = adapter
            binding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter.notifyDataSetChanged()
        }
        viewModel.error.observe(this) { error ->
            Toast.makeText(this@MainActivity, "Error $error", Toast.LENGTH_SHORT).show()
        }
        viewModel.fetchProductData()
    }

    private fun getDBData() {
        lifecycleScope.launch {
            val productList = MyApplication.database.productDao().getAllProducts()

            Log.e("Ahmed", "getDBData: $productList")
        }
    }
}
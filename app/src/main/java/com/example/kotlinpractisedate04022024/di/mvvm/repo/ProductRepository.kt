package com.example.kotlinpractisedate04022024.di.mvvm.repo

import com.example.kotlinpractisedate04022024.di.mvvm.model.Products
import com.example.kotlinpractisedate04022024.di.mvvm.model.ProductsDao
import com.example.kotlinpractisedate04022024.di.mvvm.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getProducts(): Response<ProductsDao> {
        return withContext(Dispatchers.IO) {
            try {
                apiService.getProduct()
            } catch (e: IOException) {
                // Handle IO exception (e.g., network issues)
                Response.error(500, okhttp3.ResponseBody.create(null, ""))
            }
        }
    }
}
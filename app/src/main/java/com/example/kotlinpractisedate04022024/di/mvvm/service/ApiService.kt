package com.example.kotlinpractisedate04022024.di.mvvm.service

import com.example.kotlinpractisedate04022024.di.mvvm.model.ProductsDao
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getProduct(): Response<ProductsDao>

}
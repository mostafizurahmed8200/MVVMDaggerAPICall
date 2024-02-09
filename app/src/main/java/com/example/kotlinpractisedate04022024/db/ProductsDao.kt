package com.example.kotlinpractisedate04022024.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlinpractisedate04022024.di.mvvm.model.Products

@Dao
interface ProductsDao {

    @Insert
    suspend fun insert(products: List<Products>)

    @Query("SELECT * FROM Products") // Corrected table name
    suspend fun getAllProducts(): List<Products>
}
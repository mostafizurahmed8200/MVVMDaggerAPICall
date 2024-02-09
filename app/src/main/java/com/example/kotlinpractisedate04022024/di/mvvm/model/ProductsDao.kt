package com.example.kotlinpractisedate04022024.di.mvvm.model

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductsDao(
    @SerializedName("products") var products: ArrayList<Products> = arrayListOf()
) : Serializable


 
@Entity("Products")
data class Products(
    @PrimaryKey
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("price") var price: Int? = null,
    @SerializedName("discountPercentage") var discountPercentage: Double? = null,
    @SerializedName("rating") var rating: Double? = null,
    @SerializedName("stock") var stock: Int? = null,
    @SerializedName("brand") var brand: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null,
    @SerializedName("images") var images: ArrayList<String> = arrayListOf()
) : Serializable

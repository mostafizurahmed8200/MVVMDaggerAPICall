package com.example.kotlinpractisedate04022024.di.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinpractisedate04022024.di.mvvm.model.ProductsDao
import com.example.kotlinpractisedate04022024.di.mvvm.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    //Data
    private val _productData = MutableLiveData<ProductsDao>()
    val products: LiveData<ProductsDao> get() = _productData

    //Error
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    fun fetchProductData() {
        viewModelScope.launch {
            try {
                val response = repository.getProducts()
                if (response.isSuccessful) {
                    _productData.value = response.body()
                } else {
                    _error.value = "Error : ${response.code()}"
                }

            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            }

        }

    }

}
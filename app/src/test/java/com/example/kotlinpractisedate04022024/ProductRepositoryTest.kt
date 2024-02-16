package com.example.kotlinpractisedate04022024

import com.example.kotlinpractisedate04022024.di.mvvm.model.ProductsDao
import com.example.kotlinpractisedate04022024.di.mvvm.repo.ProductRepository
import com.example.kotlinpractisedate04022024.di.mvvm.service.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Response
import java.io.IOException

@ExperimentalCoroutinesApi

@ExtendWith(MockitoExtension::class)
class ProductRepositoryTest {

    @Mock
    private lateinit var apiService: ApiService

    @InjectMocks
    private lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setUp(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test getProducts success`() {
        val mockResponse = ProductsDao()
        runBlocking {
            // Mock the ApiService response
            Mockito.`when`(apiService.getProduct()).thenReturn(Response.success(mockResponse))
            val result=productRepository.getProducts()
            // Assert
            assertEquals(Response.success(mockResponse), result)
        }

    }
    @Test
    fun `test getProducts failure`() {
        runBlocking {

            Mockito.`when`(apiService.getProduct()).thenAnswer { throw IOException() }
            // Act
            val result = productRepository.getProducts()
            // Assert
            assertEquals(Response.error<ProductsDao>(500, "".toResponseBody(null)), result)
        }
    }

}



package com.example.comprasapp.domain.repository

import com.example.comprasapp.domain.model.Product
import retrofit2.Response

interface ProductRepository {
    suspend fun getProducts(): Response<List<Product>>
    suspend fun getProductById(id: Int): Response<Product>
}
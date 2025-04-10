package com.example.comprasapp.data.remote.api

import com.example.comprasapp.data.remote.dto.FakeStoreProductDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreApi {
    @GET("products")
    suspend fun getProducts(): Response<List<FakeStoreProductDto>>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<FakeStoreProductDto>
}


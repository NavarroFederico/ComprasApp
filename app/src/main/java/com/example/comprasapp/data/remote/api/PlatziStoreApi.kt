package com.example.comprasapp.data.remote.api

import com.example.comprasapp.data.remote.dto.PlatziProductDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlatziApi {

    @GET("products")
    suspend fun getProducts(): Response<List<PlatziProductDto>>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<PlatziProductDto>
}

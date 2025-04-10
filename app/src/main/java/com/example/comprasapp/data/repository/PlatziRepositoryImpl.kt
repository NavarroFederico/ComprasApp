package com.example.comprasapp.data.repository

import com.example.comprasapp.data.remote.api.PlatziApi
import com.example.comprasapp.data.remote.dto.toDomainModel
import com.example.comprasapp.domain.model.Product
import com.example.comprasapp.domain.repository.ProductRepository
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class PlatziRepositoryImpl @Inject constructor(
    private val api: PlatziApi
) : ProductRepository {

    override suspend fun getProducts(): Response<List<Product>> {
        return try {
            val response = api.getProducts()
            if (response.isSuccessful) {
                val products = response.body()?.map { it.toDomainModel() }
                Response.success(products)
            } else {
                val errorBody = response.errorBody() ?: ResponseBody.create(null, "Unknown error")
                Response.error(response.code(), errorBody)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Response.error(500, ResponseBody.create(null, e.message ?: "Exception error"))
        }
    }

    override suspend fun getProductById(id: Int): Response<Product> {
        return try {
            val response = api.getProductById(id)
            if (response.isSuccessful) {
                Response.success(response.body()?.toDomainModel())
            } else {
                val errorBody = response.errorBody() ?: ResponseBody.create(null, "Unknown error")
                Response.error(response.code(), errorBody)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Response.error(500, ResponseBody.create(null, e.message ?: "Exception error"))
        }
    }
}
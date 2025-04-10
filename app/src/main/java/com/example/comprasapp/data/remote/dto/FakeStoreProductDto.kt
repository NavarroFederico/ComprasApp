package com.example.comprasapp.data.remote.dto

import com.example.comprasapp.domain.model.Product

data class FakeStoreProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String
)

fun FakeStoreProductDto.toDomainModel(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image
    )
}
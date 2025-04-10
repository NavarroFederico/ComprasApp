package com.example.comprasapp.data.remote.dto

import com.example.comprasapp.domain.model.Product

data class PlatziCategoryDto(
    val id: Int,
    val name: String,
    val image: String,
    val slug: String
)

data class PlatziProductDto(
    val id: Int,
    val title: String,
    val slug: String,
    val price: Double,
    val description: String,
    val category: PlatziCategoryDto,
    val images: List<String>
)

fun PlatziProductDto.toDomainModel(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category.name,
        image = images.firstOrNull() ?: ""
    )
}

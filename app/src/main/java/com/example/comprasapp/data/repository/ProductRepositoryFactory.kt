package com.example.comprasapp.data.repository

import com.example.comprasapp.domain.model.Country
import com.example.comprasapp.domain.repository.ProductRepository
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ProductRepositoryFactory @Inject constructor(
    @Named("Fake") private val fakeStoreRepository: ProductRepository,
    @Named("Platzi") private val platziRepository: ProductRepository
) {
    fun getRepository(selectedCountry: Country): ProductRepository {
        return when(selectedCountry) {
            Country.FAKESTORE -> fakeStoreRepository
            Country.PLATZI -> platziRepository
        }
    }
}
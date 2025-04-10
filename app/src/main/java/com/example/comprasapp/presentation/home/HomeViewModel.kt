package com.example.comprasapp.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comprasapp.data.repository.ProductRepositoryFactory
import com.example.comprasapp.domain.model.Country
import com.example.comprasapp.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositoryFactory: ProductRepositoryFactory
) : ViewModel() {

    var selectedCountry: Country = Country.PLATZI

    private val _products = MutableLiveData<Response<List<Product>>>()
    val products: LiveData<Response<List<Product>>> get() = _products

    fun fetchProducts(country: Country) {
        viewModelScope.launch {
            try {
                val repository = repositoryFactory.getRepository(country)
                _products.value = repository.getProducts()
                Log.d("HomeViewModel", "Productos obtenidos ${selectedCountry}: ${_products.value}")
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }
}

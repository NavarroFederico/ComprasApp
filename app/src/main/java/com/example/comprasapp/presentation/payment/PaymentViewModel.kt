package com.example.comprasapp.presentation.payment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comprasapp.data.remote.api.FakeStoreApi
import com.example.comprasapp.data.remote.api.PlatziApi
import com.example.comprasapp.data.repository.ProductRepositoryFactory
import com.example.comprasapp.domain.model.Country
import com.example.comprasapp.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val repositoryFactory: ProductRepositoryFactory
) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    fun loadProductForPayment(productId: Int, country: Country) {
        viewModelScope.launch {
            try {
                val repository = repositoryFactory.getRepository(country)
                val response = repository.getProductById(productId)

                if (response.isSuccessful) {
                    response.body()?.let {
                        _product.value = it
                    } ?: Log.e("PaymentViewModel", "Producto no encontrado")
                } else {
                    Log.e("PaymentViewModel", "Error en respuesta: ${response.code()}")
                }

            } catch (e: Exception) {
                Log.e("PaymentViewModel", "Error al cargar producto", e)
            }
        }
    }

    fun processPayment() {
        Log.d("PaymentViewModel", "Procesando pago para producto: ${_product.value}")
    }
}
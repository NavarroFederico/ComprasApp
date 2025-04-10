package com.example.comprasapp.presentation.productodetail

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
class DetailViewModel @Inject constructor(
    private val repositoryFactory: ProductRepositoryFactory
) : ViewModel() {

    private val _productDetail = MutableLiveData<Response<Product>>()
    val productDetail: LiveData<Response<Product>> get() = _productDetail

    fun fetchProductDetail(productId: Int, selectedCountry: Country = Country.PLATZI) {
        viewModelScope.launch {
            try {
                val repository = repositoryFactory.getRepository(selectedCountry)
                _productDetail.value = repository.getProductById(productId)
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }
}

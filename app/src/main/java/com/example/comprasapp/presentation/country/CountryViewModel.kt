package com.example.comprasapp.presentation.country

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.comprasapp.domain.model.Country

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val KEY_SELECTED_COUNTRY = "selected_country"
    }

    private val _selectedCountry = MutableLiveData<Country>()
    val selectedCountry: LiveData<Country> get() = _selectedCountry

    init {
        val saved = savedStateHandle.get<String>(KEY_SELECTED_COUNTRY)
        if (saved != null) {
            _selectedCountry.value = Country.valueOf(saved)
        }
    }

    fun selectCountry(country: Country) {
        _selectedCountry.value = country
        savedStateHandle[KEY_SELECTED_COUNTRY] = country.name
    }
}

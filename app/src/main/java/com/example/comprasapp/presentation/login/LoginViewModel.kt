package com.example.comprasapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> get() = _loginState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            if (username == "usuario" && password == "1234") {
                _loginState.value = true
            } else {
                _loginState.value = false
            }
        }

    }
}
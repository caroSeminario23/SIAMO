package com.example.siamo.ui.registerClient

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.siamo.data.Cliente

class RegisterClientViewModel: ViewModel() {
    private val _registerSuccess = mutableStateOf(false)
    val loginSuccess: State<Boolean> = _registerSuccess

    fun registerClient(Cliente: Cliente) {
        _registerSuccess.value = true
    }
}
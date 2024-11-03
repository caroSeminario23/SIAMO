package com.example.siamo.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.siamo.data.local.LocalEmpleadoDataProvider

// ViewModel que maneja la lógica de inicio de sesión
class SiamoViewModel : ViewModel() {
    // Estado de éxito/error de inicio de sesión
    private val _loginSuccess = mutableStateOf(false)
    val loginSuccess: State<Boolean> = _loginSuccess

    private val _loginError = mutableStateOf(false)
    val loginError: State<Boolean> = _loginError

    fun onLogin(employeeCode: String , password: String) {
        val employeeCodeInt = employeeCode.toIntOrNull()
        if (employeeCodeInt != null && validateCredentials(employeeCodeInt, password)) {
            _loginSuccess.value = true
            _loginError.value = false
        } else {
            _loginError.value = true
            _loginSuccess.value = false
        }
    }

    // Función de validación de credenciales
    private fun validateCredentials(employeeCode: Int, password: String): Boolean {
        // Lógica para validar las credenciales
        return LocalEmpleadoDataProvider.isValidEmployee(employeeCode, password)
    }
}

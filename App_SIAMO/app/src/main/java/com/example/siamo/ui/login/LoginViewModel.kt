package com.example.siamo.ui.login

/*
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
*/


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siamo.data.Entity.Empleado
import com.example.siamo.data.Repository.EmpleadosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SiamoLoginViewModel(private val empleadosRepository: EmpleadosRepository) : ViewModel() {

    // Estado de la UI para el inicio de sesión
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    // Función para manejar el inicio de sesión
    fun onLogin(employeeCode: String, password: String) {
        viewModelScope.launch {
            // Se obtienen todos los empleados como flujo
            empleadosRepository.getAllEmpleadosStream()
                .map { empleados ->
                    // Se busca un empleado que coincida con el código y contraseña
                    empleados.firstOrNull {
                        it.codEmpleado.toString() == employeeCode && it.contrasenia == password
                    }
                }
                .collect { empleado ->
                    if (empleado != null) {
                        // Login exitoso
                        _loginUiState.value = _loginUiState.value.copy(
                            loginSuccess = true,
                            loginError = false
                        )
                    } else {
                        // Error de login
                        _loginUiState.value = _loginUiState.value.copy(
                            loginSuccess = false,
                            loginError = true
                        )
                    }
                }
        }
    }
}



// Estado de la UI para el inicio de sesión
data class LoginUiState(
    val loginSuccess: Boolean = false,
    val loginError: Boolean = false
)


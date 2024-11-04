package com.example.siamo.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siamo.data.Repository.EmpleadosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SiamoLoginViewModel(
    private val empleadosRepository: EmpleadosRepository
) : ViewModel() {

    // Estado de la UI para el inicio de sesión
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    // Función para manejar el inicio de sesión
    fun onLogin(employeeCode: String, password: String) {
        viewModelScope.launch {
            empleadosRepository.getAllEmpleadosStream()
                .collect { empleados ->
                    val empleado = empleados.firstOrNull {
                        it.codEmpleado.toString() == employeeCode && it.contrasenia == password
                    }
                    if (empleado != null) {
                        _loginUiState.value = _loginUiState.value.copy(
                            loginSuccess = true,
                            loginError = false
                        )
                    } else {
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


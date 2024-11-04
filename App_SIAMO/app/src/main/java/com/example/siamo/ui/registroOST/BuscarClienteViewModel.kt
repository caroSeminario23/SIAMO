package com.example.siamo.ui.registroOST

/*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.siamo.data.Entity.Cliente
import com.example.siamo.data.local.LocalClienteDataProvider

class BuscarClienteViewModel: ViewModel() {
    var buscarClienteUiState by mutableStateOf(BuscarClienteUiState())
        private set

    fun buscarCliente(dni: String): Boolean {
        val cliente = LocalClienteDataProvider.allClientes.firstOrNull { it.nro_doc.toString() == dni }
        return cliente != null
    }

}

data class BuscarClienteUiState(
    val cliente: Cliente = Cliente(
        nombres = "",
        apellidos = "",
        tipo_doc = true,
        nro_doc = 99,
        telefono = 999,
        direccion = "",
        correo = "",
        sexo = 'M'

    ),
    val isEntryValid: Boolean = false
)
 */
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siamo.data.Repository.ClientesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class BuscarClienteViewModel(
    private val clientesRepository: ClientesRepository
) : ViewModel() {

    // Estado mutable para la UI
    var buscarClienteUiState by mutableStateOf(BuscarClienteUiState())
        private set

    /**
     * Verifica si el cliente existe en la base de datos según su número de documento.
     */
    fun buscarCliente(numeroDocumento: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Solo obtiene el primer valor emitido por el Flow y verifica si es nulo
                val clienteEncontrado = clientesRepository.getClientePorNumeroDocumento(numeroDocumento).firstOrNull() != null

                // Actualiza el estado de la UI
                buscarClienteUiState = buscarClienteUiState.copy(
                    isRegistered = clienteEncontrado
                )
            } catch (e: Exception) {
                // Manejar el error apropiadamente
                buscarClienteUiState = buscarClienteUiState.copy(
                    isRegistered = false // Muestra que la búsqueda falló
                )
            }
        }
    }
}

/**
 * Estado de la UI para la pantalla de búsqueda de cliente.
 */
data class BuscarClienteUiState(
    val isRegistered: Boolean = false // Indica si el cliente está registrado
)



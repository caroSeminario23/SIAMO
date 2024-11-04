package com.example.siamo.ui.registerClient
/*
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.siamo.data.Entity.Cliente

class RegisterClientViewModel: ViewModel() {
    private val _registerSuccess = mutableStateOf(false)
    val loginSuccess: State<Boolean> = _registerSuccess

    fun registerClient(Cliente: Cliente) {
        _registerSuccess.value = true
    }
}
 */

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.siamo.data.Entity.Cliente
import com.example.siamo.data.Entity.Persona
import com.example.siamo.data.Repository.ClientesRepository
import com.example.siamo.data.Repository.PersonasRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel para registrar clientes .
 */
class RegisterClientViewModel(
    private val clientesRepository: ClientesRepository,
    private val personasRepository: PersonasRepository
) : ViewModel() {

    // Estado actual de la UI para el cliente
    var clientUiState by mutableStateOf(ClientUiState())
        private set

    /**
     * Actualiza el estado de la UI con los detalles del cliente y valida la entrada.
     */
    fun updateUiState(clientDetails: ClientDetails) {
        clientUiState = ClientUiState(clientDetails = clientDetails, isEntryValid = validateInput(clientDetails))
    }

    /**
     * Guarda un cliente y su persona asociada en la base de datos.
     */
    fun saveClient() {
        if (validateInput()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    // Primero, crea y guarda la Persona
                    val persona = clientUiState.clientDetails.toPersona()
                    personasRepository.insertPersona(persona)

                    // Luego, crea el Cliente usando el idPersona de la persona recién insertada
                    val cliente = Cliente(idPersona = persona.idPersona)
                    clientesRepository.insertCliente(cliente)

                    // Mostrar diálogo al guardar
                    clientUiState = clientUiState.copy(showDialog = true)
                } catch (e: Exception) {

                }
            }
        }
    }

    /**
     * Valida la entrada del usuario.
     */
    private fun validateInput(uiState: ClientDetails = clientUiState.clientDetails): Boolean {
        return with(uiState) {
            nombres.isNotBlank() && apellidos.isNotBlank() && numeroDocumento.isNotBlank() &&
                    direccion.isNotBlank() && correo.isNotBlank() && telefono.isNotBlank()
        }
    }

    /**
     * Cierra el diálogo de éxito.
     */
    fun dismissDialog() {
        clientUiState = clientUiState.copy(showDialog = false)
    }
}

/**
 * Extensión para convertir [ClientDetails] a [Persona].
 */
fun ClientDetails.toPersona(): Persona = Persona(
    tipoDoc = when (tipoDocumentoSeleccionado) {
        "DNI" -> 1
        "Carnet de Extranjería" -> 2
        else -> 0
    },
    numDoc = numeroDocumento,
    nombres = nombres,
    apellidos = apellidos,
    direccion = direccion,
    sexo = sexoSeleccionado.firstOrNull() ?: ' ',
    telefono = telefono,
    correo = correo
)

/**
 * Representa el estado de la UI para un cliente.
 */
data class ClientUiState(
    val clientDetails: ClientDetails = ClientDetails(),
    val isEntryValid: Boolean = false,
    val showDialog: Boolean = false // Estado para el diálogo de éxito
)

data class ClientDetails(
    val id: Int = 0,
    val nombres: String = "",
    val apellidos: String = "",
    val tipoDocumentoSeleccionado: String = "",
    val numeroDocumento: String = "",
    val direccion: String = "",
    val correo: String = "",
    val telefono: String = "",
    val sexoSeleccionado: String = ""
)

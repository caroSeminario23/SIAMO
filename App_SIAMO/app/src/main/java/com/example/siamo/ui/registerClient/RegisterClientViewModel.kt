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

    fun saveClient(
        nombres: String,
        apellidos: String,
        tipoDocumento: String,
        numeroDocumento: String,
        direccion: String,
        correo: String,
        telefono: String,
        sexo: String
    ) {
        updateUiState(
            ClientDetails(
                nombres = nombres,
                apellidos = apellidos,
                tipoDocumentoSeleccionado = tipoDocumento,
                numeroDocumento = numeroDocumento,
                direccion = direccion,
                correo = correo,
                telefono = telefono,
                sexoSeleccionado = sexo
            )
        )
        if (validateInput()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {

                    val persona = clientUiState.clientDetails.toPersona()
                    val personaId = personasRepository.insertPersona(persona)

                    val cliente = Cliente(idPersona = personaId.toInt())
                    clientesRepository.insertCliente(cliente)

                    clientUiState = clientUiState.copy(showDialog = true)
                } catch (e: Exception) {
                    // Manejo de errores
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
        "DNI" -> 0
        "Carnet de Extranjería" -> 1
        else -> -1
    },
    numDoc = numeroDocumento,
    nombres = nombres,
    apellidos = apellidos,
    direccion = direccion,
    sexo = sexoSeleccionado.substring(0, 1),
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

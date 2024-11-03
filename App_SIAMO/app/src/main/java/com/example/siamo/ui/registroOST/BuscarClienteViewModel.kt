package com.example.siamo.ui.registroOST

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
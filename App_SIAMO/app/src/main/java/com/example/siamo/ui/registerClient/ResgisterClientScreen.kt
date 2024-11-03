package com.example.siamo.ui.registerClient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siamo.R
import com.example.siamo.SiamoAppBar
import com.example.siamo.data.Entity.Cliente
import com.example.siamo.ui.navigation.NavigationDestination
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.AlertaEmergente
import com.example.siamo.ui.utils.ButtonCommon
import com.example.siamo.ui.utils.FormLabel
import com.example.siamo.ui.utils.FormLabelDropdown

object RegisterDestination : NavigationDestination {
    override val route = "register"
    override val titleRes = R.string.registro_de_cliente_title
}


@Composable
fun SiamoResgisterScreen(
    onNavigateUp: () -> Unit,
    moveToOstRegister: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    registerClientViewModel: RegisterClientViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            SiamoAppBar(
                title = stringResource(RegisterDestination.titleRes),
                canNavigateBack = true,
                navigateUp = { onNavigateUp() }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        RegistrarClienteBody(
            onRegister = { cliente -> registerClientViewModel.registerClient(cliente) },
            moveToOstRegister = navigateBack,
            contentPadding = innerPadding,
            modifier = modifier
        )
    }
}

@Composable
fun RegistrarClienteBody(
    onRegister: (Cliente) -> Unit,
    moveToOstRegister: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {

    val title: String = stringResource(R.string.registro_de_cliente_title)
    var allCompleted by rememberSaveable { mutableStateOf(false) }

    var nombres by rememberSaveable { mutableStateOf("") }
    var apellidos by rememberSaveable { mutableStateOf("") }
    var tipoDocumento by rememberSaveable { mutableStateOf("") }
    var numeroDocumento by rememberSaveable { mutableStateOf("") }
    var direccion by rememberSaveable { mutableStateOf("") }
    var correo by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var sexo by rememberSaveable { mutableStateOf("") }

    var showDialog by rememberSaveable { mutableStateOf(false) }

    fun checkAllCompleted() {
        allCompleted =
            nombres.isNotBlank() && apellidos.isNotBlank() && tipoDocumento.isNotBlank() &&
                    numeroDocumento.isNotBlank() && direccion.isNotBlank() && correo.isNotBlank() &&
                    telefono.isNotBlank() && sexo.isNotBlank()
    }

    LazyColumn(
        modifier = modifier
            .padding(16.dp)
            .padding(contentPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                text = title,
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(16.dp)
            )
        }
        item {
            FormLabel(
                stringResource(R.string.nombres_registro_cliente),
                value = nombres,
                onValueChange = { nombres = it; checkAllCompleted() })
        }
        item {
            FormLabel(
                stringResource(R.string.apellidos_registro_cliente),
                value = apellidos,
                onValueChange = { apellidos = it; checkAllCompleted() })
        }
        item {
            FormLabelDropdown(
                stringResource(R.string.tipo_de_documenato_de_identidad_registro_cliente),
                options = listOf("DNI", "Carnet de Extranjería"),
                selectedOption = tipoDocumento,
                onOptionSelected = { tipoDocumento = it; checkAllCompleted() })
        }
        item {
            FormLabel(
                stringResource(R.string.n_documento_de_identidad_registro_cliente),
                value = numeroDocumento,
                onValueChange = { numeroDocumento = it; checkAllCompleted() },
                labelType = KeyboardType.Number
            )
        }
        item {
            FormLabel(
                stringResource(R.string.direcci_n_registro_cliente),
                value = direccion,
                onValueChange = { direccion = it; checkAllCompleted() })
        }
        item {
            FormLabel(
                stringResource(R.string.correo_electronico_registro_cliente),
                value = correo,
                onValueChange = { correo = it; checkAllCompleted() })
        }
        item {
            FormLabel(
                stringResource(R.string.telefono_registro_cliente),
                value = telefono,
                onValueChange = { telefono = it; checkAllCompleted() },
                labelType = KeyboardType.Number
            )
        }
        item {
            FormLabelDropdown(
                "Sexo:",
                options = listOf("Masculino", "Femenino"),
                selectedOption = sexo,
                onOptionSelected = { sexo = it; checkAllCompleted() })
        }
        item {
            ButtonCommon(
                stringResource(R.string.registrar_button),
                enable = allCompleted,
                onClick = {
                    showDialog = true
//                    onRegister(
//                        Cliente(
//                            nombres = nombres,
//                            apellidos = apellidos,
//                            tipo_doc = tipoDocumento == "DNI",
//                            nro_doc = numeroDocumento.toInt(),
//                            direccion = direccion,
//                            correo = correo,
//                            telefono = telefono.toInt(),
//                            sexo = sexo[0]
//                        )
//                    )
                }
            )
        }
    }

    AlertaEmergente(
        showDialog = showDialog,
        message = R.string.label_cliente_registrado,
        onClick = {
            showDialog = false
            moveToOstRegister()

        }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewSiamoResgisterScreen() {
    SIAMOTheme(darkTheme = false) {
        Surface(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            SiamoResgisterScreen(moveToOstRegister = {}, navigateBack = {}, onNavigateUp = {})
        }

    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewSiamoResgisterScreenDark() {
    SIAMOTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            SiamoResgisterScreen(moveToOstRegister = {}, navigateBack = {}, onNavigateUp = {})
        }
    }
}
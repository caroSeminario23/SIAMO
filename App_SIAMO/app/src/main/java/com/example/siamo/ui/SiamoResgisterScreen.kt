package com.example.siamo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.siamo.R

@Composable
fun SiamoResgisterScreen(
    onRegister: () -> Unit,
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
            .padding(16.dp),
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
            RegisterButton(
                stringResource(R.string.registrar_button),
                enable = allCompleted,
                onClick = {
                    showDialog = true
                    onRegister()
                }
            )
        }
    }

    if (showDialog) {
        AlertaRegistro(
            showDialog = showDialog,
            onClick = { showDialog = false }
        )
    }

}

@Composable
fun FormLabel(
    text: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(bottom = 4.dp, start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = labelType,
                imeAction = imeAction
            ),
            textStyle = MaterialTheme.typography.displaySmall.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormLabelDropdown(
    text: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(bottom = 4.dp, start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = MaterialTheme.typography.displaySmall.copy(
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .exposedDropdownSize(true)
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .clip(shape = RoundedCornerShape(20.dp))
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                option,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        },
                        modifier = Modifier
                            .padding(1.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    )
                }
            }
        }
    }
}

@Composable
fun RegisterButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enable: Boolean = false
) {
    Button(
        enabled = enable,
        onClick = onClick,
        modifier = modifier
            .width(200.dp)
            .padding(16.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.secondaryContainer
        )
    }

}

@Composable
fun AlertaRegistro(
    onClick: () -> Unit,
    showDialog: Boolean,
    modifier: Modifier = Modifier
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onClick,
            text = {
                Text(
                    text = stringResource(R.string.label_cliente_registrado),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    textAlign = TextAlign.Center
                )
            },
            confirmButton = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = onClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.label_aceptar),
                            color = MaterialTheme.colorScheme.tertiaryContainer,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            },
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            shape = RoundedCornerShape(20.dp),
            modifier = modifier.border(
                3.dp,
                MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(20.dp)
            )
        )
    }
}
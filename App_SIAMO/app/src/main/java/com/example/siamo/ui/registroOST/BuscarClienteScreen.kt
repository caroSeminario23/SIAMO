package com.example.siamo.ui.registroOST

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siamo.R
import com.example.siamo.SiamoAppBar
import com.example.siamo.ui.AppViewModelProvider
import com.example.siamo.ui.navigation.NavigationDestination
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.AlertaEmergente
import com.example.siamo.ui.utils.ButtonCommon

object BuscarClienteScreen: NavigationDestination {
    override val route: String = "buscarCliente"
    override val titleRes: Int = R.string.buscar_cliente_screen_label
}

@Composable
fun BuscarClienteScreen(
    onNavigateUp : () -> Unit,
    navigateBack : () -> Unit,
    moveToRegister: () -> Unit,
    buscarClienteViewModel: BuscarClienteViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    Scaffold(
        topBar = {
            SiamoAppBar(
                title = stringResource(BuscarClienteScreen.titleRes),
                canNavigateBack = false,
                navigateUp = { onNavigateUp() }
            )
        }
    ) { innerPadding ->
        BuscarClienteContent(
            moveToRegister = moveToRegister,
            contentPadding = innerPadding,
            viewModel = buscarClienteViewModel
        )
    }
}

@Composable
fun BuscarClienteContent(
    moveToRegister: () -> Unit,
    contentPadding: PaddingValues,
    viewModel: BuscarClienteViewModel,
    modifier: Modifier = Modifier
) {
    var value by rememberSaveable { mutableStateOf("") }
    var showDialog by rememberSaveable { mutableStateOf(false) }
    val isRegistred = viewModel.buscarClienteUiState.isRegistered

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Text(
                text = "1. Ingrese el N° del documento de\n" +
                    "     identidad del cliente",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            TextField(
                value = value,
                onValueChange = { value = it },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                textStyle = MaterialTheme.typography.displaySmall.copy(
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                modifier = Modifier.fillMaxWidth()
            )
            ButtonCommon(
                text = stringResource(R.string.login_button),
                onClick = { viewModel.buscarCliente(value)
                          showDialog = true},
                enable = value.isNotBlank(),
                modifier = Modifier
                    .align(Alignment.End)
            )

            Spacer(modifier = Modifier.padding(48.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color(0xFF5B5B7E)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.privacy_policy),
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.abeezee_regular)),
                    fontSize = 14.sp
                ),
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }

    if (!isRegistred) {       // if (viewModel.uistate.isRegistred)
        AlertaEmergente(
            onClick = {
                showDialog = false
                moveToRegister() },
            showDialog = showDialog,
            message = R.string.buscar_cliente_error_message,
            errorMessage = R.string.buscar_cliente_error_label,
            type = "error"
        )
    } else {
        AlertaEmergente(
            onClick = {
                showDialog = false
                 },
            showDialog = showDialog,
            message = R.string.cliente_encontrado_con_exito_label
        )
    }

}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun BuscarClienteScreenPreview() {
    SIAMOTheme {
        BuscarClienteScreen(
            onNavigateUp = {},
            navigateBack = {},
            moveToRegister = {}
        )
    }
}

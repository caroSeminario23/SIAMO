package com.example.siamo.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siamo.R
import com.example.siamo.SiamoAppBar
import com.example.siamo.ui.navigation.NavigationDestination
import com.example.siamo.ui.theme.SIAMOTheme
import com.example.siamo.ui.utils.ButtonCommon
import com.example.siamo.ui.utils.FormLabel

object LoginDestination: NavigationDestination {
    override val route = "login"
    override val titleRes = R.string.login_title
}

@Composable
fun SiamoLoginScreen(
    onNavigateUp : () -> Unit,
    navigateBack : () -> Unit,
    moveToRegister: () -> Unit,
    loginViewModel: SiamoViewModel = viewModel(),
) {

    Scaffold(
        topBar = {
            SiamoAppBar(
                title = "",
                canNavigateBack = false,
                navigateUp = { onNavigateUp() }
            )
        }
    ){ innerPadding ->
        LoginConten(
            onLogin = { employeeCode, password ->
                loginViewModel.onLogin(employeeCode, password)
                // No navegamos a otra pantalla; solo mostramos mensajes de éxito/error
            },
//            loginSuccess = loginViewModel.loginSuccess.value,
//            loginError = loginViewModel.loginError.value,
            viewModel = loginViewModel,
            onAccept = moveToRegister,
            contentPadding = innerPadding
        )
    }
}

@Composable
fun LoginConten(
    onLogin: (String, String) -> Unit,
    viewModel: SiamoViewModel,
    onAccept: () -> Unit,
//    loginSuccess: Boolean,
    contentPadding: PaddingValues,
//    loginError: Boolean
){
    var employeeCode by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
//    val passwordVisible by remember { mutableStateOf(false) }
    var showMessage by remember { mutableStateOf(false) }
    var messageText by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    // Usando Box como contenedor principal para permitir superposición
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(contentPadding),

        ) {
        // Contenido de la pantalla de login
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
//                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // Column para el contenido de login (PENDIENTE)
            Column(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(R.dimen.login_padding))
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.login_title),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.adamina_regular)),
                        fontSize = 34.sp,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                )

                Text(
                    text = stringResource(R.string.login_message),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.abeezee_italic)),
                        color = MaterialTheme.colorScheme.inverseSurface,
                        fontSize = 15.sp
                    )
                )

                    Spacer(modifier = Modifier.height(29.dp))

                FormLabel(
                    text = stringResource(R.string.codigo_de_empleado_label),
                    value = employeeCode,
                    onValueChange = { employeeCode = it }
                )

                Spacer(modifier = Modifier.height(30.dp))

                FormLabel(
                    text = stringResource(R.string.contrasena_label),
                    value = password,
                    onValueChange = { password = it },
                    visualTransformation = PasswordVisualTransformation(),
                    labelType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Botón de login
                ButtonCommon(
                    text = stringResource(R.string.login_button),
                    onClick = {
                        onLogin(employeeCode, password)
                        if (viewModel.loginSuccess.value) {
                            messageText = "Inicio de sesión exitoso"
                            isError = false
                            showMessage = true
                        } else if (viewModel.loginError.value) {
                            messageText = "Usuario o contraseña incorrecto"
                            isError = true
                            showMessage = true
                        }
                    },
                    enable = employeeCode.isNotBlank() && password.isNotBlank()
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Pie de página
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface),
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

        // Superposición de MessageWindow
        if (showMessage) {
            MessageWindow(
                message = messageText,
                isError = isError,
                onDismiss = { showMessage = false },
                onAccept = {
                    showMessage = false
                    onAccept()
                }
            )
        }
    }//
}

@Preview
@Composable
fun LoginPreview() {
    SIAMOTheme {
        SiamoLoginScreen(
            onNavigateUp = { /*TODO*/ },
            navigateBack = { /*TODO*/ },
            moveToRegister = { /*TODO*/ }
        )
    }
}

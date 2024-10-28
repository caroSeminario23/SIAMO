package com.example.siamo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siamo.R
import com.example.siamo.data.local.LocalEmpleadoDataProvider


@Composable
fun SiamoLoginScreen(
    viewModel: SiamoViewModel,
    onLogin: (String, String) -> Unit,
    loginSuccess: Boolean,
    loginError: Boolean
) {
    var employeeCode by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val passwordVisible by remember { mutableStateOf(false) }
    var showMessage by remember { mutableStateOf(false) }
    var messageText by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    // Usando Box como contenedor principal para permitir superposición
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF5F5F5))
    ) {
        // Contenido de la pantalla de login
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Encabezado azul en la parte superior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(MaterialTheme.colorScheme.inversePrimary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.aboreto_regular)),
                        fontSize = 29.sp
                    ),
                    color = Color(0xFFCFE6F1)
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            // Column para el contenido de login
            Column(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(R.dimen.login_padding))
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
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
                        color = Color(0xFF2C3134),
                        fontSize = 15.sp
                    )
                )

                Spacer(modifier = Modifier.height(29.dp))

                // Input de código de empleado
                BasicTextField(
                    value = employeeCode,
                    onValueChange = { employeeCode = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .width(340.dp)
                        .height(57.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onSecondaryContainer)
                        .padding(20.dp),
                    decorationBox = { innerTextField ->
                        if (employeeCode.isEmpty()) {
                            Text(
                                text = stringResource(R.string.employee_code_hint),
                                color = Color.Gray,
                                fontSize = 15.sp,
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.abeezee_regular))
                                )
                            )
                        }
                        innerTextField()
                    }
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Input de contraseña
                BasicTextField(
                    value = password,
                    onValueChange = { password = it },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .width(340.dp)
                        .height(57.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(MaterialTheme.colorScheme.onSecondaryContainer)
                        .padding(20.dp),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                if (password.isEmpty()) {
                                    Text(
                                        text = stringResource(R.string.password_hint),
                                        color = Color.Gray,
                                        fontSize = 15.sp,
                                        style = TextStyle(
                                            fontFamily = FontFamily(Font(R.font.abeezee_regular))
                                        )
                                    )
                                }
                                innerTextField()
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Botón de login
                Button(
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
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.inversePrimary
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 20.dp)
                ) {
                    Text(
                        text = stringResource(R.string.login_button),
                        fontSize = 15.sp,
                        color = Color(0xFFCFE6F1)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Pie de página
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        // Superposición de MessageWindow
        if (showMessage) {
            MessageWindow(
                message = messageText,
                isError = isError,
                onDismiss = { showMessage = false },
                         )
        }
    }
}


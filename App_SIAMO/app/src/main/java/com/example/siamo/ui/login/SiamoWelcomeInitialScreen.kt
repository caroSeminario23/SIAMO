package com.example.siamo.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.siamo.R
import com.example.siamo.ui.navigation.NavigationDestination
import com.example.siamo.ui.theme.SIAMOTheme

object WelcomeDestination : NavigationDestination {
    override val route = "welcome"
    override val titleRes = R.string.app_name
}


@Composable
fun WelcomeScreen(onWelcomeClick: () -> Unit) {
    // Fondo de pantalla dividido utilizando el tema
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Parte superior de color 0xFF5B5B7E
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f) // Ocupa el 50% de la altura
                .background(MaterialTheme.colorScheme.tertiary)
        )

        // Parte inferior de color 0xFF0B6780
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.52f)
                .background(MaterialTheme.colorScheme.primary)
                .align(Alignment.BottomCenter) // Alinea este Box en la parte inferior
        )
        
        // Contenido centrado
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1.9f))
            // Imagen del logo
            Image(
                painter = painterResource(id = R.drawable.siamo_logo),
                contentDescription = null,
                modifier = Modifier.size(220.dp)
            )

            // Texto principal
            Text(
                text = stringResource(R.string.app_title), // Usa una cadena de recursos
                color = MaterialTheme.colorScheme.background, // Cambia a color de texto del tema
                fontSize = 20.sp,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.adamina_regular))
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 70.dp)
            )
            Spacer(modifier = Modifier.weight(1f))

            // Botón de Bienvenido
            Button(
                onClick = { onWelcomeClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(dimensionResource(R.dimen.padding_medium)),
                modifier = Modifier.width(200.dp)
            ) {
                Text(
                    text = stringResource(R.string.welcome_button),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.adamina_regular))
                )
            }
            Spacer(modifier = Modifier.height(45.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    SIAMOTheme {
        WelcomeScreen(onWelcomeClick = {})
    }
}
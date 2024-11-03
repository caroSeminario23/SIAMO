package com.example.siamo.ui.utils

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siamo.R
import com.example.siamo.ui.theme.SIAMOTheme

@Composable
fun AlertaEmergente(
    onClick: () -> Unit,
    showDialog: Boolean,
    @StringRes message: Int,
    modifier: Modifier = Modifier,
    type: String = "success",
    @StringRes errorMessage: Int = R.string.accept,
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onClick,
            text = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = stringResource(message),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        textAlign = TextAlign.Center
                    )
                }
            },
            confirmButton = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = onClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (type == "success")
                                MaterialTheme.colorScheme.tertiary
                            else
                                MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text(
                            text = if(type == "sucess") stringResource(R.string.label_aceptar) else
                                stringResource(errorMessage),
                            color = MaterialTheme.colorScheme.tertiaryContainer,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            },
            containerColor = if (type == "success") MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.errorContainer,
            shape = RoundedCornerShape(20.dp),
            modifier = modifier.border(
                3.dp,
                if (type == "success" )MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.error,
                shape = RoundedCornerShape(20.dp)
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlertaEmergentePreview() {
    SIAMOTheme{
        AlertaEmergente(
            onClick = {},
            showDialog = true,
            message = R.string.cliente_encontrado_con_exito_label
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlertaEmergenteErrorPreview() {
    SIAMOTheme {
        AlertaEmergente(
            onClick = {},
            showDialog = true,
            type = "error",
            message = R.string.buscar_cliente_error_message
        )
    }
}
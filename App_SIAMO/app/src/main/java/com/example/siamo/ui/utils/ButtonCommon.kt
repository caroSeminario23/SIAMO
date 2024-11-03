package com.example.siamo.ui.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonCommon(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enable: Boolean = false
) {
    Button(
        enabled = enable,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
        ),
        modifier = modifier
//            .width(200.dp)
            .padding(top = 20.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.secondaryContainer
        )
    }

}
package com.example.siamo.ui
// MessageWindow.kt


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.siamo.R

@Composable
fun MessageWindow(
    message: String,
    isError: Boolean,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = if (isError) MaterialTheme.colorScheme.onErrorContainer else Color(0xFFE1DFFF),
            border = BorderStroke(2.dp, if (isError) Color(0xFFBA1A1A) else Color(0xFF5B5B7E)),
            modifier = Modifier.padding(16.dp).width(300.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = message,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.abeezee_regular)),
                        fontSize = 15.sp
                    ),
                    color = if (isError) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.onTertiary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isError)  Color(0xFFBA1A1A) else Color(0xFF5B5B7E),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = if (isError) stringResource(R.string.try_again) else stringResource(R.string.accept),
                            style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.abeezee_regular)),
                        fontSize = 13.sp
                    ),)
                }
            }
        }
    }
}

package com.example.siamo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.siamo.ui.SiamoApp
import com.example.siamo.ui.theme.SIAMOTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SIAMOTheme {
                Surface {
                    SiamoApp()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun DefaultPreview() {
    SIAMOTheme {
        Surface {
            SiamoApp()
        }
    }
}
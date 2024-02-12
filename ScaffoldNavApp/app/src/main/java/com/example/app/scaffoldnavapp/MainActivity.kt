package com.example.app.scaffoldnavapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app.scaffoldnavapp.ui.theme.ScaffoldNavAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldNavAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldNavApp()
                }
            }
        }
    }
}

@Composable
fun ScaffoldNavApp() {
    ScaffoldNavAppScreen()
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    // ScaffoldNavAppTheme {
        ScaffoldNavApp()
    // }
}
package com.example.app.loginform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                LoginCard(
                    modifier = Modifier.padding(16.dp),
                    colors = MaterialTheme.colorScheme.inverseOnSurface
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationFormPreview() {
    Surface (
        color = MaterialTheme.colorScheme.background
    ) {
        LoginCard(
            modifier = Modifier.padding(16.dp),
            colors = MaterialTheme.colorScheme.inverseOnSurface
        )
    }
}
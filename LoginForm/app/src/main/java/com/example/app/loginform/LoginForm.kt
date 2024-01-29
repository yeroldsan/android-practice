package com.example.app.loginform

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app.loginform.ui.theme.LoginFormTheme

@Composable
fun RegistrationForm(modifier: Modifier = Modifier) {
    var usernameInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }

    val onUserNameChange: (String) -> Unit = { usernameInput = it }
    val onPasswordChange: (String) -> Unit = { passwordInput = it }

    Column {
        Text(text = "Login")

    }
}

/*
@Preview(showBackground = true)
@Composable
fun RegistrationFormPreview() {
    LoginFormTheme {
        RegistrationForm("Android")
    }
}
*/
package com.example.app.loginform

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginForm() {
    var email by remember { mutableStateOf("") }
    var isValidEmail by remember { mutableStateOf(true) }
    var password by remember { mutableStateOf("") }
    var isValidPassword by remember{ mutableStateOf(true) }
    var passwordHidden by remember { mutableStateOf(true) }

    fun validateEmail(email: String): Boolean {
        val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+\$")
        return email.matches(emailRegex)
    }

    fun validatePassword(pass: String): Boolean {
        return pass.isNotEmpty() && pass.length >= 8
    }

    val onEmailChange: (String) -> Unit = {
        email = it
        isValidEmail = validateEmail(it)
    }
    val onPasswordChange: (String) -> Unit = {
        password = it
        isValidPassword = validatePassword(it)
    }

    Text(
        text = "Login",
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(24.dp)
        )
    Row (
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text(text = "Email") },
            isError = !isValidEmail,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                Text(text = if (!isValidEmail) "Invalid email address" else "")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                autoCorrect = false,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions {
                validateEmail(email)
            },
            trailingIcon = {
                Icon(imageVector = Icons.Outlined.Email, contentDescription = "Email Icon")
            }
        )
    }
    Row (
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(text = "Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                Text(text = if (!isValidPassword) "At least 8 characters" else "")
            },
            isError = !isValidPassword,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions { validatePassword(password) },
            visualTransformation =
            if (passwordHidden) PasswordVisualTransformation()
            else VisualTransformation.None,
            trailingIcon = {
                val visibilityIcon = // Change icon based on password visibility state
                    if (passwordHidden) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff
                // Localized description for accessibility services
                val description = if (passwordHidden) "Show password" else "Hide password"

                IconButton(
                    onClick = { passwordHidden = !passwordHidden }
                ) {
                    Icon(imageVector = visibilityIcon, contentDescription = description)
                }
            }
        )
    }
    Button(
        onClick = { /*TODO*/ },
        enabled = isValidEmail && isValidPassword && email.isNotEmpty() && password.isNotEmpty(),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Text(text = "Login")
    }
}

@Composable
fun LoginCard(modifier: Modifier = Modifier, colors: Color) {
    /*
    By default, a Card wraps its content in a Column composable,
    placing each item inside the card below one another.
     */
    Card (
        modifier = modifier,
        colors = CardDefaults.cardColors(colors)
    ) {
        LoginForm()
    }
}

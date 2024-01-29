package com.example.app.loginform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.loginform.ui.theme.LoginFormTheme

@Composable
fun RegistrationForm(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    // var passwordHidden by remember { mutableStateOf(true) }
    var isError by remember{ mutableStateOf(false) }
    val charLimit = 8

    fun validatePassword(text: String) {
        isError = text.length < charLimit
    }

    val onUserNameChange: (String) -> Unit = { email = it }
    val onPasswordChange: (String) -> Unit = { password = it }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 36.dp)
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.paddingFromBaseline(bottom = 42.dp)
        )
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = onUserNameChange,
                label = { Text(text = "Email") },
                singleLine = true,
            )
        }
       Row (
           horizontalArrangement = Arrangement.Center,
           verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier.fillMaxWidth()

       ) {
           Icon(
               imageVector = Icons.Outlined.Lock,
               contentDescription = null,
               modifier = Modifier.padding(horizontal = 10.dp)
           )
           OutlinedTextField(
               value = password,
               onValueChange = onPasswordChange,
               label = { Text(text = "Password") },
               singleLine = true,
               isError = isError,
               keyboardActions = KeyboardActions { validatePassword(password) },
               keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//               visualTransformation =
//                    if (passwordHidden) PasswordVisualTransformation()
//                    else VisualTransformation.None,
//               trailingIcon = {
//                   IconButton(onClick = { passwordHidden = !passwordHidden }) {
//                       val visibilityIcon =
//                           if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
//                       // Please provide localized description for accessibility services
//                       val description = if (passwordHidden) "Show password" else "Hide password"
//                       Icon(imageVector = visibilityIcon, contentDescription = description)
//                   }
//               }
           )
       }
    }
}

@Composable
fun LoginCard() {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 500.dp)
            .paddingFromBaseline(bottom = 0.dp)
    ) {
        RegistrationForm()
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationFormPreview() {
    LoginCard()
    LoginFormTheme {
        // LoginCard()
    }
}

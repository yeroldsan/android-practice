package com.example.app.loginform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.loginform.ui.theme.LoginFormTheme

@Composable
fun RegistrationForm(modifier: Modifier = Modifier) {
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
                tint = if (isValidEmail) MaterialTheme.colorScheme.primary else Color.Black,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                label = { Text(text = "Email") },
                isError = !isValidEmail,
                singleLine = true,
                supportingText = {
                    Text(text = if (!isValidEmail) "Invalid email address" else "")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    autoCorrect = false,
                    // capitalization = KeyboardOptions.Capitalization.None,
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions {
                    validateEmail(email)
                }
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
               tint = if (isValidEmail) MaterialTheme.colorScheme.primary else Color.Black,
               modifier = Modifier.padding(horizontal = 10.dp)
           )
           OutlinedTextField(
               value = password,
               onValueChange = onPasswordChange,
               label = { Text(text = "Password") },
               singleLine = true,
               supportingText = {
                   Text(text = if (!isValidPassword) "Password must be at least 8 characters" else "")
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
            enabled = isValidEmail && isValidPassword,
            // modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
        ) {
            Text(text = "Login")
        }
    }
}

@Composable
fun LoginCard() {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            //.align(Alignment.BottomCenter),
//            .heightIn(max = 500.dp)
//            .paddingFromBaseline(bottom = 0.dp)
//            .padding(horizontal = 16.dp)
    ) {
        RegistrationForm()
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationFormPreview() {
    Surface {
        LoginCard()
    }
    LoginFormTheme {
        // LoginCard()
    }
}

package com.jcpd.loginscreen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jcpd.designsystem.theme.BgLoginColor
import com.jcpd.designsystem.theme.OrangeMain
import com.jcpd.drawable.R


@Composable
fun LoginButton(state: LoginScreenState, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp), onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = OrangeMain,
            disabledContainerColor = Color.Red
        ),
        enabled = state.loading != true
    ) {
        if (state.loading == true) {
            Text("Loading")
        } else {
            Text("Log in")
        }
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier,
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val state by viewModel.state.collectAsStateWithLifecycle()
    var validate : Boolean? by remember { mutableStateOf(null)}

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BgLoginColor)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.drink_app_logo),
                contentDescription = "Drinks App Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(1f)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("User") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.Gray,
                    focusedBorderColor = OrangeMain,
                    unfocusedBorderColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it
                                validate = false},
                placeholder = { Text("Password") },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(id = if (passwordVisible) R.drawable.outline_visibility_24 else R.drawable.outline_visibility_off_24), // Assuming you have a drawable for the "off" state too
                            contentDescription = "Toggle visibility",
                            tint = if (passwordVisible) OrangeMain else Color.Gray
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.Gray,
                    focusedBorderColor = OrangeMain,
                    unfocusedBorderColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            LoginButton(state = state) {
                viewModel.signInWithEmailAndPassword(
                    email = email,
                    password = password
                )
                validate = true
            }
            SignUpText(
                onSignUpClicked = onRegisterClick
            )
            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (state.access == true) {
                    onLoginSuccess()
                } else if (state.loading == true) {
                    CircularProgressIndicator(modifier = Modifier.padding(top = 8.dp))
                } else if (state.access == false && validate == true) {
                    Text(
                        "Usuario no encontrado",
                        modifier = Modifier.padding(horizontal = 24.dp),
                        color = Color.Red
                    )
                }
            } else {
                Text(
                    "Debe de llenar todos los campos",
                    modifier = Modifier.padding(horizontal = 24.dp),
                    color = Color.Red
                )
            }
        }
    }
}

@Composable
fun SignUpText(onSignUpClicked: () -> Unit) {
    val annotatedString = buildAnnotatedString {
        append("¿No tienes una cuenta? ")
        pushStringAnnotation(tag = "SIGN_UP", annotation = "sign_up")
        withStyle(
            style = SpanStyle(
                color = OrangeMain,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("Sign Up")
        }
        pop()
    }
    ClickableText(
        text = annotatedString,
        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "SIGN_UP", start = offset, end = offset)
                .firstOrNull()?.let {
                    onSignUpClicked()
                }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(modifier: Modifier = Modifier) {
    LoginScreen(modifier, onLoginSuccess = {}, onRegisterClick = {})
}
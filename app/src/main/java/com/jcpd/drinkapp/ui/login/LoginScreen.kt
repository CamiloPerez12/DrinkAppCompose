package com.jcpd.drinkapp.ui.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jcpd.drinkapp.R
import com.jcpd.drinkapp.ui.theme.BgLoginColor
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jcpd.drinkapp.ui.theme.OrangeButton


@Composable
fun LoginScreen(modifier: Modifier, viewModel: LoginScreenViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Column(
        modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(BgLoginColor),
        verticalArrangement = Arrangement.Top
    ) {
        Header()
        Body(modifier = modifier)
        InputText(email = email, label = "User") { email = it }
        InputPass(password = password) { password = it }
        AnimatedVisibility(state.access != true && state.access != null) {
            Text(
                "Contraseña Incorrecta",
                modifier = Modifier.padding(horizontal = 24.dp),
                color = Color.Red
            )
        }
        LoginButton(state = state) {
            viewModel.signInWithEmailAndPassword(
                email = email,
                password = password
            )
        }
    }
}

@Composable
fun Header() {
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Close app"
    )
}

@Composable
fun Body(modifier: Modifier = Modifier) {
    Column(
        modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageLogo()
    }
}

@Composable
fun ImageLogo() {
    Image(
        painterResource(R.drawable.drink_app_logo),
        contentDescription = "Image Logo"
    )
}

@Composable
fun InputText(
    modifier: Modifier = Modifier,
    email: String,
    label: String,
    onTextChanged: (String) -> Unit
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        value = email,
        label = { Text(label) },
        onValueChange = { onTextChanged(it) })
}

@Composable
fun InputPass(modifier: Modifier = Modifier, password: String, onTextChanged: (String) -> Unit) {

    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        value = password,
        onValueChange = { onTextChanged(it) },
        singleLine = true,
        label = { Text("Password") },
        visualTransformation =
            if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) Icons.Filled.ThumbUp else Icons.Filled.Clear
                val description = if (passwordHidden) "Show password" else "Hide password"
                Icon(imageVector = visibilityIcon, contentDescription = description)
            }
        }
    )
}


@Composable
fun LoginButton(state: LoginScreenState, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp), onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = OrangeButton,
            disabledContainerColor = Color.Red
        ),
        enabled = state.loading != true
    ) {
        if (state.loading == true) {
            Text("Loading")
        } else {
            Text("No loading")
        }

    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(modifier: Modifier = Modifier) {
    LoginScreen(modifier)
}
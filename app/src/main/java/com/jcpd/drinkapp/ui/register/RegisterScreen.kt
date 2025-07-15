package com.jcpd.drinkapp.ui.register

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.jcpd.drinkapp.R
import com.jcpd.drinkapp.ui.theme.BgLoginColor
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jcpd.drinkapp.ui.login.RegisterViewModel
import com.jcpd.drinkapp.ui.theme.OrangeMain


@Composable
fun RegisterButton(state: RegisterScreenState, onClick: () -> Unit) {
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
            Text("Sign in")
        }
    }
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier,
    onNavigateBackToLogin: () -> Unit,
    onSignUpSuccess: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordVisible2 by remember { mutableStateOf(false) }
    val state by viewModel.state.collectAsStateWithLifecycle()
    var validate: Boolean? by remember { mutableStateOf(null) }
    val openAlertDialog = remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(BgLoginColor)
            .padding(horizontal = 24.dp),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BgLoginColor,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("")
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBackToLogin) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = OrangeMain,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        }
    ) {
        Box(
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
                    placeholder = { Text("Escribe tu email") },
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
                    onValueChange = {
                        password = it
                        validate = false
                    },
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
                OutlinedTextField(
                    value = password2,
                    onValueChange = {
                        password2 = it
                        validate = false
                    },
                    placeholder = { Text("Password") },
                    singleLine = true,
                    visualTransformation = if (passwordVisible2) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible2 = !passwordVisible2 }) {
                            Icon(
                                painter = painterResource(id = if (passwordVisible2) R.drawable.outline_visibility_24 else R.drawable.outline_visibility_off_24), // Assuming you have a drawable for the "off" state too
                                contentDescription = "Toggle visibility",
                                tint = if (passwordVisible2) OrangeMain else Color.Gray
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

                RegisterButton(state = state) {
                    viewModel.createUserWithEmailAndPassword(
                        email = email,
                        password = password
                    )
                    validate = true
                }
                if (email.isNotEmpty() && password.isNotEmpty() && password2.isNotEmpty()) {
                    if (state.access == true) {
                        AlertDialogExample(
                            onDismissRequest = { openAlertDialog.value = false },
                            onConfirmation = {
                                println("Confirmation registered")
                                openAlertDialog.value = false
                                onNavigateBackToLogin()
                            },
                            dialogTitle = "User created",
                            dialogText = "Se a creado el usuario: ${email}",
                            icon = Icons.Default.Info
                        )
                        onSignUpSuccess
                    } else if (state.loading == true) {
                        CircularProgressIndicator(modifier = Modifier.padding(top = 8.dp))
                    } else if (state.access == false && validate == true) {
                        Text(
                            "Usuario no creado",
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
}
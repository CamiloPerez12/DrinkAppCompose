package com.jcpd.drinkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jcpd.drinkapp.navigation.HomeScreenRoute
import com.jcpd.drinkapp.navigation.LoginScreenRoute
import com.jcpd.drinkapp.navigation.RegisterScreenRoute
import com.jcpd.drinkapp.ui.homeScreen.HomeScreen
import com.jcpd.drinkapp.ui.login.LoginScreen
import com.jcpd.drinkapp.ui.register.RegisterScreen
import com.jcpd.drinkapp.ui.theme.DrinkAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrinkAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController() // Create and remember the NavController

    NavHost(
        navController = navController,
        startDestination = LoginScreenRoute.route // The first screen to show
    ) {
        composable(LoginScreenRoute.route) {
            LoginScreen(
                modifier = Modifier,
                onLoginSuccess = {
                    navController.navigate(HomeScreenRoute.route) {
                        popUpTo(LoginScreenRoute.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onRegisterClick = {
                    navController.navigate(RegisterScreenRoute.route) {
                        popUpTo(RegisterScreenRoute.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                viewModel = viewModel(),
            )
        }
        composable(HomeScreenRoute.route) {
            HomeScreen()
        }
        composable(RegisterScreenRoute.route) {
            RegisterScreen(
                modifier = Modifier,
                onSignUpSuccess = {
                    navController.navigate(HomeScreenRoute.route) {
                        popUpTo(LoginScreenRoute.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigateBackToLogin = {
                    navController.popBackStack()
                }
            )
        }
    }
}
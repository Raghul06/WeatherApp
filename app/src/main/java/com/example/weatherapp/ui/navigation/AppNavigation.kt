package com.example.weatherapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.ui.login.LoginScreen
import com.example.weatherapp.ui.onboarding.OnboardingScreen
import com.example.weatherapp.ui.userList.UserListScreen
import com.example.weatherapp.ui.userform.UserFormScreen

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "onboarding",
        modifier = modifier
    ) {
        composable("onboarding") {
            OnboardingScreen(
                onLoginClick = {
                    navController.navigate("login")
                }
            )
        }
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("userList")
                }
            )
        }
        composable("userList") {
            UserListScreen(
                onAddUserClick = {
                    navController.navigate("userForm")
                }
                /*,
                onUserClick = {
                    navController.navigate("weather")
                }*/
            )
        }
        composable("userForm") {
            UserFormScreen(
                onSave = {
                    navController.navigate("userList")
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
        /*





        composable("weather") {
            WeatherScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onLogoutClick = {
                    navController.navigate("login") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }*/
    }
}

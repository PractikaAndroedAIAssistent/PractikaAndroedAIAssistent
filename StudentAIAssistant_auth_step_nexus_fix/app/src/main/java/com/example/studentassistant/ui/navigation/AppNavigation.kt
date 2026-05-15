package com.example.studentassistant.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.NoteAlt
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.SmartToy
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studentassistant.feature.auth.presentation.AuthViewModel
import com.example.studentassistant.feature.auth.presentation.LoginScreen
import com.example.studentassistant.feature.auth.presentation.RegisterScreen
import com.example.studentassistant.feature.auth.presentation.WelcomeScreen
import com.example.studentassistant.ui.screens.AiToolsScreen
import com.example.studentassistant.ui.screens.HomeScreen
import com.example.studentassistant.ui.screens.NotesScreen
import com.example.studentassistant.ui.screens.ProfileScreen
import com.example.studentassistant.ui.screens.ScheduleScreen

sealed class AuthScreen(val route: String) {
    data object Welcome : AuthScreen("welcome")
    data object Login : AuthScreen("login")
    data object Register : AuthScreen("register")
}

sealed class BottomScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomScreen("home", "Главная", Icons.Rounded.Home)
    data object Schedule : BottomScreen("schedule", "Расписание", Icons.Rounded.CalendarMonth)
    data object Notes : BottomScreen("notes", "Конспекты", Icons.Rounded.NoteAlt)
    data object Ai : BottomScreen("ai", "AI", Icons.Rounded.SmartToy)
    data object Profile : BottomScreen("profile", "Профиль", Icons.Rounded.Person)
}

@Composable
fun AppNavigation(
    authViewModel: AuthViewModel = viewModel()
) {
    val authState by authViewModel.uiState.collectAsState()

    if (authState.isAuthorized) {
        MainNavigation(
            userName = authState.currentUser?.firstName.orEmpty(),
            onLogout = authViewModel::logout
        )
    } else {
        AuthNavigation(authViewModel = authViewModel)
    }
}

@Composable
private fun AuthNavigation(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val authState by authViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = AuthScreen.Welcome.route
    ) {
        composable(AuthScreen.Welcome.route) {
            WelcomeScreen(
                onLoginClick = { navController.navigate(AuthScreen.Login.route) },
                onRegisterClick = { navController.navigate(AuthScreen.Register.route) }
            )
        }

        composable(AuthScreen.Login.route) {
            LoginScreen(
                state = authState,
                onLogin = authViewModel::login,
                onRegisterClick = { navController.navigate(AuthScreen.Register.route) }
            )
        }

        composable(AuthScreen.Register.route) {
            RegisterScreen(
                state = authState,
                onRoleSelected = authViewModel::selectRole,
                onRegister = authViewModel::register,
                onLoginClick = { navController.navigate(AuthScreen.Login.route) }
            )
        }
    }
}

@Composable
private fun MainNavigation(
    userName: String,
    onLogout: () -> Unit
) {
    val navController = rememberNavController()
    val bottomItems = listOf(
        BottomScreen.Home,
        BottomScreen.Schedule,
        BottomScreen.Notes,
        BottomScreen.Ai,
        BottomScreen.Profile
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomItems.forEach { screen ->
                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(BottomScreen.Home.route) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomScreen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomScreen.Home.route) { HomeScreen() }
            composable(BottomScreen.Schedule.route) { ScheduleScreen() }
            composable(BottomScreen.Notes.route) { NotesScreen() }
            composable(BottomScreen.Ai.route) { AiToolsScreen() }
            composable(BottomScreen.Profile.route) {
                ProfileScreen(userName = userName, onLogout = onLogout)
            }
        }
    }
}

package com.example.gramaangana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gramaangana.ui.screens.*
import com.example.gramaangana.ui.theme.GramaAnganaTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            GramaAnganaTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    GramaAnganaApp()
                }
            }
        }
    }
}

@Composable
fun GramaAnganaApp() {

    val navController = rememberNavController()

    val auth = FirebaseAuth.getInstance()

    val startDestination =
        if (auth.currentUser != null)
            "home"
        else
            "login"

    val currentRoute =
        navController.currentBackStackEntryFlow
            .collectAsState(initial = navController.currentBackStackEntry)
            .value
            ?.destination
            ?.route

    val showBottomBar =
        currentRoute != "login"

    Scaffold(

        bottomBar = {

            if (showBottomBar) {

                GramaBottomNav(
                    navController = navController,
                    currentRoute = currentRoute
                )
            }
        }

    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        ) {

            composable("login") {

                LoginScreen(
                    onLogin = {
                        navController.navigate("home")
                    },

                    onSignupClick = {
                        navController.navigate("signup")
                    }
                )
            }

            composable("signup") {

                SignupScreen(

                    onSignupSuccess = {
                        navController.navigate("home")
                    },

                    onBackToLogin = {
                        navController.popBackStack()
                    }
                )
            }

            composable("home") {
                HomeDashboard(navController)
            }

            composable("calendar") {
                EventCalendarScreen(navController)
            }

            composable(
                "booking/{selectedDate}"
            ) { backStackEntry ->

                val selectedDate =
                    backStackEntry.arguments
                        ?.getString("selectedDate")
                        ?: ""

                BookingRequestScreen(

                    navController = navController,

                    selectedDate = selectedDate,

                    onSuccess = {
                        navController.popBackStack()
                    }
                )
            }
            composable("maintenance") {
                MaintenanceJarScreen()
            }

            composable("board") {
                EventBoardScreen()
            }

            composable("adminBookings") {
                AdminBookingsScreen()
            }
        }
    }
}
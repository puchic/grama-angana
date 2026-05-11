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
            startDestination = "login",
            modifier = Modifier.padding(paddingValues)
        ) {

            composable("login") {

                LoginScreen(
                    onLogin = {
                        navController.navigate("home")
                    }
                )
            }

            composable("home") {
                HomeDashboard(navController)
            }

            composable("calendar") {
                EventCalendarScreen()
            }

            composable("booking") {
                BookingRequestScreen(
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
        }
    }
}
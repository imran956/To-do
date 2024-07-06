package com.example.to_dolist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.to_dolist.navigation.destination.listComposable
import com.example.to_dolist.navigation.destination.splashComposable
import com.example.to_dolist.navigation.destination.taskComposable
import com.example.to_dolist.ui.viewmodels.SharedViewModel
import com.example.to_dolist.util.Constants.SPLASH_SCREEN

@Composable
fun SetUpNavigation(
    sharedViewModel: SharedViewModel,
    navController: NavHostController
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = SPLASH_SCREEN) {

        splashComposable (
            navigateToListScreen = screen.splash
        )
        listComposable(
            navigateToTaskScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
    }
}
package com.example.to_dolist.navigation.destination

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.to_dolist.ui.screens.splash.SplashScreen
import com.example.to_dolist.util.Constants.SPLASH_SCREEN

fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        route = SPLASH_SCREEN,
        exitTransition = {
            slideOutVertically (
                targetOffsetY = { -it },
                animationSpec = tween(300)
            )
        }
    ) {
        SplashScreen(navigateToListScreen)
    }
}
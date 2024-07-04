package com.example.to_dolist.navigation.destination

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_dolist.ui.screens.list.ListScreen
import com.example.to_dolist.ui.viewmodels.SharedViewModel
import com.example.to_dolist.util.Constants.LIST_ARGUMENT_KEY
import com.example.to_dolist.util.Constants.LIST_SCREEN
import com.example.to_dolist.util.toAction

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
){
    composable(
        route = LIST_SCREEN,
        arguments = listOf(
            navArgument(LIST_ARGUMENT_KEY){
            type = NavType.StringType
        })
    ){navBackStackEntry ->
        //Here we get the action from the arguments but in string type we have to convert that in their respective type
        //so we use the extension function that convert string to action
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()
//        Log.d("listComposable", action.name)
        LaunchedEffect(key1 = action) {
            sharedViewModel.action.value = action
        }
        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}
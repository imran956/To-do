package com.example.to_dolist.navigation.destination

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_dolist.ui.screens.task.TaskScreen
import com.example.to_dolist.ui.viewmodels.SharedViewModel
import com.example.to_dolist.util.Action
import com.example.to_dolist.util.Constants
import com.example.to_dolist.util.Constants.TASK_ARGUMENT_KEY

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
){
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY){
            type = NavType.IntType
        })
    ){navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
//        Log.d("taskComposable", taskId.toString())
        //sharedViewModel.getSelectedTask(taskId = taskId) has been called from LaunchedEffect later
        LaunchedEffect(key1 = taskId) {
            sharedViewModel.getSelectedTask(taskId = taskId)
        }
        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        //In launchedEffect whenever key changes this block will be triggered
        LaunchedEffect(key1 = selectedTask){
//            Log.d("selectedTask", selectedTask.toString())
            if(selectedTask != null || taskId == -1){
                //this code was getting executed whenever key was changing that means on delete action
                // also it was getting executed and update our text field to null and if  try to undo
                // that task then I was getting null that's why I have keep this inside if block .
                // Now this will only be executed whenever there will be task in selected id or
                // if floating action button clicked that is taskId = -1
                sharedViewModel.updateTaskFields(selectedTask = selectedTask)
            }
        }
        TaskScreen(
            navigateToListScreen = navigateToListScreen,
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel
        )
    }
}
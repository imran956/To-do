package com.example.to_dolist.ui.screens.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_dolist.R
import com.example.to_dolist.components.DisplayAlertDialog
import com.example.to_dolist.data.models.Priority
import com.example.to_dolist.data.models.ToDoTask
import com.example.to_dolist.ui.theme.topAppBarBackgroundColor
import com.example.to_dolist.ui.theme.topAppBarContentColor
import com.example.to_dolist.util.Action

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {

    if (selectedTask == null) {
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    } else {
        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.add_task),
                color = MaterialTheme.colorScheme.topAppBarContentColor
            )
        },
        navigationIcon = {
            BackAction(onBackClicked = navigateToListScreen)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor
        ),
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }
    )
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {

    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.add_task),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )

    }
}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit
) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.back_arrow),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingTaskAppBar(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colorScheme.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            CloseAction(onCloseClicked = navigateToListScreen)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor
        ),
        actions = {
            ExistingTaskAppBarActions(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen
            )
        }
    )
}

@Composable
fun ExistingTaskAppBarActions(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit
) {
    var openDialog by remember {
        mutableStateOf(false)
    }
    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_task, selectedTask.title),
        message = stringResource(id = R.string.delete_task_confirmation, selectedTask.title),
        openDialog = openDialog,
        onCloseClicked = { openDialog = false },
        onYesClicked = { navigateToListScreen(Action.DELETE) }
    )
    DeleteAction(onDeleteClicked = { openDialog = true })
    UpdateAction(onUpdateClicked = navigateToListScreen)
}

@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit
) {

    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_icon),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteAction(
    onDeleteClicked: () -> Unit
) {

    IconButton(onClick = { onDeleteClicked() }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit
) {

    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update_icon),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Preview
@Composable
private fun TaskAppBarPreview() {
    TaskAppBar(navigateToListScreen = {}, selectedTask = null)
}
package com.example.to_dolist.ui.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_dolist.R
import com.example.to_dolist.data.models.Priority
import com.example.to_dolist.data.models.ToDoTask
import com.example.to_dolist.ui.theme.HighPriorityColor
import com.example.to_dolist.ui.theme.LARGEST_PADDING
import com.example.to_dolist.ui.theme.LARGE_PADDING
import com.example.to_dolist.ui.theme.PRIORITY_INDICATOR_SIZE
import com.example.to_dolist.ui.theme.TASK_ITEM_ELEVATION
import com.example.to_dolist.ui.theme.taskItemBackgroundColor
import com.example.to_dolist.ui.theme.taskItemTextColor
import com.example.to_dolist.util.RequestState
import com.example.to_dolist.util.SearchAppBarState

@Composable
fun ListContent(
    allTasks: RequestState<List<ToDoTask>>,
    searchedTasks: RequestState<List<ToDoTask>>,
    lowPriorityTasks: List<ToDoTask>,
    highPriorityTasks: List<ToDoTask>,
    sortState: RequestState<Priority>,
    searchAppBarState: SearchAppBarState,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    padding: PaddingValues
) {
    if(sortState is RequestState.Success){
        when{
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (searchedTasks is RequestState.Success) {
                    HandleListContent(
                        task = searchedTasks.data,
                        navigateToTaskScreen = navigateToTaskScreen,
                        padding = padding
                    )
                }

            }
            sortState.data == Priority.None ->{
                if (allTasks is RequestState.Success) {
                    HandleListContent(
                        task = allTasks.data,
                        navigateToTaskScreen = navigateToTaskScreen,
                        padding = padding
                    )
                }
            }
            sortState.data == Priority.Low -> {
                HandleListContent(
                    task = lowPriorityTasks,
                    navigateToTaskScreen = navigateToTaskScreen,
                    padding = padding
                )
            }
            sortState.data == Priority.High -> {
                HandleListContent(
                    task = highPriorityTasks,
                    navigateToTaskScreen = navigateToTaskScreen,
                    padding = padding
                )
            }
        }
    }
}

@Composable
fun HandleListContent(
    task: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    padding: PaddingValues
) {
    if (task.isEmpty()) {
        EmptyContent()
    } else {
        DisplayTasks(
            tasks = task,
            navigateToTaskScreen = navigateToTaskScreen,
            padding = padding
        )
    }
}

@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    padding: PaddingValues
) {
    LazyColumn(modifier = Modifier.padding(padding)) {
        items(
            tasks,
            key = { task ->
                task.id
            }
        ) { task ->
            TaskItem(
                toDoTask = task,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}

@Composable
fun RedBackground(degree: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HighPriorityColor)
            .padding(horizontal = LARGEST_PADDING),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier.rotate(degree),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(R.string.delete_icon),
            tint = Color.White
        )
    }
}

@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.taskItemBackgroundColor,
        shape = RoundedCornerShape(5.dp),
        shadowElevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    color = MaterialTheme.colorScheme.taskItemTextColor,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }
                }
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                color = MaterialTheme.colorScheme.taskItemTextColor,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Light,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun TaskItemPreview() {
    TaskItem(
        toDoTask = ToDoTask(0, "Title", "Some random text", Priority.High),
        navigateToTaskScreen = {}
    )
}
package com.example.to_dolist.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_dolist.R
import com.example.to_dolist.components.PriorityDropDown
import com.example.to_dolist.data.models.Priority
import com.example.to_dolist.ui.theme.LARGE_PADDING
import com.example.to_dolist.ui.theme.MEDIUM_PADDING

@Composable
fun TaskContent(
    title: String,
    onTitleChanged: (String) -> Unit,
    description: String,
    onDescriptionChanged: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit,
    padding: PaddingValues
) {
    Column(modifier = Modifier.padding(start = LARGE_PADDING, end = LARGE_PADDING,bottom = LARGE_PADDING)){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = stringResource(id = R.string.title)) },
                value = title,
                onValueChange = { onTitleChanged(it) },
                textStyle = MaterialTheme.typography.bodyLarge,
                singleLine = true
            )

            Divider(
                modifier = Modifier.height(MEDIUM_PADDING),
                color = MaterialTheme.colorScheme.background
            )

            PriorityDropDown(
                priority = priority,
                onPrioritySelected = onPrioritySelected
            )

            OutlinedTextField(
                value = description,
                onValueChange = { onDescriptionChanged(it) },
                label = { Text(text = stringResource(id = R.string.description)) },
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
private fun TaskContentPreview() {
    TaskContent(
        title = "",
        onTitleChanged = {},
        description = "",
        onDescriptionChanged = {},
        priority = Priority.Medium,
        onPrioritySelected = {},
        padding = PaddingValues(0.dp)
    )
}
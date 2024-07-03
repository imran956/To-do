package com.example.to_dolist.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_dolist.R
import com.example.to_dolist.data.models.Priority
import com.example.to_dolist.ui.theme.PRIORITY_DROPDOWN_HEIGHT
import com.example.to_dolist.ui.theme.PRIORITY_INDICATOR_SIZE
import com.example.to_dolist.ui.theme.topAppBarContentColor

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val angle: Float by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    Row(
        modifier = Modifier
        .fillMaxWidth()
        .height(PRIORITY_DROPDOWN_HEIGHT)
        .clickable { expanded = true }
        .border(
            width = 1.dp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
            shape = MaterialTheme.shapes.small
        ),
        verticalAlignment = Alignment.CenterVertically,
    )
    {
        Canvas(
            modifier = Modifier
                .size(PRIORITY_INDICATOR_SIZE)
                .weight(1f)
        ) {
            drawCircle(color = priority.color)
        }
        Text(
            modifier = Modifier.weight(8f),
            text = priority.name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.topAppBarContentColor
        )
        IconButton(
            modifier = Modifier
                .weight(weight = 1.5f)
                .alpha(alpha = 0.7f)
                .rotate(degrees = angle),
            onClick = { expanded = true }
        ) {

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(id = R.string.drop_down_arrow_icon),
                tint = MaterialTheme.colorScheme.topAppBarContentColor
            )
        }
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(fraction = 0.94f),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            DropdownMenuItem(
                text = { PriorityItem(priority = Priority.Low) },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.Low)
                }
            )

            DropdownMenuItem(
                text = { PriorityItem(priority = Priority.Medium) },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.Medium)
                }
            )

            DropdownMenuItem(
                text = { PriorityItem(priority = Priority.High) },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.High)
                }
            )
        }
    }
}

@Preview
@Composable
private fun PriorityDropDownPreview() {
    PriorityDropDown(Priority.Low,{})
}
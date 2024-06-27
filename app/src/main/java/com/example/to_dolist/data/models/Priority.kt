package com.example.to_dolist.data.models

import androidx.compose.ui.graphics.Color
import com.example.to_dolist.ui.theme.HighPriorityColor
import com.example.to_dolist.ui.theme.LowPriorityColor
import com.example.to_dolist.ui.theme.MediumPriorityColor
import com.example.to_dolist.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    High(HighPriorityColor),
    Medium(MediumPriorityColor),
    Low(LowPriorityColor),
    None(NonePriorityColor)

}
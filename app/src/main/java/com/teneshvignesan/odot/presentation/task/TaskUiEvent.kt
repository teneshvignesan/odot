package com.teneshvignesan.odot.presentation.task

import androidx.compose.material3.SnackbarDuration

sealed class TaskUiEvent {
    data class ShowSnackbar(val message: String, val duration: SnackbarDuration = SnackbarDuration.Short): TaskUiEvent()
    object SaveTask: TaskUiEvent()
    object DeleteTask: TaskUiEvent()
}
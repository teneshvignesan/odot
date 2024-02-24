package com.teneshvignesan.odot.presentation.home

import androidx.compose.material3.SnackbarDuration

sealed class HomeUiEvent {
    data class ShowSnackbar(val message: String, val duration: SnackbarDuration = SnackbarDuration.Short): HomeUiEvent()
    data object CompletedTask: HomeUiEvent()
}
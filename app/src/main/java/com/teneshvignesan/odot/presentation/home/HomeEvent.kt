package com.teneshvignesan.odot.presentation.home

sealed class HomeEvent {
    data class CompletedTask(val id: Int, val completed: Boolean) : HomeEvent()
}
package com.teneshvignesan.odot.presentation.home

import com.teneshvignesan.odot.domain.model.Task

sealed class HomeEvent {
    data class DeleteTask(val task: Task): HomeEvent()
    data class CompleteTask(val id: Int): HomeEvent()
}
package com.teneshvignesan.odot.presentation.home

import com.teneshvignesan.odot.domain.model.Task

data class HomeState (
    val tasks: List<Task> = emptyList()
)
package com.teneshvignesan.odot.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teneshvignesan.odot.domain.use_cases.task.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    private var getTasksJob: Job? = null

    init {
        getTasks()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.CompleteTask -> {
                viewModelScope.launch {
                    taskUseCases.completeTask(event.id)
                }
            }

            is HomeEvent.DeleteTask -> {
                viewModelScope.launch {
                    taskUseCases.deleteTask(event.task)
                }
            }
        }
    }

    private fun getTasks() {

        getTasksJob?.cancel()
        getTasksJob = taskUseCases.getTasks()
            .onEach { tasks ->
                state.value.copy(
                    tasks = tasks,
                )
            }
            .launchIn(viewModelScope)

    }
}
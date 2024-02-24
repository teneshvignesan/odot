package com.teneshvignesan.odot.presentation.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teneshvignesan.odot.domain.model.InvalidTaskException
import com.teneshvignesan.odot.domain.use_cases.task.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _uiEventFlow = MutableSharedFlow<HomeUiEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    private var getTasksJob: Job? = null

    init {
        getTasks()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.CompletedTask -> {
                viewModelScope.launch {
                    try {
                        taskUseCases.completeTask(event.id, event.completed)
                        _uiEventFlow.emit(HomeUiEvent.CompletedTask)
                    } catch (e: InvalidTaskException) {
                        Log.d("HomeViewModel", e.message.toString())
                        _uiEventFlow.emit(
                            HomeUiEvent.ShowSnackbar(
                                message = e.message ?: "Failed to mark task completed."
                            )
                        )
                    }
                }
            }
        }
    }

    private fun getTasks() {

        getTasksJob?.cancel()
        getTasksJob = taskUseCases.getTasks()
            .onEach { tasks ->
                _state.value = state.value.copy(
                    tasks = tasks,
                )
            }
            .launchIn(viewModelScope)

    }
}
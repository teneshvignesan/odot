package com.teneshvignesan.odot.presentation.task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teneshvignesan.odot.domain.use_cases.category.CategoryUseCases
import com.teneshvignesan.odot.domain.use_cases.task.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val categoryUseCases: CategoryUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TaskState())
    val state: State<TaskState> = _state

    private var getCategoriesJob: Job? = null

    fun onEvent(event: TaskEvent) {
        when (event) {
            is TaskEvent.TitleChanged -> {
                _state.value = _state.value.copy(
                    title = event.value
                )
            }
            is TaskEvent.DescriptionChanged -> {
                _state.value = _state.value.copy(
                    description = event.value
                )
            }
            is TaskEvent.CategoryChanged -> TODO()
            is TaskEvent.CompletedChanged -> TODO()
            is TaskEvent.DeleteTask -> TODO()
            is TaskEvent.EndDateTimeChanged -> TODO()
            is TaskEvent.SaveTask -> TODO()
            is TaskEvent.StartDateTimeChanged -> TODO()
        }
    }

    private fun getCategories() {
        getCategoriesJob?.cancel()
        getCategoriesJob = categoryUseCases.getCategories()
            .onEach { categories ->
                state.value.copy(
                    categories = categories,
                )
            }
            .launchIn(viewModelScope)
    }
}
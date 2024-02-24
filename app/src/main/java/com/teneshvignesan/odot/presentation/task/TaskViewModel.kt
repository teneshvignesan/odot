package com.teneshvignesan.odot.presentation.task

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teneshvignesan.odot.domain.model.InvalidTaskException
import com.teneshvignesan.odot.domain.model.Task
import com.teneshvignesan.odot.domain.use_cases.category.CategoryUseCases
import com.teneshvignesan.odot.domain.use_cases.task.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val categoryUseCases: CategoryUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(TaskState())
    val state: State<TaskState> = _state

    private val _uiEventFlow = MutableSharedFlow<TaskUiEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()


    private var getCategoriesJob: Job? = null

    init {
        savedStateHandle.get<Int>("taskId")?.let { taskId ->
            if (taskId != -1) {
                viewModelScope.launch {
                    taskUseCases.getTask(taskId)?.also { task ->
                        _state.value.currentId = task.id
                        _state.value = _state.value.copy(
                            title = task.title,
                            description = task.description,
                            completed = task.completed,
                            selectedCategoryId = task.categoryId,
                            startDate = LocalDate.from(task.startDateTime),
                            startTime = LocalTime.from(task.startDateTime),
                        )
                    }
                }
            }
        }
        getCategories()

    }

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

            is TaskEvent.CategoryChanged -> {
                _state.value = _state.value.copy(
                    selectedCategoryId = event.value
                )
            }

            is TaskEvent.CompletedChanged -> {
                Log.d("CompletedChanged", event.value.toString())
            }

            is TaskEvent.DeleteTask -> {
                viewModelScope.launch {
                    try {
                        taskUseCases.deleteTask(
                            Task(
                                _state.value.currentId,
                                _state.value.title,
                                _state.value.description,
                                _state.value.selectedCategoryId,
                                LocalDateTime.of(_state.value.startDate, _state.value.startTime),
                                _state.value.completed
                            )
                        )
                        _uiEventFlow.emit(TaskUiEvent.DeleteTask)
                    } catch (e: InvalidTaskException) {
                        Log.d("TaskViewModel", e.message.toString())
                        _uiEventFlow.emit(
                            TaskUiEvent.ShowSnackbar(
                                message = e.message ?: "Failed to delete task."
                            )
                        )
                    }
                }
            }

            is TaskEvent.SaveTask -> {

                viewModelScope.launch {
                    try {
                        taskUseCases.saveTask(
                            Task(
                                _state.value.currentId,
                                _state.value.title,
                                _state.value.description,
                                _state.value.selectedCategoryId,
                                LocalDateTime.of(_state.value.startDate, _state.value.startTime),
                                _state.value.completed
                            )
                        )
                        _uiEventFlow.emit(TaskUiEvent.SaveTask)
                    } catch (e: InvalidTaskException) {
                        Log.d("TaskViewModel", e.message.toString())
                        _uiEventFlow.emit(
                            TaskUiEvent.ShowSnackbar(
                                message = e.message ?: "Failed to save task."
                            )
                        )
                    }
                }
            }

            is TaskEvent.ToggleDatePickerDialog -> {
                if (event.value) {
                    _state.value = _state.value.copy(
                        showDatePicker = true
                    )
                    return
                }

                _state.value = _state.value.copy(
                    showDatePicker = false
                )
            }

            is TaskEvent.ToggleTimePickerDialog -> {
                if (event.value) {
                    _state.value = _state.value.copy(
                        showTimePicker = true
                    )
                    return
                }

                _state.value = _state.value.copy(
                    showTimePicker = false
                )
            }

            is TaskEvent.StartDateChanged -> {

                _state.value = _state.value.copy(
                    startDate = event.value
                )
            }

            is TaskEvent.StartTimeChanged -> {

                _state.value = _state.value.copy(
                    startTime = event.value
                )
            }
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
package com.teneshvignesan.odot.presentation.task

import android.util.Log
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

            is TaskEvent.CategoryChanged -> {
                _state.value = _state.value.copy(
                    selectedCategoryId = event.value
                )
            }

            is TaskEvent.CompletedChanged -> {
                Log.d("CompletedChanged", event.value.toString())
            }

            is TaskEvent.DeleteTask -> {
                Log.d("DeleteTask", event.toString())
            }

            is TaskEvent.SaveTask -> {
                Log.d("SaveTask", event.toString())
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
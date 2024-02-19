package com.teneshvignesan.odot.presentation.task

import com.teneshvignesan.odot.domain.model.Task
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

sealed class TaskEvent {
    data class TitleChanged(val value: String) : TaskEvent()
    data class DescriptionChanged(val value: String) : TaskEvent()
    data class CategoryChanged(val value: Int) : TaskEvent()
    data class CompletedChanged(val value: Boolean) : TaskEvent()
    data class StartDateChanged(val value: LocalDate) : TaskEvent()
    data class StartTimeChanged(val value: LocalTime) : TaskEvent()
    data class SaveTask(val task: Task) : TaskEvent()
    data class DeleteTask(val task: Task) : TaskEvent()
    data class ToggleDatePickerDialog(val value: Boolean) : TaskEvent()
    data class ToggleTimePickerDialog(val value: Boolean) : TaskEvent()
}
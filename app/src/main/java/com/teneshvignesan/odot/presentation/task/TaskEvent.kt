package com.teneshvignesan.odot.presentation.task

import java.time.LocalDate
import java.time.LocalTime

sealed class TaskEvent {
    data class TitleChanged(val value: String) : TaskEvent()
    data class DescriptionChanged(val value: String) : TaskEvent()
    data class CategoryChanged(val value: Int) : TaskEvent()
    data class CompletedChanged(val value: Boolean) : TaskEvent()
    data class StartDateChanged(val value: LocalDate) : TaskEvent()
    data class StartTimeChanged(val value: LocalTime) : TaskEvent()
    data object SaveTask : TaskEvent()
    data object DeleteTask : TaskEvent()
    data class ToggleDatePickerDialog(val value: Boolean) : TaskEvent()
    data class ToggleTimePickerDialog(val value: Boolean) : TaskEvent()
}
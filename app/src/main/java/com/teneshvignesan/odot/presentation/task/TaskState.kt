package com.teneshvignesan.odot.presentation.task

import com.teneshvignesan.odot.domain.model.Category
import java.time.LocalDate
import java.time.LocalTime

data class TaskState(
    var currentId: Int? = null,
    val categories: List<Category> = emptyList(),
    var title: String = "",
    var description: String = "",
    var startDate: LocalDate = LocalDate.now(),
    var startTime: LocalTime = LocalTime.now(),
    var completed: Boolean = false,
    var selectedCategoryId: Int? = null,
    var showDatePicker: Boolean = false,
    var showTimePicker: Boolean = false,
)
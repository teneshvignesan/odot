package com.teneshvignesan.odot.presentation.task

import com.teneshvignesan.odot.domain.model.Category
import java.time.LocalDateTime

data class TaskState(
    var currentId: Int? = null,
    val categories: List<Category> = emptyList(),
    var title: String = "",
    var description: String = "",
    var startDateTime: LocalDateTime = LocalDateTime.now(),
    var endDateTime: LocalDateTime? = null,
    var completed: Boolean = false,
    var selectedCategoryId: Int? = null,
)
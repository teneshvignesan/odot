package com.teneshvignesan.odot.presentation.task

import com.teneshvignesan.odot.domain.model.Task
import java.time.LocalDateTime

sealed class TaskEvent {
    data class TitleChanged(val value: String) : TaskEvent()
    data class DescriptionChanged(val value: String) : TaskEvent()
    data class CategoryChanged(val value: Int) : TaskEvent()
    data class CompletedChanged(val value: Boolean) : TaskEvent()
    data class StartDateTimeChanged(val value: LocalDateTime) : TaskEvent()
    data class EndDateTimeChanged(val value: LocalDateTime) : TaskEvent()
    data class SaveTask(val task: Task) : TaskEvent()
    data class DeleteTask(val task: Task) : TaskEvent()
}
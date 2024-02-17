package com.teneshvignesan.odot.domain.use_cases.task

import com.teneshvignesan.odot.domain.model.Task
import com.teneshvignesan.odot.domain.repository.TaskRepository

class SaveTask(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        return repository.saveOne(task)
    }
}
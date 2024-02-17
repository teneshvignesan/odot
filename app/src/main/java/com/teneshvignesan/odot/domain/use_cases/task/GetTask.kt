package com.teneshvignesan.odot.domain.use_cases.task

import com.teneshvignesan.odot.domain.model.Task
import com.teneshvignesan.odot.domain.repository.TaskRepository

class GetTask(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: Int): Task? {
        return repository.getOne(id)
    }
}
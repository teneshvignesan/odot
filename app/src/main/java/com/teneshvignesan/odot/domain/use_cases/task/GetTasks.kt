package com.teneshvignesan.odot.domain.use_cases.task

import com.teneshvignesan.odot.domain.model.Task
import com.teneshvignesan.odot.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasks(
    private val repository: TaskRepository
){
    operator fun invoke(): Flow<List<Task>> {
        return repository.getAll()
    }
}
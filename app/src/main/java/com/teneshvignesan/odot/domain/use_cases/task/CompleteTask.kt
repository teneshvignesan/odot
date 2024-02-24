package com.teneshvignesan.odot.domain.use_cases.task

import com.teneshvignesan.odot.domain.repository.TaskRepository

class CompleteTask(
    private val repository: TaskRepository
){
    suspend operator fun invoke(id: Int, completed: Boolean) {
        return repository.completeOne(id,completed)
    }
}
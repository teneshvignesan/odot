package com.teneshvignesan.odot.domain.use_cases.task

import com.teneshvignesan.odot.domain.repository.TaskRepository

class CompleteTask(
    private val repository: TaskRepository
){
    suspend operator fun invoke(id: Int) {
        return repository.completeOne(id)
    }
}
package com.teneshvignesan.odot.data.repository

import com.teneshvignesan.odot.data.local.dao.TaskDao
import com.teneshvignesan.odot.domain.model.Task
import com.teneshvignesan.odot.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(private val dao: TaskDao): TaskRepository {
    override fun getAll(): Flow<List<Task>> {
        return dao.getAll()
    }

    override suspend fun getOne(id: Int): Task? {
        return dao.getOne(id)
    }

    override suspend fun saveOne(task: Task) {
        return dao.saveOne(task)
    }

    override suspend fun deleteOne(task: Task) {
        return dao.deleteOne(task)
    }

    override suspend fun deleteAll() {
        return dao.deleteAll()
    }

    override suspend fun completeOne(id: Int, completed: Boolean) {
        return dao.completeOne(id, completed)
    }
}
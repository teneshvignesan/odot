package com.teneshvignesan.odot.data.repository

import com.teneshvignesan.odot.data.local.dao.TaskDao
import com.teneshvignesan.odot.domain.model.Task
import com.teneshvignesan.odot.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

class TaskRepositoryImpl(private val dao: TaskDao): TaskRepository {
    override fun getAll(): Flow<List<Task>> {
        return dao.getAll()
    }

    override fun getAllByDate(date: LocalDateTime): Flow<List<Task>> {
        return dao.getAllByDate(date)
    }

    override fun getAllByCategory(id: Int): Flow<List<Task>> {
        return dao.getAllByCategory(id)
    }

    override fun getAllByTitle(text: String): Flow<List<Task>> {
        return dao.getAllByTitle(text)
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
}
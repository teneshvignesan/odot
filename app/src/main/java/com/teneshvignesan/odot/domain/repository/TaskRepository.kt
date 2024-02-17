package com.teneshvignesan.odot.domain.repository

import com.teneshvignesan.odot.domain.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface TaskRepository {

    fun getAll(): Flow<List<Task>>

    suspend fun getOne(id: Int): Task?

    suspend fun saveOne(task: Task)

    suspend fun deleteOne(task: Task)

    suspend fun deleteAll()

    suspend fun completeOne(id: Int)
}
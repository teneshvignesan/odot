package com.teneshvignesan.odot.domain.repository

import com.teneshvignesan.odot.domain.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface TaskRepository {

    fun getAll(): Flow<List<Task>>

    fun getAllByDate(date: LocalDateTime): Flow<List<Task>>

    fun getAllByCategory(id: Int): Flow<List<Task>>

    fun getAllByTitle(text: String): Flow<List<Task>>

    suspend fun getOne(id: Int): Task?

    suspend fun saveOne(task: Task)

    suspend fun deleteOne(task: Task)

    suspend fun deleteAll()
}
package com.teneshvignesan.odot.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.teneshvignesan.odot.domain.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks ORDER BY startDateTime DESC")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE startDateTime= :date")
    fun getAllByDate(date: LocalDateTime): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE categoryId= :categoryId")
    fun getAllByCategory(categoryId: Int): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE title= :text")
    fun getAllByTitle(text: String): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id LIKE :id")
    suspend fun getOne(id: Int): Task?

    @Upsert
    suspend fun saveOne(task: Task)

    @Delete
    suspend fun deleteOne(task: Task)

    @Query("DELETE FROM tasks")
    suspend fun deleteAll()

    @Query("UPDATE tasks SET completed= :completed WHERE id= :id")
    suspend fun completeOne(id: Int, completed: Boolean)
}
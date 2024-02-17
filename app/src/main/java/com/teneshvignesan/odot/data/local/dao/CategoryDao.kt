package com.teneshvignesan.odot.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.teneshvignesan.odot.domain.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories ORDER BY title ASC")
    fun getAll(): Flow<List<Category>>


    @Query("SELECT * FROM categories WHERE id= :id")
    suspend fun getOne(id: Int): Category?

    @Upsert
    suspend fun saveOne(category: Category)

    @Delete
    suspend fun deleteOne(category: Category)

    @Query("DELETE FROM categories")
    suspend fun deleteAll()
}
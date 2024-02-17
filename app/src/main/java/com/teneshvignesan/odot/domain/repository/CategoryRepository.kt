package com.teneshvignesan.odot.domain.repository

import com.teneshvignesan.odot.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getAll(): Flow<List<Category>>

    suspend fun getOne(id: Int): Category?

    suspend fun saveOne(category: Category)

    suspend fun deleteOne(category: Category)

    suspend fun deleteAll()
}
package com.teneshvignesan.odot.data.repository

import com.teneshvignesan.odot.data.local.dao.CategoryDao
import com.teneshvignesan.odot.domain.model.Category
import com.teneshvignesan.odot.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(private val dao: CategoryDao): CategoryRepository {
    override fun getAll(): Flow<List<Category>> {
        return dao.getAll()
    }

    override suspend fun getOne(id: Int): Category? {
        return dao.getOne(id)
    }

    override suspend fun saveOne(category: Category) {
        return dao.saveOne(category)
    }

    override suspend fun deleteOne(category: Category) {
        return dao.deleteOne(category)
    }

    override suspend fun deleteAll() {
        return dao.deleteAll()
    }
}
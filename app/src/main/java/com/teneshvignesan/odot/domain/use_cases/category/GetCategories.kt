package com.teneshvignesan.odot.domain.use_cases.category

import com.teneshvignesan.odot.domain.model.Category
import com.teneshvignesan.odot.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetCategories(
    private val repository: CategoryRepository
){
    operator fun invoke(): Flow<List<Category>> {
        return repository.getAll()
    }
}
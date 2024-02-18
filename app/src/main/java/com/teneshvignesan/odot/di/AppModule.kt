package com.teneshvignesan.odot.di

import com.teneshvignesan.odot.domain.repository.CategoryRepository
import com.teneshvignesan.odot.domain.repository.TaskRepository
import com.teneshvignesan.odot.domain.use_cases.category.CategoryUseCases
import com.teneshvignesan.odot.domain.use_cases.category.GetCategories
import com.teneshvignesan.odot.domain.use_cases.task.CompleteTask
import com.teneshvignesan.odot.domain.use_cases.task.DeleteTask
import com.teneshvignesan.odot.domain.use_cases.task.GetTask
import com.teneshvignesan.odot.domain.use_cases.task.GetTasks
import com.teneshvignesan.odot.domain.use_cases.task.SaveTask
import com.teneshvignesan.odot.domain.use_cases.task.TaskUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskUseCases(repository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            getTasks = GetTasks(repository),
            deleteTask = DeleteTask(repository),
            saveTask = SaveTask(repository),
            getTask = GetTask(repository),
            completeTask = CompleteTask(repository),
        )
    }

    @Provides
    @Singleton
    fun provideCategoryUseCases(repository: CategoryRepository): CategoryUseCases {
        return CategoryUseCases(
            getCategories = GetCategories(repository),
        )
    }
}
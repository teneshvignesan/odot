package com.teneshvignesan.odot.di

import android.app.Application
import androidx.room.Room
import com.teneshvignesan.odot.data.local.OdotDatabase
import com.teneshvignesan.odot.data.repository.CategoryRepositoryImpl
import com.teneshvignesan.odot.data.repository.TaskRepositoryImpl
import com.teneshvignesan.odot.domain.repository.CategoryRepository
import com.teneshvignesan.odot.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideOdotDatabase(app: Application): OdotDatabase {
        return Room.databaseBuilder(
            app,
            OdotDatabase::class.java,
            "odot_db"
        ).build()
    }


    @Provides
    @Singleton
    fun provideTaskRepository(db: OdotDatabase): TaskRepository {
        return TaskRepositoryImpl(db.taskDao)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(db: OdotDatabase): CategoryRepository {
        return CategoryRepositoryImpl(db.categoryDao)
    }
}
package com.teneshvignesan.odot.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teneshvignesan.odot.data.local.converter.DateConverter
import com.teneshvignesan.odot.data.local.dao.CategoryDao
import com.teneshvignesan.odot.data.local.dao.TaskDao
import com.teneshvignesan.odot.domain.model.Category
import com.teneshvignesan.odot.domain.model.Task

@Database(
    entities = [Task::class, Category::class],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class OdotDatabase: RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val categoryDao: CategoryDao
}
package com.teneshvignesan.odot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "category") val categoryId: Int,
    @ColumnInfo(name = "dueDate") val dueDate: LocalDateTime,
    @ColumnInfo(name = "completed") val completed: Boolean = false,
)

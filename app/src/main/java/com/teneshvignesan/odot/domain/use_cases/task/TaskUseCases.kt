package com.teneshvignesan.odot.domain.use_cases.task

data class TaskUseCases (
    val getTasks: GetTasks,
    val getTask: GetTask,
    val deleteTask: DeleteTask,
    val completeTask: CompleteTask,
    val saveTask: SaveTask
)
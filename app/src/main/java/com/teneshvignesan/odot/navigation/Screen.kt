package com.teneshvignesan.odot.navigation

sealed class Screen(val route: String) {
    data object HomeScreen: Screen(route = "home_screen")
    data object TaskScreen: Screen(route = "task_screen?taskId={taskId}") {
        fun passTaskId(id: Int): String {
            return "task_screen?taskId=$id"
        }
    }
}
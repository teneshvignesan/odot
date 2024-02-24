package com.teneshvignesan.odot.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.teneshvignesan.odot.presentation.home.HomeScreen
import com.teneshvignesan.odot.presentation.task.TaskScreen

@Composable
fun SetupNavGraph(startDestination: String, navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = startDestination)
    {
        homeRoute(
            navigateToTaskScreen = {
                navHostController.navigate(Screen.TaskScreen.route)
            },
            navigateToTaskScreenWithArgs = {
                navHostController.navigate(Screen.TaskScreen.passTaskId(it))
            }
        )
        taskRoute(
            navigateToHomeScreen = { navHostController.popBackStack() }
        )
    }
}

fun NavGraphBuilder.homeRoute(
    navigateToTaskScreen: () -> Unit,
    navigateToTaskScreenWithArgs: (Int) -> Unit
) {
    composable(route = Screen.HomeScreen.route) {
        HomeScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            navigateToTaskScreenWithArgs = navigateToTaskScreenWithArgs
        )
    }
}

fun NavGraphBuilder.taskRoute(
    navigateToHomeScreen: () -> Unit,
) {
    composable(
        route = Screen.TaskScreen.route,
        arguments = listOf(
            navArgument(name = "taskId") {
                type = NavType.IntType
                defaultValue = -1
            }
        )
    ) {
        TaskScreen(
            navigateToHomeScreen = navigateToHomeScreen,
        )
    }
}
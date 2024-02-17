package com.teneshvignesan.odot.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.teneshvignesan.odot.domain.model.Task
import com.teneshvignesan.odot.presentation.home.widget.TaskItem
import com.teneshvignesan.odot.presentation.task.TaskScreen
import java.time.LocalDateTime

class HomeScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getViewModel<HomeViewModel>()

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = "Search tales"
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navigator.push(TaskScreen(null)) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add new tale"
                    )
                }
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 10.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {

                    if (viewModel.state.value.tasks.isEmpty()) {
                        EmptyTasksContent()
                    }

                    if (viewModel.state.value.tasks.isNotEmpty()) {
                        TaskList(viewModel.state.value.tasks)
                    }
                }
            },
        )
    }
}

@Composable
fun EmptyTasksContent() {
    Text(
        text = "Let's begin the adventure of organizing activities more efficiently!",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TaskList(
    tasks: List<Task>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(tasks) { task ->
            TaskItem(task)
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun PreviewTasksList() {
    TaskList(
        listOf(
            Task(
                title = "Start the day with workout routine",
                description = "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before the final copy is available",
                startDateTime = LocalDateTime.now(),
                endDateTime = LocalDateTime.of(2024, 2, 18, 1, 0, 0, 0)
            ),
            Task(
                title = "Prepare presentation for work",
                description = "Lorem ipsum may be used as a placeholder before the final copy is available",
                startDateTime = LocalDateTime.now(),
                endDateTime = LocalDateTime.of(2024, 2, 18, 15, 10, 50, 30)
            ),
        )
    )
}
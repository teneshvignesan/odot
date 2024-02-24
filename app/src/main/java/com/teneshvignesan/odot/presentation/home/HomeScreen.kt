package com.teneshvignesan.odot.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teneshvignesan.odot.presentation.home.widget.TaskItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToTaskScreen: () -> Unit,
    navigateToTaskScreenWithArgs: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            modifier = Modifier.width(20.dp),
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
                            modifier = Modifier.width(20.dp),
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "Search task"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToTaskScreen() }
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
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        items(viewModel.state.value.tasks) { task ->
                            if (task.id !== null) {
                                TaskItem(
                                    task,
                                    {
                                        navigateToTaskScreenWithArgs(task.id)
                                    },
                                    {
                                        viewModel.onEvent(
                                            HomeEvent.CompletedTask(
                                                task.id,
                                                !task.completed
                                            )
                                        )
                                    },
                                )
                            }
                        }
                    }
                }
            }
        },
    )
}

@Composable
fun EmptyTasksContent() {
    Text(
        modifier = Modifier.width(300.dp),
        text = "Let's begin the adventure of organizing activities more efficiently!",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
    )
}
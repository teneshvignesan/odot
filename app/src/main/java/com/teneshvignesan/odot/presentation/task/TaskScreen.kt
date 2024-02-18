package com.teneshvignesan.odot.presentation.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.teneshvignesan.odot.presentation.task.widget.CategoryRadioGroup

data class TaskScreen(val taskId: Int? = null) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getViewModel<TaskViewModel>()

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Create New Task",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { navigator.pop() }
                        ) {
                            Icon(
                                modifier = Modifier.width(20.dp),
                                imageVector = Icons.Filled.ArrowBackIosNew,
                                contentDescription = "Back"
                            )
                        }
                    },
                )
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 10.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                ) {
                    Row {
                        Column(
                            modifier = Modifier
                                .weight(2f)
                                .padding(end = 10.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
                                text = "Task Name",
                                style = MaterialTheme.typography.titleMedium
                            )
                            TextField (
                                modifier = Modifier.fillMaxWidth(),
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                                shape = RoundedCornerShape(5.dp),
                                value = viewModel.state.value.title,
                                onValueChange = { viewModel.onEvent(TaskEvent.TitleChanged(it)) },
                                placeholder = {
                                    Text(
                                        text = "Your tale title here.",
                                        color = Color.Gray,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                },
                                singleLine = true
                            )
                            Text(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
                                text = "Description",
                                style = MaterialTheme.typography.titleMedium
                            )
                            TextField (
                                modifier = Modifier.fillMaxWidth(),
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                                shape = RoundedCornerShape(5.dp),
                                value = "",
                                onValueChange = { it },
                                placeholder = {
                                    Text(
                                        text = "Describe your tale in details here.",
                                        color = Color.Gray,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                },
                                minLines = 3,
                                maxLines = 8
                            )
                            Text(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
                                text = "Categories",
                                style = MaterialTheme.typography.titleMedium
                            )
                            if(viewModel.state.value.categories.isNotEmpty()) {
                                CategoryRadioGroup(
                                    categories = viewModel.state.value.categories,
                                    selectedCategoryId = null,
                                    onSelectedCategory = {}
                                )
                            }
                        }
                    }
                }
            },
        )
    }
}
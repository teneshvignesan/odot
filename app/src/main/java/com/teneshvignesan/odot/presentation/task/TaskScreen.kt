package com.teneshvignesan.odot.presentation.task

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.teneshvignesan.odot.presentation.widget.CustomDatePickerDialog
import com.teneshvignesan.odot.presentation.widget.CustomTimePickerDialog
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class TaskScreen(val taskId: Int? = null) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getViewModel<TaskViewModel>()

        val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm a")

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
                        .padding(horizontal = 15.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
                        text = "Task Name",
                        style = MaterialTheme.typography.titleMedium
                    )
                    TextField(
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
                        text = "Categories",
                        style = MaterialTheme.typography.titleMedium
                    )
                    if (viewModel.state.value.categories.isNotEmpty()) {
                        CategoryRadioGroup(
                            categories = viewModel.state.value.categories,
                            selectedCategoryId = 2,
                            onSelectedCategory = {
                                viewModel.onEvent(TaskEvent.CategoryChanged(it))
                            }
                        )
                    }
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
                        text = "Date & Time",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .weight(0.7f)
                                .clickable {
                                    viewModel.onEvent(
                                        TaskEvent.ToggleDatePickerDialog(
                                            true
                                        )
                                    )
                                },
                            shape = RoundedCornerShape(5.dp),
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.DateRange,
                                    contentDescription = "Task Date"
                                )
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = dateFormatter.format(viewModel.state.value.startDate),
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        Card(
                            modifier = Modifier
                                .weight(0.5f)
                                .clickable {
                                    viewModel.onEvent(
                                        TaskEvent.ToggleTimePickerDialog(
                                            true
                                        )
                                    )
                                },
                            shape = RoundedCornerShape(5.dp),
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.AccessTime,
                                    contentDescription = "Task Date"
                                )
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = timeFormatter.format(viewModel.state.value.startTime),
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium
                    )
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(5.dp),
                        value = viewModel.state.value.description,
                        onValueChange = { viewModel.onEvent(TaskEvent.DescriptionChanged(it)) },
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
                }
                if (viewModel.state.value.showDatePicker) {
                    CustomDatePickerDialog(
                        initialDate = LocalDate.from(viewModel.state.value.startDate),
                        onDateSelected = {
                            viewModel.onEvent(TaskEvent.StartDateChanged(it))
                        },
                        onDismiss = {
                            viewModel.onEvent(TaskEvent.ToggleDatePickerDialog(false))
                        }
                    )
                }
                if (viewModel.state.value.showTimePicker) {
                    CustomTimePickerDialog(
                        initialTime = LocalTime.from(viewModel.state.value.startTime),
                        onTimeSelected = {
                            viewModel.onEvent(TaskEvent.StartTimeChanged(it))
                            Log.d("myTag", it.toString())
                        },
                        onDismiss = {
                            viewModel.onEvent(TaskEvent.ToggleTimePickerDialog(false))
                        }
                    )
                }
            },
        )
    }
}
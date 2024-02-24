package com.teneshvignesan.odot.presentation.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teneshvignesan.odot.presentation.task.widget.CategoryRadioGroup
import com.teneshvignesan.odot.presentation.widget.CustomDatePickerDialog
import com.teneshvignesan.odot.presentation.widget.CustomTimePickerDialog
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    navigateToHomeScreen: () -> Unit,
    viewModel: TaskViewModel = hiltViewModel()
) {

    val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm a")
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.uiEventFlow.collectLatest { event ->
            when(event) {
                is TaskUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is TaskUiEvent.SaveTask -> {
                    navigateToHomeScreen()
                }
                is TaskUiEvent.DeleteTask -> {
                    navigateToHomeScreen()
                }
            }
        }
    }


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
                        onClick = { navigateToHomeScreen() }
                    ) {
                        Icon(
                            modifier = Modifier.width(20.dp),
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    if(viewModel.state.value.currentId !== null) {
                        IconButton(
                            onClick = { viewModel.onEvent(TaskEvent.DeleteTask) }
                        ) {
                            Icon(
                                modifier = Modifier.width(20.dp),
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete task"
                            )
                        }
                    }
                }
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
                if (viewModel.state.value.categories.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
                        text = "Categories",
                        style = MaterialTheme.typography.titleMedium
                    )
                    CategoryRadioGroup(
                        categories = viewModel.state.value.categories,
                        defaultId = viewModel.state.value.selectedCategoryId,
                        onClick = {
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
                Button(
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { viewModel.onEvent(TaskEvent.SaveTask) }
                ) {
                    if(viewModel.state.value.currentId === null) {
                        Text(text = "Create")
                    } else {
                        Text(text = "Update")
                    }
                }
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
                    },
                    onDismiss = {
                        viewModel.onEvent(TaskEvent.ToggleTimePickerDialog(false))
                    }
                )
            }
        },
    )
}
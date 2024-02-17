package com.teneshvignesan.odot.presentation.home.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teneshvignesan.odot.domain.model.Task
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun TaskItem(
    task: Task,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        val startTimeInString = task.startDateTime.format(DateTimeFormatter.ofPattern("HH:mm a"))
        val endTimeInString = task.endDateTime.format(DateTimeFormatter.ofPattern("HH:mm a"))
        val durationInString = "$startTimeInString - $endTimeInString"

        Text(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 15.dp, top = 15.dp),
            text = task.title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
        )
        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .width(18.dp),
                imageVector = Icons.Filled.AccessTime,
                contentDescription = "Task duration"
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 4.dp),
                text = durationInString.replace("am", "AM").replace("pm", "PM"),
                style = MaterialTheme.typography.bodySmall,

                )
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun PreviewTaskItem() {
    TaskItem(
        task = Task(
            title = "Start the day with workout routine",
            description = "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before the final copy is available",
            startDateTime = LocalDateTime.now(),
            endDateTime = LocalDateTime.now()
        )
    )
}
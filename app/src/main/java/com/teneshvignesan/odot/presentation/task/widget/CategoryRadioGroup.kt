package com.teneshvignesan.odot.presentation.task.widget

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teneshvignesan.odot.domain.model.Category

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryRadioGroup(
    categories: List<Category>,
    defaultId: Int? = null,
    onClick: (Int) -> Unit
) {

    var currentId by remember { mutableStateOf(defaultId) }

    FlowRow {
        for (category in categories) {
            if(category.id === currentId) {
                Button(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    onClick = {}
                ) {
                    Text(text = category.title)
                }
            }

            if(category.id !== currentId) {
                OutlinedButton(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    onClick = {
                        currentId = category.id
                        currentId?.let { onClick(it) }
                    }
                ) {
                    Text(text = category.title)
                }
            }
        }
    }
}
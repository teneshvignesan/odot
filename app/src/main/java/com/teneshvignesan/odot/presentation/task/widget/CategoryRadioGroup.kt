package com.teneshvignesan.odot.presentation.task.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.teneshvignesan.odot.domain.model.Category


@Composable
fun CategoryRadioGroup(
    categories: List<Category>,
    selectedCategoryId: Int? = null,
    onSelectedCategory: () -> Unit
) {
}

@Preview(
    showSystemUi = true
)
@Composable
fun PreviewCategoryItem() {
    CategoryRadioGroup(
        categories = emptyList(),
        onSelectedCategory = {}
    )
}
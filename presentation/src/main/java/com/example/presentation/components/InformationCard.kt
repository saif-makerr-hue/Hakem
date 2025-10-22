package com.example.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun InformationCard(
    modifier: Modifier = Modifier,
    information: String,
    labelText: String,
    onTextChange: (String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp,
            ),
        shape = RoundedCornerShape(16.dp)
    ) {
        OutlinedTextField(
            value = information,
            onValueChange = onTextChange,
            label = { Text(text = labelText) }
        )
    }
}

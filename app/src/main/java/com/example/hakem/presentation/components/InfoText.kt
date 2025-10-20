package com.example.hakem.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Composable
fun InfoText(
    name: String,
    modifier: Modifier = Modifier,
    size: TextUnit = TextUnit.Unspecified,
    style: TextStyle = TextStyle(),

) {
    Text(
        text = name,
        fontSize = size,
        color = MaterialTheme.colorScheme.secondary,
        textAlign = TextAlign.Start,
        modifier = modifier,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = style
    )
}
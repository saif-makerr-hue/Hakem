package com.example.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun InfoTag(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.bodySmall,
    fontSize: TextUnit = TextUnit.Unspecified,

) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 1.dp,
        modifier = modifier
            .padding(1.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(4.dp),
            style = style,
            textAlign = TextAlign.Start,
            fontSize = fontSize
            )
    }
}

@Preview
@Composable
fun PreviewInfoTag(){
    InfoTag(text = "Hello")
}
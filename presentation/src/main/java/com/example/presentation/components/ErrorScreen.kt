package com.example.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorText: String,
    onClick: () -> Unit,

) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorText,
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = onClick) {
            Text(stringResource(R.string.retry))
        }
    }
}
@Preview
@Composable
fun PreviewErrorScreen(){
    ErrorScreen(
        errorText = "Error",
        onClick = {}
    )
}
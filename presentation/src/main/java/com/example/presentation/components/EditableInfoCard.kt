package com.example.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EditableInfoCard(
    modifier: Modifier = Modifier,
    information: String,
    labelText: String,
    onTextChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    ) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp,
            ),
        value = information,
        onValueChange = onTextChange,
        label = { Text(text = labelText) },
        keyboardOptions = keyboardOptions,
        shape = RoundedCornerShape(16.dp)
    )
}
@Preview
@Composable
fun PreviewEditableInfoCard(){
     EditableInfoCard(
         information = "Hello",
         labelText = "Enter Information",
         onTextChange = {},
     )
}
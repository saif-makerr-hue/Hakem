package com.example.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun PatientCard(
    modifier: Modifier = Modifier,
    firstName: String,
    lastName: String,
    id: String,
    comments: String,
    painter: Painter,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Image(
            painter = painter,
            contentDescription = stringResource(R.string.profile_image),
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .aspectRatio(1f)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape),
            alignment = Alignment.Center
        )
        SpaceWidth(8.dp)
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable { onClick() }) {
            InfoText("First Name : $firstName", style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(4.dp))
            SurfaceText("Last Name : $lastName")
            SurfaceText("Comments : $comments")
            SurfaceText("Id : $id")
        }
        SpaceWidth(16.dp)
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }

    }
}
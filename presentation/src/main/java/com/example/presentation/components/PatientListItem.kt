package com.example.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R

@Composable
fun PatientListItem(
    modifier: Modifier = Modifier,
    firstName: String,
    lastName: String,
    id: String,
    comments: String,
    Dob:String,
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
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape),
            alignment = Alignment.Center
        )
        Spacer(modifier = modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable { onClick() }) {
            InfoTag(
                text = stringResource(R.string.first_name, firstName),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            InfoTag(
                text = stringResource(R.string.last_name, lastName),
            )
            InfoTag(text = stringResource(R.string.comments, comments))
            InfoTag(text = stringResource(R.string.dob, Dob) )
            InfoTag(text = stringResource(R.string.id, id))
        }
        Spacer(modifier = modifier.width(16.dp))
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete)
            )
        }
    }
}

@Preview
@Composable
fun PreviewPatientListItem() {
    PatientListItem(
        firstName = "FirstName",
        lastName = "LastName",
        id = "Id",
        comments = "Comments",
        Dob = "dob",
        painter = painterResource(R.drawable.place_holder),
        onClick = {},
        onDelete = {}
    )
}
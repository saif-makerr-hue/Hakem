package com.example.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.domain.model.PatientsItem
import com.example.presentation.R


@Composable
fun EditPatientForm(
    modifier: Modifier = Modifier,
    patient: PatientsItem,
    onSaveInfo: () -> Unit,
    onChangeFirstName: (String) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeComments: (String) -> Unit,
    onChangeDob: (Long) -> Unit,
    onChangeAvatar: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            InfoTag(
                text = "Update Patients Info",
                fontSize = 18.sp
            )
        }
        item {
            Image(
                painter = rememberAsyncImagePainter(
                    model = patient.avatar,
                    placeholder = painterResource(R.drawable.place_holder),
                    error = painterResource(R.drawable.place_holder)
                ),
                contentDescription = "Patient Avatar",
                modifier = modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
        item {
            EditableInfoCard(
                information = patient.avatar,
                onTextChange = { onChangeAvatar(it) },
                labelText = "Image Url"
            )
        }
        item {

            EditableInfoCard(
                information = patient.firstName,
                onTextChange = { onChangeFirstName(it) },
                labelText = "First Name"
            )
        }
        item {
            EditableInfoCard(
                information = patient.lastName,
                onTextChange = { onChangeLastName(it) },
                labelText = "Last Name"
            )
        }
        item {
            EditableInfoCard(
                information = patient.comments,
                onTextChange = { onChangeComments(it) },
                labelText = "Comments"
            )
        }
        item {
            EditableInfoCard(
                information = patient.dob.toString(),
                onTextChange = { newText ->
                    newText.toLongOrNull()?.let(onChangeDob)
                },
                labelText = "Dob",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }

        item {
            Button(
                onClick = onSaveInfo,
                modifier = modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
            ) { Text(text = "Save", color = Color.White) }
        }

    }
}

@Preview
@Composable
fun PreviewEditPatientForm(){
    EditPatientForm(
        patient = PatientsItem(),
        onSaveInfo = {},
        onChangeFirstName = {},
        onChangeLastName = {},
        onChangeComments = {},
        onChangeDob = {},
    ) { }
}
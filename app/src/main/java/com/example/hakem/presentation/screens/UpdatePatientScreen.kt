package com.example.hakem.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.hakem.core.state.PatientUiState
import com.example.hakem.domain.model.PatientsItem
import com.example.hakem.presentation.components.InfoText
import com.example.hakem.presentation.components.InformationCard
import com.example.hakem.presentation.components.Space
import com.example.hakem.presentation.viewModels.UpdateViewModel

@Composable
fun UpdatePatientScreen(
    viewModel: UpdateViewModel = hiltViewModel(),
    onClickHome: () -> Unit
) {
    val uiState by viewModel.patientState.collectAsState()

    when (uiState) {
        is PatientUiState.Error -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    "ُError : ${(uiState as? PatientUiState.Error)?.message} ",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        PatientUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(40.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            }
        }

        is PatientUiState.Success -> {
            val patient = (uiState as PatientUiState.Success).data
            UpdatePatientContent(
                patient = patient,
                onSave = {
                    viewModel.savePatientInformation()
                    onClickHome()
                },
                onChangeFirstName = viewModel::onChangeFirstName,
                onChangeLastName = viewModel::onChangeLastName,
                onChangeComments = viewModel::onChangeComments,
                onChangeDob = viewModel::onChangeDob,
                onChangeAvatar = viewModel::onChangeAvatar
            )
        }
    }
}


@Composable
fun UpdatePatientContent(
    patient: PatientsItem,
    modifier: Modifier = Modifier,
    onSave: () -> Unit,
    onChangeFirstName: (String) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeComments: (String) -> Unit,
    onChangeDob: (Long) -> Unit,
    onChangeAvatar: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                InfoText(name = "Update Patients Info", size = 18.sp)
                InfoText(name = "ID :${patient.id} ", size = 14.sp)
            }
            Space(24.dp)
            Image(
                painter = rememberAsyncImagePainter(patient.avatar),
                contentDescription = "Patient Avatar",
                modifier = modifier
                    .size(160.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Space(24.dp)
            InformationCard(
                information = patient.avatar,
                onTextChange = { onChangeAvatar(it) },
                labelText = "Image Url"
            )
            Space(24.dp)
            InformationCard(
                information = patient.firstName,
                onTextChange = { onChangeFirstName(it) },
                labelText = "First Name"
            )
            Space(24.dp)
            InformationCard(
                information = patient.lastName,
                onTextChange = { onChangeLastName(it) },
                labelText = "Last Name"
            )
            Space(24.dp)
            InformationCard(
                information = patient.comments,
                onTextChange = { onChangeComments(it) },
                labelText = "Comments"
            )
            Space(24.dp)
            InformationCard(
                information = patient.dob.toString(),
                onTextChange = { newText ->
                    newText.toLongOrNull()?.let(onChangeDob)
                },
                labelText = "Dob"
            )
            Space(24.dp)
            Button(
                onClick = onSave,
                modifier = modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
            ) { Text(text = "Save", color = Color.White) }
        }
    }
}
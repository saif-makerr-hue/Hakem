package com.example.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.core.state.PatientUiState
import com.example.domain.model.PatientsItem
import com.example.presentation.components.EditPatientForm
import com.example.presentation.components.ErrorScreen
import com.example.presentation.components.LoadingScreen
import com.example.presentation.viewModels.UpdateViewModel

@Composable
fun UpdatePatientScreen(
    viewModel: UpdateViewModel = hiltViewModel(),
    onClickHome: () -> Unit,
) {
    val state by viewModel.patientState.collectAsState()

    when (val uiState = state) {
        is PatientUiState.Error -> {
            ErrorScreen(
                errorText = "ُError : ${(uiState as? PatientUiState.Error)?.message} ",
                onClick = onClickHome,
            )
        }

        PatientUiState.Loading -> {
            LoadingScreen()
        }

        is PatientUiState.Success -> {
            UpdatePatientContent(
                patient = uiState.data,
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
    modifier: Modifier = Modifier,
    patient: PatientsItem,
    onSave: () -> Unit,
    onChangeFirstName: (String) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeComments: (String) -> Unit,
    onChangeDob: (Long) -> Unit,
    onChangeAvatar: (String) -> Unit
) {
    EditPatientForm(
        modifier = modifier ,
        patient = patient,
        onSaveInfo = onSave,
        onChangeFirstName = onChangeFirstName,
        onChangeLastName = onChangeLastName,
        onChangeComments = onChangeComments,
        onChangeDob = onChangeDob,
        onChangeAvatar = onChangeAvatar
    )
}

@Preview
@Composable
fun PreviewUpdatePatientScreen() {
    UpdatePatientContent(
        patient = PatientsItem(),
        onSave = {},
        onChangeFirstName = {},
        onChangeLastName = {},
        onChangeComments = {},
        onChangeDob = {},
        onChangeAvatar = {},
    )
}
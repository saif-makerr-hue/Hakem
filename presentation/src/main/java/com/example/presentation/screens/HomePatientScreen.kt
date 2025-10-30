package com.example.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.core.state.PatientUiState
import com.example.domain.model.PatientsItem
import com.example.presentation.components.ErrorScreen
import com.example.presentation.components.LoadingScreen
import com.example.presentation.components.PatientListItem
import com.example.presentation.viewModels.PatientViewModel

@Composable
fun HomePatientScreen(
    viewModel: PatientViewModel = hiltViewModel(),
    onClickToAdd: () -> Unit,
    onClickToUpdate: (String) -> Unit
) {
    val state by viewModel.patientStateList.collectAsState()

    when (val uiStateList = state) {
        is PatientUiState.Error -> {
            ErrorScreen(
                onClick = { viewModel.getPatientInfoList() },
                errorText = "Error : ${(uiStateList as? PatientUiState.Error)?.message}"
            )
        }

        PatientUiState.Loading -> {
            LoadingScreen()
        }

        is PatientUiState.Success -> {
            HomePatientContent(
                patientList = uiStateList .data,
                onRefresh = { viewModel.getPatientInfoList() },
                isRefreshing = false,
                onClickToAdd = onClickToAdd,
                onClickToUpdate = onClickToUpdate,
                onDelete = viewModel::deletePatient,
            )
        }
    }
}

@Composable
fun HomePatientContent(
    modifier: Modifier = Modifier,
    patientList: List<PatientsItem>,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    onClickToAdd: () -> Unit,
    onClickToUpdate: (String) -> Unit,
    onDelete: (String) -> Unit,
) {
    val pullToRefreshState = rememberPullToRefreshState()
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = Modifier.statusBarsPadding(),
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                state = pullToRefreshState
            )
        },
        content = {
            Box(
                modifier = modifier.fillMaxSize()
            ) {
                AnimatedVisibility(visible = patientList.isEmpty()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    content = {
                        items(patientList) {
                            PatientListItem(
                                modifier = Modifier.padding(4.dp),
                                firstName = it.firstName,
                                lastName = it.lastName,
                                id = it.id,
                                comments = it.comments,
                                Dob = it.dob.toString(),
                                painter = rememberAsyncImagePainter(model = it.avatar),
                                onClick = { onClickToUpdate(it.id) },
                                onDelete = { onDelete(it.id) }
                            )
                        }
                    }
                )
                FloatingActionButton(
                    onClick = onClickToAdd,
                    modifier = Modifier
                        .align(alignment = Alignment.BottomEnd)
                        .padding(vertical = 16.dp, horizontal = 12.dp),
                    containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewHomePatientContent() {
    val samplePatients = List(3) { index ->
        PatientsItem(
            id = "P$index",
            firstName = "John",
            lastName = "Doe $index",
            comments = "Sample patient record $index",
            dob = 19900L + index,
            avatar = ""
        )
    }
    HomePatientContent(
        patientList = samplePatients,
        onRefresh = {},
        isRefreshing = false,
        onClickToAdd = {},
        onClickToUpdate = {},
        onDelete = {}
    )
}
package com.example.hakem.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.hakem.core.state.PatientUiState
import com.example.hakem.domain.model.PatientsItem
import com.example.hakem.presentation.components.PatientCard
import com.example.hakem.presentation.viewModels.PatientViewModel

@Composable
fun HomePatientScreen(
    modifier: Modifier = Modifier,
    viewModel: PatientViewModel = hiltViewModel(),
    onClickToAdd: () -> Unit,
    onClickToUpdate: (String) -> Unit
) {
    val uiStateList by viewModel.patientStateList.collectAsState()

    when (uiStateList) {
        is PatientUiState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Error : ${(uiStateList as? PatientUiState.Error)?.message}",
                    modifier = Modifier.padding(16.dp)
                )
                Button(onClick = { viewModel.getPatientInfoList() }) {
                    Text("Retry")
                }
            }
        }

        PatientUiState.Loading -> {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                CircularProgressIndicator(
                    modifier = Modifier.size(40.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            }
        }

        is PatientUiState.Success -> {
            HomePatientContent(
                patientList = (uiStateList as PatientUiState.Success<List<PatientsItem>>).data,
                onRefresh = { viewModel.getPatientInfoList() },
                isRefreshing = false,
                onClickToAdd = onClickToAdd,
                onClickToUpdate = onClickToUpdate,
                onDelete = viewModel::deletePatient
            )
        }
    }
}

@Composable
fun HomePatientContent(
    patientList: List<PatientsItem>,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    onClickToAdd: () -> Unit,
    onClickToUpdate: (String) -> Unit,
    onDelete: (String) -> Unit
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
                modifier = Modifier.fillMaxSize()
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
                            PatientCard(
                                modifier = Modifier.padding(4.dp),
                                firstName = it.firstName,
                                lastName = it.lastName,
                                id = it.id,
                                comments = it.comments,
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
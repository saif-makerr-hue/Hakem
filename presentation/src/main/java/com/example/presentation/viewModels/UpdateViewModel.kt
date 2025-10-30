package com.example.presentation.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.core.state.PatientUiState
import com.example.data.datasource.repository.PatientRepositoryImp
import com.example.domain.model.PatientsItem
import com.example.presentation.navigator.UpdatePatient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val repository: PatientRepositoryImp,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _patientState = MutableStateFlow<PatientState>(PatientUiState.Loading)
    val patientState = _patientState.asStateFlow()


    private val args = savedStateHandle.toRoute<UpdatePatient>()

    init {
        if (args.id.isNotEmpty()) {
            getPatientInfoById(args.id)
        } else {
            _patientState.update { PatientUiState.Success(PatientsItem()) }
        }
    }

    private fun getPatientInfoById(id: String) {
        handelUiState { repository.getPatientInfo(id) }
    }

    private fun updatePatientInfo(patientsItem: PatientsItem, id: String) {
        handelUiState { repository.updatePatientInfo(patientsItem, id) }
    }

    private fun addPatientInfo(patientsItem: PatientsItem) {
        handelUiState { repository.addPatientInfo(patientsItem) }
    }

    private fun handelUiState(function: suspend () -> PatientsItem) {
        viewModelScope.launch {
            try {
                _patientState.update { PatientUiState.Loading }
                val patient = function()
                _patientState.update { PatientUiState.Success(patient) }
            } catch (e: Exception) {
                _patientState.update { PatientUiState.Error(e.message.toString()) }
            }
        }
    }


    private fun changePatientInfo(change: (PatientsItem) -> PatientsItem) {
        _patientState.update { current ->
            when (current) {
                is PatientUiState.Success -> {
                    PatientUiState.Success(change(current.data))
                }

                else -> current
            }
        }
    }

    fun onChangeFirstName(firstName: String) {
        changePatientInfo { it.copy(firstName = firstName) }
    }

    fun onChangeLastName(lastName: String) {
        changePatientInfo { it.copy(lastName = lastName) }
    }

    fun onChangeComments(comments: String) {
        changePatientInfo { it.copy(comments = comments) }
    }

    fun onChangeDob(dob: Long) {
        changePatientInfo { it.copy(dob = dob) }
    }

    fun onChangeAvatar(avatar: String) {
        changePatientInfo { it.copy(avatar = avatar) }
    }


    fun savePatientInformation() {
        val current = (_patientState.value as? PatientUiState.Success)?.data ?: return
        if (args.id.isNotEmpty()) {
            updatePatientInfo(current, args.id)
        } else {
            addPatientInfo(current)
        }
    }
}

typealias PatientState = PatientUiState<PatientsItem>
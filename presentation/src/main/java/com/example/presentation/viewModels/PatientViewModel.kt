package com.example.presentation.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.state.PatientUiState
import com.example.data.datasource.repository_imp.PatientRepositoryImp
import com.example.domain.model.PatientsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(
    private val repository: PatientRepositoryImp,
) : ViewModel() {

    private val _patientStateList = MutableStateFlow<PatientsStateList>(PatientUiState.Loading)
    val patientStateList = _patientStateList.asStateFlow()

    init {
        getPatientInfoList()
    }
    fun getPatientInfoList() {
        viewModelScope.launch {
            try {
                _patientStateList.update { PatientUiState.Loading }
                val listFoodMenu = repository.getPatientsInfoList()
                _patientStateList.update { PatientUiState.Success(listFoodMenu) }
            } catch (e: Exception) {
                _patientStateList.update { PatientUiState.Error(e.message.toString()) }
            }
        }
    }

    fun deletePatient(id: String) {

        viewModelScope.launch {
            try {
                repository.deletePatientInfo(id = id)
                getPatientInfoList()
            } catch (e: Exception) {
                _patientStateList.update { PatientUiState.Error(e.message.toString()) }
            }

        }
    }
}
typealias PatientsStateList = PatientUiState<List<PatientsItem>>


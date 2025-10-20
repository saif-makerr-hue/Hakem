package com.example.hakem.core.state

sealed class PatientUiState<out T> {
    data class Success<T>(val data: T) : PatientUiState<T>()
    data class Error(val message: String) : PatientUiState<Nothing>()
    data object Loading : PatientUiState<Nothing>()
}
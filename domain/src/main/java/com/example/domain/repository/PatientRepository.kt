package com.example.domain.repository

import com.example.domain.model.PatientsItem


interface PatientRepository {

    suspend fun getPatientsInfoList(): List<PatientsItem>

    suspend fun getPatientInfo(id: String): PatientsItem

    suspend fun addPatientInfo(patient: PatientsItem): PatientsItem

    suspend fun updatePatientInfo(patient: PatientsItem, id: String): PatientsItem

    suspend fun deletePatientInfo(id: String)
}
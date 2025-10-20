package com.example.hakem.domain.repository

import com.example.hakem.domain.model.PatientsItem


interface PatientRepository {

    suspend fun getPatientsInfoList(): List<PatientsItem>

    suspend fun getPatientInfo(id: String): PatientsItem

    suspend fun addPatientInfo(patient: PatientsItem): PatientsItem

    suspend fun updatePatientInfo(patient: PatientsItem, id: String): PatientsItem

    suspend fun deletePatientInfo(id: String)
}
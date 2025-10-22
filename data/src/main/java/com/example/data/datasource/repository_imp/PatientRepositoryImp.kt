package com.example.data.datasource.repository_imp

import com.example.data.datasource.api.PatientService
import com.example.domain.model.PatientsItem
import com.example.domain.repository.PatientRepository
import javax.inject.Inject

class PatientRepositoryImp @Inject constructor(
    private val patientService: PatientService
) : PatientRepository {

    override suspend fun getPatientsInfoList(): List<PatientsItem> {
        return patientService.getPatientsInfoList()
    }

    override suspend fun getPatientInfo(id: String): PatientsItem {
        return patientService.getPatientInfo(id = id)
    }

    override suspend fun addPatientInfo(patient: PatientsItem): PatientsItem {
        return patientService.addPatientInfo(patient = patient)
    }

    override suspend fun updatePatientInfo(patient: PatientsItem, id: String): PatientsItem {
        return patientService.updatePatientInfo(patient = patient, id = id)
    }

    override suspend fun deletePatientInfo(id: String) {
        return patientService.deletePatientInfo(id = id)
    }

}
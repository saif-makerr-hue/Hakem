package com.example.data.datasource.api

import com.example.domain.model.PatientsItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PatientService {

    @GET(API_GET)
    suspend fun getPatientsInfoList(): List<PatientsItem>

    @GET(API_GET_ID)
    suspend fun getPatientInfo(@Path("id") id: String): PatientsItem

    @POST(API_GET)
    suspend fun addPatientInfo(@Body patient: PatientsItem): PatientsItem

    @PUT(API_GET_ID)
    suspend fun updatePatientInfo(@Body patient: PatientsItem,@Path("id") id: String): PatientsItem

    @DELETE(API_GET_ID)
    suspend fun deletePatientInfo(@Path("id") id: String)
}

private const val API_GET="api/v1/Patient"
private const val API_GET_ID = "api/v1/Patient/{id}"
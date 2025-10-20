package com.example.hakem.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PatientsItem(
    val firstName: String = "",
    val dob: Long = 0,
    val avatar: String = "",
    val lastName: String = "",
    val comments: String = "",
    val id: String= ""
)

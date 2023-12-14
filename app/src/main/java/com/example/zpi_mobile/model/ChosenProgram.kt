package com.example.zpi_mobile.model

import kotlinx.serialization.Serializable

@Serializable
data class ChosenProgram(
    val field_name: String,
    val education_level: String,
    val is_full_time: Boolean,
    val is_general_academic: Boolean,
    val language: String,
    val inPolish: Boolean
)
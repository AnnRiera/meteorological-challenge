package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Logs(
    val errorMessage: String,
    val timestamp: Long
)

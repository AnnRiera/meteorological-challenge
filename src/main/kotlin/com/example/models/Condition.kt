package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)

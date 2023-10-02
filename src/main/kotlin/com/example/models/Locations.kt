package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Locations(
    val location: Location,
    val current: Current
)

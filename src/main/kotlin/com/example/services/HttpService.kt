package com.example.services

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

object HttpService {
    fun init(): HttpClient {
        val client =  HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
        return client
    }
}
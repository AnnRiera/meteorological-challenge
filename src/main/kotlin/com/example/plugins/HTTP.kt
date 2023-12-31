package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.plugins.openapi.*

fun Application.configureHTTP() {
    routing {
        openAPI(path = "openapi")
    }
}

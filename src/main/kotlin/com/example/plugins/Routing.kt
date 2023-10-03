package com.example.plugins

import com.example.routes.locationsRoute
import io.ktor.server.application.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        openAPI(path="openapi", swaggerFile = "openapi/documentation.yaml")
        locationsRoute()
    }
}
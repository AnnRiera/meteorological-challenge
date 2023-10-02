package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import com.example.routes.locationsRoute

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        locationsRoute()
    }
}

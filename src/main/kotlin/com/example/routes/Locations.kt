package com.example.routes

import com.example.services.LocationService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.locationsRoute() {
    route("/location") {
        get("/all") {
            call.respond(LocationService.fetchLocations())
        }

        get("/{name}") {
            if (call.parameters["name"].isNullOrEmpty()) {
                throw Error("El nombre es obligatorio")
            }

            val name = call.parameters["name"]!!
            call.respond(LocationService.getLocation(name))
        }
    }
}
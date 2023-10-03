package com.example.services

import com.example.enums.LocationsEnum
import com.example.models.Locations
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

object LocationService {
    suspend fun fetchLocations(): MutableList<Locations> {
        val client = HttpService.init()

        val locations = arrayOf<Locations>()
        val results = locations.toMutableList()
        try {
            for (location in LocationsEnum.entries) {
                results.add(client.get("https://api.weatherapi.com/v1/current.json?key=a9c6ddd80a2d4734b82142524233009&q=$location").body())
            }

            RedisService.insert(results)

        } catch (e: Exception) {
            println(e)
            throw Exception(e.message)
        }
        return results
    }

    fun getLocation(name: String): JsonElement {
        try {
            return Json.decodeFromString(RedisService.get(name))
        } catch (e: Exception) {
            println(e)
            throw Exception(e.message)
        }
    }

    //fun getAllLocations() : MutableList<Locations> {
        //val locations = arrayOf<Locations>()
        //val results = locations.toMutableList()
        //for (location in LocationsEnum.entries) {
            //results.add(RedisService.get("$location"))
        //}
    //}
}
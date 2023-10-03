package com.example.services

import com.example.models.Locations
import com.example.models.Logs
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant

object CronjobService {
     fun job() {
         runBlocking {
             launch {
                 flow<MutableList<Locations>> {
                     val random = Math.random()
                     if (random < 0.2) {
                         throw Error ("The API Request Failed")
                     }

                     println("Starting the service for fetching locations...")
                     LocationService.fetchLocations()
                 }.catch {
                     print(it.message)
                     LoggerService.insertLog(Logs(it.message!!, Instant.now().toEpochMilli()))
                 }.retryWhen { cause, attempt ->
                     if (cause is Error && attempt < 1) {
                        return@retryWhen true
                     }
                     false
                 }.collectLatest {
                     print(it)
                 }
             }
         }
    }
}
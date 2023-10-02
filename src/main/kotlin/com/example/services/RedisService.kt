package com.example.services

import com.example.models.Locations
import com.redis.lettucemod.RedisModulesClient
import com.redis.lettucemod.api.StatefulRedisModulesConnection
import com.redis.lettucemod.api.sync.RedisJSONCommands
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object RedisService {
    private fun init(): StatefulRedisModulesConnection<String, String> {
        val redisClient: RedisModulesClient = RedisModulesClient.create("redis://localhost:6379")
        return redisClient.connect()
    }

    private fun execute(): RedisJSONCommands<String, String> {
        val connection = init()
        return connection.sync()
    }

    fun insert(locations: MutableList<Locations>) {
        val exec = execute()

        try {
            for (location in locations) {
                val jsonString = Json.encodeToString(location)
                exec.jsonSet(location.location.name, "$", jsonString)
            }

            init().close()
        } catch (e: Exception) {
            println(e)
            throw Error(e.message)
        }

    }

    fun get(name: String): String {
        try {
            val exec = execute()
            val response = exec.jsonGet(name)

            init().close()
            return response
        } catch (e: Exception) {
            println(e)
            throw Error(e.message)
        }
    }
}
package com.example.services

import com.example.models.Locations
import com.example.models.Logs
import com.redis.lettucemod.RedisModulesClient
import com.redis.lettucemod.api.StatefulRedisModulesConnection
import com.redis.lettucemod.api.sync.RedisModulesCommands
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object RedisService {
    private fun init(): StatefulRedisModulesConnection<String, String> {
        val redisClient: RedisModulesClient = RedisModulesClient.create("redis://localhost:6379")
        return redisClient.connect()
    }

    private fun execute(): RedisModulesCommands<String, String> {
        val connection = init()
        return connection.sync()
    }

    fun insert(locations: MutableList<Locations>) {
        try {
            val exec = execute()
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

    fun addLog(log: Logs) {
        val exec = execute()
        val jsonString = Json.encodeToString(log)
        exec.jsonSet("Log-${java.time.Instant.now().toEpochMilli()}", "$", jsonString)
        init().close()
    }
}
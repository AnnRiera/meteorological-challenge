package com.example

import com.example.plugins.configureHTTP
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureHTTP()
    configureRouting()

    // val ktorLogger: ch.qos.logback.classic.Logger =
        //LoggerFactory.getLogger("KTOR-WEB-APP") as ch.qos.logback.classic.Logger

    //val redisClient: RedisModulesClient = RedisModulesClient.create("redis://localhost:6379")
    //val redisConnection: StatefulRedisModulesConnection<String, String> = redisClient.connect()

    //val redisSyncCommand: RedisModulesCommands<String, String> = redisConnection.sync()
    //val redisCoroutineCommand = redisConnection.coroutines()
    //val redisReactiveCommand = redisConnection.reactive()

    // setup the search commands not included in libraries
    //val redisSearchCommands = RedisCommandFactory(redisConnection).getCommands(RedisSearchCommands::class.java)

    // allow one character strings for FT.SEARCH
    //redisSearchCommands.ftConfigSet("MINPREFIX", "1")

    // Build the JSON Text search indexes
    //try {
        // check if index exists
        //val result = redisSyncCommand.ftInfo("users_index")
    //} catch (e: Exception) {
        // setup json text search index
        //val result = redisSyncCommand.ftCreate(
            //"users_index",
            //CreateOptions.builder<String, String>()
                //.prefix("user:")
                //.on(CreateOptions.DataType.JSON)
                //.build(),
            //Field.tag("$.id")  // note: TAGs do not separate words/special characters
                //.`as`("id")
                //.build(),
            //Field.tag("$.email")
                //.`as`("email")
                //.build(),
            //Field.text("$.name")
                //.`as`("name")
                //.sortable()
                //.withSuffixTrie()  // for improved search (go -> going, goes, gone)
                //.build()
        //)

        //if (result != "OK") {
        //    ktorLogger.error("Error creating index: $result")
        //}
    //}
}

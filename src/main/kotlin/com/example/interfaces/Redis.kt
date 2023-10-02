package com.example.interfaces

import io.lettuce.core.dynamic.Commands
import io.lettuce.core.dynamic.annotation.Command
import io.lettuce.core.dynamic.annotation.CommandNaming

interface RedisSearchCommands : Commands {
    @Command("FT.CREATE")
    @CommandNaming(strategy = CommandNaming.Strategy.DOT)
    fun ftCreate(index: String, vararg args: String): String

    @Command("FT.CONFIG SET")
    @CommandNaming(strategy = CommandNaming.Strategy.DOT)
    fun ftConfigSet(key: String, value: String): String
}
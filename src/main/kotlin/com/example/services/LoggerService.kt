package com.example.services

import com.example.models.Logs

object LoggerService {
    fun insertLog(log: Logs) {
        RedisService.addLog(log)
    }
}
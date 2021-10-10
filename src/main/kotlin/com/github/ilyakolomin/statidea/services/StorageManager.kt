package com.github.ilyakolomin.statidea.services

import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter

object StorageManager {
    private const val fileName = "StatIDEA_stats.json"

    fun saveStatistics(statisticsKeeper: StatisticsKeeper) {
        val writer = FileWriter(fileName)
        writer.write(Json.encodeToString(StatisticsKeeper.serializer(), statisticsKeeper))
        writer.close()
    }

    fun restoreStatistics(): StatisticsKeeper {
        return try {
            Json.decodeFromString(StatisticsKeeper.serializer(), File(fileName).bufferedReader().readLine())
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            StatisticsKeeper()
        }
    }
}

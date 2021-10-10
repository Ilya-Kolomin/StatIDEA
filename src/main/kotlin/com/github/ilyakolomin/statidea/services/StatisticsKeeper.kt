package com.github.ilyakolomin.statidea.services

import kotlinx.serialization.Serializable
import java.time.LocalDate
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@Serializable
data class StatisticsKeeper(
    var countKeys: Int = 0,
    var countClicks: Int = 0,
    var countMoves: Int = 0,
    var pressedKeys: HashMap<String, Int> = HashMap(),
    var keysWithTime: ArrayList<DataWithDate<String>> = ArrayList(),
    var clicksWithTime: ArrayList<DataWithDate<Int>> = ArrayList()
) {

    fun keyPress(key: String) {
        countKeys++
        val value = pressedKeys.getOrDefault(key, 0) + 1
        pressedKeys[key] = value
        keysWithTime.add(DataWithDate(key))
    }

    fun mouseClick(click: Int) {
        countClicks++
        clicksWithTime.add(DataWithDate(click))
    }

    fun mouseMove() {
        countMoves++
    }

    @Serializable
    data class DataWithDate<T>(var data: T, var date: String = LocalDate.now().toString())
}

package com.github.ilyakolomin.statidea.services

import kotlinx.serialization.Serializable
import java.util.Date
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@Serializable
data class StatisticsKeeper(
    var countKeys: Int = 0, var countClicks: String = "0", var countMoves: Int = 0,
    var pressedKeys: HashMap<String, Int> = HashMap(),
    var keysWithTime: ArrayList<DataWithTime<String>> = ArrayList(),
    var clicksWithTime: ArrayList<DataWithTime<Int>> = ArrayList()
) {

    fun keyPress(key: String) {
        countKeys++
        val value = pressedKeys.getOrDefault(key, 0) + 1
        pressedKeys[key] = value
        keysWithTime.add(DataWithTime(key))
    }

    fun mouseClick(click: Int) {
        countClicks = (countClicks.toInt() + 1).toString()
        clicksWithTime.add(DataWithTime(click))
    }

    fun mouseMove() {
        countMoves++
    }

    @Serializable
    data class DataWithTime<T>(var data: T, var date: String = Date().toString())
}

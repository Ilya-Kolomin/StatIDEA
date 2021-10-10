package com.github.ilyakolomin.statidea.ui

import com.github.ilyakolomin.statidea.services.StatisticsKeeper
import javax.swing.JLabel
import javax.swing.JPanel

class StatisticsWindow(statisticsKeeper: StatisticsKeeper) {
    private var countKeys: JLabel? = null
    private var countClicks: JLabel? = null
    private var countMoves: JLabel? = null
    var statisticsWindowContent: JPanel? = null

    init {
        setCountKeysText("Count of typed symbols: " + statisticsKeeper.countKeys)
        setCountClicksText("Count of mouse clicks: " + statisticsKeeper.countClicks)
        setCountMovesText("Count of mouse moves: " + statisticsKeeper.countMoves)
    }

    fun setCountKeysText(text: String?) {
        countKeys!!.text = text
    }

    fun setCountClicksText(text: String?) {
        countClicks!!.text = text
    }

    fun setCountMovesText(text: String?) {
        countMoves!!.text = text
    }
}

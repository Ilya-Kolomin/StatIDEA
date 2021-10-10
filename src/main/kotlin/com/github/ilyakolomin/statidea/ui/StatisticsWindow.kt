package com.github.ilyakolomin.statidea.ui

import com.github.ilyakolomin.statidea.services.StatisticsKeeper
import java.time.LocalDate
import java.time.Period
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTable
import javax.swing.table.AbstractTableModel

class StatisticsWindow(statisticsKeeper: StatisticsKeeper) {
    private var countKeys: JLabel? = null
    private var countClicks: JLabel? = null
    private var countMoves: JLabel? = null
    private var daysTable: JTable? = null
    var statisticsWindowContent: JPanel? = null
    val countKeysAtDay: ArrayList<Int> = ArrayList(7)

    init {
        setCountKeysText("Count of typed symbols: " + statisticsKeeper.countKeys)
        setCountClicksText("Count of mouse clicks: " + statisticsKeeper.countClicks)
        setCountMovesText("Count of mouse moves: " + statisticsKeeper.countMoves)

        daysTable!!.model = object : AbstractTableModel() {

            init {
                val currentDate = LocalDate.now()
                for (i in 0..6) {
                    countKeysAtDay.add(0)
                }
                for (keyWithTime in statisticsKeeper.keysWithTime) {
                    val daysAgo = Period.between(LocalDate.parse(keyWithTime.date), currentDate).days
                    if (daysAgo < 7) {
                        countKeysAtDay[6 - daysAgo]++
                    }
                }
            }

            override fun getRowCount(): Int {
                return 2
            }

            override fun getColumnCount(): Int {
                return 7
            }

            override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
                return if (rowIndex == 0) {
                    (6 - columnIndex).toString() + " days ago"
                } else {
                    countKeysAtDay[columnIndex]
                }
            }

        }
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

    fun updateCountLastDay() {
        countKeysAtDay[6]++
        daysTable!!.updateUI()
    }

}

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
    val daysAtWeek = 7
    val countKeysAtDay: ArrayList<Int> = ArrayList(daysAtWeek)

    init {
        setCountKeysText("Count of typed symbols: " + statisticsKeeper.countKeys)
        setCountClicksText("Count of mouse clicks: " + statisticsKeeper.countClicks)
        setCountMovesText("Count of mouse moves: " + statisticsKeeper.countMoves)

        daysTable!!.model = object : AbstractTableModel() {

            val numberOfRows = 2

            init {
                val currentDate = LocalDate.now()
                for (i in 0 until daysAtWeek) {
                    countKeysAtDay.add(0)
                    countKeysAtDay[i] = 0
                }
                for (keyWithTime in statisticsKeeper.keysWithTime) {
                    val daysAgo = Period.between(LocalDate.parse(keyWithTime.date), currentDate).days
                    if (daysAgo < daysAtWeek) {
                        countKeysAtDay[daysAtWeek - 1 - daysAgo]++
                    }
                }
            }

            override fun getRowCount(): Int {
                return numberOfRows
            }

            override fun getColumnCount(): Int {
                return daysAtWeek
            }

            override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
                return if (rowIndex == 0) {
                    (daysAtWeek - 1 - columnIndex).toString() + " days ago"
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
        countKeysAtDay[daysAtWeek - 1]++
        daysTable!!.updateUI()
    }
}

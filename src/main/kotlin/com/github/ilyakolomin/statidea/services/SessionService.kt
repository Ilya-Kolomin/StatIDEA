package com.github.ilyakolomin.statidea.services

import com.github.ilyakolomin.statidea.ui.StatisticsWindow
import org.jnativehook.GlobalScreen
import org.jnativehook.keyboard.NativeKeyEvent
import org.jnativehook.keyboard.NativeKeyListener
import org.jnativehook.mouse.NativeMouseEvent
import org.jnativehook.mouse.NativeMouseListener
import org.jnativehook.mouse.NativeMouseMotionListener

object SessionService {

    val statisticsKeeper: StatisticsKeeper = StorageManager.restoreStatistics()
    var statisticsWindow: StatisticsWindow = StatisticsWindow(statisticsKeeper)

    init {

        GlobalScreen.registerNativeHook()
        GlobalScreen.addNativeKeyListener(EventHandlerKeyboard)
        GlobalScreen.addNativeMouseListener(EventHandlerMouse)
        GlobalScreen.addNativeMouseMotionListener(EventHandlerMouseMotion)
    }

    object EventHandlerKeyboard : NativeKeyListener {
        override fun nativeKeyPressed(e: NativeKeyEvent) {
            val key = NativeKeyEvent.getKeyText(e.keyCode)
            statisticsKeeper.keyPress(key)
            statisticsWindow.setCountKeysText("Count of typed symbols: " + statisticsKeeper.countKeys)
            statisticsWindow.updateCountLastDay()
        }

        override fun nativeKeyReleased(p0: NativeKeyEvent?) {
            TODO("Not yet implemented")
        }

        override fun nativeKeyTyped(p0: NativeKeyEvent?) {
            TODO("Not yet implemented")
        }
    }

    object EventHandlerMouse : NativeMouseListener {
        override fun nativeMouseClicked(p0: NativeMouseEvent) {
            statisticsKeeper.mouseClick(p0.button)
            statisticsWindow.setCountClicksText("Count of mouse clicks: " + statisticsKeeper.countClicks)
        }

        override fun nativeMousePressed(p0: NativeMouseEvent?) {
            TODO("Not yet implemented")
        }

        override fun nativeMouseReleased(p0: NativeMouseEvent?) {
            TODO("Not yet implemented")
        }
    }

    object EventHandlerMouseMotion : NativeMouseMotionListener {
        override fun nativeMouseMoved(p0: NativeMouseEvent?) {
            statisticsKeeper.mouseMove()
            statisticsWindow.setCountMovesText("Count of mouse moves: " + statisticsKeeper.countMoves)
        }

        override fun nativeMouseDragged(p0: NativeMouseEvent?) {
            TODO("Not yet implemented")
        }
    }
}

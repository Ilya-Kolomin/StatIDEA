package com.github.ilyakolomin.statidea.services

import com.github.ilyakolomin.statidea.MyBundle
import org.jnativehook.GlobalScreen
import org.jnativehook.keyboard.NativeKeyEvent
import org.jnativehook.keyboard.NativeKeyListener
import org.jnativehook.mouse.NativeMouseEvent
import org.jnativehook.mouse.NativeMouseListener
import org.jnativehook.mouse.NativeMouseMotionListener

object MyApplicationService {

    var count_keys: Int = 0
    var count_clicks: Int = 0

    init {
        println(MyBundle.message("applicationService"))
        GlobalScreen.registerNativeHook()
        GlobalScreen.addNativeKeyListener(MainKeyListener)
        GlobalScreen.addNativeMouseListener(MainMouseListener)
        GlobalScreen.addNativeMouseMotionListener(MainMouseMotionListener)
    }

    object MainKeyListener : NativeKeyListener {
        override fun nativeKeyPressed(e: NativeKeyEvent) {
            count_keys++
        }

        override fun nativeKeyReleased(p0: NativeKeyEvent?) {
            TODO("Not yet implemented")
        }

        override fun nativeKeyTyped(p0: NativeKeyEvent?) {
            TODO("Not yet implemented")
        }
    }

    object MainMouseListener : NativeMouseListener {
        override fun nativeMouseClicked(p0: NativeMouseEvent?) {
            count_clicks++
        }

        override fun nativeMousePressed(p0: NativeMouseEvent?) {
            TODO("Not yet implemented")
        }

        override fun nativeMouseReleased(p0: NativeMouseEvent?) {
            TODO("Not yet implemented")
        }
    }

    object MainMouseMotionListener : NativeMouseMotionListener {
        override fun nativeMouseMoved(p0: NativeMouseEvent?) {
            TODO("Not yet implemented")
        }

        override fun nativeMouseDragged(p0: NativeMouseEvent?) {
            TODO("Not yet implemented")
        }
    }
}

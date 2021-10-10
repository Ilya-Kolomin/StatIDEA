package com.github.ilyakolomin.statidea.ui

import com.github.ilyakolomin.statidea.services.SessionService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class UIRenderFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val contentManager = toolWindow.contentManager
        contentManager.addContent(
            contentManager.factory.createContent(
//                StatisticsWindow().statisticsWindowContent,
                service<SessionService>().statisticsWindow.statisticsWindowContent,
                null,
                false
            )
        )
//        contentManager.addContent(
//            ContentFactory.SERVICE.getInstance()
//                .createContent(StatisticsWindow().statisticsWindowContent, "", false)
//        )
    }
}


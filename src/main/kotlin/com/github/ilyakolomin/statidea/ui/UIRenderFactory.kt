package com.github.ilyakolomin.statidea.ui

import com.github.ilyakolomin.statidea.services.SessionService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory

class UIRenderFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val contentManager = toolWindow.contentManager
        contentManager.addContent(
            contentManager.factory.createContent(
                service<SessionService>().statisticsWindow.statisticsWindowContent,
                null,
                false
            )
        )
    }
}

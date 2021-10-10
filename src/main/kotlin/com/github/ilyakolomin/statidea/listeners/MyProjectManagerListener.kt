package com.github.ilyakolomin.statidea.listeners

import com.github.ilyakolomin.statidea.services.SessionService
import com.github.ilyakolomin.statidea.services.StorageManager
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener

internal class MyProjectManagerListener : ProjectManagerListener {

    override fun projectOpened(project: Project) {
        service<SessionService>()
    }

    override fun projectClosing(project: Project) {
        super.projectClosing(project)
        StorageManager.saveStatistics(service<SessionService>().statisticsKeeper)
    }
}

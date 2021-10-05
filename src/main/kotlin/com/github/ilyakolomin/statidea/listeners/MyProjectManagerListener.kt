package com.github.ilyakolomin.statidea.listeners

import com.github.ilyakolomin.statidea.services.MyApplicationService
import com.github.ilyakolomin.statidea.services.MyProjectService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener

internal class MyProjectManagerListener : ProjectManagerListener {

    override fun projectOpened(project: Project) {
        project.service<MyProjectService>()
        service<MyApplicationService>()
    }
}

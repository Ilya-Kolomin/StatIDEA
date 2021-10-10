# StatIDEA

![Build](https://github.com/Ilya-Kolomin/StatIDEA/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/17724-statidea)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/17724-statidea)

***

## Content
1. [Description](#description)
> * [Project description](#project-description)
> * [Brief artifact description](#brief-artifact-description)
> * [Demo](#demo)
2. [Project requirements](#project-requirements)
> * [Glossary](#glossary)
> * [User Stories](#user-stories)
> * [Required Features](#required-features)
> * [Non-functional Requirements](#non-functional-requirements)
3. [Design](#design)
4. [Architecture](#architecture)
5. [Analysis](#analysis)
6. [Template ToDo list](#template-todo-list)
7. [Installation](#installation)

***

## Description
### Project description
<!-- Plugin description -->
StatIDEA is a plugin for JetBrains’ IDEs to measure programmer stats such as the use of the keyboard (typed symbols, used shortcuts, copy/paste usage, and etc.) and mouse (number of clicks, what actions programmer performed, time spent using mouse, and etc.).

This plugin will be useful for programmers who want to improve their productivity. For example, he might show the client that he is using the mouse too often to create a new file, which might be a good trigger to learn how to use a shortcut for him. Another example is that a user may periodically enter the same pattern; with our plugin, the user can detect this and create a shortcut to save their time in the future.

The main objective of this project is to learn how to construct and develop open-source projects and study software system analysis. The product will be distributed free of charge.
<!-- Plugin description end -->

### Brief artifact description
The artifact has following section: 
* glossary
* bussiness goals and objectives
* roles and responsibilies
* requirement analysis and specifications (features, user stories, non-functional requirements)
* software development plan
* prototype
* development views (static view, dynamic view, allocation view)

We suggest that you first study the README, and in case of misunderstanding - visit [Artifact](https://docs.google.com/document/d/1pzEI4KoVcqn5pdFqiqAIp8Q0a2v1-GzP/edit?usp=sharing&ouid=111082605146895567204&rtpof=true&sd=true)

### Demo
![](https://github.com/Ilya-Kolomin/StatIDEA/blob/main/demo.gif)

***

## Project requirements
### Glossary
__`JetBrain`__ - software vendor specializing in the creation of development tools.

__`Plugin`__ - a software component that adds a specific feature to an existing program.

__`Shortcut`__ - a sequence or combination of keystrokes on a computer keyboard that invokes commands in software.

__`IDE (Integrated development environment)`__ - a software application that provides comprehensive facilities to computer programmers for software development.

### User Stories
| User Type | User Story Title | User Story |
| --- | --- | --- |
| Plugin User | Account | As a user, I want to connect the plugin to my account so that my stats could be shared over multiple computers and IDEs |
| Plugin User | Statistics | As a user I want this plugin to collect my statistics of mouse and keyboard usage so that I could make optimizing decisions. |
| Plugin User | Statistics | As a user I want this plugin to show the collected statistic so that I could see it and make decisions |
| Plugin User | Suggestions | As a user I want this plugin to suggest the usage of different shortcuts based on statistics so that my work at IDE will be faster |
| Developer of plugin | Technologies | As a developer, I want to learn the JetBrains’ documentation of creating plugins so that I could develop this plugin |

### Required Features
1. Collect the number of prints, clicks, and keyboard entries for statistics.
2. Show the statistics of clicks and keyboard typing.
3. Show graphs of usages of some features and performance.

### Non-functional Requirements
| Characteristics | Sub-characteristics | Definition |
| --- | --- | --- |
| Security | Confidentiality | Users should have access to their stats only by their JetBrain account. Could be achieved by a built-in authentication mechanism|
| Portability | Installability | The plugin should work correctly on all JetBrains IDEs (CLion, PyCharm, etc.). Could be achieved by using the recommended template for plugins and avoiding IDE-specific features |
| Usability | Accessibility | The program should collect stats for any symbols and languages that users use. Could be achieved by using the UTF-8 coding |
| Maintainability | Modifiability | Every modification should be automatically built and deployed. Could be achieved by using GitHub Actions |

***

## Design
1. Client contains files with counters on local machine.
2. Client is connected to the server (if possible).
3. Client gets latest information from database (server) and rewrites local counters.
4. Client collects number of actions (keyboard pressing, mouse usage, menus calls) during some small specified time.
5. Client sends this information to the server.
6. In the end, client ends the session, server updates values in the database.

This architecture design reduces the database load and allows to save last counters’ state in case of errors from client.

#### UML diagram description
![](https://github.com/Ilya-Kolomin/StatIDEA/blob/main/images/UML.jpg)

#### Sequence diagram description
![](https://github.com/Ilya-Kolomin/StatIDEA/blob/main/images/sequence.jpg)

#### SOLID principles
There are some code examples where SOLID principles holds.
Below you can see how `MyProjectManagerListener` follows __Interface Segregation Principle__
because it implements an interface, also there are other classes which implements 1 or 2 interfaces.

```
internal class MyProjectManagerListener : ProjectManagerListener {

    override fun projectOpened(project: Project) {
        service<SessionService>()
    }

    override fun projectClosing(project: Project) {
        super.projectClosing(project)
        StorageManager.saveStatistics(service<SessionService>().statisticsKeeper)
    }
}
```
Also we should not forget what since we follow __Interface Segragation Principle__ it also means what
we hold __Dependency Inversion Principle__.

Now let's look to these to objects:
`object SessionService`, `object StorageManager `
they follow __Single Responsibility Principle__ sincle first only has 3 function, while second one only 2.

***

## Architecture

__Static view description__

![](https://github.com/Ilya-Kolomin/StatIDEA/blob/main/images/static_view.png)

__Dynamic view description__

![](https://github.com/Ilya-Kolomin/StatIDEA/blob/main/images/dynamic_view.png)
***

## Analysis

***

## Template ToDo list
- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [x] Verify the [pluginGroup](/gradle.properties), [plugin ID](/src/main/resources/META-INF/plugin.xml) and [sources package](/src/main/kotlin).
- [x] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [ ] Set the Plugin ID in the above README badges.
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.


This Fancy IntelliJ Platform Plugin is going to be your implementation of the brilliant ideas that you have.

This specific section is a source for the [plugin.xml](/src/main/resources/META-INF/plugin.xml) file which will be extracted by the [Gradle](/build.gradle.kts) during the build process.

To keep everything working, do not remove `<!-- ... -->` sections. 

***

## Installation

- Using IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "StatIDEA"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/Ilya-Kolomin/StatIDEA/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

***

## Authors
[Ilya Kolomin](https://github.com/Ilya-Kolomin)

[Andrey Khoroshavin](https://github.com/Andrew174194)

[Amir Khuzin](https://github.com/Amirka-Kh)

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template

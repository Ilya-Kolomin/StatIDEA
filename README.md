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
> * [UML diagram description](#uml-diagram-description)
> * [Sequence diagram description](#sequence-diagram-description)
> * [SOLID principles](#solid-principles)
4. [Architecture](#architecture)
> * [Static view description](#static-view-description)
> * [Dynamic view description](#dynamic-view-description)
5. [Installation](#installation)

***

## Description
### Project description
<!-- Plugin description -->
StatIDEA is a plugin for JetBrains’ IDEs to measure programmer stats such as the use of the keyboard (typed symbols, used shortcuts, copy/paste usage, etc.) and mouse (number of clicks, what actions programmer performed, time spent using mouse, etc.).

This plugin will be useful for programmers who want to improve their productivity. For example, he might show the client that he is using the mouse too often to create a new file, which might be a good trigger to learn how to use a shortcut for him. Another example is that a user may periodically enter the same pattern; with our plugin, the user can detect this and create a shortcut to save their time in the future.

The main objective of this project is to learn how to construct and develop open-source projects and study software system analysis. The product will be distributed free of charge with the official JetBrains plugin marketplace or manually via GitHub.
<!-- Plugin description end -->

### Brief artifact description

Link for Artifact - https://docs.google.com/document/d/1pzEI4KoVcqn5pdFqiqAIp8Q0a2v1-GzP/edit?usp=sharing&ouid=111082605146895567204&rtpof=true&sd=true

The artifact has the following section:
* Business Goals
* Stakeholders and their responsibilities
* Functional and non-functional requirements
* Software Development plan
* Architecture (static and dynamic views)
* User scenarios
* UML class diagram
* Future perspectives

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
| Plugin User | Statistics | As a user, I want the plugin to collect my statistics on mouse usage so that I could make optimizing decisions. |
| Plugin User | Statistics | As a user, I want the plugin to collect my statistics on keyboard usage so that I could make optimizing decisions. |
| Plugin User | Statistics | As a user, I want the plugin to show the collected statistic so that I could see it and make decisions. |
| Plugin User | Suggestions | As a user I want this plugin to suggest the usage of different shortcuts based on statistics so that my work at IDE will be faster |
| Developer of plugin | Technologies | As a developer, I want to learn the JetBrains’ documentation of creating plugins so that I could develop this plugin |

### Required Features
1. Collect the number of prints, clicks, and keyboard entries for statistics.
2. Show the statistics of clicks and keyboard typing.
3. Write the number of actions for the past few days.

### Non-functional Requirements
| Characteristics | Sub-characteristics | Definition |
| --- | --- | --- |
| Security | Confidentiality | Users should have access to their stats only by their JetBrain account. Could be achieved by a built-in authentication mechanism|
| Portability | Installability | The plugin should work correctly on all JetBrains IDEs (CLion, PyCharm, etc.). Could be achieved by using the recommended template for plugins and avoiding IDE-specific features |
| Usability | Accessibility | The program should collect stats for any symbols and languages that users use. Could be achieved by using the UTF-8 coding |
| Maintainability | Modifiability | Every modification should be automatically built and deployed. Could be achieved by using GitHub Actions |

***

## Design

### UML diagram description
StatisticsKeeper, DataWithTime,    SessionService, EventHandlerMouseMotion, EventHandlerKeyboard, EventHandlerMouse,  StatisticsWindow, StorageManager, UIRenderFactory
![](https://github.com/Ilya-Kolomin/StatIDEA/blob/main/images/UML.jpg)

### Sequence diagram description
In this diagram, you can find how a user interacts with a plugin. A timeline goes vertically from top to bottom. User starts the IDE, which invokes StatIDEA plugin, it starts handlers and internal classes, when reads saved statistics and in case of keyboard interaction show the statistic. Other details you can read from the diagram:
![](https://github.com/Ilya-Kolomin/StatIDEA/blob/main/images/sequence.jpg)

### SOLID principles
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
Also, we should not forget what since we follow __Interface Segregation Principle__ it also means what
we hold __Dependency Inversion Principle__.

Now let's look to these to objects:
`object SessionService`, `object StorageManager ` they follow __Single Responsibility Principle__ since first only has 3 function, while second one only 2.

So, overall we have hold 3 principles: Single Responsibility Principle, Interface Segregation Principle and Dependency Inversion Principle.

***

## Architecture

### Static view description

In our static view we have 3 layers: `IDE Viewer Side`, `Backend side` and `Storage part`. 

In __IDE Viewer Side__ we have 3 modules responsible for client interactions. 

In __Backend side__ we have 4 modules, which processes clients inputs, applies his changes and talks with Storage part.

In __Storage part__ we have 2 modules: StorageManager which talks with backend, StatIDEA_stats.json where records and session are saved.

![](https://github.com/Ilya-Kolomin/StatIDEA/blob/main/images/static_view.png)

### Dynamic view description

In this view we draw how the system works during runtime, how different modules interact with each other. For example, `Event handlers` update statistics in `StatisticsKeeper`, while `StatisticsKeeper` is used to provide stats to `StatisticsWindow`.

Also, we can see that `StorageManager` talks with `SessionService` and `StatiscsKeeper` which are on 
__backend side__.

![](https://github.com/Ilya-Kolomin/StatIDEA/blob/main/images/dynamic_view.png)
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

# Code Review Guide

## Classes to review

### [model.Main.java](https://github.com/TCSS360Group2Fall2023/Golden-Group-2-Repository/blob/main/src/Main.java)

This class stores global variables for use in other classes, and starts the application.

### [model.Project.java](https://github.com/TCSS360Group2Fall2023/Golden-Group-2-Repository/blob/main/src/Project.java)

This class stores the data associated with a project. This will include a name, description, images, logs, expenses, and tools.

User stories:

* As a DIY enthusiast, I’d like to start a project that contains my budget, things to buy, and a way to log my progress.
* As a DIY enthusiast, I’d like to be able to store my purchased tools outside my projects, and reference them inside my projects as needed without having to add them to my budget.

### [model.DataIO.java](https://github.com/TCSS360Group2Fall2023/Golden-Group-2-Repository/blob/main/src/DataIO.java)

This class covers the importing and exporting of user information.

User stories:

* As a user I want to export settings for synchronization to other devices.
* As a user I want to import settings to synchronize with other devices.
* As a user I want to export and import other information in the application.

## Coding conventions followed

* Using Comparable for objects expected to be in lists
* Class Javadocs with our team name the main author(s)
* Method Javadocs with author(s)
* Use camelCase on local variables
* Use ALL_CAPS on global variables
* Always start a for/if/while with an inline curly brace then end with a curly brace on its own line
* Statements following a curly brace should start on the next line, including else statements
* Space functions or logical blocks of code with exactly 1 empty line
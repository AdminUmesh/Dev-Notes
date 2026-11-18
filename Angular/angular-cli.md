# Angular CLI --- Study Notes

## 1. What is Angular CLI?

**Angular CLI (Command Line Interface)** is a command-line tool that
helps developers create, develop, test, and maintain Angular
applications.

In simple words, Angular CLI automates repetitive development tasks so
developers do not need to create and configure everything manually.

The command used to run Angular CLI is:

``` bash
ng
```

## 2. Why do we need Angular CLI?

Suppose you want to create an Angular component.

Without Angular CLI, you would manually: 1. Create a TypeScript file. 2.
Create an HTML file. 3. Create a CSS file. 4. Write the component
decorator and related code. 5. Configure the component as needed.

With Angular CLI, you can run:

``` bash
ng generate component employee
```

Angular CLI generates the component files and boilerplate code.

## 3. Important Angular CLI commands

  Command                              Purpose
  ------------------------------------ ----------------------------------------------
  `ng new my-app`                      Create a new Angular project
  `ng serve`                           Run the development server
  `ng serve -o`                        Run the application and open it in a browser
  `ng generate component employee`     Generate a component
  `ng generate service employee`       Generate a service
  `ng generate directive highlight`    Generate a directive
  `ng generate pipe currency-format`   Generate a pipe
  `ng build`                           Build the application
  `ng test`                            Run unit tests
  `ng version`                         Display Angular CLI and Angular versions

## 4. Create and run an Angular application

### Step 1: Install Angular CLI

``` bash
npm install -g @angular/cli
```

### Step 2: Create a project

``` bash
ng new employee-management
```

### Step 3: Navigate into the project

``` bash
cd employee-management
```

### Step 4: Run the application

``` bash
ng serve -o
```

The application will usually be available at:

``` text
http://localhost:4200
```

## 5. How Angular CLI works

1.  The developer runs an `ng` command in the terminal.
2.  Angular CLI processes the command.
3.  Depending on the command, it generates project files, runs the
    development server, builds the application, or performs another
    development task.

## 6. Angular vs Angular CLI

  -----------------------------------------------------------------------
  Angular                             Angular CLI
  ----------------------------------- -----------------------------------
  Frontend framework                  Command-line development tool

  Used to build applications          Used to manage Angular projects

  Provides components, services,      Generates components, services,
  directives, and other framework     directives, and other project files
  features                            

  Provides application functionality  Automates development tasks
  -----------------------------------------------------------------------

**Remember:** Angular is the framework; Angular CLI is the tool that
helps you work with that framework.

## 7. Interview question

**Q: What is Angular CLI, and why do we use it?**

**Answer:** Angular CLI is a command-line interface tool used to
initialize, develop, scaffold, test, and maintain Angular applications.
It automates repetitive tasks such as generating components, services,
and directives, and building applications.

## 8. Related concepts to learn next

-   `npm`: Node.js package manager, used to install and manage packages.
-   `npx`: A package runner that can execute package commands without
    requiring a global installation.
-   `ng`: The command used to run Angular CLI commands.

------------------------------------------------------------------------

*Study notes: Angular CLI basics.*

# Node.js
Node.js is a JavaScript runtime. It allows you to execute JavaScript outside a browser.

## Node Installation
Download the LTS version for Windows.  [From Official Website](https://nodejs.org/en/download)

## Version Check

```bash
node -v
```

## Run JavaScript
### 1. Run JavaScript without creating a file
Open Command Prompt or the VS Code terminal and type:

```bash
node
```
This opens the Node.js REPL (Read-Eval-Print Loop), where you can type JavaScript directly.

```javaScript
let name = "Umesh";
console.log("Hello " + name);
```

### 2. Run JavaScript from a file
Create a file **EX:** app.js:

```javaScript
// app.js file
let name = "Umesh";
console.log("Hello " + name);
```
Open a terminal in that file's directory and run:

```bash
node app.js
```

# What is NPM
npm stands for Node Package Manager. It is a package manager that comes bundled with Node.js by default.

Its main purpose is to install, manage, and update libraries and tools that your JavaScript or Angular project needs.

## Version Check

```bash
npm -v
```

## Why do we need npm?
Suppose you're developing an Angular application. Your application uses libraries such as Angular, RxJS, and other packages.

Instead of downloading each library manually, you can use npm to install them.

**For example:**
```bash
npm install @angular/core
```

## Common npm Commands

| Command | Purpose |
|---|---|
| `npm install` | Installs the dependencies listed in `package.json`. |
| `npm install <package>` | Installs a specific package. |
| `npm install -g <package>` | Installs a package globally. |
| `npm uninstall <package>` | Removes a package. |
| `npm update` | Updates packages within their allowed version ranges. |
| `npm run start` | Runs the `start` script defined in `package.json`. |


**npm is also used to run scripts defined in package.json, such as:**
- npm start
- npm run build
- npm test
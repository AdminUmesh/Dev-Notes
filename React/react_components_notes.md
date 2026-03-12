# React Components Notes

## 📌 What is a Component?

A **Component** is a reusable piece of UI in React.

It is basically a **JavaScript function that returns JSX (UI)**.

Components allow developers to **break the UI into small reusable
pieces**.

Example:

``` jsx
function Header() {
  return <h1>Welcome to React</h1>;
}
```

Using the component:

``` jsx
function App() {
  return (
    <div>
      <Header />
    </div>
  );
}
```

------------------------------------------------------------------------

# Why Components Are Important

Components help in:

-   Reusability
-   Code organization
-   Easier maintenance
-   Better scalability

Instead of writing a large UI in one file, React apps are built using
many small components.

------------------------------------------------------------------------

# Types of Components

React has two types of components:

1.  Functional Components (Modern)
2.  Class Components (Older)

------------------------------------------------------------------------

# 1️⃣ Functional Components

Functional components are **simple JavaScript functions**.

They are the **most commonly used components in modern React**.

Example:

``` jsx
function Welcome() {
  return <h1>Hello User</h1>;
}
```

Arrow function example:

``` jsx
const Welcome = () => {
  return <h1>Hello User</h1>;
};
```

------------------------------------------------------------------------

# 2️⃣ Class Components (Older Method)

Before React Hooks, developers used **class-based components**.

Example:

``` jsx
import React from "react";

class Welcome extends React.Component {
  render() {
    return <h1>Hello User</h1>;
  }
}

export default Welcome;
```

Today, **functional components + hooks** are recommended.

------------------------------------------------------------------------

# Creating a Component

Example file:

    src/components/Header.jsx

Code:

``` jsx
function Header() {
  return (
    <div>
      <h1>My Website</h1>
    </div>
  );
}

export default Header;
```

------------------------------------------------------------------------

# Using a Component

Import the component:

``` jsx
import Header from "./components/Header";
```

Use it in JSX:

``` jsx
function App() {
  return (
    <div>
      <Header />
    </div>
  );
}
```

------------------------------------------------------------------------

# Component Naming Rules

✔ Component name must start with **Capital Letter**.

Correct:

``` jsx
function Navbar() {}
```

Wrong:

``` jsx
function navbar() {}
```

React treats lowercase names as **HTML elements**.

------------------------------------------------------------------------

# Nested Components

Components can be used inside other components.

Example:

``` jsx
function Header() {
  return <h1>Header</h1>;
}

function Footer() {
  return <p>Footer</p>;
}

function App() {
  return (
    <div>
      <Header />
      <Footer />
    </div>
  );
}
```

------------------------------------------------------------------------

# Reusable Components

Components are reusable.

Example:

``` jsx
function Button() {
  return <button>Click Me</button>;
}
```

Using it multiple times:

``` jsx
<Button />
<Button />
<Button />
```

------------------------------------------------------------------------

# Component Folder Structure

Recommended structure:

    src
    │
    ├── components
    │   ├── Header.jsx
    │   ├── Footer.jsx
    │   ├── Navbar.jsx
    │
    ├── pages
    │   ├── Home.jsx
    │   ├── About.jsx
    │
    ├── App.jsx
    └── main.jsx

------------------------------------------------------------------------

# Components vs Pages

  Type         Purpose
  ------------ --------------------------
  Components   Small reusable UI blocks
  Pages        Full screen views

Example:

Components: - Navbar - Button - Card

Pages: - Home page - Login page - Dashboard

------------------------------------------------------------------------

# Component with Props Example

``` jsx
function User(props) {
  return <h1>Hello {props.name}</h1>;
}
```

Usage:

``` jsx
<User name="Umesh" />
<User name="Rahul" />
```

------------------------------------------------------------------------

# Component Lifecycle (Basic Idea)

When React runs:

    Component Created
            ↓
    Component Rendered
            ↓
    State / Props Change
            ↓
    Component Re-render

------------------------------------------------------------------------

# Best Practices

✔ Keep components small\
✔ Use meaningful names\
✔ Reuse components\
✔ Avoid large components\
✔ Separate UI and logic

------------------------------------------------------------------------

# Example Real Component

``` jsx
function Profile() {

  const name = "Umesh Kumar";

  return (
    <div>
      <h1>{name}</h1>
      <p>Software Developer</p>
    </div>
  );
}

export default Profile;
```

------------------------------------------------------------------------

# Summary

React Components allow developers to:

✔ Break UI into small pieces\
✔ Reuse UI elements\
✔ Build scalable applications\
✔ Maintain clean project structure

Components are the **foundation of React applications**.

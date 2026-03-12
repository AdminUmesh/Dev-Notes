# Advanced React Notes

This document covers important advanced React concepts used in
real-world applications.

Topics included:

1.  useEffect Lifecycle\
2.  API Calls\
3.  Custom Hooks\
4.  Context API\
5.  Routing (React Router)\
6.  State Management (Redux)\
7.  Performance Optimization\
8.  Project Architecture

------------------------------------------------------------------------

# 1️⃣ useEffect Lifecycle

## What is useEffect?

`useEffect` is a React Hook used to run **side effects** in functional
components.

Examples of side effects:

-   API calls
-   Timers
-   Subscriptions
-   DOM manipulation

Basic Syntax:

``` jsx
import { useEffect } from "react";

useEffect(() => {
  console.log("Component mounted");
}, []);
```

### Dependency Array Behavior

  Dependency   Behavior
  ------------ -----------------------------
  `[]`         Runs once (component mount)
  `[value]`    Runs when value changes
  none         Runs every render

Example:

``` jsx
useEffect(() => {
  console.log("Count changed");
}, [count]);
```

### Cleanup Function

Used to prevent memory leaks.

``` jsx
useEffect(() => {
  const timer = setInterval(() => {
    console.log("Running");
  }, 1000);

  return () => clearInterval(timer);
}, []);
```

------------------------------------------------------------------------

# 2️⃣ API Calls

React apps communicate with backend APIs.

Example using **fetch**:

``` jsx
import { useEffect, useState } from "react";

function Users(){

  const [users,setUsers] = useState([]);

  useEffect(() => {
    fetch("https://jsonplaceholder.typicode.com/users")
      .then(res => res.json())
      .then(data => setUsers(data));
  },[]);

  return (
    <div>
      {users.map(user => (
        <p key={user.id}>{user.name}</p>
      ))}
    </div>
  );
}
```

Example using **Axios**:

``` bash
npm install axios
```

``` jsx
import axios from "axios";

useEffect(() => {
  axios.get("/api/users")
       .then(res => setUsers(res.data));
}, []);
```

------------------------------------------------------------------------

# 3️⃣ Custom Hooks

Custom hooks allow developers to **reuse logic across components**.

Example:

``` jsx
import { useState } from "react";

function useCounter(){

  const [count,setCount] = useState(0);

  const increase = () => setCount(count + 1);

  return {count,increase};
}
```

Using custom hook:

``` jsx
function Counter(){

  const {count,increase} = useCounter();

  return (
    <button onClick={increase}>
      {count}
    </button>
  );
}
```

Rule: Custom hooks must start with **use**.

------------------------------------------------------------------------

# 4️⃣ Context API

Context API is used to **share global data without prop drilling**.

Example use cases:

-   Authentication
-   Theme
-   User data

Create context:

``` jsx
import { createContext } from "react";

export const UserContext = createContext();
```

Provider:

``` jsx
<UserContext.Provider value={user}>
  <App />
</UserContext.Provider>
```

Consume context:

``` jsx
import { useContext } from "react";

const user = useContext(UserContext);
```

------------------------------------------------------------------------

# 5️⃣ Routing (React Router)

Routing allows navigation between pages without reloading.

Install:

``` bash
npm install react-router-dom
```

Example:

``` jsx
import { BrowserRouter,Routes,Route } from "react-router-dom";

function App(){

  return (
    <BrowserRouter>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
      </Routes>

    </BrowserRouter>
  );
}
```

Navigation:

``` jsx
import { Link } from "react-router-dom";

<Link to="/about">About</Link>
```

------------------------------------------------------------------------

# 6️⃣ State Management (Redux)

Redux is used to manage **global application state**.

Install:

``` bash
npm install @reduxjs/toolkit react-redux
```

Create store:

``` jsx
import { configureStore } from "@reduxjs/toolkit";

export const store = configureStore({
  reducer: {}
});
```

Provider:

``` jsx
import { Provider } from "react-redux";

<Provider store={store}>
  <App />
</Provider>
```

Access state:

``` jsx
import { useSelector,useDispatch } from "react-redux";
```

Redux helps manage:

-   authentication
-   cart systems
-   large application state

------------------------------------------------------------------------

# 7️⃣ Performance Optimization

React provides several performance techniques.

### React.memo

Prevents unnecessary re-renders.

``` jsx
const MyComponent = React.memo(function MyComponent(props){
  return <div>{props.name}</div>;
});
```

### useMemo

Memoizes expensive calculations.

``` jsx
const value = useMemo(() => calculateExpensiveValue(data), [data]);
```

### useCallback

Prevents function recreation.

``` jsx
const handleClick = useCallback(() => {
  console.log("clicked");
}, []);
```

------------------------------------------------------------------------

# 8️⃣ Project Architecture

Typical **production React project structure**:

    src
    │
    ├── components
    ├── pages
    ├── hooks
    ├── context
    ├── redux
    ├── services
    ├── utils
    ├── assets
    ├── routes
    │
    ├── App.tsx
    └── main.tsx

Explanation:

  Folder       Purpose
  ------------ ------------------
  components   reusable UI
  pages        full screens
  hooks        custom hooks
  context      global contexts
  redux        redux store
  services     API calls
  utils        helper functions
  routes       routing config

------------------------------------------------------------------------

# Typical React Application Flow

    User Interaction
          ↓
    Event Triggered
          ↓
    State Update
          ↓
    API Call
          ↓
    Backend Response
          ↓
    UI Re-render

------------------------------------------------------------------------

# Summary

These advanced topics help build **scalable React applications**.

Important concepts:

✔ useEffect lifecycle\
✔ API communication\
✔ Custom hooks\
✔ Global state with Context / Redux\
✔ Routing with React Router\
✔ Performance optimization\
✔ Clean project architecture

## What is Conditional Rendering?

**Conditional Rendering** means displaying different UI elements
depending on a condition.

Just like JavaScript conditions (`if`, `else`, `ternary`), React can
render different components or elements based on state or props.

**Example:**

``` jsx
function App() {
  const isLoggedIn = true;

  return (
    <div>
      {isLoggedIn ? <h1>Welcome User</h1> : <h1>Please Login</h1>}
    </div>
  );
}
```

------------------------------------------------------------------------

# Why Conditional Rendering is Important

Conditional rendering helps to:

-   Show different UI for different users
-   Display loading states
-   Handle authentication
-   Show or hide elements
-   Control UI based on data

------------------------------------------------------------------------

# 1️⃣ Using if Statement

**Example:**

``` jsx
function Greeting({ isLoggedIn }) {

  if (isLoggedIn) {
    return <h1>Welcome Back</h1>;
  }

  return <h1>Please Login</h1>;
}
```

------------------------------------------------------------------------

# 2️⃣ Using Ternary Operator

The most common method in React.

**Syntax:**

``` jsx
condition ? trueUI : falseUI
```

**Example:**

``` jsx
function App(){

  const isLoggedIn = false;

  return (
    <div>
      {isLoggedIn ? <h1>Dashboard</h1> : <h1>Login Page</h1>}
    </div>
  );
}
```

------------------------------------------------------------------------

# 3️⃣ Using Logical AND (&&)

Used when you only want to show UI if condition is true.

**Example:**

``` jsx
function App(){

  const showMessage = true;

  return (
    <div>
      {showMessage && <p>This message is visible</p>}
    </div>
  );
}
```

If condition is **false**, nothing will render.

------------------------------------------------------------------------

# 4️⃣ Using Element Variables

You can store JSX in variables.

**Example:**

``` jsx
function App(){

  const isLoggedIn = true;

  let message;

  if(isLoggedIn){
    message = <h1>Welcome User</h1>;
  } else {
    message = <h1>Please Login</h1>;
  }

  return (
    <div>
      {message}
    </div>
  );
}
```

------------------------------------------------------------------------

# 5️⃣ Conditional Rendering with Components

**Example:**

``` jsx
function Login(){
  return <h1>Login Page</h1>;
}

function Dashboard(){
  return <h1>Dashboard</h1>;
}

function App(){

  const isLoggedIn = true;

  return (
    <div>
      {isLoggedIn ? <Dashboard /> : <Login />}
    </div>
  );
}
```

------------------------------------------------------------------------

# 6️⃣ Conditional Rendering with Loading State

**Example:**

``` jsx
import { useState, useEffect } from "react";

function Data(){

  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setTimeout(() => {
      setLoading(false);
    }, 2000);
  }, []);

  return (
    <div>
      {loading ? <p>Loading...</p> : <p>Data Loaded</p>}
    </div>
  );
}
```

------------------------------------------------------------------------

# 7️⃣ Conditional Rendering in Lists

**Example:**

``` jsx
const users = ["Umesh", "Rahul", "Amit"];

<ul>
  {users.length > 0 ? (
    users.map((u, index) => <li key={index}>{u}</li>)
  ) : (
    <p>No Users Found</p>
  )}
</ul>
```

------------------------------------------------------------------------

# Common Use Cases

  |Situation       | Example|
  |----------------| -------------------------|
  |Authentication  | Show Login or Dashboard|
  |Loading         | Show spinner|
  |Error Handling  | Show error message|
  |Empty Data      | Show "No Data" message|
  |Feature Flags   | Enable/disable UI|

------------------------------------------------------------------------

# Best Practices

✔ Use **ternary operator for simple conditions**\
✔ Use **if statements for complex logic**\
✔ Avoid deeply nested ternaries\
✔ Keep JSX clean and readable\
✔ Use separate components for complex UI

------------------------------------------------------------------------

# Example Real Component

``` jsx
function Notification({ count }){

  return (
    <div>
      {count > 0 ? (
        <h2>You have {count} notifications</h2>
      ) : (
        <h2>No notifications</h2>
      )}
    </div>
  );
}
```

------------------------------------------------------------------------

# Conditional Rendering Flow

    State / Props Value
            ↓
    Condition Checked
            ↓
    React Decides UI
            ↓
    Component Rendered

------------------------------------------------------------------------

# Summary

Conditional Rendering allows React developers to:

✔ Show or hide UI dynamically\
✔ Control UI based on state or props\
✔ Improve user experience\
✔ Handle loading and authentication flows

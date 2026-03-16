## What is useState?

`useState` is a **React Hook** used to **create and manage state inside
functional components**.

State represents **data that can change over time** and causes the UI to
update automatically.

Before React Hooks, state could only be used in **class components**.
Now we can use it in **functional components**.

------------------------------------------------------------------------

# Basic Syntax

``` jsx
const [state, setState] = useState(initialValue);
```

**Explanation:**

  |Part    |       Meaning |
  |-------------- | -------------------------------|
  |state          | Current value|
  |setState       | Function used to update state|
  |initialValue   | Starting value of state|

------------------------------------------------------------------------

# Simple Example

``` jsx
import { useState } from "react";

function Counter() {

  const [count, setCount] = useState(0);

  return (
    <div>
      <h1>{count}</h1>

      <button onClick={() => setCount(count + 1)}>
        Increase
      </button>
    </div>
  );
}
```

**Flow:**

    User Clicks Button
            ↓
    setCount() runs
            ↓
    State Updates
            ↓
    Component Re-renders
            ↓
    UI Updates

------------------------------------------------------------------------

# Updating State

State should always be updated using the **setter function**.

Correct:

``` jsx
setCount(count + 1);
```

Wrong:

``` jsx
count = count + 1;
```

Direct modification **will not update the UI**.

------------------------------------------------------------------------

# Multiple State Variables

You can create multiple state variables.

Example:

``` jsx
const [name, setName] = useState("");
const [age, setAge] = useState(0);
const [isLoggedIn, setIsLoggedIn] = useState(false);
```

**Example component:**

``` jsx
function Profile(){

  const [name, setName] = useState("Umesh");
  const [age, setAge] = useState(25);

  return (
    <div>
      <h1>{name}</h1>
      <p>{age}</p>
    </div>
  );
}
```

------------------------------------------------------------------------

# State with Input Fields

Example:

``` jsx
import { useState } from "react";

function Login(){

  const [email, setEmail] = useState("");

  return (
    <div>

      <input
        type="text"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />

      <p>{email}</p>

    </div>
  );
}
```

This creates a **controlled component**.

------------------------------------------------------------------------

# State with Objects

State can store objects.

Example:

``` jsx
const [user, setUser] = useState({
  name: "Umesh",
  age: 25
});
```

Updating object state:

``` jsx
setUser({
  ...user,
  age: 26
});
```

`...user` keeps existing values.

------------------------------------------------------------------------

# State with Arrays

Example:

``` jsx
const [users, setUsers] = useState(["Umesh", "Rahul"]);
```

Add new item:

``` jsx
setUsers([...users, "Amit"]);
```

Rendering:

``` jsx
<ul>
  {users.map((u, index) => (
    <li key={index}>{u}</li>
  ))}
</ul>
```

------------------------------------------------------------------------

# Functional State Update

When new state depends on previous state.

Example:

``` jsx
setCount(prev => prev + 1);
```

Better for frequent updates.

------------------------------------------------------------------------

# When to Use useState

Use useState when:

✔ UI needs dynamic data\
✔ Input values need to be stored\
✔ Buttons change values\
✔ API responses update UI

------------------------------------------------------------------------

# useState vs Props

  |Feature         |useState              |  Props|
  --------------- |-----------------------| ------------------------------|
  |Data location   |Inside component      |  From parent|
  |Mutable         |Yes                   |  No|
  |Purpose         |Manage component data |  Pass data between components|

------------------------------------------------------------------------

# Example Real Component

``` jsx
import { useState } from "react";

function LikeButton(){

  const [likes, setLikes] = useState(0);

  return (
    <div>

      <h2>Likes: {likes}</h2>

      <button onClick={() => setLikes(likes + 1)}>
        Like
      </button>

    </div>
  );
}
```

------------------------------------------------------------------------

# React State Update Flow

    Component Render
            ↓
    User Interaction
            ↓
    setState() called
            ↓
    React updates state
            ↓
    Component Re-render
            ↓
    Updated UI

------------------------------------------------------------------------

# Best Practices

✔ Keep state minimal\
✔ Do not modify state directly\
✔ Use multiple states for different data\
✔ Use functional update when needed\
✔ Keep state close to where it is used

------------------------------------------------------------------------

# Summary

`useState` allows React developers to:

✔ Store component data\
✔ Update UI dynamically\
✔ Handle user interaction\
✔ Manage application state

`useState` is one of the **most important hooks in React**.
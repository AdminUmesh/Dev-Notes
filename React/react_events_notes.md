# React Events Notes

## 📌 What are Events in React?

Events in React are **user interactions with the UI**.

Examples of events:

-   Button click
-   Form submission
-   Mouse hover
-   Keyboard typing
-   Input change

React handles events using **event handlers** similar to JavaScript but
with some differences.

------------------------------------------------------------------------

# Basic Event Example

``` jsx
function App() {

  function handleClick() {
    alert("Button Clicked");
  }

  return (
    <button onClick={handleClick}>
      Click Me
    </button>
  );
}
```

Explanation:

  Part          Meaning
  ------------- ------------------------
  onClick       React event
  handleClick   Event handler function

------------------------------------------------------------------------

# React Event Naming

React uses **camelCase** for events.

  HTML       React
  ---------- ----------
  onclick    onClick
  onchange   onChange
  onsubmit   onSubmit

Example:

``` jsx
<button onClick={handleClick}>Click</button>
```

------------------------------------------------------------------------

# Common React Events

  Event          Description
  -------------- ------------------------------------
  onClick        Triggered when button is clicked
  onChange       Triggered when input value changes
  onSubmit       Triggered when form is submitted
  onMouseEnter   Mouse enters element
  onMouseLeave   Mouse leaves element
  onKeyDown      Keyboard key pressed

------------------------------------------------------------------------

# Button Click Event

Example:

``` jsx
function Button(){

  const handleClick = () => {
    console.log("Button clicked");
  };

  return (
    <button onClick={handleClick}>
      Click
    </button>
  );
}
```

------------------------------------------------------------------------

# Passing Arguments to Event

Example:

``` jsx
function App(){

  const showMessage = (name) => {
    alert("Hello " + name);
  };

  return (
    <button onClick={() => showMessage("Umesh")}>
      Click
    </button>
  );
}
```

Arrow function is used to pass arguments.

------------------------------------------------------------------------

# Input Change Event

Example:

``` jsx
import { useState } from "react";

function InputExample(){

  const [name, setName] = useState("");

  return (
    <div>

      <input
        type="text"
        onChange={(e) => setName(e.target.value)}
      />

      <p>{name}</p>

    </div>
  );
}
```

Explanation:

    User Types
         ↓
    onChange Triggered
         ↓
    State Updated
         ↓
    UI Updated

------------------------------------------------------------------------

# Form Submit Event

Example:

``` jsx
function Login(){

  const handleSubmit = (e) => {
    e.preventDefault();
    alert("Form Submitted");
  };

  return (
    <form onSubmit={handleSubmit}>

      <input type="text" placeholder="Username" />

      <button type="submit">
        Login
      </button>

    </form>
  );
}
```

`e.preventDefault()` stops page refresh.

------------------------------------------------------------------------

# Mouse Events

Example:

``` jsx
function HoverExample(){

  const handleHover = () => {
    console.log("Mouse Entered");
  };

  return (
    <div onMouseEnter={handleHover}>
      Hover Over Me
    </div>
  );
}
```

------------------------------------------------------------------------

# Keyboard Events

Example:

``` jsx
function KeyExample(){

  const handleKey = (e) => {
    console.log(e.key);
  };

  return (
    <input
      type="text"
      onKeyDown={handleKey}
    />
  );
}
```

This logs the key pressed.

------------------------------------------------------------------------

# Synthetic Events

React uses **Synthetic Events**.

These are **wrapper objects around native browser events**.

Benefits:

-   Cross-browser compatibility
-   Consistent behavior

Example:

``` jsx
function Click(){

  const handleClick = (event) => {
    console.log(event.target);
  };

  return (
    <button onClick={handleClick}>
      Click
    </button>
  );
}
```

------------------------------------------------------------------------

# Event Handling Best Practices

✔ Use arrow functions when passing parameters\
✔ Keep event handlers small\
✔ Use meaningful function names\
✔ Avoid inline complex logic\
✔ Prevent default behavior when necessary

------------------------------------------------------------------------

# Example Real Component

``` jsx
import { useState } from "react";

function Counter(){

  const [count, setCount] = useState(0);

  const increase = () => {
    setCount(count + 1);
  };

  return (
    <div>

      <h1>{count}</h1>

      <button onClick={increase}>
        Increase
      </button>

    </div>
  );
}
```

------------------------------------------------------------------------

# React Event Flow

    User Interaction
           ↓
    Event Triggered
           ↓
    Event Handler Function Runs
           ↓
    State Update (if needed)
           ↓
    Component Re-render
           ↓
    UI Updated

------------------------------------------------------------------------

# Summary

React Events allow developers to:

✔ Handle user interactions\
✔ Update UI dynamically\
✔ Control forms and inputs\
✔ Trigger application logic

Events are a **core part of building interactive React applications**.

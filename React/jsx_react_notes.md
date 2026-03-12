# JSX Notes (React)

## 📌 What is JSX?

JSX stands for **JavaScript XML**.\
It allows developers to write **HTML-like code inside JavaScript**.

React uses JSX to describe what the UI should look like.

Example:

``` jsx
function App() {
  return (
    <div>
      <h1>Hello React</h1>
      <p>This is JSX syntax</p>
    </div>
  );
}
```

Behind the scenes, JSX is converted into JavaScript.

Example conversion:

``` javascript
React.createElement("h1", null, "Hello React")
```

------------------------------------------------------------------------

# Why JSX is Used

JSX makes UI code:

-   Easier to read
-   Easier to write
-   Similar to HTML structure
-   Better developer experience

Without JSX:

``` javascript
React.createElement("div", null,
  React.createElement("h1", null, "Hello")
)
```

With JSX:

``` jsx
<div>
  <h1>Hello</h1>
</div>
```

------------------------------------------------------------------------

# JSX Rules

## 1️⃣ Single Parent Element

JSX must return **one parent element**.

❌ Wrong

``` jsx
return (
  <h1>Hello</h1>
  <p>World</p>
)
```

✅ Correct

``` jsx
return (
  <div>
    <h1>Hello</h1>
    <p>World</p>
  </div>
)
```

------------------------------------------------------------------------

## 2️⃣ Use className Instead of class

HTML uses `class`, but JSX uses **className**.

❌ Wrong

``` jsx
<div class="box"></div>
```

✅ Correct

``` jsx
<div className="box"></div>
```

------------------------------------------------------------------------

## 3️⃣ JavaScript Inside Curly Braces

You can use JavaScript inside `{}`.

Example:

``` jsx
const name = "Umesh";

<h1>Hello {name}</h1>
```

Example with expression:

``` jsx
<h2>{10 + 5}</h2>
```

------------------------------------------------------------------------

## 4️⃣ Self Closing Tags

Tags without children must be self-closing.

❌ Wrong

``` jsx
<img src="logo.png">
```

✅ Correct

``` jsx
<img src="logo.png" />
```

------------------------------------------------------------------------

## 5️⃣ Inline Styling

JSX styles are written using **JavaScript objects**.

Example:

``` jsx
const style = {
  color: "red",
  fontSize: "20px"
};

<h1 style={style}>Hello</h1>
```

Or directly:

``` jsx
<h1 style={{ color: "blue" }}>Hello</h1>
```

------------------------------------------------------------------------

# Embedding Expressions in JSX

You can use:

-   variables
-   functions
-   calculations

Example:

``` jsx
function App() {

  const user = "Umesh";

  return (
    <div>
      <h1>Hello {user}</h1>
      <p>{5 * 10}</p>
    </div>
  );
}
```

------------------------------------------------------------------------

# Conditional Rendering in JSX

## Using Ternary Operator

``` jsx
const isLoggedIn = true;

<h1>
  {isLoggedIn ? "Welcome User" : "Please Login"}
</h1>
```

## Using Logical AND

``` jsx
const showMessage = true;

{showMessage && <p>This is visible</p>}
```

------------------------------------------------------------------------

# Rendering Lists in JSX

Use `.map()` to render multiple elements.

Example:

``` jsx
const users = ["Umesh", "Rahul", "Amit"];

<ul>
  {users.map((user, index) => (
    <li key={index}>{user}</li>
  ))}
</ul>
```

------------------------------------------------------------------------

# Fragments in JSX

Instead of `<div>`, React provides **Fragments**.

``` jsx
<>
  <h1>Hello</h1>
  <p>World</p>
</>
```

This avoids unnecessary HTML elements.

------------------------------------------------------------------------

# JSX with Components

JSX can render components.

Example:

``` jsx
function Header() {
  return <h1>Header Component</h1>;
}

function App() {
  return (
    <div>
      <Header />
    </div>
  );
}
```

------------------------------------------------------------------------

# JSX vs HTML

  Feature          HTML     JSX
  ---------------- -------- -----------
  class            class    className
  for              for      htmlFor
  JS expressions   ❌       ✔
  styling          string   JS object

------------------------------------------------------------------------

# JSX Best Practices

✔ Keep JSX clean and readable\
✔ Use components for large UI blocks\
✔ Avoid complex logic inside JSX\
✔ Use fragments instead of extra divs\
✔ Use keys when rendering lists

------------------------------------------------------------------------

# JSX Example Component

``` jsx
function Profile() {

  const name = "Umesh";
  const age = 25;

  return (
    <div>
      <h1>{name}</h1>
      <p>Age: {age}</p>
    </div>
  );
}

export default Profile;
```

------------------------------------------------------------------------

# React Rendering Flow

    JSX Written
          ↓
    Babel Transpiles JSX
          ↓
    React.createElement()
          ↓
    Virtual DOM
          ↓
    Real DOM Update

------------------------------------------------------------------------

# Summary

JSX allows developers to:

✔ Write HTML inside JavaScript\
✔ Create dynamic UI easily\
✔ Use JavaScript expressions in UI\
✔ Build reusable components

JSX is one of the **core concepts of React development**.

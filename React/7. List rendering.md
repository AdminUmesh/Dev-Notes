## What is List Rendering?

**List Rendering** in React means displaying multiple items from an
array or data collection in the UI.

React commonly uses the **JavaScript `.map()` function** to render
lists.

Example:

``` jsx
const users = ["Umesh", "Rahul", "Amit"];

function App() {
  return (
    <ul>
      {users.map((user) => (
        <li>{user}</li>
      ))}
    </ul>
  );
}
```

------------------------------------------------------------------------

# Why List Rendering is Important

List rendering is used when:

-   Displaying data from APIs
-   Showing tables
-   Rendering menus
-   Displaying products

Most real applications render data **dynamically using lists**.

------------------------------------------------------------------------

# Using map() for List Rendering

The `.map()` function loops through an array and returns JSX.

Example:

``` jsx
const numbers = [1,2,3,4];

function App(){
  return (
    <div>
      {numbers.map(num => (
        <p>{num}</p>
      ))}
    </div>
  );
}
```

Flow:

    Array Data
         ↓
    .map() Loop
         ↓
    Return JSX
         ↓
    React Renders List

------------------------------------------------------------------------

# The Key Prop

When rendering lists, React requires a **key**.

A key helps React identify which items changed.

Example:

``` jsx
const users = ["Umesh","Rahul","Amit"];

function App(){
  return (
    <ul>
      {users.map((user,index) => (
        <li key={index}>{user}</li>
      ))}
    </ul>
  );
}
```

Why keys are important:

-   Improves performance
-   Helps React update UI efficiently
-   Prevents rendering issues

------------------------------------------------------------------------

# Best Key Practice

Using **unique IDs** is better than index.

Example:

``` jsx
const users = [
  {id:1, name:"Umesh"},
  {id:2, name:"Rahul"},
  {id:3, name:"Amit"}
];

function App(){
  return (
    <ul>
      {users.map(user => (
        <li key={user.id}>{user.name}</li>
      ))}
    </ul>
  );
}
```

------------------------------------------------------------------------

# Rendering Lists of Components

Lists can render components.

Example:

``` jsx
function User({name}){
  return <li>{name}</li>;
}

const users = ["Umesh","Rahul","Amit"];

function App(){
  return (
    <ul>
      {users.map((user,index) => (
        <User key={index} name={user} />
      ))}
    </ul>
  );
}
```

------------------------------------------------------------------------

# Conditional Rendering in Lists

Example:

``` jsx
const users = ["Umesh","Rahul","Amit"];

function App(){

  return (
    <div>
      {users.length > 0 ? (
        users.map((u,i) => <p key={i}>{u}</p>)
      ) : (
        <p>No Users Found</p>
      )}
    </div>
  );
}
```

------------------------------------------------------------------------

# Rendering Lists from API Data

Example:

``` jsx
import { useState, useEffect } from "react";

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

------------------------------------------------------------------------

# Rendering Tables

Example:

``` jsx
const users = [
  {id:1,name:"Umesh",age:25},
  {id:2,name:"Rahul",age:26}
];

function Table(){

  return (
    <table>
      <tbody>
        {users.map(user => (
          <tr key={user.id}>
            <td>{user.name}</td>
            <td>{user.age}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
```

------------------------------------------------------------------------

# Filtering Lists

Example:

``` jsx
const numbers = [1,2,3,4,5];

function App(){

  const evenNumbers = numbers.filter(n => n % 2 === 0);

  return (
    <div>
      {evenNumbers.map(n => (
        <p key={n}>{n}</p>
      ))}
    </div>
  );
}
```

------------------------------------------------------------------------

# List Rendering Flow

    Data Source (Array / API)
            ↓
    map() Loop
            ↓
    JSX Generated
            ↓
    React Virtual DOM
            ↓
    UI Rendered

------------------------------------------------------------------------

# Best Practices

✔ Always use a **key prop**\
✔ Prefer **unique IDs instead of index**\
✔ Keep list items simple\
✔ Use components for complex list items\
✔ Avoid heavy logic inside `.map()`

------------------------------------------------------------------------

# Example Real Component

``` jsx
function ProductList(){

  const products = [
    {id:1,name:"Laptop"},
    {id:2,name:"Phone"},
    {id:3,name:"Tablet"}
  ];

  return (
    <div>

      {products.map(product => (
        <p key={product.id}>
          {product.name}
        </p>
      ))}

    </div>
  );
}
```

------------------------------------------------------------------------

# Summary

React List Rendering allows developers to:

✔ Display dynamic data\
✔ Render UI from arrays\
✔ Handle API data\
✔ Build tables and lists efficiently

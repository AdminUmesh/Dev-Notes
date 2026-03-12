# React Props Notes

## 📌 What are Props?

**Props (short for Properties)** are used to **pass data from a parent
component to a child component**.

Props make components **dynamic and reusable**.

React components receive props as **input parameters**.

------------------------------------------------------------------------

# Basic Example

Parent Component:

``` jsx
function App() {
  return <User name="Umesh" />;
}
```

Child Component:

``` jsx
function User(props) {
  return <h1>Hello {props.name}</h1>;
}
```

Output:

    Hello Umesh

------------------------------------------------------------------------

# How Props Work

Flow of data:

    Parent Component
            ↓
    Pass Props
            ↓
    Child Component
            ↓
    Render UI

Props allow a component to behave differently depending on the data
received.

------------------------------------------------------------------------

# Props with Multiple Values

You can pass multiple props.

Example:

``` jsx
function App() {
  return <User name="Umesh" age={25} city="Delhi" />;
}
```

Child component:

``` jsx
function User(props) {
  return (
    <div>
      <h1>{props.name}</h1>
      <p>Age: {props.age}</p>
      <p>City: {props.city}</p>
    </div>
  );
}
```

------------------------------------------------------------------------

# Props Destructuring

Instead of using `props.name`, we can destructure props.

Example:

``` jsx
function User({ name, age }) {
  return (
    <div>
      <h1>{name}</h1>
      <p>Age: {age}</p>
    </div>
  );
}
```

Usage:

``` jsx
<User name="Umesh" age={25} />
```

Destructuring makes code **cleaner and easier to read**.

------------------------------------------------------------------------

# Passing Numbers, Strings, and Boolean Props

### String

``` jsx
<User name="Umesh" />
```

### Number

``` jsx
<User age={25} />
```

### Boolean

``` jsx
<User isAdmin={true} />
```

or

``` jsx
<User isAdmin />
```

------------------------------------------------------------------------

# Passing Arrays as Props

Example:

``` jsx
function App() {
  const users = ["Umesh", "Rahul", "Amit"];

  return <UserList users={users} />;
}
```

Child component:

``` jsx
function UserList({ users }) {
  return (
    <ul>
      {users.map((user, index) => (
        <li key={index}>{user}</li>
      ))}
    </ul>
  );
}
```

------------------------------------------------------------------------

# Passing Objects as Props

Example:

``` jsx
function App() {
  const user = {
    name: "Umesh",
    age: 25
  };

  return <Profile user={user} />;
}
```

Child component:

``` jsx
function Profile({ user }) {
  return (
    <div>
      <h1>{user.name}</h1>
      <p>{user.age}</p>
    </div>
  );
}
```

------------------------------------------------------------------------

# Passing Functions as Props

Functions can also be passed as props.

Example:

Parent component:

``` jsx
function App() {

  const handleClick = () => {
    alert("Button clicked");
  };

  return <Button onClick={handleClick} />;
}
```

Child component:

``` jsx
function Button({ onClick }) {
  return <button onClick={onClick}>Click</button>;
}
```

------------------------------------------------------------------------

# Default Props

You can define default values.

Example:

``` jsx
function User({ name = "Guest" }) {
  return <h1>Hello {name}</h1>;
}
```

Usage:

``` jsx
<User />
```

Output:

    Hello Guest

------------------------------------------------------------------------

# Props Are Read-Only

Props **cannot be modified inside the child component**.

❌ Wrong

``` jsx
props.name = "Rahul";
```

✔ Correct approach:

Use state if you need to change data.

------------------------------------------------------------------------

# Props vs State

  Feature       Props              State
  ------------- ------------------ ----------------------
  Data source   Parent component   Inside component
  Mutable       No                 Yes
  Purpose       Pass data          Manage internal data

------------------------------------------------------------------------

# Real Example

``` jsx
function Product({ title, price }) {
  return (
    <div>
      <h2>{title}</h2>
      <p>Price: ₹{price}</p>
    </div>
  );
}
```

Usage:

``` jsx
<Product title="Laptop" price={50000} />
<Product title="Phone" price={20000} />
```

------------------------------------------------------------------------

# Best Practices

✔ Use destructuring for cleaner code\
✔ Keep props simple\
✔ Do not modify props\
✔ Use props to make components reusable\
✔ Pass functions for child-to-parent communication

------------------------------------------------------------------------

# Summary

Props allow React developers to:

✔ Pass data between components\
✔ Make components reusable\
✔ Build dynamic user interfaces\
✔ Maintain clean architecture

Props are one of the **core concepts of React development**.

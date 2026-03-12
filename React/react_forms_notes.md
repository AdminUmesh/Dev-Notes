# React Forms Notes

## 📌 What are Forms in React?

Forms are used to **collect user input** such as:

-   Login information
-   Registration data
-   Search queries
-   Feedback forms
-   Contact details

React handles forms using **controlled components**, where form data is
managed by **React state**.

------------------------------------------------------------------------

# Controlled Components

A **controlled component** means the form input value is controlled by
React state.

Example:

``` jsx
import { useState } from "react";

function LoginForm() {

  const [username, setUsername] = useState("");

  return (
    <div>

      <input
        type="text"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />

      <p>Username: {username}</p>

    </div>
  );
}
```

Flow:

    User Types
         ↓
    onChange Event
         ↓
    State Updated
         ↓
    Component Re-render
         ↓
    UI Updated

------------------------------------------------------------------------

# Handling Multiple Inputs

Example:

``` jsx
import { useState } from "react";

function Register(){

  const [form,setForm] = useState({
    name:"",
    email:""
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  return (
    <div>

      <input
        name="name"
        placeholder="Name"
        onChange={handleChange}
      />

      <input
        name="email"
        placeholder="Email"
        onChange={handleChange}
      />

      <p>{form.name}</p>
      <p>{form.email}</p>

    </div>
  );
}
```

------------------------------------------------------------------------

# Form Submission

Example:

``` jsx
function Contact(){

  const handleSubmit = (e) => {
    e.preventDefault();
    alert("Form submitted");
  };

  return (
    <form onSubmit={handleSubmit}>

      <input placeholder="Name" />

      <button type="submit">
        Submit
      </button>

    </form>
  );
}
```

`e.preventDefault()` prevents page reload.

------------------------------------------------------------------------

# Input Types in React Forms

  Input Type   Example
  ------------ -----------------------------
  Text         `<input type="text" />`
  Password     `<input type="password" />`
  Email        `<input type="email" />`
  Checkbox     `<input type="checkbox" />`
  Radio        `<input type="radio" />`
  Select       `<select>` dropdown
  Textarea     `<textarea>`

Example:

``` jsx
<textarea placeholder="Message"></textarea>
```

------------------------------------------------------------------------

# Select Dropdown Example

``` jsx
import { useState } from "react";

function Country(){

  const [country,setCountry] = useState("");

  return (

    <select onChange={(e)=>setCountry(e.target.value)}>

      <option value="">Select Country</option>
      <option value="India">India</option>
      <option value="USA">USA</option>

    </select>

  );
}
```

------------------------------------------------------------------------

# Checkbox Example

``` jsx
import { useState } from "react";

function Terms(){

  const [accepted,setAccepted] = useState(false);

  return (

    <div>

      <input
        type="checkbox"
        onChange={(e)=>setAccepted(e.target.checked)}
      />

      {accepted && <p>Terms accepted</p>}

    </div>

  );
}
```

------------------------------------------------------------------------

# Form Validation Example

Example:

``` jsx
function Login(){

  const [email,setEmail] = useState("");
  const [error,setError] = useState("");

  const handleSubmit = (e)=>{
    e.preventDefault();

    if(!email){
      setError("Email is required");
    }
  };

  return (
    <form onSubmit={handleSubmit}>

      <input
        type="email"
        onChange={(e)=>setEmail(e.target.value)}
      />

      <p>{error}</p>

      <button type="submit">Login</button>

    </form>
  );
}
```

------------------------------------------------------------------------

# React Hook Form (Popular Library)

Many React apps use **React Hook Form** for better performance.

Install:

``` bash
npm install react-hook-form
```

Example:

``` jsx
import { useForm } from "react-hook-form";

function Login(){

  const { register, handleSubmit } = useForm();

  const onSubmit = (data) => {
    console.log(data);
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>

      <input {...register("email")} />

      <input {...register("password")} type="password" />

      <button type="submit">Login</button>

    </form>
  );
}
```

Benefits:

-   Less code
-   Better performance
-   Easy validation
-   Cleaner form handling

------------------------------------------------------------------------

# Form Handling Flow

    User Input
         ↓
    onChange Event
         ↓
    State Updated
         ↓
    Validation
         ↓
    Form Submitted
         ↓
    API Call (Optional)

------------------------------------------------------------------------

# Best Practices

✔ Use controlled components\
✔ Validate inputs before submit\
✔ Keep form state organized\
✔ Use React Hook Form for large forms\
✔ Avoid unnecessary re-renders

------------------------------------------------------------------------

# Example Real Component

``` jsx
import { useState } from "react";

function Feedback(){

  const [message,setMessage] = useState("");

  const handleSubmit = (e)=>{
    e.preventDefault();
    alert(message);
  };

  return (

    <form onSubmit={handleSubmit}>

      <textarea
        onChange={(e)=>setMessage(e.target.value)}
      ></textarea>

      <button type="submit">
        Send Feedback
      </button>

    </form>

  );
}
```

------------------------------------------------------------------------

# Summary

React Forms allow developers to:

✔ Capture user input\
✔ Manage form state\
✔ Validate user data\
✔ Submit data to APIs

Forms are a **key part of building interactive web applications**.

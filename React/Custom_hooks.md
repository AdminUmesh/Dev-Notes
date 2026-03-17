# React Custom Hooks - Complete Notes

## 1. What are Custom Hooks?

Custom Hooks are reusable JavaScript functions in React that start with `use` and allow you to extract and reuse component logic.

👉 Example:
```jsx
function useCounter() {
  const [count, setCount] = useState(0);
  return { count, setCount };
}
```

---

## 2. Why Use Custom Hooks?

- Reuse logic across components
- Keep components clean and readable
- Avoid code duplication
- Better separation of concerns

---

## 3. Rules of Custom Hooks

- Must start with `use`
- Can call other hooks (useState, useEffect, etc.)
- Must follow React Hook rules

---

## 4. Basic Example

```jsx
import { useState } from "react";

function useCounter() {
  const [count, setCount] = useState(0);

  const increment = () => setCount(count + 1);
  const decrement = () => setCount(count - 1);

  return { count, increment, decrement };
}
```

Usage:
```jsx
function Counter() {
  const { count, increment, decrement } = useCounter();

  return (
    <>
      <h1>{count}</h1>
      <button onClick={increment}>+</button>
      <button onClick={decrement}>-</button>
    </>
  );
}
```

---

## 5. Custom Hook with useEffect

```jsx
import { useState, useEffect } from "react";

function useFetch(url) {
  const [data, setData] = useState(null);

  useEffect(() => {
    fetch(url)
      .then(res => res.json())
      .then(data => setData(data));
  }, [url]);

  return data;
}
```

---

## 6. Custom Hook with Cleanup

```jsx
function useTimer() {
  useEffect(() => {
    const timer = setInterval(() => {
      console.log("Running...");
    }, 1000);

    return () => clearInterval(timer);
  }, []);
}
```

---

## 7. Folder Structure Best Practice

```
src/
 ├── hooks/
 │    ├── useCounter.ts
 │    ├── useFetch.ts
 │    └── useAuth.ts
```

---

## 8. Advanced Example (Form Hook)

```jsx
function useForm(initialValues) {
  const [values, setValues] = useState(initialValues);

  const handleChange = (e) => {
    setValues({
      ...values,
      [e.target.name]: e.target.value,
    });
  };

  return { values, handleChange };
}
```

---

## 9. When NOT to Use Custom Hooks

- When logic is too simple
- When used only in one component
- When it adds unnecessary complexity

---

## 10. Interview Tips

- Custom hooks = reusable logic, not UI
- Helps follow DRY principle
- Improves maintainability
- Often used with API calls, forms, auth

---

## 11. Summary

- Custom hooks start with `use`
- Used to share logic across components
- Can use all React hooks inside
- Keeps code clean and scalable

---

Happy Coding 🚀

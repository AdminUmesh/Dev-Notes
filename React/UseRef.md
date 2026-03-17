## What is useRef?

`useRef` is a React Hook that allows you to:
- Store a mutable value
- Persist value between renders
- Access DOM elements directly

---

## Syntax

```javascript
const ref = useRef(initialValue);
```

---

## Key Features

- Does NOT cause re-render when updated
- Value stored in `.current`
- Persists across renders

---

## 1. Store Value Without Re-render

```javascript
import { useRef } from "react";

function Counter() {
  const count = useRef(0);

  const increase = () => {
    count.current += 1;
    console.log(count.current);
  };

  return <button onClick={increase}>Click</button>;
}
```

---

## 2. Access DOM Element

```javascript
import { useRef } from "react";

function InputFocus() {
  const inputRef = useRef(null);

  const focusInput = () => {
    inputRef.current.focus();
  };

  return (
    <>
      <input ref={inputRef} />
      <button onClick={focusInput}>Focus</button>
    </>
  );
}
```

---

## 3. Persist Value Between Renders

```javascript
const prevValue = useRef();

useEffect(() => {
  prevValue.current = value;
}, [value]);
```

---

## useRef vs useState

| Feature | useRef | useState |
|--------|--------|---------|
| Causes re-render | ❌ No | ✅ Yes |
| Stores value | ✅ Yes | ✅ Yes |
| Used for UI | ❌ No | ✅ Yes |
| Access DOM | ✅ Yes | ❌ No |

---

## When to Use useRef

- Store value without re-render
- Access DOM elements
- Store previous value
- Timers or intervals

---

## When NOT to Use

- For UI state
- When you need re-render

---

## Summary

- `useRef` stores value in `.current`
- Updating it does NOT re-render
- Useful for DOM and non-UI data

---

## Final Tip

Use:
- `useState` → for UI
- `useRef` → for background values

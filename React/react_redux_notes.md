# Redux Notes for React

## 📌 What is Redux?

Redux is a **state management library** used to manage **global
application state** in React applications.

It helps when multiple components need to share and update the same
data.

Example use cases: - Authentication state - Shopping cart - Theme
settings - User profile - Dashboard data

------------------------------------------------------------------------

# Why Redux is Used

Without Redux:

Component A ↓ props Component B ↓ props Component C

This is called **prop drilling**.

Redux solves this by using a **central store**.

        Redux Store
        /    |    \

ComponentA ComponentB ComponentC

All components can access shared data.

------------------------------------------------------------------------

# Core Concepts of Redux

Redux has three main parts:

  Concept    Description
  ---------- ------------------------
  Store      Global state container
  Actions    Describe what happened
  Reducers   Update the state

------------------------------------------------------------------------

# Redux Flow

User Action ↓ Dispatch Action ↓ Reducer Updates State ↓ Store Updates ↓
UI Re-renders

------------------------------------------------------------------------

# Installing Redux Toolkit

Modern React apps use **Redux Toolkit**.

Install:

``` bash
npm install @reduxjs/toolkit react-redux
```

------------------------------------------------------------------------

# Creating a Redux Store

Example:

``` javascript
import { configureStore } from "@reduxjs/toolkit";

export const store = configureStore({
  reducer: {}
});
```

------------------------------------------------------------------------

# Providing the Store to React

Wrap your app with `Provider`.

``` javascript
import { Provider } from "react-redux";
import { store } from "./store";

<Provider store={store}>
  <App />
</Provider>
```

Now all components can access Redux state.

------------------------------------------------------------------------

# Creating a Slice

Redux Toolkit uses **Slices**.

Example:

``` javascript
import { createSlice } from "@reduxjs/toolkit";

const counterSlice = createSlice({
  name: "counter",
  initialState: { value: 0 },
  reducers: {
    increment: (state) => {
      state.value += 1;
    },
    decrement: (state) => {
      state.value -= 1;
    }
  }
});

export const { increment, decrement } = counterSlice.actions;
export default counterSlice.reducer;
```

------------------------------------------------------------------------

# Adding Reducer to Store

``` javascript
import { configureStore } from "@reduxjs/toolkit";
import counterReducer from "./counterSlice";

export const store = configureStore({
  reducer: {
    counter: counterReducer
  }
});
```

------------------------------------------------------------------------

# Accessing State (useSelector)

``` javascript
import { useSelector } from "react-redux";

const count = useSelector((state) => state.counter.value);
```

This reads data from Redux store.

------------------------------------------------------------------------

# Updating State (useDispatch)

``` javascript
import { useDispatch } from "react-redux";
import { increment } from "./counterSlice";

const dispatch = useDispatch();

dispatch(increment());
```

------------------------------------------------------------------------

# Example React Component

``` javascript
import { useSelector, useDispatch } from "react-redux";
import { increment, decrement } from "./counterSlice";

function Counter(){

  const count = useSelector((state)=>state.counter.value);
  const dispatch = useDispatch();

  return (
    <div>
      <h1>{count}</h1>

      <button onClick={()=>dispatch(increment())}>
        Increase
      </button>

      <button onClick={()=>dispatch(decrement())}>
        Decrease
      </button>

    </div>
  );
}
```

------------------------------------------------------------------------

# Typical Redux Folder Structure

src │ ├── app │ └── store.js │ ├── features │ └── counter │ └──
counterSlice.js │ ├── components │ └── App.js

------------------------------------------------------------------------

# When to Use Redux

Use Redux when:

-   Multiple components need the same state
-   Application is large
-   State logic becomes complex
-   API data is shared across pages

For small apps, **React Context + useState** may be enough.

------------------------------------------------------------------------

# Advantages of Redux

✔ Centralized state\
✔ Predictable state updates\
✔ Easier debugging\
✔ DevTools support\
✔ Scalable architecture

------------------------------------------------------------------------

# Redux DevTools

Redux DevTools allow you to:

-   Inspect state
-   Track actions
-   Time travel debugging

Browser extension:
https://chrome.google.com/webstore/detail/redux-devtools

------------------------------------------------------------------------

# Redux vs Context API

  Feature       Redux        Context API
  ------------- ------------ --------------------
  Complexity    Higher       Simpler
  Best for      Large apps   Small apps
  DevTools      Yes          No
  Performance   Optimized    Can re-render more

------------------------------------------------------------------------

# Real World Example

Example global states managed by Redux:

User Authentication\
Shopping Cart\
Notifications\
Theme Settings\
Dashboard Data

------------------------------------------------------------------------

# Summary

Redux helps React developers:

✔ Manage global state\
✔ Avoid prop drilling\
✔ Maintain predictable state updates\
✔ Build scalable applications

Modern React apps typically use **Redux Toolkit** for cleaner and
simpler Redux code.

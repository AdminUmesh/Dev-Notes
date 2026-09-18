### Topics included:
4.  Context API\
6.  State Management (Redux)\
7.  Performance Optimization\
8.  Project Architecture

------------------------------------------------------------------------

# 4️⃣ Context API

Context API is used to **share global data without prop drilling**.

Example use cases:

-   Authentication
-   Theme
-   User data

Create context:

``` jsx
import { createContext } from "react";

export const UserContext = createContext();
```

Provider:

``` jsx
<UserContext.Provider value={user}>
  <App />
</UserContext.Provider>
```

Consume context:

``` jsx
import { useContext } from "react";

const user = useContext(UserContext);
```

------------------------------------------------------------------------

# 6️⃣ State Management (Redux)

Redux is used to manage **global application state**.

Install:

``` bash
npm install @reduxjs/toolkit react-redux
```

Create store:

``` jsx
import { configureStore } from "@reduxjs/toolkit";

export const store = configureStore({
  reducer: {}
});
```

Provider:

``` jsx
import { Provider } from "react-redux";

<Provider store={store}>
  <App />
</Provider>
```

Access state:

``` jsx
import { useSelector,useDispatch } from "react-redux";
```

Redux helps manage:

-   authentication
-   cart systems
-   large application state

------------------------------------------------------------------------

# 7️⃣ Performance Optimization

React provides several performance techniques.

### React.memo

Prevents unnecessary re-renders.

``` jsx
const MyComponent = React.memo(function MyComponent(props){
  return <div>{props.name}</div>;
});
```

### useMemo

Memoizes expensive calculations.

``` jsx
const value = useMemo(() => calculateExpensiveValue(data), [data]);
```

### useCallback

Prevents function recreation.

``` jsx
const handleClick = useCallback(() => {
  console.log("clicked");
}, []);
```

------------------------------------------------------------------------

# 8️⃣ Project Architecture

Typical **production React project structure**:

    src
    │
    ├── components
    ├── pages
    ├── hooks
    ├── context
    ├── redux
    ├── services
    ├── utils
    ├── assets
    ├── routes
    │
    ├── App.tsx
    └── main.tsx

**Explanation:**

  |Folder      | Purpose|
  |------------| ------------------|
  |components  | reusable UI|
  |pages       | full screens|
  |hooks       | custom hooks|
  |context     | global contexts|
  |redux       | redux store|
  |services    | API calls|
  |utils       | helper functions|
  |routes      | routing config|

------------------------------------------------------------------------

# Typical React Application Flow

    User Interaction
          ↓
    Event Triggered
          ↓
    State Update
          ↓
    API Call
          ↓
    Backend Response
          ↓
    UI Re-render

------------------------------------------------------------------------
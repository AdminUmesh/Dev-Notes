# React API Calling Notes (Fetch, Axios, RTK Query)

## 1. Using Fetch API

### Example
```javascript
useEffect(() => {
  fetch("https://api.example.com/users")
    .then((res) => res.json())
    .then((data) => console.log(data))
    .catch((err) => console.error(err));
}, []);
```

### Pros
- Built-in (no installation)
- Simple

### Cons
- No interceptors
- Manual error handling

---

## 2. Using Axios

### Install
```bash
npm install axios
```

### Example
```javascript
import axios from "axios";

useEffect(() => {
  axios.get("https://api.example.com/users")
    .then((res) => console.log(res.data))
    .catch((err) => console.error(err));
}, []);
```

### Pros
- Easy syntax
- Interceptors support
- Auto JSON parsing

### Cons
- Extra dependency

---

## 3. Using React Query (TanStack Query)

### Install
```bash
npm install @tanstack/react-query
```

### Example
```javascript
import { useQuery } from "@tanstack/react-query";

const fetchUsers = async () => {
  const res = await fetch("https://api.example.com/users");
  return res.json();
};

function Users() {
  const { data, isLoading, error } = useQuery({
    queryKey: ["users"],
    queryFn: fetchUsers,
  });

  if (isLoading) return <p>Loading...</p>;
  if (error) return <p>Error</p>;

  return <div>{JSON.stringify(data)}</div>;
}
```

### Pros
- Caching
- Auto refetch
- Loading & error states

### Cons
- Learning curve

---

## 4. Using RTK Query (Redux Toolkit)

### Install
```bash
npm install @reduxjs/toolkit react-redux
```

### Example
```javascript
import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

export const api = createApi({
  reducerPath: "api",
  baseQuery: fetchBaseQuery({ baseUrl: "https://api.example.com/" }),
  endpoints: (builder) => ({
    getUsers: builder.query({
      query: () => "users",
    }),
  }),
});

export const { useGetUsersQuery } = api;
```

### Usage
```javascript
function Users() {
  const { data, isLoading } = useGetUsersQuery();

  if (isLoading) return <p>Loading...</p>;

  return <div>{JSON.stringify(data)}</div>;
}
```

### Pros
- Built-in caching
- Redux integration
- Auto state management

### Cons
- Requires Redux setup

---

## Summary

| Method | Best For |
|--------|---------|
| Fetch | Small apps |
| Axios | Medium apps |
| React Query | Advanced data fetching |
| RTK Query | Large Redux apps |

---

## Final Tip
Choose based on project size:
- Small → Fetch
- Medium → Axios
- Large → React Query / RTK Query

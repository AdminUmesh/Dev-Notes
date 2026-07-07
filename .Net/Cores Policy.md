# CORS (Cross-Origin Resource Sharing)

## What is CORS?

CORS is a **browser security feature** that prevents a web page from making requests to a different **origin** (domain, protocol, or port) unless the target server explicitly allows it.

> **Important:** CORS is enforced only by browsers. Server-to-server requests are **not** affected.

### Example

Frontend:
```
https://example.com
```

API:
```
https://api.example.com
```

The browser blocks the request unless the API allows `https://example.com`.

---

## Why is CORS Important?

- 🔒 Prevents unauthorized cross-origin requests.
- 🛡️ Protects against malicious websites.
- ✅ Allows only trusted domains to access your API.

---

## Does CORS Apply to Server-Side Requests?

**No.**

CORS is a **browser security feature**.

Example:

```
Angular  ---> API      ❌ CORS Applies
.NET API ---> Another API   ✅ No CORS
```

---

# CORS in ASP.NET Core

By default, ASP.NET Core blocks cross-origin requests unless CORS is enabled.

## Step 1: Register CORS

```csharp
builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowAll", policy =>
    {
        policy.AllowAnyOrigin()
              .AllowAnyMethod()
              .AllowAnyHeader();
    });
});
```

---

## Step 2: Enable CORS Middleware

```csharp
var app = builder.Build();

app.UseCors("AllowAll");

app.MapControllers();

app.Run();
```

---

# CORS Methods

## AllowAnyOrigin()

Allows requests from **any domain**.

```csharp
policy.AllowAnyOrigin();
```

Example:

```
*
```

---

## AllowAnyMethod()

Allows all HTTP methods.

```csharp
policy.AllowAnyMethod();
```

Methods:

- GET
- POST
- PUT
- DELETE
- PATCH

---

## AllowAnyHeader()

Allows all request headers.

```csharp
policy.AllowAnyHeader();
```

---

# Restrict CORS (Recommended for Production)

Allow only trusted domains.

```csharp
builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowSpecificOrigins", policy =>
    {
        policy.WithOrigins(
                "https://yourfrontend.com",
                "https://another-frontend.com")
              .AllowAnyMethod()
              .AllowAnyHeader();
    });
});
```

---

# Interview Question

### Why do we use CORS?

CORS allows a browser to securely access resources from another origin while preventing unauthorized websites from making requests to your API.

---

# Key Points

- ✅ CORS = Browser security feature
- ✅ Server-to-server requests are NOT affected
- ✅ Configure CORS in ASP.NET Core using `AddCors()`
- ✅ Enable using `UseCors()`
- ✅ Use `WithOrigins()` in production
- ❌ Avoid `AllowAnyOrigin()` in production

---

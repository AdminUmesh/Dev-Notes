# Session 🔥
## Session Settings in Program.cs file

**(A) Register session services (before builder.Build())**
```c#
builder.Services.AddDistributedMemoryCache(); // where to store session (in RAM)
builder.Services.AddSession(options =>
{
    options.IdleTimeout = TimeSpan.FromMinutes(30); // session lifetime
});

```

**(B) Use session middleware (after UseRouting())**
```c#
// ✅ 2. Enable session in the pipeline
app.UseSession();

```

**Reason (simple):**

- AddDistributedMemoryCache() → where session data is stored (in RAM).

- AddSession() → tells ASP.NET Core “I want to use Session”.

- UseSession() → makes HttpContext.Session work in controllers.

- IdleTimeout = how long the session survives without any activity.

## How to Store & Get Session Values in ASP.NET Core MVC

**1. Set Session**
```c#
HttpContext.Session.SetString("MyName", "Umesh Kumar");
```

**2. Get Session**
```c#
var name = HttpContext.Session.GetString("MyName");
```

**3. Modify Session**
```c#
// Same method for add and modify:
HttpContext.Session.SetString("MyName", "Umesh Kumar");

// If "MyName" exists → it overwrites it.
// If it doesn’t exist → it creates it.
```

**4. Remove a single session key**
```c#
HttpContext.Session.Remove("MyName");
```

**5. Clear all session data**
```c#
HttpContext.Session.Clear();
```

# Cookies 🔥

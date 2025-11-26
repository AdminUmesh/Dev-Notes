
# Custom Middleware in ASP.NET Core — Full Detailed Notes

## **What is Custom Middleware?**

**Middleware** is a class that:
- Runs for **every request**
- Can **read, modify, stop, or forward** the request
- Can **modify or replace the response**
- Can execute code **before and after** the next middleware in the pipeline

It forms part of the **HTTP Request Pipeline**, meaning every incoming request and outgoing response flows through it.

---

## **Middleware Pipeline Flow**

```
Incoming Request
      ↓
 ┌──────────────────────────┐
 │   Custom Middleware 1    │  (Before _next)
 └──────────────────────────┘
      ↓ _next()
 ┌──────────────────────────┐
 │   Next Middleware/Controller │
 └──────────────────────────┘
      ↑
 (After _next)
 Response can be modified here
      ↑
Outgoing Response
```

---

## **Why Custom Middleware?**
| Purpose | Example |
|---------|---------|
| Centralized exception handling | Global try/catch |
| Logging | Track request/response |
| Performance monitoring | Execution time |
| Request validation | Check custom headers / block request |
| Response modification | Mask fields, encryption, replace text |

---

## **How to Create Custom Middleware**

### **1. Create a middleware class**
```csharp
public class RequestLoggingMiddleware
{
    private readonly RequestDelegate _next;

    public RequestLoggingMiddleware(RequestDelegate next)
    {
        _next = next;
    }

    public async Task InvokeAsync(HttpContext context)
    {
        Console.WriteLine($"Request: {context.Request.Method} {context.Request.Path}");

        await _next(context);  // Call the next middleware

        Console.WriteLine($"Response: {context.Response.StatusCode}");
    }
}
```

---

### **2. Register in Program.cs**
```csharp
app.UseMiddleware<RequestLoggingMiddleware>();
```

### **Order is important**
```csharp
app.UseRouting();
app.UseAuthentication();
app.UseAuthorization();
app.MapControllers();
```

---

## **Example: Exception Handling Middleware**
```csharp
public class ExceptionMiddleware
{
    private readonly RequestDelegate _next;

    public ExceptionMiddleware(RequestDelegate next)
    {
        _next = next;
    }

    public async Task InvokeAsync(HttpContext context)
    {
        try
        {
            await _next(context);
        }
        catch (Exception ex)
        {
            context.Response.StatusCode = 500;
            await context.Response.WriteAsync($"Error: {ex.Message}");
        }
    }
}
```
Use:
```csharp
app.UseMiddleware<ExceptionMiddleware>();
```

---

## **Example: Modify/Replace Response**
```csharp
public async Task InvokeAsync(HttpContext context)
{
    var originalStream = context.Response.Body;
    using var newStream = new MemoryStream();
    context.Response.Body = newStream;

    await _next(context);

    newStream.Seek(0, SeekOrigin.Begin);
    string response = await new StreamReader(newStream).ReadToEndAsync();

    string updatedResponse = response.Replace("Hello", "Namaste");

    var bytes = System.Text.Encoding.UTF8.GetBytes(updatedResponse);
    context.Response.Body = originalStream;
    await context.Response.Body.WriteAsync(bytes);
}
```

---

## **How to Practice in a Dummy Project**
### Recommended exercises
| Task | What You Learn |
|-------|------------------|
| Logging middleware | Before & after `_next()` |
| Exception middleware | Replace response & handle errors |
| Response modifying middleware | Read/write response body |
| Header validation | Stop pipeline early |
| Stopwatch execution timing | Performance measurement |

### Example blocking middleware
```csharp
if (!context.Request.Headers.ContainsKey("X-Key"))
{
    context.Response.StatusCode = 401;
    await context.Response.WriteAsync("Unauthorized");
    return; // stops pipeline
}
```

---

## **Common Interview-level Questions**
### ❓ What is difference between Middleware & Services?
| Middleware | Services |
|-----------|----------|
| Runs on every request | Runs only when requested |
| Controls pipeline | Provides utility/business logic |
| Can modify response | Cannot modify response |
| Added via `app.Use()` | Added via `builder.Services.Add()` |

### ❓ Why does order matter in middleware?
Because each middleware decides whether and when the next component executes.

### ❓ When to use custom middleware?
When common behavior must apply to all endpoints.

---

## **Summary**
✔ Middleware runs for every request  
✔ Can modify request & response  
✔ Can stop pipeline or allow forward execution  
✔ Executes logic before & after `_next(context)`  
✔ Excellent for cross‑cutting concerns like logging, error handling, security

---

## **Next Steps**
Practice creating:
- Logging middleware
- Exception middleware
- Response rewrite middleware
- Header validation middleware

Want full working sample project? Ask: **“give dummy project with 3 middlewares zipped”**

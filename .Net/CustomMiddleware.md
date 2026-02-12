
# Custom Middleware in ASP.NET Core

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

# Audit Log for every click
```c#
public class AuditMiddleware
{
    private readonly RequestDelegate _next;

    public AuditMiddleware(RequestDelegate next)
    {
        _next = next;
    }

    public async Task Invoke(HttpContext context)
    {
        var user = context.User?.Identity?.Name ?? "Anonymous";
        var path = context.Request.Path;
        var method = context.Request.Method;
        var time = DateTime.UtcNow;

        await _next(context); // execute API

        // log after execution
        AuditLogger.Log(new AuditEntry
        {
            UserId = user,
            Action = $"{method} {path}",
            Timestamp = time,
            StatusCode = context.Response.StatusCode
        });
    }
}

```
### Register Once
```c#
app.UseMiddleware<AuditMiddleware>();
```

#### What it logs automatically
- UserId
- API path
- HTTP method
- Payload (optional)
- Timestamp
- IP
- Result (success/failure)

# Handle Exception in SQL Server
```c#
public class ExceptionMiddleware
{
    private readonly RequestDelegate _next;
    private readonly ILogger<ExceptionMiddleware> _logger;

    public ExceptionMiddleware(RequestDelegate next, ILogger<ExceptionMiddleware> logger)
    {
        _next = next;
        _logger = logger;
    }

    public async Task InvokeAsync(HttpContext context)
    {
        try
        {
            await _next(context);
        }
        catch (Exception ex)
        {
            await HandleExceptionAsync(context, ex);
        }
    }

    private async Task HandleExceptionAsync(HttpContext context, Exception exception)
    {
        HttpStatusCode statusCode = HttpStatusCode.InternalServerError;
        string message = "An unexpected error occurred.";

        switch (exception)
        {
            // 🔹 Direct SQL Server errors (Dapper, ADO.NET)
            case SqlException sqlEx:
                statusCode = HttpStatusCode.BadRequest;
                message = GetSqlServerMessage(sqlEx);
                _logger.LogError(sqlEx, "SQL Server error");
                break;

            // 🔹 Timeout
            case TimeoutException timeoutEx:
                statusCode = HttpStatusCode.RequestTimeout;
                message = "The request timed out while processing.";
                _logger.LogError(timeoutEx, "Timeout error");
                break;

            // 🔹 Unauthorized access
            case UnauthorizedAccessException unauthEx:
                statusCode = HttpStatusCode.Unauthorized;
                message = "Unauthorized access.";
                _logger.LogWarning(unauthEx, "Unauthorized access");
                break;

            // 🔹 Bad input
            case ArgumentException argEx:
                statusCode = HttpStatusCode.BadRequest;
                message = argEx.Message;
                _logger.LogWarning(argEx, "Invalid argument");
                break;

            // 🔹 Everything else
            default:
                message = exception.Message;
                _logger.LogError(exception, "Unhandled exception");
                break;
        }

        var apiResponse = new APIResponse(message, statusCode, false, null);

        context.Response.StatusCode = (int)statusCode;
        context.Response.ContentType = "application/json";

        await context.Response.WriteAsync(
            JsonSerializer.Serialize(apiResponse)
        );
    }

    private static string GetSqlServerMessage(SqlException sqlEx)
    {
        return sqlEx.Number switch
        {
            2627 => "Duplicate key error: A record with the same key already exists.",
            2601 => "Duplicate index error: A record with the same value already exists.",
            547 => "Constraint violation: Related record not found.",
            -2 => "Database timeout: The operation took too long to complete.",
            _ => $"SQL Server Error ({sqlEx.Message})"
        };
    }
}
```

### Register Once
```c#
app.UseMiddleware<ExceptionMiddleware>();
```

#### what do this
- This will return exact error for mysql (which mannage here). **like:** `A record with the same value already exists.`
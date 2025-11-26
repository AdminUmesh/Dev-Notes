# ASP.NET Core `Program.cs` – Complete Deep Notes

These notes explain, in depth:

- What `Program.cs` does in ASP.NET Core
- What **services** are (`builder.Services.*`)
- What **middleware** is (`app.Use*`)
- How the **request pipeline** works
- Explanation of the specific lines you shared:

  ```csharp
  builder.Services.AddResolvers();

  var app = builder.Build();

  // Configure the HTTP request pipeline.
  app.UseMiddleware<ExceptionMiddleware>();
  app.UseRouting();
  app.UseCors("MyAllowOrigins");
  app.UseCookiePolicy();
  app.UseSwagger();
  app.UseSwaggerUI();
  app.UseAuthentication();
  app.UseHttpsRedirection();
  app.UseAuthorization();
  //app.UseMiddleware<EncryptionMiddleware>();
  app.UseStaticFiles();
  app.MapHub<NotificationHub>("/v1/api/notifications").RequireCors("MyAllowOrigins");
  app.MapControllers();
  app.Run();
  ```

---

## 1. What is `Program.cs`?

`Program.cs` is the **entry point** of your ASP.NET Core application.

It is responsible for:

1. Creating a **builder** (`WebApplicationBuilder`)
2. Registering **services** (via `builder.Services`)
3. Building the app (`var app = builder.Build();`)
4. Configuring the **middleware pipeline** (via `app.UseXxx()`)
5. Mapping **endpoints** (controllers, SignalR hubs, minimal APIs, etc.)
6. Starting the web server (`app.Run();`)

In .NET 6 and later (minimal hosting model), a typical `Program.cs` looks like this:

```csharp
var builder = WebApplication.CreateBuilder(args);

// 1. Register services
builder.Services.AddControllers();
// ... other services

var app = builder.Build();

// 2. Configure middleware
app.UseRouting();
app.UseAuthentication();
app.UseAuthorization();

// 3. Map endpoints
app.MapControllers();

// 4. Run
app.Run();
```

---

## 2. The Two Main Parts of `Program.cs`

### 2.1 Service Registration (`builder.Services`)

This is where you register **dependencies** your app needs, using Dependency Injection (DI).

```csharp
var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers();
builder.Services.AddDbContext<AppDbContext>();
builder.Services.AddScoped<IUserService, UserService>();
builder.Services.AddAuthentication();
builder.Services.AddCors();
// etc.
```

**Key idea:**

- `builder.Services` is an `IServiceCollection`
- You **add** services to it
- Later, ASP.NET Core will **resolve** these services when needed

### 2.2 App / Middleware Configuration (`app.Use*`, `app.Map*`)

After you call:

```csharp
var app = builder.Build();
```

You configure:

- The **HTTP request pipeline** using `app.UseXxx()`
- The **endpoints** using `app.MapXxx()`

Example:

```csharp
app.UseRouting();
app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();
app.Run();
```

---

## 3. What are Services?

**Services** are classes registered in the DI container and resolved when needed.

Examples:

- Framework services:
  - `AddControllers()` – MVC / API controllers
  - `AddDbContext<>()` – Entity Framework Core
  - `AddAuthentication()`, `AddAuthorization()`
  - `AddCors()`, `AddSwaggerGen()`
- Custom services:
  - `IUserService`, `IEmailService`, repositories, etc.
- 3rd-party services:
  - MediatR, AutoMapper, etc.

### 3.1 Service Lifetimes

When you register services, you choose a **lifetime**:

```csharp
builder.Services.AddSingleton<IMyService, MyService>(); // one instance for entire app
builder.Services.AddScoped<IMyService, MyService>();    // one instance per HTTP request
builder.Services.AddTransient<IMyService, MyService>(); // new instance every time it is requested
```

| Lifetime    | When created / reused                                       |
|------------|--------------------------------------------------------------|
| Singleton  | First time it is requested, reused for entire application    |
| Scoped     | Once per HTTP request                                        |
| Transient  | New instance every time it is injected                        |

**Important:**  
Services **do not automatically run on every request**. They run only when **someone asks for them**, such as:

- A **controller constructor**
- Another **service**
- A **middleware** that has the service injected

---

## 4. What is Middleware?

**Middleware** is a component that:

- Is part of the HTTP request pipeline
- Runs **for every request**
- Can:
  - read/modify the incoming **HttpContext**
  - decide whether to pass the request to the **next** middleware
  - read/modify the **response** on the way back

### 4.1 Middleware Shape

A middleware typically looks like this:

```csharp
public class MyMiddleware
{
    private readonly RequestDelegate _next;

    public MyMiddleware(RequestDelegate next)
    {
        _next = next;
    }

    public async Task InvokeAsync(HttpContext context)
    {
        // Code before next middleware (request)
        Console.WriteLine("Before");

        await _next(context);  // Call the next middleware

        // Code after next middleware (response)
        Console.WriteLine("After");
    }
}
```

You register it in `Program.cs`:

```csharp
app.UseMiddleware<MyMiddleware>();
```

### 4.2 Middleware Order is Critical

Order matters a lot. Example:

```csharp
app.UseRouting();
app.UseAuthentication();
app.UseAuthorization();
app.MapControllers();
```

Request flows like:

1. `UseRouting`
2. `UseAuthentication`
3. `UseAuthorization`
4. `MapControllers` (endpoint)
5. Controller action executes

If the order is wrong, things can fail (for example: authorization before authentication, etc.).

---

## 5. Your `Program.cs` Snippet – Line by Line

You showed:

```csharp
builder.Services.AddResolvers();

var app = builder.Build();

// Configure the HTTP request pipeline.
app.UseMiddleware<ExceptionMiddleware>();
app.UseRouting();
app.UseCors("MyAllowOrigins");
app.UseCookiePolicy();
app.UseSwagger();
app.UseSwaggerUI();
app.UseAuthentication();
app.UseHttpsRedirection();
app.UseAuthorization();
//app.UseMiddleware<EncryptionMiddleware>();
app.UseStaticFiles();
app.MapHub<NotificationHub>("/v1/api/notifications").RequireCors("MyAllowOrigins");
app.MapControllers();
app.Run();
```

Let’s break this down.

---

## 6. `builder.Services.AddResolvers();`

This is **not a standard ASP.NET Core method**.  
It is most likely a **custom extension method** defined by you or your team/library.

Typical pattern:

```csharp
public static class ServiceCollectionExtensions
{
    public static IServiceCollection AddResolvers(this IServiceCollection services)
    {
        // Example: register resolver classes, handlers, etc.
        services.AddScoped<IMyResolver, MyResolver>();
        // More registrations...

        return services;
    }
}
```

Then you call it in `Program.cs`:

```csharp
builder.Services.AddResolvers();
```

### What it usually means

- "Resolvers" could be:
  - GraphQL resolvers
  - Query/Command handlers (like CQRS)
  - Some custom abstraction for handling business logic

**Key point:**  
`AddResolvers()` is just a **clean way** to group multiple service registrations into one method.

Example version (for understanding only):

```csharp
public static class ServiceCollectionExtensions
{
    public static IServiceCollection AddResolvers(this IServiceCollection services)
    {
        // Example registrations
        services.AddScoped<IUserResolver, UserResolver>();
        services.AddScoped<IOrderResolver, OrderResolver>();

        return services;
    }
}
```

So your line:

```csharp
builder.Services.AddResolvers();
```

means: **“Register all my resolver services into DI”**.

---

## 7. `var app = builder.Build();`

This line:

```csharp
var app = builder.Build();
```

- Uses everything you've configured in `builder` (services, configuration, logging, etc.)
- Creates a `WebApplication` instance (`app`)
- Prepares the DI container and pipeline configuration

After this point, you **stop adding services** and you **start configuring middleware and endpoints**.

---

## 8. Configuring the HTTP Request Pipeline

Now your pipeline:

```csharp
app.UseMiddleware<ExceptionMiddleware>();
app.UseRouting();
app.UseCors("MyAllowOrigins");
app.UseCookiePolicy();
app.UseSwagger();
app.UseSwaggerUI();
app.UseAuthentication();
app.UseHttpsRedirection();
app.UseAuthorization();
//app.UseMiddleware<EncryptionMiddleware>();
app.UseStaticFiles();
app.MapHub<NotificationHub>("/v1/api/notifications").RequireCors("MyAllowOrigins");
app.MapControllers();
app.Run();
```

We’ll go one by one.

### 8.1 `app.UseMiddleware<ExceptionMiddleware>();`

Custom **exception handling middleware**.

Typical usage:

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
            // Log exception
            // Set proper response code and body
            context.Response.StatusCode = 500;
            await context.Response.WriteAsJsonAsync(new { message = "Something went wrong" });
        }
    }
}
```

- Placing it **at the top** of the pipeline is good because it can catch exceptions from everything below it.

---

### 8.2 `app.UseRouting();`

Enables **endpoint routing**.

- It matches the incoming request path (URL) with the route table (controllers/endpoints).
- It doesn’t yet execute the endpoint; it just decides *which* endpoint should handle the request.

This needs to happen **before**:

- Authentication/Authorization (so they know which endpoint / policies to apply)
- `MapControllers` or other `Map*` endpoint mappings

---

### 8.3 `app.UseCors("MyAllowOrigins");`

Enables **CORS middleware** with the named policy `"MyAllowOrigins"`.

This uses configuration like:

```csharp
builder.Services.AddCors(options =>
{
    options.AddPolicy("MyAllowOrigins", policy =>
    {
        policy.WithOrigins("http://localhost:4200")
              .AllowAnyHeader()
              .AllowAnyMethod();
    });
});
```

Placing CORS after routing but before endpoints is typical, especially if you want CORS headers in responses for API endpoints.

---

### 8.4 `app.UseCookiePolicy();`

Applies a **cookie policy** to incoming and outgoing cookies.

You normally configure options with:

```csharp
builder.Services.Configure<CookiePolicyOptions>(options =>
{
    options.MinimumSameSitePolicy = SameSiteMode.Lax;
    options.HttpOnly = HttpOnlyPolicy.Always;
    // etc.
});
```

This middleware:

- Can enforce rules for all cookies (like SameSite, HttpOnly, Secure, etc.)
- Used often when you care about privacy & regulations

---

### 8.5 `app.UseSwagger();` and `app.UseSwaggerUI();`

These two go together:

#### `app.UseSwagger();`

- Exposes the **Swagger/OpenAPI JSON** at a URL (usually `/swagger/v1/swagger.json`).
- Used by tools & SwaggerUI to understand your API definitions.

#### `app.UseSwaggerUI();`

- Serves the interactive Swagger UI page in the browser (usually `/swagger`).
- Lets you test APIs directly from a web page.

You also need services:

```csharp
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
```

Often you wrap these in an environment check (only enable in Development).

---

### 8.6 `app.UseAuthentication();`

Adds **Authentication Middleware** to the pipeline.

- For each request, it tries to **authenticate** the user
- It uses services configured by:

  ```csharp
  builder.Services
      .AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
      .AddJwtBearer(options => { ... });
  ```

- If the request has a valid JWT (or cookie, etc.), it sets:

  ```csharp
  HttpContext.User = ClaimsPrincipal
  ```

**Important:** Authentication should happen **before** authorization.

---

### 8.7 `app.UseHttpsRedirection();`

Redirects HTTP requests to HTTPS.

- If a request comes on `http://`, it returns a **302/307 Redirect** to `https://`
- Should ideally be placed early in the pipeline, but after exception handling.

In your order, it comes after `UseAuthentication`. In many examples you’ll see it earlier, but it can still work.

---

### 8.8 `app.UseAuthorization();`

Adds **Authorization Middleware**.

- Uses `HttpContext.User` (set by Authentication)
- Checks `[Authorize]` attributes
- Enforces policies/roles

If authorization fails:

- It returns **401** (unauthenticated) or **403** (forbidden)

**Order is important:**

```csharp
app.UseAuthentication();
app.UseAuthorization();
```

---

### 8.9 (Commented) `app.UseMiddleware<EncryptionMiddleware>();`

This is another **custom middleware**, currently disabled.

It might be intended to:

- Decrypt incoming request bodies
- Encrypt outgoing responses
- Apply extra security features

If enabled:

```csharp
app.UseMiddleware<EncryptionMiddleware>();
```

it would run for every request, like your `ExceptionMiddleware`.

---

### 8.10 `app.UseStaticFiles();`

Enables serving **static files** like:

- `.html`, `.css`, `.js`
- images (`.png`, `.jpg`, etc.)
- from `wwwroot` folder (by default)

Example: request `/images/logo.png` will be served from `wwwroot/images/logo.png` if it exists.

This is important if your API also serves a web frontend or files.

---

### 8.11 SignalR Hub Mapping – `app.MapHub<NotificationHub>(...)`

```csharp
app.MapHub<NotificationHub>("/v1/api/notifications")
   .RequireCors("MyAllowOrigins");
```

This line:

- Maps a **SignalR hub** at the URL `/v1/api/notifications`
- The hub type is `NotificationHub` (you must have a class like `public class NotificationHub : Hub { ... }`)
- `.RequireCors("MyAllowOrigins")` ensures that this hub uses the CORS policy `"MyAllowOrigins"`

SignalR is used for **real-time communication** (live notifications, chat, live dashboards).

Clients (like Angular) will connect using:

```ts
// Example in TS
const hubConnection = new signalR.HubConnectionBuilder()
  .withUrl("https://your-api.com/v1/api/notifications")
  .build();
```

---

### 8.12 `app.MapControllers();`

This maps controller routes.

- It uses routing attributes like `[Route("api/[controller]")]` and `[HttpGet("x")]`, etc.
- It’s where API endpoints become reachable

**Important:**

- This should be **after** `UseRouting()`
- It acts as an **endpoint** in the routing system

Request flow:

1. `UseRouting` – determines endpoint
2. Auth / CORS / etc.
3. `MapControllers` – executes matched controller action

---

### 8.13 `app.Run();`

Starts the app:

- Starts Kestrel web server
- Begins listening for HTTP requests
- This is always **last** in the pipeline

Nothing after `app.Run()` will execute.

---

## 9. How Everything Works Together for a Request

Let’s say a request comes from Angular:  

`GET /api/products` with JWT token and from allowed origin.

Pipeline order in your app:

1. `ExceptionMiddleware`
2. `UseRouting`
3. `UseCors("MyAllowOrigins")`
4. `UseCookiePolicy`
5. `UseSwagger` / `UseSwaggerUI` (for swagger paths)
6. `UseAuthentication`
7. `UseHttpsRedirection`
8. `UseAuthorization`
9. `UseStaticFiles` (if static file path)
10. `MapHub<NotificationHub>` (if SignalR path)
11. `MapControllers` (if controller path)

For `/api/products`:

- Exception middleware wraps everything
- Routing figures out which controller/action
- CORS ensures correct headers
- Authentication reads JWT, sets `HttpContext.User`
- Authorization checks `[Authorize]` (if any)
- Finally, the controller action executes
- If an exception occurs, `ExceptionMiddleware` catches it at the top

---

## 10. Other Important Things (Often in `Program.cs` but Not Shown)

These are commonly present and good to know:

### 10.1 Configuration

```csharp
var builder = WebApplication.CreateBuilder(args);

// Access configuration:
var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");
var someKey = builder.Configuration["SomeSection:SomeKey"];
```

Used to load values from `appsettings.json`, `appsettings.Development.json`, environment variables, etc.

### 10.2 Logging

Logging is built-in and configured in `Program.cs`:

```csharp
builder.Logging.ClearProviders();
builder.Logging.AddConsole();
```

You inject `ILogger<MyClass>` into controllers/services and log events.

### 10.3 Environments (Development / Production)

You can change behavior based on environment:

```csharp
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}
else
{
    app.UseExceptionHandler("/Error");
    app.UseHsts();
}
```

### 10.4 Database Migration at Startup (Optional)

Some apps auto-apply migrations:

```csharp
using (var scope = app.Services.CreateScope())
{
    var db = scope.ServiceProvider.GetRequiredService<AppDbContext>();
    db.Database.Migrate();
}
```

---

## 11. Super Short Summary

- `builder.Services.*` → register **services** (dependencies, configurations, handlers)
- `builder.Services.AddResolvers()` → custom extension method to register your **resolver classes** in DI
- `var app = builder.Build();` → create the app with DI + configuration
- `app.UseXxx()` → add **middleware** (runs for every request)
- `app.MapXxx()` → define **endpoints** (controllers, hubs, minimal APIs)
- Middleware order is **very important** (especially routing, CORS, auth)
- `app.Run()` → start the web server, app is now live

This file is meant as a **deep reference** so you can open it anytime while working with `Program.cs` and quickly recall what each part does.
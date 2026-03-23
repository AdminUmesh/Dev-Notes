### Pipeline

The request pipeline in .NET is a sequence of middleware components configured using app.Use and app.Map that process an incoming HTTP request and generate a response in order.

`Request pipeline = ordered middleware that handles request and response.`

### Middleware

Middleware is a component in ASP.NET Core that handles HTTP requests and responses in a pipeline, executing in the order they are added.

`Middleware = code that processes request and response in sequence.`

**Example**
 Middleware components like `app.UseRouting()`, `app.UseAuthentication()`, and `app.UseSession()` process requests step-by-step in the request pipeline and can perform tasks such as logging, exception handling, authentication, authorization, serving static files, and more.

 ```c#
 // All below starting with app.Use are middlewares
  app.UseRouting()
  app.UseAuthentication()
  app.UseSession()
  app.UseHttpsRedirection()
 ```

### Difference: Use vs Run vs Map
**1. app.Use()**
Middleware that can call the next middleware

- Has access to next()
- Can continue pipeline OR stop it
- Most commonly used

**Example:**
```c#
app.Use(async (context, next) =>
{
    Console.WriteLine("Before");
    await next(); // passes to next middleware
    Console.WriteLine("After");
});
```

**2. app.Run()**

Terminal middleware (ends the pipeline)

- Does NOT call next middleware
- Stops execution here

**Example:**
```c#
app.Run(async context =>
{
    await context.Response.WriteAsync("Hello World");
});

// 👉 No middleware runs after this
```
 
**3. app.Map()**

Branch the pipeline based on URL path

- Creates a separate pipeline
- Runs only for specific route

**Example:**
```c#
app.Map("/admin", adminApp =>
{
    adminApp.Run(async context =>
    {
        await context.Response.WriteAsync("Admin Page");
    });
});

// 👉 Only works when URL = /admin
```

 ## Services
 In ASP.NET Core, services are components that are used to perform specific tasks in your application, such as managing authentication, handling sessions, logging, and more. 

 `These services are registered in the dependency injection container, and you can access them throughout the application via constructor injection.`

**all below starting with builder.Services.Add is Services**
 ```C#
 // this is a Service
 builder.Services.AddAuthentication("Cookies")
    .AddCookie(options =>
    {
        options.Cookie.HttpOnly = true;
        options.Cookie.IsEssential = true;  
        options.LoginPath = "/Account/Login";
        options.LogoutPath = "/Account/Logout";
        options.ExpireTimeSpan = TimeSpan.FromMinutes(30);
    });

// this is another service
    builder.Services.AddSession(options =>
{
    options.IdleTimeout = TimeSpan.FromMinutes(30); // Set session timeout
});

// this is also a service
builder.Services.AddDistributedMemoryCache(); // To store session in memory
```

#### Relationship between Services and Middleware
Middleware and Services Are Part of the Request-Response Pipeline:

#### Can Middleware Work Without a Service?

Yes, some simple middleware can work without relying on any services. These middlewares are typically static in nature and do not require dynamic configuration or interaction with other application components.

**Example**
- Static File Middleware
```c#
app.UseStaticFiles();

//This middleware serves static files (like images, CSS, JS) from the wwwroot directory.
```

 - HTTPS Redirection Middleware

 ```c#
 app.UseHttpsRedirection();

 // his middleware simply redirects HTTP requests to HTTPS.
 ```

#### Can Services Work Without a Middleware?
Yes, many services can work independently of middleware, particularly background services, data access services, and other infrastructure-related services that don’t directly interact with the HTTP request pipeline.

**Examples of Services That Can Work Without Middleware**

1. DbContext (or other database services) can be used independently from middleware. You can inject and use services like DbContext into controllers.

```c#
public class HomeController : Controller
{
    private readonly MyDbContext _dbContext;
    public HomeController(MyDbContext dbContext)
    {
        _dbContext = dbContext;
    }

    public IActionResult Index()
    {
        var data = _dbContext.Users.ToList();
        return View(data);
    }
}
```
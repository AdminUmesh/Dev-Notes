# Controllers
Controllers are used for handling HTTP requests, (not business logic)

1. Accept an HTTP request (from browser, Postman, etc.)

1. Talk to services

1. Return a result (View, JSON, etc.)

- `It becomes tightly coupled
If Controller A depends on Controller B’s method:`


# Services
service is a class that contains reusable business logic or data.

**For example:** Sending emails, processing payments, generating reports, etc.

### Without Services?
You can also write the logic inside the controller, but it becomes messy if the project grows.

#### Reusability
- Controller logic is not reusable.
- Service logic is reusable in many places.

#### Testability
- Controllers are tightly tied to HTTP (browser, API).
- Services are just logic — easy to test.

## Step-by-Step: Creating and Using a Service in .NET Core MVC

### **Step 1:** Create a Services Folder (manually)

### **Step 2:** Create a Service Interface and Class

**Interface (optional but best practice)**
Inside Services, add a new file: IEmailService.cs

```csharp
namespace YourProject.Services
{
    public interface IEmailService
    {
        void SendWelcomeEmail(string toEmail, string name, string password);
    }
}
```

**Add another file: EmailService.cs**

### **Step 3:** Register the Service in Program.cs

```csharp
builder.Services.AddScoped<IEmailService, EmailService>();
```

This tells .NET: “Whenever I ask for IEmailService, give me EmailService.”

### **Step 4:** Use in a Controller
```csharp
using YourProject.Services;

public class AccountController : Controller
{
    private readonly IEmailService _emailService;

    public AccountController(IEmailService emailService)
    {
        _emailService = emailService;  // dependency injection
    }
}
```

### Benefits of Doing This

-  Code is reusable
-  Controller stays clean
-  Easy to test
-  Easy to change email provider in future

# Dependency Injection
Dependency Injection (DI) is a design pattern where a class receives its required objects (dependencies) from the DI container rather than creating them using `new`

`Dependency Injection is a design pattern used to achieve loose coupling between classes.`

### DI Registration in Program.cs
```c#
builder.Services.AddScoped<IEmailService, EmailService>();
// `It tells .NET: "If anyone needs an IEmailService, give them an instance of EmailService.`

builder.Services.AddSingleton<IRepo, Repo>();
builder.Services.AddTransient<ILog, Logger>();

```

### Use the interface in your controller:

```csharp
public class AccountController : Controller
{
    private readonly IEmailService _emailService;

    // Constructor Injection — THIS is where DI happens
    public AccountController(IEmailService emailService)
    {
        _emailService = emailService;
    }

    public IActionResult Register()
    {
        _emailService.SendEmail(...); // You can now use the email logic
        return View();
    }
}
```

#### Without DI (Tight Coupling):
A class creates the object it needs.

- Classes depend on specific implementations.
- Hard to test (you can't easily replace real classes with test versions).
- Hard to change (if Engine changes, Car also needs to change).
- Code is less flexible and reusable.

#### With DI (Loose Coupling):
The object is passed to the class, usually through constructor, method, or property.

- Classes depend on abstractions (like interfaces), not implementations.
- Easy to test (you can inject fake/mock classes).
- Easy to change or upgrade parts (replace PetrolEngine with ElectricEngine).
- Promotes clean architecture (especially in large projects).

## Why Use Dependency Injection (DI)?
**1. Loose Coupling**
**2. Easier Testing**
**3. Reusability**

**Example:** DataAccess can work with MSSQL, MySQL, SQLite—no change needed.

**4. Follows SOLID Principles**
DI helps achieve D - Dependency Inversion Principle.
`High-level modules should not depend on low-level modules. Both should depend on abstractions.`

### Proccess to achieve DI in API
| Step | What You Do                                   |
|------|-----------------------------------------------|
| 1    | Define an interface (e.g., `IMessageService`) |
| 2    | Implement it in Class          |
| 3    | Register it in `Program.cs`                   |
| 4    | Inject it into a controller or service  view Constructor and Method      |

---

### .NET Core DI Lifetimes: Singleton vs Scoped vs Transient

**Lifetime Summary**

| Lifetime   | Instance Created     | Scope                    | Shared Across Requests |
|------------|----------------------|--------------------------|-------------------------|
| Transient  | Every time requested | Short-lived (per usage) | ❌ No                  |
| Scoped     | Once per request     | Per HTTP request         | ✅ Yes (within request) |
| Singleton  | Once per app         | App-wide                 | ✅ Yes (globally)       |

# Tight Coupling
Tightly coupled code means one class or component directly depends on another class's concrete implementation. If you change one thing, it can break many other parts.

**Example:**
```csharp
public class NotificationService
{
    private EmailService emailService = new EmailService();  // tightly coupled

    public void Send(string message)
    {
        emailService.SendEmail(message);
    }
}
```

### Problem:

- Hard to test (you always need real EmailService).

- Not flexible or reusable.

# Loose Coupling
Loosely coupled code uses abstractions (like interfaces). Classes depend on interfaces, not concrete implementations.

**Example using Interface + Dependency Injection:**
```csharp
public interface INotification
{
    void Send(string message);
}

public class EmailService : INotification
{
    public void Send(string message)
    {
        Console.WriteLine("Sending Email: " + message);
    }
}

public class NotificationService
{
    private readonly INotification _notifier;

    public NotificationService(INotification notifier)  // loose coupling
    {
        _notifier = notifier;
    }

    public void Notify(string message)
    {
        _notifier.Send(message);
    }
}
```

**Now you can:**
Replace EmailService with SMSService or PushNotificationService.

# 1. Why Use Dependency Injection Instead of `new`

## Using `new`

``` csharp
public class OrderService
{
    private readonly EmailService _emailService = new EmailService();
}
```

Works, but introduces several problems.

### Problem 1: Tight Coupling

`OrderService` is permanently tied to `EmailService`.

Replacing it with another implementation requires code changes.

------------------------------------------------------------------------

### Problem 2: Constructor Dependency Explosion

Initially:

``` csharp
public class EmailService
{
}
```

Later:

``` csharp
public class EmailService
{
    public EmailService(
        ILogger<EmailService> logger,
        IConfiguration configuration,
        HttpClient httpClient,
        AppDbContext db)
    {
    }
}
```

Every `new EmailService(...)` must now provide all dependencies.

With DI:

``` csharp
builder.Services.AddScoped<EmailService>();
```

The DI container automatically resolves constructor dependencies.

------------------------------------------------------------------------

### Problem 3: Lifetime Management

Without DI:

``` text
UserService --------> new DatabaseService()
OrderService -------> new DatabaseService()
ProductService -----> new DatabaseService()
```

Three different objects.

With DI:

``` csharp
builder.Services.AddSingleton<DatabaseService>();
```

All services share the same instance.

------------------------------------------------------------------------

### Problem 4: Unit Testing

Without DI:

``` csharp
private EmailService email = new EmailService();
```

Calling `PlaceOrder()` may send a real email.

With DI:

``` csharp
public OrderService(IEmailService emailService)
```

Inject a fake or mock implementation during testing.

------------------------------------------------------------------------

### Problem 5: Configuration Management

Without DI:

``` csharp
new EmailService(
    "smtp.gmail.com",
    587,
    "username",
    "password");
```

Repeated everywhere.

With DI, configuration is injected automatically.

------------------------------------------------------------------------

**Comparison**

| Using `new` | Using DI |
|-------------|----------|
| Tight coupling | Loose coupling |
| Manual object creation | DI container creates objects |
| Hard to replace implementation | Easy to replace implementation |
| Difficult to unit test | Easy to mock and test |
| No lifetime management | Supports **Singleton**, **Scoped**, and **Transient** lifetimes |
| Repeated object creation | Centralized object creation |
| Constructor changes affect many classes | Usually only DI registration changes |


# 2. Why an Interface DI is Better than a Concrete Class DI

## DI without an interface

```csharp
builder.Services.AddScoped<EmailService>();
```

``` csharp
public class OrderService
{
    private readonly EmailService _emailService;

    public OrderService(EmailService emailService)
    {
        _emailService = emailService;
    }
}
```


If `EmailService` is replaced with `GmailService`, every class that
depends on `EmailService` must be modified.

```csharp
builder.Services.AddScoped<GmailService>();
```

```bash
- private readonly EmailService _emailService;  to
+ private readonly GmailService _emailService;

- public OrderService(EmailService emailService) to
+ public OrderService(GmailService emailService)
```

**Example:** OrderService, UserService, PaymentService, NotificationService, ReportService

Each constructor and field type must change.

## DI With an Interface

``` csharp
public interface IEmailService
{
    void Send();
}

public class EmailService : IEmailService
{
    public void Send() { }
}

public class GmailService : IEmailService
{
    public void Send() { }
}
```
**Business classes depend only on the interface:**

``` csharp
public class OrderService
{
    private readonly IEmailService _emailService;

    public OrderService(IEmailService emailService)
    {
        _emailService = emailService;
    }
}
```

### Only the DI registration changes:

``` csharp
builder.Services.AddScoped<IEmailService, EmailService>();

// Later
builder.Services.AddScoped<IEmailService, GmailService>();
```

No business class needs to change.

### Interview Points

-   Loose coupling
-   Easy to replace implementations
-   Follows Dependency Inversion Principle (DIP)
-   Easier unit testing
-   Better maintainability
-   Business code depends on abstraction, not implementation

------------------------------------------------------------------------

# 3. If DI without an interface is still tightly coupled, then why do we use it?

They are similar in one way, but **not the same**.

### Comparison

 | Scenario | Uses DI? | Tight Coupling? | Need to change consumer if implementation changes? |
|----------|:--------:|:---------------:|:--------------------------------------------------:|
| `new EmailService()` | ❌ | ✅ | ✅ |
| DI with `EmailService` (no interface) | ✅ | ✅ | ✅ |
| DI with `IEmailService` | ✅ | ❌ | ❌ Usually No |


## Case 1: Using `new`

``` csharp
public class OrderService
{
    private readonly EmailService _email = new EmailService();
}
```

### Problems

-   `OrderService` creates the dependency itself.
-   If `EmailService` later requires constructor parameters, every
    `new EmailService(...)` must be updated.
-   No lifetime management (Singleton / Scoped / Transient).
-   Difficult to mock during unit testing.
-   Object creation is scattered throughout the application.

------------------------------------------------------------------------

## Case 2: DI without an Interface

``` csharp
builder.Services.AddScoped<EmailService>();
```

``` csharp
public class OrderService
{
    private readonly EmailService _email;

    public OrderService(EmailService email)
    {
        _email = email;
    }
}
```

This is still coupled to `EmailService`.

If you replace it with `GmailService`, you'll still need to change:

``` diff
- private readonly EmailService _email;
+ private readonly GmailService _email;

- public OrderService(EmailService email)
+ public OrderService(GmailService email)
```

### Benefits over `new`

**1. The DI container creates the object**

``` csharp
new EmailService(...);
```

The DI container creates it automatically.

**Constructor dependencies are resolved automatically**

``` csharp
public class EmailService
{
    public EmailService(
        ILogger<EmailService> logger,
        IConfiguration configuration)
    {
    }
}
```

Using `new`:

``` csharp
new EmailService(logger, configuration);
```

Using DI:

``` csharp
builder.Services.AddScoped<EmailService>();
```

No change is required in `OrderService`. The container injects
dependencies automatically.

**3. Lifetime Management**

``` csharp
builder.Services.AddSingleton<EmailService>();
```

or

``` csharp
builder.Services.AddScoped<EmailService>();
```

No code changes are needed in consumers.

------------------------------------------------------------------------

## Case 3: DI with an Interface (Best Practice)

``` csharp
builder.Services.AddScoped<IEmailService, EmailService>();
```

``` csharp
public class OrderService
{
    private readonly IEmailService _email;

    public OrderService(IEmailService email)
    {
        _email = email;
    }
}
```

Later:

``` csharp
builder.Services.AddScoped<IEmailService, GmailService>();
```

`OrderService` does not change.

------------------------------------------------------------------------

### Think of It in Two Dimensions

### Who creates the object?

-   `new` → Your class creates it.
-   DI → The DI container creates it.

### What does your class depend on?

-   Concrete class → Tight coupling.
-   Interface → Loose coupling.

------------------------------------------------------------------------

### Interview Answer
- Dependency Injection handles object creation
- lifetime management, and constructor dependency resolution.
- Interfaces reduce coupling by allowing code to depend on abstractions instead of implementations.
- DI with a concrete class is better than using `new`, but DI with interfaces provides true loose coupling
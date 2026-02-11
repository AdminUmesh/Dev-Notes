# Controllers
Controllers are used for handling HTTP requests, (not business logic)

1. Accept an HTTP request (from browser, Postman, etc.)

1. Talk to services

1. Return a result (View, JSON, etc.)

- `It becomes tightly coupled
If Controller A depends on Controller B’s method:`

- `You’re making A dependent on B’s internal behavior`

- `If B changes, A might break`


# Services
### What is a Services folder?
Services are lightweight, logic-only. You can test them with a simple unit test.

- A Services folder usually contains reusable business logic.

**For example:** sending emails, processing payments, generating reports, etc.

### Without Services?
You can also write the logic inside the controller, but it becomes messy if the project grows.

#### Reusability
- Controller logic is not reusable.
- Service logic is reusable in many places.

#### Testability
- Controllers are tightly tied to HTTP (browser, API).
- Services are just logic — easy to test.

### Step-by-Step: Creating and Using a Service in .NET Core MVC

**Step 1:** Create a Services Folder (manually)


**Step 2:** Create a Service Interface and Class

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

```csharp
using System.Net;
using System.Net.Mail;

namespace YourProject.Services
{
    public class EmailService : IEmailService
    {
        public void SendWelcomeEmail(string toEmail, string name, string password)
        {
            MailMessage mail = new MailMessage("youremail@example.com", toEmail);
            mail.Subject = "Welcome!";
            mail.Body = $"Hello {name},\nYour temporary password is {password}.";

            SmtpClient smtp = new SmtpClient("smtp.gmail.com")
            {
                Port = 587,
                Credentials = new NetworkCredential("youremail@example.com", "your-app-password"),
                EnableSsl = true
            };

            smtp.Send(mail);
        }
    }
}
```

**Step 3:** Register the Service in Program.cs

```csharp
builder.Services.AddScoped<IEmailService, EmailService>();
```

This tells .NET: “Whenever I ask for IEmailService, give me EmailService.”

**Step 4:** Use in a Controller
```csharp
using YourProject.Services;

public class AccountController : Controller
{
    private readonly IEmailService _emailService;

    public AccountController(IEmailService emailService)
    {
        _emailService = emailService;  // dependency injection
    }

    public IActionResult Register()
    {
        string email = "user@example.com";
        string name = "Raj";
        string password = "Temp@123";

        _emailService.SendWelcomeEmail(email, name, password);

        return View();
    }
}
```

#### Benefits of Doing This

- ✅ Code is reusable
- ✅ Controller stays clean
- ✅ Easy to test
- ✅ Easy to change email provider in future

# Dependency Injection
Dependency Injection (DI) is a design pattern where a class receives its required objects (dependencies) from the DI container rather than creating them using `new`

**It makes your code:**

- Clean
- Testable 
- Reusable
- Loosely Coupled  (not tightly stuck together)

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


# Tight Coupling
Tightly coupled code means one class or component directly depends on another class's concrete implementation. If you change one thing, it can break many other parts.

❌ Example:
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



# More About DI
=> Dependency Injection is a design pattern used to achieve loose coupling between classes. Instead of creating dependencies inside a class, you pass them from outside.

- Without DI: A class creates the object it needs.

- With DI: The object is passed to the class, usually through constructor, method, or property.

#### Tightly Coupled Example
```csharp
using System;

public class Engine
{
    public void Start()
    {
        Console.WriteLine("Engine started");
    }
}

public class Car
{
    private Engine _engine = new Engine(); // tightly coupled

    public void StartCar()
    {
        _engine.Start();
    }
}

public class Program
{
    public static void Main()
    {
        Car car = new Car();
        car.StartCar();
    }
}
```

#### Loosely Coupled Example using Dependency Injection
```csharp
using System;

// Step 1: Define an interface
public interface IEngine
{
    void Start();
}

// Step 2: Implement the interface
public class Engine : IEngine
{
    public void Start()
    {
        Console.WriteLine("Engine started");
    }
}

// Step 3: Inject the dependency using the interface
public class Car
{
    private IEngine _engine;

    public Car(IEngine engine) // dependency injected via constructor
    {
        _engine = engine;
    }

    public void StartCar()
    {
        _engine.Start();
    }
}

// Step 4: Use it in the Main method
public class Program
{
    public static void Main()
    {
        IEngine engine = new Engine(); // create the dependency
        Car car = new Car(engine);     // inject the dependency
        car.StartCar();
    }
}
```

#### Without DI (Tight Coupling):
- Classes depend on specific implementations.

- Hard to test (you can't easily replace real classes with test versions).

- Hard to change (if Engine changes, Car also needs to change).

- Code is less flexible and reusable.

#### With DI (Loose Coupling):
- Classes depend on abstractions (like interfaces), not implementations.

- Easy to test (you can inject fake/mock classes).

- Easy to change or upgrade parts (replace PetrolEngine with ElectricEngine).

- Promotes clean architecture (especially in large projects).

## Why Use Dependency Injection (DI)?
**1. Loose Coupling**
**2. Easier Testing**
**3. Reusability**

With DI, your classes are generic and work with any implementation.

Example: DataAccess can work with MSSQL, MySQL, SQLite—no change needed.

**4. Follows SOLID Principles**
DI helps achieve D - Dependency Inversion Principle.

"High-level modules should not depend on low-level modules. Both should depend on abstractions."

# Dependency Injection (Step by Step)

#### 1. Define an Interface
```csharp
public interface IMessageService
{
    string GetMessage();
}
```

#### 2. Implement the Interface
```csharp
public class HelloMessageService : IMessageService
{
    public string GetMessage()
    {
        return "Hello from DI!";
    }
}
```

#### 3.Register the Service in Program.cs

```csharp

// Register DI
builder.Services.AddScoped<IMessageService, HelloMessageService>();

```

#### 4. Inject via Constructor in Controller
```csharp
using Microsoft.AspNetCore.Mvc;

public class HomeController : Controller
{
    private readonly IMessageService _messageService;

    // ✅ Constructor Injection
    public HomeController(IMessageService messageService)
    {
        _messageService = messageService;
    }

    public IActionResult Index()
    {
        var message = _messageService.GetMessage();
        ViewBag.Message = message;
        return View();
    }
}
```

# Summary:

| Step | What You Do                                   |
|------|-----------------------------------------------|
| 1    | Define an interface (e.g., `IMessageService`) |
| 2    | Implement it (`HelloMessageService`)          |
| 3    | Register it in `Program.cs`                   |
| 4    | Inject it into a controller or service        |


### Minimal API (No Controller) — .NET 6+
If you're using .NET 6 or later without controllers, here's how you inject services into Minimal APIs.

**Define Services**
```csharp
public interface IMessageService
{
    string GetMessage();
}

public class MessageService : IMessageService
{
    public string GetMessage() => "Hello from Minimal API!";
}
```
**Program.cs with Minimal API**

```csharp
var builder = WebApplication.CreateBuilder(args);

builder.Services.AddScoped<IMessageService, MessageService>();

var app = builder.Build();

// Inject service directly into endpoint
app.MapGet("/", (IMessageService service) =>
{
    return service.GetMessage();
});

app.Run();
```

---

### .NET Core DI Lifetimes: Singleton vs Scoped vs Transient

**Lifetime Summary**

| Lifetime   | Instance Created     | Scope                    | Shared Across Requests |
|------------|----------------------|--------------------------|-------------------------|
| Transient  | Every time requested | Short-lived (per usage) | ❌ No                  |
| Scoped     | Once per request     | Per HTTP request         | ✅ Yes (within request) |
| Singleton  | Once per app         | App-wide                 | ✅ Yes (globally)       |

---


# Dependency Injection – Practical Understanding (No Theory)

## ❌ Problem Case: Using `new` (Tightly Coupled)

### Email Service
```csharp
public class EmailService
{
    public void Send(string message)
    {
        Console.WriteLine("Email sent: " + message);
    }
}
```

### Controller (Problem)
```csharp
public class NotificationController
{
    public void Notify()
    {
        EmailService service = new EmailService(); // tightly coupled
        service.Send("Hello User");
    }
}
```

### Problem
- Controller directly depends on `EmailService`
- Any change requires modifying controller code
- Violates Open/Closed Principle

---

## 🔥 Requirement Change: SMS Instead of Email

```csharp
public class SmsService
{
    public void Send(string message)
    {
        Console.WriteLine("SMS sent: " + message);
    }
}
```

Controller must change again ❌

---

## ✅ Solution: Constructor Injection

### Step 1: Interface
```csharp
public interface INotificationService
{
    void Send(string message);
}
```

### Step 2: Implementations
```csharp
public class EmailService : INotificationService
{
    public void Send(string message)
    {
        Console.WriteLine("Email sent: " + message);
    }
}
```

```csharp
public class SmsService : INotificationService
{
    public void Send(string message)
    {
        Console.WriteLine("SMS sent: " + message);
    }
}
```

### Step 3: Controller (Never Changes)
```csharp
public class NotificationController
{
    private readonly INotificationService _service;

    public NotificationController(INotificationService service)
    {
        _service = service;
    }

    public void Notify()
    {
        _service.Send("Hello User");
    }
}
```

### Step 4: Configure Dependency
```csharp
services.AddScoped<INotificationService, EmailService>();
// OR
services.AddScoped<INotificationService, SmsService>();
```

---

## 🔥 Real Benefit

| Scenario | Using `new` | Constructor Injection |
|--------|------------|-----------------------|
| Change service | Controller change | Only config change |
| Testing | Hard | Easy |
| Maintainability | Poor | Excellent |

---

## 🧠 Key Takeaway
> Using `new` couples your code.  
> Constructor Injection keeps it flexible and clean.

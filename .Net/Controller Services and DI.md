# Controller
Controllers are used for handling HTTP requests, (not business logic)

1. Accept an HTTP request (from browser, Postman, etc.)

1. Talk to services

1. Return a result (View, JSON, etc.)

- `It becomes tightly coupled
If Controller A depends on Controller B’s method:`

- `You’re making A dependent on B’s internal behavior`

- `If B changes, A might break`


# Services
## What is a Services folder?
Services are lightweight, logic-only. You can test them with a simple unit test.

- A Services folder usually contains reusable business logic.

**For example:** sending emails, processing payments, generating reports, etc.

## Without Services folder?
You can also write the logic inside the controller, but it becomes messy if the project grows.

## Reusability
🔴 Controller logic is not reusable.

🟢 Service logic is reusable in many places.

## Testability
🔴 Controllers are tightly tied to HTTP (browser, API).

🟢 Services are just logic — easy to test.

# Step-by-Step: Creating and Using a Service in .NET Core MVC

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

**Step 4:** Use It in a Controller
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

## 🎁 Benefits of Doing It This Way

- ✅ Code is reusable
- ✅ Controller stays clean
- ✅ Easy to test
- ✅ Easy to change email provider in future

# Dependency Injection
Dependency Injection is a design pattern where you don’t create the objects (services, classes) manually

**It makes your code:**

- Clean 🧼

- Testable 🧪

- Reusable ♻️

- Loosely Coupled 🤝 (not tightly stuck together)

# How DI used in the email integration code?
1. Register the service in Program.cs

```csharp
builder.Services.AddScoped<IEmailService, EmailService>();
```
`This is Dependency Injection registration. It tells .NET: "If anyone needs an IEmailService, give them an instance of EmailService."`

2. Use the interface in your controller:

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

**How DI is used in the constructor of the controller.**

**Without DI (Bad Example)**
```csharp
public class AccountController : Controller
{
    public IActionResult Register()
    {
        var emailService = new EmailService(); // 👎 Manual creation
        emailService.SendEmail(...);
        return View();
    }
}
```

**This is:**

- Hard to test
- Tightly coupled
- Not reusable

## Tight Coupling
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

## 🧨 Problem:

- Hard to test (you always need real EmailService).

- Not flexible or reusable.

## Loose Coupling
Loosely coupled code uses abstractions (like interfaces). Classes depend on interfaces, not concrete implementations.

✅ Example using Interface + Dependency Injection:
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

🧠 Now you can:
Replace EmailService with SMSService or PushNotificationService.


📌 Summary:

## Feature	Tight Coupling	Loose Coupling
Depends On	Concrete classes	Interfaces (abstractions)

```csharp
public abstract class NotificationBase
{
    public abstract void Send(string message);
}

public class EmailService : NotificationBase
{
    public override void Send(string message)
    {
        Console.WriteLine("Email sent: " + message);
    }
}

public class NotificationManager
{
    private readonly NotificationBase _notifier;

    public NotificationManager(NotificationBase notifier)
    {
        _notifier = notifier;
    }

    public void Notify(string msg) => _notifier.Send(msg);
}
```

## This is still loosely coupled because
NotificationManager doesn’t care which service is used — it just uses the base class.

🧠 Summary:

Approach	Loose Coupling?	Preferred?
Interface        	✅ Yes	⭐ Best
Abstract Class	    ✅ Yes	👍 Good
Concrete Class Only	❌ No	🚫 Avoid

# More about DI
=> Dependency Injection is a design pattern used to achieve loose coupling between classes. Instead of creating dependencies inside a class, you pass them from outside.

- Without DI: A class creates the object it needs.

- With DI: The object is passed to the class, usually through constructor, method, or property.

## Tightly Coupled Example
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

## Loosely Coupled Example using Dependency Injection
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

## Without DI (Tight Coupling):
- Classes depend on specific implementations.

- Hard to test (you can't easily replace real classes with test versions).

- Hard to change (if Engine changes, Car also needs to change).

- Code is less flexible and reusable.

## With DI (Loose Coupling):
- Classes depend on abstractions (like interfaces), not implementations.

- Easy to test (you can inject fake/mock classes).

- Easy to change or upgrade parts (replace PetrolEngine with ElectricEngine).

- Promotes clean architecture (especially in large projects).

# Why Use Dependency Injection (DI)?
**1. Loose Coupling**

Without DI: Class depends directly on other classes (tight coupling).

With DI: Class depends on interfaces or abstractions, making it flexible.

💡 You can change or swap components (e.g., switch from MSSQL to MySQL) without changing the main class.

**2. Easier Testing**

Without DI: You cannot easily replace real services.

With DI: You can inject fake or mock objects for testing.

🧪 Makes unit testing much simpler.

**3. Reusability**

With DI, your classes are generic and work with any implementation.

Example: DataAccess can work with MSSQL, MySQL, SQLite—no change needed.

**4. Follows SOLID Principles**

DI helps achieve D - Dependency Inversion Principle.

"High-level modules should not depend on low-level modules. Both should depend on abstractions."

# If You do same thing with creating Instance then Why use DIs

## Non-DI Example (Tightly Coupled) — Adding ElectricEngine
```csharp
using System;

public class PetrolEngine
{
    public void Start()
    {
        Console.WriteLine("Petrol engine started");
    }
}

public class ElectricEngine
{
    public void Start()
    {
        Console.WriteLine("Electric engine started");
    }
}

public class Car
{
    private PetrolEngine _engine = new PetrolEngine(); // tightly coupled to PetrolEngine

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
        car.StartCar();  // always starts PetrolEngine
    }
}
```
**Issues if you want to use ElectricEngine instead:** You have to modify the Car class to use ElectricEngine.

**For example,** change private PetrolEngine _engine = new PetrolEngine(); to private ElectricEngine _engine = new ElectricEngine();.

- This means changing the Car class every time you want to switch engine types — not flexible.

- You cannot easily switch engine at runtime without rewriting Car.

## DI Example (Loosely Coupled) — Adding ElectricEngine
```csharp
using System;

// Step 1: Interface (abstraction)
public interface IEngine
{
    void Start();
}

// Step 2: PetrolEngine implements IEngine
public class PetrolEngine : IEngine
{
    public void Start()
    {
        Console.WriteLine("Petrol engine started");
    }
}

// Step 3: ElectricEngine also implements IEngine
public class ElectricEngine : IEngine
{
    public void Start()
    {
        Console.WriteLine("Electric engine started");
    }
}

// Step 4: Car depends on IEngine, injected from outside
public class Car
{
    private IEngine _engine;

    public Car(IEngine engine)
    {
        _engine = engine;
    }

    public void StartCar()
    {
        _engine.Start();
    }
}

public class Program
{
    public static void Main()
    {
        // Now you can easily switch engine by changing only this line:
        IEngine engine = new ElectricEngine();  // Or new PetrolEngine();
        Car car = new Car(engine);
        car.StartCar();
    }
}
```

**Benefits here:**

- No need to change the Car class at all when switching engines.

- You can inject any engine implementing IEngine.

- Easy to extend later (add DieselEngine or HybridEngine without touching Car).

- Much better for testing and maintainability.

# Dependency Injection (Step by Step)

## 1. Define an Interface
```csharp
public interface IMessageService
{
    string GetMessage();
}
```

# 2. Implement the Interface
```csharp
public class HelloMessageService : IMessageService
{
    public string GetMessage()
    {
        return "Hello from DI!";
    }
}
```

# 3.Register the Service in Program.cs
For .NET Core 6/7/8 (Minimal hosting model):

```csharp
var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllersWithViews();

// Register DI
builder.Services.AddScoped<IMessageService, HelloMessageService>();

var app = builder.Build();

app.MapDefaultControllerRoute();
app.Run();
```
If you're using ASP.NET Core 3.1 or .NET 5, DI is configured in Startup.cs

```csharp
public class Program
{
    public static void Main(string[] args)
    {
        CreateHostBuilder(args).Build().Run();
    }

    public static IHostBuilder CreateHostBuilder(string[] args) =>
        Host.CreateDefaultBuilder(args)
            .ConfigureWebHostDefaults(webBuilder =>
            {
                webBuilder.UseStartup<Startup>();
            });
}
```
- **AddScoped** – one instance per HTTP request (commonly used in web apps).
- Use **AddSingleton** or **AddTransient** based on your needs.

# 4. Inject via Constructor in Controller
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

| Step | What You Do                                  |
|------|-----------------------------------------------|
| 1    | Define an interface (e.g., `IMessageService`) |
| 2    | Implement it (`HelloMessageService`)          |
| 3    | Register it in `Program.cs`                   |
| 4    | Inject it into a controller or service        |


# Minimal API (No Controller) — .NET 6+
If you're using .NET 6 or later without controllers, here's how you inject services into Minimal APIs.

🔸 Define Services
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
Program.cs with Minimal API

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
🟢 No need for controllers or classes — services can be injected right into route handlers!

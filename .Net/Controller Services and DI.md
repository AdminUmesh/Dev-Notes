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

c```csharp
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

## This is still loosely coupled because**
NotificationManager doesn’t care which service is used — it just uses the base class.

🧠 Summary:

Approach	Loose Coupling?	Preferred?
Interface        	✅ Yes	⭐ Best
Abstract Class	    ✅ Yes	👍 Good
Concrete Class Only	❌ No	🚫 Avoid
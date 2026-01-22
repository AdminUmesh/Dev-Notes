
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

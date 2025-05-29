
# SOLID Principles in C# (.NET Core) – All in One Example

## What is SOLID?
**SOLID** is an acronym for five design principles introduced by Robert C. Martin (Uncle Bob):

- **S - Single Responsibility Principle (SRP)**: A class should have only one reason to change.
- **O - Open/Closed Principle (OCP)**: Software should be open for extension but closed for modification.
- **L - Liskov Substitution Principle (LSP)**: Subtypes must be substitutable for their base types.
- **I - Interface Segregation Principle (ISP)**: Don't force a client to depend on interfaces it doesn't use.
- **D - Dependency Inversion Principle (DIP)**: Depend on abstractions, not on concretions.

## Why is SOLID Useful?
- Improves **maintainability**
- Enhances **readability**
- Makes code **easier to test**
- Supports **scalability**

---

## ✅ Example: Notification System

This example demonstrates all five SOLID principles in a simple notification system using C#.

```csharp
using System;

// I - Interface Segregation Principle
public interface INotifier
{
    void Send(string message);
}

// S - Single Responsibility Principle
public class EmailNotifier : INotifier
{
    public void Send(string message)
    {
        Console.WriteLine("Sending Email: " + message);
    }
}

public class SmsNotifier : INotifier
{
    public void Send(string message)
    {
        Console.WriteLine("Sending SMS: " + message);
    }
}

// O - Open/Closed Principle
public class NotificationService
{
    private readonly INotifier _notifier;

    // D - Dependency Inversion Principle
    public NotificationService(INotifier notifier)
    {
        _notifier = notifier;
    }

    public void Notify(string message)
    {
        _notifier.Send(message);
    }
}

// L - Liskov Substitution Principle is respected
// Any class that implements INotifier (e.g., EmailNotifier, SmsNotifier)
// can be used without changing the NotificationService logic

class Program
{
    static void Main()
    {
        // Using Email
        INotifier emailNotifier = new EmailNotifier();
        var emailService = new NotificationService(emailNotifier);
        emailService.Notify("Welcome via Email!");

        // Using SMS
        INotifier smsNotifier = new SmsNotifier();
        var smsService = new NotificationService(smsNotifier);
        smsService.Notify("Welcome via SMS!");
    }
}
```

## 🔍 How Each Principle Is Applied

| Principle | How It's Used |
|-----------|----------------|
| **S (Single Responsibility)** | Each class has one job (EmailNotifier only sends email, etc.). |
| **O (Open/Closed)**           | You can add new notifiers like PushNotifier without changing NotificationService. |
| **L (Liskov Substitution)**   | NotificationService works with any implementation of INotifier. |
| **I (Interface Segregation)** | INotifier is a simple, focused interface. |
| **D (Dependency Inversion)**  | NotificationService depends on INotifier abstraction, not a specific implementation. |

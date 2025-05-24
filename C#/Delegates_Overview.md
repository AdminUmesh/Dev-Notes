
# Delegates

A delegate is like a pointer to a method. It is used to pass methods as parameters.

---

## 🔹 Syntax:

```csharp
delegate int MyDelegate(int x, int y);  // Declaration

class Program
{
    static int Add(int a, int b) => a + b;

    static void Main()
    {
        MyDelegate d = Add;             // Assigning method to delegate
        int result = d(5, 3);           // Calling the method using delegate
        Console.WriteLine(result);      // Output: 8
    }
}
```

---

## 🔹 Use Cases:
- Callback methods
- Event handling
- Strategy pattern

---

### ❓ Why use delegates when we can directly call the method like `Add(5, 3)`?

Let’s explore the benefits of delegates and why they are useful in C#:

---

## ✅ 1. Flexibility (Pass Methods as Parameters)
With delegates, you can pass methods as arguments to other methods.  
This enables more dynamic and reusable code.

### 🔸 Without Delegate:
You have to call a specific method like `Add(a, b)`.

### 🔸 With Delegate:
You can pass different methods (like Add, Subtract, etc.) to the same logic.

```csharp
delegate int MyDelegate(int x, int y);

static int Add(int a, int b) => a + b;
static int Subtract(int a, int b) => a - b;

static void Calculate(MyDelegate op, int x, int y)
{
    Console.WriteLine(op(x, y));
}

static void Main()
{
    Calculate(Add, 5, 3);      // Output: 8
    Calculate(Subtract, 5, 3); // Output: 2
}
```

✅ **Benefit**: `Calculate()` works with any logic passed via delegate.

---

## ✅ 2. Event Handling (UI, Windows Forms, Web)

Delegates are the core behind events in C#.  
For example, in a Windows Form:

```csharp
button.Click += new EventHandler(Button_Click);
```

Here, `EventHandler` is a delegate.

✅ **Benefit**: Delegates let you attach different logic to events at runtime.

---

## 🧮 Real-World Example: Calculator Using Delegates

```csharp
using System;

delegate int Operation(int x, int y);

class Calculator
{
    static int Add(int a, int b) => a + b;
    static int Subtract(int a, int b) => a - b;
    static int Multiply(int a, int b) => a * b;
    static int Divide(int a, int b) => b != 0 ? a / b : 0;

    static void PerformCalculation(int x, int y, Operation op)
    {
        int result = op(x, y);
        Console.WriteLine("Result: " + result);
    }

    static void Main()
    {
        Console.WriteLine("Choose operation: 1-Add, 2-Subtract, 3-Multiply, 4-Divide");
        int choice = int.Parse(Console.ReadLine());

        Console.WriteLine("Enter first number:");
        int a = int.Parse(Console.ReadLine());
        Console.WriteLine("Enter second number:");
        int b = int.Parse(Console.ReadLine());

        Operation selectedOp = null;

        switch (choice)
        {
            case 1: selectedOp = Add; break;
            case 2: selectedOp = Subtract; break;
            case 3: selectedOp = Multiply; break;
            case 4: selectedOp = Divide; break;
            default: Console.WriteLine("Invalid choice"); return;
        }

        PerformCalculation(a, b, selectedOp);
    }
}
```

---

## ✅ Delegate Summary

### 🔸 What is a Delegate?
A delegate is a type-safe function pointer — it stores a reference to a method that matches its signature (i.e., same return type and parameter types).

### 🔹 Basic Syntax:
```csharp
delegate ReturnType DelegateName(ParameterType1 param1, ParameterType2 param2, ...);
```

---

## ✅ Key Rules:

| Rule                | Explanation                                      |
|---------------------|--------------------------------------------------|
| 🔹 Return Type       | Must match exactly (e.g., int, string, etc.)     |
| 🔹 Parameter Types   | Must match in number, order, and type            |
| 🔹 Static/Instance   | Both can be used if the signature matches        |
| 🔹 Multiple Methods  | You can change the method assigned to the delegate |

---

## ✅ Quick Examples:

| Delegate Definition            | Valid Matching Method Signature |
|--------------------------------|---------------------------------|
| `delegate int MyDel(int a, int b);` | `int Add(int x, int y)`         |
| `delegate void Greet(string name);` | `void SayHello(string n)`       |
| `delegate string GetMsg();`         | `string ShowMessage()`          |

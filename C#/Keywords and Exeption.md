# 1. base
Purpose: Access members of the base class from the derived class.
```csharp
using System;

class BaseClass {
    public void Display() {
        Console.WriteLine("Base class method");
    }
}

class DerivedClass : BaseClass {
    public void Show() {
        base.Display();  // Calls the base class method
    }
}

class Program {
    static void Main() {
        DerivedClass obj = new DerivedClass();
        obj.Show();  // Output: Base class method
    }
}
//Output: Base class method
```

# 2. override
Purpose: Redefine a method from the base class in the derived class.

```csharp
using System;

class BaseClass {
    public virtual void Display() {
        Console.WriteLine("Base class method");
    }
}

class DerivedClass : BaseClass {
    public override void Display() {
        Console.WriteLine("Overridden method in derived class");
    }
}

class Program {
    static void Main() {
        DerivedClass obj = new DerivedClass();
        obj.Display();  // Output: Overridden method in derived class
    }
}
// Output: Overridden method in derived class
```

# 3. readonly
Purpose: Declare fields that can only be assigned during initialization or in the constructor.

```csharp
using System;

class MyClass {
    public readonly int MyField;

    public MyClass(int value) {
        MyField = value;  // Can only be set here or in the constructor
    }
}

class Program {
    static void Main() {
        MyClass obj = new MyClass(10);
        Console.WriteLine(obj.MyField);  // Output: 10
        // obj.MyField = 20;  // Error: Cannot assign to 'MyField' because it is readonly
    }
}
// Output: 10
```

# 4. this
Purpose: Refers to the current instance of the class.

```csharp
using System;

class MyClass {
    public int value;

    public MyClass(int value) {
        this.value = value;  // 'this' differentiates between the parameter and the field
    }

    public void Display() {
        Console.WriteLine("Value: " + this.value);  // Access instance field
    }
}

class Program {
    static void Main() {
        MyClass obj = new MyClass(5);
        obj.Display();  // Output: Value: 5
    }
}
// Output: Value: 5
```
# 5. new
Purpose: Used to create objects or hide members of the base class.

**Example (Object creation):**
```csharp
using System;

class MyClass {
    public void Display() {
        Console.WriteLine("Object created!");
    }
}

class Program {
    static void Main() {
        MyClass obj = new MyClass();  // Creates a new object of MyClass
        obj.Display();  // Output: Object created!
    }
}
//Output: Object created!
```

**Example (Hiding base class members):**

```csharp
using System;

class BaseClass {
    public void Display() {
        Console.WriteLine("Base class method");
    }
}

class DerivedClass : BaseClass {
    public new void Display() {  // Hides the base class method
        Console.WriteLine("New method in derived class");
    }
}

class Program {
    static void Main() {
        DerivedClass obj = new DerivedClass();
        obj.Display();  // Output: New method in derived class
    }
}
// Output: New method in derived class
```

# 6. is (Alternative to instanceof in C#)
Purpose: Check if an object is of a certain type.

```csharp
using System;

class Program {
    static void Main() {
        object obj = "Hello";

        if (obj is string) {  // Checks if obj is a string
            Console.WriteLine("obj is a string");
        } else {
            Console.WriteLine("obj is not a string");
        }
    }
}
// Output: obj is a string
```

## Summary of the Keywords:
- **base:** Access base class members.
- **override:** Redefine base class methods in derived classes.
- **readonly:** Declare fields that can only be assigned once.
- **this:** Reference to the current instance of the class.
- **new:** Create objects or hide base class members.
- **is:** Check the type of an object.

# Exception Hierarchy in C#
In C#, exceptions are objects that derive from the System.Exception class. `The two main categories of exceptions in C# are:`

1. **System.Exception (Base Class)**

   - All exceptions in C# inherit from this class.
2. **Types of Exceptions:**

   - `System.SystemException` (Represents exceptions that occur in the runtime, including: )

     - `System.DivideByZeroException:` Division by zero.
     - `System.NullReferenceException:` Null object reference.
     - `System.IndexOutOfRangeException:` Invalid index in arrays or collections.
     - `System.ArgumentException:` Invalid argument passed to a method.
     - `System.FormatException:` Invalid format, e.g., when parsing a string to a number.
     - `System.IO.IOException:` General I/O errors.
    - `System.ApplicationException` (For application-specific exceptions, although ApplicationException itself is rarely used directly.)

   - `System.Error` (Errors that are typically fatal and beyond the control of the program, such as OutOfMemoryException and StackOverflowException).

# Mechanisms to Handle Exceptions in C#
## try Block:

- The try block contains code that might cause an exception. If an exception occurs, the program will jump to the appropriate catch block for handling.
## catch Block:

- The catch block handles exceptions thrown in the try block. The exception is caught as an object and can be processed.
## throw Keyword:

- The throw keyword is used to throw an exception explicitly, either a predefined one or a custom exception.
## throws Keyword:

- In C#, there is no direct equivalent to Java's throws keyword. However, C# exceptions are declared within the method signature implicitly, and methods that throw exceptions should document the possible exceptions in the comments.
## finally Block:

- The finally block is used to execute code after the try block has finished, regardless of whether an exception was thrown or not. It's usually used for cleanup code (like closing files, database connections, etc.).

# Code Examples:
### 1. Basic Exception Handling in C#:
``` csharp
using System;

class MainClass {
    public static void Main(string[] args) {
        string str = null;

        try {
            Console.WriteLine(str.ToUpper());  // Will throw NullReferenceException
        }
        catch (NullReferenceException e) {
            Console.WriteLine("Caught NullReferenceException: " + e.Message);
        }
    }
}
```

**Output:** `Caught NullReferenceException: Object reference not set to an instance of an object.`

### 2. Using throw to Raise Exceptions:
```csharp
using System;

class MainClass {
    static void CheckAge(int age) {
        if (age < 18) {
            throw new InvalidOperationException("Access denied - You must be at least 18 years old.");
        } else {
            Console.WriteLine("Access granted - You are old enough!");
        }
    }

    public static void Main(string[] args) {
        try {
            CheckAge(15);  // Will throw an InvalidOperationException
        }
        catch (InvalidOperationException e) {
            Console.WriteLine(e.Message);  // Handle custom exception
        }
    }
}
```
**Output:** `Access denied - You must be at least 18 years old.`

### 3. Using finally Block:
```csharp
using System;

class MainClass {
    public static void Main(string[] args) {
        try {
            Console.WriteLine("Code executing...");
            throw new Exception("An error occurred.");  // Will throw an exception
        }
        catch (Exception e) {
            Console.WriteLine("Caught exception: " + e.Message);
        }
        finally {
            Console.WriteLine("This will always run, whether exception is thrown or not.");
        }
    }
}
```

**Output:**
```vbnet
Code executing...
Caught exception: An error occurred.
This will always run, whether exception is thrown or not.
```

### 4. Multiple catch Blocks:
```csharp
using System;

class MainClass {
    public static void Main(string[] args) {
        try {
            // ArithmeticException
            int result = 10 / 0;  // Division by zero
        }
        catch (DivideByZeroException e) {
            Console.WriteLine("Caught DivideByZeroException: " + e.Message);
        }

        try {
            // IndexOutOfRangeException
            int[] numbers = { 1, 2, 3 };
            Console.WriteLine(numbers[5]);  // Accessing an invalid index
        }
        catch (IndexOutOfRangeException e) {
            Console.WriteLine("Caught IndexOutOfRangeException: " + e.Message);
        }
    }
}
```
**Output:**
```vbnet
Caught DivideByZeroException: Attempted to divide by zero.
Caught IndexOutOfRangeException: Index was outside the bounds of the array.
```

# Exception Types in C#
1. **System.Exception (Base Class):** The base class for all exceptions in C#.

2. **Common Runtime Exceptions:**
   - System.DivideByZeroException
   - System.NullReferenceException
   - System.IndexOutOfRangeException
   - System.FormatException
   - System.IO.IOException

3. **System.Errors (Critical system errors):**

   - System.OutOfMemoryException
   - System.StackOverflowException

# Nested Try-Catch Blocks:
You can nest `try-catch` blocks in C# to handle multiple exceptions at different levels:

```csharp
using System;

class MainClass {
    public static void Main(string[] args) {
        try {
            Console.WriteLine("Outer try block.");
            try {
                Console.WriteLine("Inner try block.");
                throw new InvalidOperationException("An error in the inner block.");
            }
            catch (InvalidOperationException e) {
                Console.WriteLine("Caught exception in inner block: " + e.Message);
            }
        }
        catch (Exception e) {
            Console.WriteLine("Caught exception in outer block: " + e.Message);
        }
    }
}
```
**Output:**
```kotlin
Outer try block.
Inner try block.
Caught exception in inner block: An error in the inner block.
```

# Summary:
- `try:` Used to enclose code that might throw an exception.
- `catch:` Handles exceptions thrown by the try block.
- `finally:` Always executes, regardless of whether an exception occurred or not.
- `throw:` Used to explicitly throw an exception.
- `Multiple` **catch` blocks:** Handle different exceptions separately.
- **No Checked Exceptions:** In C#, all exceptions are unchecked, so no need to declare exceptions in method signatures.
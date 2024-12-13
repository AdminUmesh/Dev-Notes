# `object` Type

### Purpose:
- The object type is the base type from which all other types in C# inherit.It can hold any data type (value types like int, double, char, or reference types like classes, interfaces, etc.).
- It is commonly used when you want to store a variable of unknown type, but you still want to ensure type safety at compile-time.

### Key Points:
- **Type Safety:** When you store a value in an object, the compiler ensures type safety, but you'll often need to cast the object back to its original type to access its members.

- **Boxing/Unboxing:** For value types, storing them in an object involves a process called "boxing," where the value is wrapped in an object. Unboxing is the reverse process, where you cast the object back to the original value type.

**Example**
```c#
object myObj = 10;  // Can hold any type
Console.WriteLine(myObj);  // Prints 10

// To use it as an integer, you need to cast it:
int myInt = (int)myObj;  // Unboxing
Console.WriteLine(myInt);  // Prints 10
```
### Common Use Case: 
The object type is often used in collections like ArrayList or in APIs that can return multiple types, such as System.Object in collections of mixed types. However, it requires explicit casting, which can be error-prone.

# `dynamic` Type

### Purpose:
- The dynamic type in C# bypasses compile-time type checking. It defers type resolution until runtime, meaning you can work with objects without knowing their type at compile time.
- The dynamic type is useful when you're interacting with data where types are not known in advance, such as working with COM objects, dynamic languages (like Python or JavaScript), or JSON data from external sources.

### Key Points:
- **No Compile-Time Type Checking:** With dynamic, the compiler does not check types at compile time. Errors related to type mismatches will only show up during execution.
- **Late Binding:** Method calls, property accesses, and field accesses on dynamic objects are resolved at runtime. This is known as late binding.

**Example**
```c#
dynamic myObj = 10;  // No type specified
Console.WriteLine(myObj);  // Prints 10

myObj = "Hello";  // Now myObj is a string
Console.WriteLine(myObj);  // Prints Hello

myObj = new { Name = "Alice", Age = 30 };  // Anonymous type
Console.WriteLine(myObj.Name);  // Prints Alice
```

# `var` type
In C#, the var keyword is used for implicit typing, meaning that the type of a variable is determined by the compiler at compile-time based on the right-hand side (assigned value). 

### Implicit Typing:
When you use var, the C# compiler determines the type of the variable at compile time based on the type of the assigned value. You don't explicitly declare the type.

```c#
var number = 42;  // Compiler infers that `number` is of type `int`
var name = "Alice";  // Compiler infers that `name` is of type `string`
```

### Cannot Be Used Without Initialization:
You must initialize the variable when you declare it with var.
```c#
    var myVar;  // Error: Implicitly-typed variable must be initialized.
```

### No Changing the Type:
Once a var variable's type is inferred, it cannot change. You cannot reassign a var variable to a value of a different type.
```c#
var num = 10;  // num is inferred as int
num = "Hello";  // Error: Cannot assign a string to an int variable
```

### Used with Any Type:
var can be used with any data type, including custom types, collections, and anonymous types.
```c#
var myList = new List<int> { 1, 2, 3 };  // myList is inferred as List<int>
var person = new { Name = "Alice", Age = 30 };  // Anonymous type with properties Name and Age
```

### Working with LINQ Queries:
In LINQ queries, the type of the result can often be complex, especially when querying objects or collections.
```C#
var result = from p in products
             where p.Price > 100
             select p.Name;
```

# 1. Value Types
Value types hold the actual data. When you assign a value type variable to another variable, a copy of the data is made. Value types are stored on the stack.

### 1.1 Integral Types (Whole numbers)
- byte: 8-bit unsigned integer (0 to 255)
- sbyte: 8-bit signed integer (-128 to 127)
- short: 16-bit signed integer (-32,768 to 32,767)
- ushort: 16-bit unsigned integer (0 to 65,535)
- int: 32-bit signed integer (-2,147,483,648 to 2,147,483,647)
- uint: 32-bit unsigned integer (0 to 4,294,967,295)
- long: 64-bit signed integer (-9,223,372,036,854,775,808 to 9,223,372,036,854,775,807)
- ulong: 64-bit unsigned integer (0 to 18,446,744,073,709,551,615)

### c1.2 Floating Point Types
- float: 32-bit single-precision floating-point number (~±3.4 × 10^38, 7 digits of precision)
- double: 64-bit double-precision floating-point number (~±1.7 × 10^308, 15–16 digits of precision)

### 1.3 Decimal Type
- decimal: 128-bit precise decimal type (±1.0 × 10^-28 to ±7.9 × 10^28, 28-29 significant digits)
  - Useful for financial and monetary calculations due to its higher precision and smaller range than double and float.
1.4 Boolean Type
bool: Represents a value of true or false.
1.5 Character Type
char: 16-bit Unicode character (a single character, like 'a', '1', '@').

# 2. Reference Types
Reference types store references to the memory location where the actual data is stored. When you assign a reference type variable to another variable, they both point to the same memory location. Reference types are stored on the heap.

### 2.1 String Type
string: Represents a sequence of characters (e.g., "Hello, World!"). In C#, strings are immutable.

### 2.2 Object Type
object: The base type for all other types in C#. Every type in C# (including int, bool, string, etc.) is derived from object. It can hold any data type, but you often need to cast the value back to its original type to access its members.

### 2.3 Class Type
**class:** A user-defined reference type, used to create objects that can contain fields, methods, and properties.

```csharp
class Person
{
    public string Name { get; set; }
    public int Age { get; set; }
}
```

### 2.4 Array Type
**array:** An ordered collection of elements of the same type.
```C#
int[] numbers = {1, 2, 3};
```

### 2.5 Delegate Type
**delegate:** Represents a reference to a method with a particular parameter list and return type. Delegates are used to implement event handling and callback methods.

```csharp
delegate void PrintMessage(string message);
```
### 2.6 Interface Type

**interface:** Defines a contract or blueprint for classes and structs. An interface specifies the methods and properties a class or struct must implement but does not provide the implementation itself.

```csharp
interface IDriveable
{
    void Drive();
}
```

# 3. Nullable Types
nullable types: Used to represent value types that can also have a null value (since value types like int, bool, etc., cannot be null by default).

```c#
//Syntax: 
T? (where T is a value type).
```

```csharp
//Example:
int? x = null;  // Nullable int
bool? isActive = null;  // Nullable bool
```

# 4. Special Types
### 4.1 Void Type
**void:** Represents the absence of a return value. Used as the return type of methods that don't return a value.

```csharp
void PrintMessage()
{
    Console.WriteLine("Hello");
}
```

### 4.2 Dynamic Type
**dynamic:** A type that bypasses compile-time type checking. Its type is determined at runtime, so you can assign any type of value to a dynamic variable. This is useful when working with data where the type is not known at compile-time (e.g., JSON data, COM objects, or using reflection).

```csharp
dynamic obj = 10;
obj = "Hello";  // obj's type can change dynamically at runtime.
```

### 4.3 Enum Type
**enum:** A special "class" that represents a set of named constants.

```csharp
enum Days { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }
Days today = Days.Monday;
```

### 4.4 Struct Type
**struct:** A value type that can contain fields, methods, and properties. Structs are similar to classes but are stored on the stack rather than the heap.

```csharp
struct Point
{
    public int X;
    public int Y;
}
```

### 4.5 Tuple Type
**Tuple:** Represents a data structure that can hold a fixed-size sequence of elements of different types.

```csharp
var person = Tuple.Create("Alice", 30, true);  // Tuple of (Name, Age, IsActive)
```

### 4.6 Pointer Type
***pointer:** C# allows the use of pointers in unsafe code. Pointers can hold the memory address of another variable. However, using pointers requires the unsafe keyword and is not common in everyday C# programming.

```csharp
unsafe
{
    int x = 10;
    int* ptr = &x;  // Pointer to x
}
```

# 5. Type Aliases
C# also allows the use of aliases for certain types. The most common example is int being an alias for System.Int32.

- **int:** Alias for System.Int32
- **long:** Alias for System.Int64
- **double:** Alias for System.Double
- **float:** Alias for System.Single
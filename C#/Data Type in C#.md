# Object Type

### Example
```c#
object myObj = 10;
Console.WriteLine(myObj); // Output: 10

myObj = "Hello";
Console.WriteLine(myObj); // Output: Hello
```

### Purpose:
- object is the base type of all types in C#.

- It can store any type of value, such as int, double, char, string, or an object of a class.

- When retrieving a value, you may need to cast it to its original type.

### Common Use Case: 
- Used when a variable needs to hold values of different types.

- Used in non-generic collections like ArrayList.

# dynamic Type
### Example
```c#
dynamic myObj = 10;
Console.WriteLine(myObj); // Output: 10

myObj = "Hello";
Console.WriteLine(myObj); // Output: Hello

myObj = new { Name = "Alice", Age = 30 };
Console.WriteLine(myObj.Name); // Output: Alice
```
### Purpose:
- dynamic allows a variable to hold different types of values.

- Type checking happens at runtime, not compile time.

- You can access properties and call methods without the compiler checking them beforehand.

### Key Points:
- No compile-time checking: Some errors appear only when the code runs.

- Late binding: Method calls and property access are resolved at runtime.

- Use case: Useful when working with COM objects or data whose type is not known in advance.



# var type
### Example
```c#
var number = 42;       // int
var name = "Alice";    // string
var price = 99.5;      // double

number = 50;           // Allowed
// number = "Hello";   // Error: number is an int
```

### Purpose:
- var allows the compiler to automatically determine a variable's type from its assigned value.

- The type is decided at compile time.

- Once the type is determined, it cannot be changed.

### Key Points:
**Initialization is required: You must assign a value when declaring a var variable.**

```c#
// var myVar; // Error
var myVar = 10; // Correct
```

**Works with collections and custom types:**
```c#
var numbers = new List<int> { 1, 2, 3 };
```
- Useful with anonymous types and LINQ queries, where the result type may be complex.

- **Note:** var is not a separate data type. It is a keyword that lets the compiler infer the variable's type.

# 1. Value Types
### Purpose:

- Value types store their value directly.

- When you assign one value-type variable to another, a copy of the value is made.

- Value types are not always stored on the stack; their storage location depends on how they are used.

### 1.1 Integral Types (Whole numbers)
Used to store positive and negative whole numbers. Some types store only non-negative numbers.

| Type | Size | Range |
|---|---|---|
| `byte` | 8-bit | 0 to 255 |
| `sbyte` | 8-bit | -128 to 127 |
| `short` | 16-bit | -32,768 to 32,767 |
| `ushort` | 16-bit | 0 to 65,535 |
| `int` | 32-bit | -2,147,483,648 to 2,147,483,647 |
| `uint` | 32-bit | 0 to 4,294,967,295 |
| `long` | 64-bit | -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807 |
| `ulong` | 64-bit | 0 to 18,446,744,073,709,551,615 |

**Example:**
```c#
int age = 25;
byte marks = 100;
long population = 8000000000L;
```

### c1.2 Floating Point Types
Used to store numbers with decimal points. These types can have small rounding errors.

- **float:** Uses less memory and provides about 6–7 significant digits of precision.
- **double:** Provides greater precision than float and is commonly used for decimal calculations.

**Example**
```c#
float height = 5.8f;
double price = 99.99;
```

### 1.3 Decimal Type
**decimal:** Provides high decimal precision and is useful for financial calculations, such as money.

```c#
decimal salary = 45000.50m;
```
**Remember:** Use the m suffix when assigning a decimal literal.

### 1.4 Boolean Type
bool: Stores only two values: true or false.

```c#
bool isActive = true;
bool isLoggedIn = false;
```

### 1.5 Character Type
char: Stores a single UTF-16 code unit, commonly used for one character.
```c#
char grade = 'A';
char symbol = '@';
char digit = '5';
```

# 2. Reference Types
### Purpose:

- Reference types store a reference to an object rather than storing the object's data directly in the variable.

- When you assign one reference variable to another, both can refer to the same object.

- Objects of reference types are generally allocated on the heap.

### 2.1 String Type
**string:** Stores a sequence of characters. Strings are immutable, which means their contents cannot be changed after creation.

```c#
string name = "Hello";
name = "World";
```
Here, name refers to the new string "World". The original string "Hello" remains unchanged.

### 2.2 Object Type
**object:** The base type of all C# types. It can hold a value of any type, but you may need casting to access the original value's specific members.

```c#
object value = 10;
int number = (int)value;
```
Here, value stores 10, and we cast it back to int to retrieve it as an integer.

### 2.3 Class Type
**class:** A user-defined reference type used to create objects containing properties, fields, and methods.

```csharp
class Person
{
    public string Name { get; set; }
    public int Age { get; set; }
}

Person p = new Person();
p.Name = "Rahul";
p.Age = 25;
```
Here, Person defines the structure of a person, and p refers to a Person object.

### 2.4 Array Type
**array:** Stores multiple elements of the same type in an ordered collection. Array elements are accessed using an index starting from 0.

```C#
int[] numbers = { 10, 20, 30 };

Console.WriteLine(numbers[0]); // 10
```

### 2.5 Delegate Type
**delegate:** Holds a reference to a method that matches a particular parameter list and return type. Delegates are useful for callbacks and event handling.

```csharp
delegate void PrintMessage(string message);

void Show(string message)
{
    Console.WriteLine(message);
}

PrintMessage print = Show;
print("Hello");
```
Here, the delegate refers to the Show method and calls it when print is invoked.

### 2.6 Interface Type

**interface:** Defines a contract that a class or struct can implement. It specifies members that the implementing type must provide.

```csharp
interface IDriveable
{
    void Drive();
}

class Car : IDriveable
{
    public void Drive()
    {
        Console.WriteLine("Car is driving");
    }
}
```
Here, Car implements IDriveable by providing the Drive() method.

# 3. Nullable Types
### Purpose:
- Nullable types allow value types like int, bool, and double to store null.

- By default, these value types cannot store null.

### Syntax: 
```c#
T? variableName;
```

### Example:
```csharp
int? x = null;  // Nullable int
bool? isActive = null;  // Nullable bool

age = 25;        // Allowed
isActive = true; // Allowed
```
**Note:** T? is shorthand for Nullable<T> and is used with value types.

# 4. Special Types
### 4.1 Void Type
void means a method does not return any value.

```csharp
void PrintMessage()
{
    Console.WriteLine("Hello");
}
```

### 4.2 Dynamic Type
- dynamic allows a variable to hold different types of values.

- Type checking happens at runtime.

```csharp
dynamic obj = 10;
obj = "Hello";  // obj's type can change dynamically at runtime.
```

### 4.3 Enum Type
enum is used to define a set of named constants.

```csharp
enum Days { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }
Days today = Days.Monday;
```

### 4.4 Struct Type
- struct is a value type that can contain fields, properties, and methods.

- It is similar to a class but has value-type behavior.

```csharp
struct Point
{
    public int X;
    public int Y;
}
```

### 4.5 Tuple Type
A tuple stores a fixed group of values, which can have different types.

```csharp
var person = ("Alice", 30, true);
// Name, Age, IsActive
```

### 4.6 Pointer Type
- A pointer stores the memory address of another variable.

- Pointers are used in unsafe code and are uncommon in everyday C# development.

```csharp
unsafe
{
    int x = 10;
    int* ptr = &x;  // Pointer to x
}
```
**Note:** To use pointers, enable unsafe code in your project settings.

# 5. Type Aliases
C# also allows the use of aliases for certain types. The most common example is int being an alias for System.Int32.

- **int:** Alias for System.Int32
- **long:** Alias for System.Int64
- **double:** Alias for System.Double
- **float:** Alias for System.Single
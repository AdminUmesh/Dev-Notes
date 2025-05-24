
# Lambda Expressions (Detailed)

## 🔹 What is a Lambda Expression?
A lambda expression is a short and anonymous function.
It can be used to define a method inline, without giving it a name.

---

## 🔹 Syntax:
```csharp
(parameters) => expression_or_block
```

---

## ✅ Example 1: Simple Expression
```csharp
Func<int, int, int> add = (a, b) => a + b;
Console.WriteLine(add(3, 4));  // Output: 7
```
✅ `Func<int, int, int>` means: takes 2 int inputs and returns an int.

---

## 🔹 Common Built-in Delegate Types

### 1️⃣ Func<> – Returns a Value
```csharp
Func<int, int, int> multiply = (a, b) => a * b;
int result = multiply(4, 5);   // 20
```
Func<T1, T2, TResult>: last type is return type.

✅ Examples:
```csharp
Func<string> getMessage = () => "Hello";
Func<int, int> square = x => x * x;
Func<string, int> getLength = s => s.Length;
```

---

### 2️⃣ Action<> – Returns Nothing (void)
```csharp
Action<string> greet = name => Console.WriteLine("Hello, " + name);
greet("Amit");  // Output: Hello, Amit
```
Action<T> takes input(s) but returns nothing.

---

### 3️⃣ Predicate<> – Returns True/False
```csharp
Predicate<int> isEven = x => x % 2 == 0;
Console.WriteLine(isEven(4));  // True
Console.WriteLine(isEven(5));  // False
```
Always returns bool.  
Used for filtering or testing conditions.

---

## ✅ Lambda with LINQ
```csharp
List<int> nums = new List<int> { 1, 2, 3, 4, 5 };

var evenNumbers = nums.Where(x => x % 2 == 0);  // Uses Predicate<int>
foreach (var n in evenNumbers)
    Console.WriteLine(n);  // Output: 2 4
```

---

## ✅ Lambda Expression Types

| Delegate Type  | Parameters | Return Type | Use Case                            |
|----------------|------------|-------------|-------------------------------------|
| Func<T>        | 0 to 16    | ✅ Yes      | Returns result                      |
| Action<T>      | 0 to 16    | ❌ No       | Only performs an action (no return)|
| Predicate<T>   | 1          | ✅ Bool     | Tests condition (true/false)        |

---

## 🧠 Real-World Use Cases

| Situation          | Delegate Type            | Lambda Example                       |
|--------------------|--------------------------|--------------------------------------|
| Math operations    | Func<int, int, int>      | (a, b) => a + b                      |
| Print messages     | Action<string>           | name => Console.WriteLine(name)     |
| Check even number  | Predicate<int>           | x => x % 2 == 0                      |
| Filter list in LINQ| Predicate<T>             | list.Where(x => x > 10)             |

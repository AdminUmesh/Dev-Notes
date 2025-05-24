
# LINQ – Language Integrated Query

## 🔹 What is LINQ?
LINQ allows you to query collections (like arrays, lists, or database sets) in a consistent, readable, SQL-like way, using C# syntax.

You can use LINQ with:
- Arrays
- Lists
- Dictionaries
- XML
- Databases (via Entity Framework)

---

## LINQ Syntax Types

### 🔸 1. Query Syntax (SQL-like)
```csharp
int[] numbers = { 1, 2, 3, 4, 5 };

var result = from n in numbers
             where n > 2
             select n;

foreach (var num in result)
    Console.WriteLine(num); // 3 4 5
```

### 🔸 2. Method Syntax (Lambda Style)
```csharp
var result = numbers.Where(n => n > 2);

foreach (var num in result)
    Console.WriteLine(num); // 3 4 5
```

---

## 🔧 Common LINQ Methods (with Examples)

| Method              | Purpose                        | Example                                      |
|---------------------|--------------------------------|----------------------------------------------|
| `Where()`           | Filter elements                | `list.Where(x => x > 10)`                    |
| `Select()`          | Transform or project each element | `list.Select(x => x * 2)`                 |
| `OrderBy()`         | Sort ascending                 | `list.OrderBy(x => x)`                       |
| `OrderByDescending()`| Sort descending               | `list.OrderByDescending(x => x)`            |
| `First()`           | Get first matching element     | `list.First(x => x > 10)`                    |
| `FirstOrDefault()`  | Like First, returns null if not found | `list.FirstOrDefault(x => x > 10)`    |
| `Count()`           | Count elements                 | `list.Count(x => x > 5)`                     |
| `Sum()`             | Sum of numeric elements        | `list.Sum()`                                 |
| `Any()`             | Returns true if any match      | `list.Any(x => x == 5)`                      |
| `All()`             | Returns true if all match      | `list.All(x => x > 0)`                       |
| `Distinct()`        | Removes duplicates             | `list.Distinct()`                            |
| `ToList()`          | Converts result to a List<T>   | `var newList = list.Where(x => x > 5).ToList()` |

---

## 🎯 Real-World Example
```csharp
List<string> names = new List<string> { "Amit", "Ravi", "Neha", "Ravi" };

// Get names starting with 'R' and remove duplicates
var filtered = names
    .Where(name => name.StartsWith("R"))
    .Distinct()
    .ToList();

foreach (var name in filtered)
    Console.WriteLine(name);  // Output: Ravi
```

---

## 🧠 Summary Table

| Concept   | Description                  | Example                    |
|-----------|------------------------------|----------------------------|
| Delegate  | Object that refers to a method | `MyDelegate d = Add;`    |
| Lambda    | Anonymous method              | `(x, y) => x + y`         |
| LINQ      | Query collections             | `list.Where(x => x > 10)` |

---

## 📌 Best Practices

- Use method syntax for most flexibility (especially with lambdas).
- Use `ToList()` if you want to reuse the query result.
- Prefer `FirstOrDefault()` over `First()` to avoid exceptions.
- Chain LINQ methods for clean and readable filtering.

# Find DataType using GetType()
In C#, you can find the data type of a variable or an expression using the GetType() method, which is a member of the System.Object class. This method returns a Type object that represents the runtime type of the current instance.
```c#
using System;

class Program
{
    static void Main()
    {
        int number = 10;
        Type type = number.GetType();
        Console.WriteLine("Data type of number: " + type);
    }
}
```
# Find DataType using typeof
```c#
using System;

class Program
{
    static void Main()
    {
        Type type = typeof(int);
        Console.WriteLine("Data type of int: " + type);
    }
}
    
```

# String Conversions:

**Parse Methods:**
Converting strings to other data types like integers, doubles, dates, etc.
```c#
int intValue = int.Parse("123");
double doubleValue = double.Parse("3.14");
DateTime dateValue = DateTime.Parse("2022-01-15");
```

**TryParse Methods:** Safely parsing strings to other data types without throwing exceptions.
```c#
bool success = int.TryParse("123", out int intValue);
```

# Numeric Conversions:
**Implicit Conversion:** Conversion between compatible numeric types where no data loss occurs.

**Explicit Conversion:** Conversion between numeric types where data loss may occur, requiring explicit casting or conversion methods like Convert.ToInt32().
```c#
int intValue = (int)3.14; // Explicit casting
int intValue = Convert.ToInt32(3.14); // Using Convert.ToInt32
```

# Type Conversion Methods:
**Convert Class:** Provides methods for converting base data types to other base data types.
```c#
int intValue = Convert.ToInt32("123");
```

**BitConverter Class:** Converts base data types to an array of bytes, and vice versa.
```c#
byte[] bytes = BitConverter.GetBytes(123);
```

# Object Conversions:
**Boxing:** Converting a value type to the object type (e.g., int to object).

**Unboxing:** Converting an object type to a value type (e.g., object to int).
```c#
int intValue = 123;
object objValue = intValue; // Boxing
int intValue2 = (int)objValue; // Unboxing
```

# Enum Conversions:
**Enum.Parse:** Converts string representation of the name or underlying value of an enum to the enum type.

**Enum.TryParse:** Safely parses string representation of an enum to the enum type without throwing exceptions.
```c#
MyEnum enumValue = (MyEnum)Enum.Parse(typeof(MyEnum), "Value1");
```

# Collection Conversions:

**Array Conversions:**

ToArray(): Converts a collection (e.g., list) to an array.
```c#
List<int> list = new List<int> { 1, 2, 3 };
int[] array = list.ToArray();
```

**List Conversions:**

ToList(): Converts an array or another type of collection to a list.
```c#
int[] array = { 1, 2, 3 };
List<int> list = array.ToList();
```

**Dictionary Conversions:**
ToDictionary(): Converts a collection of key-value pairs (e.g., list of tuples) to a dictionary.
```c#
var list = new List<(string Key, int Value)> { ("A", 1), ("B", 2) };
var dictionary = list.ToDictionary(pair => pair.Key, pair => pair.Value);
```

**Collection Initialization:**
Collection Initializers: Initialize a collection with a set of values at the time of declaration.
```c#
var list = new List<int> { 1, 2, 3 };
var dictionary = new Dictionary<string, int> { { "A", 1 }, { "B", 2 } };
```

**LINQ Operations:**

LINQ provides a powerful set of methods for querying and transforming collections, allowing you to perform various conversions using methods like Select, SelectMany, Where, GroupBy, etc.
```C#
var array = new[] { 1, 2, 3, 4, 5 };
var evenNumbers = array.Where(num => num % 2 == 0).ToArray();
```

**Implicit and Explicit Conversions:**

Some collection types allow for implicit or explicit conversions between compatible types. For example, you can implicitly convert an array to a list or explicitly convert a list to an array using methods like ToArray and ToList.

# JSON to Object & Object to JSON Conversion
One of the most common approaches is to use the System.Text.Json namespace or third-party libraries like Newtonsoft.Json (Json.NET). Below are examples of how to perform these conversions using both approaches:

## Using System.Text.Json (Available in .NET Core 3.0 and later, and .NET 5+):

**Object to JSON:**
```c#
using System;
using System.Text.Json;

class Program
{
    static void Main()
    {
        // Object to JSON
        var person = new { Name = "John", Age = 30 };
        string json = JsonSerializer.Serialize(person);
        Console.WriteLine(json);
    }
}
```

**JSON to Object:**
```c#
using System;
using System.Text.Json;

class Program
{
    static void Main()
    {
        // JSON to Object
        string json = "{\"Name\":\"John\",\"Age\":30}";
        var person = JsonSerializer.Deserialize<Person>(json);
        Console.WriteLine($"Name: {person.Name}, Age: {person.Age}");
    }
    
    public class Person
    {
        public string Name { get; set; }
        public int Age { get; set; }
    }
}
```

## Using Newtonsoft.Json (Json.NET):
**Object to JSON:**
```c#
using System;
using Newtonsoft.Json;

class Program
{
    static void Main()
    {
        // Object to JSON
        var person = new { Name = "John", Age = 30 };
        string json = JsonConvert.SerializeObject(person);
        Console.WriteLine(json);
    }
}
```

**JSON to Object:**
```c#
using System;
using Newtonsoft.Json;

class Program
{
    static void Main()
    {
        // JSON to Object
        string json = "{\"Name\":\"John\",\"Age\":30}";
        var person = JsonConvert.DeserializeObject<Person>(json);
        Console.WriteLine($"Name: {person.Name}, Age: {person.Age}");
    }
    
    public class Person
    {
        public string Name { get; set; }
        public int Age { get; set; }
    }
}
```

# JSON to Array & Array to JSON Conversion

## Using System.Text.Json (Available in .NET Core 3.0 and later, and .NET 5+):

**Array to JSON:**
```c#
using System;
using System.Text.Json;

class Program
{
    static void Main()
    {
        // Array to JSON
        int[] numbers = { 1, 2, 3, 4, 5 };
        string json = JsonSerializer.Serialize(numbers);
        Console.WriteLine(json);
    }
}
```

**JSON to Array:**
```c#
using System;
using System.Text.Json;

class Program
{
    static void Main()
    {
        // JSON to Array
        string json = "[1, 2, 3, 4, 5]";
        int[] numbers = JsonSerializer.Deserialize<int[]>(json);
        foreach (var number in numbers)
        {
            Console.WriteLine(number);
        }
    }
}
```

## Using Newtonsoft.Json (Json.NET):
**Array to JSON::**
```c#
using System;
using Newtonsoft.Json;

class Program
{
    static void Main()
    {
        // Array to JSON
        int[] numbers = { 1, 2, 3, 4, 5 };
        string json = JsonConvert.SerializeObject(numbers);
        Console.WriteLine(json);
    }
}
```

**JSON to Array:**
```c#
using System;
using Newtonsoft.Json;

class Program
{
    static void Main()
    {
        // JSON to Array
        string json = "[1, 2, 3, 4, 5]";
        int[] numbers = JsonConvert.DeserializeObject<int[]>(json);
        foreach (var number in numbers)
        {
            Console.WriteLine(number);
        }
    }
}
```
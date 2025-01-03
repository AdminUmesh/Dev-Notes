# Generics VS Non-Generics
There are two types of collections available in C#: non-generic collections and generic collections.

The `System.Collections` namespace contains the non-generic collection types and `System.Collections.Generic` namespace includes generic collection types.

## Generics
A generic collection is strongly typed (you can store one type of objects into it) so that we can eliminate runtime type mismatches, it improves the performance by avoiding boxing and unboxing

- Generic collections are generally more efficient in terms of both execution speed and memory usage because they avoid the overhead of boxing and unboxing.

**Examples**

- List: `List<T>`
- Dictionary: `Dictionary<TKey, TValue>`
- Queue: `Queue<T>`
- Stack: `Stack<T>`
- HashSet: `HashSet<T>`
- SortedList: `SortedList<TKey, TValue>`
- LinkedList: `LinkedList<T>`
- SortedSet: `SortedSet<T>`
- `IEnumerable<T>` and `ICollection<T>`

**Example of Generic Stack()**
```c#
using System;
using System.Collections.Generic;

class Program
{
    static void Main()
    {
        // Create a stack that holds integers
        Stack<int> intStack = new Stack<int>();
        
        intStack.Push(1);
        intStack.Push(2);
        intStack.Push(3);

        // Retrieving elements from the stack
        Console.WriteLine(intStack.Pop()); // Output: 3
        Console.WriteLine(intStack.Pop()); // Output: 2
        Console.WriteLine(intStack.Pop()); // Output: 1

        // This will produce a compile-time error if you try to push a non-integer value
        // intStack.Push("Hello"); // Error: Cannot convert string to int
    }
}
```

## Non-Generics
Loosely typed means you can store any type of value in the collection.Result type mismatches are not caught at compile time, you might encounter runtime errors due to incorrect type assumptions.

- Non-generic collections may have performance overhead due to the need for `boxing (converting value types to reference types)` and `unboxing (converting reference types back to value types).`

**Examples**

- ArrayList: ArrayList
- Hashtable: Hashtable
- Queue: Queue
- Stack: Stack
- SortedList: SortedList
- DictionaryEntry: DictionaryEntry
- CollectionBase: CollectionBase
- IDictionary Interface

**Example of Non-Generic Stack()**
```c#
using System;
using System.Collections;

class Program
{
    static void Main()
    {
        Stack stack = new Stack();
        
        stack.Push(1);
        stack.Push("Hello");
        stack.Push(3.14);

        // Retrieving elements from the stack
        Console.WriteLine(stack.Pop()); // Output: 3.14
        Console.WriteLine(stack.Pop()); // Output: Hello
        Console.WriteLine(stack.Pop()); // Output: 1

        // Casting example (unsafe)
        // int number = (int)stack.Pop(); // This will throw an InvalidCastException if the cast is incorrect
    }
}
```

# `List<T>`
a List<T> is a generic collection that can hold a dynamic number of elements. `It is part of the System.Collections.Generic namespace.` You can use List<T> to store elements of any type (e.g., integers, strings, custom objects, etc.).

```c#
using System;
using System.Collections.Generic;

class Program
{
    static void Main()
    {
        // Declare and initialize a List of integers
        List<int> numbers = new List<int>();

        // Add elements to the list
        numbers.Add(10);
        numbers.Add(20);
        numbers.Add(30);
        
        // Insert an element at a specific index
        numbers.Insert(1, 15); // Insert 15 at index 1
        
        // Display the elements
        Console.WriteLine("Numbers in the list:");
        foreach (var number in numbers)
        {
            Console.WriteLine(number);
        }

        // Remove an element by value
        numbers.Remove(20);
        
        // Remove an element by index
        numbers.RemoveAt(1);

        // Display the updated list
        Console.WriteLine("\nUpdated Numbers:");
        foreach (var number in numbers)
        {
            Console.WriteLine(number);
        }
        
        // Check if a value exists in the list
        if (numbers.Contains(10))
        {
            Console.WriteLine("\nThe number 10 is in the list.");
        }
    }
}
```

### Key Methods and Properties of List<T>:
- **Add(T item):** Adds an element to the end of the list.
- **Insert(int index, T item):** Inserts an element at a specified index.
- **Remove(T item):** Removes the first occurrence of a specific object from the list.
- **RemoveAt(int index):** Removes the element at the specified index.
- **Contains(T item):** Checks if the list contains a specific element.
- **Count:** Gets the number of elements in the list.
- **Clear():** Removes all elements from the list.
- **IndexOf(T item):** Returns the index of the first occurrence of the specified element.
- **Sort():** Sorts the list.

# ArrayList
ArrayList is non-generic, it can hold objects of any type, but you may need to explicitly cast the elements when retrieving them. **For example,** `if you add an int and later retrieve it, you will need to cast it back to int.`
```C#
using System;
using System.Collections;

class Program
{
    static void Main()
    {
        // Declare and initialize an ArrayList
        ArrayList list = new ArrayList();

        // Add elements to the ArrayList
        list.Add(10);           // Integer
        list.Add("Hello");      // String
        list.Add(3.14);         // Double
        list.Add(true);         // Boolean

        // Insert an element at a specific index
        list.Insert(1, "World"); // Insert "World" at index 1
        
        // Display the elements in the ArrayList
        Console.WriteLine("ArrayList elements:");
        foreach (var item in list)
        {
            Console.WriteLine(item);
        }

        // Remove an element by value
        list.Remove(3.14); // Remove the double value 3.14
        
        // Remove an element by index
        list.RemoveAt(2);  // Remove element at index 2

        // Display the updated ArrayList
        Console.WriteLine("\nUpdated ArrayList:");
        foreach (var item in list)
        {
            Console.WriteLine(item);
        }

        // Check if an element exists in the ArrayList
        if (list.Contains(10))
        {
            Console.WriteLine("\nThe number 10 is in the ArrayList.");
        }
    }
}
```

**Performance:**
ArrayList can be less efficient `than List<T>` when you're working with a large number of elements because it stores objects as object types, requiring boxing and unboxing for value types (like int, double, etc.)

### Key Methods and Properties of ArrayList:
- **Add(object item):** Adds an element to the end of the ArrayList.
- **Insert(int index, object item):** Inserts an element at a specific index.
- **Remove(object item):** Removes the first occurrence of a specific object from the ArrayList.
- **RemoveAt(int index):** Removes the element at the specified index.
- **Contains(object item):** Checks if the ArrayList contains a specific element.
- **Count:** Gets the number of elements in the ArrayList.
- **Clear():** Removes all elements from the ArrayList.
- **IndexOf(object item):** Returns the index of the first occurrence of the specified element.
- **ToArray():** Converts the ArrayList to an array.

# `Dictionary: Dictionary<TKey, TValue>`
Dictionary<TKey, TValue> is a collection that stores `key-value pairs`. It is part of the `System.Collections.Generic` namespace, and it is type-safe and efficient for looking up values based on keys.

### Key Features of Dictionary<TKey, TValue>:
- **Key-Value Pairs:** Each item in the dictionary is a pair where one part is the key and the other is the value.
- **Efficient Lookup:** Provides fast access to values through keys.
- **Unique Keys:** Keys in a dictionary must be unique; if you try to add a duplicate key, it will throw an exception.
- **Type-Safe:** Both the key and value types are strongly typed.

```c#
using System;
using System.Collections.Generic;

class Program
{
    static void Main()
    {
        // Declare and initialize a Dictionary with string keys and int values
        Dictionary<string, int> scores = new Dictionary<string, int>();

        // Add key-value pairs to the dictionary
        scores.Add("Alice", 90);
        scores.Add("Bob", 85);
        scores.Add("Charlie", 92);

        // Display all elements in the dictionary
        Console.WriteLine("Scores:");
        foreach (var entry in scores)
        {
            Console.WriteLine($"Name: {entry.Key}, Score: {entry.Value}");
        }

        // Access a value using a key
        Console.WriteLine("\nScore of Alice: " + scores["Alice"]);

        // Check if a key exists before accessing its value
        if (scores.ContainsKey("Bob"))
        {
            Console.WriteLine("Bob's score: " + scores["Bob"]);
        }

        // Remove an entry by key
        scores.Remove("Charlie");

        // Display the updated dictionary
        Console.WriteLine("\nUpdated Scores:");
        foreach (var entry in scores)
        {
            Console.WriteLine($"Name: {entry.Key}, Score: {entry.Value}");
        }

        // Try to get the value for a key safely
        if (scores.TryGetValue("David", out int davidScore))
        {
            Console.WriteLine("\nDavid's score: " + davidScore);
        }
        else
        {
            Console.WriteLine("\nDavid is not in the dictionary.");
        }
    }
}
```

### Key Methods and Properties of Dictionary<TKey, TValue>:
- **Add(TKey key, TValue value):** Adds a key-value pair to the dictionary.
- **ContainsKey(TKey key):** Checks if a given key exists in the dictionary.
- **ContainsValue(TValue value):** Checks if a given value exists in the dictionary.
- **Remove(TKey key):** Removes the key-value pair with the specified key.
- **TryGetValue(TKey key, out TValue value):** Tries to get the value associated with the key; returns true if found, otherwise false.
- **Item[TKey key]:** Indexer to get or set the value for a given key.
- **Count:** Gets the number of key-value pairs in the dictionary.
- **Clear():** Removes all key-value pairs from the dictionary.

# Hashtable
Hashtable is a non-generic collection that stores `key-value pairs`. It is part of the System.Collections namespace. Unlike Dictionary<TKey, TValue>

### Key Features of Hashtable:
- **Key-Value Pairs:** Each entry in the hashtable consists of a key and a corresponding value.
- **Non-Generic:** Since Hashtable is non-generic, both the keys and the values are stored as object types. You have to explicitly cast them when retrieving.
- **Efficient Lookup:** Hash tables provide efficient lookups (constant time complexity, O(1) on average) for retrieving values based on keys.
- **Unique Keys:** Keys in a Hashtable must be unique. If you try to add a duplicate key, an exception will be thrown.
- **Thread-Safety:** Hashtable is not thread-safe for concurrent operations by default. You need to use Hashtable.Synchronized to make it thread-safe.

```C#
using System;
using System.Collections;

class Program
{
    static void Main()
    {
        // Declare and initialize a Hashtable
        Hashtable hashtable = new Hashtable();

        // Add key-value pairs to the Hashtable
        hashtable.Add("Alice", 90);
        hashtable.Add("Bob", 85);
        hashtable.Add("Charlie", 92);

        // Display all elements in the Hashtable
        Console.WriteLine("Scores:");
        foreach (DictionaryEntry entry in hashtable)
        {
            Console.WriteLine($"Name: {entry.Key}, Score: {entry.Value}");
        }

        // Access a value using a key
        Console.WriteLine("\nScore of Alice: " + hashtable["Alice"]);

        // Check if a key exists before accessing its value
        if (hashtable.ContainsKey("Bob"))
        {
            Console.WriteLine("Bob's score: " + hashtable["Bob"]);
        }

        // Remove an entry by key
        hashtable.Remove("Charlie");

        // Display the updated Hashtable
        Console.WriteLine("\nUpdated Scores:");
        foreach (DictionaryEntry entry in hashtable)
        {
            Console.WriteLine($"Name: {entry.Key}, Score: {entry.Value}");
        }

        // Try to get the value for a key safely
        if (hashtable.ContainsKey("David"))
        {
            Console.WriteLine("\nDavid's score: " + hashtable["David"]);
        }
        else
        {
            Console.WriteLine("\nDavid is not in the hashtable.");
        }
    }
}
```

### Key Methods and Properties of Hashtable:
- **Add(object key, object value):** Adds a key-value pair to the hashtable.
- **ContainsKey(object key):** Checks if the hashtable contains a specific key.
- **ContainsValue(object value):** Checks if the hashtable contains a specific value.
- **Remove(object key):** Removes the key-value pair with the specified key.
- **Item[object key]:** Indexer to get or set the value for a given key.
- **Count:** Gets the number of key-value pairs in the hashtable.
- **Clear():** Removes all key-value pairs from the hashtable.
- **Keys:** Gets a collection containing all the keys in the hashtable.
- **Values:** Gets a collection containing all the values in the hashtable.
- **Synchronized(Hashtable table):** Provides a thread-safe wrapper around a Hashtable.
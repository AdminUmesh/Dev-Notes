# Generics VS Non-Generics
There are two types of collections available in C#: non-generic collections and generic collections.

The `System.Collections` namespace contains the non-generic collection types and `System.Collections.Generic` namespace includes generic collection types.

## Generics
A generic collection is strongly typed (you can store one type of objects into it) so that we can eliminate runtime type mismatches, it improves the performance by avoiding boxing and unboxing

- Generic collections are generally more efficient in terms of both execution speed and memory usage because they avoid the overhead of boxing and unboxing.

**Examples**

- List: List<T>
- Dictionary: Dictionary<TKey, TValue>
- Queue: Queue<T>
- Stack: Stack<T>
- HashSet: HashSet<T>
- LinkedList: LinkedList<T>
- SortedSet: SortedSet<T>

## Non-Generics
Loosely typed means you can store any type of value in the collection.Result type mismatches are not caught at compile time, you might encounter runtime errors due to incorrect type assumptions.

- Non-generic collections may have performance overhead due to the need for `boxing (converting value types to reference types)` and `unboxing (converting reference types back to value types).`

**Examples**

- ArrayList: ArrayList
- Hashtable: Hashtable
- Queue: Queue
- Stack: Stack
- SortedList: SortedList
- Queue: Queue
- Stack: Stack

# Generic Vs Non-Generic Stack() example

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
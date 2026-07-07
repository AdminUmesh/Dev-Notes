# .NET Memory Management & Garbage Collection

### What is Garbage Collection?

Garbage Collection is the process by which the .NET CLR automatically identifies and removes unused objects from managed memory (Heap), reclaiming memory to prevent memory leaks.

### Why do we need Garbage Collection?

**Without GC:**

- Memory keeps increasing.
- Unused objects remain in memory.
- Application becomes slow.
- Eventually it crashes with OutOfMemoryException.

**GC solves these problems automatically.**

```csharp
// Example
public void Test()
{
    Employee emp = new Employee();

    emp.Name = "Umesh";

} // Method ends
```

When the method ends:

Employee object has no reference pointing to it. `It becomes eligible for Garbage Collection.` `GC will eventually remove it from memory.`


**GC Process**
```csharp
Create Object
      │
      ▼
Heap Memory

      │
Reference Lost

      ▼
Object becomes Garbage

      │
GC runs automatically

      ▼
Memory Reclaimed
```

## What Happens When You Create a Variable?

### 🔹 Stack 

If you write:

``` csharp
int age = 25;
```

What happens?

-   Memory is reserved immediately.
-   Stored in the Stack.
-   When method ends → memory automatically removed.
-   No Garbage Collector involved.

Stack is: - Fast - Automatic cleanup - Used for value types

Value Types: - int - double - bool - struct

------------------------------------------------------------------------

### 🔹 Heap 

If you write:

``` csharp
var person = new Person();
```

What happens?

-   Object is created in Heap.
-   A reference (pointer) is stored in Stack.
-   Garbage Collector manages Heap.

Heap is: - Slower than stack - Flexible size - Managed by GC

------------------------------------------------------------------------

## Garbage Collector (GC) -- The Cleaner

GC is like hotel housekeeping.

It checks: "Is anyone still using this room?"

If NOT → it frees the memory.

Important: GC runs automatically. You don't manually delete objects.

------------------------------------------------------------------------

## Generations (Why GC is Smart)

.NET divides objects into generations.

Because:

Most objects die young.

### 🔹 Gen 0 

Short life objects: - Method variables - Temporary strings - API request
objects

GC cleans Gen 0 very often.

------------------------------------------------------------------------

### 🔹 Gen 1

Objects that survived Gen 0.

Medium lifetime.

------------------------------------------------------------------------

### 🔹 Gen 2 

Long-lived objects: - Static fields - Singleton services - Cached data

Gen 2 cleanup is: - Expensive - Slower - Causes pauses

Senior Thinking: Reducing Gen 2 collections improves performance.

------------------------------------------------------------------------

## LOH (Large Object Heap)

If object size \> 85,000 bytes → goes to LOH.

Example:

``` csharp
byte[] buffer = new byte[100000];
```

LOH characteristics: - Collected only during Gen 2 - Can cause long
pauses - Can fragment memory

Large JSON responses often go to LOH.

------------------------------------------------------------------------

## GC Pauses (Why Performance Drops)

When GC runs: All threads pause.

This is called: Stop-The-World

Gen 0 → small pause Gen 2 → big pause

If your API handles 10,000 requests/sec: Frequent Gen 2 = slow API

------------------------------------------------------------------------

## IDisposable & Using

GC cleans memory.

But NOT:

-   File handles
-   Database connections
-   Network sockets

These are unmanaged resources.

Example:

``` csharp
using(var connection = new SqlConnection())
{
}
```

When block ends: Dispose() is called. Connection released immediately.

------------------------------------------------------------------------

## Finalizers (Danger Zone)

``` csharp
~MyClass()
{
}
```

If you use finalizer: Object survives longer. Moves to special queue.
Often reaches Gen 2.

This increases memory pressure.

Senior rule: Avoid finalizers unless necessary.

------------------------------------------------------------------------


## Why Async Increases Memory

Async creates:

-   Task object
-   State machine
-   Captured variables

Example:

``` csharp
async Task<int> GetData()
{
    await Task.Delay(1000);
    return 1;
}
```

Each call allocates memory.

High traffic API = high allocations.

------------------------------------------------------------------------

## Object Pooling

Instead of creating new object every time:

Reuse old ones.

Used for: - StringBuilder - Buffers - High-performance APIs

Reduces: - Allocations - GC pressure - Gen 2 collections

------------------------------------------------------------------------

## Senior Level Thinking

Junior: "I wrote working API."

Senior: - What is allocation rate? - How many Gen 2 collections per
minute? - Any LOH allocations? - GC pause time?

Tools: - dotnet-counters - dotnet-trace - PerfView

------------------------------------------------------------------------
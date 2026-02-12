# .NET Memory Management & Garbage Collection

### Beginner to Senior Level (Story Based Explanation)

------------------------------------------------------------------------

# 1️⃣ The Very Beginning -- What Happens When You Create a Variable?

Imagine your computer memory is like a hotel.

When your program runs, it asks the hotel: "Give me some rooms to store
my data."

There are TWO main areas in this hotel:

-   Stack (Small, fast rooms)
-   Heap (Big, flexible rooms)

------------------------------------------------------------------------

# 2️⃣ Stack vs Heap (The Hotel Story)

## 🔹 Stack -- Short Stay Rooms

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

## 🔹 Heap -- Long Stay Area

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

# 3️⃣ Garbage Collector (GC) -- The Cleaner

GC is like hotel housekeeping.

It checks: "Is anyone still using this room?"

If NOT → it frees the memory.

Important: GC runs automatically. You don't manually delete objects.

------------------------------------------------------------------------

# 4️⃣ Generations (Why GC is Smart)

.NET divides objects into generations.

Because:

Most objects die young.

## 🔹 Gen 0 -- Baby Objects

Short life objects: - Method variables - Temporary strings - API request
objects

GC cleans Gen 0 very often.

------------------------------------------------------------------------

## 🔹 Gen 1 -- Teenagers

Objects that survived Gen 0.

Medium lifetime.

------------------------------------------------------------------------

## 🔹 Gen 2 -- Old Objects

Long-lived objects: - Static fields - Singleton services - Cached data

Gen 2 cleanup is: - Expensive - Slower - Causes pauses

Senior Thinking: Reducing Gen 2 collections improves performance.

------------------------------------------------------------------------

# 5️⃣ LOH (Large Object Heap)

If object size \> 85,000 bytes → goes to LOH.

Example:

``` csharp
byte[] buffer = new byte[100000];
```

LOH characteristics: - Collected only during Gen 2 - Can cause long
pauses - Can fragment memory

Large JSON responses often go to LOH.

------------------------------------------------------------------------

# 6️⃣ GC Pauses (Why Performance Drops)

When GC runs: All threads pause.

This is called: Stop-The-World

Gen 0 → small pause Gen 2 → big pause

If your API handles 10,000 requests/sec: Frequent Gen 2 = slow API

------------------------------------------------------------------------

# 7️⃣ IDisposable & Using

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

# 8️⃣ Finalizers (Danger Zone)

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

# 9️⃣ Why Memory Leaks Happen in Managed Code

Even with GC, leaks happen.

## 1️⃣ Static Variables

``` csharp
public static List<User> cache = new();
```

Never collected.

------------------------------------------------------------------------

## 2️⃣ Events Not Unsubscribed

If subscriber not removed: Object never freed.

------------------------------------------------------------------------

## 3️⃣ Singleton Holding References

Long-lived service storing short-lived objects.

------------------------------------------------------------------------

# 🔟 Why Async Increases Memory

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

# 1️⃣1️⃣ Object Pooling

Instead of creating new object every time:

Reuse old ones.

Used for: - StringBuilder - Buffers - High-performance APIs

Reduces: - Allocations - GC pressure - Gen 2 collections

------------------------------------------------------------------------

# 1️⃣2️⃣ Senior Level Thinking

Junior: "I wrote working API."

Senior: - What is allocation rate? - How many Gen 2 collections per
minute? - Any LOH allocations? - GC pause time?

Tools: - dotnet-counters - dotnet-trace - PerfView

------------------------------------------------------------------------

# Final Mental Model

Stack → Fast, short life Heap → Managed memory Gen 0 → Young Gen 2 →
Expensive LOH → Heavy objects Finalizer → Slow path Async → Hidden
allocations Static → Possible leak Pooling → Optimization

------------------------------------------------------------------------

End of Notes.
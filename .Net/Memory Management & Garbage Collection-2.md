# .NET Memory Management & Garbage Collection

## Complete Deep-Dive Guide (Beginner → Senior → Architect)

Author: Structured for Backend Developers (Especially ASP.NET Core
Developers)

------------------------------------------------------------------------

# SECTION 1 -- FOUNDATIONS

## 1.1 What Is Memory? (Ultra Basic)

Memory is simply space where your program stores data while running.

When your .NET application starts: - CLR (Common Language Runtime) is
loaded - Memory space is allocated - GC (Garbage Collector) becomes
active

Think of memory like a giant whiteboard. Your variables are writings on
that whiteboard. When no one needs the writing, GC erases it.

------------------------------------------------------------------------

## 1.2 Process Memory Layout

A .NET process contains:

-   Code Segment
-   Stack
-   Heap
-   Static Area
-   Thread Stack (per thread)

Understanding this is critical for performance debugging.

------------------------------------------------------------------------

# SECTION 2 -- STACK MEMORY

## 2.1 What Goes to Stack?

Value types: - int - double - bool - struct - enum

Method calls also use stack frames.

Example:

``` csharp
void Add()
{
    int a = 5;
    int b = 10;
}
```

When method exits → stack frame removed instantly.

No GC involved.

------------------------------------------------------------------------

## 2.2 Stack Frame Internals

Each method call creates: - Return address - Parameters - Local
variables

Stack is extremely fast because: - No fragmentation - Simple pointer
movement - LIFO structure

------------------------------------------------------------------------

# SECTION 3 -- HEAP MEMORY

## 3.1 What Goes to Heap?

Reference types: - class - array - string - object - delegate

Example:

``` csharp
var user = new User();
```

Stack → holds reference Heap → holds actual object

------------------------------------------------------------------------

## 3.2 Heap Allocation Cost

Heap allocation requires: - Finding space - Updating allocation
pointer - Possible GC trigger

High allocation rate = more GC runs.

------------------------------------------------------------------------

# SECTION 4 -- GARBAGE COLLECTOR ARCHITECTURE

## 4.1 Why GC Exists

Manual memory management causes: - Memory leaks - Crashes - Undefined
behavior

GC automates cleanup.

------------------------------------------------------------------------

## 4.2 Mark and Sweep Algorithm

GC works in phases:

1.  Mark (find reachable objects)
2.  Sweep (remove unreachable objects)
3.  Compact (defragment memory)

------------------------------------------------------------------------

## 4.3 Root References

GC starts from:

-   Static fields
-   Local variables
-   CPU registers
-   Active threads

If object reachable from root → survives.

------------------------------------------------------------------------

# SECTION 5 -- GENERATIONAL GC

## 5.1 Why Generations?

Observation: Most objects die young.

So GC divides memory into:

-   Gen 0
-   Gen 1
-   Gen 2

------------------------------------------------------------------------

## 5.2 Gen 0

Short-lived objects. Collected very frequently. Very fast collection.

------------------------------------------------------------------------

## 5.3 Gen 1

Buffer generation. Holds survivors of Gen 0.

------------------------------------------------------------------------

## 5.4 Gen 2

Long-lived objects. Expensive cleanup. Causes major pauses.

Senior optimization: Reduce Gen 2 pressure.

------------------------------------------------------------------------

# SECTION 6 -- LARGE OBJECT HEAP (LOH)

Objects \> 85,000 bytes go here.

Examples: - Large arrays - Big JSON - Image buffers

LOH is: - Not compacted often - Collected only with Gen 2

This causes performance spikes.

------------------------------------------------------------------------

# SECTION 7 -- GC MODES

## 7.1 Workstation GC

Used for desktop apps.

## 7.2 Server GC

Used in ASP.NET Core production. Optimized for multi-core systems.

------------------------------------------------------------------------

# SECTION 8 -- GC PAUSES

When GC runs: Application threads pause.

Gen 0 → tiny pause Gen 2 → noticeable pause

High throughput APIs must control allocations.

------------------------------------------------------------------------

# SECTION 9 -- MEMORY LEAKS IN MANAGED CODE

## 9.1 Static Fields

Static references never collected.

## 9.2 Events

Unsubscribed events keep objects alive.

## 9.3 Singleton Services

Long-lived services storing request objects.

------------------------------------------------------------------------

# SECTION 10 -- IDisposable & Resource Management

GC handles memory only.

Not: - File handles - DB connections - Network sockets

Use IDisposable pattern.

------------------------------------------------------------------------

## 10.1 Dispose Pattern

Implement properly:

-   Public Dispose()
-   Protected Dispose(bool)
-   SuppressFinalize()

------------------------------------------------------------------------

# SECTION 11 -- FINALIZERS

Finalizers slow down GC. Object survives longer. Moves to special queue.

Avoid unless necessary.

------------------------------------------------------------------------

# SECTION 12 -- ASYNC & MEMORY

Async methods create:

-   State machine
-   Task object
-   Captured variables

High RPS systems → high allocations.

Optimization: - ValueTask - Avoid unnecessary async - Minimize closures

------------------------------------------------------------------------

# SECTION 13 -- OBJECT POOLING

Reuse expensive objects.

Examples: - StringBuilder - Buffers - DbContext pooling

Reduces GC pressure.

------------------------------------------------------------------------

# SECTION 14 -- PERFORMANCE MEASUREMENT

Tools:

-   dotnet-counters
-   dotnet-trace
-   PerfView
-   Visual Studio Diagnostic Tools

Important Metrics: - Allocation rate - Gen 2 collections - LOH size - GC
pause time

------------------------------------------------------------------------

# SECTION 15 -- REAL-WORLD OPTIMIZATION STRATEGIES

-   Reduce allocations in loops
-   Avoid boxing
-   Use Span`<T>`{=html}
-   Avoid large temporary strings
-   Stream large data instead of loading fully

------------------------------------------------------------------------

# SECTION 16 -- ADVANCED TOPICS

-   Memory pressure
-   Pinning
-   Fragmentation
-   Struct vs Class tradeoffs
-   Stackalloc
-   ArrayPool`<T>`{=html}
-   GC tuning in production

------------------------------------------------------------------------

# SECTION 17 -- ARCHITECT LEVEL THINKING

Ask:

-   Is this object short-lived?
-   Can this be pooled?
-   Can this be struct?
-   Can this allocation be avoided?
-   Will this hit LOH?

------------------------------------------------------------------------

# FINAL SUMMARY

Stack → Fast & automatic Heap → Managed by GC Gen 0 → Frequent cleanup
Gen 2 → Expensive cleanup LOH → Heavy objects Async → Hidden allocations
Static → Possible memory leak Pooling → Performance optimization

------------------------------------------------------------------------

End of 40--50 Page Deep Dive Notes
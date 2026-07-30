# .NET Memory Management & Garbage Collection

# 1. Memory Basics

Memory is the temporary storage used by a running application.

------------------------------------------------------------------------

# 2. Process Memory Layout

-   **Code Segment** -- Compiled instructions
-   **Static Area** -- Static fields
-   **Heap** -- Reference-type objects
-   **Stack** -- Method calls and value types

------------------------------------------------------------------------

# 3. Stack Memory

Stores:

-   Local variables
-   Method parameters
-   Return addresses
-   Value types

``` csharp
void Add()
{
    int a = 5;
    int b = 10;
}
```

When the method exits, the entire stack frame is removed automatically.

**No Garbage Collection is involved.**

## Characteristics

-   Very fast
-   LIFO (Last In, First Out)
-   Automatic cleanup
-   No fragmentation

------------------------------------------------------------------------

# 4. Heap Memory

Stores reference types.

``` csharp
Person person = new Person();
```

``` text
Stack
person ─────────────► Heap
                     Person Object
```

The stack stores the reference; the actual object lives on the heap.

Reference types include:

-   class
-   string
-   array
-   object
-   delegate

------------------------------------------------------------------------

# 5. Object Creation

``` csharp
public void Test()
{
    Employee emp = new Employee();
    emp.Name = "Umesh";
}
```

Lifecycle:

1.  Object created on Heap.
2.  Reference stored on Stack.
3.  Method exits.
4.  Reference disappears.
5.  Object becomes unreachable.
6.  GC eventually reclaims memory.

------------------------------------------------------------------------

# 6. Garbage Collection

Garbage Collection (GC) automatically removes unreachable objects from
managed memory.

Without GC:

-   Memory keeps increasing.
-   Unused objects remain.
-   Application slows down.
-   Eventually throws `OutOfMemoryException`.

GC automatically:

-   Finds unused objects.
-   Reclaims memory.
-   Compacts the heap.

## GC Flow

``` text
Create Object
      │
      ▼
Heap Memory
      │
Reference Lost
      ▼
Object becomes Garbage
      │
GC Runs
      ▼
Memory Reclaimed
```

------------------------------------------------------------------------

# 7. Generational Garbage Collection

Most objects die young, so .NET divides the heap into generations.

## Gen 0

-   Short-lived objects
-   Frequent collection
-   Very fast

Examples:

-   Temporary objects
-   Request objects
-   Small strings

## Gen 1

Objects that survived Gen 0.

## Gen 2

Long-lived objects:

-   Static fields
-   Singleton services
-   Cached data

Gen 2 collection is slower and more expensive.

------------------------------------------------------------------------

# 8. Large Object Heap (LOH)

Objects larger than **85,000 bytes** go to the LOH.

``` csharp
byte[] buffer = new byte[100000];
```

Characteristics:

-   Collected only with Gen 2
-   Can fragment memory
-   Longer GC pauses

------------------------------------------------------------------------

# 9. Managed vs Unmanaged Resources

GC cleans **managed memory** only.

Not cleaned by GC:

-   Database connections
-   File handles
-   Network sockets
-   OS handles

------------------------------------------------------------------------

# 10. IDisposable

Use `IDisposable` for unmanaged resources.

``` csharp
using(var connection = new SqlConnection())
{
    // use connection
}
```

`Dispose()` is called automatically at the end of the `using` block.

------------------------------------------------------------------------

# 11. Finalizers

``` csharp
~MyClass()
{
}
```

Avoid finalizers unless necessary because they delay collection and
increase memory pressure.

------------------------------------------------------------------------

# 12. Memory Leaks

Common causes:

-   Static fields
-   Event subscriptions not removed
-   Long-lived singleton references
-   Unlimited caches

------------------------------------------------------------------------

# 13. Object Pooling

Reuse expensive objects instead of recreating them.

Examples:

-   StringBuilder
-   ArrayPool`<T>`{=html}
-   DbContext Pooling

Benefits:

-   Fewer allocations
-   Lower GC pressure
-   Better performance

------------------------------------------------------------------------

# 14. Performance Optimization

-   Reduce allocations inside loops
-   Avoid boxing
-   Use `Span<T>`
-   Use `stackalloc`
-   Stream large files
-   Avoid LOH allocations

------------------------------------------------------------------------

# 15. Senior Thinking

Ask yourself:

-   Can this allocation be avoided?
-   Can the object be pooled?
-   Will it reach LOH?
-   Is Gen 2 growing?
-   Is a struct more appropriate?

------------------------------------------------------------------------

# 16. Interview Summary

-   Stack → Fast, automatic cleanup, value types.
-   Heap → Stores reference types.
-   GC → Mark, Sweep, Compact.
-   Gen 0 → Frequent collection.
-   Gen 1 → Intermediate generation.
-   Gen 2 → Long-lived objects.
-   LOH → Objects \>85 KB.
-   IDisposable → Releases unmanaged resources.
-   Finalizers → Use sparingly.
-   Object Pooling → Reduces allocations.
-   Monitor using `dotnet-counters`, `PerfView`, and `dotnet-trace`.

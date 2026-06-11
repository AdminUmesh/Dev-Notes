#### **What happens when you run a C# program?**
**Step 1:** OS creates a Process
- Inside every process there is at least one thread.

## 1. What is a Thread?
A Thread is the actual worker that executes code.

-   A **thread** is the smallest unit of execution inside a
    **process**.\
-   **Process** = running instance of an application.\
-   Each process has **at least one thread** → the **Main thread**.\
-   Threads are managed & scheduled by the **Operating System (OS)**.

```bash
Process
 └── Thread
       └── Executes code
```

------------------------------------------------------------------------

## 2. Thread in C# / .NET

-   C# (language) does **not** directly create threads.\
-   **.NET CLR (Common Language Runtime)** provides libraries
    (`System.Threading`) to work with threads.\


## The Main Thread
Every C# application starts with one thread.
```c#
class Program
{
    static void Main()
    {
        Console.WriteLine("Start");
    }
}
```

## 5. Why do we need multiple threads?
Suppose you are building a UI application.

**User clicks:**
Download File

**Download takes:**
30 seconds

If UI uses only one thread: UI freezes because the thread is busy downloading.

## 6. Multiple Threads
Instead of:

```bash
Main Thread
 ├── UI
 └── Download
```

We can do:

```bash
Main Thread
 └── UI

Worker Thread
 └── Download
```

**Now:**
UI remains responsive & Download runs separately

## 3. Main Concepts

-   **Main Thread** → automatically created when program starts
    (`Main()` runs on it).\
-   **Worker Threads** → created explicitly or by .NET runtime.\
-   **Thread.Sleep(ms)** → pauses execution of that thread.\
-   **Thread.CurrentThread.ManagedThreadId** → gives unique ID of the
    current thread.

------------------------------------------------------------------------

## Thread Execution Example
```c#
using System.Threading;

Thread t = new Thread(Print);

t.Start();

Console.WriteLine("Main Thread");

void Print()
{
    Console.WriteLine("Worker Thread");
}
```

**Possible output:**

```bash
Main Thread
Worker Thread

or

Worker Thread
Main Thread
````
**Why?**

Because the OS decides which thread gets CPU first.This is called: `Scheduling`

## CPU and Threads

Suppose CPU has: 4 cores
```bash
Core 1
Core 2
Core 3
Core 4
```

OS can run threads on different cores.

**Example:**
```c#
Thread A → Core 1
Thread B → Core 2
Thread C → Core 3
Thread D → Core 4
```
Now multiple threads truly run simultaneously.

## What if there are more threads than cores?

Suppose: 4 cores & 20 threads

CPU rapidly switches:
```base
Thread 1
Thread 2
Thread 3
Thread 4
...
```
Thousands of times per second. This is called:`Context Switching`

It gives the illusion that all threads are running together.

## Thread Life Cycle
```bash
Created
   ↓
Ready
   ↓
Running
   ↓
Blocked (waiting)
   ↓
Running Again
   ↓
Terminated
```

# 4. Ways to Work with Threads in .NET

### 4.1 Manual Threads

``` csharp
Thread t = new Thread(() => Console.WriteLine("Hello from new thread"));
t.Start();
```

### 4.2 ThreadPool

-   Uses a pool of pre-created threads.\
-   Better for **short-lived tasks**.

``` csharp
using System.Threading;

ThreadPool.QueueUserWorkItem(state =>
{
    Console.WriteLine($"Running on Thread: {Thread.CurrentThread.ManagedThreadId}");
});
```

### 4.3 Task Parallel Library (TPL)

-   Abstraction over threads & threadpool.\
-   Easy to run asynchronous work.

``` csharp
using System;
using System.Threading.Tasks;

class Program
{
    static void DoWork()
    {
        Console.WriteLine($"Task running on Thread: {Thread.CurrentThread.ManagedThreadId}");
    }

    static void Main()
    {
        Task t = Task.Run(DoWork);
        t.Wait(); // Wait until task completes
    }
}
```

### 4.4 Async / Await

-   Modern way to write asynchronous code.\
-   Uses TPL + state machine under the hood.

``` csharp
using System;
using System.Threading.Tasks;

class Program
{
    static async Task DoWorkAsync()
    {
        await Task.Delay(1000); // Non-blocking wait
        Console.WriteLine($"Async work on Thread: {Thread.CurrentThread.ManagedThreadId}");
    }

    static async Task Main()
    {
        await DoWorkAsync();
        Console.WriteLine("Main method done");
    }
}
```

------------------------------------------------------------------------

## 5. Best Practices

-   ✅ Use **Task / async/await** → easier, safer, less error-prone.\
-   ✅ Use **ThreadPool** for background short work.\
-   ❌ Avoid creating too many manual threads → high memory + context
    switching.\
-   Always clean up with `.Join()` or `.Wait()` if you need
    synchronization.

------------------------------------------------------------------------

## 6. Thread Limits

-   Each thread takes \~1MB stack space in .NET.\
-   Thousands of threads can crash performance (context switching
    overhead).\
-   Use **Task / ThreadPool** for scalability.

------------------------------------------------------------------------

## 7. Thread vs Process vs Async

-   **Process** = whole application instance.\
-   **Thread** = execution unit inside process.\
-   **Async/await** = logical concurrency (not always a new thread).

------------------------------------------------------------------------

✅ **Summary:**\
- Thread is an **OS concept**, exposed in .NET.\
- Main thread runs `Main()`.\
- You can use manual `Thread`, `ThreadPool`, `Task`, or `async/await`.\
- Best practice → prefer `Task` or `async/await` over raw threads.

## 1. Process vs Thread
Process	Thread
Running instance of an application	Smallest unit of execution
Has its own memory	Shares process memory
Heavyweight	Lightweight
Example: Chrome.exe	Chrome tab worker thread
Process
 ├── Thread 1
 ├── Thread 2
 └── Thread 3

Interview: A process can contain multiple threads.

## 2. Foreground vs Background Thread

Foreground Thread

Keeps application alive.
Application won't exit until it finishes.

Background Thread

Runs in background.
Automatically terminated when all foreground threads end.
thread.IsBackground = true;

## 3. ThreadPool

A pool of reusable worker threads managed by .NET.

Instead of:

new Thread(...)

.NET reuses existing threads.

Benefits:

Faster
Less memory
Better performance
ThreadPool.QueueUserWorkItem(...)

## 4. Task

Higher-level abstraction over threads.

Task.Run(() =>
{
    DoWork();
});

Benefits:

Easier to use
Uses ThreadPool internally
Supports async/await

Interview: Task is not a thread; it represents a unit of work.

## 5. async / await

Used for non-blocking asynchronous operations.

await httpClient.GetAsync(url);

Benefits:

Keeps UI responsive
Improves server scalability
Doesn't block threads while waiting

Interview: async/await doesn't necessarily create a new thread.

## 6. Synchronization

Used when multiple threads access shared data.

lock
lock(obj)
{
    balance -= 100;
}

Only one thread enters at a time.

Monitor

More control than lock.

Monitor.Enter(obj);
try
{
    // code
}
finally
{
    Monitor.Exit(obj);
}
Mutex

Works across processes.

Mutex mutex = new Mutex();

Only one process/thread can access resource.

Semaphore

Allows limited number of threads.

SemaphoreSlim sem = new SemaphoreSlim(3);

Example:

Only 3 users allowed simultaneously.

## 7. Race Condition

Occurs when multiple threads modify shared data simultaneously.

Example:

balance = balance - 100;

Thread A and B execute together.

Expected:

1000 → 800

Actual:

1000 → 900

Possible due to timing.

Solution:

lock
Mutex
Transaction

## 8. Deadlock

Two threads wait forever for each other.

Thread A
  Lock Resource1
  Wait Resource2

Thread B
  Lock Resource2
  Wait Resource1

Result:

Application Stuck Forever

This is Deadlock.

## 9. Parallel Programming

Runs multiple operations simultaneously.

Parallel.ForEach
Parallel.ForEach(items, item =>
{
    Process(item);
});

Uses multiple CPU cores.

Best for:

CPU-intensive work
Image processing
Large calculations
PLINQ

Parallel LINQ

var result = numbers
                .AsParallel()
                .Where(x => x > 100)
                .ToList();

LINQ query executes in parallel.

One-Line Interview Revision
Topic	One-Line Definition
Process	Running instance of an application
Thread	Smallest unit of execution inside a process
Foreground Thread	Keeps application alive
Background Thread	Stops when application exits
ThreadPool	Reusable threads managed by .NET
Task	Represents an asynchronous unit of work
async/await	Non-blocking asynchronous programming
lock	Allows one thread at a time
Monitor	Advanced version of lock
Mutex	Lock across processes
Semaphore	Allows limited concurrent access
Race Condition	Multiple threads causing inconsistent data
Deadlock	Threads waiting forever for each other
Parallel.ForEach	Executes loop iterations in parallel
PLINQ	Executes LINQ queries in parallel

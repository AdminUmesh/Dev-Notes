# 🧵 Threading in C# / .NET --

## 1. What is a Thread?

-   A **thread** is the smallest unit of execution inside a
    **process**.\
-   **Process** = running instance of an application.\
-   Each process has **at least one thread** → the **Main thread**.\
-   Threads are managed & scheduled by the **Operating System (OS)**.

------------------------------------------------------------------------

## 2. Thread in C# / .NET

-   C# (language) does **not** directly create threads.\
-   **.NET CLR (Common Language Runtime)** provides libraries
    (`System.Threading`) to work with threads.\
-   When you use `.NET Thread APIs`, the **OS creates the actual
    thread**.

**Example:**

``` csharp
using System;
using System.Threading;

class Program
{
    static void PrintNumbers()
    {
        for (int i = 1; i <= 5; i++)
        {
            Console.WriteLine($"Worker Thread: {i}");
            Thread.Sleep(500);
        }
    }

    static void Main()
    {
        Thread t = new Thread(PrintNumbers);
        t.Start();

        for (int i = 1; i <= 5; i++)
        {
            Console.WriteLine($"Main Thread: {i}");
            Thread.Sleep(500);
        }
    }
}
```

------------------------------------------------------------------------

## 3. Main Concepts

-   **Main Thread** → automatically created when program starts
    (`Main()` runs on it).\
-   **Worker Threads** → created explicitly or by .NET runtime.\
-   **Thread.Sleep(ms)** → pauses execution of that thread.\
-   **Thread.CurrentThread.ManagedThreadId** → gives unique ID of the
    current thread.

------------------------------------------------------------------------

## 4. Ways to Work with Threads in .NET

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

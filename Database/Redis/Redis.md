# What is Redis?

Redis is an in-memory NoSQL database used mainly for:

- Caching
- Session storage
- Real-time applications
- Pub/Sub messaging
- Queue systems

Official Website:
https://redis.io

---

# Why Redis is Fast

Redis stores data in RAM (memory), so it is much faster than traditional databases.

Traditional databases:
- Read/write from disk
- Slower for repeated requests

Redis:
- Stores data in memory
- Microsecond response time

---

# When Should You Use Redis?

## Use Redis When:
- You need caching
- Your APIs are slow
- You have many users
- You need real-time updates
- You want to reduce SQL Server load

## Do NOT Use Redis As Main Database For:
- Complex joins
- Permanent transactional systems
- Heavy relational queries

---

# Redis vs SQL Server

| Feature | Redis | SQL Server |
|---|---|---|
| Type | NoSQL | Relational |
| Storage | RAM | Disk |
| Speed | Extremely Fast | Moderate |
| Joins | No | Yes |
| Best Use | Cache | Permanent Data |

---

# Install Redis

## Option 1: Docker

```bash
docker run --name redis -p 6379:6379 redis
```

## Option 2: Windows

Use:
- WSL
- Docker Desktop
- Redis Windows Port

---

# Redis Default Port

```text
6379
```

---

# NuGet Packages for .NET

## Main Redis Package

```powershell
Install-Package StackExchange.Redis
```

GitHub:
https://github.com/StackExchange/StackExchange.Redis

---

# ASP.NET Core Redis Cache Package

```powershell
Install-Package Microsoft.Extensions.Caching.StackExchangeRedis
```

---

# Basic Redis Connection in .NET

```csharp
using StackExchange.Redis;

var redis = ConnectionMultiplexer.Connect("localhost:6379");

IDatabase db = redis.GetDatabase();
```

---

# Store Data

```csharp
db.StringSet("name", "Umesh");
```

---

# Read Data

```csharp
string name = db.StringGet("name");

Console.WriteLine(name);
```

---

# Store Data with Expiry

```csharp
db.StringSet(
    "otp",
    "123456",
    TimeSpan.FromMinutes(5));
```

After 5 minutes, Redis automatically deletes the key.

---

# ASP.NET Core Setup

## Program.cs

```csharp
builder.Services.AddStackExchangeRedisCache(options =>
{
    options.Configuration = "localhost:6379";
});
```

---

# Using IDistributedCache

```csharp
using Microsoft.Extensions.Caching.Distributed;

public class ProductService
{
    private readonly IDistributedCache _cache;

    public ProductService(IDistributedCache cache)
    {
        _cache = cache;
    }
}
```

---

# Save JSON Data

```csharp
var productJson = JsonConvert.SerializeObject(product);

await _cache.SetStringAsync(
    "product:1",
    productJson,
    new DistributedCacheEntryOptions
    {
        AbsoluteExpirationRelativeToNow = TimeSpan.FromMinutes(10)
    });
```

---

# Read JSON Data

```csharp
var cachedProduct = await _cache.GetStringAsync("product:1");

if(cachedProduct != null)
{
    var product = JsonConvert.DeserializeObject<Product>(cachedProduct);
}
```

---

# Common Redis Use Cases

## 1. API Response Cache

```text
User Request
→ Redis
→ SQL Server
```

---

## 2. OTP Storage

```text
otp:9999 → 123456
```

---

## 3. User Session

```text
session:user1 → token_data
```

---

## 4. Real-Time Dashboard

Used in:
- Airport systems
- Stock market apps
- Kitchen display systems

---

# Redis Data Types

## String

```text
name → Umesh
```

## Hash

```text
user:1
  name → Umesh
  age → 25
```

## List

```text
notifications
```

## Set

Unique values only.

## Sorted Set

Leaderboard systems.

---

# Important Redis Concepts

## TTL (Time To Live)

Automatically removes old cache data.

## Pub/Sub

One service publishes message, others receive instantly.

## Persistence

Redis can save data to disk.

---

# Best Practice

## Recommended Architecture

```text
Client
 ↓
API
 ↓
Redis Cache
 ↓
SQL Server
```

---

# Learning Path

## Beginner
1. Install Redis
2. Connect in .NET
3. String operations
4. Cache example
5. Expiry / TTL

## Intermediate
1. Hashes
2. Lists
3. Pub/Sub
4. Distributed Cache

## Advanced
1. Redis Cluster
2. Persistence
3. Streams
4. Distributed Locking

---

# Interview Questions

## What is Redis?

Redis is an in-memory NoSQL database commonly used for caching and high-speed data access.

## Why is Redis Fast?

Because it stores data in RAM.

## Redis vs IMemoryCache?

IMemoryCache:
- Local to app
- Single server only

Redis:
- Distributed
- Shared across servers
- Better for production

---

# Final Recommendation

For small projects:
- Use IMemoryCache

For production systems:
- Use Redis

For scalable enterprise apps:
- Use Redis + IMemoryCache together

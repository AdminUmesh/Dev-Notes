# Dapper in .NET – Complete Notes (ADO.NET Alternative)

Author: Umesh Kumar Singh  
Level: Beginner → Intermediate  
Based on: ADO.NET concepts (Connection, Command, Reader, Adapter)

---

## What is Dapper

**Dapper** is a **micro-ORM** (Object Relational Mapper) for .NET created by the StackOverflow team.

- It is a **lightweight wrapper over ADO.NET**
- Uses **raw SQL** but maps results to C# objects automatically
- Extremely **fast** compared to traditional ORMs
- No tracking, no magic – **you control SQL**

> 👉 Dapper internally uses **ADO.NET (SqlConnection, SqlCommand, SqlDataReader)**

---

## Where Dapper Fits

```
ADO.NET (Low-level, verbose)
        ↓
Dapper (Fast, simple, SQL-first)
        ↓
Entity Framework Core (High-level, abstraction, tracking)
```

---

## Why Use Dapper

- ✔ High performance
- ✔ Full control over SQL queries
- ✔ Very small learning curve
- ✔ Perfect for APIs & reporting systems
- ❌ No change tracking
- ❌ No LINQ abstraction like EF Core

---

## How to Add Dapper in .NET (NuGet)

### Using Visual Studio
```
Right Click Project → Manage NuGet Packages → Browse → Dapper → Install
```

### Using Package Manager Console
```powershell
Install-Package Dapper
```

### Using .NET CLI
```bash
dotnet add package Dapper
```

Namespace:
```csharp
using Dapper;
```

---

## Core Concept of Dapper

> **Dapper = SQL + Mapping**

You write SQL → Dapper executes it → Maps result to object

---

## Dapper Uses ADO.NET Connection

```csharp
using System.Data;
using Microsoft.Data.SqlClient;

string cs = "data source=DEV-UMESH\SQLEXPRESS;Initial Catalog=TestDB;Integrated Security=True";

using IDbConnection con = new SqlConnection(cs);
```

✔ Dapper does **NOT** manage connection creation  
✔ You must open/close (or use `using`)

---

## Dapper Main Methods

| Method | Purpose |
|------|--------|
| Query<T>() | Multiple rows |
| QueryFirst<T>() | First row |
| QueryFirstOrDefault<T>() | First or null |
| QuerySingle<T>() | Exactly one row |
| QuerySingleOrDefault<T>() | One or null |
| Execute() | Insert/Update/Delete |
| ExecuteScalar<T>() | Single value (count, max, sum) |

---

## Reading Multiple Rows (Like ExecuteReader)

### SQL Table
```sql
SELECT Id, Name, Salary FROM Employee;
```

### C# Model
```csharp
class Employee
{
    public int Id { get; set; }
    public string Name { get; set; }
    public decimal Salary { get; set; }
}
```

### Dapper Code
```csharp
var employees = con.Query<Employee>("SELECT * FROM Employee").ToList();

foreach (var emp in employees)
{
    Console.WriteLine($"{emp.Id} {emp.Name} {emp.Salary}");
}
```

✔ Best replacement of **SqlDataReader**

---

## Reading Single Row

### QueryFirst
```csharp
var emp = con.QueryFirst<Employee>(
    "SELECT * FROM Employee WHERE Id = @Id",
    new { Id = 1 }
);
```

### QueryFirstOrDefault (Safe)
```csharp
var emp = con.QueryFirstOrDefault<Employee>(
    "SELECT * FROM Employee WHERE Id = @Id",
    new { Id = 100 }
);
```

---

## Reading Single Column / Scalar Value (COUNT, MAX)

### BEST METHOD → ExecuteScalar<T>

```csharp
int count = con.ExecuteScalar<int>(
    "SELECT COUNT(*) FROM Employee"
);
```

### Other Examples
```csharp
decimal maxSalary = con.ExecuteScalar<decimal>(
    "SELECT MAX(Salary) FROM Employee"
);
```

✔ Best replacement for **ExecuteScalar()** in ADO.NET

---

## Insert Data (ExecuteNonQuery Equivalent)

```csharp
string sql = "INSERT INTO Employee (Name, Salary) VALUES (@Name, @Salary)";

int rows = con.Execute(sql, new
{
    Name = "Umesh",
    Salary = 50000
});

if (rows > 0)
{
    Console.WriteLine("Inserted Successfully");
}
```

✔ Uses **Execute()**

---

## Update Data

```csharp
string sql = "UPDATE Employee SET Salary=@Salary WHERE Id=@Id";

con.Execute(sql, new
{
    Id = 1,
    Salary = 60000
});
```

---

## Delete Data

```csharp
con.Execute(
    "DELETE FROM Employee WHERE Id=@Id",
    new { Id = 1 }
);
```

---

## Stored Procedure with Dapper

### SQL Stored Procedure
```sql
CREATE PROC GetEmployeeById
    @Id INT
AS
BEGIN
    SELECT * FROM Employee WHERE Id = @Id
END
```

### C# Call
```csharp
var emp = con.QueryFirstOrDefault<Employee>(
    "GetEmployeeById",
    new { Id = 1 },
    commandType: CommandType.StoredProcedure
);
```

---

## Passing Multiple Parameters

```csharp
var result = con.Query<Employee>(
    "SELECT * FROM Employee WHERE Salary > @Sal AND Name LIKE @Name",
    new { Sal = 30000, Name = "%U%" }
);
```

---

## Handling Multiple Result Sets

```csharp
using var multi = con.QueryMultiple(
    "SELECT * FROM Employee; SELECT * FROM Department;"
);

var employees = multi.Read<Employee>().ToList();
var departments = multi.Read<Department>().ToList();
```

✔ Replacement of `NextResult()` in SqlDataReader

---

## Dapper vs ADO.NET vs EF Core

| Feature | ADO.NET | Dapper | EF Core |
|------|-------|-------|--------|
| Performance | Fast | ⚡ Fastest | Slower |
| SQL Control | Full | Full | Limited |
| Boilerplate | High | Low | Very Low |
| Learning Curve | High | Easy | Medium |
| Tracking | ❌ | ❌ | ✔ |
| Best For | Low-level | APIs, Reports | Business Apps |

---

## When to Use What

### Use ADO.NET When
- You want full low-level control
- Learning fundamentals

### Use Dapper When
- High-performance APIs
- Complex SQL queries
- Stored procedures
- Reporting systems

### Use EF Core When
- Rapid development
- CRUD-heavy apps
- Domain-driven design
- Change tracking needed

---

## Dapper Best Practices

- ✔ Always use **parameterized queries**
- ✔ Use `QueryFirstOrDefault` instead of `QueryFirst`
- ✔ Use `ExecuteScalar<T>` for count/sum
- ✔ Keep SQL in stored procedures for large apps
- ❌ Avoid string concatenation in SQL

---

## Summary

- Dapper is **ADO.NET + Mapping**
- Faster than EF Core
- Cleaner than ADO.NET
- Perfect balance between performance & control

---
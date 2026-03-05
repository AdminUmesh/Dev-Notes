# LINQ vs EF Core

## 1. What is LINQ?

**LINQ (Language Integrated Query)** is a feature of C#/.NET that allows
querying data using a consistent syntax.

It can work with multiple data sources: - In-memory collections (List,
Array) - Databases (via EF Core) - XML - DataSets

Example (LINQ to Objects):

``` csharp
var numbers = new List<int> {1,2,3,4};
var result = numbers.Where(x => x > 2).ToList();
```

Here LINQ runs **in memory**.

------------------------------------------------------------------------

## 2. What is EF Core?

**Entity Framework Core (EF Core)** is Microsoft's ORM (Object
Relational Mapper) for .NET.

It allows developers to work with database data using C# objects instead
of writing SQL.

Installed using NuGet:

    Microsoft.EntityFrameworkCore

Example:

``` csharp
var users = _context.Users.Where(u => u.Age > 25).ToList();
```

EF Core converts the LINQ query into SQL and executes it in the
database.

------------------------------------------------------------------------

## 3. Relationship Between LINQ and EF Core

LINQ is a **query language feature**.

EF Core is a **LINQ provider for databases**.

Flow:

    LINQ Query
         ↓
    EF Core translates to SQL
         ↓
    Database executes query

------------------------------------------------------------------------

## 4. Types of LINQ

  Type               Runs Where
  ------------------ ----------------------
  LINQ to Objects    Memory
  LINQ to Entities   Database via EF Core
  LINQ to XML        XML documents

------------------------------------------------------------------------

## 5. Key Differences

  Feature                  LINQ               EF Core
  ------------------------ ------------------ --------------------------------
  Type                     Language feature   ORM framework
  Created By               Microsoft          Microsoft
  Purpose                  Query data         Map database tables to objects
  SQL Required             No                 No
  Works Without Database   Yes                No
  Installed via NuGet      No                 Yes

------------------------------------------------------------------------

## 6. Simple Understanding

-   **LINQ** = Query syntax in C#
-   **EF Core** = Tool that uses LINQ to query databases

Example:

``` csharp
_context.Users.Where(x => x.Age > 30)
```

Here: - `.Where()` → LINQ - `_context.Users` → EF Core DbSet - SQL is
generated automatically.

------------------------------------------------------------------------

## Final Summary

-   LINQ is part of **.NET**
-   EF Core is a **database ORM**
-   EF Core **uses LINQ** to generate SQL queries

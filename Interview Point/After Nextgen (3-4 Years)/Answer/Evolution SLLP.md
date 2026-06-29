# .NET Interview Notes (Based on My Interview)

## Overview

These notes include: - Answer - Interviewer's target - Difficulty -
Common follow-up doubts

------------------------------------------------------------------------

# 1. Brief Introduction

## Difficulty

⭐ Easy

## Interviewer's Target

-   Communication
-   Confidence
-   Relevant experience

## Sample

> My name is Umesh Kumar Singh. I have around 3 years of experience as a
> Full Stack Developer working with .NET Core, Angular, SQL Server,
> Dapper, EF Core and Azure. I develop REST APIs, optimize SQL, create
> stored procedures and build enterprise applications.

## Common Doubts

### Q: Should I tell my entire life story?

No. Keep it 60--90 seconds.

------------------------------------------------------------------------

# 2. Repository Pattern

## Difficulty

⭐⭐⭐ Medium

## Target

Architecture knowledge.

## Answer

Repository is a layer between Business Logic and Database.

    Controller
        ↓
    Service
        ↓
    Repository
        ↓
    Database

Repository hides data access implementation.

## Benefits

-   Separation of concerns
-   Easy testing
-   Centralized data access
-   Easier maintenance

## Doubts

### Q: Why not write SQL in Controller?

Because Controller should only handle HTTP requests.

### Q: Repository vs DbContext?

Repository wraps DbContext and exposes business-friendly methods.

------------------------------------------------------------------------

# 3. Why Layers in a Monolith?

## Difficulty

⭐⭐⭐⭐

## Target

Architecture thinking.

## Important

Monolith means **one deployment**, not **one project**.

## Doubt

### Q: Why not just create folders?

Folders organize code.

Layers/projects enforce dependencies.

Example:

    API
     ↓
    Business
     ↓
    Data

API cannot directly access Data if there is no project reference.

Folders cannot stop this:

``` csharp
Controller
{
    SqlConnection con = ...
}
```

Projects can.

## Another Doubt

### Q: Can't I reuse business logic in the same project?

Yes.

For small projects, a single project with folders is enough.

Separate layers become valuable when:

-   many developers work together
-   business logic is reused by API, Console, Background Jobs
-   architecture rules must be enforced
-   project becomes large

## Best Interview Answer

Deployment and code organization are different concepts.

Layering improves maintainability, testing, reuse and dependency
control.

------------------------------------------------------------------------

# 4. Count 'S' Without Loop

## Difficulty

⭐⭐⭐

``` sql
SELECT
LEN('Umesh Kumar Singh')
-
LEN(REPLACE('Umesh Kumar Singh','S',''))
```

Result: 2

------------------------------------------------------------------------

# 5. Dependency Injection & Singleton

## Difficulty

⭐⭐⭐⭐

## Why DI?

Without DI

    Service
       ↓
    new Repository()

Tightly coupled.

With DI

    Service
       ↓
    IRepository

Loosely coupled.

## Benefits

-   Easy testing
-   Easy replacement
-   Low coupling

## Singleton

One object for the application lifetime.

Examples

-   Logger
-   Cache
-   Configuration

## Doubts

### Q: When should I NOT use Singleton?

When service stores user-specific state.

Example: Shopping Cart.

Use Scoped instead.

------------------------------------------------------------------------

# 6. Rate Yourself

## Difficulty

⭐

Never say 10/10.

Example

SQL : 8/10

C# : 8/10

Mention strengths and learning areas.

------------------------------------------------------------------------

# 7. Temporary Table vs CTE

## Difficulty

⭐⭐⭐⭐

  CTE                  Temp Table
  -------------------- ----------------------------------
  Virtual              Physical in tempdb
  One statement        Whole session
  No indexes           Supports indexes
  Good for recursion   Good for large intermediate data

## Doubts

### Q: Which is faster?

Depends.

Small query → CTE

Large reusable dataset → Temp Table

------------------------------------------------------------------------

# 8. Create Temporary Table

``` sql
CREATE TABLE #Employee
(
    Id INT,
    Name VARCHAR(100)
)
```

Global Temp

``` sql
CREATE TABLE ##Employee
```

------------------------------------------------------------------------

# 9. Stored Procedure Parameters

-   Input
-   Output
-   Return Value

Example

``` sql
CREATE PROC Test
@Id INT,
@Total INT OUTPUT
AS
BEGIN
RETURN 1
END
```

------------------------------------------------------------------------

# 10. Slow Stored Procedure in Production

## Difficulty

⭐⭐⭐⭐⭐

## Steps

1.  Identify slow query
2.  Check execution plan
3.  Check missing indexes
4.  Check parameter sniffing
5.  Check blocking
6.  Check statistics
7.  Review IO and CPU
8.  Break huge query if needed

## Doubts

### Q: Should I run SET STATISTICS in Production?

No.

Use Query Store, DMVs and execution plans whenever possible.

------------------------------------------------------------------------

# 11. Dapper vs EF Core

  Dapper             EF Core
  ------------------ --------------------
  Micro ORM          Full ORM
  Faster             Easier development
  Manual SQL         LINQ
  Less abstraction   Change tracking

## Which one to use?

Dapper

-   Performance critical
-   Complex Stored Procedures

EF Core

-   CRUD
-   Rapid development

------------------------------------------------------------------------

# 12. ORM

ORM converts tables into C# objects.

    Employee Table

    ↓

    Employee Class

Examples

-   Entity Framework Core
-   Dapper (Micro ORM)
-   NHibernate

------------------------------------------------------------------------

# 13. Which ORM Have You Used?

Answer honestly.

Example

"I have used Entity Framework Core and Dapper."

------------------------------------------------------------------------

# 14. Abstract Class vs Interface

  Interface              Abstract Class
  ---------------------- ----------------------
  Contract               Base implementation
  Multiple inheritance   Single inheritance
  No instance            Can have constructor

## Doubt

### When Interface?

Different classes share capability.

Example

IPayment

### When Abstract?

Classes share common implementation.

Example

Employee

------------------------------------------------------------------------

# 15. Singleton Pattern

One instance during application lifetime.

    builder.Services.AddSingleton<ILogger, Logger>();

Do not use Singleton for mutable user-specific data.

------------------------------------------------------------------------

# 16. Partial Class

Split one class into multiple files.

    Employee.cs

    Employee.Validation.cs

    Employee.Helper.cs

Compiler combines them.

------------------------------------------------------------------------

# Final Tips

## Most Important Topics

⭐⭐⭐⭐⭐

-   SQL Performance
-   Dependency Injection
-   Repository Pattern
-   Layered Architecture
-   Stored Procedure Optimization

## Medium Priority

-   ORM
-   Singleton
-   Abstract vs Interface
-   Temp Table vs CTE

## Easy

-   Partial Class
-   Introduction
-   Stored Procedure Parameters



# Interview Short Notes

## 1. How many ways to create a table?

-   Using SSMS Table Designer
-   Using `CREATE TABLE` SQL query
-   Using Entity Framework (Code First Migration)
-   Using SQL Scripts during deployment

## 4. How to create a table without SSMS?

Use SQL:

``` sql
CREATE TABLE Employee(
    Id INT PRIMARY KEY,
    Name NVARCHAR(100),
    Salary DECIMAL(10,2)
);
```

Run it using sqlcmd, Azure Data Studio, Visual Studio SQL tools, or from
an application.

## 5. How to create/run/open an MVC Project?

-   Create:

``` bash
dotnet new mvc -n MyMvcApp
```

-   Open in Visual Studio or VS Code.
-   Restore packages:

``` bash
dotnet restore
```

-   Run:

``` bash
dotnet run
```

or press **F5** in Visual Studio.

## 6. What did you do in the DMS project?

-   Developed APIs in .NET Core.
-   File upload/download.
-   Antivirus scanning using ClamAV before saving files.
-   Metadata & document history.
-   Folder/document management.
-   Share links with permissions and expiry.
-   Role-based authorization.
-   Database operations using EF Core/Dapper.

## 7. What work have you done in SQL and C#?

### SQL

-   Stored Procedures
-   Joins, CTEs, Views
-   Functions
-   Indexes
-   Query optimization
-   Database design
-   CRUD operations

### C

-   ASP.NET Core Web API
-   MVC
-   Dependency Injection
-   Entity Framework Core & Dapper
-   JWT Authentication
-   LINQ
-   Exception Handling
-   File Upload APIs
-   Logging (Serilog)
-   REST APIs

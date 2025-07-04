# Entity Framework (EF)

## 🔸 What is Entity Framework?

Entity Framework (EF) is a popular .NET ORM (Object Relational Mapper) that lets you work with databases using C# code instead of SQL queries.

It helps you:

- Connect to a database  
- Create tables from your C# classes  
- Query and update data using LINQ  
- Avoid writing raw SQL in most cases  

---

## 🔧 Example: Without SQL, Using EF

Let’s say you have a table in the database named `Employees`.  
With Entity Framework, you can write:

```csharp
var employees = dbContext.Employees
                         .Where(e => e.Salary > 50000)
                         .ToList();
```

No need to write:

```sql
SELECT * FROM Employees WHERE Salary > 50000;
```

---

## 🔨 Key Features of EF

| Feature                 | Description                              |
|------------------------|------------------------------------------|
| ORM                    | Maps C# classes to DB tables             |
| LINQ to Entities       | Use LINQ to query database               |
| Migrations             | Update database schema from code         |
| Code-First Approach    | Create DB from C# classes                |
| Database-First Approach| Generate C# code from existing DB        |

---

## 🧱 Example Class (Model)

```csharp
public class Employee
{
    public int Id { get; set; }
    public string Name { get; set; }
    public decimal Salary { get; set; }
}
```

---

## 🗃️ DB Context Class

```csharp
public class AppDbContext : DbContext
{
    public DbSet<Employee> Employees { get; set; }
}
```

---

## 🧠 Querying with LINQ

```csharp
using (var context = new AppDbContext())
{
    var highPaid = context.Employees
                          .Where(e => e.Salary > 60000)
                          .ToList();
}
```

---

## ✅ Summary

| Term           | Meaning                                        |
|----------------|------------------------------------------------|
| EF (Entity Framework) | Tool to interact with database using C# |
| ORM            | Maps C# classes to DB tables                  |
| LINQ in EF     | You can query database like C# collections    |

---

# 🚀 EF Core Tutorial: Console App

A step-by-step tutorial to create a .NET Console App using Entity Framework Core, LINQ, and SQL Server — ideal for beginners.

---

## ✅ Goal

A basic Employee Management Console App using:

- Entity Framework Core  
- SQL Server  
- LINQ to query employees  

---

## 🔧 Step-by-Step Setup

### 🧱 Step 1: Create Console App

```bash
dotnet new console -n EmployeeApp
cd EmployeeApp
```

---

### 📦 Step 2: Install Required EF Core Packages

```bash
dotnet add package Microsoft.EntityFrameworkCore
dotnet add package Microsoft.EntityFrameworkCore.SqlServer
dotnet add package Microsoft.EntityFrameworkCore.Tools
```

---

### 🧑‍💼 Step 3: Create the Employee Model — `Employee.cs`

```csharp
public class Employee
{
    public int Id { get; set; }
    public string Name { get; set; }
    public decimal Salary { get; set; }
}
```

---

### 🗃️ Step 4: Create the Database Context — `AppDbContext.cs`

```csharp
using Microsoft.EntityFrameworkCore;

public class AppDbContext : DbContext
{
    public DbSet<Employee> Employees { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        optionsBuilder.UseSqlServer("Server=YOUR_SERVER;Database=EmployeeDB;Trusted_Connection=True;");
    }
}
```

🔁 Replace `"YOUR_SERVER"` with your local SQL Server name like:

```ini
Server=localhost\SQLEXPRESS;
```

---

### ⚙️ Step 5: Add & Apply Migration (Database Creation)

```bash
dotnet ef migrations add InitialCreate
dotnet ef database update
```

This will:

✅ Create the database  
✅ Create a table named `Employees`

---

### 📥 Step 6: Insert & Query Data using LINQ — `Program.cs`

```csharp
using System;
using System.Linq;

class Program
{
    static void Main()
    {
        using var db = new AppDbContext();

        // Insert sample data if table is empty
        if (!db.Employees.Any())
        {
            db.Employees.Add(new Employee { Name = "Amit", Salary = 60000 });
            db.Employees.Add(new Employee { Name = "Neha", Salary = 75000 });
            db.Employees.Add(new Employee { Name = "Ravi", Salary = 50000 });
            db.SaveChanges();
        }

        // Query: Get employees with salary > 55000
        var highEarners = db.Employees
                            .Where(e => e.Salary > 55000)
                            .ToList();

        Console.WriteLine("High earning employees:");
        foreach (var emp in highEarners)
        {
            Console.WriteLine($"{emp.Name} - ₹{emp.Salary}");
        }
    }
}
```

---

### ✅ Output

```bash
High earning employees:
Amit - ₹60000
Neha - ₹75000
```

---

## 🧠 Summary

| Task           | Description                                |
|----------------|--------------------------------------------|
| EF Setup       | Use `DbContext`, models, and `UseSqlServer()` |
| Insert Data    | `.Add()` and `.SaveChanges()`              |
| Query with LINQ| `.Where()`, `.ToList()`, etc.              |
| Tools          | `dotnet ef migrations`, `database update`  |

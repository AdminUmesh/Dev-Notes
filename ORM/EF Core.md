# EF Core + MySQL / SQL Server – Setup Notes (.NET)

These notes show step‑by‑step how to add **Entity Framework Core** to your ASP.NET Core Web API project with either:

- **MySQL** (using `Pomelo.EntityFrameworkCore.MySql`), or  
- **Microsoft SQL Server** (using `Microsoft.EntityFrameworkCore.SqlServer`)

You can choose **one** or support **both** in different projects.

---

## 0. Prerequisites

- .NET 7 or .NET 8 SDK installed
- An ASP.NET Core Web API project (e.g. `Secure_API`)
- MySQL server or SQL Server running and you know:
  - server address
  - database name
  - username/password

Optional but recommended:
- `dotnet-ef` CLI tool

```bash
dotnet tool install --global dotnet-ef
```

---

## 1. Install EF Core Packages

Go to your **backend project folder** (where `.csproj` is) and run:

### 1.1 Common EF Core packages

```bash
# EF Core base
dotnet add package Microsoft.EntityFrameworkCore
# Design package (needed for migrations)
dotnet add package Microsoft.EntityFrameworkCore.Design
```

### 1.2 Provider for **MySQL**

Using Pomelo provider:

```bash
dotnet add package Pomelo.EntityFrameworkCore.MySql
```

### 1.3 Provider for **SQL Server**

```bash
dotnet add package Microsoft.EntityFrameworkCore.SqlServer
```

You only need **one provider** per project, depending on your DB.

---

## 2. Add Connection String (appsettings.json)

In your **backend** project, open `appsettings.json` and add:

### 2.1 Example for **MySQL**

```jsonc
{
  "ConnectionStrings": {
    "DefaultConnection": "Server=localhost;Port=3306;Database=MyApiDb;User=root;Password=your_password;"
  }
}
```

### 2.2 Example for **SQL Server**

```jsonc
{
  "ConnectionStrings": {
    "DefaultConnection": "Server=localhost\\\\SQLEXPRESS;Database=MyApiDb;Trusted_Connection=True;TrustServerCertificate=True;"
  }
}
```

> Adjust server name, database, user, password as per your setup.

---

## 3. Create an Entity (Model)

Example: `Product` entity.

```csharp
// Models/Product.cs
namespace Secure_API.Models
{
    public class Product
    {
        public int Id { get; set; }
        public string Name { get; set; } = "";
        public decimal Price { get; set; }
    }
}
```

You will persist this model in DB via EF Core.

---

## 4. Create the DbContext

```csharp
// Data/AppDbContext.cs
using Microsoft.EntityFrameworkCore;
using Secure_API.Models;

namespace Secure_API.Data
{
    public class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options)
        {
        }

        public DbSet<Product> Products { get; set; }
    }
}
```

This class represents your database session.

---

## 5. Register DbContext in Program.cs

Open `Program.cs` and add `using`:

```csharp
using Microsoft.EntityFrameworkCore;
using Secure_API.Data;
```

### 5.1 For **MySQL**

```csharp
var builder = WebApplication.CreateBuilder(args);

// connection string
var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");

builder.Services.AddDbContext<AppDbContext>(options =>
    options.UseMySql(connectionString, ServerVersion.AutoDetect(connectionString)));

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
```

### 5.2 For **SQL Server**

```csharp
var builder = WebApplication.CreateBuilder(args);

var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");

builder.Services.AddDbContext<AppDbContext>(options =>
    options.UseSqlServer(connectionString));

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
```

> Pick **one** of the blocks depending on your DB.

---

## 6. Create Migrations

In the backend project folder (where `.csproj` is):

```bash
# Add initial migration
dotnet ef migrations add InitialCreate

# Apply migration to database
dotnet ef database update
```

What this does:

- `migrations add` -> generates C# migration files based on your models and DbContext
- `database update` -> creates DB and tables (if not exists) according to migrations

After this, your DB should have a **Products** table.

---

## 7. Use DbContext in a Controller

Example: simple API controller for `Product`.

```csharp
// Controllers/ProductsController.cs
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Secure_API.Data;
using Secure_API.Models;

namespace Secure_API.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class ProductsController : ControllerBase
    {
        private readonly AppDbContext _db;

        public ProductsController(AppDbContext db)
        {
            _db = db;
        }

        [HttpGet]
        public async Task<ActionResult<List<Product>>> GetAll()
        {
            return await _db.Products.ToListAsync();
        }

        [HttpPost]
        public async Task<ActionResult<Product>> Create(Product product)
        {
            _db.Products.Add(product);
            await _db.SaveChangesAsync();
            return CreatedAtAction(nameof(GetAll), new { id = product.Id }, product);
        }
    }
}
```

Run your API and test with Swagger or Postman.

---

## 8. Typical Folder Structure (Backend)

```text
backend/
  Secure_API.csproj
  Program.cs
  appsettings.json
  Models/
    Product.cs
    User.cs
  Data/
    AppDbContext.cs
  Controllers/
    ProductsController.cs
    AuthController.cs
  Migrations/
    2025xxxxxx_InitialCreate.cs
```

Your **Angular frontend** will be in a separate folder (e.g. `frontend/`).

---

## 9. Switching Between MySQL and SQL Server

If you ever want to switch:

1. Change the `ConnectionStrings:DefaultConnection` in `appsettings.json`
2. Replace `UseMySql(...)` with `UseSqlServer(...)` or vice‑versa in `Program.cs`
3. Delete existing migrations and create new ones (or map carefully to existing DB).

---

## 10. Useful Commands Summary

```bash
# Install EF and provider
dotnet add package Microsoft.EntityFrameworkCore
dotnet add package Microsoft.EntityFrameworkCore.Design
dotnet add package Pomelo.EntityFrameworkCore.MySql      # MySQL
# or
dotnet add package Microsoft.EntityFrameworkCore.SqlServer # SQL Server

# Tools
dotnet tool install --global dotnet-ef

# Migrations
dotnet ef migrations add InitialCreate
dotnet ef database update
```

---

## 11. Best Practices (Short)

- Keep connection strings in **appsettings.json** / environment variables.
- Never commit real production passwords in source control.
- Use **DbContext** via DI (`AddDbContext`) instead of manually `new`ing it.
- Prefer async EF methods (`ToListAsync`, `SaveChangesAsync`, etc.).
- Split entities into separate files and use clear naming.

---

This is enough to get **EF Core + MySQL or SQL Server** running in your .NET API project.

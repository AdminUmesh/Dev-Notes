# EF Core vs Dapper vs ADO.Net

## 1. ADO.NET (Foundation Layer)

ADO.NET is the low-level data access technology built into .NET by
Microsoft.

### Characteristics

-   Built-in with .NET
-   No NuGet required
-   Works directly with database connections
-   Requires manual SQL and manual object mapping

### Example

``` csharp
using SqlConnection con = new SqlConnection(connectionString);
con.Open();

SqlCommand cmd = new SqlCommand("SELECT * FROM Users", con);
SqlDataReader reader = cmd.ExecuteReader();
```

### Pros

-   Maximum control
-   Very fast
-   No external dependencies

### Cons

-   Boilerplate code
-   Manual mapping
-   Harder maintenance

------------------------------------------------------------------------

# 2. Entity Framework Core (EF Core)

EF Core is Microsoft's modern ORM for .NET.

### Installation

Installed via NuGet:

    Microsoft.EntityFrameworkCore
    Microsoft.EntityFrameworkCore.SqlServer
    Microsoft.EntityFrameworkCore.Tools

### Purpose

Convert database tables into C# objects and allow developers to query
using LINQ instead of SQL.

### Example

``` csharp
var users = _context.Users.Where(x => x.Age > 25).ToList();
```

### Advantages

-   Automatic object mapping
-   Change tracking
-   LINQ queries
-   Database migrations
-   Relationship management

### Disadvantages

-   Slightly slower than raw SQL
-   Complex queries sometimes harder

### Best Used For

-   Enterprise applications
-   CRUD-heavy applications
-   Rapid development

------------------------------------------------------------------------

# 3. Dapper (Micro ORM)

Dapper is a lightweight ORM created by the Stack Overflow team.

### Installation

Installed via NuGet:

    Dapper

### Purpose

Execute SQL manually but map results automatically to objects.

### Example

``` csharp
var users = connection.Query<User>("SELECT * FROM Users").ToList();
```

### Advantages

-   Very fast
-   Minimal abstraction
-   Easy stored procedure usage
-   Lightweight

### Disadvantages

-   No change tracking
-   No migrations
-   SQL must be written manually

### Best Used For

-   High-performance APIs
-   Stored procedure heavy systems
-   Microservices

------------------------------------------------------------------------

# 4. DTO (Data Transfer Object)

DTO is a design pattern, not a framework or library.

### Purpose

Transfer data between layers without exposing database entities.

### Example

``` csharp
public class UserDTO
{
    public string Name { get; set; }
    public string Email { get; set; }
}
```

### Why Use DTO?

-   Security (hide sensitive fields)
-   Reduce payload size
-   Decouple API from database schema
-   Cleaner architecture

### Typical Flow

    Database → Entity → DTO → API Response → Frontend

------------------------------------------------------------------------

# 5. Architecture Relationship

    Application Code
            ↑
     EF Core / Dapper
            ↑
          ADO.NET
            ↑
         Database

EF Core and Dapper both internally use ADO.NET.

------------------------------------------------------------------------

# 6. Comparison Table

  Feature          EF Core     Dapper          ADO.NET
  ---------------- ----------- --------------- -----------------
  Created By       Microsoft   StackOverflow   Microsoft
  Type             Full ORM    Micro ORM       Data Access API
  SQL Required     Optional    Required        Required
  Performance      Medium      High            Very High
  Ease of Use      Easy        Medium          Hard
  NuGet Required   Yes         Yes             No

------------------------------------------------------------------------

# 7. When to Use What

### Use EF Core when:

-   Building enterprise apps
-   Need migrations and relationships
-   Prefer LINQ over SQL

### Use Dapper when:

-   Performance critical APIs
-   Stored procedure heavy systems
-   Need more SQL control

### Use ADO.NET when:

-   Building custom framework
-   Maximum performance required
-   Low level database operations

------------------------------------------------------------------------

# Key Takeaway

-   EF Core = Productivity
-   Dapper = Performance
-   ADO.NET = Foundation
-   DTO = Data shaping pattern

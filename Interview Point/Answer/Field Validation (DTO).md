# DTO Model
A **DTO (Data Transfer Object)** is a class used to transfer data between layers or services.  
It usually:
- Contains only data properties
- Has no business logic
- Is used to shape data for APIs or views

**Example:**
```csharp
public class UserDto
{
    public string FirstName { get; set; }
    public string LastName { get; set; }
}
```

### 1. JSON Property Name
When serializing/deserializing JSON, you can map a property to a specific JSON key.

```csharp
// using Newtonsoft.Json;
using Newtonsoft.Json;

public class UserDto
{
    [JsonProperty("first_name")]
    public string FirstName { get; set; }
}

//Using System.Text.Json:
using System.Text.Json.Serialization;

public class UserDto
{
    [JsonPropertyName("first_name")]
    public string FirstName { get; set; }
}
```

### 2.Field Validation Attributes
You can validate DTO fields using **Data Annotations**.

| Attribute | Purpose | Example |
|-----------|---------|---------|
| `[Key]` | Marks property as primary key | `[Key] public int Id { get; set; }` |
| `[Keyless]` | Marks entity without primary key | `[Keyless] public class MyEntity { ... }` |
| `[Required]` | Field cannot be null | `[Required] public string Name { get; set; }` |
| `[StringLength(50)]` | Max length of string | `[StringLength(50)] public string Name { get; set; }` |
| `[MaxLength(50)]` | Max length (esp. EF Core mapping) | `[MaxLength(50)] public string Name { get; set; }` |
| `[MinLength(3)]` | Min length | `[MinLength(3)] public string Name { get; set; }` |
| `[Range(1, 100)]` | Numeric range | `[Range(1,100)] public int Age { get; set; }` |
| `[EmailAddress]` | Must be valid email | `[EmailAddress] public string Email { get; set; }` |
| `[Phone]` | Must be valid phone | `[Phone] public string Phone { get; set; }` |
| `[DefaultValue("value")]` | Sets default value | `[DefaultValue("Active")] public string Status { get; set; }` |
| `[Column(TypeName = "varchar(50)")]` | DB type mapping | `[Column(TypeName = "varchar(50)")] public string Name { get; set; }` |

---

### 3. Null Acceptance
By default:

- string? → nullable

- string → non-nullable (in C# 8+ with nullable enabled)

- [Required] → ensures value is provided

**Example:**
```csharp
[Required]
public string Name { get; set; }  // Cannot be null

public string? Nickname { get; set; }  // Can be null
```

### 4. Ignoring Fields in Mapping or Serialization
a) Ignore in EF Core
If a property should not be mapped to the database:

```csharp
[NotMapped]
public string FullName => $"{FirstName} {LastName}";
```

b) Ignore in JSON Output

```csharp
// Using Newtonsoft.Json:
[JsonIgnore]
public string SecretKey { get; set; }

// Using System.Text.Json:
[JsonIgnore]
public string SecretKey { get; set; }
```

### 5. Column/Table Mapping
[Table("table_name")] → maps entity to a specific table

[Column("db_column_name")] → maps property to a specific column

**Example:**

```csharp
[Table("Users")]
public class UserEntity
{
    [Key]
    public int Id { get; set; }

    [Column("first_name")]
    public string FirstName { get; set; }
}
```

### 6. Full Example
```csharp
[Table("Users")]
public class UserEntity
{
    [Key]
    public int Id { get; set; }

    [Required]
    [StringLength(50)]
    [JsonPropertyName("first_name")]
    public string FirstName { get; set; }

    [Required]
    [StringLength(50)]
    [JsonPropertyName("last_name")]
    public string LastName { get; set; }

    [NotMapped]
    public string FullName => $"{FirstName} {LastName}";

    [EmailAddress]
    public string Email { get; set; }

    [DefaultValue("Active")]
    public string Status { get; set; }
}
```
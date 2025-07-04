# Retrive Related

### retrieve a single record from the Users table where the Email equals a given email address.
```c#
var user = await _context.user
    .Where(x => x.Email == email)
    .FirstOrDefaultAsync();
```
```c#
var user = await _context.user
   var user = await _context.user.FirstOrDefaultAsync(x => x.Email == email);
```

### Write code to retrieve all users whose name contains the word "admin" (case-insensitive).
```c#
// case-insensitive
var users = await _context.Users
    .Where(x => x.Name.ToLower().Contains("admin"))
    .ToListAsync();
```
```c#
// using EF.Functions.Like (optional, for more raw SQL-like feel):
var users = await _context.Users
    .Where(x => EF.Functions.Like(x.Name, "%admin%"))
    .ToListAsync();
```

### Write code to count how many users have a non-null email address.
```c#
var emailCount = await _context.Users.CountAsync(x => x.Email != null);
```

### retrieve the top 5 users ordered by their creation date (CreatedAt), most recent first.
```c#
var users = await _context.Users
    .OrderByDescending(x => x.CreatedAt)
    .Take(5)
    .ToListAsync();
```

###  check if any user exists with the email "admin@example.com".
```c#
var exists = await _context.Users.AnyAsync(x => x.Email == "admin@example.com");
```

###  get the total number of users created in the current year.
```c#
// Correct version using DateTime.Year:
var count = await _context.Users
    .CountAsync(x => x.CreatedAt.Year == 2025);
```

```c#
// Extra tip (for dynamic year):
var currentYear = DateTime.Now.Year;
var count = await _context.Users
    .CountAsync(x => x.CreatedAt.Year == currentYear);
```

### group users by their Role and get the count of users in each role..
```c#
var groupUser = await _context.Users
    .GroupBy(x => x.Role)
    .Select(g => new 
    {
        Role = g.Key,
        Count = g.Count()
    })
    .ToListAsync();

   // .GroupBy(x => x.Role) groups the users by their role.

   // .Select(g => new { Role = g.Key, Count = g.Count() }) creates an anonymous object with the role name and user count.

   // .ToListAsync() executes the query.
```

# Insert Related

###  insert a new User into the database. The user has Id, Name, and Email.
```c#
var user = new User
{
    Id = id,
    Name = name,
    Email = email
};

await _context.Users.AddAsync(user);
await _context.SaveChangesAsync();
```

# Update Related

###  Update the name of the user whose ID is 5 to "Updated Name".
```c#
var user = await _context.Users.FirstOrDefaultAsync(x => x.Id == 5);
if (user != null)
{
    user.Name = "Updated Name";
    await _context.SaveChangesAsync();
}
```

# Delete Related

###  Delete the user with ID 5.
```c#
// Delete a single user by ID
var user = await _context.Users.FirstOrDefaultAsync(x => x.Id == 5);
if (user != null)
{
    _context.Users.Remove(user);
    await _context.SaveChangesAsync();
}
```

```c#
// Delete multiple users (using RemoveRange):
var usersToDelete = await _context.Users
    .Where(x => x.Role == "Guest")
    .ToListAsync();

_context.Users.RemoveRange(usersToDelete);
await _context.SaveChangesAsync();
```

# Other Method Explained

### 1. SingleOrDefaultAsync()
**Use:** Returns the user if exactly one matches. Returns null if none match. Throws error if more than one match.

```csharp
var user = await _context.Users.SingleOrDefaultAsync(x => x.Email == "admin@example.com");
```

### 2. AddRange() / AddRangeAsync()
**Use:** Insert multiple records at once.
```csharp
var users = new List<User>
{
    new User { Name = "User1", Email = "u1@example.com" },
    new User { Name = "User2", Email = "u2@example.com" },
    new User { Name = "User3", Email = "u3@example.com" }
};

await _context.Users.AddRangeAsync(users);
await _context.SaveChangesAsync();
// SaveChangesAsync() writes them to the database.
```

### 3. FirstAsync() and SingleAsync()
**Use:**
- FirstAsync() → like FirstOrDefaultAsync() but throws if no match.

- SingleAsync() → like SingleOrDefaultAsync() but throws if zero or more than one match.

```csharp
var first = await _context.Users.FirstAsync(x => x.Email.Contains("admin"));
var single = await _context.Users.SingleAsync(x => x.Id == 1);
```

### 4. FindAsync()
**Use:** Fetch an entity by its primary key.
```csharp
var user = await _context.Users.FindAsync(1);

// Works only with primary keys.
// Fast and efficient.
// If entity is already tracked (in memory), it won’t hit the database.
```

### 5. Update() and UpdateRange()
**Use:** Tell EF you’re updating an entity (not required if you're tracking it already).

```csharp
var user = new User { Id = 1, Name = "Updated", Email = "updated@example.com" };
_context.Users.Update(user);
await _context.SaveChangesAsync();

// Only use Update() when you attach a detached object (like from an API).
```

### 6. RemoveRange()
**Use:** Delete multiple records.

```csharp
var users = await _context.Users.Where(x => x.Role == "Guest").ToListAsync();
_context.Users.RemoveRange(users);
await _context.SaveChangesAsync();
```

### 7. AsNoTracking()
**Use:** Read-only query, improves performance by not tracking the entity.
```csharp
var users = await _context.Users
    .AsNoTracking()
    .Where(x => x.Role == "Admin")
    .ToListAsync();
```

### 8. Include() (Eager loading)
**Use:** Load related entities (navigation properties).

```csharp
var users = await _context.Users
    .Include(u => u.Posts)
    .ToListAsync();
```

### 9. FromSqlInterpolated() / FromSqlRaw()
**Use:** Run raw SQL stored procedures or queries.
```csharp
var notifications = await _context.Notifications
    .FromSqlInterpolated($"CALL GetNotificationsByUser({userId})")
    .ToListAsync();
```

### 10. AnyAsync()

**Use:** Check if any record exists matching a condition.
```csharp
bool exists = await _context.Users.AnyAsync(x => x.Email == "admin@example.com");
```

# 🧾 EF Core Method Summary

| Method               | Purpose                             | Returns                          |
|----------------------|-------------------------------------|----------------------------------|
| `FirstOrDefaultAsync`| First match or `null`               | Single entity or `null`          |
| `SingleOrDefaultAsync`| One match or `null` (throws on many)| Single entity or `null`          |
| `AddRangeAsync`      | Add multiple records                | `void` (adds to context)         |
| `FindAsync`          | Find by Primary Key                 | Entity or `null`                 |
| `RemoveRange`        | Delete multiple records             | `void`                           |
| `Update`             | Update detached object              | `void`                           |
| `AnyAsync`           | Check if any record exists          | `bool`                           |
| `AsNoTracking`       | Read-only query (better performance)| List or entity (not tracked)     |
| `Include`            | Load related/nested entities        | Entity/entities with navigation  |
| `FromSqlInterpolated`| Execute raw SQL or stored procedure | Entity/entities                  |

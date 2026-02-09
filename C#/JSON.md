
# JSON in C# (.NET)
---

## 1. What is JSON (in C# context)

JSON (JavaScript Object Notation) is a lightweight, text-based data format.
In C#, JSON is always handled as a **string** and must be converted to/from objects.

---

## 2. Sample JSON

```json
{
  "id": 101,
  "name": "Umesh",
  "active": true,
  "skills": ["C#", "Angular", "SQL"]
}
```

---

## 3. C# Model

```csharp
public class User
{
    public int Id { get; set; }
    public string Name { get; set; }
    public bool Active { get; set; }
    public List<string> Skills { get; set; }
}
```

---

## 4. Using System.Text.Json (Built-in, Recommended)

### Deserialize JSON → Object

```csharp
using System.Text.Json;

string json = @"{
  ""id"": 101,
  ""name"": ""Umesh"",
  ""active"": true,
  ""skills"": [""C#"", ""Angular"", ""SQL""]
}";

User user = JsonSerializer.Deserialize<User>(json);

Console.WriteLine(user.Id);
Console.WriteLine(user.Name);
Console.WriteLine(user.Active);
Console.WriteLine(string.Join(", ", user.Skills));
```

### Output

```
101
Umesh
True
C#, Angular, SQL
```

---

### Serialize Object → JSON

```csharp
User user = new User
{
    Id = 102,
    Name = "Rahul",
    Active = false,
    Skills = new List<string> { "C#", "SQL" }
};

string json = JsonSerializer.Serialize(user);
Console.WriteLine(json);
```

### Output

```json
{"Id":102,"Name":"Rahul","Active":false,"Skills":["C#","SQL"]}
```

---

## 5. Using Newtonsoft.Json (Legacy / Flexible)

### Deserialize

```csharp
using Newtonsoft.Json;

User user = JsonConvert.DeserializeObject<User>(json);
Console.WriteLine(user.Name);
```

### Output

```
Umesh
```

---

### Serialize (Pretty Print)

```csharp
string json = JsonConvert.SerializeObject(user, Formatting.Indented);
Console.WriteLine(json);
```

### Output

```json
{
  "Id": 101,
  "Name": "Umesh",
  "Active": true,
  "Skills": [
    "C#",
    "Angular",
    "SQL"
  ]
}
```

---

## 6. System.Text.Json vs Newtonsoft.Json

| Feature | System.Text.Json | Newtonsoft.Json |
|------|------------------|----------------|
| Built-in | Yes | No |
| Performance | Faster | Slightly slower |
| Case insensitive | No (default) | Yes |
| Dynamic JSON | Limited | Excellent |
| Legacy support | No | Yes |

---

## 7. Common Mistakes

### ❌ Not JSON
```csharp
{ id = 1, name = "Umesh" }
```

### ✅ Valid JSON
```json
{ "id": 1, "name": "Umesh" }
```

---

## 8. When to Use Which

### Use System.Text.Json
- New projects
- ASP.NET Core APIs
- Performance-critical apps

### Use Newtonsoft.Json
- Legacy projects
- Dynamic or unknown JSON
- Complex polymorphism

---

## 9. Final Summary

- JSON is language-independent
- In C#, JSON must be serialized/deserialized
- System.Text.Json is modern and fast
- Newtonsoft.Json is flexible and mature

---

END OF NOTES

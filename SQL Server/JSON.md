
# JSON in SQL Server
---

## 1. What is JSON?

JSON (JavaScript Object Notation) is a lightweight, text-based data-interchange format.
SQL Server does NOT have a native JSON data type (unlike MySQL). JSON is stored as NVARCHAR.

---

## 2. Important Concept (Very Important)

❌ **SQL Server does NOT support `JSON_TABLE`**  
✅ **`OPENJSON` is the SQL Server equivalent of MySQL `JSON_TABLE`**

---

## 3. JSON Storage in SQL Server

```sql
CREATE TABLE users (
  id INT IDENTITY(1,1) PRIMARY KEY,
  data NVARCHAR(MAX)
);
```

---

## 4. Insert JSON Data

```sql
INSERT INTO users (data)
VALUES ('{ "name": "Umesh", "age": 30, "skills": ["C#", "SQL"] }');
```

---

## 5. Validate JSON

```sql
SELECT ISJSON(data) AS IsValidJson FROM users;
```

Output:
```
1
```

---

## 6. Read JSON Values

### Scalar value
```sql
SELECT JSON_VALUE(data, '$.name') AS Name FROM users;
```

### Object or array
```sql
SELECT JSON_QUERY(data, '$.skills') AS Skills FROM users;
```

---

## 7. OPENJSON — SQL Server Equivalent of JSON_TABLE

### MySQL JSON_TABLE (for comparison)

```sql
SELECT *
FROM JSON_TABLE(
  '[{"id":1},{"id":2},{"id":3}]',
  '$[*]' COLUMNS (sid INT PATH '$.id')
) jt;
```

---

### SQL Server OPENJSON (Same result)

```sql
SELECT id
FROM OPENJSON('[{"id":1},{"id":2},{"id":3}]')
WITH (
  id INT '$.id'
);
```

### Output

| id |
|----|
| 1  |
| 2  |
| 3  |

---

## 8. OPENJSON with Table Data

```sql
SELECT u.id, j.skill
FROM users u
CROSS APPLY OPENJSON(u.data, '$.skills') AS j;
```

Output:

| id | skill |
|----|-------|
| 1  | C#    |
| 1  | SQL   |

---

## 9. Update JSON Values

```sql
UPDATE users
SET data = JSON_MODIFY(data, '$.age', 31)
WHERE id = 1;
```

---

## 10. Search JSON Data

```sql
SELECT *
FROM users
WHERE JSON_VALUE(data, '$.name') = 'Umesh';
```

---

## 11. JSON_TABLE vs OPENJSON (Interview Gold)

| Feature | MySQL JSON_TABLE | SQL Server OPENJSON |
|------|-----------------|---------------------|
| Native JSON type | Yes | No |
| Converts JSON to rows | Yes | Yes |
| Supports schema | Yes | Yes |
| Stored procedure friendly | Yes | Yes |
| Standard SQL | Partial | Microsoft-specific |

---

## 12. Common Mistakes

❌ Trying to use JSON_TABLE in SQL Server  
❌ Treating NVARCHAR as relational data  

---

## 13. Final Summary

- SQL Server has NO `JSON_TABLE`
- `OPENJSON` is the correct replacement
- JSON stored as NVARCHAR
- Use OPENJSON for arrays
- Use JSON_VALUE / JSON_QUERY for reading

---

END OF NOTES

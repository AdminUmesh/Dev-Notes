
# JSON in MySQL

---

## 1. What is JSON?

JSON (JavaScript Object Notation) is a lightweight, text-based data-interchange format.
MySQL supports JSON natively (MySQL 5.7+).

---

## 2. JSON Data Type in MySQL

MySQL provides a native `JSON` column type which:
- Stores JSON in optimized binary format
- Automatically validates JSON
- Allows efficient querying

---

## 3. Create Table with JSON Column

```sql
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  data JSON
);
```

---

## 4. Insert JSON Data

```sql
INSERT INTO users (data)
VALUES ('{ "name": "Umesh", "age": 30, "skills": ["C#", "SQL"] }');
```

---

## 5. Read JSON Values

### Extract Single Value

```sql
SELECT JSON_EXTRACT(data, '$.name') AS name FROM users;
```

### Output

```
"Umesh"
```

---

## 6. JSON Operators (`->` and `->>`)

```sql
SELECT data->'$.name' FROM users;
SELECT data->>'$.name' FROM users;
```

### Difference

| Operator | Result |
|-------|--------|
| `->` | JSON value (quoted) |
| `->>` | Scalar value (unquoted) |

---

## 7. Read JSON Array Values

```sql
SELECT data->'$.skills[0]' AS firstSkill FROM users;
```

### Output

```
"C#"
```

---

## 8. Update JSON Values

```sql
UPDATE users
SET data = JSON_SET(data, '$.age', 31)
WHERE id = 1;
```

---

## 9. Check JSON Validity

```sql
SELECT JSON_VALID(data) FROM users;
```

---

## 10. Search JSON Data

```sql
SELECT *
FROM users
WHERE JSON_EXTRACT(data, '$.name') = 'Umesh';
```

---

## 11. JSON_TABLE (Advanced)

```sql
SELECT *
FROM JSON_TABLE(
  '[{"id":1},{"id":2},{"id":3}]',
  '$[*]' COLUMNS (sid INT PATH '$.id')
) jt;
```

### Output

| sid |
|----:|
| 1 |
| 2 |
| 3 |

---

## 12. Common Mistakes

### ❌ Invalid JSON
```json
{ name: "Umesh" }
```

### ✅ Valid JSON
```json
{ "name": "Umesh" }
```

---

## 13. When to Use JSON in MySQL

- Semi-structured data
- Flexible schema
- Storing API responses
- Logging & metadata

---

## 14. When NOT to Use JSON

- Highly relational data
- Data requiring frequent joins
- Heavy indexing requirements

---

## 15. Final Summary

- MySQL supports native JSON
- JSON is stored in binary format
- Use `JSON_EXTRACT`, `->`, `->>` to read values
- Use `JSON_TABLE` to convert JSON arrays to rows

---

END OF NOTES

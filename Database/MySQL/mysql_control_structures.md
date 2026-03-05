
# MySQL Control Structures Cheat Sheet

## ✅ 1. DECLARE and SET Variables

Declare and assign values to variables (inside a procedure or BEGIN...END block):

```sql
DECLARE myVar INT;
SET myVar = 10;
```

---

## ✅ 2. IF...ELSE in MySQL

```sql
IF myVar > 5 THEN
   -- do something
ELSE
   -- do something else
END IF;
```

---

## ✅ 3. CASE Statement in MySQL

```sql
CASE 
    WHEN myVar = 1 THEN SELECT 'One';
    WHEN myVar = 2 THEN SELECT 'Two';
    ELSE SELECT 'Other';
END CASE;
```

---

## 🧪 Example: Full Practice in a Stored Procedure

```sql
DELIMITER //

CREATE PROCEDURE CheckNumberStatus(IN num INT)
BEGIN
    DECLARE message VARCHAR(50);
    
    -- Using IF
    IF num < 0 THEN
        SET message = 'Negative';
    ELSEIF num = 0 THEN
        SET message = 'Zero';
    ELSE
        -- Using CASE inside ELSE
        CASE 
            WHEN num BETWEEN 1 AND 10 THEN SET message = 'Between 1 and 10';
            WHEN num > 10 THEN SET message = 'Greater than 10';
            ELSE SET message = 'Unknown';
        END CASE;
    END IF;

    SELECT message AS Result;
END //

DELIMITER ;
```

Call the procedure:

```sql
CALL CheckNumberStatus(5);
CALL CheckNumberStatus(-3);
CALL CheckNumberStatus(15);
```

---

## ✅ When to Use `BEGIN ... END`

Used to group multiple SQL statements together.

### Example:

```sql
IF total > 100 THEN
    BEGIN
        UPDATE orders SET status = 'Large' WHERE id = orderId;
        INSERT INTO logs(action) VALUES ('Marked as Large Order');
    END;
END IF;
```

### Summary Table

| Use Case                  | Need `BEGIN ... END`? |
|---------------------------|------------------------|
| Single `IF`/`ELSE` line   | ❌ Not needed          |
| Multiple statements       | ✅ Required            |
| Stored procedures         | ✅ Recommended         |
| Triggers / Loops          | ✅ Required            |

---

## ✅ WHILE Loop in MySQL

### Syntax

```sql
WHILE condition DO
    -- Statements
END WHILE;
```

### Example: Generate numbers from 1 to N

Create table:

```sql
CREATE TABLE numbers (
    val INT
);
```

Create procedure:

```sql
DELIMITER //

CREATE PROCEDURE GenerateNumbers(IN limit_num INT)
BEGIN
    DECLARE i INT DEFAULT 1;

    WHILE i <= limit_num DO
        INSERT INTO numbers(val) VALUES (i);
        SET i = i + 1;
    END WHILE;
END //

DELIMITER ;
```

Call the procedure:

```sql
CALL GenerateNumbers(5);
```

### Output in `numbers` table:

```
+------+
| val  |
+------+
| 1    |
| 2    |
| 3    |
| 4    |
| 5    |
+------+
```

### Summary

- `WHILE` is like a loop: repeats as long as condition is true.
- Must be used inside `BEGIN...END` blocks.
- Don’t forget to update the loop variable to avoid infinite loops.

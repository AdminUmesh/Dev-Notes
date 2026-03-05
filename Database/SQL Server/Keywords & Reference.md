
# 📘 Common T-SQL Keywords and Usage in SQL Server

This document summarizes frequently used T-SQL keywords, syntax, and examples to help with SQL Server development and debugging.

---

## ✅ 1. `IF`
**Purpose:** Executes a block of code only if a condition is true.

```sql
IF EXISTS (SELECT 1 FROM Users WHERE Username = 'admin')
    PRINT 'Admin user exists.'
ELSE
    PRINT 'Admin user not found.'
```

---

## ✅ 2. `EXISTS`
**Purpose:** Tests for the existence of any record in a subquery.

```sql
IF EXISTS (SELECT * FROM Orders WHERE OrderDate > '2025-01-01')
    PRINT 'Recent orders found.'
```

---

## ✅ 3. `ON`
**Purpose:** Used in `JOIN`, `TRIGGER`, and `CONSTRAINT` clauses.

**Join Example:**
```sql
SELECT a.Name, b.OrderDate
FROM Customers a
JOIN Orders b ON a.CustomerID = b.CustomerID;
```

**Trigger Example:**
```sql
CREATE TRIGGER trg_AfterInsert
ON Employees
AFTER INSERT
AS
BEGIN
    PRINT 'Insert occurred.'
END
```

---

## ✅ 4. `BEGIN` and `END`
**Purpose:** Groups multiple statements into a block.

```sql
IF @value > 10
BEGIN
    PRINT 'Value is greater than 10';
    SET @status = 'High';
END
ELSE
BEGIN
    PRINT 'Value is 10 or less';
    SET @status = 'Low';
END
```

---

## ✅ 5. `CHECK`
**Purpose:** Defines constraints to enforce data integrity.

```sql
CREATE TABLE Products (
    ProductID INT PRIMARY KEY,
    Price DECIMAL(10, 2),
    CONSTRAINT chk_Price CHECK (Price > 0)
);
```

**Add to existing table:**
```sql
ALTER TABLE Products
ADD CONSTRAINT chk_Stock CHECK (StockQuantity >= 0);
```

---

## 🔧 Variables and Debugging

### `DECLARE`
**Purpose:** Declares variables.

```sql
DECLARE @name NVARCHAR(50);
DECLARE @age INT;
```

### `SET`
**Purpose:** Assigns values to variables.

```sql
SET @age = 30;
SET @userCount = (SELECT COUNT(*) FROM Users);
```

### `PRINT`
**Purpose:** Outputs messages for debugging.

```sql
PRINT 'User name is: ' + @name;
```

### 🧠 Combined Example
```sql
DECLARE @price DECIMAL(10,2);
DECLARE @message NVARCHAR(100);

SET @price = 99.99;

IF @price > 50
BEGIN
    SET @message = 'Price is high: ' + CAST(@price AS NVARCHAR(20));
    PRINT @message;
END
ELSE
BEGIN
    SET @message = 'Price is acceptable: ' + CAST(@price AS NVARCHAR(20));
    PRINT @message;
END
```

---

## 🔄 Control-of-Flow

- `WHILE`, `BREAK`, `CONTINUE`, `RETURN`, `GOTO`

```sql
WHILE @counter < 5
BEGIN
    PRINT @counter;
    SET @counter = @counter + 1;
END
```

---

## 📋 Data Definition Language (DDL)

- `CREATE`, `ALTER`, `DROP`, `TRUNCATE`

---

## 🔐 Constraints and Integrity

- `PRIMARY KEY`, `FOREIGN KEY`, `UNIQUE`, `DEFAULT`, `NOT NULL`

---

## 🔍 Query-Related Keywords

- `SELECT`, `FROM`, `WHERE`, `GROUP BY`, `HAVING`, `ORDER BY`, `TOP`

---

## 🔁 Joins and Unions

- `INNER JOIN`, `LEFT JOIN`, `RIGHT JOIN`, `FULL OUTER JOIN`
- `UNION`, `UNION ALL`

---

## 🔐 Transaction Control

```sql
BEGIN TRY
    BEGIN TRANSACTION;
    -- SQL statements
    COMMIT;
END TRY
BEGIN CATCH
    ROLLBACK;
    PRINT ERROR_MESSAGE();
END CATCH
```

---

## 📦 Stored Procedures & Functions

- `PROCEDURE`, `FUNCTION`, `AS`, `OUTPUT`, `EXEC`

---

## ⌛ Other Useful Keywords

- `CASE` – conditional logic  
- `ISNULL()` / `COALESCE()` – handling NULLs  
- `CAST()` / `CONVERT()` – data conversion  
- `WITH` – for CTEs or hints  
- `IDENTITY` – auto-increment columns  

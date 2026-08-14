# What is an Index?

An **Index** is a database object that helps SQL Server find data **faster**.

Without an index, SQL Server checks every row in the table (**Table Scan**).

With an index, SQL Server directly locates the required rows (**Index Seek**).

> **Indexes are mainly created on columns that are frequently used in:**
> - WHERE
> - JOIN
> - ORDER BY
> - GROUP BY

---

# Why Do We Use Indexes?

Indexes improve database performance by:

- Speeding up SELECT queries
- Improving WHERE clause performance
- Making JOIN operations faster
- Improving ORDER BY and GROUP BY
- Enforcing uniqueness using UNIQUE indexes

---

# Example Without Index
Suppose we have 10,00,000 employee records.

```sql
SELECT *
FROM Employee
WHERE EmployeeID = 500000;
```

### Without Index

SQL Server checks every row until it finds the employee.

```
1
2
3
4
...
500000
```

This is called a **Table Scan**.

It is slow.

---

### With Index

SQL Server directly jumps to EmployeeID = 500000.

```
Index
 │
 ├──100
 ├──200
 ├──300
 └──500000 ✔
```

This is called an **Index Seek**.

It is much faster.

---

# Types of Indexes

There are several types of indexes in SQL Server.

1. Clustered Index
2. Non-Clustered Index
3. Unique Index
4. Filtered Index

---

# 1. Clustered Index

A **Clustered Index** stores the table data in sorted order.

The data itself is physically arranged according to the indexed column.

> A table can have **only ONE Clustered Index** because data can be sorted in only one way.

---

## Example

```sql
CREATE TABLE Employee
(
    EmployeeID INT PRIMARY KEY,
    Name NVARCHAR(100),
    Salary DECIMAL(10,2)
);
```

When the primary key is created, SQL Server automatically creates a **Clustered Index** (by default).

The data is stored like this:

| EmployeeID | Name |
|------------|------|
| 1 | Amit |
| 2 | Rahul |
| 3 | Mohan |
| 4 | Neha |

Notice the data is physically stored in EmployeeID order.

---

## Create Clustered Index Manually

Use this only if the table has no clustered index.

```sql
CREATE CLUSTERED INDEX IX_Employee
ON Employee(EmployeeID);
```

---

## Advantages

- Very fast searching
- Very fast range queries
- Faster sorting

---

## Disadvantages

- Only one clustered index per table
- INSERT/UPDATE can become slower because SQL Server may need to rearrange data

---

# 2. Non-Clustered Index

A **Non-Clustered Index** is stored separately from the table.

It does **NOT** rearrange the table data.

Instead, it stores:

- Indexed value
- Pointer to the actual row

A table can have **multiple Non-Clustered Indexes**.

---

## Example

Employee table

| EmployeeID | Name | City |
|------------|------|------|
| 1 | Amit | Delhi |
| 2 | Rahul | Noida |
| 3 | Neha | Jaipur |

Create index

```sql
CREATE INDEX IX_Employee_Name
ON Employee(Name);
```

SQL Server creates a separate structure like:

| Name | Row Pointer |
|------|-------------|
| Amit | Row 1 |
| Neha | Row 3 |
| Rahul | Row 2 |

The table itself remains unchanged.

---

## Multiple Column Index

```sql
CREATE INDEX IX_Customer_Name
ON Customers(LastName, FirstName);
```

Useful when both columns are searched together.

---

## Advantages

- Multiple indexes allowed
- Improves search speed
- Improves JOIN performance

---

## Disadvantages

- Uses extra storage
- INSERT, UPDATE and DELETE become slightly slower because indexes must also be updated

---

# Clustered vs Non-Clustered

| Clustered Index | Non-Clustered Index |
|-----------------|---------------------|
| Data is physically sorted | Data remains unchanged |
| Only one per table | Many allowed |
| Faster for range queries | Faster for searching specific columns |
| Default on Primary Key | Created manually |

---

# 3. Unique Index

A **Unique Index** prevents duplicate values.

If duplicate values are inserted, SQL Server throws an error.

---

## Example

```sql
CREATE UNIQUE INDEX IX_Email
ON Employee(Email);
```

Allowed

| Email |
|-------|
| abc@gmail.com |
| xyz@gmail.com |

Not Allowed

| Email |
|-------|
| abc@gmail.com |
| abc@gmail.com ❌ |

---

## Note

A Unique Index can be:

- Clustered
- Non-Clustered

---

# 4. Filtered Index

A **Filtered Index** indexes only a subset of rows.

This makes the index:

- Smaller
- Faster
- Uses less storage

---

## Example

Employee table

| EmployeeID | IsActive |
|------------|----------|
| 1 | 1 |
| 2 | 0 |
| 3 | 1 |
| 4 | 0 |

Suppose we only search active employees.

```sql
CREATE INDEX IX_ActiveEmployees
ON Employee(EmployeeID)
WHERE IsActive = 1;
```

Now SQL Server stores only:

| EmployeeID |
|------------|
| 1 |
| 3 |

instead of all rows.

---

## Benefits

- Smaller index
- Faster queries
- Less maintenance

---

# Rename an Index

```sql
EXEC sp_rename
    N'dbo.Employee.IX_OldName',
    N'IX_NewName',
    N'INDEX';
```

Or

```
SSMS
Database
   └── Tables
        └── Table
              └── Indexes
                    Right Click → Rename
```

---

# Drop an Index

Single index

```sql
DROP INDEX IX_Employee_Name
ON Employee;
```

Multiple indexes

```sql
DROP INDEX
    IX_Name ON Employee,
    IX_City ON Employee;
```

---

# Disable an Index

Sometimes you want to keep the index but stop SQL Server from using it.

```sql
ALTER INDEX IX_Employee_Name
ON Employee
DISABLE;
```

The index still exists but SQL Server cannot use it.

---

# Enable (Rebuild) an Index

```sql
ALTER INDEX IX_Employee_Name
ON Employee
REBUILD;
```

This enables the disabled index again.

---

# View All Indexes

```sql
EXEC sp_helpindex 'Employee';
```

Or

```sql
SELECT *
FROM sys.indexes
WHERE object_id = OBJECT_ID('Employee');
```

---


# Interview Questions

### Can a table have multiple Clustered Indexes?

No.

Only one Clustered Index is allowed.

---

### Can a table have multiple Non-Clustered Indexes?

Yes.

A table can have many Non-Clustered Indexes.

---

### Does Primary Key create an index?

Yes.

By default, SQL Server creates a Clustered Index for the Primary Key.

---

### Do indexes improve INSERT performance?

No.

Indexes make INSERT, UPDATE, and DELETE slightly slower because SQL Server must also update the indexes.

---

### Which operations become faster using indexes?

- SELECT
- WHERE
- JOIN
- ORDER BY
- GROUP BY

---
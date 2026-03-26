## **Clone one table data in another table (both tables columns are same)**

**1. Copy All Data (Most Common)**
```sql
INSERT INTO TargetTable
SELECT *
FROM SourceTable;
-- Copies all rows from SourceTable to TargetTable
```
**2. Copy Specific Columns (Best Practice)**
```sql
INSERT INTO TargetTable (Id, Name, Salary)
SELECT Id, Name, Salary
FROM SourceTable;
-- Prevents issues if column order changes
```

**3. Copy with Condition**
```sql
INSERT INTO TargetTable
SELECT *
FROM SourceTable
WHERE Salary > 50000;
-- Only copies filtered data
```

**4. Copy + Modify Data**
```sql
INSERT INTO TargetTable (Id, Name, Salary)
SELECT Id, Name, Salary + 1000
FROM SourceTable;
-- You can transform data while copying
```

**5. Clone Structure + Data (New Table)**

If target table does NOT exist:
```sql
SELECT *
INTO NewTable
FROM SourceTable;
-- Creates table + copies data
```

**6. Clone Only Structure (No Data)**
```sql
SELECT *
INTO NewTable
FROM SourceTable
WHERE 1 = 0;
-- Creates empty table
```

## **all matched employee from left table**

**1. Inner Join**
```sql
SELECT *
FROM Employees e
INNER JOIN Departments d
    ON e.DeptId = d.Id;
    -- INNER JOIN returns only matching records between two tables
```

**2. Left Join**
```sql
SELECT *
FROM Employees e
LEFT JOIN Departments d
    ON e.DeptId = d.Id;
    -- LEFT JOIN returns all left record + matched records
```
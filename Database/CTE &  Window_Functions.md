## 1. What is CTE (Common Table Expression)?

A **CTE** is a temporary result set that can be referenced within a SQL
query.\
It improves readability and helps break complex queries into simpler
steps.

### Basic Syntax

``` sql
WITH CTE_Name AS (
    SELECT column1, column2
    FROM table_name
    WHERE condition
)
SELECT *
FROM CTE_Name;
```

### Example

``` sql
WITH HighSalaryEmployees AS (
    SELECT Name, Salary
    FROM Employees
    WHERE Salary > 50000
)
SELECT *
FROM HighSalaryEmployees;
```

### Advantages of CTE

-   Improves query readability
-   Helps organize complex queries
-   Useful for recursive queries
-   Can be reused within the same query

------------------------------------------------------------------------

## 2. Recursive CTE

A recursive CTE references itself and is often used for hierarchical
data.

### Example

``` sql
WITH EmployeeHierarchy AS (
    SELECT EmployeeID, ManagerID, Name
    FROM Employees
    WHERE ManagerID IS NULL

    UNION ALL

    SELECT e.EmployeeID, e.ManagerID, e.Name
    FROM Employees e
    INNER JOIN EmployeeHierarchy eh
        ON e.ManagerID = eh.EmployeeID
)
SELECT *
FROM EmployeeHierarchy;
```

Used for: - Organization charts - Tree structures - Category hierarchies

------------------------------------------------------------------------
## Note:
CTE is NOT for making impossible queries possible. Mostly: `CTE is for making complex queries readable and manageable.`

### Example WITHOUT CTE
**Find employees earning above department average salary**

```sql
SELECT e.*
FROM Employees e
JOIN (
    SELECT DepartmentID,
           AVG(Salary) AS AvgSalary
    FROM Employees
    GROUP BY DepartmentID
) d
ON e.DepartmentID = d.DepartmentID
WHERE e.Salary > d.AvgSalary;
```

**Beginner Problem Here**
```sql
JOIN (
    SELECT ...
)
```
**is harder to read.** `Especially when query becomes huge.`

SAME Query WITH CTE
```sql
WITH DepartmentAverage AS (
    SELECT DepartmentID,
           AVG(Salary) AS AvgSalary
    FROM Employees
    GROUP BY DepartmentID
)

SELECT e.*
FROM Employees e
JOIN DepartmentAverage d
ON e.DepartmentID = d.DepartmentID
WHERE e.Salary > d.AvgSalary;
```

### GOLDEN LINE
- CTE does NOT make SQL more powerful.
- It makes SQL more readable and maintainable.
------------------------------------------------------------------------

# 3. Window Function?

A **Window Function** performs calculations across a set of table rows
related to the current row.

Unlike GROUP BY, it **does not collapse rows**.

### Basic Syntax

``` sql
FUNCTION_NAME() OVER (
    PARTITION BY column
    ORDER BY column
)
```
### Problem
In a Result table you have to calculate RanK of Stundent

```sql
SELECT StudentName,
       Marks,
       ROW_NUMBER() OVER(
           ORDER BY Marks DESC
       ) AS Rank
FROM Studentss;
```

### MOST IMPORTANT PART
```sql
OVER(...)
-- Perform calculation across related rows
```

### Result
| StudentName | Marks | Rank |
| ----- | ----- | ----- | 
|Simran| 90|	1|
|Rahul|	70 |	2|
|Karan|	60 |	3|
------------------------------------------------------------------------

## 4. Common Window Functions

### ROW_NUMBER()

``` sql
SELECT Name, Salary,
ROW_NUMBER() OVER (ORDER BY Salary DESC) AS RowNum
FROM Employees;
```

### RANK()

``` sql
SELECT Name, Salary,
RANK() OVER (ORDER BY Salary DESC) AS RankNum
FROM Employees;
```

### DENSE_RANK()

``` sql
SELECT Name, Salary,
DENSE_RANK() OVER (ORDER BY Salary DESC) AS RankNum
FROM Employees;
```

### PARTITION Example

``` sql
SELECT Department, Name, Salary,
ROW_NUMBER() OVER (
PARTITION BY Department
ORDER BY Salary DESC
) AS DeptRank
FROM Employees;
```

------------------------------------------------------------------------

## 5. CTE + Window Function Example

``` sql
WITH LatestActivity AS (
SELECT Lead_Code,
Created_Date,
ROW_NUMBER() OVER (
PARTITION BY Lead_Code
ORDER BY Created_Date DESC
) AS rn
FROM crm_Lead_Events_tr
)

SELECT *
FROM LatestActivity
WHERE rn = 1;
```

Purpose: Find the latest activity for each lead.

------------------------------------------------------------------------

## 6. Key Differences

  Feature     CTE                    Window Function
  ----------- ---------------------- ---------------------
  Type        Temporary result set   Row calculation
  Purpose     Simplify queries       Ranking & analytics
  Lifetime    Single query           Used inside SELECT
  Recursion   Supported              Not supported

------------------------------------------------------------------------

## Final Summary

-   **CTE** = Temporary named query used within a statement.
-   **Window Functions** = Perform calculations across rows without
    grouping them.
-   Often used together in reports and analytics queries.

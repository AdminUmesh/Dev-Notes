# SQL Developer Cheat Sheet

## 1. CTE (Common Table Expression)

A CTE is a temporary named result set used within a query.

### Syntax

``` sql
WITH CTE_Name AS (
    SELECT column1, column2
    FROM table_name
)
SELECT *
FROM CTE_Name;
```

### Example

``` sql
WITH HighSalary AS (
    SELECT Name, Salary
    FROM Employees
    WHERE Salary > 50000
)
SELECT * FROM HighSalary;
```

### Benefits

-   Improves readability
-   Breaks complex queries into steps
-   Supports recursion

------------------------------------------------------------------------

# 2. Window Functions

Window functions perform calculations across rows related to the current
row without grouping them.

### Syntax

``` sql
FUNCTION() OVER (
    PARTITION BY column
    ORDER BY column
)
```

### Example

``` sql
SELECT Name, Salary,
ROW_NUMBER() OVER (ORDER BY Salary DESC) AS RowNum
FROM Employees;
```

------------------------------------------------------------------------

# 3. ROW_NUMBER vs RANK vs DENSE_RANK

### ROW_NUMBER

Unique sequential numbering.

``` sql
SELECT Name, Salary,
ROW_NUMBER() OVER (ORDER BY Salary DESC) AS RowNum
FROM Employees;
```

### RANK

Duplicate ranks cause gaps.

``` sql
SELECT Name, Salary,
RANK() OVER (ORDER BY Salary DESC) AS RankNum
FROM Employees;
```

### DENSE_RANK

Duplicate ranks without gaps.

``` sql
SELECT Name, Salary,
DENSE_RANK() OVER (ORDER BY Salary DESC) AS RankNum
FROM Employees;
```

### Difference

  Function     Duplicates    Gaps
  ------------ ------------- ------
  ROW_NUMBER   Not allowed   No
  RANK         Allowed       Yes
  DENSE_RANK   Allowed       No

------------------------------------------------------------------------

# 4. JOIN Types

### INNER JOIN

Returns matching records from both tables.

``` sql
SELECT *
FROM Orders o
INNER JOIN Customers c
ON o.CustomerId = c.Id;
```

### LEFT JOIN

Returns all rows from left table and matching rows from right table.

``` sql
SELECT *
FROM Customers c
LEFT JOIN Orders o
ON c.Id = o.CustomerId;
```

### RIGHT JOIN

Returns all rows from right table.

### FULL JOIN

Returns all rows from both tables.

------------------------------------------------------------------------

# 5. GROUP_CONCAT()

Concatenates values from multiple rows into one string.

### Syntax

``` sql
GROUP_CONCAT(column_name)
```

### Example

``` sql
SELECT Department,
GROUP_CONCAT(EmployeeName)
FROM Employees
GROUP BY Department;
```

### Custom separator

``` sql
GROUP_CONCAT(EmployeeName SEPARATOR ' | ')
```

------------------------------------------------------------------------

# 6. Subquery vs CTE

  Feature       Subquery        CTE
  ------------- --------------- -----------
  Readability   Harder          Cleaner
  Reusability   Limited         Can reuse
  Recursion     Not supported   Supported
  Performance   Similar         Similar

### Subquery Example

``` sql
SELECT *
FROM Employees
WHERE Salary >
(
SELECT AVG(Salary)
FROM Employees
);
```

### CTE Example

``` sql
WITH AvgSalary AS (
SELECT AVG(Salary) AS AvgSal
FROM Employees
)

SELECT *
FROM Employees
WHERE Salary > (SELECT AvgSal FROM AvgSalary);
```

------------------------------------------------------------------------

# 7. Top N per Group Pattern

Used to get top records within each group.

### Example: Top 1 employee per department

``` sql
WITH RankedEmployees AS (
SELECT
Department,
Name,
Salary,
ROW_NUMBER() OVER (
PARTITION BY Department
ORDER BY Salary DESC
) AS rn
FROM Employees
)

SELECT *
FROM RankedEmployees
WHERE rn = 1;
```

------------------------------------------------------------------------

# Quick Visual Summary

  Concept            Purpose
  ------------------ ------------------------
  CTE                Temporary named query
  Window Functions   Row-based calculations
  ROW_NUMBER         Unique numbering
  RANK               Ranking with gaps
  DENSE_RANK         Ranking without gaps
  JOIN               Combine tables
  GROUP_CONCAT       Combine multiple rows
  Subquery           Query inside query
  Top N per Group    Best rows per category

------------------------------------------------------------------------

# Interview Tip

Most SQL interview questions are based on:

-   Window Functions
-   JOIN logic
-   Top N per group
-   Aggregation
-   Subqueries vs CTE

Master these and you can solve most real-world SQL problems.

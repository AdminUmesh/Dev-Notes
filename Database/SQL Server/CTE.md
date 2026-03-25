## What is a CTE in SQL Server

CTE (Common Table Expression) is a temporary named result set that you can use within a query (like a readable inline view).
`It makes complex queries cleaner, modular, and easier to understand.`

**Basic Syntax**
```sql
WITH CTE_Name AS
(
    SELECT column1, column2
    FROM TableName
    WHERE condition
)
SELECT *
FROM CTE_Name;
```

**Simple Example**
```sql
WITH EmployeeCTE AS
(
    SELECT Id, Name, Salary
    FROM Employees
    WHERE Salary > 50000
)
SELECT *
FROM EmployeeCTE;
```
This works like a temporary result set (not stored permanently)

### **Why Use CTE?**

- Improve readability
- Break complex queries into parts
- Reuse query logic
- Useful in recursion (very important)

### **Types of CTE**

**1. Simple CTE**

Basic filtering or transformation

```sql
WITH DeptCTE AS
(
    SELECT * FROM Departments WHERE IsActive = 1
)
SELECT * FROM DeptCTE;
```

**2. Recursive CTE**

Used for hierarchical data like:
- Employee → Manager
- Category → Subcategory

### **Example:** Employee Hierarchy
```sql
WITH EmployeeHierarchy AS
(
    -- Anchor query
    SELECT Id, Name, ManagerId
    FROM Employees
    WHERE ManagerId IS NULL

    UNION ALL

    -- Recursive query
    SELECT e.Id, e.Name, e.ManagerId
    FROM Employees e
    INNER JOIN EmployeeHierarchy eh
        ON e.ManagerId = eh.Id
)
SELECT *
FROM EmployeeHierarchy;
```

## **CTE vs Temporary Table**|
|Feature|	CTE|	Temp Table (#Temp)|
|-------|------|----------------------|
|Storage|	Memory (logical)	|TempDB (physical)
|Reusability|	Single query only	|Multiple queries
|Performance|	Good (small data)	|Better for large data
|Indexing|	❌ Not possible	|✅ Possible

## **CTE vs Subquery**
|Feature	|CTE|	Subquery|
|-----------|---|-----------|
|Readability	|✅ High	|❌ Low|
|Reuse	|✅ Yes	|❌ No|
|Complexity	|Handles better	|Gets messy|

## **Important Rules- 

- Must start with `WITH`
- Must be followed by a SELECT/INSERT/UPDATE/DELETE
- Scope is only for one query
- Cannot use `ORDER BY` inside CTE (unless TOP is used)

## **Real-World Example**
Get Top 3 Highest Salaries

```sql
WITH SalaryRank AS
(
    SELECT 
        Name,
        Salary,
        ROW_NUMBER() OVER (ORDER BY Salary DESC) AS RankNo
    FROM Employees
)
SELECT *
FROM SalaryRank
WHERE RankNo <= 3;
```
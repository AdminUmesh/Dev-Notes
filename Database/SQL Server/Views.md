# What is a View in SQL Server?
A view is a virtual table created by a query that retrieves data from one or more tables. `Which does not store data itself like a a physical table.`

## Views can be used to:
- **Simplify complex queries** → Make difficult queries easier to use.
- **Hide database complexity** → Show only the required data.
- **Improve security** → Restrict access to specific rows or columns.
- **Reuse queries** → Use the same query again and again.

## How to Create a View
**Syntax:**
```sql
CREATE VIEW view_name AS
SELECT column1, column2, ...
FROM table_name
WHERE condition;
```

## How to Execute a View
**Syntax:**
```sql
SELECT * FROM view_name;
```

## How to Alter a View
**Syntax:**
```sql
ALTER VIEW view_name AS
SELECT column1, column2, ...
FROM table_name
WHERE condition;
```

## How to Drop a View
**Syntax:**
```sql
DROP VIEW view_name;
```

# Types of Views
### Simple Views:

A simple view retrieves data from a single table.
```sql
CREATE VIEW SimpleEmployeeView AS
SELECT FirstName, LastName
FROM Employees;
```

### Complex Views:
A complex view can involve multiple tables, joins, aggregations (such as SUM, AVG, etc.), and even subqueries.

```sql
CREATE VIEW EmployeeSummaryView AS
SELECT Department, COUNT(*) AS EmployeeCount, AVG(Salary) AS AverageSalary
FROM Employees
GROUP BY Department;
```

### Updatable Views:
These are views that allow updates (inserts, updates, deletes) to the underlying tables through the view.
Not all views are updatable (e.g., views with DISTINCT, GROUP BY, or JOIN operations may be non-updatable).

```sql
CREATE VIEW EmployeeInsertView AS
SELECT FirstName, LastName, Department, Salary
FROM Employees;
```

```sql
INSERT INTO EmployeeInsertView (FirstName, LastName, Department, Salary)
VALUES ('John', 'Doe', 'Sales', 50000);
```

### Inline Views:
These are subqueries used directly in a FROM clause of a query.
They do not store any data and are often used temporarily for specific queries.

```sql
SELECT Department, EmployeeCount
FROM (SELECT Department, COUNT(*) AS EmployeeCount FROM Employees GROUP BY Department) AS Dep
```

# Views

## Note

* We **cannot add an `UPDATE` query** while creating or altering a View.
* A View is created using a **`SELECT` query**.
* We can **update, delete, or insert data through an existing simple View**.
* Views using **`DISTINCT`, `GROUP BY`, or `JOIN`** are generally **not directly updatable**.

## View vs Function

| View                                     | Function                                        |
| ---------------------------------------- | ----------------------------------------------- |
| Mainly used to **retrieve/display data** | Used to **perform reusable logic/calculations** |
| Usually does not take parameters         | Can take **parameters**                         |
| Acts like a **virtual table**            | Returns a **value or table**                    |

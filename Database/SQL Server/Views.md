# View in SQL Server?
A view is a virtual table created by a query that retrieves data from one or more tables.
 
 - `Which does not store data itself like a a physical table.`
 -`It normally stores the query definition, not a separate copy of the table's data.`

## Views can be used to:
- **Simplify complex queries** → Make difficult queries easier to use.
- **Hide database complexity** → Show only the required data.
- **Improve security** → Restrict access to specific rows or columns.
- **Reuse queries** → Use the same query again and again.

### Create a View
**Syntax:**
```sql
CREATE VIEW view_name AS
SELECT column1, column2, ...
FROM table_name
WHERE condition;
```

### Execute a View
**Syntax:**
```sql
SELECT * FROM view_name;
```

### Alter a View
**Syntax:**
```sql
ALTER VIEW view_name AS
SELECT column1, column2, ...
FROM table_name
WHERE condition;
```

### Drop a View
**Syntax:**
```sql
DROP VIEW view_name;
```

# Types of Views
### Simple Views:

A simple view retrieves data from one table, usually without grouping or aggregate calculations.
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
These are views that allow updates (inserts, updates, deletes) to the underlying tables through the view not directly on the table.
Not all views are updatable (e.g., views with DISTINCT, GROUP BY, or JOIN operations may be non-updatable).

```sql
CREATE VIEW EmployeeView AS
SELECT EmpId, Name, Department, Salary
FROM Employees;
```

**Now You can Apply Insert, Update and Delete on a view which directly affrect the original table**
```sql
UPDATE EmployeeView
SET Salary = 60000
WHERE EmpId = 1;

INSERT INTO EmployeeInsertView (FirstName, LastName, Department, Salary)
VALUES ('John', 'Doe', 'Sales', 50000);

DELETE FROM EmployeeView
WHERE EmpId = 1;
```

### Inline Views:
An inline view is a subquery used inside the FROM clause of a query. In SQL Server, it is not a separately created, named view.

```sql
SELECT Department, EmployeeCount
FROM (SELECT Department, COUNT(*) AS EmployeeCount FROM Employees GROUP BY Department) AS Dep
```

# Note
* You can only use **`SELECT` query** while creating a view.
* We **cannot add an `UPDATE` query** while creating or altering a View.
* We can **update, delete, or insert data through an existing simple View** `which is directly affrect the original table`
* Views using **`DISTINCT`, `GROUP BY`, or `JOIN`** are generally **not directly updatable**.
* you cannot pass parameters directly to a view like you do with a stored procedure.

# View vs Function

| View                                     | Function                                        |
| ---------------------------------------- | ----------------------------------------------- |
| Mainly used to **retrieve/display data** | Used to **perform reusable logic/calculations** |
| Does not accept parameters directly      | Can **parameters**                              |
| Acts like a **virtual table**            | Returns a **value or table**                    |
| Created using a SELECT query             | Can contain SQL logic and return a value or table |

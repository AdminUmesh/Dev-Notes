# What is a View in SQL Server?
A view is a virtual table created by a query that retrieves data from one or more tables. `Unlike a physical table, a view does not store data itself.` Instead, it provides a way to access data in a more flexible, simplified, or secured manner.

## Views can be used to:
- Simplify complex queries by encapsulating them into a single object.
- Provide a level of abstraction, hiding the complexity of database structures from the user.
- Enhance security by restricting access to certain columns or rows of a table.
- Allow for reuse of frequently used queries.

## Uses of Views
- **Data Abstraction:** Views can hide the complexity of the underlying table structure.
- **Simplify Queries:** Instead of repeatedly writing complex queries, you can create a view and use it just like a table.
- **Security:** Views can be used to restrict access to specific columns or rows. For example, users can be given access to a view that only shows non-sensitive data.

## How to Create a View
**Syntax:**
```sql
Copy code
CREATE VIEW view_name AS
SELECT column1, column2, ...
FROM table_name
WHERE condition;
```

**Example:**
```sql
CREATE VIEW EmployeeView AS
SELECT FirstName, LastName, Department
FROM Employees
WHERE IsActive = 1;
-- This creates a view called EmployeeView that retrieves FirstName, LastName, and Department columns from the Employees table where the IsActive flag is set to 1.
```

## How to Execute a View
**Syntax:**
```sql
SELECT * FROM view_name;
```

**Example:**
```sql
SELECT * FROM EmployeeView;
-- This will return all rows and columns from the EmployeeView view.
```

## How to Alter a View
**Syntax:**
```sql
ALTER VIEW view_name AS
SELECT column1, column2, ...
FROM table_name
WHERE condition;
```

**Example:**
```sql
ALTER VIEW EmployeeView AS
SELECT FirstName, LastName, Department, Salary
FROM Employees
WHERE IsActive = 1;
-- This alters the EmployeeView to also include the Salary column.
```

## How to Drop a View
**Syntax:**
```sql
DROP VIEW view_name;
```

**Example:**
```sql
DROP VIEW EmployeeView;
-- This will delete the EmployeeView from the database.
```

# Types of Views
### Simple Views:

A simple view retrieves data from a single table.
It can include filtering, sorting, and other operations.
These views are often used for basic queries.

```sql
CREATE VIEW SimpleEmployeeView AS
SELECT FirstName, LastName
FROM Employees;
```

### Complex Views:
A complex view can involve multiple tables, joins, aggregations (such as SUM, AVG, etc.), and even subqueries.
They are used to present data in a more advanced manner and might include calculations, filtering, or grouping.

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
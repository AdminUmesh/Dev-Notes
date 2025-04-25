# INNER JOIN
An INNER JOIN only returns rows where there is a match in both tables. If there is no match, will not appear in the final result of the query.

## Example:
Imagine we have two tables:

**employees:**

| emp_id | emp_name |
|--------|----------|
| 1      | Alice    |
| 2      | Bob      |
| 3      | Charlie |

**departments:**

| dep_id | emp_id | dep_name    |
|--------|--------|-------------|
| 10     | 1      | HR          |
| 20     | 2      | Engineering |
| 30     | 4      | Marketing   |

**Query:**

```sql
SELECT e.emp_name, d.dep_name
FROM employees e
INNER JOIN departments d
ON e.emp_id = d.emp_id;
```

**Result:**

| emp_name |	dep_name |
| -------- | ----------- |
| Alice |	HR |
| Bob |	Engineering |

In this case, only Alice and Bob are included because their emp_id exists in both the employees and departments tables.

# OUTER JOIN (LEFT, RIGHT, FULL)
### LEFT JOIN (or LEFT OUTER JOIN)
includes all rows from the left table, and the matched rows from the right table. If there is no match, the result will contain NULL values for the columns of the right table.

```sql
SELECT e.emp_name, d.dep_name
FROM employees e
LEFT JOIN departments d
ON e.emp_id = d.emp_id;
```

**Result:**

| emp_name |	dep_name |
| -------- | ----------- |
| Alice |	HR |
| Bob |	Engineering |
| Charlie |	NULL |

Here, Charlie does not have a matching emp_id in the departments table, so NULL is returned for dep_name.

### RIGHT JOIN (or RIGHT OUTER JOIN)
includes all rows from the right table, and the matched rows from the left table. If there is no match, the result will contain NULL values for the left table.

```sql
SELECT e.emp_name, d.dep_name
FROM employees e
RIGHT JOIN departments d
ON e.emp_id = d.emp_id;
```

**Result:**
| emp_name |	dep_name |
| -------- | ----------- |
| Alice |	HR |
| Bob |	Engineering |
| NULL |	Marketing |

Here, Marketing department does not have a matching emp_id in the employees table, so NULL is returned for emp_name.

### FULL JOIN (or FULL OUTER JOIN)
includes all rows from both tables, with NULL values for unmatched rows from both tables.

```sql
SELECT e.emp_name, d.dep_name
FROM employees e
FULL JOIN departments d
ON e.emp_id = d.emp_id;
```

**Result:**

| emp_name |	dep_name |
| -------- | ----------- |
| Alice |	HR |
| Bob |	Engineering |
| Charlie |	NULL |
| NULL |	Marketing |

This result includes all rows from both tables. Rows without a match will have NULL in the columns of the missing table.

# CROSS JOIN
A CROSS JOIN returns the Cartesian product of both tables, meaning it pairs each row from the first table with every row from the second table.

```sql
SELECT e.emp_name, d.dep_name
FROM employees e
CROSS JOIN departments d;
```

**Result:**

| emp_name |	dep_name |
| -------- | ----------- |
| Alice |	HR |
| Alice |	Engineering |
| Alice |	Marketing |
| Bob |	HR |
| Bob |	Engineering |
| Bob |	Marketing |
| Charlie |	HR |
| Charlie |	Engineering |
| Charlie |	Marketing |

Each employee is paired with every department, generating all possible combinations.

# SELF JOIN
A SELF JOIN is a join where a table is joined with itself. It is useful for querying hierarchical or recursive data, such as finding relationships like managers and employees.

Example: Imagine an employees table with a manager_id column, which references the emp_id of the employee’s manager.

**employees table:**
| emp_id |	emp_name |	manager_id |
| ------ | --------- | ----------- |
| 1      |	Alice    |	NULL       |
| 2      | 	Bob      |	1          |
| 3      | 	Charlie  |	1          |
| 4      |	David    |	2          |

```sql
SELECT e1.emp_name AS employee, e2.emp_name AS manager
FROM employees e1
LEFT JOIN employees e2
ON e1.manager_id = e2.emp_id;
```

**Result:**

| employee |	manager |
| -------- | ---------- |
| Alice |	NULL |
| Bob |	Alice |
| Charlie |	Alice |
| David |	Bob |

This result shows the employee names along with their respective managers. If an employee does not have a manager, NULL is returned.

## Summary of Joins:

**INNER JOIN:** Matches rows where a condition is true in both tables.

**OUTER JOIN** (LEFT, RIGHT, FULL): Includes unmatched rows from one or both tables, filling missing values with NULL.

**CROSS JOIN:** Returns all possible combinations of rows from two tables.

**SELF JOIN:** Joins a table with itself, useful for hierarchical relationships.

# MS SQL Constraints💡

## Unique Key
A unique key ensures that all values in a column (or combination of columns) are unique. It prevents duplicate values in the column.

- The column can contain NULL values, but NULL is considered a unique value (i.e., multiple NULLs are allowed).

- You can create multiple unique constraints on different columns in a table.

**Create a unique key on a column**
```sql
CREATE TABLE table_name (  
    col1 datatype,  
    col2 datatype UNIQUE,  
    ...  
);  
```

**Create more than one unique key on different columns**
```sql
CREATE TABLE table_name (  
    col1 col_definition,  
    col2 col_definition,  
    ...  
    CONSTRAINT constraint_name UNIQUE (column_name)  
); 
```

**Show unique columns in the table**
```sql
EXEC sp_helpindex 'table_name';
```

**Remove a unique constraint**
```sql
ALTER TABLE table_name DROP CONSTRAINT constraint_name;  
```

## Primary Key
A primary key is used to uniquely identify each record in a table. It ensures that no column defined as the primary key can contain NULL values. A table can have only one primary key.

**Create a primary key**
1. Using CREATE TABLE Statement

```sql
CREATE TABLE Persons (
    ID int NOT NULL,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Age int,
    PRIMARY KEY (ID)
);
```

2. Using ALTER TABLE Statement

```sql
ALTER TABLE table_name
ADD CONSTRAINT PK_constraint_name PRIMARY KEY (column_name);
```

**Drop a primary key**
```sql
ALTER TABLE table_name
DROP CONSTRAINT PK_constraint_name;
```

## Foreign Key
A foreign key is used to link one table to another by referencing the primary key or a unique key of another table.

**Create a foreign key**

1. Using CREATE TABLE Statement

```sql
CREATE TABLE Orders (
    OrderID int NOT NULL,
    OrderNumber int NOT NULL,
    PersonID int,
    PRIMARY KEY (OrderID),
    FOREIGN KEY (PersonID) REFERENCES Persons(PersonID)
);
```

2. Using ALTER TABLE Statement

```sql
ALTER TABLE Orders
ADD CONSTRAINT FK_constraint_name FOREIGN KEY (PersonID) REFERENCES Persons(PersonID);
```

**Drop a foreign key constraint**
```sql
ALTER TABLE table_name
DROP CONSTRAINT FK_constraint_name;
```
**Note**

A foreign key must reference :
- `A primary key` Or `A unique key` in the Parent (referenced) table.
```sql
--Parent Table 
CREATE TABLE Parent(
ID int UNIQUE
);

--Child Table 
CREATE TABLE Child(
ParentID int,
FOREIGN KEY (ParentID) REFERENCES Parent(ID)
);
```

## Composite Key
A composite key consists of two or more columns in a table that uniquely identify each row in the table. It is typically used when no single column can serve as a unique identifier.

**Create a composite key**

1. Using CREATE TABLE Statement
```sql
CREATE TABLE Product (  
    Prod_ID int NOT NULL,   
    Name varchar(45),   
    Manufacturer varchar(45),  
    CONSTRAINT PK_Product PRIMARY KEY (Name, Manufacturer)  
);
```

2. Using ALTER TABLE Statement

```sql
ALTER TABLE Student
ADD CONSTRAINT PK_Student PRIMARY KEY (stud_id, subject);
```

## Key Differences to Note in MS SQL:
**Primary Key:** In MS SQL Server, the primary key constraint is always created with a unique index on the column(s).

**Foreign Key:** The foreign key constraint references the primary or unique key from another table.

**Composite Key:** A composite key can be defined on more than one column, typically used when a single column cannot uniquely identify rows.

**Removing Constraints:** In MS SQL, you must specify the name of the constraint when removing it (e.g., DROP CONSTRAINT), unlike MySQL where it can be dropped by the column name.
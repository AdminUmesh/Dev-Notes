# Functions in SQL Server
In SQL Server, functions are objects that return a value or a table. They encapsulate reusable logic that can be called in a query or other expressions.
`Functions are similar to stored procedures but differ in that they return a value, which can be a scalar value or a table.`

## There are two main types of functions in SQL Server:

1. **Scalar Functions**
2. **Table-Valued Functions (TVF)**

## 1. Scalar Functions
A scalar function returns a single value based on the input parameters. This value could be of any data type (e.g., int, varchar, datetime).

**Types of Scalar Functions:**

- 1.1 **Built-in Scalar Functions:**
These are predefined functions in SQL Server, such as:
  - **LEN():** Returns the length of a string.
  - **GETDATE():** Returns the current date and time.
  - **ABS():** Returns the absolute value of a number.
  - **UPPER(), LOWER():** Converts text to uppercase or lowercase.

- 2.2 **User-Defined Scalar Functions (UDFs):**
 These are functions defined by the user to perform specific operations.

## 2. Table-Valued Functions (TVFs)
A table-valued function returns a table rather than a scalar value.

**Types of Table-Valued Functions:**

- 2.1 **Inline Table-Valued Functions (iTVF):** These functions return a result set directly from a single SELECT statement.

- 2.2 **Multi-Statement Table-Valued Functions (mTVF):** These functions return a table, but the result is constructed from multiple INSERT statements.

# Predefined vs. User-Defined Functions

## Predefined Functions (System Functions):
These are functions that are built into SQL Server. You don’t need to define them, and they provide a variety of common tasks, such as string manipulation, date functions, mathematical operations, and more.

**Examples:**
- GETDATE()
- CHARINDEX()
- DATEADD()
- CONVERT()

## User-Defined Functions (UDFs):
These are custom functions created by the user to encapsulate logic that is not covered by the predefined functions.
`UDFs can be scalar or table-valued functions, depending on their return type.`

**Example:**

```sql
CREATE FUNCTION dbo.CalculateTax (@Amount DECIMAL)
RETURNS DECIMAL
AS
BEGIN
    RETURN @Amount * 0.07  -- Example of tax calculation
END
```

# Creating, Calling, Altering, Dropping and View_Code Scalar Functions

## Create a Scalar Function
To create a scalar function, you use the CREATE FUNCTION statement.

```sql
CREATE FUNCTION dbo.GetFullName (@FirstName VARCHAR(50), @LastName VARCHAR(50))
RETURNS VARCHAR(100)
AS
BEGIN
    RETURN @FirstName + ' ' + @LastName
END
--This function takes two parameters (First Name and Last Name) and returns the full name.
```

## Calling a Scalar Function
```sql
-- Calling the scalar function in a SELECT statement
SELECT dbo.GetFullName('John', 'Doe') AS FullName;
```

## Alter a Scalar Function
If you need to modify an existing function, you use the ALTER FUNCTION statement.

```sql
ALTER FUNCTION dbo.GetFullName (@FirstName VARCHAR(50), @LastName VARCHAR(50), @Title VARCHAR(20))
RETURNS VARCHAR(100)
AS
BEGIN
    RETURN @Title + ' ' + @FirstName + ' ' + @LastName
END
```

## Drop a Scalar Function
To delete an existing function, use the DROP FUNCTION statement:
```sql
DROP FUNCTION dbo.GetFullName;
--This will remove the GetFullName function from the database.
```
## View the Code of a Function 
```sql
-- View the code of a function
SELECT OBJECT_DEFINITION(OBJECT_ID('dbo.GetFullName'));
```

# Creating, Calling, Altering, Dropping, and Viewing the Code of Table-Valued Functions (TVF)
## Create a Table-Valued Function (TVF)
To create a Table-Valued Function (TVF), you use the CREATE FUNCTION statement, specifying the function's return type as TABLE.

**Example of Inline Table-Valued Function (iTVF):**
```sql
CREATE FUNCTION dbo.GetEmployeesByDepartment (@DepartmentID INT)
RETURNS TABLE
AS
RETURN
(
    SELECT EmployeeID, Name
    FROM Employees
    WHERE DepartmentID = @DepartmentID
);
-- This function takes a DepartmentID as a parameter and returns a table of EmployeeID and Name from the Employees table.
```

**Example of Multi-Statement Table-Valued Function (mTVF):**
```sql
CREATE FUNCTION dbo.GetEmployeesWithSalary (@Salary DECIMAL)
RETURNS @EmployeeTable TABLE
(
    EmployeeID INT,
    Name VARCHAR(100)
)
AS
BEGIN
    INSERT INTO @EmployeeTable
    SELECT EmployeeID, Name
    FROM Employees
    WHERE Salary > @Salary;

    RETURN;
END
-- This function takes a Salary as a parameter and returns a table with EmployeeID and Name for employees whose salary is greater than the given amount.
```

## Calling a Table-Valued Function (TVF)
To call a Table-Valued Function, you use it just like a table in a SELECT statement. If it is an inline TVF, it can be called directly in the FROM clause.

**Inline Table-Valued Function:**
```sql
-- Calling the inline table-valued function in a SELECT statement
SELECT * 
FROM dbo.GetEmployeesByDepartment(2);
-- This will return all employees from department 2.
```

**Calling a Multi-Statement Table-Valued Function:**
```sql
-- Calling the multi-statement table-valued function in a SELECT statement
SELECT * 
FROM dbo.GetEmployeesWithSalary(50000);
-- This will return employees whose salary is greater than 50,000.
```

## Alter a Table-Valued Function (TVF)
If you need to modify an existing table-valued function, you use the ALTER FUNCTION statement.

**Altering an Inline Table-Valued Function:**
```sql
ALTER FUNCTION dbo.GetEmployeesByDepartment (@DepartmentID INT)
RETURNS TABLE
AS
RETURN
(
    SELECT EmployeeID, Name, JobTitle
    FROM Employees
    WHERE DepartmentID = @DepartmentID
);
-- This modified version includes JobTitle in the result set.
```

**Altering a Multi-Statement Table-Valued Function:**
```sql
ALTER FUNCTION dbo.GetEmployeesWithSalary (@Salary DECIMAL)
RETURNS @EmployeeTable TABLE
(
    EmployeeID INT,
    Name VARCHAR(100),
    DepartmentID INT
)
AS
BEGIN
    INSERT INTO @EmployeeTable
    SELECT EmployeeID, Name, DepartmentID
    FROM Employees
    WHERE Salary > @Salary;

    RETURN;
END
-- This version now includes the DepartmentID in the result set.
```

## Drop a Table-Valued Function (TVF)
To delete an existing table-valued function, you use the DROP FUNCTION statement.

```sql
DROP FUNCTION dbo.GetEmployeesByDepartment;
-- This will remove the GetEmployeesByDepartment function from the database.
```

## View the Code of a Table-Valued Function (TVF)

**Using OBJECT_DEFINITION():**

```sql
-- View the code of a table-valued function
SELECT OBJECT_DEFINITION(OBJECT_ID('dbo.GetEmployeesByDepartment'));
```

**Using sys.objects and sys.sql_modules:**
```sql
-- View the code of a table-valued function
SELECT sm.definition
FROM sys.objects o
JOIN sys.sql_modules sm ON o.object_id = sm.object_id
WHERE o.type = 'FN'  -- 'FN' is the type for functions (scalar or table-valued)
AND o.name = 'GetEmployeesByDepartment';
```

# Physical Location:
You can check Under `database -> Programmability -> Functions`

## benifites of using a function 
- **Code Reusability:** Functions allow you to write a block of code once and reuse it multiple times throughout your program.

- **Modularity:** Functions help break down large, complex programs into smaller, manageable pieces. Each function can perform a specific task, making it easier to read, understand, and maintain the code.

- **Abstraction:** Functions allow you to hide the complexity of certain operations. You can call a function without knowing all the details of its implementation, which simplifies the logic of your program.

- **Testing and Debugging:** Since functions encapsulate specific logic, it's easier to isolate and test individual parts of your code. This makes debugging more efficient.

- **Improved Readability:** By giving descriptive names to your functions, you make your code more readable. This improves collaboration and long-term maintainability.


## #CheckThis??
```sql
-- What's store in this folder
under table-> programmobality -> functions -> aggregate functions
```

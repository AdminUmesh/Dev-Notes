# What is a Stored Procedure in SQL Server?

A Stored Procedure (SP) in is a precompiled collection of one or more SQL statements that can be executed together. Stored procedures are often used for repetitive tasks, data validation, and complex operations.

## Benefits of Stored Procedures

- **Performance:** Since stored procedures are precompiled, the SQL Server can optimize the execution plan, leading to better performance for repeated executions.

- **Reusability:** A stored procedure can be reused across different applications or users.

- **Security:** Permissions can be set to allow users to execute the procedure without granting direct access to the underlying tables.

- **Maintainability:** Changes to the logic inside a stored procedure do not require modifying every application or script that uses it.

- **Error Handling:** You can implement robust error handling mechanisms inside a stored procedure.

- **Reduced Network Traffic:** Since SQL code is executed on the server, it reduces the amount of data transferred over the network.

# Create a Stored Procedure
**Syntax:**
```SQL
CREATE PROCEDURE ProcedureName
AS
BEGIN
    -- SQL Statements
    SELECT * FROM TableName;
END;
```

## Without Parameters:
**Example:**

```sql
CREATE PROCEDURE GetAllEmployees
AS
BEGIN
    SELECT * FROM Employees;
END;
```

## With a Single Parameter:
**Example:**
```SQL
CREATE PROCEDURE GetEmployeeByID (@EmployeeID INT)
AS
BEGIN
    SELECT * FROM Employees WHERE EmployeeID = @EmployeeID;
END;
```
## With Multiple Parameters:
**Example:**
```SQL
CREATE PROCEDURE GetEmployeeByDepartmentAndSalary
(@DepartmentID INT, @MinSalary DECIMAL)
AS
BEGIN
    SELECT * FROM Employees WHERE DepartmentID = @DepartmentID AND Salary >= @MinSalary;
END;
```

# How to Call a Stored Procedure
## Without Parameters:
```SQL
EXEC ProcedureName;
```

## With Parameters:
```SQL
EXEC GetEmployeeByID @EmployeeID = 123;
```

## How to Show Queries in a Stored Procedure
To view the SQL code of an existing stored procedure, you can use the sp_helptext system stored procedure:

```SQL
EXEC sp_helptext 'ProcedureName';
This command will display the text (the SQL code) of the stored procedure.
```

# How to Alter a Stored Procedure
To modify an existing stored procedure, use the ALTER PROCEDURE statement:

**Syntax:**

```Sql
ALTER PROCEDURE ProcedureName (@param1 DataType, @param2 DataType)
AS
BEGIN
    -- Modified SQL Statements
    SELECT * FROM Employees WHERE DepartmentID = @param1;
END;
```

**Example:**

```sql
ALTER PROCEDURE GetEmployeeByDepartmentAndSalary
(@DepartmentID INT, @MinSalary DECIMAL)
AS
BEGIN
    SELECT * FROM Employees WHERE DepartmentID = @DepartmentID AND Salary >= @MinSalary;
END;
```

# How to Drop a Stored Procedure
To delete a stored procedure, use the DROP PROCEDURE 

**Syntax:**
```sql
DROP PROCEDURE ProcedureName;
```

**Example:**

```sql
DROP PROCEDURE GetEmployeeByID;
-- This command will remove the stored procedure from the database.
```

# Physical Location:
You can check Under `database -> Programmability -> Stored Procedures`

# Summary
- `Stored procedures are used to encapsulate SQL logic and improve performance, security, and maintainability.`

- `You can create stored procedures with no parameters, single parameters, or multiple parameters.`

- `Use sp_helptext to view the SQL code of a stored procedure.`

- `Execute stored procedures using EXEC or EXECUTE.`

- `Modify existing procedures with ALTER PROCEDURE.`

- `Drop a stored procedure using DROP PROCEDURE.`

These techniques help in creating efficient, reusable, and secure database operations.

# Stored Procedures in ADO.net
```c#
namespace CSATSU_RMS.Controllers
{
    public class SPController : ApiController
    {
        public IEnumerable<string> Get()
        {
            SqlConnection con = new SqlConnection(ConfigurationManager.ConnectionStrings["229999"].ConnectionString);
            con.Open();

            DataSet ds = new DataSet();

            // Define the stored procedure name
            string storedProcedureName = "rms_rkot_Sum";

            // Create the SqlCommand and set it to execute the stored procedure
            SqlCommand cmd = new SqlCommand(storedProcedureName, con)
            {
                CommandType = CommandType.StoredProcedure // Set the command type to Stored Procedure
            };

            // Add parameters to the stored procedure (assuming RSUM_TBL and RSUM_STS are the parameters)
            cmd.Parameters.AddWithValue("@RSUM_TBL", "01");
            cmd.Parameters.AddWithValue("@RSUM_STS", "k");

            // Use a SqlDataAdapter to execute the command and fill the DataSet
            SqlDataAdapter da = new SqlDataAdapter(cmd);
            da.Fill(ds, "OrderNo");

            if (ds.Tables["OrderNo"].Rows.Count > 0)
            {
                
            }

            return null;
        }
    }
}
``` 

# Stored Procedure vs SQL function
Both stored procedures and functions are used in database management systems (DBMS) to encapsulate SQL queries and logic, but they have some key differences:

## Purpose and Usage
**Stored Procedure:**
- A stored procedure is typically used to perform operations that do not return values. It can perform actions like inserting, updating, or deleting data in the database.

**Function:**
- A function is primarily used to perform a calculation or return a single value.
`Functions are often used when a return value is needed and can be used in SQL queries directly, like in SELECT statements.`

## Side Effects
**Stored Procedure:**
- A stored procedure can have effects on the database, such as modifying data (INSERT, UPDATE, DELETE) or performing administrative tasks (e.g., creating tables, managing permissions).

**Function:**
- Functions are not supposed to modify the database state (i.e., no INSERT, UPDATE, or DELETE operations should occur in a function).
It only returns a value and does not directly affect the database.

## Return Value
**Stored Procedure:**
- A stored procedure does not always return a value. It can return multiple results (e.g., a result set), but it typically does not return a single value.
If needed, a stored procedure can return a status or an output parameter, but this is not mandatory.

**Function:**
A function always returns a value, and it can only return a single value of a specific data type (e.g., INTEGER, VARCHAR, etc.).
Functions can be used directly in SQL queries to return results, like in a SELECT statement.

## Error Handling && Transaction Control
**Stored Procedure:**
- Stored procedures often include more complex error handling and transaction control (e.g., using BEGIN, COMMIT, ROLLBACK).

**Function:**
- Functions are generally simpler in terms of error handling and do not usually involve complex transaction management.
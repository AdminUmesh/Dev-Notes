# What is a Trigger in MS SQL?
A trigger in Microsoft SQL Server is a special type of stored procedure that is automatically executed (or fired) in response to specific events on a particular table or view. These events can be INSERT, UPDATE, or DELETE operations.

## Uses of Triggers
- **Data Validation:** Ensure that the data entered into a table adheres to certain business rules (e.g., prevent inserting negative numbers).

- **Auditing:** Keep track of changes made to a table (e.g., who changed data and when).

- **Cascading Actions:** Automatically perform operations on related tables (e.g., delete records from related tables when a row is deleted from a parent table).

- **Preventing Invalid Transactions:** Prevent certain operations, such as disallowing an update on a record after it has been flagged for review.

# Types of Triggers in MS SQL
There are mainly two types of triggers in SQL Server:

## DML Triggers (Data Manipulation Language Triggers):
They are fired when a INSERT, UPDATE, or DELETE operation occurs on a table or view.
**They can be further divided into:**
- AFTER Trigger (or FOR Trigger): Executed after the data modification is performed.` It is the most common type.
- INSTEAD OF Trigger: Executes instead of the action (e.g., instead of performing an INSERT, UPDATE, or DELETE, the trigger can perform another action, such as logging data or modifying related records).

## DDL Triggers (Data Definition Language Triggers):

These are fired in response to changes to the schema, such as CREATE, ALTER, or DROP operations on a database or table.
For example, a DDL trigger can be used to prevent a user from dropping a table or altering a schema without proper permissions.
LOGON and LOGOFF Triggers:

These triggers are used to log or enforce security on user logins and logoffs.
They are executed when a user logs on or logs off from the SQL Server.
How to Create, Execute, Alter, and Drop Triggers in MS SQL

### 1. Creating a Trigger
Syntax for creating a trigger:
```sql
CREATE TRIGGER TriggerName
ON TableName
{AFTER | INSTEAD OF} {INSERT | UPDATE | DELETE}
AS
BEGIN
   -- Trigger logic goes here
END;
```

**Example:** This trigger logs the date and time whenever a row is inserted into the Employee table.
```sql
CREATE TRIGGER LogEmployeeInsert
ON Employee
AFTER INSERT
AS
BEGIN
    INSERT INTO AuditLog (ActionType, TableName, ActionDate)
    VALUES ('INSERT', 'Employee', GETDATE());
END;
```

### 2. Executing a Trigger
Triggers are automatically executed when the specified event occurs on the table. You don't manually execute a trigger. It fires in response to an INSERT, UPDATE, or DELETE operation.

**Example:** When you insert a row into the Employee table, the LogEmployeeInsert trigger will automatically execute:

```sql
INSERT INTO Employee (EmployeeID, Name, Position)
VALUES (1, 'John Doe', 'Manager');
```
This will cause the trigger LogEmployeeInsert to log the action in the AuditLog table.

### 3. Altering a Trigger
To modify a trigger, you use the ALTER TRIGGER command.

**Syntax:**
```sql
ALTER TRIGGER TriggerName
ON TableName
{AFTER | INSTEAD OF} {INSERT | UPDATE | DELETE}
AS
BEGIN
   -- Updated trigger logic
END;
```

**Example:** Modifying the LogEmployeeInsert trigger to log a message along with the insert action:
```sql
ALTER TRIGGER LogEmployeeInsert
ON Employee
AFTER INSERT
AS
BEGIN
    INSERT INTO AuditLog (ActionType, TableName, ActionDate, Details)
    VALUES ('INSERT', 'Employee', GETDATE(), 'New employee added');
END;
```

### 4. Dropping a Trigger
To remove a trigger from a table, you can use the DROP TRIGGER command.

**Syntax:**
```sql
DROP TRIGGER TriggerName;
```

**Example:** Dropping the LogEmployeeInsert trigger:
```sql
DROP TRIGGER LogEmployeeInsert;
```

# Example of Using Triggers
Here’s an example that combines multiple types of triggers:

**Trigger for AFTER INSERT:** Automatically logs insert operations into an audit table.

```sql
CREATE TRIGGER AfterEmployeeInsert
ON Employee
AFTER INSERT
AS
BEGIN
    DECLARE @EmployeeID INT;
    DECLARE @EmployeeName NVARCHAR(100);
    
    SELECT @EmployeeID = EmployeeID, @EmployeeName = Name FROM INSERTED;
    
    INSERT INTO AuditLog (ActionType, TableName, ActionDate, Details)
    VALUES ('INSERT', 'Employee', GETDATE(), CONCAT('Employee Added: ', @EmployeeName));
END;
```
**Trigger for INSTEAD OF DELETE:** Prevents deletion of certain records, e.g., employees with a specific status.

```sql
CREATE TRIGGER PreventDeleteEmployee
ON Employee
INSTEAD OF DELETE
AS
BEGIN
    DECLARE @EmployeeID INT;
    SELECT @EmployeeID = EmployeeID FROM DELETED;
    
    IF EXISTS (SELECT * FROM Employee WHERE EmployeeID = @EmployeeID AND Status = 'Active')
    BEGIN
        PRINT 'Cannot delete active employees';
    END
    ELSE
    BEGIN
        DELETE FROM Employee WHERE EmployeeID = @EmployeeID;
    END
END;
```
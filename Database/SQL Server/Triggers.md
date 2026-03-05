# What is a Trigger in MS SQL?
A trigger is a special stored procedure that runs automatically when you insert, update, or delete data in a table or view.

# Types of Triggers in SQL Server:
### 1. DML Triggers (Data Manipulation Language Triggers):
- AFTER Triggers (Default):
Run after an INSERT, UPDATE, or DELETE.

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

- INSTEAD OF Triggers:
Run instead of the actual INSERT, UPDATE, or DELETE.

```sql
CREATE TRIGGER PreventActiveDelete
ON Employee
INSTEAD OF DELETE
AS
BEGIN
    IF EXISTS (
        SELECT 1 FROM Employee
        WHERE EmployeeID IN (SELECT EmployeeID FROM DELETED)
        AND Status = 'Active'
    )
    BEGIN
        PRINT 'Active employees cannot be deleted.';
    END
    ELSE
    BEGIN
        DELETE FROM Employee
        WHERE EmployeeID IN (SELECT EmployeeID FROM DELETED);
    END
END;

--If the employee being deleted is active, it blocks the delete.

--Otherwise, it allows the delete.
```

### 2. DDL Triggers (Data Definition Language Triggers):**
- Run when database objects change, like: CREATE, ALTER, DROP a table, view, or schema.

**Example:** Prevent users from dropping important tables.

```sql
CREATE TRIGGER PreventCreateAlterTables
ON DATABASE
FOR CREATE_TABLE, ALTER_TABLE
AS
BEGIN
    PRINT 'Creating or altering tables is not allowed in this database.';
    ROLLBACK;
END;
```

```sql
CREATE TRIGGER PreventTableDrop
ON DATABASE
FOR DROP_TABLE
AS
BEGIN
    PRINT 'You are not allowed to drop tables in this database.';
    ROLLBACK;
END;
```

### 3. LOGON and LOGOFF Triggers**
- Run when a user logs in or logs out of SQL Server.

```sql
--Step 1: Create a table to store login info
CREATE TABLE LoginAudit (
    LoginTime DATETIME,
    LoginName SYSNAME,
    HostName NVARCHAR(100)
);
```

```sql
--Step 2: Create the LOGON trigger
CREATE TRIGGER LogUserLogin
ON ALL SERVER
FOR LOGON
AS
BEGIN
    INSERT INTO LoginAudit (LoginTime, LoginName, HostName)
    VALUES (
        GETDATE(),
        ORIGINAL_LOGIN(),
        HOST_NAME()
    );
END;
```

**Example:** Used for logging or security checks.

# Uses of Triggers

- **Data Validation:** Make sure the data is correct before saving — for example, stop negative values from being inserted.
```sql
CREATE TRIGGER PreventNegativeSalary
ON Employee
FOR INSERT
AS
BEGIN
    IF EXISTS (SELECT * FROM INSERTED WHERE Salary < 0)
    BEGIN
        RAISERROR('Salary cannot be negative.', 16, 1);
        ROLLBACK;
    END
END;
```
- **Auditing:** Track changes record - who changed what and when.
```sql
CREATE TABLE EmployeeAudit (
    EmpID INT,
    ChangedBy SYSNAME,
    ChangeDate DATETIME
);

CREATE TRIGGER AuditEmployeeUpdate
ON Employee
AFTER UPDATE
AS
BEGIN
    INSERT INTO EmployeeAudit (EmpID, ChangedBy, ChangeDate)
    SELECT EmployeeID, SYSTEM_USER, GETDATE()
    FROM INSERTED;
END;
```

- **Cascading Actions:** Automatically change related tables — for example, delete child records when a parent record is deleted.
```sql
CREATE TRIGGER CascadeDeleteDependents
ON Employee
AFTER DELETE
AS
BEGIN
    DELETE FROM Dependent
    WHERE EmpID IN (SELECT EmployeeID FROM DELETED);
END;
```

- **Preventing Invalid Transactions:** Stop unwanted changes — like blocking updates if a record is marked as "Reviewed".
```sql
CREATE TRIGGER PreventReviewedUpdate
ON Orders
FOR UPDATE
AS
BEGIN
    IF EXISTS (
        SELECT * FROM DELETED WHERE Status = 'Reviewed'
    )
    BEGIN
        RAISERROR('Reviewed records cannot be updated.', 16, 1);
        ROLLBACK;
    END
END;
```

# 1. Creating a Trigger
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

# 2. Executing a Trigger
Triggers are automatically executed when the specified event occurs on the table. You don't manually execute a trigger. It fires in response to an INSERT, UPDATE, or DELETE operation.

**Example:** When you insert a row into the Employee table, the LogEmployeeInsert trigger will automatically execute:

```sql
INSERT INTO Employee (EmployeeID, Name, Position)
VALUES (1, 'John Doe', 'Manager');
```
This will cause the trigger LogEmployeeInsert to log the action in the AuditLog table.

# 3. Altering a Trigger
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

# 4. Dropping a Trigger
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
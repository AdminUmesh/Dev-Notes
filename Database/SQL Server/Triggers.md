# Trigger in MS SQL?
A trigger is just like a stored procedure that runs automatically when someone insert, update, or delete data in a table or view.

# Types of Triggers

### **1. DML Triggers (Data Manipulation Language Triggers):**

- **AFTER Triggers (Default):**
Run after an INSERT, UPDATE, or DELETE.

```sql
CREATE TRIGGER TriggerName
ON TableName
AFTER INSERT
AS
BEGIN
    INSERT INTO AuditLog (ActionType, TableName, ActionDate)
    VALUES ('INSERT', 'Employee', GETDATE());
END;
```

- **INSTEAD OF Triggers:**
Run instead of the actual INSERT, UPDATE, or DELETE.

```sql
CREATE TRIGGER TriggerName
ON TableName
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

### **2. DDL Triggers (Data Definition Language Triggers):**
- Run when database objects change, like: CREATE, ALTER, DROP a table, view, or schema.

**Example:** Prevent users from dropping important tables.

```sql
CREATE TRIGGER TriggerName
ON DATABASE
FOR CREATE_TABLE, ALTER_TABLE, DROP_TABLE
AS
BEGIN
    PRINT 'Creating, altering or drop tables is not allowed in this database.';
    ROLLBACK;
END;
```

### **3. LOGON Triggers**
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
CREATE TRIGGER TriggerName
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

# **Uses of Triggers**

### Data Validation:
 Make sure the data is correct before saving — for example, stop negative values from being inserted.
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
### Auditing:
Track changes record - who changed what and when.
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

### Cascading Actions:
 Automatically change related tables — for example, delete child records when a parent record is deleted.
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

### Preventing Invalid Transactions:
 Stop unwanted changes — like blocking updates if a record is marked as "Reviewed".
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



# **1. Altering a Trigger**
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

# **2. Dropping a Trigger**
To remove a trigger from a table, you can use the DROP TRIGGER command.

**Syntax:**
```sql
DROP TRIGGER TriggerName;
```

# **Magic Tables in SQL Server**
Magic tables are temporary internal tables (INSERTED and DELETED) created automatically by SQL Server during DML operations (INSERT, UPDATE, DELETE).

**Key Points**
- Created automatically by SQL Server
- Not physical tables (exist only temporarily)
- Used inside triggers only
- Cannot be accessed directly outside triggers

### **Types of Magic Tables**
**1. INSERTED Table**
👉 Stores new rows

**Used in:**
- INSERT
- UPDATE (new values)

**2. DELETED Table**
👉 Stores old rows

**Used in:**
- DELETE
- UPDATE (old values)

### **Magic Table Example**

```sql
CREATE TRIGGER trg_AfterInsert
ON Employees
AFTER INSERT
AS
BEGIN
    SELECT * FROM INSERTED;
END

-- This will show newly inserted rows
```

```sql
-- Update Example
CREATE TRIGGER trg_Update
ON Employees
AFTER UPDATE
AS
BEGIN
    SELECT * FROM INSERTED; -- new values
    SELECT * FROM DELETED;  -- old values
END
```

### **Note**
`Think like:`

- INSERTED = after change (new data)
- DELETED = before change (old data)

# **For MySql**
MySQL uses `NEW` and `OLD` keywords inside triggers

## **Comparison**
|SQL Server	|MySQL|
|----------|------|
|INSERTED	|NEW|
|DELETED	|OLD|

### **MySQL Example**
**INSERT Trigger**
```sql
CREATE TRIGGER before_insert_user
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    SET NEW.name = UPPER(NEW.name);
END;
```
👉 NEW = new row values

**UPDATE Trigger**
```sql
CREATE TRIGGER before_update_user
BEFORE UPDATE ON users
FOR EACH ROW
BEGIN
    IF OLD.salary <> NEW.salary THEN
        -- do something
    END IF;
END;
```
👉 OLD = previous values
👉 NEW = updated values

**DELETE Trigger**
```sql
CREATE TRIGGER before_delete_user
BEFORE DELETE ON users
FOR EACH ROW
BEGIN
    -- access OLD values
END;
```
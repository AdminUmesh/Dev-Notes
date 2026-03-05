
# 🔐 SQL Server Database Security Guide: Beginner to Advanced

---

## 🟢 Beginner Level: Basic Concepts

### ✅ What is Security in SQL Server?
- Security protects data and operations from unauthorized access.
- Two main levels:
  - **Server-Level Security**: Who can connect to SQL Server?
  - **Database-Level Security**: What can they do inside a database?

### ✅ Logins (Server-Level Authentication)
```sql
CREATE LOGIN user1 WITH PASSWORD = 'StrongPass123';
```

### ✅ Check Existing Logins
```sql
SELECT name, type_desc FROM sys.server_principals;
```

### ✅ Default Login Info
```sql
SELECT SYSTEM_USER AS LoginName;
```

### ✅ Grant Sysadmin Role
```sql
ALTER SERVER ROLE sysadmin ADD MEMBER user1;
```

### ✅ Remove Sysadmin Role
```sql
ALTER SERVER ROLE sysadmin DROP MEMBER user1;
```

---

## 🟡 Intermediate Level: Database Users & Permissions

### ✅ Create Database User
```sql
USE YourDatabase;
CREATE USER db_user1 FOR LOGIN user1;
```

### ✅ View Users in a Database
```sql
SELECT name, type_desc FROM sys.database_principals
WHERE type IN ('S', 'U', 'G', 'E') AND name NOT LIKE '##%';
```

### ✅ Grant Permissions
```sql
GRANT SELECT TO db_user1;
GRANT INSERT TO db_user1;
GRANT EXECUTE TO db_user1;
```

### ✅ Revoke/Deny Permissions
```sql
REVOKE SELECT ON dbo.Table1 FROM db_user1;
DENY DELETE ON dbo.Table1 TO db_user1;
```

### ✅ Check User Permissions
```sql
SELECT dp.name, p.permission_name, p.state_desc, o.name AS ObjectName
FROM sys.database_permissions p
JOIN sys.database_principals dp ON p.grantee_principal_id = dp.principal_id
LEFT JOIN sys.objects o ON p.major_id = o.object_id
WHERE dp.name = 'db_user1';
```

---

## 🟠 Intermediate+: Roles & Ownership

### ✅ Common Database Roles
| Role Name         | Description                          |
|-------------------|--------------------------------------|
| `db_datareader`   | Can read all tables/views             |
| `db_datawriter`   | Can insert/update/delete              |
| `db_owner`        | Full control of the database          |
| `db_ddladmin`     | Can run DDL commands (CREATE/ALTER)   |
| `db_securityadmin`| Can manage permissions                |

### ✅ Assign to a Role
```sql
ALTER ROLE db_datareader ADD MEMBER db_user1;
```

### ✅ Remove from a Role
```sql
ALTER ROLE db_datareader DROP MEMBER db_user1;
```

### ✅ Change Database Owner
```sql
ALTER AUTHORIZATION ON DATABASE::YourDatabase TO user1;
```

---

## 🔴 Advanced Level: Full Control & Special Topics

### ✅ sysadmin Login Access
- Has full access to all databases and objects
- Does **not** require a mapped user in any database

### ✅ Drop a Database
Only allowed if login is:
- `sysadmin`
- OR the **owner** of the database

```sql
DROP DATABASE YourDatabase;
```

### ✅ Execute As (User Impersonation)
```sql
EXECUTE AS USER = 'db_user1';
-- Run some SQL as that user
REVERT; -- Go back to original user
```

### ✅ Check Orphaned Users
```sql
USE YourDatabase;
EXEC sp_change_users_login 'Report';
```

### ✅ Fix Orphaned Users
```sql
EXEC sp_change_users_login 'Auto_Fix', 'db_user1', NULL, 'StrongPass123';
-- or for modern versions
ALTER USER db_user1 WITH LOGIN = user1;
```

---

## 📌 Real-World Tips

- 🔐 Always use **least privilege** principle (only give what’s needed)
- 🧪 Use `EXECUTE AS` to test permissions safely
- 📂 Use roles instead of granting direct permissions to many users
- 🚫 Avoid giving `sysadmin` unless absolutely required
- 🔄 Always audit login, user, and role mappings

---

## 🧠 Glossary

| Term         | Meaning                                        |
|--------------|------------------------------------------------|
| Login        | Server-level identity to connect to SQL Server |
| User         | Database-level identity mapped to a login      |
| Role         | A collection of permissions                    |
| sysadmin     | Server-level admin role                        |
| db_owner     | Full control over one database                 |
| GRANT        | Allow action                                   |
| REVOKE       | Remove previously granted permission           |
| DENY         | Explicitly block action                        |

---

Created with ❤️ for secure SQL Server management.

# Test this Example
```sql
-- 🔹 Step 1: Create a new login (NO sysadmin permission)
CREATE LOGIN test_login WITH PASSWORD = 'Test@1234';

-- 🔹 Step 2: Create a new database
CREATE DATABASE TestDB;

-- 🔹 Step 3: Switch to that database
USE TestDB;

-- 🔹 Step 4: Create a sample table
CREATE TABLE Employees (
    Id INT PRIMARY KEY,
    Name NVARCHAR(50),
    Salary MONEY
);

-- 🔹 Step 5: Insert sample data
INSERT INTO Employees VALUES (1, 'Alice', 50000), (2, 'Bob', 60000);

-- 🔹 Step 6: Try logging in with test_login manually
-- (This will fail to access TestDB because no DB user exists yet)

-- 🔹 Step 7: Create a database user mapped to test_login
CREATE USER test_user FOR LOGIN test_login;

-- 🔹 Step 8: Grant SELECT permission only
GRANT SELECT ON Employees TO test_user;

-- 🔹 Optional: Verify permission mapping
SELECT 
    dp.name AS UserName,
    p.permission_name,
    p.state_desc,
    o.name AS ObjectName
FROM sys.database_permissions p
JOIN sys.database_principals dp ON p.grantee_principal_id = dp.principal_id
LEFT JOIN sys.objects o ON p.major_id = o.object_id
WHERE dp.name = 'test_user';

-- 🔹 Step 9: Test — Log in as test_login and try the following
-- This works:
-- SELECT * FROM TestDB.dbo.Employees;

-- These will fail:
-- INSERT INTO Employees VALUES (3, 'Charlie', 70000);
-- DELETE FROM Employees WHERE Id = 1;
-- CREATE TABLE Test (ID INT);
-- EXEC sp_helpdb;  -- No permission

```
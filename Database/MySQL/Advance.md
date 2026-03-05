
# ✅ 1. Basics of Stored Procedures

## 🔹 Goal
Understand how to create, call, and drop stored procedures, including using `IN` and `OUT` parameters.

---

## 🔸 What is a Stored Procedure?

A **Stored Procedure** is a set of SQL statements stored in the database that can be executed repeatedly.  
It helps encapsulate logic, reuse code, and improve security.

---

## 🔸 Syntax Basics

```sql
DELIMITER $$

CREATE PROCEDURE procedure_name(IN input_param TYPE, OUT output_param TYPE)
BEGIN
    -- SQL statements
END$$

DELIMITER ;
```

- `IN` – Input parameter
- `OUT` – Output parameter
- `DELIMITER` – Temporarily changes the SQL command delimiter so semicolons inside the procedure don't end execution.

---

## 🧪 Assignment: Get User’s Full Name by ID

### 👥 Table Setup
```sql
CREATE TABLE users (
    id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50)
);

INSERT INTO users VALUES (1, 'Amit', 'Sharma');
```

---

### 🛠️ Create Stored Procedure
```sql
DELIMITER $$

CREATE PROCEDURE get_full_name(IN user_id INT, OUT full_name VARCHAR(100))
BEGIN
    SELECT CONCAT(first_name, ' ', last_name)
    INTO full_name
    FROM users
    WHERE id = user_id;
END$$

DELIMITER ;
```

---

### ▶️ Call the Procedure
```sql
CALL get_full_name(1, @name);
SELECT @name;  -- Output: Amit Sharma
```

---

## 🧹 Drop the Procedure
```sql
DROP PROCEDURE IF EXISTS get_full_name;
```

---

## 📌 Notes

- You can use `SHOW CREATE PROCEDURE procedure_name;` to see the definition.
- Use `SHOW PROCEDURE STATUS LIKE 'get_full_name';` to check if it exists.
- Use `INOUT` if you want a parameter to act as both input and output.

---

## 🧩 Related Example

```sql
CREATE DEFINER=`crmnew`@`%` PROCEDURE `crm_Get_Notification_by_user`(
    IN p_userId VARCHAR(12)
)
BEGIN
    -- Declare variables to store user details
    DECLARE var_role VARCHAR(15);
    -- Additional logic here
END;
```

---

## ✅ Summary

| Command | Purpose |
|--------|---------|
| `CREATE PROCEDURE` | Create a new procedure |
| `CALL` | Execute a procedure |
| `DROP PROCEDURE` | Remove a procedure |
| `IN / OUT / INOUT` | Define parameter types |

Stored procedures are powerful for abstracting logic and improving performance and control in database applications.



# 2📘 MySQL User and Privilege System

## 🔸 What is a MySQL User?

A **MySQL user** is a combination of:
- **Username** (e.g., `root`, `app_user`)
- **Host** (e.g., `localhost`, `%`, IP)

MySQL matches both to authenticate: `'username'@'host'`.

Examples:
- `root@localhost` – Only connects from the same server.
- `app_user@%` – Can connect from any IP address.
- `read_user@192.168.1.5` – Only from IP `192.168.1.5`.

## 🔸 Where Are Users Stored?

MySQL stores users in the internal **`mysql.user`** system table.

You can query this table:
```sql
SELECT user, host FROM mysql.user;
```

## 🔸 What Does `%` Mean in Host?

`%` is a wildcard meaning **"any host"**.  
So `'user'@'%'` allows access from any machine (if network/firewall allows it).

---

## 🔸 Create, Delete, and Update Users

### ✅ Create User:
```sql
CREATE USER 'username'@'host' IDENTIFIED BY 'password';
```
Example:
```sql
CREATE USER 'report_user'@'%' IDENTIFIED BY 'secret123';
```

### ✅ Grant Privileges:
```sql
GRANT SELECT, INSERT ON database_name.* TO 'username'@'host';
```

### ✅ Revoke Privileges:
```sql
REVOKE INSERT ON database_name.* FROM 'username'@'host';
```

### ✅ Delete User:
```sql
DROP USER 'username'@'host';
```

### ✅ Change User Password:
```sql
ALTER USER 'username'@'host' IDENTIFIED BY 'new_password';
```

---

## 🔸 View All Users

```sql
-- See all user@host combinations:
SELECT user, host FROM mysql.user;

-- See unique usernames only:
SELECT DISTINCT user FROM mysql.user;
```

---

## 🔸 Check Who You Are Logged In As

```sql
SELECT USER();         -- Login identity (username@host used to connect)
SELECT CURRENT_USER(); -- Authenticated identity (may differ due to proxying)
```

---

## 🔸 See Grants for a User

```sql
SHOW GRANTS FOR 'username'@'host';
```

Example:
```sql
SHOW GRANTS FOR 'report_user'@'%';
```

---

## 📌 Summary Table

| Command | Purpose |
|--------|---------|
| `CREATE USER` | Add a new user |
| `DROP USER` | Delete a user |
| `GRANT` | Give privileges |
| `REVOKE` | Remove privileges |
| `ALTER USER` | Change password |
| `SELECT user, host FROM mysql.user` | View all users |

---

📝 **Note**: Only users with `SUPER`, `CREATE USER`, or `SELECT` on `mysql.*` can manage other users.


# ✅ 3. DEFINER Clause and Security Context

## 🔹 Goal
Understand how the `DEFINER` clause affects permissions for stored procedures in MySQL.

---

## 🔸 Concept

In MySQL, the `DEFINER` clause determines **which user's privileges** are checked when a stored routine (procedure, function, view, or trigger) is executed.

By default, routines run with the privileges of the user who defined them (`SQL SECURITY DEFINER`).  
This can be changed using `SQL SECURITY INVOKER`.

---

## 🔸 Assignment: Create a Procedure with a DEFINER

### 🎯 Objective:
Allow a limited user (`app_user`) to delete records using a procedure created by a privileged user (`admin_user`).

---

## 🔹 Step-by-Step Instructions

### ✅ Step 1: Create Admin and App Users
```sql
-- Admin user with all privileges
CREATE USER 'admin_user'@'localhost' IDENTIFIED BY 'Admin@123';
GRANT ALL PRIVILEGES ON *.* TO 'admin_user'@'localhost';

-- Limited user with no DELETE rights
CREATE USER 'app_user'@'localhost' IDENTIFIED BY 'App@123';
GRANT SELECT, EXECUTE ON your_db.* TO 'app_user'@'localhost';
```

---

### ✅ Step 2: Create Test Table and Data (as admin)
```sql
USE your_db;

CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100)
);

INSERT INTO employees VALUES (1, 'John'), (2, 'Alice');
```

---

### ✅ Step 3: Create Stored Procedure with DEFINER (as admin)
```sql
DELIMITER $$

CREATE DEFINER='admin_user'@'localhost' PROCEDURE delete_employee(IN emp_id INT)
SQL SECURITY DEFINER
BEGIN
    DELETE FROM employees WHERE id = emp_id;
END$$

DELIMITER ;
```

---

### ✅ Step 4: Test as `app_user`
```sql
-- Login as 'app_user'@'localhost'
CALL delete_employee(1); -- Should work, even though app_user has no DELETE privilege
```

---

## 🔍 What to Watch:

| Scenario | Result |
|----------|--------|
| `app_user` calls procedure | ✅ Works due to `DEFINER='admin_user'` |
| `admin_user` is deleted | ❌ Procedure fails due to missing DEFINER user |
| Procedure is changed to `SQL SECURITY INVOKER` | ❌ `app_user` gets permission error |

---

## 🧪 Optional: View Procedure Definer
```sql
SELECT ROUTINE_NAME, DEFINER
FROM information_schema.ROUTINES
WHERE ROUTINE_TYPE = 'PROCEDURE'
  AND ROUTINE_NAME = 'delete_employee';
```

---

## 📌 Summary

- `DEFINER` defines **whose privileges** are used to execute a routine.
- Stored routines can **elevate privileges** for limited users.
- If the DEFINER user is **removed**, the routine **will stop working**.


    
# ✅ 4. SQL SECURITY INVOKER vs DEFINER

## 🔹 Goal
Understand the difference between `SQL SECURITY DEFINER` and `SQL SECURITY INVOKER`.

---

## 🔸 Assignment

### 👥 Create Users
```sql
-- Full access user
CREATE USER 'proc_owner'@'localhost' IDENTIFIED BY 'Owner@123';
GRANT ALL PRIVILEGES ON *.* TO 'proc_owner'@'localhost';

-- Limited user
CREATE USER 'limited_user'@'localhost' IDENTIFIED BY 'Limited@123';
GRANT SELECT, EXECUTE ON your_db.* TO 'limited_user'@'localhost';
```

---

### 🧾 Setup: Create Sample Table
```sql
USE your_db;

CREATE TABLE users (
    id INT PRIMARY KEY,
    name VARCHAR(100)
);

INSERT INTO users VALUES (1, 'Alice'), (2, 'Bob');
```

---

### 🛠️ Create Procedures

#### A. `SQL SECURITY DEFINER`
```sql
DELIMITER $$

CREATE DEFINER='proc_owner'@'localhost'
PROCEDURE delete_user(IN uid INT)
SQL SECURITY DEFINER
BEGIN
    DELETE FROM users WHERE id = uid;
END$$

DELIMITER ;
```

#### B. `SQL SECURITY INVOKER`
```sql
DELIMITER $$

CREATE DEFINER='proc_owner'@'localhost'
PROCEDURE delete_user_invoker(IN uid INT)
SQL SECURITY INVOKER
BEGIN
    DELETE FROM users WHERE id = uid;
END$$

DELIMITER ;
```

---

## 🧪 Test as `limited_user`
```sql
CALL delete_user(1);          -- ✅ Works (uses DEFINER's privileges)
CALL delete_user_invoker(2);  -- ❌ Fails (uses INVOKER's privileges)
```

---

# ✅ 5. Real-World Debugging and Use-Cases

---

## 🔹 Use Case 1: Procedure Fails Due to Missing DEFINER

### ❌ Problem:
```sql
CREATE DEFINER='old_user'@'%' PROCEDURE update_status() ...
```
`old_user` is deleted, and now the procedure fails.

### 🔧 Fix:
```sql
-- Find all routines with their definer
SELECT ROUTINE_NAME, DEFINER 
FROM information_schema.ROUTINES 
WHERE ROUTINE_TYPE = 'PROCEDURE';

-- Update to a valid definer
ALTER DEFINER='new_admin'@'localhost' PROCEDURE update_status;
```

---

## 🔹 Use Case 2: INVOKER Permission Issue

A junior developer uses:
```sql
SQL SECURITY INVOKER
```

Clients running the procedure get:
```
ERROR 1142 (42000): DELETE command denied...
```

### ✅ Solution:
Recreate with:
```sql
SQL SECURITY DEFINER
```
and ensure the DEFINER has required privileges.

---

# 🧪 Bonus Task: Combine Everything — Notification System

## 🎯 Project: Mark All Notifications as Read

### 🔸 Table
```sql
CREATE TABLE notifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    message TEXT,
    is_read BOOLEAN DEFAULT FALSE
);
```

### 🔸 Procedure
```sql
DELIMITER $$

CREATE DEFINER='proc_owner'@'localhost'
PROCEDURE mark_all_as_read(IN userId INT)
SQL SECURITY DEFINER
BEGIN
    UPDATE notifications SET is_read = TRUE WHERE user_id = userId;
END$$

DELIMITER ;
```

### 🔸 Setup Limited User
```sql
CREATE USER 'frontend_user'@'localhost' IDENTIFIED BY 'Front@123';
GRANT SELECT, EXECUTE ON your_db.* TO 'frontend_user'@'localhost';
```

### 🔸 Test as `frontend_user`
```sql
CALL mark_all_as_read(2);  -- ✅ Works, even without UPDATE rights
```

---

## ✅ Summary

| Security Mode | Privilege Source | Works for Low-Privilege Users |
|---------------|------------------|-------------------------------|
| DEFINER        | Routine Creator  | ✅ Yes                        |
| INVOKER        | Caller           | ❌ No (unless caller has rights) |




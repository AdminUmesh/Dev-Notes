# 1️⃣ Privileges

### 🔹 What is a Privilege?
A **privilege** is a **permission** that controls what a user can do in MySQL.

Examples:
- Read data → `SELECT`
- Insert data → `INSERT`
- Modify data → `UPDATE`
- Delete data → `DELETE`
- Create tables → `CREATE`
- Run procedures → `EXECUTE`

👉 Privileges protect your database from:
- Accidental data loss
- Unauthorized access
- Security breaches

---

### 🔹 Why Privileges Exist (Real Example)

Imagine 3 users:

| User | Purpose | Privileges |
|----|----|----|
| App user | API access | SELECT, INSERT |
| Developer | Dev work | SELECT, INSERT, UPDATE |
| DBA | Admin | ALL |

Without privileges → **any user could DROP tables** ❌

---

# 2️⃣ MySQL User and Privilege System

### 🔹 What is a MySQL User?

A **MySQL user** is a combination of:
- **Username** (e.g., `root`, `app_user`)
- **Host** (e.g., `localhost`, `%`, IP)

MySQL authenticates using:
```
'username'@'host'
```

### Examples:
- `root@localhost` – Only same machine
- `app_user@%` – Any IP
- `read_user@192.168.1.5` – Only specific IP

---

### 🔹 Where Are Users Stored?

MySQL stores users in:
```
mysql.user
```

```sql
SELECT user, host FROM mysql.user;
```

---

### 🔹 What Does `%` Mean?

`%` = wildcard = **any host**

```
'user'@'%'
```
Means: can connect from anywhere (if network allows).

---

## 🔹 Create, Delete, Update Users

### ✅ Create User
```sql
CREATE USER 'report_user'@'%' IDENTIFIED BY 'password123';
```

### ✅ Change Password
```sql
ALTER USER 'report_user'@'%' IDENTIFIED BY 'newpass';
```

### ✅ Delete User
```sql
DROP USER 'report_user'@'%';
```

---

## 🔹 View Users
```sql
SELECT user, host FROM mysql.user;
SELECT DISTINCT user FROM mysql.user;
```

---

## 🔹 Check Login Identity
```sql
SELECT USER();         -- login user
SELECT CURRENT_USER(); -- authenticated user
```

---

# 3️⃣ Privileges & Permission Types

### 🔹 Privilege Levels

| Level | Scope |
|----|----|
| Global | `*.*` |
| Database | `db_name.*` |
| Table | `db.table` |
| Column | Specific columns |
| Routine | Procedures / functions |

---

### 🔹 Grant Privileges

```sql
GRANT SELECT, INSERT ON mydb.* TO 'app_user'@'%';
```

---

### 🔹 Revoke Privileges

```sql
REVOKE INSERT ON mydb.* FROM 'app_user'@'%';
```

---

### 🔹 View User Privileges

```sql
SHOW GRANTS FOR 'app_user'@'%';
```

---

# 4️⃣ FLUSH PRIVILEGES

### 🔹 What is FLUSH PRIVILEGES?

Reloads privilege tables into memory.

```sql
FLUSH PRIVILEGES;
```

### When needed:
- Manual updates to `mysql.*` tables

### Not needed after:
- `GRANT`
- `REVOKE`
- `CREATE USER`

---

# 5️⃣ DEFINER Clause

### 🔹 What is DEFINER?

`DEFINER` defines **whose privileges are used** when a routine runs.

```sql
CREATE DEFINER='admin'@'localhost' PROCEDURE sp_test()
```

---

### 🔹 Is DEFINER Mandatory?
❌ No

Without DEFINER:
```sql
CREATE PROCEDURE sp_test()
```

MySQL uses:
```
DEFINER = CURRENT_USER
```

---

### 🔹 Why DEFINER Causes Errors

If DEFINER user is deleted:
```
ERROR: DEFINER user does not exist
```

Common during:
- DB migration
- Local setup

---

# 6️⃣ SQL SECURITY DEFINER

## 🔹 Concept

Procedure runs using **creator's privileges**.

```sql
SQL SECURITY DEFINER
```

Allows **privilege escalation safely**.

---

## 🔹 Example

Admin creates procedure:
```sql
CREATE DEFINER='admin'@'localhost'
PROCEDURE delete_employee(IN id INT)
SQL SECURITY DEFINER
BEGIN
  DELETE FROM employees WHERE emp_id = id;
END;
```

Limited user can call it without DELETE privilege.

---

# 7️⃣ SQL SECURITY INVOKER

## 🔹 Concept

Procedure runs using **caller's privileges**.

```sql
SQL SECURITY INVOKER
```

Caller **must already have permissions**.

---

## 🔹 Comparison

| Mode | Privilege Used | Risk |
|----|----|----|
| DEFINER | Creator | High if misused |
| INVOKER | Caller | Safer |

---

# 8️⃣ Stored Procedure Security

- Uses DEFINER or INVOKER
- Can expose sensitive operations
- Must follow least privilege

---

# 9️⃣ View Security

Views also have DEFINER.

```sql
CREATE VIEW v_users AS SELECT * FROM users;
```

- View may expose hidden columns
- Security depends on DEFINER

---

# 🔟 Trigger Security

- Always runs as DEFINER
- Cannot use INVOKER
- Dangerous if misconfigured

---

# 1️⃣1️⃣ Event Scheduler Security

- Background jobs
- Runs as DEFINER
- Must ensure DEFINER exists

---

# 1️⃣2️⃣ Information Schema Security Tables

Important tables:
- `mysql.user`
- `mysql.db`
- `mysql.tables_priv`
- `information_schema.ROUTINES`

Used for auditing & debugging.

---

# 1️⃣3️⃣ Database Migration Security Issues

Common problems:
- Missing DEFINER users
- Different privilege sets
- Hardcoded prod users

Best practice:
- Remove DEFINER
- Use CURRENT_USER

---

# 1️⃣4️⃣ Least Privilege Principle

> Give **minimum permissions required**.

❌ Bad:
```sql
GRANT ALL ON *.* TO 'app'@'%';
```

✅ Good:
```sql
GRANT SELECT, EXECUTE ON appdb.* TO 'app'@'%';
```

---
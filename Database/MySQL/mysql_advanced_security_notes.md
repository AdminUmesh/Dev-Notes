
# 🔐 Advanced MySQL Security, Logging, and Data Protection

This guide includes advanced topics that help prevent unauthorized data modification (INSERT, UPDATE, DELETE), improve auditing, and secure user accounts.

---

## ✅ 1. User Account Locking & Password Policies

### 🔹 Locking Accounts
Prevent login from a specific account.

```sql
ALTER USER 'user1'@'localhost' ACCOUNT LOCK;
```

### 🔹 Expiring Passwords
Force users to reset passwords after expiration.

```sql
ALTER USER 'user1'@'localhost' PASSWORD EXPIRE;
```

### 🔹 Password Reuse & Strength Policy (MySQL 8+)
```sql
SET GLOBAL validate_password.length = 12;
SET GLOBAL validate_password.policy = 2;
```

---

## ✅ 2. Role-Based Access Control (RBAC)

### 🔹 Create and Assign Roles

```sql
CREATE ROLE read_only;
GRANT SELECT ON your_db.* TO read_only;

GRANT read_only TO 'app_user'@'%';
SET DEFAULT ROLE read_only TO 'app_user'@'%';
```

### 🔹 View Role Grants

```sql
SHOW GRANTS FOR 'app_user'@'%';
```

---

## ✅ 3. SQL SECURITY INVOKER vs DEFINER (Recap)

Use `SQL SECURITY DEFINER` for procedures that need elevated rights:
```sql
CREATE DEFINER='admin'@'localhost'
PROCEDURE delete_logs()
SQL SECURITY DEFINER
BEGIN
  DELETE FROM logs WHERE created_at < NOW() - INTERVAL 30 DAY;
END;
```

---

## ✅ 4. General Log & Audit Logging

### 🔹 Enable General Log (dev only)
```sql
SET GLOBAL general_log = 'ON';
SET GLOBAL general_log_file = '/var/lib/mysql/general.log';
```

### 🔹 Read General Log
```bash
tail -f /var/lib/mysql/general.log
```

---

## ✅ 5. Prevent Data Modification by Users

### 🔹 Create Read-Only User

```sql
CREATE USER 'readonly_user'@'%' IDENTIFIED BY 'Read@123';
GRANT SELECT ON your_db.* TO 'readonly_user'@'%';
```

### 🔹 Block INSERT/DELETE/UPDATE

Do NOT grant these:
```sql
-- DO NOT DO THIS for readonly users
-- GRANT INSERT, DELETE, UPDATE ON your_db.* TO 'readonly_user'@'%';
```

---

## ✅ 6. Triggers for Change Monitoring

Track changes to sensitive data.

```sql
CREATE TABLE user_audit (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  old_name VARCHAR(100),
  new_name VARCHAR(100),
  changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DELIMITER $$

CREATE TRIGGER before_user_update
BEFORE UPDATE ON users
FOR EACH ROW
BEGIN
  INSERT INTO user_audit(user_id, old_name, new_name)
  VALUES (OLD.id, OLD.first_name, NEW.first_name);
END$$

DELIMITER ;
```

---

## ✅ 7. SSL/TLS Encryption (for secure network connections)

### 🔹 In `my.cnf`
```ini
[mysqld]
ssl-ca=ca.pem
ssl-cert=server-cert.pem
ssl-key=server-key.pem
require_secure_transport = ON
```

---

## ✅ 8. Application-Level Data Encryption

Use MySQL AES functions for simple field-level encryption (not recommended for high-security apps).

```sql
-- Store encrypted value
INSERT INTO secrets (val) VALUES (AES_ENCRYPT('mydata', 'key'));

-- Retrieve
SELECT AES_DECRYPT(val, 'key') FROM secrets;
```

---

## ✅ 9. Check Who Can Modify Data

```sql
SELECT user, host FROM mysql.user;

-- List users with INSERT/DELETE/UPDATE
SHOW GRANTS FOR 'username'@'host';
```

---

## 🧪 Bonus: Real-world Security Checks

| Check | Command |
|-------|---------|
| 🔎 Who can delete? | `SHOW GRANTS FOR 'user'@'host';` |
| 🔐 Who is DEFINER? | `SELECT ROUTINE_NAME, DEFINER FROM information_schema.ROUTINES;` |
| 🚫 Block changes | `REVOKE INSERT, UPDATE, DELETE ON db.* FROM 'user'@'host';` |
| 📜 View access logs | General/Audit logs |

---

## 🧩 Final Tips

- Always use **`LIMIT`** and **`WHERE`** in `DELETE` and `UPDATE`.
- Enable **binary logs** for replication + point-in-time recovery.
- Keep your **root** and **admin** users accessible only from `localhost`.


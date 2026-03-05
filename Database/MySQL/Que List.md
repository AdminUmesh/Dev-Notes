# 📘 Challenging Database Interview Q&A (Developer → Tech Lead)

> **Audience:** Backend Developer, Full‑Stack Developer, Senior Developer, Tech Lead, Architect  
> **Focus:** Real‑world decision making, production safety, security, scalability

---

## 1️⃣ Prevent direct DELETE from DB tools but allow DELETE via API
**Role:** Backend Developer / Senior Developer

### ✅ Solution
- Do **NOT** grant `DELETE` privilege to application DB users
- Allow API to delete via **stored procedures only**
- Use `SQL SECURITY DEFINER`

```sql
REVOKE DELETE ON appdb.* FROM 'app_user'@'%';
GRANT EXECUTE ON appdb.* TO 'app_user'@'%';
```

```sql
CREATE DEFINER='admin'@'localhost'
PROCEDURE delete_order(IN id INT)
SQL SECURITY DEFINER
BEGIN
  DELETE FROM orders WHERE order_id = id;
END;
```

### 🎯 Why this works
- SQLyog / Workbench user cannot DELETE
- API can only delete through controlled logic
- Enforces **least privilege**

---

## 2️⃣ Prevent even DB developers from deleting production data
**Role:** Tech Lead / Architect

### ✅ Solution
- Use **soft delete** (`is_deleted` flag)
- Block hard DELETE at DB level
- Require **approval workflow** for permanent deletion
- Log all delete attempts

```sql
ALTER TABLE users ADD is_deleted BOOLEAN DEFAULT FALSE;
```

```sql
UPDATE users SET is_deleted = TRUE WHERE id = ?;
```

### 🎯 Key mindset
> Production data is **never deleted directly**

---

## 3️⃣ Reflect DB updates on UI without page refresh
**Role:** Full‑Stack Developer

### ✅ Answer
- WebSockets (best)
- Server‑Sent Events (SSE)
- Long polling (fallback)

```text
DB Change → Backend Event → WebSocket → UI Update
```

### 🎯 Why WebSocket
- Low latency
- Bi‑directional
- Real‑time friendly

---

## 4️⃣ When WebSockets are a bad choice
**Role:** Senior Developer

### ❌ Avoid WebSockets when:
- Millions of idle users
- Rare updates
- Stateless infrastructure only

### ✅ Better options
- Polling (simple)
- SSE (one‑way updates)

---

## 5️⃣ Measure stored procedure execution time
**Role:** Backend Developer / DB Engineer

### ✅ Techniques
1. Enable slow query log
2. Use performance schema
3. Manual logging inside procedure

```sql
SET start_time = NOW();
-- logic
INSERT INTO proc_log(exec_time)
VALUES (TIMESTAMPDIFF(MICROSECOND, start_time, NOW()));
```

---

## 6️⃣ Log who executed which procedure and from where
**Role:** Backend Developer / Security Engineer

### ✅ Strategy
- Use `CURRENT_USER()`
- Capture `USER()`
- Store IP from API layer

```sql
INSERT INTO audit_log(user, host, action)
VALUES (CURRENT_USER(), USER(), 'delete_order');
```

---

## 7️⃣ Procedure suddenly slow in production
**Role:** Senior Developer / Tech Lead

### ✅ Debug without downtime
- Enable slow query log
- Check execution plan (`EXPLAIN`)
- Compare parameter values
- Review recent data growth

### ❌ Don’t
- Restart DB
- Modify schema blindly

---

## 8️⃣ Choosing MySQL vs SQL Server vs Oracle
**Role:** Tech Lead / Architect

| Factor | MySQL | SQL Server | Oracle |
|----|----|----|----|
| Cost | Free | Paid | Expensive |
| Enterprise | Medium | High | Very High |
| Tooling | Basic | Excellent | Advanced |

### 🎯 Decision based on
- Budget
- Compliance
- Scale
- Support needs

---

## 9️⃣ When MySQL is a bad choice
**Role:** Senior Developer / Architect

### ❌ Avoid MySQL when
- Complex analytics
- Heavy enterprise reporting
- Advanced auditing required

---

## 🔟 Why migrate from MySQL to SQL Server / Oracle
**Role:** Manager / Architect

### Common reasons
- Compliance (SOX, HIPAA)
- Vendor support
- Advanced security
- Enterprise tooling

---

## 1️⃣1️⃣ Fix DEFINER errors after migration without downtime
**Role:** Backend Developer / DB Engineer

### ✅ Fix
- Remove DEFINER
- Use CURRENT_USER

```sql
CREATE PROCEDURE sp_test()
-- no DEFINER
```

Or recreate with valid user

---

## 1️⃣2️⃣ Backward‑compatible DB schema change
**Role:** Senior Developer / Tech Lead

### ✅ Strategy
- Add columns first
- Deploy code
- Migrate data
- Remove old columns later

> Never break running code

---

## 1️⃣3️⃣ Prevent SQL Injection even with DB read access
**Role:** Backend Developer / Security Engineer

### Defense in depth
- Prepared statements
- No dynamic SQL
- No direct table access
- Procedures only

---

## 1️⃣4️⃣ Track & rollback accidental deletion
**Role:** DB Engineer / Tech Lead

### ✅ Methods
- Binary logs
- Point‑in‑time recovery
- Soft delete
- Audit tables

---

## 1️⃣5️⃣ Safe DB access for juniors
**Role:** Tech Lead / Engineering Manager

### ✅ Best practices
- Read‑only access
- No prod credentials
- Feature flags
- Code review for DB changes

---

## 🎯 Final Interview Insight

> Syntax gets you shortlisted.  
> **Judgment gets you selected.**

If you can explain *why* you chose a solution, you’re already senior‑level.



# ✅ SQL Transactions and Commands

## 🔄 What is a Transaction in SQL?
A transaction in SQL is a sequence of one or more SQL statements that are executed as a single unit of work. The idea is that all the operations within a transaction should either complete successfully together (**COMMIT**) or fail together (**ROLLBACK**)—ensuring data integrity.

## 🔄 Key Properties – ACID

| Property | Description |
|----------|-------------|
| A – Atomicity | All operations in the transaction are treated as a single unit. Either all succeed, or none do. |
| C – Consistency | The database remains in a consistent state before and after the transaction. |
| I – Isolation | Transactions do not interfere with each other. Intermediate results are hidden until commit. |
| D – Durability | Once committed, the transaction changes are permanent—even in case of a crash. |

## 🧾 Basic Example

```sql
BEGIN TRANSACTION;

UPDATE accounts SET balance = balance - 500 WHERE account_id = 1;
UPDATE accounts SET balance = balance + 500 WHERE account_id = 2;

COMMIT;
```

If something goes wrong during the process (e.g., second update fails), you can do:

```sql
ROLLBACK;
```

---

## 🔧 Common SQL Commands Used in Transactions

### 1. BEGIN TRANSACTION; or START TRANSACTION;
**Purpose:** Starts a new transaction. This tells the database engine that a group of operations are going to be treated as a single unit.

✅ Example:

```sql
BEGIN TRANSACTION;

UPDATE products SET stock = stock - 1 WHERE product_id = 101;
-- More operations can be added here
```

### 2. COMMIT;
**Purpose:** Ends the current transaction and makes all changes permanent in the database.

✅ Example (continued from above):

```sql
COMMIT;
```

### 3. ROLLBACK;
**Purpose:** Cancels the current transaction and undoes all changes made since the transaction began.

✅ Example:

```sql
BEGIN TRANSACTION;

UPDATE accounts SET balance = balance - 1000 WHERE account_id = 1;
-- Oops! An error occurs

ROLLBACK;
```

### 4. SAVEPOINT
**Purpose:** Creates a bookmark or checkpoint within a transaction. You can roll back to a specific savepoint.

✅ Example:

```sql
BEGIN TRANSACTION;

UPDATE accounts SET balance = balance - 500 WHERE account_id = 1;
SAVEPOINT after_debit;

UPDATE accounts SET balance = balance + 500 WHERE account_id = 2;
-- Update fails

ROLLBACK TO after_debit;

COMMIT;
```

### 5. SET TRANSACTION
**Purpose:** Sets isolation level for the current transaction.

🔐 Common Isolation Levels:
- READ UNCOMMITTED
- READ COMMITTED
- REPEATABLE READ
- SERIALIZABLE

✅ Example:

```sql
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

BEGIN TRANSACTION;

-- Operations

COMMIT;
```

---

## 🔁 Summary Table

| Command           | Purpose                              |
|-------------------|--------------------------------------|
| BEGIN TRANSACTION | Starts a transaction                 |
| COMMIT            | Saves changes made in the transaction |
| ROLLBACK          | Undoes all changes since the start   |
| SAVEPOINT         | Creates a rollback checkpoint        |
| ROLLBACK TO       | Rolls back to a savepoint            |
| SET TRANSACTION   | Defines transaction isolation level  |

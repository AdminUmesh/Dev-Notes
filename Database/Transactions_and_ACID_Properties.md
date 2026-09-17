# Transactions

A transaction is a group of SQL statements executed as a single unit of
work. It either succeeds completely (**COMMIT**) or fails completely
(**ROLLBACK**).

A transaction either:

-   **Commits** (all changes are saved permanently)
-   **Rolls back** (all changes are undone)

------------------------------------------------------------------------

## Why Do We Need Transactions?

-   Maintain data consistency
-   Prevent partial updates
-   Handle failures safely

------------------------------------------------------------------------

## **Example:** Bank Transfer

Transfer ₹1000 from Account A to Account B.

``` sql
BEGIN TRANSACTION;

UPDATE Accounts
SET Balance = Balance - 1000
WHERE AccountId = 1;

UPDATE Accounts
SET Balance = Balance + 1000
WHERE AccountId = 2;

COMMIT;
```

If the second update fails:

``` sql
ROLLBACK;
```

No money is lost because the entire transaction is undone.

------------------------------------------------------------------------

# Transaction Commands

## BEGIN TRANSACTION

Starts a new transaction.

``` sql
BEGIN TRANSACTION;
```

## COMMIT

Permanently saves all changes made during the transaction.

``` sql
COMMIT;
```

## ROLLBACK

Undoes all changes made since the transaction began.

``` sql
ROLLBACK;
```

------------------------------------------------------------------------

# SAVE TRANSACTION (Sql Server) / SAVEPOINT (MySql)

A **Savepoint/ Save Transaction** creates a checkpoint inside a transaction. You can roll
back to the savepoint instead of rolling back the entire transaction.

## For Sql Server
``` sql
BEGIN TRANSACTION;

UPDATE Accounts
SET Balance = Balance - 1000
WHERE AccountId = 1;

SAVE TRANSACTION TransferStarted;
-- SAVEPOINT TransferStarted; //For Mysql

UPDATE Accounts
SET Balance = Balance + 1000
WHERE AccountId = 2;

-- Something went wrong
ROLLBACK TRANSACTION TransferStarted;
-- ROLLBACK TO SAVEPOINT TransferStarted; // For Mysql

COMMIT;
```

> **Note:** `ROLLBACK TRANSACTION` or `ROLLBACK TO` without a savepoint name rolls back
> the entire transaction.

------------------------------------------------------------------------

# @@ROWCOUNT (Sql Server) /ROW_COUNT() (MySql)

`@@ROWCOUNT` , `ROW_COUNT()` returns the number of rows affected by the last SQL
statement.

### Example Sql Sqever

``` sql
UPDATE Accounts
SET Balance = Balance - 1000
WHERE AccountId = 1;

SELECT @@ROWCOUNT;
-- SELECT ROW_COUNT(); //For MySql
```

Output:

``` text
1
```

### Practical Example (SQL Server)

``` sql
BEGIN TRANSACTION;

UPDATE Accounts
SET Balance = Balance - 1000
WHERE AccountId = 1;

IF @@ROWCOUNT = 0
-- IF ROW_COUNT() = 0; //For MySql
BEGIN
    ROLLBACK;
    PRINT 'Source account not found.';
    RETURN;
END

UPDATE Accounts
SET Balance = Balance + 1000
WHERE AccountId = 2;

IF @@ROWCOUNT = 0
-- IF ROW_COUNT() = 0; //For MySql
BEGIN
    ROLLBACK;
    PRINT 'Destination account not found.';
    RETURN;
END

COMMIT;
```

------------------------------------------------------------------------

# Auto-Commit in SQL Server

SQL Server uses **Auto-Commit mode** by default.

-   Every `INSERT`, `UPDATE`, or `DELETE` statement is automatically
    committed.
-   To manage transactions manually, use `BEGIN TRANSACTION`, `COMMIT`,
    and `ROLLBACK`.

``` sql
BEGIN TRANSACTION;

-- SQL statements

COMMIT;
```

or

``` sql
ROLLBACK;
```

> **Interview Tip:** `SELECT @@autocommit` is available in **MySQL**,
> **not SQL Server**.

------------------------------------------------------------------------

# Interview Questions

### Q1. What is a transaction?

A transaction is a group of SQL statements executed as a single unit of
work. It either succeeds completely (**COMMIT**) or fails completely
(**ROLLBACK**).

### Q2. What is the difference between COMMIT and ROLLBACK?

-   **COMMIT:** Saves changes permanently.
-   **ROLLBACK:** Reverts all uncommitted changes.

### Q3. What is a Savepoint?

A savepoint is a checkpoint inside a transaction that allows partial
rollback without undoing the entire transaction.

### Q4. What is `@@ROWCOUNT` used for?

It returns the number of rows affected by the most recently executed SQL
statement.

### Q5. Does SQL Server support `@@autocommit`?

No. `@@autocommit` is a MySQL feature. SQL Server uses auto-commit mode
by default but does not expose it through a variable.

# ACID Properties

ACID properties are four rules that make database transactions reliable and safe.

`We use ACID so that a transaction involves multiple table or database operations, the database doesn't end up in an incorrect or half-completed state.`

## Key Properties – ACID

| Property | Description |
|----------|-------------|
| A – Atomicity | All operations in the transaction are treated as a single unit. Either all succeed, or none do. |
| C – Consistency | The database remains in a consistent state before and after the transaction. |
| I – Isolation | Transactions do not interfere with each other. Intermediate results are hidden until commit. |
| D – Durability | Once committed, the transaction changes are permanent—even in case of a crash. |
------------------------------------------------------------------------

# ACID Summary

  Property      Meaning
  ------------- -----------------------------------------
  Atomicity     All operations succeed or all fail
  Consistency   Database remains valid
  Isolation     Concurrent transactions don't interfere
  Durability    Committed data is permanent

------------------------------------------------------------------------

1.  What is a transaction?
2.  Difference between COMMIT and ROLLBACK?
3.  Explain ACID properties.
4.  Why is Atomicity important?
5.  What happens if the server crashes after COMMIT?
6.  What problems does Isolation solve?

------------------------------------------------------------------------

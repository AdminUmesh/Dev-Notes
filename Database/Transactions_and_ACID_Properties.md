# Transactions & ACID Properties

## What is a Transaction?

A transaction is a group of SQL statements executed as a single unit of
work. It either succeeds completely (**COMMIT**) or fails completely
(**ROLLBACK**).

A transaction either:

-   **Commits** (all changes are saved permanently)
-   **Rolls back** (all changes are undone)

------------------------------------------------------------------------

# Why Do We Need Transactions?

-   Maintain data consistency
-   Prevent partial updates
-   Handle failures safely

------------------------------------------------------------------------

# Example: Bank Transfer

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

# SAVE TRANSACTION (Savepoint)

A **Savepoint** creates a checkpoint inside a transaction. You can roll
back to the savepoint instead of rolling back the entire transaction.

``` sql
BEGIN TRANSACTION;

UPDATE Accounts
SET Balance = Balance - 1000
WHERE AccountId = 1;

SAVE TRANSACTION TransferStarted;

UPDATE Accounts
SET Balance = Balance + 1000
WHERE AccountId = 2;

-- Something went wrong
ROLLBACK TRANSACTION TransferStarted;

COMMIT;
```

> **Note:** `ROLLBACK TRANSACTION` without a savepoint name rolls back
> the entire transaction.

------------------------------------------------------------------------

# @@ROWCOUNT

`@@ROWCOUNT` returns the number of rows affected by the last SQL
statement.

### Example

``` sql
UPDATE Accounts
SET Balance = Balance - 1000
WHERE AccountId = 1;

SELECT @@ROWCOUNT;
```

Output:

``` text
1
```

### Practical Example

``` sql
BEGIN TRANSACTION;

UPDATE Accounts
SET Balance = Balance - 1000
WHERE AccountId = 1;

IF @@ROWCOUNT = 0
BEGIN
    ROLLBACK;
    PRINT 'Source account not found.';
    RETURN;
END

UPDATE Accounts
SET Balance = Balance + 1000
WHERE AccountId = 2;

IF @@ROWCOUNT = 0
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

ACID ensures every transaction is reliable.

## A - Atomicity

**All or Nothing**

Either every operation succeeds or none do.

**Example:** Money is deducted and credited together. If one fails,
everything is rolled back.

------------------------------------------------------------------------

## C - Consistency

A transaction moves the database from one valid state to another.

Rules, constraints, and relationships remain valid.

------------------------------------------------------------------------

## I - Isolation

Multiple transactions should not interfere with each other.

Each transaction behaves as if it is running alone.

------------------------------------------------------------------------

## D - Durability

Once a transaction is committed, the data is permanently stored---even
if the server crashes.

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

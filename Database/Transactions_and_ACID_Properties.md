# Transactions & ACID Properties (SQL Interview Notes)

## What is a Transaction?

A **Transaction** is a sequence of one or more SQL operations that are
treated as a **single unit of work**.

A transaction either: - **Commits** (all changes are saved), or -
**Rolls back** (all changes are undone).

> **Interview Definition:** A transaction is a logical unit of work that
> executes one or more database operations while ensuring data
> consistency.

------------------------------------------------------------------------

# Why Do We Need Transactions?

-   Maintain data consistency
-   Prevent partial updates
-   Handle failures safely
-   Support concurrent users

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

No money is lost.

------------------------------------------------------------------------

# Transaction Commands

## BEGIN TRANSACTION

Starts a transaction.

``` sql
BEGIN TRANSACTION;
```

## COMMIT

Saves all changes permanently.

``` sql
COMMIT;
```

## ROLLBACK

Undoes all changes since the transaction started.

``` sql
ROLLBACK;
```

------------------------------------------------------------------------

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

# Real-Life Example

ATM Withdrawal

1.  Check balance
2.  Deduct amount
3.  Dispense cash
4.  Update account

If cash cannot be dispensed, the deduction is rolled back.

------------------------------------------------------------------------

# Common Interview Questions

1.  What is a transaction?
2.  Difference between COMMIT and ROLLBACK?
3.  Explain ACID properties.
4.  Why is Atomicity important?
5.  What happens if the server crashes after COMMIT?
6.  What problems does Isolation solve?

------------------------------------------------------------------------

# Interview Answer (1 Minute)

> A transaction is a logical unit of work that groups multiple database
> operations into a single execution. It ensures that either all
> operations succeed or none are applied. Transactions follow the ACID
> properties: Atomicity (all or nothing), Consistency (database remains
> valid), Isolation (concurrent transactions don't interfere), and
> Durability (committed data remains permanent even after failures). SQL
> provides `BEGIN TRANSACTION`, `COMMIT`, and `ROLLBACK` to manage
> transactions.

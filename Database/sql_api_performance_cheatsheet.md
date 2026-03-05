# SQL Backend Performance Cheat Sheet

## 1. Query Performance Tips

### Use Indexes on Filter and Join Columns

Indexes help databases locate rows faster.

Example:

``` sql
CREATE INDEX idx_users_email ON Users(email);
```

Use indexes on: - WHERE columns - JOIN columns - ORDER BY columns -
GROUP BY columns

------------------------------------------------------------------------

### Avoid SELECT \*

Only select columns you actually need.

Bad:

``` sql
SELECT * FROM Orders;
```

Good:

``` sql
SELECT OrderId, CustomerId, Amount FROM Orders;
```

Benefits: - Less network data - Faster query execution - Lower memory
usage

------------------------------------------------------------------------

### Use WHERE Filters Early

Filtering reduces the dataset early.

Example:

``` sql
SELECT *
FROM Orders
WHERE OrderDate >= '2024-01-01';
```

------------------------------------------------------------------------

### Avoid Functions on Indexed Columns

Bad:

``` sql
SELECT *
FROM Users
WHERE YEAR(CreatedDate) = 2024;
```

Good:

``` sql
SELECT *
FROM Users
WHERE CreatedDate >= '2024-01-01'
AND CreatedDate < '2025-01-01';
```

------------------------------------------------------------------------

### Use LIMIT for Large Queries

``` sql
SELECT *
FROM Orders
ORDER BY CreatedDate DESC
LIMIT 50;
```

Prevents loading millions of rows.

------------------------------------------------------------------------

### Use EXPLAIN to Analyze Query Plan

``` sql
EXPLAIN SELECT *
FROM Orders
WHERE CustomerId = 10;
```

Helps identify: - Table scans - Index usage - Query cost

------------------------------------------------------------------------

# 2. Common SQL Mistakes in APIs

### N+1 Query Problem

Bad Example:

``` pseudo
Get users
For each user:
    Query orders
```

Results: - 1 query for users - N queries for orders

Total = N + 1 queries

Better Solution: Use JOIN or batch query.

``` sql
SELECT *
FROM Users u
LEFT JOIN Orders o ON u.Id = o.UserId;
```

------------------------------------------------------------------------

### Returning Too Much Data

Bad:

``` sql
SELECT *
FROM Orders;
```

Good:

``` sql
SELECT OrderId, Amount
FROM Orders
LIMIT 50;
```

------------------------------------------------------------------------

### No Pagination in API

Bad API behavior: Returning 50,000 rows in one response.

Always use pagination.

------------------------------------------------------------------------

### Missing Index on Search Columns

Bad:

``` sql
SELECT *
FROM Users
WHERE email = 'abc@email.com';
```

Without index → full table scan.

Fix:

``` sql
CREATE INDEX idx_email ON Users(email);
```

------------------------------------------------------------------------

### Repeated Expensive Queries

Instead of running the same query repeatedly: - Use caching - Use
materialized results - Use Redis if needed

------------------------------------------------------------------------

# 3. Pagination Patterns

Pagination prevents APIs from returning huge datasets.

Two common approaches:

## OFFSET Pagination

### Example

``` sql
SELECT *
FROM Orders
ORDER BY CreatedDate DESC
LIMIT 10 OFFSET 20;
```

Meaning: Skip first 20 rows and return next 10.

### Pros

-   Easy to implement

### Cons

-   Slower for very large tables
-   Database still scans skipped rows

------------------------------------------------------------------------

## Keyset Pagination (Seek Method)

More efficient pagination using indexed columns.

### Example

``` sql
SELECT *
FROM Orders
WHERE Id > 100
ORDER BY Id
LIMIT 10;
```

Next page uses last received ID.

### Pros

-   Very fast
-   Uses index efficiently
-   Best for large tables

### Cons

-   Cannot jump to arbitrary page numbers

------------------------------------------------------------------------

# 4. OFFSET vs Keyset Comparison

  Feature              OFFSET Pagination         Keyset Pagination
  -------------------- ------------------------- -------------------
  Performance          Slower for large tables   Very fast
  Random Page Access   Yes                       No
  Index Usage          Limited                   Excellent
  Best For             Small datasets            Large datasets

------------------------------------------------------------------------

# 5. Backend Developer Best Practices

Always: - Use indexes on filters - Limit returned rows - Avoid N+1
queries - Use pagination - Analyze queries using EXPLAIN

For large APIs: - Prefer keyset pagination - Cache frequently used
data - Avoid heavy joins when unnecessary

------------------------------------------------------------------------

# Quick Summary

  Topic               Key Idea
  ------------------- ---------------------------------------
  Query Performance   Use indexes and avoid full scans
  API SQL Mistakes    Avoid N+1 and returning huge datasets
  Pagination          Use OFFSET for simple cases
  Large Systems       Prefer keyset pagination

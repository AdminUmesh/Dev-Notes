# Temporary Tables in SQL Server

In SQL Server, temporary tables are used to store data temporarily for the duration of a session or query.
`These tables are often used when you need to perform intermediate operations or complex calculations and you don't want to store the results permanently in the database.`

# Types of Temporary Tables
## Local Temporary Tables:

These tables are created with a single # symbol before the table name.

**Scope:** Local temporary tables are only visible to the session (or connection) that created them. `They are automatically dropped when the session or connection is closed.`

```sql
CREATE TABLE #TempTable (ID INT, Name VARCHAR(50));
```

## Global Temporary Tables:

These tables are created with a double ## symbol before the table name.

**Scope:** Global temporary tables are visible to all sessions. `They are dropped when the session that created them is closed and there are no other active connections that are using the table.`

```sql
CREATE TABLE ##GlobalTempTable (ID INT, Name VARCHAR(50));
```

## Uses of Temporary Tables

- **Intermediate Data Storage:** Temporary tables are useful when you need to store intermediate results of a query or complex operations (e.g., calculations, aggregations) that are not required for permanent storage.

- **Performance Optimization:** They can improve performance by breaking a complex query into smaller parts, storing intermediate results, and referencing the temporary table later.

- **Data Transformation:** When performing transformations or cleansing of data, temporary tables can help store the transformed data before inserting it into permanent tables.

# How to Create, Execute, Alter & Drop a Temp Table

## Creating Temporary Tables

**Local Temporary Table:**
```sql
CREATE TABLE #TempTable (
    ID INT,
    Name VARCHAR(50)
);
```

**Global Temporary Table:**
```sql
CREATE TABLE ##GlobalTempTable (
    ID INT,
    Name VARCHAR(50)
);
```

## Inserting Data into Temporary Tables
```sql
INSERT INTO #TempTable (ID, Name)
VALUES (1, 'John'), (2, 'Jane');
```

## Executing Queries on Temporary Tables
```sql
SELECT * FROM #TempTable;
```

## Altering Temporary Tables
```sql
-- Add a new column

ALTER TABLE #TempTable ADD Age INT;

-- Modify an existing column (e.g., change data type)
ALTER TABLE #TempTable ALTER COLUMN Name VARCHAR(100);
```

## Dropping Temporary Tables
```sql
DROP TABLE #TempTable;
-- You don’t need to drop a local temporary table explicitly; SQL Server automatically drops it when the session ends.

-- Global temporary tables will be dropped when there are no active connections referring to them.
```

# How can i see my all Temp table in MSMS

### Using Object Explorer panel
You can check Under `database -> System Database -> Tempdb -> Temporary tables`

### Using Query
```sql
SELECT * 
FROM tempdb.sys.tables
WHERE name LIKE '#TempTable%';
```
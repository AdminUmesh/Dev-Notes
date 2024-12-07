# What are Indexes in MS SQL?
An index is a database object used to improve the performance of queries. Indexes allow the SQL Server to quickly locate and access the data in a table without having to scan the entire table.
`Indexes are mainly created on columns that are frequently used in WHERE, JOIN, ORDER BY, or GROUP BY clauses.`

## Purpose of Indexes:
**Speeding up queries:** Indexes can significantly reduce the time it takes to retrieve data.

**Unique data enforcement:** Unique indexes ensure that no two rows have the same values in the indexed columns.

**Improve JOIN performance:** Indexes can enhance the performance of join operations.

**Optimizing sorting and filtering:** Indexes speed up sorting (ORDER BY) and filtering (WHERE) operations.

# Types of Indexes in MS SQL

## Clustered Index:

The clustered index determines the physical order of data in the table. A table can have only one clustered index.
- When you create a table with a primary key, SQL Server automatically creates a corresponding clustered index that includes primary key columns.

- When a clustered index is created, the data rows are rearranged to match the order of the indexed column.

**When a table does not have a primary key, which is very rare, you can use the CREATE CLUSTERED INDEX**
```sql
-- create a clustered index manually.
CREATE CLUSTERED INDEX idx_clustered ON Employee(EmployeeID);
```
https://www.sqlservertutorial.net/sql-server-indexes/sql-server-clustered-indexes/

## Non-Clustered Index: 
A non-clustered index is a separate structure from the data table. It contains pointers to the data rows rather than rearranging the data itself.
`A table can have multiple non-clustered indexes.` The data is not rearranged, and the index stores pointers to the actual data.

```sql
-- for a single column
CREATE [NONCLUSTERED] INDEX index_name
ON table_name(column_list);

-- for multiple column
CREATE INDEX ix_customers_name 
ON sales.customers(last_name, first_name);
```
https://www.sqlservertutorial.net/sql-server-indexes/sql-server-create-index/

## RENAME Index
**Renaming an index using the system stored procedure sp_rename**

renames the index ix_customers_city of the sales.customers table to ix_cust_city:
```sql
EXEC sp_rename 
        N'sales.customers.ix_customers_city',
        N'ix_cust_city' ,
        N'INDEX';
```
**using the SQL Server Management Studio (SSMS)**
```sql
-- under database -> table -> indexes -> right click on index and Rename
```

## UNIQUE Index
A unique index ensures the index key columns do not contain any duplicate values.

A unique index may consist of one or many columns. If a unique index has one column, the values in this column will be unique. In case the unique index has multiple columns, the combination of values in these columns is unique.

`Any attempt to insert or update data into the unique index key columns that cause the duplicate will result in an error.`

`A unique index can be clustered or non-clustered.`

```sql
-- Syntax to creating a unique index
CREATE UNIQUE INDEX index_name
ON table_name(column_list);
```

## DROP Index
The DROP INDEX statement removes one or more indexes from the current database.

```sql
-- single index droped
DROP INDEX [IF EXISTS] index_name
ON table_name;

--multiple index droped
DROP INDEX [IF EXISTS] 
    index_name1 ON table_name1,
    index_name2 ON table_name2,
    ...;
```

## How to Disable Index
## How to Enable Index
## What is Filter Index

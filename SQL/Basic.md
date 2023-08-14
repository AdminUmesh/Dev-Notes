 # <p style="text-align: center;">SQL Basic</p>
SQL stands for Structured Query Language. It is used to access and manipulate databases

**What Can SQL do?**
SQL can execute queries against a database, retrieve data, insert records, update records, delete records from a database and many more.

**SELECT** -extracts data from a database
```sql
SELECT column1, column2, ... 
FROM table_name;

SELECT * FROM table_name;
```

**SELECT DISTINCT** statement is extract only distinct (different) values.
Inside a table, a column often contains many duplicate values; and sometimes you only want to list the differet (distinct) values

```sql
- SELECT DISTINCT column1, column2, ...  
FROM table_name;
```
**WHERE** - The WHERE clause is used to filter records.
It is used to extract only those records that fulfill a specified condition.

**Note:** The WHERE clause is not only used in SELECT statements, it is also used in UPDATE, DELETE, etc.!

```sql
SELECT column1, column2, ...
FROM table_name
WHERE condition;
```

**SQL AND, OR and NOT Operators**
- The WHERE clause can be combined with AND, OR, and NOT operators.

- The AND and OR operators are used to filter records based on more than one condition:

- The AND operator displays a record if all the conditions separated by AND are TRUE.
- The OR operator displays a record if any of the conditions separated by OR is TRUE.
- The NOT operator displays a record if the condition(s) is NOT TRUE.

```sql
SELECT column1, column2, ...
FROM table_name
WHERE condition1 AND/OR/NOT condition2 AND/OR/NOT condition3 ...;
```

**ORDER BY**
The ORDER BY keyword is used to sort the result-set in ascending or descending order.

```sql
SELECT column1, column2, ...
FROM table_name
ORDER BY column1, column2, ... ASC|DESC;
```

**INSERT INTO**
INSERT INTO statement is used to insert new records in a table.

```sql
INSERT INTO table_name (column1, column2, column3, ...)
VALUES (value1, value2, value3, ...);
```

**What is a NULL Value?**
A field with a NULL value is a field with no value.

- If a field in a table is optional, it is possible to insert a new record or update a record without adding a value to this field. Then, the field will be saved with a NULL value.

**How to Test for NULL Values?**
It is not possible to test for NULL values with comparison operators, such as =, <, or <>. We will have to use the IS NULL and IS NOT NULL operators instead.

```sql
SELECT column_names
FROM table_name
WHERE column_name IS NULL/ IS NOT NULL;
```

**UPDATE** - updates data in a database
The UPDATE statement is used to modify the existing records in a table.

```sql
UPDATE table_name
SET column1 = value1, column2 = value2, ...
WHERE condition;
```

**DELETE** - deletes data from a database
The DELETE statement is used to delete existing records in a table.

```sql
DELETE FROM table_name WHERE condition;
```

**LIMIT**
The LIMIT clause is used to specify the number of records to return.

```sql
SELECT column_name(s)
FROM table_name
WHERE condition
LIMIT number;
```

**MIN() and MAX()**
- The MIN() function returns the smallest value of the selected column.

- The MAX() function returns the largest value of the selected column.

```sql
MIN() Syntax
SELECT MIN/MAX(column_name)
FROM table_name
WHERE condition;
```

**COUNT(), AVG() and SUM()**
- The COUNT() function returns the number of rows that matches a specified criterion.
- The AVG() function returns the average value of a numeric column. 
- The SUM() function returns the total sum of a numeric column.

```sql
SELECT COUNT/AVG/SUM(column_name)
FROM table_name
WHERE condition;
```

**LIKE Operator**
The LIKE operator is used in a WHERE clause to search for a specified pattern in a column.

- The percent sign (%) represents zero, one, or multiple characters
- The underscore sign (_) represents one, single character

```sql
SELECT column1, column2, ...
FROM table_name
WHERE columnN LIKE pattern;
```

**Here are some examples showing different LIKE operators with '%' and '_' wildcards:**

|      LIKE Operator  	     |                      Description                        |
|----------------------------| --------------------------------------------------------|
| `WHERE CustomerName LIKE 'a%'`	| `Finds any values that start with "a"` |
| `WHERE CustomerName LIKE '%a'`	|  `Finds any values that end with "a"` |
| `WHERE CustomerName LIKE '%or%'`	|  `Finds any values that have "or" in any position` |
| `WHERE CustomerName LIKE '_r%'`	|  `Finds any values that have "r" in the second position` |
| `WHERE CustomerName LIKE 'a_%'`	|  `Finds any values that start with "a" and are at least 2 characters in length` |
| `WHERE CustomerName LIKE 'a__%'`	|  `Finds any values that start with "a" and are at least 3 characters in length` |
| `WHERE ContactName LIKE 'a%o'`	|  `Finds any values that start with "a" and ends with "o"` |

**Wildcard Characters**
- A wildcard character is used to substitute one or more characters in a string.
- https://www.w3schools.com/sql/sql_wildcards.asp

**IN Operator**
- The IN operator allows you to specify multiple values in a WHERE clause.

- The IN operator is a shorthand for multiple OR conditions.

```sql
SELECT column_name(s)
FROM table_name
WHERE column_name IN (value1, value2, ...);
```

**BETWEEN Operator**
- The BETWEEN operator selects values within a given range. The values can be numbers, text, or dates.

- The BETWEEN operator is inclusive: begin and end values are included.

```sql
SELECT column_name(s)
FROM table_name
WHERE column_name BETWEEN value1 AND value2;

SELECT * FROM Products
WHERE Price BETWEEN 10 AND 20;
```

**NOT BETWEEN**
```sql
SELECT * FROM Products
WHERE Price NOT BETWEEN 10 AND 20;
```

**Aliases**
- Aliases are used to give a table, or a column in a table, a temporary name. Its often used to make column names more readable.

- An alias only exists for the duration of that query and created with the AS keyword.

```sql
SELECT column_name AS alias_name
FROM table_name;

SELECT column_name(s)
FROM table_name AS alias_name;
```

**SQL JOIN**
A JOIN clause is used to combine rows from two or more tables, based on a related column between them.

**Inner Join**
- The INNER JOIN keyword selects records that have matching values in both tables.

-https://www.w3schools.com/sql/sql_join_inner.asp

**Left Join**
- The LEFT JOIN keyword returns all records from the left table (table1), and the matching records from the right table (table2). The result is 0 records from the right side, if there is no match.

- https://www.w3schools.com/sql/sql_join_left.asp

**Right Join**
- The RIGHT JOIN keyword returns all records from the right table (table2), and the matching records from the left table (table1). The result is 0 records from the left side, if there is no match.

- https://www.w3schools.com/sql/sql_join_right.asp

**Full Join**
- The FULL OUTER JOIN keyword returns all records when there is a match in left (table1) or right (table2) table records.

- Tip: FULL OUTER JOIN and FULL JOIN are the same.

- https://www.w3schools.com/sql/sql_join_full.asp

**Self Join**
- A self join is a regular join, but the table is joined with itself.

- https://www.w3schools.com/sql/sql_join_self.asp

**UNION**
The UNION operator is used to combine the result-set of two or more SELECT statements.

Every SELECT statement within UNION must have the same number of columns
The columns must also have similar data types
The columns in every SELECT statement must also be in the same order

- https://www.w3schools.com/sql/sql_union.asp

**GROUP BY**
- The GROUP BY statement groups rows that have the same values into summary rows, like "find the number of customers in each country".

- The GROUP BY statement is often used with aggregate functions (COUNT(), MAX(), MIN(), SUM(), AVG()) to group the result-set by one or more columns

```sql
SELECT column_name(s)
FROM table_name
WHERE condition
GROUP BY column_name(s)
ORDER BY column_name(s);

SELECT COUNT(CustomerID), Country
FROM Customers
GROUP BY Country;
```

**HAVING Clause**
- The HAVING clause was added to SQL because the WHERE keyword cannot be used with aggregate functions.

```sql
SELECT column_name(s)
FROM table_name
WHERE condition
GROUP BY column_name(s)
HAVING condition
ORDER BY column_name(s);

SELECT COUNT(CustomerID), Country
FROM Customers
GROUP BY Country
HAVING COUNT(CustomerID) > 5;
```

**EXISTS Operator**
- The EXISTS operator is used to test for the existence of any record in a subquery.

- The EXISTS operator returns TRUE if the subquery returns one or more records.

```sql
SELECT column_name(s)
FROM table_name
WHERE EXISTS
(SELECT column_name FROM table_name WHERE condition);

SELECT SupplierName
FROM Suppliers
WHERE EXISTS (SELECT ProductName FROM Products WHERE Products.SupplierID = Suppliers.supplierID AND Price < 20);
```

**REference W3 School**
- SQL Exists
- SQL Any, All
- SQL Select Into
- SQL Insert Into Select
- SQL Case
- SQL Null Functions
- SQL Stored Procedures
- SQL Comments
- SQL Operators

**INSERT INTO** - inserts new data into a database
**CREATE DATABASE** - creates a new database
**ALTER DATABASE** - modifies a database
**CREATE TABLE** - creates a new table
**ALTER TABLE** - modifies a table
**DROP TABLE** - deletes a table
**CREATE INDEX** - creates an index (search key)
**DROP INDEX** - deletes an index
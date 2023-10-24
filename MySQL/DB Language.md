# <p style="text-align: center;">Database Language</p>
Database language can be used to read, store and update the data in database.

# 1. DDL (Data Difinition language)
The DDL Commands in Structured Query Language are used to create and modify the schema of the database and its objects.

## 1.1 CREATE Command
CREATE is a DDL command used to create databases, tables, triggers and other database objects.

**Create Database**
```sql
CREATE DATABASE databasename;
```

**Create Table**
```sql
CREATE TABLE table_name (
    column1 datatype,
    column2 datatype,
    column3 datatype,
   ....
);
```
**Create Table Using Another Table**
```sql
CREATE TABLE new_table_name AS
    SELECT column1, column2,...
    FROM existing_table_name
    WHERE ....;
```

**Create View**
In SQL, a view is a virtual table based on the result-set of an SQL statement.

A view contains rows and columns, just like a real table. The fields in a view are fields from one or more real tables in the database.

`**Note:` A view always shows up-to-date data! The database engine recreates the view, every time a user queries it.

```sql
CREATE VIEW view_name AS
SELECT column1, column2, ...
FROM table_name
WHERE condition;
```

`CREATE OR REPLACE VIEW`
A view can be updated with the CREATE OR REPLACE VIEW statement.

```sql
CREATE OR REPLACE VIEW view_name AS
SELECT column1, column2, ...
FROM table_name
WHERE condition;
```
**Create Index**
Indexes are used to retrieve data from the database more quickly than otherwise. The users cannot see the indexes, they are just used to speed up searches/queries.

- Automatically created indexes for PRIMARY KEY ans UNIQUE columns.

- Avoid column that contain too much null value.

- Small table do not require index.

```sql
CREATE INDEX index_name on table_name(column_name);
or
CREATE INDEX index_name on table_name(column1, column2...);
```

`Find how many index created on any table`
```sql
SHOW INDEX FROM table_name;
// its show how many index is created on any spacific table.
```

## 1.2 DROP Command
DROP is a DDL command used to delete whole structure of a table or database. We can easily remove the entire table, view, or index from the database using this DDL command.

**Drop Database**
```sql
DROP DATABASE databasename;
```

**Drop Table**
```sql
DROP TABLE table_name;
```

**Drop Index**
```sql
DROP INDEX index_name ON table_name;
```

**Drop View**
```sql
DROP VIEW view_name;
```

## 1.3 ALTER Command
ALTER is a DDL command which changes or modifies the existing structure of the table or database.

We can also add and drop constraints of the table using the ALTER command.

**Add Column in existing Table**
```sql
ALTER TABLE table_name
ADD column_name datatype;
```

**Drop Column from table**
```sql
ALTER TABLE table_name
DROP COLUMN column_name;
```

**Modify column**
To change the data type of a column in a table

```sql
ALTER TABLE table_name
MODIFY COLUMN column_name datatype;
```

## 1.4 TRUNCATE Command
TRUNCATE is a DDL command which deletes all row like delete, but it has no option to delete specific rows and not rollback function available.
`This is fast because it does'nt create log file`

```sql
TRUNCATE TABLE table_name;
```
If a Column is Auto Increment it start counting from 0 after TRUNCATE. `But in delete it start from last count`

## 1.5 RENAME Command

RENAME is a DDL command which is used to change the name of the database table.

**Rename a Database**
You can't Rename Directly a database in MySql. `But you can copy it in a new Database.`

**How to Copy data from one DB to Another?**
`Step-1`
Create a new Database.
`Step-2`
Backup Previous Database in .sql formate
```
// Open MySQL->Bin file in CMD. and run bellow command.

mysqldump -u user_name -p old_DB_name > location/file_name.sql
```
`Step-3`
Import your Backup file in new DataBase
```
mysql -u user_name -p new_DB_name < location/file_name.sql
```

**Rename a Table**
```sql
RENAME TABLE old_table_name TO new_name;
or
RENAME TABLE old_table1 TO new_table1,
   old_table2 TO new_table2,
   old_table3 TO new_table3;
```

**Rename a Column**
```sql
ALTER TABLE t11
CHANGE COLUMN t1 t2 datatype;
```

# 2. DCL (Data Control Language)
- DCL is used to control user access in a database.
- Using DCL command, it allows or restricts the user from accessing data in database schema.

**What is Privilleges?**
Privilage is a permition given by Database Administration. By using privilage we can create/delete own users and give/revoke some permition to him.

**Use MySQL in CMD**
1. Open MySQL->Bin file in CMD.
2. login user.
```
mysql -u username -p
```
**Show current login user**
```
Select user();
```
**Create new User**
```
CREATE USER 'your_username' IDENTIFIED by 'your_password';
```
or
```
CREATE USER 'your_username'@'localhost' IDENTIFIED by 'your_password';
```
**Check total user list**
Userlist is store in Mysql.user table

**Drop a User**
```
DROP USER 'your_username';
```
or
```
DROP USER 'your_username'@'localhost';
```

## 2.1 GRANT
GRANT command gives user's access privileges to the database.
This command allows specified users to perform specific tasks.

**SELECT:** Allows users to retrieve (read) data from a database table.
```
GRANT SELECT ON database_name.table_name TO 'username'@'hostname';
// Specific table of a DB
```

```
GRANT SELECT ON database_name.* TO 'user_name'@'localhost';
// All table of a DB
```

**INSERT:** Grants permission to add new rows of data to a database table.

**UPDATE:** Permits users to modify existing data within a table.

**DELETE:** Allows users to remove data from a table.

**CREATE:** Grants the ability to create new databases or tables.

**ALTER:** Permits the modification of existing tables, such as adding or deleting columns.

**DROP:** Grants permission to delete databases or tables.

**SHOW DATABASES:** Allows users to list available databases on the server.

**GRANT OPTION:** Allows a user to grant or revoke privileges to/from other users. This privilege is typically given to administrative users.

**ALL PRIVILEGES:** Grants all privileges for a specific database or table, including the ability to grant or revoke privileges for other users. It's a superuser privilege.

## 2.2 REVOKE
REVOKE command is used to cancel previously granted or denied permissions.
This command withdraw access privileges given with the GRANT command.
It takes back permissions from user.

**SELECT:** Denied users to retrieve (read) data from a database table.
```
REVOKE SELECT ON database_name.table_name TO 'username'@'hostname';
// Specific table of a DB
```

```
REVOKE SELECT ON database_name.* TO 'user_name'@'localhost';
// All table of a DB
```

# 3. DML(Data Manipulation Language)
The DML commands in Structured Query Language change the data present in the SQL database. We can easily access, store, modify, update and delete the existing records from the database using DML commands.

## 3.1 SELECT Command
The SELECT statement is used to select data from a database.

**Select All fields of a table**
```sql
SELECT * FROM table_name;
```

**Select Specific Table**
```sql
SELECT CustomerName, City, Country FROM Customers;
```

**Select Unique value from a table**

The SELECT DISTINCT statement is used to return only distinct (different) values.

```sql
SELECT DISTINCT column1, column2, ...
FROM table_name;
```

**Return total number of differen column**
```sql
SELECT COUNT(DISTINCT Column_name) FROM table_name;
```

## 3.2 INSERT Command
INSERT is data manipulation command in Structured Query Language, which allows users to insert data in database tables.

**Insert new records in Specific column**
```sql
INSERT INTO table_name (column1, column2, column3, ...)
VALUES (value1, value2, value3, ...);
```

**Insert new records in all column**
```sql
INSERT INTO table_name
VALUES (value1, value2, value3, ...);
```

## 3.3 UPDATE Command
UPDATE is a DML command in Structured Query Language, which allows users to update or modify the existing data in database tables.

**Update Specific row && Multiple Row**
if in where clouse put id=1(unique) it update only one record, if you put not unique(like countary='india') it update many matched row.

```sql
UPDATE table_name
SET column1 = value1, column2 = value2, ...
WHERE condition;
```

**Note:** The WHERE clause specifies which record(s) that should be updated. If you omit the WHERE clause, all records in the table will be updated!

```sql
UPDATE Customers
SET PostalCode = 00000;
// it update all PostalCode in costomers table
```

## 3.4 DELETE Command
DELETE is a DML command which allows SQL users to remove single or multiple existing records from the database tables.

**Delete all Record**
```sql
DELETE FROM table_name;
```
If a Column is Auto Increment, it start counting from last count after Delete/Delete all. `But in truncate it start from 0`

**Delete Specific Record**
```sql
DELETE FROM table_name WHERE condition;
```

# 4. TCL(Transaction Control Language)

TCL commands is help the user manage the transactions that take place in a database. COMMIT and ROLLBACK Command only apply on UPDATE, DELETE and INSERT commands.

## 4.1 COMMIT
COMMIT is Used to save data. If you run COMMIT command after some changes it can't be ROLLBACK.

```sql
COMMIT;
```

## 4.2 SAVEPOINT
SAVEPOINT is a special mark inside a transaction that is usefull to rollback by marks.

```sql
SAVEPOINT umesh;
```
## 4.3 ROLLBACK
ROLLBACK is used to undo all data whose not commited. (Only UPDATE, DELETE and INSERT)

```sql
ROLLBACK;           // revert all transaction whose not commited.

ROLLBACK to umesh;  // Only revert transaction upto umesh. 
```
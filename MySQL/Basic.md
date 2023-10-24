# MySQL Basic++💡

**Data vs Information**
Data is a collection of row facts Where Information is processed organized data.

## Database
A database is an organized collection of data, so that it can be easily accessed and managed.
`There are many databases available like MySQL, Sybase, Oracle, MongoDB, Informix, PostgreSQL, SQL Server, etc.`

**Type of database**

**1. Relational Database (RDBMS)**
Everything in a relational database is stored in the form of relations. The RDBMS database uses tables to store data. A table is a collection of related data entries and contains rows and columns to store data.

**Ex-** MS SQL Server, ORACLE, My-SQL and Microsoft Access are based on RDBMS.

**2. Cloud Database**
A cloud database is a database that typically runs on a cloud computing platform and access to the database is provided as-a-service. 

**Ex-** AWS (Amazon Web Services), Microsoft SQL server and Google cloud spanner

**3. NoSQL Database**
It provides a mechanism for storage and retrieval of data other than tabular relations. NoSQL database doesn't use tables for storing data. It is generally used to store big data and real-time web applications. `NoSQL Database is used to refer a non-SQL or non relational database.`

**Ex-** MongoDB, CouchDB, Cloudant

## DBMS
Database management System is software which is used to store and retrieve the database. For example, Oracle, MySQL, etc.; these are some popular DBMS tools.

## SQL
SQL stands for Structured Query Language. It is used to access and manipulate databases.

**What Can SQL do?**
SQL can execute queries against a database, retrieve data, insert records, update records, delete records from a database and many more.

## MySQL
MySQL is a relational database management system based on the Structured Query Language, which is the popular language for accessing and managing the records in the database. MySQL is open-source and free software under the GNU license. It is supported by Oracle Company. `cross-platform`

**INSERT INTO** - inserts new data into a database
**CREATE DATABASE** - creates a new database
**ALTER DATABASE** - modifies a database
**CREATE TABLE** - creates a new table
**ALTER TABLE** - modifies a table
**DROP TABLE** - deletes a table
**CREATE INDEX** - creates an index (search key)
**DROP INDEX** - deletes an index

## MySQL Connection Types

**1. Using Command-line**
- **Step-1 =>** Open MySQL->Bin file in CMD.

- **Step-2 =>** Run command `mysql -u your_username -p` In the syntax, the -u your_username indicates that we will connect to the MySQL server using the root user account and -p instructs MySQL to ask for a password.

- **Optional =>** command `SHOW DATABASES;` for check Database and `EXIT;` to disconnect the opened MySQL database server.

**2. Using MySQL Workbench**
- **1. XAMPP + Workbench =>** First start XAMPP Server after that open Workbench

- **2. MySQL Community Server + Workbench =>** its compulsary to first install MySQL Community Server after that install Workbench


**3. Using PHP Script**
Start XAMPP Server and execute below command.
```php
<html>  
   <head>  
      <title>MySQL Server Connection</title>  
   </head>  
   <body>  
      <?php  
         $servername = 'localhost:3306';  
         $username = 'javatpoint';  
         $dbpass = 'jtp123';  
         $conn = mysql_connect($servername, $username, $password);          
         if(! $conn ) {  
            die('Connection failed: ' . mysql_error());  
         }  
         echo 'Connection is successful';  
         mysql_close($conn);  
      ?>  
   </body>  
</html>    
```
**To check database =>** Start XAMPP Server and type `http://localhost/phpmyadmin` in browser URL.

## LIMIT
The LIMIT clause is used to specify the number of records to return.

```sql
SELECT column_name(s)
FROM table_name
WHERE condition
LIMIT number;
----------------
SELECT * FROM Customers
LIMIT 3; // Show only 3 records.
```

## Aliases
Aliases are used to give a table, or a column in a table, a temporary name. Its often used to make column names more readable.

- An alias only exists for the duration of that query and created with the AS keyword.

```sql
SELECT column_name AS alias_name
FROM table_name;

SELECT column_name(s)
FROM table_name AS alias_name;
```
## Having
The HAVING clause was added to SQL because the WHERE keyword cannot be used with aggregate functions.

```sql
SELECT COUNT(CustomerID), Country
FROM Customers
GROUP BY Country
HAVING COUNT(CustomerID) > 5;
```
##Change user password
```sql
mysql> USE mysql;  
   
mysql> UPDATE user SET password = PASSWORD('jtp12345') WHERE user = 'peter' AND host = 'localhost';  
   
mysql> FLUSH PRIVILEGES;  
```
## Replace
**The REPLACE()** function replaces all occurrences of a substring within a string, with a new substring.

**Note:** This function performs a case-sensitive replacement.

**Syntax**
```sql
REPLACE(string, from_string, new_string)
```
**Table name:- `city`**
|  CID  |   City_Name  | 
|-------| -------------|
| `1`	|  `Agra`      |
| `2`	|  `Delhi`     |
| `3`	|  `Bhopal`    |
```sql
select replace(city_name, 'a', 'i') as changed_city
from city;
```
**Result**
|changed_city  | 
| -------------|
|  `Agri`      |
|  `Delhi`     |
|  `Bhopil`    |

## Substring
Extract a substring from a string (start at position, extract how much characters):
```sql
SELECT SUBSTRING("SQL Tutorial", 5, 3)
// Output Tut
```

**Example** (Substring with remove)
```sql
select substring(replace(city_name, 'a', 'i'), 2, 4) as substring_replace
from city;
```
|substring_replace  | 
| -------------|
|  `gri`      |
|  `elhi`     |
|  `hopi`    |

## How many column can insert at a time?
The hard limit is 4096 columns per table, with a maximum row size of 65,535 bytes

## What is delimeter in MySQL
Delimiters are used when we need to define the stored procedures as well as to create triggers. `Default delimiter is semicolon.`

```sql
Delimiter//
select*from world//
```

## Describe, Show & USE 
**Describe**
You can get the information about the table structure using the DESCRIBE statement. This statement works as a shortcut for SHOW COLUMNS. You can also use DESC for this statement.
```sql
Describe table_name; // it show all structure 
```

**Show**
The MySQL SHOW commands permit showing off the structure, fields, indexes, and functions created or stored procedures of the MySQL server, which may be necessary to view for the users at a time.
```sql
SHOW COLUMN from tabel_name; // work in selected database
SHOW COLUMN from database_name.tabel_name; // show those table information who is not selected.
```
**Example**
SHOW DATABASES;
SHOW ERRORS;
SHOW TABLES;
SHOW COLUMNS FROM TableName;
SHOW [FULL] PROCESSLIST;
SHOW EVENTS;
SHOW TABLE STATUS;
SHOW PRIVILEGES;
SHOW WARNINGS;
SHOW CREATE VIEW;
SHOW VARIABLES;
SHOW ENGINE;
SHOW INDEX;
SHOW GRANTS;
SHOW STATUS;
SHOW PLUGINS;
SHOW CREATE FUNCTION;

**USE**
The USE statement of MySQL helps you to select/use a database. You can also change to another database with this statement. Once you set the current database it will be same until the end of the session unless you change the it.

**Example**
if i have two database use and sample we can easly switch it

```sql
mysql> use test;  // Use test database
mysql> CREATE TABLE SAMPLE(NAME VARCHAR(10));
Query OK, 0 rows affected (1.65 sec)
mysql> INSERT INTO SAMPLE VALUES ('Raj');
Query OK, 1 row affected (0.19 sec)

mysql> use sample  // Use sample database
Database changed
mysql> SELECT * FROM SAMPLE;
ERROR 1146 (42S02): Table 'sample.sample' doesn't exist
```
# Future Update💡
- Datatype in mySQL
- Regular Expration in Mysql (yahoo baba)
- injection in mysql
- schemas and normal form in mysql
- ENUM
- SET
- convert 
- extract
- information_Schema
- Date time in mysql (Know about Time Stamp also)
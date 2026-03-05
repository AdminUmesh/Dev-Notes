# MySQL Conditions💡

## AND, OR & NOT
The WHERE clause can be combined with AND, OR, and NOT operators.
`The AND and OR operators are used to filter records based on more than one condition:`

- The AND operator displays a record if all the conditions separated by AND are TRUE.
- The OR operator displays a record if any of the conditions separated by OR is TRUE.
- The NOT operator displays a record if the condition(s) is NOT TRUE.

```sql
SELECT column1, column2, ...
FROM table_name
WHERE condition1 AND/OR/NOT condition2 AND/OR/NOT condition3 ...;
```
## AND & OR 
```sql
SELECT *  
FROM students  
WHERE (course_name = 'Java' AND student_name = 'Aryan')  
OR (student_id < 2); 
```

## Boolean
A Boolean is the simplest data type that always returns two possible values, either true or false. `MySQL does not contain built-in Boolean or Bool data type. They provide a TINYINT data type instead of Boolean or Bool data types.`  MySQL considered value zero as false and non-zero value as true.

```sql
 CREATE TABLE student (  
    studentid INT PRIMARY KEY AUTO_INCREMENT,  
    name VARCHAR(40) NOT NULL,  
    age VARCHAR(3),  
    pass BOOLEAN  
);  
// Data Type of pass is tinyint(1)
```
**Example-1**
```sql
 INSERT INTO officers(name, pass, address) VALUES('Tejas',true, 'lucknow'), ('Umesh',false, 'lucknow'), ('niketa',false, 'agra');  
```

**Above query is look like**
`officers table`
|  ID  |   Name  |  pass  | Address  |
|------| ------- |--------| ---------| 
| `1`  |  `Tejas`| `1`    | `lucknow`|
| `2`  | `Umesh` |  `0`	  |`lucknow` |
| `3`  | `Niketa`| `0`    | `agra`   | 

**Example-2**
```sql
SELECT id, name, pass FROM officers WHERE pass IS FALSE;  
  
OR,  
  
SELECT id, name, pass FROM officers WHERE pass IS NOT TRUE;   
```
**Result**
|  ID  |   Name  |  pass  |
|------| ------- |--------| 
| `2`  | `Umesh` |  `0`	  |
| `3`  | `niketa`|  `0`	  | 


## LIKE Operator
The LIKE operator is used in a WHERE clause to search for a specified pattern in a column.

- The percent sign (%) represents zero, one, or multiple characters
- The underscore sign (_) represents one, single character

```sql
SELECT column1, column2, ...
FROM table_name
WHERE columnN LIKE pattern;
```

```sql
expression LIKE pattern [ ESCAPE 'escape_character' ]  
```
**escape_character:** It is optional. It allows you to test for literal instances of a wildcard character such as % or _. If you do not provide the escape_character, MySQL assumes that "\" is the escape_character.

**Example-1:** Using % (percent) Wildcard:
```sql
SELECT name  
FROM officers  
WHERE address LIKE 'Luck%';  
```
|   Name  |
| ------- |
| `Umesh` | 
| `Tejas` |

**Example-2:** Using _ (Underscore) Wildcard:
```sql
SELECT name  
FROM officers  
WHERE address LIKE 'Luc_now';   
```
|   Name  |
| ------- |
| `Umesh` | 
| `Tejas` |

**Example-2:** Using NOT Operator:
```sql
SSELECT name  
FROM officers  
WHERE address NOT LIKE 'Luck%';    
```
|   Name  |
| ------- |
| `Niketa`| 

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


## IN Operator
The MySQL IN condition is used to reduce the use of multiple OR conditions in a SELECT, INSERT, UPDATE and DELETE statement.

- The IN operator is a shorthand for multiple OR conditions.

```sql
SELECT column_name(s)
FROM table_name
WHERE column_name IN (value1, value2, ...);
```
**Execute the following query:**
```sql
SELECT *  
FROM officers  
WHERE name IN ('Umesh', 'Niketa');
--------------------------------
SELECT *  
FROM officers  
WHERE name = 'Umesh'   
OR officer_name = 'Niketa';  
```  
**Output**
|  ID  |   Name  |  Address  |
|------| ------- |-----------| 
| `1`  | `Umesh` | `lucknow` |
| `2`  | `Niketa`|  `agra`	 |

## ANY & ALL
The ANY keyword is a MySQL operator that returns the Boolean value TRUE if the comparison is TRUE for ANY of the subquery condition. `ALL SQL operator works related to ANY operator, but it returns true when all the subquery values are satisfied by the condition in MySQL.`

**Syntax**
```sql
operand comparison_operator ANY/ALL (subquery)  
// comparison_operator(=  >  <  >=  <=  <>  !=)
```
**Example**
```sql
SELECT colm1 FROM table1 WHERE colm1 > ANY/ALL (SELECT colm1 FROM table2);  
```
[Go on Youtube](https://youtu.be/JkfUvuKL2UM?si=ZqR8j59TuPoOU2eR)

## EXISTS
The EXISTS operator is used to test for the existence of any record in a subquery. `The EXISTS operator returns TRUE if the subquery returns one or more records.`

**Syntax**
```sql
SELECT col_names  
FROM tab_name  
WHERE [NOT] EXISTS (  
    SELECT col_names   
    FROM tab_name   
    WHERE condition  
);  
------------------------
SELECT SupplierName
FROM Suppliers
WHERE EXISTS (SELECT ProductName FROM Products WHERE Products.SupplierID = Suppliers.supplierID AND Price < 20);
```
[Go on Youtube](https://youtu.be/JkfUvuKL2UM?si=ZqR8j59TuPoOU2eR)

## NOT
The NOT condition is opposite of MySQL IN condition. It is used to negate a condition in a SELECT, INSERT, UPDATE or DELETE statement.

**Syntax:**
```sql
NOT condition 
```
**Example-1:**
```sql
SELECT *  
FROM officers  
WHERE name NOT IN ('Niketa','Tejas'); 
```
|  ID  |   Name  |  pass  | Address  |
|------| ------- |--------| ---------| 
| `2`  | `Umesh` |  `0`	  |`lucknow` |

**Example-2:**
```sql
SELECT *  
FROM officers  
WHERE name NOT LIKE 'l%'; 
```
|  ID  |   Name  |  pass  | Address  |
|------| ------- |--------| ---------| 
| `3`  | `Niketa`|  `0`	  |`agra`    |


## Not Equal
Not Equal is an inequality operator that used for returning a set of rows after comparing two expressions that are not equal.`The MySQL contains two types of Not Equal operator, which are (< >) and (! =).`

**Difference Between (< >) and (! =) Operator**
The Not Equal operators in MySQL works the same to perform an inequality test between two expressions. They always give the same result. `However, they contain one difference that “< >” follows the ISO standard whereas “!=” does not follow ISO standard.`

```sql
SELECT * FROM students WHERE city <> "England"; 
------------------------------------------------
SELECT * FROM students WHERE city != "England";  
```
**Example:** [Click here](https://www.javatpoint.com/mysql-not-equal)


## IS NULL & IS NOT NULL

**What is a NULL Value?**
A field with a NULL value is a field with no value.

- If a field in a table is optional, it is possible to insert a new record or update a record without adding a value to this field. Then, the field will be saved with a NULL value.

**How to Test a NULL Values?**
It is not possible to test for NULL values with comparison operators, such as =, <, or <>. We will have to use the IS NULL and IS NOT NULL operators instead.

```sql
SELECT column_names
FROM table_name
WHERE column_name IS NULL/ IS NOT NULL;
```

## BETWEEN & NOT BETWEEN
The BETWEEN operator selects values within a given range. The values can be numbers, text, or dates.

- The BETWEEN operator is inclusive: begin and end values are included.

```sql
SELECT column_name(s)
FROM table_name
WHERE column_name BETWEEN value1 AND value2;

SELECT * FROM Products
WHERE Price BETWEEN 10 AND 20;
```

**NOT BETWEEN**
It is just opposite of between operator
```sql
SELECT * FROM Products
WHERE Price NOT BETWEEN 10 AND 20;
```

# MySQL Trigger💡
A trigger is a set of SQL statements that called/invoked automatically when a data modification event is made against a table.

## Triggers are of two types according to the SQL standard:

**1. Row-Level Trigger:** It is a trigger, which is activated for each row by a triggering statement such as insert, update, or delete. For example, if a table has inserted, updated, or deleted multiple rows, the row trigger is fired automatically for each row affected by the insert, update, or delete statement.

**2. Statement-Level Trigger:** It is a trigger, which is fired once for each event that occurs on a table regardless of how many rows are inserted, updated, or deleted.
`MySQL doesn't support statement-level triggers.`

**Why we need/use triggers in MySQL?**
- Triggers help us to keep a log of records like maintaining audit trails in tables.
- Triggers reduce the client-side code that saves time and effort.
- Triggers are easy to maintain.

**Naming Conventions**
Trigger must have a unique name within a schema. Triggers in different schemas can have same name.

## Types of Triggers in MySQL?
**Before Insert:** It is activated before the insertion of data into the table.
**After Insert:** It is activated after the insertion of data into the table.
**Before Update:** It is activated before the update of data in the table.
**After Update:** It is activated after the update of the data in the table.
**Before Delete:** It is activated before the data is removed from the table.
**After Delete:** It is activated after the deletion of data from the table.
```sql
(BEFOR | AFTER) table_name (INSERT | UPDATE | DELETE) 

// Trigger Activation Time: BEFORE | AFTER
// Trigger Event: INSERT | UPDATE | DELETE
```

**CREATE TRIGGER**
```sql
CREATE TRIGGER trigger_name    
    (AFTER | BEFORE) (INSERT | UPDATE | DELETE)  
         ON table_name FOR EACH ROW    
         BEGIN    
        --variable declarations    
        --trigger code    
        END;     
```
**OLD.colum_name & NEW.colum_name**
We can use the column name with NEW and OLD modifiers as OLD.col_name and NEW.col_name. The OLD.column_name indicates the column of an existing row before the updation or deletion occurs. NEW.col_name indicates the column of a new row that will be inserted or an existing row after it is updated.

**For example** suppose we want to update the column name working_hours using the trigger. In the trigger body, we can access the column value before the update as OLD.working_hours and the new value NEW.working_hours.

`employee table`
|  ID  |   Name  |  pass  | working_hours  |
|------| ------- |--------| ---------------| 
| `2`  | `Umesh` |  `0`	  |     `8`        |

```sql
mysql> DELIMITER //  
mysql> Create Trigger before_insert_workinghours   
BEFORE INSERT ON employee FOR EACH ROW  
BEGIN  
IF NEW.working_hours < 0 THEN SET NEW.working_hours = 0;  
END IF;  
END //  
```
If Any want to insert negative value in working_hour table it automatic set 0 with trigger.
```sql
INSERT INTO employee VALUES    
('3', 'Bably', 'true', -13);  
```
In this output, we can see that on inserting the negative values into the working_hours column of the table will automatically fill the zero value by a trigger.

**More About trigger** [Click link-1](https://youtu.be/rIi1dvPdTHE?si=xObOh4FPpEloJ13_) or [Click link-2](https://youtu.be/rIi1dvPdTHE?si=5dd-6tP43n_eESBk)

## Show/List Triggers
The show or list trigger is much needed when we have many databases that contain various tables. Sometimes we have the same trigger names in many databases; this query plays an important role in that case.
```sql
SHOW TRIGGERS;
```
## Choose the specific database
```sql
USE database_name; 
```
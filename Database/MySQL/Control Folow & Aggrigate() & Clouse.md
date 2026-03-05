# MySQL Aggrigate Function💡
Aggregate function is used to perform calculations on multiple values and return the result in a single value like the average of all values, the sum of all values.

## Count() Function
count() function is used to returns the count of an expression. It allows us to count all rows or only some rows of the table that matches a specified condition. `It is a type of aggregate function whose return type is BIGINT. This function returns 0 if it does not find any matching rows.`

It returns the number of rows, including rows with NULL values in a group

**Syntax**
```sql
SELECT COUNT (aggregate_expression)    
FROM table_name    
[WHERE conditions];
```
**Example**
```sql
SELECT COUNT(emp_name) FROM employees; 
```
```sql
SELECT COUNT(*) FROM employees WHERE emp_age>32;    
```
```sql
SELECT COUNT(DISTINCT emp_age) FROM employees;   
```

## MIN() and MAX()
- The MIN() function returns the smallest value of the selected column.

- The MAX() function returns the largest value of the selected column.

```sql
MIN() Syntax
SELECT MIN/MAX(column_name)
FROM table_name
WHERE condition;
```

## COUNT(), AVG() and SUM()
- The COUNT() function returns the number of rows that matches a specified criterion.
- The AVG() function returns the average value of a numeric column. 
- The SUM() function returns the total sum of a numeric column.

```sql
SELECT COUNT/AVG/SUM(column_name)
FROM table_name
WHERE condition;
```

## GROUP_CONCAT()
It returns a concatenated string. `This function is used to concatenate string from multiple rows into a single string using various clauses.`

```sql
SELECT emp_id, emp_fname, dept_id,   
GROUP_CONCAT(designation) as "designation" FROM employee group by emp_id;
```
**Result**
|emp_id|emp_fname|dept_id|   designation  | 
|------| ------- |------| ----------------|
| `1`  |  `Aaa`  |  `1`	|  `Engineer, Developwer` |
| `2`  | `Bbb`   |  `5`	|  `Manaager, BDE` |
| `3`  | `Ccc`   |  `9`	|  `Teacher, Principal`|


## first()
first function is used to return the first value of the selected column.

**Syntax:**
```sql
SELECT column_name  
FROM table_name  
LIMIT 1; 
``` 

## last()
MySQL last function is used to return the last value of the selected column.

**Syntax:**
```sql
SELECT column_name  
FROM table_name  
ORDER BY column_name DESC  
LIMIT 1;  
``` 

# MySQL Clouses💡

**WHERE**
The WHERE clause is used to filter records.
It is used to extract only those records that fulfill a specified condition.

```sql
SELECT column1, column2, ...
FROM table_name
WHERE condition;
```

**DISTINCT**
MySQL DISTINCT clause is used to remove duplicate records from the table and fetch only the unique records. 

**FROM**
The MySQL FROM Clause is used to select some records from a table.

**ORDER BY**
The ORDER BY keyword is used to sort the result-set in ascending or descending order.

```sql
SELECT column1, column2, ...
FROM table_name
ORDER BY column1, column2, ... ASC|DESC;
```

**GROUP BY**
The GROUP BY statement groups rows that have the same values into summary rows, like "find the number of customers in each country".

- The GROUP BY statement is often used with aggregate functions (COUNT(), MAX(), MIN(), SUM(), AVG()) to group the result-set by one or more columns

```sql
SELECT column_name(s)
FROM table_name
WHERE condition
GROUP BY column_name(s)
ORDER BY column_name(s);
---------------------
SELECT COUNT(CustomerID), Country
FROM Customers
GROUP BY Country;
```

**HAVING Clause**
The HAVING clause was added to SQL because the WHERE keyword cannot be used with aggregate functions.

```sql
SELECT column_name(s)
FROM table_name
WHERE condition
GROUP BY column_name(s)
HAVING condition
ORDER BY column_name(s);
-----------------------
SELECT COUNT(CustomerID), Country
FROM Customers
GROUP BY Country
HAVING COUNT(CustomerID) > 5;
```

# MySQL Control Folow function💡
## if()
The IF function returns a value YES when the given condition evaluates to true and returns a NO value when the condition evaluates to false. `It returns values either in a string or numeric form depending upon the context in which this function is used.`

**Syntax**
```sql
IF ( expression 1, expression 2, expression 3)   
```

**Example-1**
```sql
SELECT IF(200>350,'YES','NO');
// Output No
```

**Example-2**
`student table`
|  ID  |   Name  |Gender|   age   | 
|------| ------- |------| --------|
| `1`  |  `Aaa`  |  `M`	|  `10`   |
| `2`  | `Bbb`   |  `M`	|  `22`  |

```sql
SELECT name,  
IF(age>20,"Mature","Immature")    
As Result  
FROM student;  
```

**Result**
|  name |   result  | 
|-------| ----------|
| `Aaa`	|`Immature` |
| `Bbb`	|`Mature`   |

## IFNULL()
The IFNULL function accepts two expressions, and if the first expression is not null, it returns the first arguments. If the first expression is null, `it returns the second argument. This function returns either string or numeric value, depending on the context where it is used.`

**Syntax**
```sql
IFNULL (Expression1, Expression2)    
```

**Example-1**
If the first expression is not NULL, it will return the first expression, which is 'Hello' value.
```sql
SELECT IFNULL("Hello", "javaTpoint");
// Output Hello
```

**Example-2**
If the first expression is not NULL, it will return the first expression. Otherwise, it will return the second expression, which is five (5).
```sql
SELECT IFNULL(NULL,5);  
// Output 5
```

**Example-3**
`student_contact table`
|  ID  |   Name  |   CellPhone  |  HomePhone   | 
|------| ------- |--------------| -------------|
| `1`  |  `Aaa`  | `9708306322` |  `Null`      |
| `2`  | `Bbb`   |  `Null`	    | `9525544734` |

```sql
SELECT   
    name, IFNULL(cellphone, homephone) phone  
FROM  
    student_contact;  
```

**Result**
|  name |   phone    | 
|-------| -----------|
| `Aaa`	|`9708306322`|
| `Bbb`	|`9525544734`|

## NULLIF()
The NULLIF function accepts two expressions, and if the first expression is equal to the second expression, it returns the NULL. Otherwise, it returns the first expression.

**Syntax**
```sql
NULLIF (Expression1, Expression2)     
```

**Example-1**
```sql
SELECT NULLIF("javaTpoint", "javaTpoint"); 
// Output NULL
```

**Example-2**
```sql
SELECT NULLIF("Hello", "404");  
// Output Hello
```

**Example-3**
`Employee table`
|  ID  |   Name  |   CellPhone  |  Qualification | 
|------| ------- |--------------| -------------|
| `1`  |  `Umesh`| `9708306322` |  `Btech`    |
| `2`  | `Tejas` |  `Null`	    | `Mtech`     |

```sql
SELECT name, qualification,   
NULLIF (qualification,"Btech") result   
FROM Employee;    
```

**Result**
|  name |Qualification| result |
|-------| -----------| -------|
|`Umesh`| `Btech`    | `NULL` |
|`Tejas`| `Mtech`    | `Mtech`|


## CASE()
The CASE expression validates various conditions and returns the result when the first condition is true. Once the condition is met, it stops traversing and gives the output. If it will not find any condition true, it executes the else block. When the else block is not found, it returns a NULL value.

**Syntax**
```sql
CASE value   
    WHEN [compare_value] THEN result   
    [WHEN [compare_value] THEN result ...]   
    [ELSE result]   
END       
```

**Example-1**
```sql
 SELECT CASE 1 WHEN 1 THEN 'one' WHEN 2 THEN 'two' ELSE 'more' END;  
// Output one
```

**Example-2**
```sql
 SELECT CASE BINARY 'B' WHEN 'a' THEN 1 WHEN 'b' THEN 2 END;  
// Output NULL
```

**Example-3**
`student table`
|  ID  |   Name  |   CellPhone  |  Class   | 
|------| ------- |--------------| ---------|
| `1`  |  `Umesh`| `9708306322` |  `CS`    |
| `2`  | `Tejas` |  `Null`	    | `EC`     |

```sql
SELECT id, name,  
 CASE class   
    WHEN 'CS' THEN 'Computer Science'   
    WHEN 'EC' THEN 'Electronics'   
    ELSE 'Electrical Engineering'   
END AS department from students;      
```

**Result**
|  id |   name  | department        |
|-----| --------| ------------------|
|`1`  | `Umesh` | `Computer Science`|
|`2`  | `Tejas` | `Electronics`     |
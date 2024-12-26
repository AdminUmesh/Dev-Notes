# MySql Joins💡
A JOIN clause is used to retrieve data from multiple tables.

**Table name:- `personal`**
|  ID  |   Name  |Gender|   City  | 
|------| ------- |------| --------|
| `1`  |  `Aaa`  |  `M`	|  `1`    |
| `2`  | `Bbb`   |  `M`	|  `100`  |
| `3`  | `Ccc`   |  `M`	|  `2`    |
| `4`  |  `Ddd`  |  `M`	|  `200`  |
| `5`  |  `Eee`  |  `F`	|  `3`    |
| `6`  | `Fff`   |  `F`	|  `300`  |
| `7`  | `Ggg`   |  `F`	|  `4`    |

**Table name:- `city`**
|  CID  |   City Name  | 
|-------| -------------|
| `1`	|  `Agra`      |
| `2`	|  `Delhi`     |
| `3`	|  `Bhopal`    |
| `4`	|  `Jaipur`    |
| `5`	|  `Noida`     |

## Inner Join
INNER JOIN keyword selects records that have matching values in both tables.

**Syntax**
```sql
SELECT column_name(s)
FROM table1
INNER JOIN table2
ON table1.column_name = table2.column_name;
```
**Example**
```sql
SELECT *
FROM personal
INNER JOIN city
ON personal.city = city.cid;
```
**Result**
|  ID  |   Name  |Gender|   City  |   CID |   City Name  |
|------| ------- |------| --------|-------| -------------|
| `1`  |  `Aaa`  |  `M`	|  `1`    | `1`   |  `Agra`      |
| `3`  | `Ccc`   |  `M`	|  `2`    | `2`	  |  `Delhi`     |
| `5`  |  `Eee`  |  `F`	|  `3`    | `3`   |  `Bhopal`    |
| `7`  | `Ggg`   |  `F`	|  `4`    | `4`	  |  `Jaipur`    |

## Left Join
- The LEFT JOIN keyword returns all records from the left table (table1), and the matching records from the right table (table2). The result is 0 records from the right side, if there is no match.

**Syntax**
```sql
SELECT column_name(s)
FROM table1
LEFT JOIN table2
ON table1.column_name = table2.column_name;
```
**Example**
```sql
SELECT *
FROM personal
LEFT JOIN city
ON personal.city = city.cid;
```
**Result**
|  ID  |   Name  |Gender|   City  |   CID |   City Name  |
|------| ------- |------| --------|-------| -------------|
| `1`  |  `Aaa`  |  `M`	|  `1`    | `1`   |  `Agra`      |
| `2`  | `Bbb`   |  `M`	|  `100`  |       |              |
| `3`  | `Ccc`   |  `M`	|  `2`    | `2`	  |  `Delhi`     |
| `4`  |  `Ddd`  |  `M`	|  `200`  |       |              |
| `5`  |  `Eee`  |  `F`	|  `3`    | `3`   |  `Bhopal`    |
| `6`  | `Fff`   |  `F`	|  `300`  |       |              |
| `7`  | `Ggg`   |  `F`	|  `4`    | `4`	  |  `Jaipur`    |

## Right Join
- The RIGHT JOIN keyword returns all records from the right table (table2), and the matching records from the left table (table1). The result is 0 records from the left side, if there is no match.

**Syntax**
```sql
SELECT column_name(s)
FROM table1
RIGHT JOIN table2
ON table1.column_name = table2.column_name;
```
**Example**
```sql
SELECT *
FROM personal
RIGHT JOIN city
ON personal.city = city.cid;
```
**Result**
|  ID  |   Name  |Gender|   City  |   CID |   City Name  |
|------| ------- |------| --------|-------| -------------|
| `1`  |  `Aaa`  |  `M`	|  `1`    | `1`   |  `Agra`      |
| `3`  | `Ccc`   |  `M`	|  `2`    | `2`   |  `Delhi`     |
| `5`  | `Eee`   |  `F`	|  `3`    | `3`	  |  `Bhopal`    |
|      |         |  	|         | `4`   |  `Jaipur`    |
|      |         |  	|         | `5`   |  `Noida`     |

## Cross Join
The CROSS JOIN keyword returns all records from both tables (table1 and table2).If it mathched or not.

**Syntax**
```sql
SELECT column_name(s)
FROM table1
CROSS JOIN table2;
```
**Example**
```sql
SELECT personal.id, city.[city name]
FROM personal
CROSS JOIN city;
```
**Result**
[Go on Youtube](https://youtu.be/M0VICJpq_jA?si=A2zyt3MpzSVTdCDo)

## Self Join
A self join is a regular join, but the table is joined with itself.

**Syntax**
```sql
SELECT column_name(s)
FROM table1 T1, table1 T2
WHERE condition;
```
**Example**
[Go on Youtube](https://youtu.be/6DQpvfdj6EE?si=sJwOPbXQ8YLmAWKg)

## UNION
The UNION operator is used to combine the result-set of two or more SELECT statements.

- Every SELECT statement within UNION must have the same number of columns
- The columns must also have similar data types
- The columns in every SELECT statement must also be in the same order

**Syntax**
```sql
SELECT city, name FROM table1
UNION
SELECT column_name(s) FROM table2;
```

**Example**
```sql
SELECT name, city FROM personal
UNION
SELECT [city name], cid FROM city;
```
**Result**
It's combine both table result and give a distinct value.
[Go on Youtube](https://youtu.be/ekA1tuSeHXc?si=M4PzcVH5UIJpFKyj)

## UNION ALL
Same as UNION but The UNION operator selects only distinct values by default. To allow duplicate values, use UNION ALL:
[Go on Youtube](https://youtu.be/ekA1tuSeHXc?si=M4PzcVH5UIJpFKyj)

## Joins more than two table

**Syntax for INNER JOIN**
```sql
SELECT column_name(s)
FROM table1
INNER JOIN table2
ON table1.column_name = table2.column_name
INNER JOIN table3
ON table1.column_name = table3.column_name
```

# MySQL Constraints💡

## 1. Unique Key
A unique key is a single field or combination of fields that ensure all values going to store into the column will be unique. `It means a column cannot stores duplicate values.`

- It is useful in preventing the two records from storing identical values into the column.
- It can contain null value into the table.

**Create only one unique key column into a table**
```sql
CREATE TABLE table_name(  
    col1 datatype,  
    col2 datatype UNIQUE,  
    ...  
);  
```
**Create more than one unique key column into a table**
```sql
CREATE TABLE table_name(  
  col1 col_definition,  
  col2 col_definition,  
  ...  
  [CONSTRAINT constraint_name]  
  UNIQUE(column_name(s))  
);  
```

**Show Unique column in table**
```sql
SHOW INDEX FROM table_name;
```

**Remove Unique from table**
```sql
ALTER TABLE table_name DROP INDEX column_name;  
```
## 2. Primary Key
Primary key is a single or combination of the field, which is used to identify each record in a table uniquely. `If the column contains primary key constraints, then it cannot be null or empty.`

- The primary key column value must be unique.
- Each table can contain only one primary key.
- The primary key column cannot be null or empty.

**Create a primary key in two ways:**
**1. Using CREATE TABLE Statement**
```sql
CREATE TABLE table_name(  
    col1 datatype PRIMARY KEY,  
    col2 datatype,  
    ...  
);  

or

CREATE TABLE Persons (
    ID int NOT NULL,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Age int,
    PRIMARY KEY (ID)
);
```

**2. Using ALTER TABLE Statement**
```sql
ALTER TABLE table_name
ADD PRIMARY KEY (column_name);
```

**DROP a PRIMARY KEY**
```sql
ALTER TABLE table_name
DROP PRIMARY KEY;
```
## 3. Foreign Key
The foreign key is used to link one or more than one table together. It is also known as the referencing key. A foreign key matches the primary key field of another table. It means a foreign key field in one table refers to the primary key field of the other table.

**Create a foreign key in two ways:**
**1. Using CREATE TABLE Statement**
```sql
CREATE TABLE Orders (
    OrderID int NOT NULL,
    OrderNumber int NOT NULL,
    PersonID int,
    PRIMARY KEY (OrderID),
    FOREIGN KEY (PersonID) REFERENCES table_name(column_name)
);
```

**2. Using ALTER TABLE Statement**
```sql
ALTER TABLE Orders
ADD FOREIGN KEY (PersonID) REFERENCES Persons(PersonID);
```

**DROP a FOREIGN KEY Constraint**
```sql
ALTER TABLE table_name
DROP FOREIGN KEY constraint_name;
```
**constraint_name:** It specifies the name of the foreign key constraint. If we have not provided the constraint name, MySQL generates its name automatically.

### Example:
Consider two tables: `orders` and `customers`. Each order is placed by a customer, so the orders table needs to reference the customers table.

`customers` **Table (Parent Table):**
```sql
CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    customer_name VARCHAR(100),
    email VARCHAR(100)
);
```

`orders` **Table (Child Table):**
```sql
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    order_date DATE,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
```

- `customer_id` is the foreign key in the `orders` table.
- `customer_id` in the `orders` table references the `customer_id` in the `customers` table (which is the **primary key** of the `customers` table).
- This means every record in the `orders` table must correspond to an existing `customer` in the customers table.

### Behavior of Foreign Keys:
**Insertion:** You cannot insert an order into the table unless the you are inserting already exists in the table.`orders` `customer_id` `customers`

For example:
```sql
INSERT INTO customers (customer_id, customer_name) VALUES (1, 'John Doe');
INSERT INTO orders (order_id, order_date, customer_id) VALUES (101, '2024-12-01', 1);
-- This is valid because customer_id = 1 exists in the customers table.
```
**Deletion:** If you try to delete a row from the `customers` table that is being referenced in the `orders` table, you will get an error unless you handle this case using **cascade options** like **ON DELETE CASCADE**.

For example:
```sql
DELETE FROM customers WHERE customer_id = 1;
-- This will fail if there are orders linked to this customer, unless you define cascading behavior.
```

**Update:** You cannot update a foreign key value in the child table (`orders`) to a value that doesn’t exist in the parent table (`customers`).

## 4. Composite Key
A composite key in MySQL is a combination of two or more than two columns in a table that allows us to identify each row of the table uniquely. `Whenever a primary key consist of more than one attribute is known as composite key.`

**Using CREATE Statement**
```sql
CREATE TABLE Product (  
    Prod_ID int NOT NULL,   
    Name varchar(45),   
    Manufacturer varchar(45),  
    PRIMARY KEY(Name, Manufacturer)  
);  
```

**2. Using ALTER TABLE Statement**
```sql
ALTER TABLE Student add primary key(stud_id, subject);
```
# MySql Joins
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

## UNION ALL
Same as UNION but The UNION operator selects only distinct values by default. To allow duplicate values, use UNION ALL:

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
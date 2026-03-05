# SQL Notes: CONCAT vs GROUP_CONCAT

## 1. CONCAT()

`CONCAT()` is a SQL function used to join multiple strings into a single
string.

### Syntax

``` sql
CONCAT(string1, string2, string3, ...)
```

### Example

``` sql
SELECT CONCAT(FirstName, ' ', LastName) AS FullName
FROM Employees;
```

### Result

If: FirstName = John\
LastName = Doe

Output:

    John Doe

### Characteristics

-   Combines **columns or strings in the same row**
-   Works row by row
-   Returns a single combined value

### Example with multiple columns

``` sql
SELECT CONCAT(City, '-', Country)
FROM Customers;
```

Output example:

    Delhi-India
    London-UK
    Paris-France

------------------------------------------------------------------------

# 2. GROUP_CONCAT()

`GROUP_CONCAT()` is an **aggregate function** that concatenates values
from **multiple rows into a single string**.

### Syntax

``` sql
GROUP_CONCAT(column_name)
```

### Example

``` sql
SELECT Department,
GROUP_CONCAT(EmployeeName) AS Employees
FROM Employees
GROUP BY Department;
```

### Example Data

  Department   EmployeeName
  ------------ --------------
  IT           John
  IT           Mike
  HR           Sara
  HR           Anna

### Result

  Department   Employees
  ------------ -----------
  IT           John,Mike
  HR           Sara,Anna

------------------------------------------------------------------------

# 3. GROUP_CONCAT with SEPARATOR

You can change the separator.

``` sql
SELECT Department,
GROUP_CONCAT(EmployeeName SEPARATOR ' | ')
FROM Employees
GROUP BY Department;
```

Output:

    John | Mike
    Sara | Anna

------------------------------------------------------------------------

# 4. GROUP_CONCAT with ORDER BY

``` sql
SELECT Department,
GROUP_CONCAT(EmployeeName ORDER BY EmployeeName)
FROM Employees
GROUP BY Department;
```

This sorts values before concatenation.

------------------------------------------------------------------------

# 5. Key Differences

  Feature             CONCAT                GROUP_CONCAT
  ------------------- --------------------- ------------------------------
  Type                String function       Aggregate function
  Works on            Columns in same row   Values across multiple rows
  Requires GROUP BY   No                    Usually Yes
  Use Case            Combine fields        Combine rows into one string

------------------------------------------------------------------------

# 6. Simple Understanding

### CONCAT

    Row level joining
    FirstName + LastName

### GROUP_CONCAT

    Group level joining
    All rows in a group combined

------------------------------------------------------------------------

# 7. Real World Examples

### CONCAT

-   Create full name
-   Combine address fields
-   Build formatted strings

### GROUP_CONCAT

-   Show all products for an order
-   List all roles of a user
-   Combine tags or categories

------------------------------------------------------------------------

# Final Summary

-   **CONCAT()** joins strings from **columns in the same row**.
-   **GROUP_CONCAT()** joins values from **multiple rows into one
    string**.
-   `GROUP_CONCAT()` is commonly used with **GROUP BY** in reporting
    queries.

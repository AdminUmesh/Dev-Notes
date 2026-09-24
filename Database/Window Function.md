# Window Function
A **Window Function** performs a calculation across a set of table rows related to the current row.

`Unlike GROUP BY, it **does not collapse rows**.`

---

## Basic Syntax
```sql
FUNCTION_NAME() OVER (
    PARTITION BY column
    ORDER BY column
)
```

### Most Important Part
```sql
OVER(...)
```

`OVER()` defines the rows on which the window function performs its calculation.

---

# 1. ROW_NUMBER()

`ROW_NUMBER()` assigns a **unique sequential number** to each row.

### Example

```sql
SELECT
    StudentName,
    Marks,
    ROW_NUMBER() OVER (
        ORDER BY Marks DESC
    ) AS RowNum
FROM Students;
```

### Result

| StudentName | Marks | RowNum |
| ----------- | ----: | -----: |
| Simran      |    90 |      1 |
| Rahul       |    70 |      2 |
| Karan       |    70 |      3 |
| Karan       |    60 |      4 |

### Important

Even if two students have the same marks, `ROW_NUMBER()` gives them **different numbers**.

---

# 2. RANK()

`RANK()` assigns the **same rank to rows with the same value**.

If there is a tie, the next rank is skipped.

### Example

```sql
SELECT
    StudentName,
    Marks,
    RANK() OVER (
        ORDER BY Marks DESC
    ) AS RankNum
FROM Students;
```

### Result

| StudentName | Marks | RankNum |
| ----------- | ----: | ------: |
| Simran      |    90 |       1 |
| Rahul       |    70 |       2 |
| Karan       |    70 |       2 |
| Amit        |    60 |       4 |

Notice that there is **no rank 3** because Rahul and Karan both have rank 2.

---

# 3. DENSE_RANK()

`DENSE_RANK()` also gives the **same rank to rows with the same value**, but it **does not skip the next rank**.

### Example

```sql
SELECT
    StudentName,
    Marks,
    DENSE_RANK() OVER (
        ORDER BY Marks DESC
    ) AS RankNum
FROM Students;
```

### Result

| StudentName | Marks | RankNum |
| ----------- | ----: | ------: |
| Simran      |    90 |       1 |
| Rahul       |    70 |       2 |
| Karan       |    70 |       2 |
| Amit        |    60 |       3 |

---

### Easy way to remember

* **ROW_NUMBER** → Always unique number
* **RANK** → Same rank for ties, **skips** rank
* **DENSE_RANK** → Same rank for ties, **doesn't skip** rank

---

# 6. PARTITION BY

`PARTITION BY` divides the rows into groups, and the window function works **separately inside each group**.

### Example

Suppose we have:

| Department | Name   | Salary |
| ---------- | ------ | -----: |
| IT         | Rahul  |  80000 |
| IT         | Amit   |  60000 |
| HR         | Simran |  70000 |
| HR         | Priya  |  50000 |

```sql
SELECT
    Department,
    Name,
    Salary,
    ROW_NUMBER() OVER (
        PARTITION BY Department
        ORDER BY Salary DESC
    ) AS DeptRank
FROM Employees;
```

### Result

| Department | Name   | Salary | DeptRank |
| ---------- | ------ | -----: | -------: |
| IT         | Rahul  |  80000 |        1 |
| IT         | Amit   |  60000 |        2 |
| HR         | Simran |  70000 |        1 |
| HR         | Priya  |  50000 |        2 |

Here, ranking **starts again from 1 for each department**.

---
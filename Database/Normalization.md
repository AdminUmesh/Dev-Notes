# Normalization?

Normalization is the process in which divide large tables into smaller related tables to reduce data duplication.

## Why is Normalization Needed?

-   Removes duplicate data
-   Saves storage
-   Makes updates easier
-   Improves consistency
-   Prevents data anomalies

------------------------------------------------------------------------

### Example (Without Normalization)

  |StudentId  | StudentName  | Course   | Teacher  |
  |-----------| -------------| ---------| ---------|
  |1          | Umesh        | C#       | Rahul    |
  |1          | Umesh        | SQL      | Amit     |
  |1          | Umesh        | Angular  | Ravi     |
  |2          | Mohit        | SQL      | Amit     |

**Problems: -** 
- Student name is repeated.
- Updating a student's name requires multiple updates.
- Wasted storage.

### After Normalization

#### Student

  |StudentId  | StudentName|
  |-----------| -------------|
  |1          | Umesh|
  |2          | Mohit|

#### Course

  |CourseId  | Course   |
  |----------| ---------|
  |1         | C#       |
  |2         | SQL      |
  |3         | Angular  |

#### StudentCourse

 | StudentId  | CourseId  | Teacher  |
 | -----------| ----------| ---------|
 | 1          | 1         | Rahul    |
 | 1          | 2         | Amit     |
 | 1          | 3         | Ravi     |
 | 2          | 2         | Amit     |

------------------------------------------------------------------------

# Normal Forms

## 1NF (First Normal Form)

**Rules:** 
- Every column contains atomic (single) values. 
- No repeating groups.

#### ❌ Example — Not in 1NF

 | Student  | Subjects          |
 | ---------| ------------------|
 | Umesh    | C#, SQL, Angular  |

Because The `Subjects` column contains multiple values in one cell.

#### ✅ Solution — Split into separate tables.

  |Student  | Subject  |
  |---------| ---------|
  |Umesh    | C#       |
  |Umesh    | SQL      |
  |Umesh    | Angular  |

Now, each cell contains a single value.

------------------------------------------------------------------------

## 2NF (Second Normal Form)

**Rules:** 
- Must satisfy 1NF. 
- Every non-key column must depend on the entire primary key.

#### ❌ Example — Not in 2NF

  |StudentId  | CourseId  | StudentName   | CourseName |
  |-----------| ----------| ------------- |------------|
  |1          | 101       | Rahul         |   SQL      |
  |1          | 102       | Rahul         |   Angular  |
  |2          | 101       | Amit          |   SQL      |

The composite primary key is `(StudentId, CourseId).`
**But:**
- StudentName depends only on StudentId.
- CourseName depends only on CourseId.

Neither depends on the whole composite key. These are called partial dependencies

#### ✅ Solution — Split into separate tables.

**Students**
|StudentId (PK)	| StudentName |
| ----------    | ----------  |
|1	            | Rahul       |
|2	            | Amit        |

**Courses**
|CourseId (PK)	| CourseName|
| ------------  | --------  |
|101	          |   SQL     |
|102	          | Angular   |

**StudentCourse**
|StudentId (PK)	| CourseId (PK)|
| ------------  | ----------   |
|1	            |    101      |
|1	            |    102      |
|2	            |    101      |

Now, each non-key column depends on the whole key of its table.
------------------------------------------------------------------------

## 3NF (Third Normal Form)

**Rules:** 
- Must satisfy 2NF. 
- A table must be in 2NF, and a non-key column should not depend on another non-key column.

#### ❌ Example — Not in 3NF
Employee

  | EmployeeId (PK)  | EmployeeName  | DepartmentId | DepartmentName|
  | ---------------  | ------------  | ------------ |  ------------ |
  |      1           |     Rahul     |      10      |       IT      |
  |      2           |     Amit      |      20      |       HR      |
  |      3           |     Priya     |      10      |       IT      |

**Dependencies:**
- EmployeeId → DepartmentId
- DepartmentId → DepartmentName

**Explanation:**
- EmployeeId determines which department an employee belongs to.
- DepartmentId determines the department's name.
- Therefore, we can find DepartmentName indirectly through DepartmentId.
This is called an indirect dependency.

It also creates duplicate data because the department name IT appears multiple times.

#### ✅ Solution — Split into two tables
Employees
| EmployeeId (PK)	| EmployeeName	| DepartmentId (FK)|
|  ------------   |  ----------   |  --------------  |
|      1	        |    Rahul	    |       10         |
|      2	        |    Amit	      |       20         |
|      3	        |    Priya	    |       10         |

Departments
|DepartmentId (PK) |	DepartmentName|
|  --------------  |  ------------  |
|        10        |	     IT       |
|        20        |	     HR       |

Now, department details are stored in one place, avoiding unnecessary duplication.

**Remember:** In 3NF, avoid an indirect dependency where a non-key column depends on another non-key column instead of depending directly on the key.

------------------------------------------------------------------------

# Data Anomalies
Data anomalies are problems that occur when data is unnecessarily duplicated or poorly organized in database tables.
`Normalization helps reduce these problems.`

## types of data anomalies
There are three main types of data anomalies:

| EmployeeId|	EmployeeName	| DepartmentId	| DepartmentName |
|  -------  |  -----------  | ------------  |  ------------  |
|     1	    |    Rahul	    |      10	      |       IT       |
|     2	    |    Amit	      |      20	      |       HR       |
|     3	    |    Priya	    |      10	      |       IT       |

### 1. Insert Anomaly
An insert anomaly occurs when we cannot insert certain information without inserting some unrelated information.

**EX:-** Problem: We want to add a new department, Finance, but no employee has joined it yet.

### 2. Update Anomaly
An update anomaly occurs when the same information is stored in multiple rows and must be updated in every row.

**EX:-** Problem: The IT department changes its name to Technology.
In the original table, we must update multiple rows.

### 3. Delete Anomaly
A delete anomaly occurs when deleting one record unintentionally removes other important information.

**EX:-** Problem: Suppose Priya is the only employee in the Finance department.
If Priya leaves and we delete her row, the only record containing the Finance department's details also disappears.
------------------------------------------------------------------------

## Advantages

-   Less redundancy
-   Better consistency
-   Saves storage
-   Easier maintenance
-   Better data integrity

## Disadvantages

-   More tables
-   More JOINs
-   More complex queries
-   Slightly slower read performance

------------------------------------------------------------------------

## Normalization vs Denormalization

  | Normalization       | Denormalization          |
  | --------------------| -------------------------|
  | Removes redundancy  | Adds redundancy          |
  | More tables         | Fewer tables             |
  | More JOINs          | Fewer JOINs              |
  | Better consistency  | Better read performance  |
  | Used in OLTP        | Used in reporting/data warehouses|

------------------------------------------------------------------------

## Interview Questions

1.  What is normalization?
2.  Explain 1NF, 2NF, and 3NF.
3.  What are insert, update, and delete anomalies?
4.  Normalization vs Denormalization?
5.  Why isn't every database fully normalized?

------------------------------------------------------------------------

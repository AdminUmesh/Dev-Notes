## What is Normalization?

Normalization is the process in which divide large tables into smaller related tables to reduce data duplication.

------------------------------------------------------------------------

## Why is Normalization Needed?

-   Removes duplicate data
-   Saves storage
-   Makes updates easier
-   Improves consistency
-   Prevents data anomalies

------------------------------------------------------------------------

## Example (Without Normalization)

  StudentId   StudentName   Course    Teacher
  ----------- ------------- --------- ---------
  1           Umesh         C#        Rahul
  1           Umesh         SQL       Amit
  1           Umesh         Angular   Ravi
  2           Mohit         SQL       Amit

Problems: - Student name is repeated. - Updating a student's name
requires multiple updates. - Wasted storage.

### After Normalization

#### Student

  StudentId   StudentName
  ----------- -------------
  1           Umesh
  2           Mohit

#### Course

  CourseId   Course
  ---------- ---------
  1          C#
  2          SQL
  3          Angular

#### StudentCourse

  StudentId   CourseId   Teacher
  ----------- ---------- ---------
  1           1          Rahul
  1           2          Amit
  1           3          Ravi
  2           2          Amit

------------------------------------------------------------------------

# Normal Forms

## 1NF (First Normal Form)

Rules: - Every column contains atomic (single) values. - No repeating
groups.

❌ Bad

  Student   Subjects
  --------- ------------------
  Umesh     C#, SQL, Angular

✅ Good

  Student   Subject
  --------- ---------
  Umesh     C#
  Umesh     SQL
  Umesh     Angular

------------------------------------------------------------------------

## 2NF (Second Normal Form)

Rules: - Must satisfy 1NF. - Every non-key column must depend on the
entire primary key.

Example:

  StudentId   CourseId   StudentName
  ----------- ---------- -------------
  1           101        Umesh

`StudentName` depends only on `StudentId`, so move it to a separate
Student table.

------------------------------------------------------------------------

## 3NF (Third Normal Form)

Rules: - Must satisfy 2NF. - No transitive dependency.

Example:

  EmployeeId   DepartmentId   DepartmentName
  ------------ -------------- ----------------
  1            10             IT

Split into:

### Employee

  EmployeeId   DepartmentId
  ------------ --------------
  1            10

### Department

  DepartmentId   DepartmentName
  -------------- ----------------
  10             IT

------------------------------------------------------------------------

# Data Anomalies

### Insert Anomaly

Cannot insert required data without unrelated data.

### Update Anomaly

Same information must be updated in multiple rows.

### Delete Anomaly

Deleting one row accidentally removes important information.

------------------------------------------------------------------------

# Advantages

-   Less redundancy
-   Better consistency
-   Saves storage
-   Easier maintenance
-   Better data integrity

# Disadvantages

-   More tables
-   More JOINs
-   More complex queries
-   Slightly slower read performance

------------------------------------------------------------------------

# Normalization vs Denormalization

  Normalization        Denormalization
  -------------------- -----------------------------------
  Removes redundancy   Adds redundancy
  More tables          Fewer tables
  More JOINs           Fewer JOINs
  Better consistency   Better read performance
  Used in OLTP         Used in reporting/data warehouses

------------------------------------------------------------------------

# Interview Questions

1.  What is normalization?
2.  Explain 1NF, 2NF, and 3NF.
3.  What are insert, update, and delete anomalies?
4.  Normalization vs Denormalization?
5.  Why isn't every database fully normalized?

------------------------------------------------------------------------

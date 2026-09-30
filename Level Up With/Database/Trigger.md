1. Trigger fundamentals

What is a trigger, and when should we use a trigger instead of a stored procedure?

What is the difference between DML, DDL, and logon triggers?

What is the difference between AFTER and INSTEAD OF triggers?

Can a table have multiple triggers for the same event? How is their execution order managed?

What is the difference between a trigger and a constraint?

Can a trigger execute another stored procedure?

Can we pass parameters to a trigger?

2. Inserted and deleted tables

What are the inserted and deleted logical tables in SQL Server triggers?

What data does inserted contain during an UPDATE operation?

What data does deleted contain during an UPDATE operation?

Why should we avoid writing triggers that assume only one row is inserted or updated?

How do you write a trigger that correctly handles 1,000 rows inserted in a single statement?

How can you identify which columns changed during an update?

3. Transactions and error handling

If a trigger fails, what happens to the original INSERT, UPDATE, or DELETE statement?

Can we use COMMIT and ROLLBACK inside a trigger? What are the implications?

How do you handle errors inside a trigger using TRY...CATCH?

What happens if a trigger performs an update and that update activates another trigger?

How do nested triggers and recursive triggers differ?

How can you prevent unwanted recursion or repeated trigger execution?

4. Performance and concurrency

A trigger makes an INSERT statement slow. How would you troubleshoot it?

Why should we avoid cursors and row-by-row processing inside triggers?

Can triggers cause deadlocks? Give a real-world example.

How can triggers affect transaction duration and blocking?

What happens when multiple users insert records simultaneously into a table with a trigger?

How would you optimize a trigger that updates another table after every insert?

5. Real-world scenario-based questions
Scenario 1 · Audit logging

Whenever an employee's salary changes, store the old salary, new salary, employee ID, and change date in an audit table. How would you implement this?

Expected discussion: AFTER UPDATE, inserted, deleted, and a set-based insert that handles multiple employees.

Scenario 2 · Inventory management

Whenever an order detail is inserted, reduce the corresponding product stock. What happens if 100 order details are inserted together?

Expected discussion: set-based logic, aggregation when multiple rows reference the same product, transaction safety, and stock validation.

Scenario 3 · Prevent invalid data

You need to prevent a user's salary from being reduced below ₹10,000. Would you use a trigger, a constraint, or application-level validation? Why?

Expected discussion: a CHECK constraint is generally appropriate for a rule that applies to individual rows, while triggers support more complex cross-row or cross-table rules.

Scenario 4 · Trigger performance

An UPDATE statement modifies 50,000 rows and takes 30 seconds after a trigger was added. How would you find and fix the problem?

Expected discussion: inspect trigger logic and execution plans, measure reads and execution time, remove row-by-row processing, and check indexes and blocking.

6. DDL trigger questions

What is a DDL trigger, and how is it different from a DML trigger?

What is the difference between ON DATABASE and ON ALL SERVER?

How can you prevent someone from dropping a table using a DDL trigger?

What is EVENTDATA() in SQL Server, and how can it be used to audit schema changes?

Can a DDL trigger capture who created, altered, or dropped a stored procedure?

Practise a senior-level question
Question 1 · Frequently tested concept
Suppose an UPDATE statement modifies 100 employee records. How many times does an AFTER UPDATE trigger execute?
A. 100 times — once for each row
B. Once for the entire UPDATE statement
C. It depends on how many columns are updated
Check answer
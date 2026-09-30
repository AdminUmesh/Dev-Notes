1. View fundamentals and concepts

What is a view, and why do we use it instead of writing a query directly against a table?

What is the difference between a simple view and a complex view?

Can we pass parameters to a view? If not, what alternatives can we use?

Can we create a view based on another view? What problems can this cause?

What happens to a view if the underlying table is dropped or a column used by the view is renamed?

2. Updating data through views

Can we perform INSERT, UPDATE, and DELETE operations through a view? Explain with examples.

Why can't we use TRUNCATE TABLE on a view?

Can we update a view that contains a JOIN between two tables?

Can we insert data through a view if the underlying table contains a NOT NULL column that is missing from the view?

What is an INSTEAD OF trigger, and how can it make a complex view updatable?

Can we update a view containing GROUP BY, DISTINCT, or aggregate functions such as SUM()?

If we update a view, does the underlying table's data also change? What if multiple tables are involved?

3. Performance and indexing

Does a normal view store data physically? When is its query executed?

Does creating a view improve query performance automatically?

What is an indexed view in SQL Server, and how is it different from a normal view?

What are the requirements and restrictions for creating an indexed view?

A view joining five tables is slow. How would you investigate and optimize it?

If a view references another view, how can you troubleshoot performance problems?

4. Real-world scenario-based questions
Scenario 1

You created a view using a SELECT query. When you try to insert a row through it, SQL Server returns an error about a missing column. Why?

Expected discussion: underlying table constraints, omitted mandatory columns, default values, and identity columns.

Scenario 2

A view displays the result of joining Employees and Departments. You need to update an employee's department through that view. Is it possible?

Expected discussion: SQL Server's rules for updating joined views, which table is being modified, and when an INSTEAD OF trigger may be needed.

Scenario 3

A view calculates total sales by customer using GROUP BY. A user wants to update one customer's total sales directly through the view. Can you do it?

Expected discussion: aggregate values are calculated from underlying rows; the grouped result is not directly updatable. Update the source rows through appropriate logic instead.

Scenario 4

A column is added to an underlying table, but your existing view does not display it. What would you do?

Expected discussion: alter the view definition to include the new column and understand how SQL Server handles view metadata.

5. Difference-based questions

View vs stored procedure: when would you use each?

View vs inline table-valued function: what is the difference?

View vs CTE: how do their definitions and usage differ?

Normal view vs indexed view: what are the storage and maintenance trade-offs?

Can a view use ORDER BY? What restrictions apply?

Can we create a view using multiple tables and subqueries?

How do permissions on a view affect access to its underlying tables?

Practise like a senior developer

Here is a practical question. Try answering it before revealing the expected answer.

Interview question · Advanced
Can we update a view that contains a JOIN between two tables?
A. No, views containing JOINs can never be updated.
B. Yes, every column from both tables can always be updated.
C. It depends on the operation and SQL Server's rules for updatable joined views.
Check answer
The correct answer is C.

SQL Server allows certain updates through joined views, but not every operation or every column is updatable. An INSTEAD OF trigger can implement custom modification logic when direct updates are not sufficient.
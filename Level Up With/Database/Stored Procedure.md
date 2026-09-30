1. Stored Procedure Fundamentals

What is a stored procedure, and how is it different from a function?

What is the difference between EXEC and sp_executesql?

What is the difference between input and output parameters in a stored procedure?

Can a stored procedure call another stored procedure? How do you handle its return value and output parameters?

What is the difference between RETURN, OUTPUT parameters, and SELECT results?

2. Performance Optimization

A stored procedure takes 2 seconds in SSMS but 10 seconds when called from a .NET application. How would you investigate the issue?

A stored procedure that previously ran in 1 second now takes 30 seconds. How would you identify the root cause?

What is parameter sniffing? How can it affect stored procedure performance?

How do you identify which query inside a stored procedure is consuming the most time?

What is the difference between CPU time and elapsed time? If elapsed time is high but CPU time is low, what could be happening?

3. Transactions and Error Handling

How do you implement a transaction inside a stored procedure?

What happens if an error occurs after the first UPDATE but before the second UPDATE?

Explain TRY...CATCH, XACT_ABORT, COMMIT, and ROLLBACK.

How would you ensure that a stored procedure either updates all related tables successfully or rolls back all changes?

What is a deadlock? How would you troubleshoot and reduce deadlocks involving stored procedures?

4. Advanced SQL and Concurrency

What is the difference between temporary tables (#Temp) and table variables (@Temp) inside a stored procedure?

When should you use dynamic SQL, and how do you prevent SQL injection?

What happens when two users execute the same stored procedure simultaneously to update the same record?

How would you implement pagination in a stored procedure for millions of records?

A stored procedure updates 100,000 records and blocks other users. How would you improve its performance and reduce blocking?
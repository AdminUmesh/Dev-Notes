## By Print Method

```sql
DECLARE @StartTime DATETIME2(7);
SET @StartTime = SYSDATETIME();

-- Your SQL Query-1
  PRINT 'Query-1 Time: ' 
+ CAST(DATEDIFF(MILLISECOND, @StartTime, SYSDATETIME()) AS VARCHAR(20))
+ ' ms';

SET @StartTime = SYSDATETIME();

-- Your SQL Query-2

  PRINT 'Query-1 Time: ' 
+ CAST(DATEDIFF(MILLISECOND, @StartTime, SYSDATETIME()) AS VARCHAR(20))
+ ' ms';
```

#### Result Look Like
```base
Query-1 Time: 984 ms
Query-2 Time: 875 ms
```

## Using Statics Method

```sql
SET STATISTICS TIME ON;
SET STATISTICS IO ON;

-- Your Query or Procedure Here

SET STATISTICS TIME OFF;
SET STATISTICS IO OFF;
```

#### Result Look Like
```base
 SQL Server Execution Times:
   CPU time = 0 ms,  elapsed time = 0 ms.
Table 'customer'. Scan count 1, logical reads 3102, physical reads 0, read-ahead reads 0, lob logical reads 0, lob physical reads 0, lob read-ahead reads 0.

 SQL Server Execution Times:
   CPU time = 891 ms,  elapsed time = 889 ms.
Query-1 Executed
```

1. #### Table 'Customer' means
2. #### Scan count means
3. #### logical reads means
4. #### CPU time means
5. #### elapsed time means


## Look Another Solution
1. First look at both together

Suppose your query gives:

CPU time     = 6000 ms
Elapsed time = 6200 ms

CPU and elapsed are both high.

Another query:

CPU time     = 100 ms
Elapsed time = 6200 ms

Elapsed is high, but CPU is low.

These two situations need different investigations.

Case 1: Elapsed time is HIGH and CPU time is HIGH

Example:

CPU time     = 6000 ms
Elapsed time = 6200 ms

This means:

SQL Server is spending most of those 6 seconds actually processing the query.

What should you investigate?

Usually start with:

A. Execution Plan

Press:

Ctrl + M

Run the query and inspect the Actual Execution Plan.

Look for expensive operations such as:

Table Scan
Index Scan
Sort
Hash Match
Key Lookup
Nested Loops
B. Logical reads

Check:

logical reads = ?

For example:

logical reads = 500,000

That's a strong indication that SQL Server is processing a lot of data.

You may need:

better indexes
better WHERE conditions
better joins
query rewriting
C. Indexes

Suppose you have:

SELECT *
FROM customer
WHERE CustomerCode = @CustomerCode;

If CustomerCode isn't indexed, SQL Server might scan a large part of the table.

A suitable index might allow:

Table Scan
      ↓
read thousands of pages

to become:

Index Seek
      ↓
find required row

So when:

CPU HIGH
Elapsed HIGH

Execution Plan + Logical Reads + Indexes are the first things I'd investigate.

Case 2: Elapsed time HIGH but CPU time LOW

This is a different situation.

Example:

CPU time     = 100 ms
Elapsed time = 6000 ms

SQL Server only worked for about 100 ms, but the query took 6 seconds overall.

That means:

SQL Server spent a lot of time waiting.

Think:

6 seconds total
│
├── CPU working: 0.1 sec
│
└── Waiting:     ~5.9 sec

Now don't immediately start adding indexes.

Instead, investigate what SQL Server is waiting for.

Common causes include:

A. Blocking / locks

Another transaction might be holding a lock.

Example:

Query A
   ↓
UPDATE customer
   ↓
holds lock

Query B
   ↓
SELECT customer
   ↓
WAITING...

Query B might show:

CPU = 50 ms
Elapsed = 6000 ms

because it wasn't actually processing for 6 seconds—it was waiting for Query A.

B. Disk / I/O

The query might be waiting for data to be read from storage.

Check:

physical reads

and the execution plan.

C. Other waits

SQL Server has many types of waits.

For deeper investigation you can look at wait statistics, but don't jump there immediately. First identify whether blocking/I/O is actually happening.

Case 3: CPU HIGH but Elapsed is LOW

For example:

CPU time     = 5000 ms
Elapsed time = 200 ms

This can happen with parallelism, where multiple CPU cores work on the query simultaneously.

For example:

              Query
                │
       ┌────────┼────────┐
       ↓        ↓        ↓
     CPU 1    CPU 2    CPU 3
       │        │        │
       └────────┼────────┘
                ↓
             Result

The total CPU consumed can be larger than the wall-clock elapsed time.

This isn't automatically a problem.

You'd need to look at the execution plan and other details before deciding whether parallelism is involved or problematic.

The easiest rule for you

For now, remember this table:

CPU	Elapsed	What it suggests	What to investigate
Low	Low	Query is fine	Nothing
High	High	Query is doing lots of work	Execution Plan, logical reads, indexes, joins
Low	High	Query is mostly waiting	Blocking, waits, I/O
High	Low	Possible parallelism	Execution Plan
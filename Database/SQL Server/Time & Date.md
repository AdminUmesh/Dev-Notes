# 1. Date and Time Data Types in SQL Server
- **DATE:** Stores only the date (YYYY-MM-DD).
- **TIME:** Stores only the time (HH:MM:SS).
- **DATETIME:** Stores both date and time (YYYY-MM-DD HH:MM:SS).
- **DATETIME2:** Similar to DATETIME but with higher precision (YYYY-MM-DD HH:MM:SS.fff).
- **SMALLDATETIME:** Stores date and time with a lower range.
- **DATETIMEOFFSET:** Includes time zone information.
- **TIMESTAMP:** Used for row versioning, not an actual date/time type.

# 2. Current Date and Time Functions
- **GETDATE():** Returns the current date and time.
- **SYSDATETIME():** Returns the current system date and time with higher precision.
- **CURRENT_TIMESTAMP:** Equivalent to GETDATE().
- **GETUTCDATE():** Returns the current UTC (Coordinated Universal Time) date and time.
- **SYSDATETIMEOFFSET():** Returns the current date and time with time zone info.

Example:
```sql
-- Returns current date and time
SELECT GETDATE();  

-- Returns current system date and time with higher precision
SELECT SYSDATETIME();  

-- Returns current UTC date and time
SELECT GETUTCDATE();  

-- Returns current date and time with time zone offset
SELECT SYSDATETIMEOFFSET();
```

# 3. Date and Time Manipulation Functions
**3.1 DATEPART:** Extract a specific part of a date (like year, month, day, etc.)
```sql
-- Extracts the year from the current date
SELECT DATEPART(YEAR, GETDATE());  -- e.g., 2024

-- Extracts the month from the current date
SELECT DATEPART(MONTH, GETDATE());  -- e.g., 12

-- Extracts the day from the current date
SELECT DATEPART(DAY, GETDATE());  -- e.g., 17

-- Extracts the hour from the current time
SELECT DATEPART(HOUR, GETDATE());  -- e.g., 14

-- Extracts the minute from the current time
SELECT DATEPART(MINUTE, GETDATE());  -- e.g., 30

-- Extracts the second from the current time
SELECT DATEPART(SECOND, GETDATE());  -- e.g., 45
```

**3.2 DATEADD:** Add or subtract a specific time interval to/from a date
```sql
-- Adds 5 days to the current date
SELECT DATEADD(DAY, 5, GETDATE());  -- e.g., 2024-12-22

-- Subtracts 2 months from the current date
SELECT DATEADD(MONTH, -2, GETDATE());  -- e.g., 2024-10-17
```

**3.3 DATEDIFF:** Get the difference between two dates in terms of a specified unit (e.g., days, months, years)
```sql
-- Calculates the difference in days between two dates
SELECT DATEDIFF(DAY, '2023-01-01', GETDATE());  -- e.g., 350 days

-- Calculates the difference in months between two dates
SELECT DATEDIFF(MONTH, '2023-01-01', GETDATE());  -- e.g., 11 months

-- Calculates the difference in years between two dates
SELECT DATEDIFF(YEAR, '2020-01-01', GETDATE());  -- e.g., 4 years
```

**3.4 CAST and CONVERT:** Converting DATETIME to specific formats
```sql
-- Cast datetime to date only (no time)
SELECT CAST(GETDATE() AS DATE);  -- e.g., 2024-12-17

-- Cast datetime to time only (no date)
SELECT CAST(GETDATE() AS TIME);  -- e.g., 14:30:45.1234567

-- Convert datetime to a string with a specific format (DD/MM/YYYY)
SELECT CONVERT(VARCHAR, GETDATE(), 103);  -- e.g., 17/12/2024

-- Convert datetime to ISO format (YYYY-MM-DD HH:MI:SS)
SELECT CONVERT(VARCHAR, GETDATE(), 120);  -- e.g., 2024-12-17 14:30:45
```

**3.5 FORMAT:** Format DATETIME values as strings (SQL Server 2012+)
```sql
-- Format date to custom string (YYYY-MM-DD HH:MI:SS)
SELECT FORMAT(GETDATE(), 'yyyy-MM-dd HH:mm:ss');  -- e.g., 2024-12-17 14:30:45

-- Format date with weekday and month names
SELECT FORMAT(GETDATE(), 'dddd, MMMM dd, yyyy');  -- e.g., Monday, December 17, 2024
```

# 4. Comparing Date and Time
SQL Server allows direct comparison of date and time values. For comparing only the date or the time, use `CAST` or `CONVERT` to extract the relevant part.`

**Example 1: Compare exact date and time:**
```sql
-- Exact match: Returns records where the order date is exactly '2023-12-01 10:30:00'
SELECT * FROM Orders WHERE OrderDate = '2023-12-01 10:30:00';
```

**Example 2: Compare only the date (ignoring time):**
```sql
-- Compare only the date part of 'OrderDate'
SELECT * FROM Orders WHERE CAST(OrderDate AS DATE) = '2023-12-01';
```

**Example 3: Compare only the time (ignoring date):**
```sql
-- Compare only the time part of 'OrderDate'
SELECT * FROM Orders WHERE CAST(OrderDate AS TIME) = '10:30:00';
```

# 5. Calculate Day Index (Day of Year)
You can calculate the **Day Index**, which is the ordinal day number of the year (1 through 365 or 366), using `DATEPART`:

```sql
-- Get the day index of the year (1-365 or 1-366)
SELECT DATEPART(DAYOFYEAR, GETDATE());  -- e.g., 351 (for Dec 17th)
```

Alternatively, to get the **Day of the Week** (1 = Sunday, 7 = Saturday):

```sql
-- Get the day of the week (1=Sunday, 7=Saturday)
SELECT DATEPART(WEEKDAY, GETDATE());  -- e.g., 2 (Monday)
```

# 6. Working with Time Zones
SQL Server doesn’t automatically handle time zones with `DATETIM`E, but you can use `DATETIMEOFFSET` to store time zone information. From SQL Server 2016 onward, you can convert between time zones using `AT TIME ZONE`.

```sql
-- Convert to UTC
SELECT GETDATE() AT TIME ZONE 'UTC';  -- Converts local time to UTC

-- Convert to Pacific Standard Time (PST)
SELECT GETDATE() AT TIME ZONE 'Pacific Standard Time';  -- Converts local time to PST
```

# 7. Useful Date and Time Formats in SQL Server
You can use `CONVERT` and `FORMAT` to output dates in different formats:

**Example:**
```sql
-- MM/DD/YY format (Style 1)
SELECT CONVERT(VARCHAR, GETDATE(), 1);  -- e.g., 12/17/24

-- DD/MM/YYYY format (Style 103)
SELECT CONVERT(VARCHAR, GETDATE(), 103);  -- e.g., 17/12/2024

-- ISO 8601 format (YYYY-MM-DD HH:MI:SS) (Style 120)
SELECT CONVERT(VARCHAR, GETDATE(), 120);  -- e.g., 2024-12-17 14:30:45
```

# 8. Extracting Specific Date Parts
**Extract Year:**

```sql
SELECT YEAR(GETDATE());  -- e.g., 2024
```

**Extract Month:**
```sql
SELECT MONTH(GETDATE());  -- e.g., 12
```

**Extract Day:**
```sql
SELECT DAY(GETDATE());  -- e.g., 17
```

**Extract Hour, Minute, Second:**
```sql
SELECT DATEPART(HOUR, GETDATE());  -- e.g., 14
SELECT DATEPART(MINUTE, GETDATE());  -- e.g., 30
SELECT DATEPART(SECOND, GETDATE());  -- e.g., 45
```

**Summary of Key Functions:**
- **Working with Date/Time Types:** Use `DATE`, `TIME`, `DATETIME`, `DATETIME2`, etc.

- **Current Date/Time Functions:** Use `GETDATE()`, `SYSDATETIME()`, `GETUTCDATE()`.

- **Date Calculations:** Use `DATEADD()`, `DATEDIFF()`, `DATEPART()`.

- **Formatting:** Use `CONVERT()` and `FORMAT()` to display in specific formats.

- **Time Zone Handling:** Use `DATETIMEOFFSET` and `AT TIME ZONE` for time zone conversions.
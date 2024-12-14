# 1. The DateTime Structure
DateTime is the primary struct in C# for representing date and time values. It contains various properties and methods that allow you to manipulate, compare, and display dates and times.

## Creating DateTime Objects
**Current Date and Time:**
You can get the current date and time using DateTime.Now:

```csharp
DateTime currentDateTime = DateTime.Now;
Console.WriteLine("Current DateTime: " + currentDateTime);
// Output (example): Current DateTime: 14/12/2024 10:30:45
```
**UTC Date and Time:**
To get the current time in UTC (Coordinated Universal Time):
```csharp
DateTime currentUtcDateTime = DateTime.UtcNow;
Console.WriteLine("Current UTC DateTime: " + currentUtcDateTime);
// Output (example): Current UTC DateTime: 14/12/2024 08:30:45
```

**Specific Date:**
You can create a DateTime object for a specific date and time:

```csharp
DateTime specificDate = new DateTime(2024, 12, 14);  // year, month, day
Console.WriteLine("Specific Date: " + specificDate);
// Output: Specific Date: 14/12/2024 00:00:00
```

**Specific Date and Time:**
To specify a date and time:

```csharp
DateTime specificDateTime = new DateTime(2024, 12, 14, 10, 30, 0);  // year, month, day, hour, minute, second
Console.WriteLine("Specific DateTime: " + specificDateTime);
// Output: Specific DateTime: 14/12/2024 10:30:00
```

## Properties of DateTime
=> **Now:** Gets the current local date and time.

```csharp
DateTime now = DateTime.Now;
Console.WriteLine("Now: " + now);
// Output (example): Now: 14/12/2024 10:30:45
```

=> **UtcNow:** Gets the current date and time in UTC.
```csharp
DateTime utcNow = DateTime.UtcNow;
Console.WriteLine("UTC Now: " + utcNow);
// Output (example): UTC Now: 14/12/2024 08:30:45
```

=> **Today:** Gets the current date with the time set to 00:00:00.

```csharp
DateTime today = DateTime.Today;
Console.WriteLine("Today: " + today);
// Output (example): Today: 14/12/2024 00:00:00
```

=> **Year, Month, Day, Hour, Minute, Second:** These properties allow you to access the individual components of a DateTime object.

```csharp
int year = now.Year;
int month = now.Month;
int day = now.Day;
Console.WriteLine($"Year: {year}, Month: {month}, Day: {day}");
// Output (example): Year: 2024, Month: 12, Day: 14
```

=> **DayOfWeek:** Gets the day of the week (e.g., Monday, Tuesday).

```csharp
DayOfWeek dayOfWeek = now.DayOfWeek;
Console.WriteLine("Day of the Week: " + dayOfWeek);
// Output (example): Day of the Week: Saturday
```

=> **DayOfYear:** Gets the day of the year (1 through 365 or 366).

```csharp
int dayOfYear = now.DayOfYear;
Console.WriteLine("Day of the Year: " + dayOfYear);
// Output (example): Day of the Year: 349
```

## Methods of DateTime
=> **AddDays(int days):** Adds the specified number of days to a DateTime.

```csharp
DateTime tomorrow = now.AddDays(1);
Console.WriteLine("Tomorrow: " + tomorrow);
// Output (example): Tomorrow: 15/12/2024 10:30:45
```

=> **AddMonths(int months):** Adds the specified number of months to a DateTime.

```csharp
DateTime nextMonth = now.AddMonths(1);
Console.WriteLine("Next Month: " + nextMonth);
// Output (example): Next Month: 14/01/2025 10:30:45
```

=> **AddYears(int years):** Adds the specified number of years to a DateTime.

```csharp
DateTime nextYear = now.AddYears(1);
Console.WriteLine("Next Year: " + nextYear);
// Output (example): Next Year: 14/12/2025 10:30:45
```

=> **AddHours(double hours):** Adds hours to a DateTime.

```csharp
DateTime newTime = now.AddHours(3);
Console.WriteLine("New Time (3 hours later): " + newTime);
// Output (example): New Time (3 hours later): 14/12/2024 13:30:45
```

=> **AddMinutes(double minutes):** Adds minutes to a DateTime.

```csharp
DateTime newTime = now.AddMinutes(30);
Console.WriteLine("New Time (30 minutes later): " + newTime);
// Output (example): New Time (30 minutes later): 14/12/2024 11:00:45
```

=> **AddSeconds(double seconds):** Adds seconds to a DateTime.

```csharp
DateTime newTime = now.AddSeconds(45);
Console.WriteLine("New Time (45 seconds later): " + newTime);
// Output (example): New Time (45 seconds later): 14/12/2024 10:31:30
```

=> **Subtract(DateTime value):** Subtracts one DateTime from another, returning a TimeSpan.

```csharp
TimeSpan timeSpan = now.Subtract(specificDateTime);
Console.WriteLine("Difference: " + timeSpan);
// Output (example): Difference: 0.0000000
```

=> **ToString(string format):** Converts a DateTime to a string using the specified format.

```csharp
string dateString = now.ToString("yyyy-MM-dd HH:mm:ss");
Console.WriteLine("Formatted DateTime: " + dateString);
// Output (example): Formatted DateTime: 2024-12-14 10:30:45
```

# 2. TimeSpan Structure
TimeSpan represents a time interval. It can be used to measure the difference between two DateTime objects or represent durations.

## Creating a TimeSpan

**From Days, Hours, Minutes, and Seconds:**

```csharp
TimeSpan timeSpan = new TimeSpan(1, 2, 30, 0);  // 1 day, 2 hours, 30 minutes, 0 seconds
Console.WriteLine("TimeSpan: " + timeSpan);
// Output: TimeSpan: 1.02:30:00
```

**From Milliseconds:**
```csharp
TimeSpan timeSpanFromMilliseconds = TimeSpan.FromMilliseconds(1000);
Console.WriteLine("TimeSpan from milliseconds: " + timeSpanFromMilliseconds);
// Output: TimeSpan from milliseconds: 00:00:01
```

**From Hours and Minutes:**
```csharp
Copy code
TimeSpan timeSpanFromHoursMinutes = new TimeSpan(1, 30, 0);  // 1 hour, 30 minutes
Console.WriteLine("TimeSpan: " + timeSpanFromHoursMinutes);
// Output: TimeSpan: 01:30:00
```

## Properties of TimeSpan
**Days, Hours, Minutes, Seconds, Milliseconds:** These properties give you the components of the TimeSpan.

```csharp
int hours = timeSpan.Hours;
int minutes = timeSpan.Minutes;
Console.WriteLine($"Hours: {hours}, Minutes: {minutes}");
// Output: Hours: 2, Minutes: 30
```

**TotalDays, TotalHours, TotalMinutes, TotalSeconds, TotalMilliseconds:** These give the total value of the TimeSpan in those units, including fractional values.

```csharp
double totalHours = timeSpan.TotalHours;
Console.WriteLine("Total Hours: " + totalHours);
// Output: Total Hours: 26.5
```

## Methods of TimeSpan
**Add(TimeSpan value):**Adds two TimeSpan objects.

```csharp
TimeSpan result = timeSpan.Add(new TimeSpan(0, 1, 0, 0));  // Adds 1 hour
Console.WriteLine("Added TimeSpan: " + result);
// Output: Added TimeSpan: 1.03:30:00
```

**Subtract(TimeSpan value):** Subtracts one TimeSpan from another.
```csharp
TimeSpan result = timeSpan.Subtract(new TimeSpan(0, 0, 30, 0));  // Subtracts 30 minutes
Console.WriteLine("Subtracted TimeSpan: " + result);
// Output: Subtracted TimeSpan: 1.02:00:00
```

**Duration():** Returns the absolute value of the TimeSpan.
```csharp
TimeSpan duration = timeSpan.Duration();
Console.WriteLine("Duration: " + duration);
// Output: Duration: 1.02:30:00
```

# 3. Formatting DateTime
You can format DateTime objects into string representations in various ways using format specifiers.

## Common DateTime Format Specifiers
**d:** Short date pattern.
```csharp
string date = now.ToString("d");  // e.g., "14/12/2024"
Console.WriteLine(date);
// Output: 14/12/2024
```

**D:** Long date pattern.
```csharp
string date = now.ToString("D");  // e.g., "Saturday, December 14, 2024"
Console.WriteLine(date);
// Output: Saturday, December 14, 2024
```

**t:** Short time pattern.
```csharp
string time = now.ToString("t");  // e.g., "10:30 AM"
Console.WriteLine(time);
// Output: 10:30 AM
```

**T:** Long time pattern.
```csharp
string time = now.ToString("T");  // e.g., "10:30:45 AM"
Console.WriteLine(time);
// Output: 10:30:45 AM
```

**f:** Full date/time pattern (short date and long time).
```csharp
string full = now.ToString("f");  // e.g., "Saturday, December 14, 2024 10:30 AM"
Console.WriteLine(full);
// Output: Saturday, December 14, 2024 10:30 AM
```

**yyyy-MM-dd:** Custom format.
```csharp
string customDate = now.ToString("yyyy-MM-dd");  // e.g., "2024-12-14"
Console.WriteLine(customDate);
// Output: 2024-12-14
```

## Custom DateTime Format Strings
You can define custom date-time formats using format specifiers:

**yyyy:** Year (4 digits)
**MM:** Month (2 digits)
**dd:** Day (2 digits)
**hh:** Hour (12-hour clock)
**HH:** Hour (24-hour clock)
**mm:** Minute (2 digits)
**ss:** Second (2 digits)

```csharp
string custom = now.ToString("yyyy-MM-dd HH:mm:ss");
Console.WriteLine("Formatted DateTime: " + custom);
// Output: Formatted DateTime: 2024-12-14 10:30:45
```

# 4. Parsing and Converting DateTime
**Parse(string s):** Converts a string to a DateTime.
```csharp
DateTime parsedDate = DateTime.Parse("2024-12-14");
Console.WriteLine("Parsed Date: " + parsedDate);
// Output: Parsed Date: 14/12/2024 00:00:00
```

**TryParse(string s, out DateTime result):** Tries to convert a string to a DateTime without throwing an exception if it fails.
```csharp
bool success = DateTime.TryParse("2024-12-14", out DateTime result);
Console.WriteLine($"Success: {success}, Parsed Date: {result}");
// Output: Success: True, Parsed Date: 14/12/2024 00:00:00
```

**ParseExact(string s, string format, IFormatProvider provider):** Parses a string with a specific format.
```csharp
DateTime exactDate = DateTime.ParseExact("2024-12-14", "yyyy-MM-dd", null);
Console.WriteLine("Parsed Exact Date: " + exactDate);
// Output: Parsed Exact Date: 14/12/2024 00:00:00
```

**TryParseExact(string s, string format, IFormatProvider provider, DateTimeStyles style, out DateTime result):** Tries to parse a string with a specific format.

```csharp
bool success = DateTime.TryParseExact("14/12/2024", "dd/MM/yyyy", null, DateTimeStyles.None, out DateTime result);
Console.WriteLine($"Success: {success}, Parsed Date: {result}");
// Output: Success: True, Parsed Date: 14/12/2024 00:00:00
```

# 5. Time Zone Handling
To work with time zones, C# provides the TimeZoneInfo class.

## Getting Time Zone Information
**Get local time zone:**
```csharp
TimeZoneInfo localZone = TimeZoneInfo.Local;
Console.WriteLine("Local Time Zone: " + localZone.DisplayName);
// Output: Local Time Zone: (UTC+01:00) Brussels, Copenhagen, Madrid, Paris
```

**Get a time zone by ID:**
```csharp
TimeZoneInfo pst = TimeZoneInfo.FindSystemTimeZoneById("Pacific Standard Time");
Console.WriteLine("Pacific Standard Time: " + pst.DisplayName);
// Output: Pacific Standard Time: (UTC-08:00) Pacific Time (US & Canada)
```

## Converting Between Time Zones
**Convert local time to UTC:**
```csharp
DateTime utcDateTime = TimeZoneInfo.ConvertTimeToUtc(now);
Console.WriteLine("Converted to UTC: " + utcDateTime);
// Output (example): Converted to UTC: 14/12/2024 08:30:45
```

**Convert UTC to local time:**
```csharp
DateTime localDateTime = TimeZoneInfo.ConvertTimeFromUtc(utcDateTime, TimeZoneInfo.Local);
Console.WriteLine("Converted to Local Time: " + localDateTime);
// Output: Converted to Local Time: 14/12/2024 10:30:45
```

# 6. Common Date and Time Calculations
**Finding the Difference Between Two Dates:**
```csharp
DateTime date1 = new DateTime(2024, 1, 1);
DateTime date2 = new DateTime(2024, 12, 14);
TimeSpan difference = date2 - date1;
Console.WriteLine("Difference (Days): " + difference.Days);
// Output: Difference (Days): 348
```

**Adding/Subtracting Time:**
```csharp
DateTime newDate = DateTime.Now.AddMonths(2);
Console.WriteLine("Date after 2 months: " + newDate);
// Output (example): Date after 2 months: 14/02/2025 10:30:45
```
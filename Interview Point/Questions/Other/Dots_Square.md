## Compamy:-
Dots Square
## Location:- 
Jaipur
## Contact:- 
8851795077

## Questions

## 1. SQL Server Weekly Transaction Summary Query

I have a SQL Server table with the following columns:  
- `trn_id`  
- `trn_date`  
- `trn_amt`  

I want to write a query that allows the user to input a **"From Date"** and a **"To Date"**. The result should display the **sum of `trn_amt` grouped by each week** within that date range.

### Example:
If the user enters the date range from **01-May-2025 to 25-May-2025**, the output should group the total transaction amount week-wise:

- **Week 1**: 01–03 May (if 01-May is Thursday)  
- **Week 2**: 04–10 May  
- **Week 3**: 11–17 May  
- **Week 4**: 18–24 May  
- **Week 5**: 25-May only  

Each row in the result should show the **week number** and the **total `trn_amt`** for that week.

**Goal:**  
Write a SQL Server query to generate this weekly summary based on the user-defined date range.

## 2. jQuery Change Event Binding Order

I have the following two jQuery `change` event handlers for a dropdown with ID `#csk`:

```javascript
// 1. Direct binding
$('#csk').change(function(){
    console.log('Direct binding');
});

// 2. Delegated binding
$(document).on('change', '#csk', function(){
    console.log('Delegated binding');
});
```

## 3. C# Program to Move All Zeroes to the End of an Array

Given the following integer array in C#:

```csharp
int[] arr = { 5, 2, 1, 0, 2, 3, 0 };

//output shuld be 5,2,1,2,3,0,0
```

## 4. C# Logic to Replace Trailing Characters of a Char Array with Another Number

**Code:**
```csharp
var invArr = ("00002").ToCharArray(); // ['0', '0', '0', '0', '2']
var invCount = 25;
var invCountArr = invCount.ToString().ToCharArray(); // ['2', '5']

for (int i = invCountArr.Length - 1, j = invArr.Length - 1; i >= 0; i--, j--)
{
    invArr[j] = invCountArr[i];
}

Console.WriteLine(string.Join("", invArr)); // Output: 00025
```

## 5. Answer the below questions

1. What is a Singleton (sigltn) in programming?

2. What is the difference between TempData, ViewData, and ViewBag in ASP.NET MVC?

3. How can I validate all input fields at once using jQuery?

4. How can I validate all fields at once in a .NET DTO (Data Transfer Object)?

5. What is Dependency Injection
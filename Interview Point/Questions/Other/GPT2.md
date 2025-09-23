# .NET Coding Challenges
### 1. LINQ Queries

**Q: You have a list of employees:**

```c#
class Employee {
    public int Id { get; set; }
    public string Name { get; set; }
    public string Department { get; set; }
    public int Salary { get; set; }
}
```

- Get all employees with salary > 30,000.

- Get distinct departments.

- Get the highest paid employee in each department.

### 2. Async / Await

**Q: Write an async method in C# that calls an external API (simulate with Task.Delay) and returns a result. Show how to call it.**

### 3. EF Core

**Q: Write a query using EF Core to fetch all vendors who have more than 5 software products mapped to them.**

### 4. Web API

**Q: Write an API endpoint in .NET Core for DELETE /api/vendors/{id}.**

- It should return 404 if not found.

- Return 200 with a success message if deleted.

# SQL Coding Challenges
### 1. Joins

**Q: You have two tables:**

- Vendors (VendorId, VendorName)

- Software (SoftwareId, VendorId, SoftwareName)

- Write a query to get VendorName and total software count for each vendor.

### 2. Top N Query

**Q: Write a query to get the top 3 highest-paid employees from the Employees table.**

### 3. CTE

**Q: Write a query using a CTE to list employees and their managers in a hierarchy.**

# Angular Coding Challenges
### 1. Component Communication

**Q: Create a parent component with a list of items and a child component to display details of a selected item. Use @Input() and @Output() for communication.**

### 2. Form Validation

**Q: Create a reactive form with fields: Name, Email, Password.**

- Validate required fields.

- Email must be valid.

- Password must be at least 8 characters.

### 3. Service & HTTP

**Q: Write an Angular service that calls an API endpoint /api/vendors.**

- Use HttpClient.

- Handle error with catchError.

- Subscribe in a component and display the list of vendors.

### 4. RxJS

**Q: Demonstrate the difference between mergeMap and switchMap with API calls.**

# System Design / Scenario-Based

**Q: Suppose you are building a CRM module where a vendor can have multiple software and OS mappings.**

- How would you design the database schema?

- How would you expose APIs?

- How would you show this in Angular with a table + filters?
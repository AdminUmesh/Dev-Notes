### **What is the Page Rendering Life Cycle?**

# 🧠 ASP.NET Core Page Rendering Life Cycle (MVC / Razor Pages)

## 🔄 High-Level Flow

Request → Middleware → Routing → Controller/Page → Model Binding → View → Response

---

## ✅ Step-by-Step Life Cycle

### 1️⃣ Client Request
- User hits a URL in browser  
- Example:  

https://example.com/home/index


---

### 2️⃣ Middleware Pipeline
- Request enters ASP.NET Core pipeline
- Passes through middleware such as:
- Authentication
- Logging
- Routing
- Exception handling

👉 Configured in `Program.cs`

---

### 3️⃣ Routing
- URL is mapped to:
- Controller + Action (MVC)
- Razor Page  

Example:
```csharp
app.MapControllerRoute(
  name: "default",
  pattern: "{controller=Home}/{action=Index}/{id?}");
4️⃣ Controller / Page Execution
Controller action or Razor Page handler executes
public IActionResult Index()
{
    return View();
}

✔ Business logic runs here
✔ Data is prepared

5️⃣ Model Binding
Maps request data to method parameters
public IActionResult Save(UserModel model)

✔ Sources:

Query string
Form data
Route values
6️⃣ Action Filters (Optional)
Run before/after action execution
Examples:
Authorization
Logging
Validation
7️⃣ View Rendering
Razor View (.cshtml) is processed
Data passed via:
Model
ViewBag / ViewData
<h1>@Model.Name</h1>
8️⃣ Response Sent to Client
HTML is generated
Sent back to browser
🔁 Razor Pages Flow (Simplified)

Instead of controllers, Razor Pages use PageModel

public class IndexModel : PageModel
{
    public void OnGet() { }
}
Flow:
Request → Middleware → Razor Page → OnGet/OnPost → View → Response
⚡ Important Concepts
✔ Middleware = Backbone

All requests pass through middleware first.


2. In a LEFT JOIN, which table is considered the "left" table? In a RIGHT JOIN, which is the "right" table?

✅ LEFT JOIN
SELECT *
FROM TableA A
LEFT JOIN TableB B ON A.id = B.id;

👉 Left table = the table written BEFORE LEFT JOIN
✔ Here → TableA is the LEFT table

Behavior:
All rows from TableA are returned
Matching rows from TableB are added
If no match → NULL values from TableB
✅ RIGHT JOIN
SELECT *
FROM TableA A
RIGHT JOIN TableB B ON A.id = B.id;

👉 Right table = the table written AFTER RIGHT JOIN
✔ Here → TableB is the RIGHT table

Behavior:
All rows from TableB are returned
Matching rows from TableA are added
If no match → NULL values from TableA
🧠 Easy Trick to Remember
LEFT JOIN  → keep ALL rows from LEFT table
RIGHT JOIN → keep ALL rows from RIGHT table

3. What is the actual use of a trigger? Is it only for data validation?

4. How can we sync a new order without using `setTimeout`? (Question from KDS)
5. If we use a ring alert on a new order arrival, how can we detect if it's a new order? (Question from KDS)

6. What is a View? If I delete or rename a column from the base table, how does it affect the View?

7. What is synchronous/asynchronous processing? Why do we use it?

8. What is a token? What types of tokens have you used? Where do you store them, and how does token validation work?

9. What is a session? Where is session data stored? Can it be accessed in JavaScript?

10. What are other ways to manage session data (excluding browser storage and database)?

12. How do you validate an API?

15. 
```js
function Print(a, b) {
    a = 10;
    b = 15;

    console.log(a);
    console.log(b);
}
Print(10, 5);
// How to print original parameter values?
// How to print values after they are overridden inside the function?
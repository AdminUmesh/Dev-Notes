## **What is the Page Rendering Life Cycle (MVC / Razor Pages)**

```bash
Request → Middleware → Routing → Controller/Page → Model Binding → View → Response
```

### 1️⃣ Client Request
User hits a URL in browser  

### 2️⃣ Middleware Pipeline
Request enters ASP.NET Core pipeline and `Passes through middleware such as:`
- Authentication
- Logging
- Routing
- Exception handling  

### 3️⃣ Routing
URL is mapped to:
- Controller + Action (MVC)
- Razor Page  

**Example:**
```csharp
app.MapControllerRoute(
  name: "default",
  pattern: "{controller=Home}/{action=Index}/{id?}");
```

### 4️⃣ Controller / Page Execution
Controller action or Razor Page handler executes
```csharp
public IActionResult Index()
{
    return View();
}
```
- Business logic runs here
- Data is prepared

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

### 5️⃣ Model Binding
Maps request data to method parameters

```csharp
public IActionResult Save(UserModel model)
```

**✔ Sources:**
- Query string
- Form data
- Route values

### 6️⃣ Action Filters (Optional)
Run before/after action execution

**Examples:**
- Authorization
- Logging
- Validation

### 7️⃣ View Rendering
Razor View (.cshtml) is processed
Data passed via:
- Model
- ViewBag / ViewData
```cshtml
<h1>@Model.Name</h1>
```

### 8️⃣ Response Sent to Client
HTML is generated
Sent back to browser

**Flow:**
```bash
Request → Middleware → Razor Page → OnGet/OnPost → View → Response
```

## **2. In a LEFT JOIN, which table is considered the "left" table? In a RIGHT JOIN, which is the "right" table?**

**LEFT JOIN**
```sql
SELECT *
FROM TableA A
LEFT JOIN TableB B ON A.id = B.id;
-- Left table = the table written BEFORE LEFT JOIN
```
Here → TableA is the LEFT table

**RIGHT JOIN**
```sql
SELECT *
FROM TableA A
RIGHT JOIN TableB B ON A.id = B.id;
-- Right table = the table written AFTER RIGHT JOIN
```
Here → TableB is the RIGHT table


## **3. What is the actual use of a trigger? Is it only for data validation?**
No — triggers are NOT only for data validation. That’s actually just a small part of what they can do.

### What is a Trigger?
A trigger is a piece of code that runs automatically when a specific database event happens.
- INSERT
- UPDATE
- DELETE

### Real Uses of Triggers
**1️⃣ Audit / Logging (Most Common Use)**
- Track who changed what and when.

**2️⃣ Data Validation (Yes, but not main use)**
- You can block invalid data.

```sql
IF EXISTS (SELECT * FROM inserted WHERE Salary < 0)
BEGIN
    RAISERROR('Salary cannot be negative', 16, 1);
    ROLLBACK TRANSACTION;
END
```
But better alternatives:
```sql
Constraints (CHECK, NOT NULL)
Application validation
```

**3️⃣ Maintain Derived Data**
- Auto-update related tables.

**Example:**
Update total order amount when items change

**4️⃣ Enforce Business Rules**
- Rules that are hard to enforce with constraints.

**Example:**
- Prevent deleting a record if it's in use elsewhere

**5️⃣ Synchronization**
- Keep tables in sync.

**Example:**
When data inserted in one table → copy to another table

**6️⃣ Soft Deletes**
Instead of deleting → mark as deleted


## 4. How can we sync a new order without using setTimeout? (Question from KDS)
## 5. If we use a ring alert on a new order arrival, how can we detect if it's a new order? (Question from KDS)

### 1️⃣ WebSockets (Most Recommended)
Persistent connection between client & server
Server pushes new order instantly
const socket = new WebSocket("wss://yourserver.com");

socket.onmessage = (event) => {
  const data = JSON.parse(event.data);
  console.log("New Order:", data);
};

✔ Instant updates
✔ No delay
✔ Perfect for KDS

2️⃣ SignalR (Best for .NET Core)

If your backend is .NET → use SignalR

await Clients.All.SendAsync("NewOrder", orderData);

Angular:

connection.on("NewOrder", (order) => {
  console.log(order);
});

✔ Easy setup
✔ Built on WebSockets

3️⃣ Firebase / Real-time DB (Optional)
Auto-sync data
Good for small apps

4️⃣ Long Polling (Fallback)
Client keeps asking server repeatedly
Better than setTimeout, but still not ideal


## 6. What is a View? If I delete or rename a column from the base table, how does it affect the View?
🧠 What is a View?

A View in SQL is a virtual table based on a SELECT query from one or more tables.

👉 It does not store data physically (except indexed/materialized views in some systems)
👉 It always shows latest data from underlying tables


👉 Result:

❌ View will break

❌ Error when you query view:

Invalid column name 'Salary'


## 7. What is synchronous/asynchronous processing? Why do we use it?
🧠 What is Synchronous vs Asynchronous Processing?
✅ 1. Synchronous Processing (Sync)

👉 Tasks execute one after another
👉 Next task waits until previous completes

🔁 Flow:
Task 1 → Task 2 → Task 3
💻 Example
console.log("Start");

let data = getData(); // waits here

console.log("End");

✔ Blocks execution
✔ Simple to understand

⚠️ Problem with Sync
❌ Slow for long tasks (API calls, DB queries)
❌ UI freeze (in frontend apps)
❌ Poor scalability
🚀 2. Asynchronous Processing (Async)

👉 Tasks run without blocking
👉 Program continues execution

🔁 Flow:
Start → Task → Continue → Result comes later
💻 Example
console.log("Start");

fetchData().then(data => {
  console.log(data);
});

console.log("End");

✔ Non-blocking
✔ Faster user experience

🔄 Visual Difference
4
🎯 Why Do We Use Async?
✅ 1. Better Performance
Handle multiple tasks at once
Example: multiple API calls
✅ 2. Responsive UI (Frontend)
No freezing
Smooth user experience
✅ 3. Scalability (Backend)
Handle many users simultaneously
Important for APIs, KDS systems, real-time apps
✅ 4. Real-Time Systems
Notifications
Live updates
WebSockets / SignalR
🆚 Quick Comparison
Feature	Synchronous	Asynchronous
Execution	Sequential	Parallel / Non-blocking
Speed	Slower	Faster
UI	Can freeze	Smooth
Complexity	Easy	Slightly complex
🧠 Interview Answer (Short)

👉 Synchronous processing executes tasks one by one and waits for completion.
👉 Asynchronous processing allows tasks to run without blocking, improving performance and responsiveness.

👉 We use async processing for:

Better performance
Non-blocking UI
Handling multiple requests
Real-time applications
⚡ Real-Life Example
🧍 Sync → Standing in queue at bank
📱 Async → Ordering food on app while doing other work

## **8. What is a token? What types of tokens have you used? Where do you store them, and how does token validation work?**
A token is a secure piece of data (usually a string) used to identify and authenticate a user after login.
`Instead of sending username/password on every request, the client sends a token.`


### **Types of Tokens**
1️⃣ JSON Web Token (JWT)
Most widely used
Stateless authentication
{
  "userId": 101,
  "role": "admin",
  "exp": 1712345678
}

2️⃣ Access Token
- Short-lived
- Sent in every API request
- Authorization: Bearer <access_token>

3️⃣ Refresh Token
- Long-lived
- Used to generate new access tokens

4️⃣ Session Token (Server-based)
- Stored on server
- Client holds session ID

5️⃣ API Token / Key
Used for system-to-system communication


### Where Do We Store Tokens?

**1️⃣ LocalStorage**
localStorage.setItem("token", token);

**2️⃣ SessionStorage**
✔ Clears on tab close

**3️⃣ Cookies (Best Practice)**
HttpOnly; Secure; SameSite

## **9. What is a session? Where is session data stored? Can it be accessed in JavaScript?**

## **10. What are other ways to manage session data (excluding browser storage and database)?**
A session is a way to store user-specific data on the server across multiple requests.

**How Session Works**
```bash
Client → Request → Server creates Session → Session ID sent to client → 
Client sends Session ID → Server fetches session data
```
**Where is Session Data Stored?**

✅ 1. Server Memory (Default)
Stored in server RAM
Fast but not scalable (lost on restart)

✅ 2. Distributed Cache
Example: Redis
✔ Fast
✔ Scalable (used in production)

✅ 3. State Server (ASP.NET)
External service to store session

✅ 4. Database
Stored in SQL Server / MySQL
✔ Persistent
❌ Slower

**What is stored in browser?**
Only a Session ID (usually in cookie)


**Can Session be accessed in JavaScript?**
NO (directly) Because: Session data lives on the server. `Browser only has session ID (cookie)`
⚠️ Exception


✅ 1. In-Memory Cache
Store data in application memory
IMemoryCache cache;

✔ Fast
❌ Not shared across servers

✅ 2. Distributed Cache
Shared across servers

Example: Redis

**Other ways include:**
In-memory cache
Distributed cache (Redis)
JWT tokens (stateless)
Cookies (session ID)
TempData (ASP.NET)

## **12. How do you validate an API?**
API validation ensures that incoming requests are correct, safe, and authorized before processing.

**Validate request data (body, query, params)**
```cs
public class UserModel
{
    [Required]
    public string Name { get; set; }

    [Range(18, 60)]
    public int Age { get; set; }
}
```

**Controller:**
```cs
if (!ModelState.IsValid)
{
    return BadRequest(ModelState);
}
// Prevents invalid data
```

**Authentication Validation**
Check who the user is

- JWT Token
- OAuth
- API Key
- Authorization: Bearer <token>

✔ Ensures user is logged in

**Authorization Validation**
Check what user is allowed to do

```cs
[Authorize(Roles = "Admin")]
// Prevents unauthorized access
```

**Business Logic Validation**
```cs
if (order.Amount <= 0)
{
    return BadRequest("Invalid amount");
}
```

5️⃣ Header Validation
Validate required headers
Content-Type: application/json


6️⃣ Rate Limiting / Throttling
- Limit requests per user/IP

7️⃣ Data Type & Format Validation


## **15. How Print original parameter and internal variable**
```js
function Print(a, b) {
    a = 10;
    b = 15;
}
Print(10, 5);
```

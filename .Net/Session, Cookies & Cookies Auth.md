# Session
Session is server-side storage used to temporarily store information about a user's interaction with a website.

## Session Settings in Program.cs file

**(A) Register session services (before builder.Build())**
```c#
builder.Services.AddDistributedMemoryCache(); // where to store session (in RAM)
builder.Services.AddSession(options =>
{
    options.IdleTimeout = TimeSpan.FromMinutes(30); // session lifetime
});

```

**(B) Use session middleware (after UseRouting())**
```c#
// ✅ 2. Enable session in the pipeline
app.UseSession();

```

**Reason (simple):**

- AddDistributedMemoryCache() → where session data is stored (in RAM).

- AddSession() → tells ASP.NET Core “I want to use Session”.

- UseSession() → makes HttpContext.Session work in controllers.

- IdleTimeout = how long the session survives without any activity.

## How to Store & Get Session Values in ASP.NET Core MVC

**1. Set Session**
```c#
HttpContext.Session.SetString("MyName", "Umesh Kumar");
```

**2. Get Session**
```c#
var name = HttpContext.Session.GetString("MyName");
```

**3. Modify Session**
```c#
// Same method for add and modify:
HttpContext.Session.SetString("MyName", "Umesh Kumar");

// If "MyName" exists → it overwrites it.
// If it doesn’t exist → it creates it.
```

**4. Remove a single session key**
```c#
HttpContext.Session.Remove("MyName");
```

**5. Clear all session data**
```c#
HttpContext.Session.Clear();
```

## When Session will clear 
- 1. Session expires after inactivity (configure idle time in Program.cs)
- 2. Cleared manually
- 3. Session can also disappear when the server/app restarts
- **Note:-** Browser closing does NOT necessarily mean server Session is immediately cleared

# Cookies
Cookies are small pieces of data stored in the user's browser that help websites remember information.
- `about 4 KB (4096 bytes)`

## 1. Server-side (C#)
Server has full control.

**Server can create:**

- **Normal cookies** (Small data stored in the browser and accessible to JavaScript.)
- **HttpOnly cookies** (Cookies that JavaScript cannot access; useful for sensitive data.)
- **Secure cookies** (Cookies sent to the server only over HTTPS connections.)
- **SameSite cookies** (Control whether cookies are sent with cross-site requests.)
- **Auth cookies (encrypted, signed)** (Cookies used for authentication, usually containing encrypted or signed authentication information.)

```C#
// Normal cookies
public IActionResult SetNormalCookie()
{
    CookieOptions options = new CookieOptions
    {
        Expires = DateTime.Now.AddHours(1),
        HttpOnly = false // JS CAN access
    };

    Response.Cookies.Append("UserColor", "Blue", options);
    Response.Cookies.Append("UserName", "Umesh", options);

    return Content("Normal Cookie Created");
}

// HttpOnly cookies
public IActionResult SetHttpOnlyCookie()
{
    CookieOptions options = new CookieOptions
    {
        Expires = DateTime.Now.AddHours(1),
        HttpOnly = true, // JS CANNOT access
        Secure = false   // for local testing use false
    };

    Response.Cookies.Append("AuthToken", "xyz123", options);

    return Content("HttpOnly Cookie Created");
}

public IActionResult ReadCookies()
{
    var color = Request.Cookies["UserColor"];
    var auth = Request.Cookies["AuthToken"];

    return Content($"Color: {color}, AuthToken: {auth}");
}

public IActionResult DeleteCookies()
{
    Response.Cookies.Delete("UserColor");
    return Content("UserColor Cookie Delete Successfully");
}
```

## 2. Client-side (JavaScript)

**JavaScript can only create:**
- Normal cookies
- Secure? → only if page is HTTPS
- SameSite (maybe)
- ❌ JavaScript CANNOT create HttpOnly cookies
- ❌ JavaScript CANNOT read HttpOnly cookies

**Get all Cookies**
```js
 let allCookies = document.cookie;
 console.log(allCookies);
```

**Get Specific Cookies**
```js
function getCookie(name) {
    let cookies = document.cookie.split(";");
    for (let cookie of cookies) {
        let [key, value] = cookie.trim().split("=");
        if (key === name) return value;
    }
    return null;
}

console.log(getCookie("UserName"));
```

# Cookies Authentication🔥

**Login → create auth cookie → ASP.NET reads cookie on every request → fills HttpContext.User → [Authorize] + roles work.**

### 1. Import this 
```c#
//In Program.cs
using Microsoft.AspNetCore.Authentication.Cookies; //Usually at top:

// In Controller
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Authorization;
using System.Security.Claims;
```

### 2. After login
```c#
[HttpPost]
public async Task<IActionResult> Login(string username, string password, string? returnUrl = null)
{
    // 1. TODO: validate user from DB. Here we hardcode for demo.
    if (username != "admin" || password != "123")
    {
        ModelState.AddModelError("", "Invalid username or password");
        return View();
    }

    // 2. Build user claims
    var claims = new List<Claim>
    {
        new Claim(ClaimTypes.NameIdentifier, "1"),      // user id
        new Claim(ClaimTypes.Name, username),           // username
        new Claim(ClaimTypes.Role, "Admin")             // role
    };

    // 3. Create identity & principal
    var identity = new ClaimsIdentity(claims, CookieAuthenticationDefaults.AuthenticationScheme);
    var principal = new ClaimsPrincipal(identity);

    // 4. Sign in → this creates the authentication cookie
    await HttpContext.SignInAsync(
        CookieAuthenticationDefaults.AuthenticationScheme,
        principal);

    // 5. Redirect after login
    if (!string.IsNullOrEmpty(returnUrl) && Url.IsLocalUrl(returnUrl))
        return Redirect(returnUrl);

    return RedirectToAction("Index", "Home");
}
```

**Above code actually do**
`await HttpContext.SignInAsync("Cookies", principal);` 

**Where do we send this “token” (cookie)?**
- You don’t send it manually anywhere.
- After SignInAsync, the cookie is set in the response.
- The browser stores it.
- On every new request, the browser automatically sends:

### 3. Code in Program.cs
```c#
using Microsoft.AspNetCore.Authentication.Cookies;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllersWithViews();

// ✅ Register Cookie Authentication
builder.Services
    .AddAuthentication(CookieAuthenticationDefaults.AuthenticationScheme)
    .AddCookie(options =>
    {
        // Cookie settings
        options.Cookie.HttpOnly = true;         // JS cannot read → safer
        options.Cookie.IsEssential = true;      // always allowed

        options.LoginPath = "/Account/Login";   // where to go if not logged in
        options.LogoutPath = "/Account/Logout"; // (optional) for signout endpoint

        options.ExpireTimeSpan = TimeSpan.FromMinutes(15); // base lifetime
        options.SlidingExpiration = true;        // extend if active
    });

builder.Services.AddAuthorization();

var app = builder.Build();

app.UseStaticFiles();
app.UseRouting();

// ✅ Add authentication & authorization middlewares
app.UseAuthentication();   // reads cookie → fills HttpContext.User
app.UseAuthorization();    // applies [Authorize]

app.MapDefaultControllerRoute();

app.Run();

```

**Meaning**
`AddAuthentication` + `AddCookie`
- Register cookie-based authentication scheme called "Cookies".

`UseAuthentication()`
- For every request, try to authenticate using the cookie.
- If cookie valid → fills HttpContext.User.

`UseAuthorization()`
- Checks [Authorize] attributes using HttpContext.User.

**The cookie has an expiry time inside it, based on:**

`options.ExpireTimeSpan = TimeSpan.FromMinutes(15);`
`options.SlidingExpiration = true;`
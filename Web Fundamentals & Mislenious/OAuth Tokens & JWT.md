# 🔥 OAuth
- OAuth (pronounced “oh-auth”) stands for Open Authorization.
- It is not “authentication” — it is mainly authorization.
- It is used everywhere — Google Login, Facebook Login, GitHub Login, etc.

**OAuth allows one application to access limited data from another application without sharing your password.**

**Example:**
- You want to log in to a website using Google Login.

- You click “Login with Google”.

- The website does not get your Google password.

- Google only sends your basic profile info with your permission.

## What is authentication?
Authentication is the process of verifying the identity of a user, system, or application.

`Authentication means (WHO are you?)`

- Login

- Identity verification

- “Is this user really the person they claim to be?”

**Example:**

- User enters username/password → system checks credentials → OK

- User is now authenticated

**Result:**
HttpContext.User is filled with the user’s claims.
(Claims = Name, UserId, Email, Roles…)

## What is authorization?
Authorization means (WHAT are you allowed to do?)

- After the user is authenticated Check permissions, roles, policies `“Does this user have access to this action?”`

**Example:**

- Is this user an Admin?

- Does this user have EditProduct permission?

- Is he allowed to access /AdminPanel?

---

# 🔥 Token
A token is just a string (text) given by the server to the client after login.
It acts like a temporary ID card.

## Why do we use a Token?

So the user does not need to send username/password on every request.

Instead:

- login once → get token

- use token for all further requests

## Types of Token

**JWT (JSON Web Token)- Stateless**
- Server does NOT store session information.
- Each request must carry all required data

**📌 How it works**
- User logs in → server sends back a token (e.g., JWT).
- Server does NOT store this token.
- On every request, the client sends the token.

Server only verifies the token using a secret key — no database lookup.

**Session Token- Stateful**
Like opaque tokens but tied to server-side session.

**📌 How it works**
- User logs in → server creates a session entry in DB/Redis/RAM (server memory).
- Server returns a session ID or opaque token.
- On each request, server checks DB/Redis//RAM (server memory) to validate session.

**Opaque Token**
Just a random string — server stores the data in DB/Redis.

---

## Implement Session Token
**Add the below in Program.cs**

```c#
builder.Services.AddAuthentication("Cookies")
    .AddCookie(options => {
        options.Cookie.HttpOnly = true;
        options.Cookie.IsEssential = true;
        options.LoginPath = "/Account/Login";
        options.LogoutPath = "/Account/Logout";
        options.ExpireTimeSpan = TimeSpan.FromMinutes(30);
    });
```

**Add middleware:**
```c#
//This two configures the request pipeline
app.UseAuthentication();
// Tries to read the auth cookie / token from the request.
// If found and valid, it sets HttpContext.User (ClaimsPrincipal).

app.UseAuthorization();
// Uses HttpContext.User to check [Authorize], roles, policies, etc.
// If user is not allowed → returns 401/403 or redirects to login.
```

**In your login method:**
```c#
await HttpContext.SignInAsync("Cookies", principal);
```

**Protect routes:**
```c#
[Authorize] //or
[Authorize(Roles = "Admin,Manager")]
```

**Add Logout method:**
```c#
await HttpContext.SignOutAsync("Cookies");
```
---

## Where session data is stored (common options)

ASP.NET Core sessions use the **Distributed Cache** system.
`Depending on which storage you want, you register a different **Distributed Cache provider**.`

## ✅ Providers Overview

| Storage Type            | Provider Name                        | Program.cs Registration                  | Notes                                   |
|-------------------------|----------------------------------------|--------------------------------------------|------------------------------------------|
| **In-process RAM**     | DistributedMemoryCache                 | `AddDistributedMemoryCache()`             | ✔ Fast, stored in server RAM (dev only) |
| **SQL Database**       | DistributedSqlServerCache              | `AddDistributedSqlServerCache()`          | ✔ Durable, works with multiple servers  |
| **Redis**              | StackExchangeRedisCache                | `AddStackExchangeRedisCache()`            | ✔ Best for production, fast + scalable  |
| **File System**        | *No built-in provider*                 | *Custom or via NuGet package*             | ❌ Not recommended for high load         |
| **Stateless (JWT/Cookie)** | *No session store needed*          | *N/A*                                      | ✔ Client stores state (JWT/cookies)     |

---

**🟢 1. In-Process Memory (Default for Development)**

Stores session data in **server RAM**.

```csharp
builder.Services.AddDistributedMemoryCache();
builder.Services.AddSession();
```


## Miscelenious Points (Session Toke)

```c#
// In Session-Cookie Setup in Program.cs
options.Cookie.HttpOnly = true;
```

- Browsers normally let JavaScript read cookies using document.cookie.

- When HttpOnly = true: JavaScript cannot read or modify that cookie using document.cookie.

- The cookie is still sent with every HTTP request to the server.

- This protects your auth cookie from XSS attacks:

- If some malicious script runs on your page, it can’t steal the auth cookie.

**So:**

- ✅ Good: Server still receives the cookie.

- ✅ Good: JS cannot read the cookie.

---

# 🔥 JWT (JSON Web Token)?
- **JWT** = JSON Web Token  
- A **signed string** that contains user info (claims).  
- Used for **stateless authentication**: server does **not** store session.

In your **controller / token service** Imports:

```csharp
using System;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using Microsoft.IdentityModel.Tokens;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
```

In **Program.cs**:

```csharp
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using System.Text;
```

---

## ⚙ Register JWT in Program.cs

```csharp
var builder = WebApplication.CreateBuilder(args);

var key = builder.Configuration["Jwt:Key"];
var issuer = builder.Configuration["Jwt:Issuer"];
var audience = builder.Configuration["Jwt:Audience"];

builder.Services
    .AddAuthentication(options =>
    {
        options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
        options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
    })
    .AddJwtBearer(options =>
    {
        options.TokenValidationParameters = new TokenValidationParameters
        {
            ValidateIssuer = true,
            ValidateAudience = true,
            ValidateLifetime = true,
            ValidateIssuerSigningKey = true,
            ValidIssuer = issuer,
            ValidAudience = audience,
            IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(key)),
            ClockSkew = TimeSpan.Zero,
            RoleClaimType = ClaimTypes.Role
        };
    });

builder.Services.AddAuthorization();
builder.Services.AddControllers();
```

---

## 🔑 Generate JWT After Login

```csharp
private string GenerateJwtToken(string userId, string username, string role)
{
    var key = _config["Jwt:Key"];
    var issuer = _config["Jwt:Issuer"];
    var audience = _config["Jwt:Audience"];

    var securityKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(key));
    var credentials = new SigningCredentials(securityKey, SecurityAlgorithms.HmacSha256);

    var claims = new[]
    {
        new Claim(JwtRegisteredClaimNames.Sub, userId),
        new Claim(ClaimTypes.Name, username),
        new Claim(ClaimTypes.NameIdentifier, userId),
        new Claim(ClaimTypes.Role, role)
    };

    var token = new JwtSecurityToken(
        issuer: issuer,
        audience: audience,
        claims: claims,
        expires: DateTime.UtcNow.AddMinutes(15),
        signingCredentials: credentials
    );

    return new JwtSecurityTokenHandler().WriteToken(token);
}
```

---

## ⏰ Token Refresh (extend time)
**Why its Important?**
- Without Refresh token in JWT it auto expired with ideal set time by you **Ex-** after 10 minutes it expire automaticly.
- To extend time, issue a **new token** using a **refresh token flow**.

### 1️⃣ No refresh token (what you did)**

- You only have one JWT.

- When it expires, the user must log in again.

- You usually set a longer lifetime (e.g., 1–2 hours or even more).

**❌ But:**

- If someone steals the token, they can use it until it expires.

- If you set very short expiry (like 5–10 min), user gets logged out too often.
---

### 2️⃣ With refresh token (more advanced)

- Access token = short (10–15 min)

- Refresh token = long (days/weeks)

- When access token expires → client silently gets a new one using refresh token.

## 🚪 Logout
Client deletes token.
---

### Que-1: I want to build an application where a user is automatically logged out after 15 minutes of inactivity. However, if the user is continuously active, their session should remain valid without interruption. Which authentication mechanism or concept is best suited to achieve this behavior?

`Use Cookie-based Authentication with Sliding Expiration` (NOT plain JWT-only)

**Because:**
- Cookie auth in ASP.NET Core already supports:
- “Logout after X minutes of inactivity”
- “Extend time automatically while user is using the app”

**`JWT alone is not good for idle-based logout (it only knows absolute expiry, not activity) unless you build a more complex refresh-token system.`**
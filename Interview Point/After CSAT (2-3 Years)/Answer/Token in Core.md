# What is a Token?
A token is a digitally signed piece of data used to authenticate and authorize a user without sending their username and password on every request.

`Think of it like an entry pass — once you log in, the server gives you a token; you present it on every request to prove who you are.`

# Types of Tokens in .NET Core

## 1. JWT (JSON Web Token)
- **Purpose**: The most common; self-contained with user claims, signed with a secret or certificate.
- **Usage**: Sent with requests to prove identity and access rights.
- **Example**: `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...`

---

## 2. Bearer Token
- **Purpose**: A general term for tokens sent in the `Authorization: Bearer <token>` header.
- **Usage**: Commonly used with JWT, but can be any token format.

---

## 3. Refresh Token
- **Purpose**: A long-lived token to get new access tokens without logging in again.
- **Usage**: Stored securely (usually in a database) and exchanged for a new access token when the old one expires.

---

## 4. Opaque Token
- **Purpose**: Random strings that have meaning only on the server (not readable like JWT).
- **Usage**: Requires server lookup to get associated user/session data.

---

# Use of Token in .NET Core
- Tokens are used in Authentication & Authorization.

- Mostly in JWT Bearer Authentication for APIs.

**Steps:**

- User logs in with username/password.

- Server validates and issues Access Token (and optionally a Refresh Token).

- Client sends token on each request in HTTP header.

- Server validates token and processes request.

# Where to Store Tokens
**Possible storage options for web apps:**

- HTTP-Only Cookies (more secure, not accessible by JavaScript, protected against XSS)

- LocalStorage / SessionStorage (easy to use, but vulnerable to XSS)

- In-memory storage (most secure for short-lived tokens, but lost on page refresh)

**Best Practice:**

- Store access tokens in memory or HTTP-only cookies.

- Store refresh tokens only in secure HTTP-only cookies.

- Never store sensitive tokens in plain localStorage if security is critical.
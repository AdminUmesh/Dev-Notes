# What is a Token in Angular?
A token is a string (often digitally signed) used by the Angular application to prove the user's identity to the backend API without sending their username and password each time.

`Think of it like a movie ticket — you get it once at login, and show it for every API call until it expires.`

---

# Types of Tokens in Angular

## 1. JWT (JSON Web Token)
- **Purpose**: Most commonly used in Angular apps for authentication and authorization.
- **Usage**: Sent in HTTP headers with API requests to prove identity.
- **Example**: `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...`

---

## 2. Bearer Token
- **Purpose**: General term for tokens sent in the `Authorization: Bearer <token>` HTTP header.
- **Usage**: Can be JWT or any other token type.

---

## 3. Refresh Token
- **Purpose**: A long-lived token to get new access tokens without forcing the user to log in again.
- **Usage**: Usually stored securely (e.g., in an HTTP-only cookie) and sent to a refresh endpoint.

---

## 4. Opaque Token
- **Purpose**: Random string with no readable data; only the server can interpret it.
- **Usage**: Requires a server-side lookup to validate.

---

# How Tokens are Used in Angular
Tokens are typically used for **API authentication**.

**Common Flow:**
1. User logs in from Angular app.
2. Angular sends credentials to backend.
3. Backend issues an Access Token (and optionally a Refresh Token).
4. Angular stores the token (securely) and attaches it to every HTTP request to the API.
5. API validates the token and returns the response.

---

# Where to Store Tokens in Angular

**Options:**
- **HTTP-Only Cookies**: Secure, prevents XSS attacks (recommended for high security).
- **LocalStorage**: Easy to use, persists after refresh, but vulnerable to XSS.
- **SessionStorage**: Similar to localStorage, but cleared when the tab is closed.
- **In-memory Variables**: Most secure for short-lived tokens; lost on refresh.

---

## Best Practices:
- **Access Tokens**: Store in memory or HTTP-only cookies.
- **Refresh Tokens**: Store only in secure HTTP-only cookies.
- **Avoid**: Storing tokens in plain localStorage if security is critical.
- Use Angular **HTTP Interceptors** to automatically attach tokens to requests.
- Always validate tokens server-side.

---

# Example: Attaching Token with HTTP Interceptor
```ts
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = localStorage.getItem('access_token'); // Example: get token from storage
    if (token) {
      const cloned = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${token}`)
      });
      return next.handle(cloned);
    }
    return next.handle(req);
  }
}

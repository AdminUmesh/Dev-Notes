# HTTP headers
HTTP headers are metadata in the form of key-value pairs sent along with both requests and responses in HTTP communication.

`They help with caching, authentication, state management, and more.`

## Types of HTTP Headers

- **Request Headers:** These headers are sent by the client (browser, API client) in an HTTP request. They convey information about the request or the client itself.

- **Response Headers:** These headers are sent by the server in an HTTP response. They provide metadata about the server's response, including status codes, content type, and more.

- **Representation Headers:** These headers help with encoding and decoding the body of the request or response. They define how the body content is represented (e.g., JSON, XML).

- **Payload Headers:** These headers are related to the data or the payload being transmitted in the body of the HTTP message. They help describe the data being sent.

## Common HTTP Headers
**Accept:** Specifies the media types the client is willing to receive.

**Example:**
```js
Accept: application/json
```

**User-Agent:** Provides information about the client software making the request (e.g., browser, mobile app).

**Example:**
```JS
User-Agent: Mozilla/5.0
```

## HTTP Status Codes Overview
The HTTP status code is a part of the response headers and indicates the result of the HTTP request. It helps clients understand the outcome of their request (e.g., success, failure, redirection).

- **1xx:** Informational responses
- **2xx:** Successful responses
- **3xx:** Redirection responses
- **4xx:** Client error responses
- **5xx:** Server error responses

## 1xx - Informational Responses
**100 Continue**

- **Meaning:** The server has received the initial part of the request and the client should continue with the rest of the request.
- **Use case:** This is sent when a client sends a request with a Expect: 100-continue header, telling the server that it should wait before sending the full request body.

**102 Processing**

- **Meaning:** The server has received and is processing the request, but no response is available yet.
- **Use case:** This code is used when a request takes a long time to process, and the server needs to let the client know that the request is being handled.

## 2xx - Successful Responses
**200 OK**

- **Meaning:** The request has been successfully processed by the server, and the server is returning the requested data (if applicable).
- **Use case:**  Common response for successful GET requests.

**201 Created**

- **Meaning:** The request has been successfully processed, and a new resource has been created as a result.
- **Use case:** Typically used for successful POST requests, such as when a new resource (e.g., user, post, etc.) is created.

**202 Accepted**

- **Meaning:** The request has been accepted for processing, but the processing is not yet complete.
- **Use case:** It’s used when a request has been received and accepted but will be processed asynchronously.

## 3xx - Redirection Responses
**307 Temporary Redirect**

- **Meaning:** TThe requested resource has temporarily moved to a different URL, but the client should continue using the original URL for future requests.
- **Use case:** Often used when a resource is temporarily moved for maintenance or other reasons.

**308 Permanent Redirect**

- **Meaning:** he requested resource has permanently moved to a new URL, and future requests should be directed to the new URL.
- **Use case:** This is similar to 301 Moved Permanently, but unlike 301, the method and body of the request should not change when following the redirect.

## 4xx - Client Error Responses
**400 Bad Request**

- **Meaning:** The server cannot process the request due to malformed syntax or invalid data.
- **Use case:** Sent when the client sends a request that is not understood by the server (e.g., bad JSON in an API request).

**401 Unauthorized**

- **Meaning:** The request requires authentication, and the client has not provided valid credentials or is not authorized to access the resource.
- **Use case:** Common when authentication (e.g., via a username and password) is required to access a resource.

**402 Payment Required**

- **Meaning:** This status code is reserved for future use. It is not commonly used in practice but was intended to indicate that payment is required to access the resource.
- **Use case:** Reserved for future use, typically for cases involving paid access or microtransactions.

**404 Not Found**

- **Meaning:** The server cannot find the requested resource. The URL might be incorrect, or the resource does not exist.
- **Use case:** The most common error indicating that the resource requested does not exist on the server.

## 5xx - Server Error Responses
**500 Internal Server Error**

- **Meaning:** The server encountered an unexpected condition that prevented it from fulfilling the request. This is a general error when no more specific error message is available.
- **Use case:** A catch-all error when something goes wrong on the server.

**501 Not Implemented**

- **Meaning:** The server does not support the functionality required to fulfill the request. The server cannot process the method or feature used in the request.
- **Use case:** Sent when the server does not recognize the request method or cannot implement it.
**Example:** If a server does not support a specific HTTP method (e.g., TRACE), it may return a 501 Not Implemented error.

## Example HTTP Request and Response

**Request**
```js
GET /index.html HTTP/1.1
Host: www.example.com
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/91.0
Accept: application/json
Connection: keep-alive
```

**Response**
```js
HTTP/1.1 200 OK
Date: Wed, 21 Oct 2020 07:28:00 GMT
Server: Apache/2.4.29 (Ubuntu)
Content-Type: text/html; charset=UTF-8
Content-Length: 1024
Cache-Control: max-age=3600
```
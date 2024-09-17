# Call API using C#
To call an API using C#, you can use the `HttpClient` class, which is part of the `System.Net.Http` namespace.

## Making a GET Request
```c#
using System;
using System.Net.Http;
using System.Threading.Tasks;

class Program
{
    static async Task Main(string[] args)
    {
        using (HttpClient client = new HttpClient())
        {
            // Define the base address for the API
            client.BaseAddress = new Uri("https://api.example.com/");

            try
            {
                // Send a GET request
                HttpResponseMessage response = await client.GetAsync("endpoint");

                // Check if the response is successful
                response.EnsureSuccessStatusCode();

                // Read the response content as a string
                string responseBody = await response.Content.ReadAsStringAsync();

                Console.WriteLine(responseBody);
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Request error: {e.Message}");
            }
        }
    }
}
```

## Making a POST Request
```c#
using System;
using System.Net.Http;
using System.Threading.Tasks;

class Program
{
    static async Task Main(string[] args)
    {
        using (HttpClient client = new HttpClient())
        {
            // Define the base address for the API
            client.BaseAddress = new Uri("https://api.example.com/");

            try
            {
                // Send a GET request
                HttpResponseMessage response = await client.GetAsync("endpoint");

                // Check if the response is successful
                response.EnsureSuccessStatusCode();

                // Read the response content as a string
                string responseBody = await response.Content.ReadAsStringAsync();

                Console.WriteLine(responseBody);
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Request error: {e.Message}");
            }
        }
    }
}
```

# Making a PUT Request
```c#
using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

class Program
{
    static async Task Main(string[] args)
    {
        using (HttpClient client = new HttpClient())
        {
            client.BaseAddress = new Uri("https://api.example.com/");

            var putData = new StringContent("{\"key\":\"newValue\"}", Encoding.UTF8, "application/json");

            try
            {
                // Send a PUT request
                HttpResponseMessage response = await client.PutAsync("endpoint", putData);

                response.EnsureSuccessStatusCode();

                string responseBody = await response.Content.ReadAsStringAsync();

                Console.WriteLine(responseBody);
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Request error: {e.Message}");
            }
        }
    }
}
```
## Making a DELETE Request
```c#
using System;
using System.Net.Http;
using System.Threading.Tasks;

class Program
{
    static async Task Main(string[] args)
    {
        using (HttpClient client = new HttpClient())
        {
            client.BaseAddress = new Uri("https://api.example.com/");

            try
            {
                // Send a DELETE request
                HttpResponseMessage response = await client.DeleteAsync("endpoint");

                response.EnsureSuccessStatusCode();

                // Read the response content if needed
                string responseBody = await response.Content.ReadAsStringAsync();

                Console.WriteLine(responseBody);
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Request error: {e.Message}");
            }
        }
    }
}
```

# Sending Data in the Request Body & Request Headers

## Sending JSON Data in the Request Body
```c#
using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

class Program
{
    static async Task Main(string[] args)
    {
        using (HttpClient client = new HttpClient())
        {
            client.BaseAddress = new Uri("https://api.example.com/");

            // Create JSON data to send in the request body
            var jsonData = new StringContent("{\"key\":\"value\"}", Encoding.UTF8, "application/json");

            try
            {
                // Send a POST request with the JSON data in the body
                HttpResponseMessage response = await client.PostAsync("endpoint", jsonData);

                response.EnsureSuccessStatusCode();

                // Read the response content
                string responseBody = await response.Content.ReadAsStringAsync();
                Console.WriteLine(responseBody);
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Request error: {e.Message}");
            }
        }
    }
}
```

## Sending Data in the Request Headers
using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
```c#
class Program
{
    static async Task Main(string[] args)
    {
        using (HttpClient client = new HttpClient())
        {
            client.BaseAddress = new Uri("https://api.example.com/");

            // Prepare the request content
            var jsonData = new StringContent("{\"key\":\"value\"}", Encoding.UTF8, "application/json");

            // Create the request message
            var requestMessage = new HttpRequestMessage(HttpMethod.Post, "endpoint")
            {
                Content = jsonData
            };

            // Add custom headers
            requestMessage.Headers.Add("Custom-Header", "HeaderValue");
            requestMessage.Headers.Add("Another-Header", "AnotherValue");

            try
            {
                // Send the request
                HttpResponseMessage response = await client.SendAsync(requestMessage);

                response.EnsureSuccessStatusCode();

                // Read the response content
                string responseBody = await response.Content.ReadAsStringAsync();
                Console.WriteLine(responseBody);
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Request error: {e.Message}");
            }
        }
    }
}
```

# Sending Data in Both Body and Headers
```c#
using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

class Program
{
    static async Task Main(string[] args)
    {
        using (HttpClient client = new HttpClient())
        {
            client.BaseAddress = new Uri("https://api.example.com/");

            // Prepare the request content
            var jsonData = new StringContent("{\"key\":\"value\"}", Encoding.UTF8, "application/json");

            // Create the request message
            var requestMessage = new HttpRequestMessage(HttpMethod.Post, "endpoint")
            {
                Content = jsonData
            };

            // Add custom headers
            requestMessage.Headers.Add("Authorization", "Bearer your_access_token");
            requestMessage.Headers.Add("Custom-Header", "HeaderValue");

            try
            {
                // Send the request
                HttpResponseMessage response = await client.SendAsync(requestMessage);

                response.EnsureSuccessStatusCode();

                // Read the response content
                string responseBody = await response.Content.ReadAsStringAsync();
                Console.WriteLine(responseBody);
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine($"Request error: {e.Message}");
            }
        }
    }
}
```

**Key Points:**

- **Request Body:** Use classes derived from HttpContent (e.g., StringContent, FormUrlEncodedContent, JsonContent) to include data in the request body.
- **Request Headers:** Use the Headers property of HttpRequestMessage to add custom headers, or set default headers on the HttpClient instance.
- **HttpClient:** HttpClient should ideally be reused for multiple requests to take advantage of connection pooling.

## Request Body Vs Request Header

**Containt**
- **Body:** Contains the main data or payload of the request.
- **Headers:** Contains metadata and additional information about the request.

**Size and Complexity:**
- **Body:** Can be large and complex, suitable for substantial data payloads.
- **Headers:** Generally small and consist of simple key-value pairs.

**Visibility:**
- **Body:** Sent in the body of the HTTP request and not visible in URLs.
- **Headers:** Sent in the header section of the HTTP request, visible and accessible via request inspection tools.
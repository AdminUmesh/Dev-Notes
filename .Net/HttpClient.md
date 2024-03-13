
HttpClient class use for sending HTTP requests and receiving HTTP responses from a resource identified by a URI.

- Namespace: System.Net.Http
- Assembly: System.Net.Http.dll

# Steps for HttpClint

1. Create the Http Clint(Create the object)
```c#
HttpClient client = new HttpClient();
```
2. Create the Request
3. Execute the request
4. Receive the response
5. Extract the required information from response
6. Close the Htttp client and dependent Resources
```c#
HttpClient client = new HttpClient();
client.Dispose(); //close the connection
```

# GetAsync Method
```c#
private string getUrl = "https://localhost:8080/laptop-bag/webapi/api/all"

//public Task<HttpResponseMessage> GetAsync(string requestUri);
[TestMethod]
public void GellAllItems(){
    HttpClient client = new HttpClient();
    client.GetAsync(getUrl);
    client.Dispose(); //close the connection
}

// public Task<HttpResponseMessage> GetAsync(Uri requestUri);
[TestMethod]
public void GellAllItems(){
    HttpClient client = new HttpClient();
    Uri URL =new uri(getUrl)
    client.GetAsync(URL);
}       
```

# How to Handle Response??

```c#
private string getUrl = "https://localhost:8080/laptop-bag/webapi/api/all"

public void GellAllItems(){
    HttpClient client = new HttpClient();
    Task<HttpResponseMessage> Response= client.GetAsync(getUrl); //because return type of HttpClient is Task<HttpResponseMessage>
    HttpResponseMessage Result =Response.Result;
    Console.WriteLine(Result.ToString()); //to string will convert the object into string
}       
```

- **Headers:-** Additional information which are attach with response

- **StatusCode:-** define the status of request

-  **Content:-** The response data.

## StatusCode
```c#
public void GellAllItems(){
    HttpClient client = new HttpClient();
    Task<HttpResponseMessage> Response= client.GetAsync(getUrl);

    HttpResponseMessage HttpResponseMessage =Response.Result;
    Console.WriteLine(HttpResponseMessage.ToString());

    //status code
    HttpStatusCode HttpStatusCode= HttpResponseMessage.StatusCode;
    Console.Writeline(HttpStatusCode); //result like 'OK' or 'not found'
    Console.Writeline((int)HttpStatusCode); //result like 200 or 404
}       
```

## Content
```c#
public void GellAllItems(){
    HttpClient client = new HttpClient();
    Task<HttpResponseMessage> Response= client.GetAsync(getUrl);

    HttpResponseMessage HttpResponseMessage =Response.Result;
    Console.WriteLine(HttpResponseMessage.ToString());

    //response data
    HttpContent ResponseContent= HttpResponseMessage.Content;
    Task<string> responseData =ResponseContent.ReadAsStringAsync(); //to read the content from resourse
    string data = responseData.result;

    Console.Writeline(data);
}        
```
**Response Data Format**
 1.  XML formate (by default)
 2. JSON formate

**Return data in specific format**
- `header are in the form of key-value pair`
```c#
public void GellAllItems(){
    HttpClient client = new HttpClient();
    HttpRequestHeaders requestHeaders=  client.DefaultRequestHeaders;
    requestHeaders.add("accept", "application/json"); //for json result
    requestHeaders.add("accept", "application/xml"); //for xml result
    Task<HttpResponseMessage> Response= client.GetAsync(getUrl);

    HttpResponseMessage HttpResponseMessage =Response.Result;
    Console.WriteLine(HttpResponseMessage.ToString());

    //response data
    HttpContent ResponseContent= HttpResponseMessage.Content;
    Task<string> responseData =ResponseContent.ReadAsStringAsync(); //to read the content from resourse
    string data = responseData.result;

    Console.Writeline(data);
    client.Dispose();
}        
```
## rewatch #10 video
PutAsync

PostAsync

DeleteAsync

GetByteArrayAsync

GetStreamAsync

SendAsync

public Uri BaseAddress { get; set; }

public HttpRequestHeaders DefaultRequestHeaders { get; }

public TimeSpan Timeout { get; set; }

public void CancelPendingRequests();
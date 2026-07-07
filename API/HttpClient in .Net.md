
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

## Get data in JSON / XML
```c#
public void Get_data_in_JSON_XML(){
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

## Another Way to Change Response data in JSON/XML
```c#
public void Another_Way_to_Change_in_json_response(){
    MediaTypeWithQualityHeaderValue jsonHeader = new MediaTypeWithQualityHeaderValue("application/json");
    HttpRequestHeaders requestHeaders=  client.DefaultRequestHeaders;
    HttpClient client = new HttpClient();
    requestHeaders.accept.add(jsonHeader);

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

## Request Using SendAsync()
```c#
public void Using_SendAsync(){
    // SendAsync take parameter as HttpRequestMessage
    HttpRequestMessage HttpRequestMessage= new HttpRequestMessage();
    HttpRequestMessage.RequestUri = new Uri(getUrl);
    HttpRequestMessage.Method = HttpMethod.Get;
    HttpRequestMessage.Method.Header.Add("accept", "application/json");
    HttpClient client = new HttpClient();
    Task<HttpResponseMessage> httpResponse =client.SendAsync(HttpRequestMessage);

    HttpResponseMessage HttpResponseMessage =httpResponse.Result;
    Console.WriteLine(HttpResponseMessage.ToString());

    //response data
    HttpContent ResponseContent= HttpResponseMessage.Content;
    Task<string> responseData =ResponseContent.ReadAsStringAsync(); //to read the content from resourse
    string data = responseData.result;

    Console.Writeline(data);
    client.Dispose();
}        
```

## use of Using 
```c#
public void Using_Statemennt(){
    // Using syntax close connection automatically
    using (HttpClint client = new HttpClient())
    {
        using(HttpRequestMessage httpRequestMessage = new HttpRequestMessage())
        {
          // You don't need httpClient.Dispose();
        }
    }
} 
```

## Make Request view Model 
```c#
public void With_Model(){
    HttpRequestMessage HttpRequestMessage= new HttpRequestMessage();
    HttpRequestMessage.RequestUri = new Uri(getUrl);
    HttpRequestMessage.Method = HttpMethod.Get;
    HttpRequestMessage.Method.Header.Add("accept", "application/json");
    HttpClient client = new HttpClient();
    Task<HttpResponseMessage> httpResponse =client.SendAsync(HttpRequestMessage);

    HttpResponseMessage HttpResponseMessage =httpResponse.Result;
    
    HttpContent ResponseContent= HttpResponseMessage.Content;
    Task<string> responseData =ResponseContent.ReadAsStringAsync();
    string data = responseData.result;
     
    // get response here using Model
    RestResponse restResponse = new RestResponse((int)StatusCode, ResponseData.Rusult);
}     

public class RestResponse
{
    private int StatusCode;
    private string responseData;

    public restResponse(int StatusCode, string responseData)
    {
        this.StatusCode = StatusCode;
        this.responseData = responseData;
    }

    public int StatusCode
    {
        get
        {
            return StatusCode;
        }
    }

    public int responseData
    {
        get
        {
            return responseData;
        }
    }

    public override string ToString(){
        return String.Format("StatsCode : {0} ResponseData: {1}", StatusCode, responseData);
    }
}
```

# Serialization / Deserialization

- Serialization, it's a process of converting the state of object to byte stream

- Deserialization, it's a process of retriving the object from the byte stream

# Future Work
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
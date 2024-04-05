# Get Request
```javaScript
// GET example

$.ajax({
    url: '/api/data',  // URL to send the request
    url: "../Home/GetOrderDetail?tblno=" + id,
    type: 'GET',        // HTTP method (GET, POST, etc.)
    dataType: 'json',   // Expected data type of the response
    timeout: 2000, //milliseconds
    success: function(data) {
        // Callback function to handle a successful response
        console.log('Data received:', data);
    },
    error: function(xhr, status, error) {
        // Callback function to handle an error response
        console.error('Error:', status, error);
    }
});
```

1. **url:** The URL to which the request will be sent.

2. **type:** The HTTP method to use for the request (e.g., 'GET', 'POST'). 

3. **dataType:** The expected data type of the response. jQuery will try to intelligently convert the response based on this.

4. **value** (e.g., 'json', 'xml', 'html').

5. **success:** A callback function that is executed if the request succeeds. The data parameter contains the response from the server.

6. **error:** A callback function that is executed if the request fails. The xhr, status, and error parameters provide additional information about the failure.

7. **timeout** If the server doesn't respond within this time frame, the error callback will be invoked

# Post Request

```javaScript
// POST example
const Array = [];
var obj = {
     OrderNo: localStorage.getItem("OrderNo"),
     OrderNo2: localStorage.getItem("OrderNo2"),
     TableNo: '5',
     itemdata: Array,
     OrderType: sessionStorage["orderplacetype"],
     RSUM_CVR: sessionStorage["cvrVal"],
     RSUM_AMT: total,
     RSUM_AMT2: total2,
     ordertyp: 'R'
}

$.ajax({
    url: '../home/OrderSplit',
    type: "POST",
    dataType: "json",
    contentType: 'application/json',
    data: JSON.stringify(obj), //or another type
    data: {
        param1: 'value1',
        param2: 'value2'
    },
    success: function(data, status, xhr) {
        console.log('Data received:', data);
    },
    error: function(xhr, status, error) {
        console.error('Error:', status, error);
    },
    beforeSend: function(xhr) {
        // Code to execute before the request is sent
        console.log('Sending request...');
    },
    complete: function(xhr, status) {
        // Code to execute regardless of success or failure
        console.log('Request completed.');
    }
});
```

1. **data:** This option is used to send data with the request. It can be a query string or an object. jQuery will handle the serialization.

2. **beforeSend:** A callback function that is executed before the request is sent.

3. **complete:** A callback function that is executed after the request completes, regardless of success or failure.

# Async and await Ajax Request 

```js
async function TableForSplit() {
    await $.ajax({
        success: async function (data1) {

            // Make another async request after success
            const data = await $.ajax({
            url: "../Home/GetSplitData?BillNo=" + OrderNo,
            type: "GET"
            });
        }
    });
}
```

# async: true/false
- By default, when you make an AJAX request with async: true, the request is asynchronous. This means that the script will send the request to the server and continue its execution without waiting for the response.

- When you set async: false, the AJAX call becomes synchronous

```js
$.ajax({
    url: 'https://api.example.com/data',
    method: 'GET',
    dataType: 'json',
    async: false, // Deprecated in newer versions of jQuery
    success: function(data, status, xhr) {
        console.log('Data received:', data);
        console.log('Status:', status);
        console.log('XHR object:', xhr);
    },
    error: function(xhr, status, error) {
        console.error('AJAX request failed:', status, error);
    }
});

```

## Handle error
- **xhr:** The XMLHttpRequest object that represents the failed AJAX request.

- **status:** A string indicating the type of error that occurred. It could be "timeout", "error", "abort", or "parsererror".

- **error:** A string describing the specific error that occurred.

## Handle Success

-  **data:** This parameter represents the data returned from the server. Depending on the data type specified in the AJAX request (dataType), jQuery will attempt to convert the server response to the specified type (e.g., JSON, XML, HTML, etc.).

- **status:** This parameter represents the status of the AJAX request. It's a string indicating the status of the request, such as "success", "notmodified", "nocontent", "error", and "timeout".

- **xhr:** This parameter represents the XMLHttpRequest object used for the AJAX request. It provides access to various properties and methods related to the request, `such as xhr.status, xhr.responseText, etc.`
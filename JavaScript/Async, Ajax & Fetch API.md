# <p style="text-align: center;">Async in JavaScript</p>
*****

JavaScript is a Synchronous, Single thread language. It execute code line by line
## Asynchronous JavaScript
Functions running in parallel with other functions are called asynchronous. `A good example is JavaScript setTimeout()`

**setTimeout()**

The setTimeout () method in JavaScript is used to execute a function after waiting for the specified time interval.
```javaScript
setTimeout(myFunction, 5000);

function myFunction() {
  document.getElementById("demo").innerHTML = "I love You !!";
}
```

**clearTimeout()**

The clearTimeout () method clears a timer set with the setTimeout () method.

```javaScript
<html>
<body>

<p>Click "Stop" to prevent myGreeting() to execute. (You have 5 seconds)</p>

<button onclick="myStopFunction()">Stop!</button>

<h2 id="demo"></h2>

<script>
const myTimeout = setTimeout(myFunction, 5000);
function myFunction() {
  document.getElementById("demo").innerHTML = "I love You !!";
}

// if click on stop button before 5 sec it will crear the setTimeout
function myStopFunction() {
  clearTimeout(myTimeout);
}
</script>
</body>
</html>
```

**setInterval()**

setInterval() is a JavaScript method that repeatedly calls a function or executes a code snippet at specified intervals (in milliseconds)
```javaScript
setInterval(myFunction, 1000);

function myFunction() {
  let d = new Date();
  document.getElementById("demo").innerHTML=
  d.getHours() + ":" +
  d.getMinutes() + ":" +
  d.getSeconds();
}

// Output:- It will show time in hh:mm:ss with each second update
```
**clearInterval()**

The clearInterval () method clears a timer set with the setInterval () method
```javaScript
const myInterval = setInterval(myTimer, 1000);

function myTimer() {
const date = new Date();
document.getElementById("demo").innerHTML = date.toLocaleTimeString();
}

function myStopFunction() {
clearInterval(myInterval);
}
```


## Callback function
- A callback is a function that passed as an argument to the another function

- This technique allows a function to call another function

- A callback function can run after another function has finished

**Ex-1**
```javaScript
function myDisplayer(something) {
  document.getElementById("demo").innerHTML = something;
}

function myCalculator(num1, num2, myCallback) {
  let sum = num1 + num2;

  // pass myDisplayer() in myCalculator function
  myCallback(sum);
}

myCalculator(5, 5, myDisplayer);
</script>
```
**Ex-2** Remove Negative from Array using callback
```javaScript
const myNumbers = [4, 1, -20, -7, 5, 9, -6];

// Call removeNeg with a Callback
const posNumbers = removeNeg(myNumbers, (x) => x >= 0);

// Display Result
document.getElementById("demo").innerHTML = posNumbers;

// Remove negative numbers
function removeNeg(numbers, callback) {
  const myArray = [];
  for (const x of numbers) {
    if (callback(x)) {
      myArray.push(x);
    }
  }
  return myArray;
}

// Output: 4,1,5,9
```

## JavaScript Promises
A Promise is a JavaScript object that links producing code and consuming code
- "Producing code" is code that can take some time

- "Consuming code" is code that must wait for the result

**Why use Promise**

Multiple callback functions would create callback hell that leads to unmanageable code. Promises are used to handle asynchronous operations in JavaScript.

**Syntex**
```javaScript
let myPromise = new Promise(function(myResolve, myReject) {
// "Producing Code" (May take some time)

  myResolve(); // when successful
  myReject();  // when error
});

// "Consuming Code" (Must wait for a fulfilled Promise)
myPromise.then(
  function(value) { /* code if successful */ },
  function(error) { /* code if some error */ }
);
```

**Exaple-** 1
```javaScript
//Create Promise
let PromiseOne = new Promise(function(resolve, reject){
  setTimeout(function(){
    console.log("Async task is complete");
    resolve() // it connect .then, if not use resolve() function .then not working
  },1000)
})

// "Consuming Code" (Must wait for a fulfilled Promise)
PromiseOne.then(function(){
  console.log("Promise Consumed");
})
/*Output:
Async task is complete
Promise Consumed
```

**Exaple-** Pass data in resolve parameter
```javaScript
let PromiseTwo = new Promise(function(resolve, reject){
  setTimeout(function(){
    console.log("Async task is complete");
    resolve({username:"Umesh", email:"u@gmail.com"}) // you can pass any type of data like object/json/array etc.
  },1000)
})

PromiseTwo.then(function(Mydata){
  console.log(Mydata);
})
/*Output:
{username:"Umesh", email:"u@gmail.com"}
```

**Exaple-** Use of Myresolve and Myreject & finally
```javaScript
let PromiseThree = new Promise(function(Myresolve, Myreject){
  setTimeout(function(){
    let val="Umesh";
    
    if(val=="Umesh"){
      Myresolve({username:"Umesh", email:"u@gmail.com"})
    }else{
      Myreject("No data");
    }
  },1000)
})

PromiseThree.then((user) =>{
  return user.username
}).then((username) =>{
  consol.log(username);
})
// for error handle
.catch(function(error){
  console.log(error);
})
// .finally is print either promise is resolved or rejected
.finally(() => console.log("the promise is either resolved or rejected"))
/*Output:
Umesh
the promise is either resolved or rejected
```

**When the producing code obtains the result, it should call one of the two callbacks:**

| Result |	Call |
| ------ | ----- |
| Success| myResolve(result value) |
| Error	 | myReject(error object) |

**Promise Object Properties**

The Promise object supports two properties: state and result.

A JavaScript Promise object can be:

**1. Pending** 
While a Promise object is "pending" (working), the result is undefined.
**2. Fulfilled**
When a Promise object is "fulfilled", the result is a value.
**3. Rejected**
When a Promise object is "rejected", the result is an error object.

| myPromise.state |	myPromise.result |
| --------------- | ---------------- |
| "pending"	 | undefined |
| "fulfilled"	| a result value |
| "rejected"	| an error object |


# Async/Await
"async and await make promises easier to write"
- async makes a function return a Promise
- await makes a function wait for a Promise

**Async Syntax**
The keyword async before a function makes the function return a promise:
```javaScript
async function myFunction() {
  return "Hello";
}
```
it's same as
```javaScript
function myFunction() {
  return MyPromise.resolve("Hello");
}
```

**Await Syntax**
The await keyword can only be used inside an async function.
- The await keyword makes the function pause the execution and wait for a resolved promise before it continues:
```javaScript
let value = await promise;
```

```javaScript
async function myDisplay() {
  let myPromise = new Promise(function(resolve, reject) {
    resolve("I love You !!");
  });
  // use in place of then
  document.getElementById("demo").innerHTML = await myPromise;
}
myDisplay();
```

**Example-1** (Async and Await)
```javaScript
let PromiseThree = new Promise(function(Myresolve, Myreject){
  setTimeout(function(){
    let val="Umesh";
    
    if(val=="Umesh"){
      Myresolve({username:"Umesh", email:"u@gmail.com"})
    }else{
      Myreject("No data");
    }
  },1000)
})

async function Consume_Function_Name(){
  // always put in try and catch if resolve and reject both define
  try{
    // await must be stored in a variable
    const response = await PromiseThree
    console.log(response);
  }catch(error){
    console.log(error);
  }
}
// call consume function
Consume_Function_Name();
```

**Example-2** (Async and Await using fetch)
```javaScript
async function getAllUsers(){
  try{
    // await because fetch take some time in fetching
    const response = await fetch('https://example.com')
    // await because .json take some time to convert into json formate
    const data = await response.json()
    console.log(data);
  }catch(error){"
    console.log("E: ", error);
  }
}
getAllUsers();
```

**Example-3** (Example-2 witn .then and .catch)
```javaScript
//in .then() no use of await because .then() complete aur task first then call other function (or .then())
fetch('https://example.com')
.then((response) => {
  return response.json();
})
.then((data) => {
  console.log(data);
})
.catch((error) => console.log(error))
```

# <p style="text-align: center;">AJAX</p>
*****
Ajax stand for Asynchronous Javascript and XML. It is used to communicate with the server without refreshing the web page and thus increasing the user experience and better performance.

## Properties/Features of AJAX
- Update a web page without reloading the page
- Send data to a web server - in the background

## How does it work?
   1. An event occurs in a web page (the page is loaded, a button is clicked)
   2. An XMLHttpRequest object is created by JavaScript
   3. The XMLHttpRequest object sends a request to a web server
   4. The server processes the request
   5. The server sends a response back to the web page
   6. The response is read by JavaScript
   7. Proper action (like page update) is performed by JavaScript


## Types of requests in AJAX
1. Synchronous-->Synchronous requests are the one which follows sequentially i.e if one process is going on and in the same time another process wants to be executed, it will not be allowed that means the only one process at a time will be executed

2. Asynchronous-->Asynchronous means that the script will send a request to the server, and continue it's execution without waiting for the reply. As soon as reply is received a browser event is fired, which in turn allows the script to execute associated actions.

## XMLHttpRequest Object
The XMLHttpRequest object can be used to exchange data with a web server behind the scenes.

**Create XMLHttpRequest Object**
```javaScript
function Ajax() {
  const xhttp = new XMLHttpRequest();
}
```

## Send a Request
To send a request to a server, you can use the open() and send() methods of the XMLHttpRequest object

**For GET method**
```javaScript
xhttp.open("GET", "url", true/false); //true means its asynchronous
xhttp.send();
```

**For POST method**
```javaScript
xhttp.open("POST", "url", true/false); // false means its synchronous
xhttp.send(string);
```

## XMLHttpRequest Object Methods
| Method | Description |
| ------ | ----------- |
| new XMLHttpRequest() |	Creates a new |
|XMLHttpRequest object | abort()	Cancels the current request
| getAllResponseHeaders() | Returns header information |
| getResponseHeader() |	Returns specific header information |
| open(method, url, async, user, psw)| Specifies the request method: the request type GET or POST url: the file location async: true (asynchronous) or false (synchronous) user: optional user name psw: optional password |
| send() | Sends the request to the server Used for GET requests |
| send(string)	| Sends the request to the server. Used for POST requests |
| setRequestHeader() |	Adds a label/value pair to the header to be sent |

## XMLHttpRequest Object Properties

| Property |	Description |
| -------- |  ------------- |
| onload |	Defines a function to be called when the request is received (loaded) |
| onreadystatechange |	Defines a function to be called when the readyState property changes readyState	Holds the status of the XMLHttpRequest. 0: request not initialized 1: server connection established 2: request received 3: processing request 4: request finished and response is ready
| responseText	| Returns the response data as a string |
| responseXML |	Returns the response data as XML data |
| status |	Returns the status-number of a request 200: "OK" 403: "Forbidden" 404: "Not Found"
| statusText	| Returns the status-text (e.g. "OK" or "Not Found") |

## .onload
.onload is a callback function that to be executed when the request receives an answer.

```javaScript
function Ajax() {

xhttp.onload = function() {
  // What to do when the response is ready
}

}
```

## onreadystatechange
The onreadystatechange property defines a callback function to be executed when the readyState changes. `The onreadystatechange function is called every time the readyState changes.`

## readyState
The readyState property holds the status of the XMLHttpRequest.

```javascript
function loadDoc() {
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("demo").innerHTML =
      this.responseText;
    }
  };
  xhttp.open("GET", "ajax_info.txt");
  xhttp.send();
}
```
## AJAX - Server Response

**Server Response Property**

**responseText:-**	get the response data as a javaScript string

**responseXML:-**	get the response data as XML data
```javaScript
const xhttp = new XMLHttpRequest();
xhttp.onload = function() {
  const xmlDoc = this.responseXML;
  const x = xmlDoc.getElementsByTagName("ARTIST");
  let txt = "";
  for (let i = 0; i < x.length; i++) {
    txt = txt + x[i].childNodes[0].nodeValue + "<br>";
  }
  document.getElementById("demo").innerHTML = txt;
}
xhttp.open("GET", "cd_catalog.xml");
xhttp.send();

/* Output:-
The XMLHttpRequest Object
Bob Dylan
Bonnie Tyler
Dolly Parton
Gary Moore
Eros Ramazzotti
Bee Gees
Dr.Hook
Rod Stewart */
```
**getResponseHeader():-**	Returns specific header information from the server resource
```javaScript
const xhttp = new XMLHttpRequest();
xhttp.onload = function() {
  document.getElementById("demo").innerHTML =
  this.getAllResponseHeaders();
}
xhttp.open("GET", "ajax_info.txt");
xhttp.send();

/* Output:-
accept-ranges: bytes age: 2693 cache-control: public,max-age=14400,public content-encoding: gzip content-length: 147 content-security-policy: frame-ancestors 'self' https://mycourses.w3schools.com; content-type: text/plain date: Fri, 01 Sep 2023 10:15:42 GMT etag: "0b1a94319dbd91:0+gzip" last-modified: Wed, 30 Aug 2023 08:09:14 GMT server: ECS (ndl/D35C) vary: Accept-Encoding x-cache: HIT x-content-security-policy: frame-ancestors 'self' https://mycourses.w3schools.com; x-powered-by: ASP.NET */
```

**getAllResponseHeaders():-**	Returns all the header information from the server resource
```javaScript
const xhttp=new XMLHttpRequest();
xhttp.onload = function() {
  document.getElementById("demo").innerHTML =
  this.getResponseHeader("Last-Modified");
}
xhttp.open("GET", "ajax_info.txt");
xhttp.send();

/* Output:-
Last modified: Wed, 30 Aug 2023 08:09:14 GMT
*/
```

## Advantages of AJAX 
1. Speed is enhanced as there is no need to reload the page again.
2. AJAX make asynchronous calls to a web server, this means client browsers avoid waiting for all the data to    arrive before starting of rendering.
3. Form validation can be done successfully through it.
4. Bandwidth utilization – It saves memory when the data is fetched from the same page.
5. More interactive.

## Disadvantages of AJAX
1. Ajax is dependent on Javascript. If there is some Javascript problem with the browser or in the OS, Ajax will not support.
2. Ajax can be problematic in Search engines as it uses Javascript for most of its parts.
3. Source code written in AJAX is easily human readable. There will be some security issues in Ajax.
4. Debugging is difficult.
5. Problem with browser back button when using AJAX enabled pages.

**Ex-1** CodeWithHarry
```javascript
console.log("Ajax tutorial in one video");


let fetchBtn = document.getElementById('fetchBtn');
fetchBtn.addEventListener('click', buttonClickHandler)

function buttonClickHandler() {
     console.log('You have clicked the fetchBtn');

    // Instantiate an xhr object
    const xhr = new XMLHttpRequest();

    // Open the object
    // xhr.open('GET', 'https://jsonplaceholder.typicode.com/todos/1', true);

    // USE THIS FOR POST REQUEST
    xhr.open('POST', 'http://dummy.restapiexample.com/api/v1/create', true);
    xhr.getResponseHeader('Content-type', 'application/json');


    // What to do on progress (optional)
    xhr.onprogress = function(){
        console.log('On progress');
    }


    // xhr.onreadystatechange = function () {
    //     console.log('ready state is ', xhr.readyState);
        
    // }

    // What to do when response is ready
    xhr.onload = function () {
        if(this.status === 200){

            console.log(this.responseText)
        }
        else{
            console.log("Some error occured")
        }
    }

    // send the request
    params = `{"name":"test34sad545","salary":"123","age":"23"}`;
    xhr.send(params);

    console.log("We are done!");
}
```
**Ex-2** CodeWithHarry

```javaScript
let popBtn = document.getElementById('popBtn');
popBtn.addEventListener('click', popHandler);

function popHandler() {
    console.log('You have clicked the pop handler');

    // Instantiate an xhr object
    const xhr = new XMLHttpRequest();

    // Open the object
    xhr.open('GET', 'http://dummy.restapiexample.com/api/v1/employees', true);


    // What to do when response is ready
    xhr.onload = function () {
        if(this.status === 200){
            let obj = JSON.parse(this.responseText);
            console.log(obj);
            let list = document.getElementById('list');
            str = "";
            for (key in obj)
            {
                str += `<li>${obj[key].employee_name} </li>`;
            }
            list.innerHTML = str;
        }
        else{
            console.log("Some error occured")
        }
    }

    // send the request
    xhr.send();
    console.log("We are done fetching employees!");
}
```


# <p style="text-align: center;">Fetch API</p>
*****
The Fetch API provides a JavaScript interface for accessing and manipulating parts of the protocol, such as requests and responses. It also provides a global fetch() method that provides an easy, logical way to fetch resources asynchronously across the network.
`Unlike XMLHttpRequest that is a callback-based API, Fetch is promise-based and provides a better alternative that can be easily used in service workers.`

- The Fetch API interface allows web browser to make HTTP requests to web servers.

- 😀 No need for XMLHttpRequest anymore.

**Ex-1**
```javaScript
fetch(file/URL)
.then(x => x.text/json())
.then(y => myDisplay(y));
// its used to then file.

                                  or
// Since Fetch is based on async and await, the example above might be easier to understand like this:
async function getText(file) {
  let x = await fetch(file);
  let y = await x.text();
  myDisplay(y);
}
```

**Ex-2**
```javaScript
async function logMovies() {
  const response = await fetch("http://example.com/movies.json");
  const movies = await response.json();
  console.log(movies);
}
```

**Ex-3**
```javaScript
async function getText(file) {
  let myObject = await fetch(file);
  let myText = await myObject.text();
  myDisplay(myText);
}
```
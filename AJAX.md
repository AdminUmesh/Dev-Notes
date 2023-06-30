---------------------------------------------------------------------------------------------------------------
|                                   **AJAX = Asynchronous JavaScript And XML.**                                |
---------------------------------------------------------------------------------------------------------------

**What is Ajax?**
Ajax stand for Asynchronous Javascript and XML. It is used to communicate with the server without refreshing the web page and thus increasing the user experience and better performance.


**Properties/Features of AJAX**
1. Update a web page without reloading the page
2. Send data to a web server - in the background


**How does it work?**

            **BROWSER**                                                        **SERVER**
|-----------------------------------|                       |----------------------------------------------------|
|  an event occures...              |                       |Process HTTPReqest                                  |
|  create an XMLHttpRequest Object  |------> INTERNET ----> |Create a responce and send data back to the brouser |
|  Send XMLHttpRequest              |                       |                                                    |
|-----------------------------------|                       |----------------------------------------------------|
                                                                                        |
            **BROWSER**                                                                 |
|----------------------------------------|                                              |
|  process returned data using javaScript|                                              |
|                                        |<<------------ INTERNET ---------<<<<<<--------
|  Update Page Containt                  |                          
|----------------------------------------|
   1. An event occurs in a web page (the page is loaded, a button is clicked)
   2. An XMLHttpRequest object is created by JavaScript
   3. The XMLHttpRequest object sends a request to a web server
   4. The server processes the request
   5. The server sends a response back to the web page
   6. The response is read by JavaScript
   7. Proper action (like page update) is performed by JavaScript


**Types of requests in AJAX**
1. Synchronous-->Synchronous requests are the one which follows sequentially i.e if one process is going on and in the same time another process wants to be executed, it will not be allowed that means the only one process at a time will be executed

2. Asynchronous-->Asynchronous means that the script will send a request to the server, and continue it's execution without waiting for the reply. As soon as reply is received a browser event is fired, which in turn allows the script to execute associated actions.


**Advantages of AJAX**
1. Speed is enhanced as there is no need to reload the page again.
2. AJAX make asynchronous calls to a web server, this means client browsers avoid waiting for all the data to    arrive before starting of rendering.
3. Form validation can be done successfully through it.
4. Bandwidth utilization – It saves memory when the data is fetched from the same page.
5. More interactive.


**Disadvantages of AJAX**
1. Ajax is dependent on Javascript. If there is some Javascript problem with the browser or in the OS, Ajax will not support.
2. Ajax can be problematic in Search engines as it uses Javascript for most of its parts.
3. Source code written in AJAX is easily human readable. There will be some security issues in Ajax.
4. Debugging is difficult.
5. Problem with browser back button when using AJAX enabled pages.


**Create Object**
req.open("GET", "abc.php", true);
req.send();


**There are two types of methods open() and send()**
req.open("GET", "abc.php", true);
req.send();


**Sending the parameter through getting or GET request**
req.open("GET", "abc.php?x=25", true);
req.send();

**Sending the parameter through getting or POST request.**
req.send("name=johndoe&marks=99");


**XMLHttpRequest also has a property named as status**
200: “OK”
404: “Page not found”

**Now remember it always that when readyState is 4 and status is 200, the response is ready.**
<p id = "dis">< /p>
req.onreadystatechange = function(){
if(req.readyState == 4 && req.status == 200){
	document.getElementById("dis").innerHTML = req.responseText;
}
}
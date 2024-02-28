# HTTP protocol
The HTTP protocol is one of the most important protocols for smooth communication between the server and the client. The main disadvantage of the HTTP protocol is that it is a stateless protocol, which means it does not track any kind of response or request by the server or the client. So in order to resolve this problem, there are three ways to track useful information. In this article, `we are going to see the difference between local storage, session storage, and cookies and why it’s important for a web developer to know these terms.`

# Local Storage
This read-only interface property provides access to the document’s local storage object; the stored data is stored across browser sessions. Similar to sessionStorage, except that sessionStorage data gets cleared when the page session ends—that is, when the page is closed. It is cleared when the last “private” tab of a browser is closed

**Benifits of local Storage**
- fetching data from localstorage is faster than fetching from server.
`so recomendation is to fetch data from server and set it as localstorage for future use`

- data is not clear untill its not clear manually. 

## Local storage has 4 methods:

**setItem() Method –** This method takes two parameters one is key and another one is value. It is used to store the value in a particular location with the name of the key.
```js
localStorage.setItem(key, value)
```

**getItem() Method –** This method takes one parameter that is key which is used to get the value stored with a particular key name.
```js
localStorage.getItem(key)
```

**removeItem() Method –** This is method is used to remove the value stored in the memory in reference to key.
```js
localStorage.removeItem(key)
```

**clear() Method –** This method is used to clear all the values stored in localstorage.
```js
localStorage.clear()
```

# Set and get Object in localstorage
```js
const user ={name:"Akshay"}
localStorage.setItem("user", user) // it set as [object object]

localStorage.getItem("user") // output as [object object]

localStorage.setItem("copy-user", JSON.stringify(user)) // it set as {"name":"Akshay"}

JSON.parse(localStorage.getItem("copy-user")) // output as {name:"Akshay"}
```

## Set a 2D Array in locastorage and remove its first index.

```js
var multiArray = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
];

// Serialize the array into a string using JSON.stringify
var serializedArray = JSON.stringify(multiArray);

// Store the serialized array in localStorage
localStorage.setItem('multiArray', serializedArray);

      //Steps for Remove a item from localstorage
var retrievedArray = localStorage.getItem('multiArray');

// Deserialize the retrieved string back into an array using JSON.parse
var deserializedArray = JSON.parse(retrievedArray);

// Assuming you know the index of the value 456 in the multidimensional array
var indexToRemove = 1; // for example

// Remove the specific value at the specified index
deserializedArray[2].splice(indexToRemove, 1); // Modify as per your array structure

// Serialize the modified array back into a string using JSON.stringify
var modifiedSerializedArray = JSON.stringify(deserializedArray);

// Store the modified serialized array back in localStorage
localStorage.setItem('multiArray', modifiedSerializedArray);
```

# About (.splice) arrays method in js 

splice is used to add and remove items from array on specific index possition.

```js
array.splice(index_howmany item_remove, Add_item1, ....., Add_itemX)
```

```js
const fruits = ["Banana", "Orange", "Apple", "Mango"];

// At add 2 elements at position 2  
fruits.splice(2, 0, "Lemon", "Kiwi");
// Output
// Banana,Orange,Lemon,Kiwi,Apple,Mango


// At position 2, remove 2 items: 
const fruits = ["Banana", "Orange", "Apple", "Mango"];
fruits.splice(2, 2);

// Output
// Banana,Orange,Kiwi
```
## Retrive last 2D Arrays from locastorage and remove its first index. (with Slice method)

```js
// Retrieve the serialized array from localStorage
var serializedArray = localStorage.getItem('multiArray');

// Deserialize the serialized array back into a JavaScript array
var multiArray = JSON.parse(serializedArray);

// Use the map function to create a new array with the first sub-array removed
var newArray = multiArray.slice(1);

// Serialize the updated array into a string using JSON.stringify
var updatedSerializedArray = JSON.stringify(newArray);

// Store the updated serialized array back in localStorage
localStorage.setItem('multiArray', updatedSerializedArray);
```

# Checking data formate of localstorage

**For String**
```javascript
var savedData = localStorage.getItem('key');
if (typeof savedData === 'string') {
    console.log('Data is saved as a string.');
}
```

**For Json**
```javascript
var savedData = localStorage.getItem('key');
try {
    var parsedData = JSON.parse(savedData);
    console.log('Data is saved as JSON.');
} catch (error) {
    console.log('Data is not in JSON format.');
}
```

**For Arrays**
```javascript
// Assume savedData is the data retrieved from localStorage

// Check if savedData is an array
if (Array.isArray(savedData)) {
    console.log('Data is saved as an array.');
} else {
    console.log('Data is not saved as an array.');
}

// Alternatively, you can also use instanceof operator
if (savedData instanceof Array) {
    console.log('Data is saved as an array.');
} else {
    console.log('Data is not saved as an array.');
}
```

# Save a dummy json data in localstorage

```js
// Dummy JSON data
var dummyData = {
    "users": [
        {"id": 1, "name": "John Doe", "email": "john@example.com"},
        {"id": 2, "name": "Jane Smith", "email": "jane@example.com"},
        // Add more dummy data here...
    ],
    "products": [
        {"id": 1, "name": "Product 1", "price": 10.99},
        {"id": 2, "name": "Product 2", "price": 20.49},
        // Add more dummy data here...
    ],
    // Add more sections of dummy data as needed...
};

// Convert dummyData to a JSON string
var serializedData = JSON.stringify(dummyData);

// Save the serialized data to localStorage
localStorage.setItem('dummyData', serializedData);

// Retrieve the serialized data from localStorage
var retrievedData = localStorage.getItem('dummyData');

// Parse the retrieved data back to JSON format
var parsedData = JSON.parse(retrievedData);

// Now you can use the parsedData object as needed
console.log(parsedData);

```

**Delete all products from dummydata**

```javascript
// Retrieve the serialized data from localStorage
var serializedData = localStorage.getItem('dummyData');

// Parse the retrieved data into a JavaScript object
var parsedData = JSON.parse(serializedData);

// Remove the "products" property from the parsed data
delete parsedData.products;

// Convert the modified object back to a JSON string
var updatedSerializedData = JSON.stringify(parsedData);

// Save the updated JSON string back to localStorage
localStorage.setItem('dummyData', updatedSerializedData);

console.log('All products deleted successfully.');

```

**Delete only a product data whose product ID is 2 from dummydata**
```javascript
// Retrieve the serialized data from localStorage
var serializedData = localStorage.getItem('dummyData');

// Parse the retrieved data into a JavaScript object
var parsedData = JSON.parse(serializedData);

// Assume you want to delete a product with ID 2
var productIdToDelete = 2;

// Find the index of the product to delete
var productIndexToDelete = parsedData.products.findIndex(function(product) {
    return product.id === productIdToDelete;
});

// If the product is found, remove it from the products array
if (productIndexToDelete !== -1) {
    parsedData.products.splice(productIndexToDelete, 1);

    // Convert the modified object back to a JSON string
    var updatedSerializedData = JSON.stringify(parsedData);

    // Save the updated JSON string back to localStorage
    localStorage.setItem('dummyData', updatedSerializedData);

    console.log('Product deleted successfully.');
} else {
    console.log('Product not found.');
}
```

# Session Storage
Session Storage objects can be accessed using the sessionStorage read-only property. The difference between sessionStorage and localStorage is that localStorage data does not expire, whereas sessionStorage data is cleared when the page session ends.

`A unique page session gets created once a document is loaded in a browser tab. Page sessions are valid for only one tab at a time. Pages are only saved for the amount of time that the tab or the browser is open;`

## Session Storage has 4 methods:

**setItem() Method** – This method takes two parameters one is key and another one is value. It is used to store the value in a particular location with the name of the key.
```js
sessionStorage.setItem(key, value)
```

**getIteam() Method –** This method takes one parameter that is key which is used to get the value stored with a particular key name.
```js
sessionStorage.getItem(key)
```

**removeItem() Method –** This is method is used to remove the value stored in the memory in reference to key.
```js
sessionStorage.removeItem(key)
```

**clear() Method –** This method is used to clear all the values stored in the session storage
```js
sessionStorage.clear()
```

# Cookie:
The term “cookie” refers to just the textual information about a website. In order to recognize you and show you results according to your preferences, this website saves some information in your local system when you visit a particular website. The history of the internet has long been marked by the use of cookies. A website visitor asks the server for a web page when they visit it. Every request for a server is unique.

`In JavaScript, you can work with cookies using the document.cookie property. Cookies are small pieces of data that are stored on the client-side (in the user's browser) and are sent with every HTTP request to the same domain.`

**Setting a Cookie:**
- You can set a cookie by assigning a string value to document.cookie. The cookie string should be formatted as name=value. You can also specify additional attributes such as expiration date, path, and domain.
```js
document.cookie = "username=John";
```

**Getting a Cookie:**
- You can retrieve the value of a cookie by reading the document.cookie property.
```js
var username = document.cookie;
```
Note that document.cookie returns a string containing all cookies for the current domain. You may need to parse this string to extract the value of a specific cookie.

**Deleting a Cookie:**
- To delete a cookie, you can set its value to an empty string and optionally specify attributes such as expiration date in the past.
```js
document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
```

# Difference Between Local Storage, Session Storage, And Cookies

| Local Storage | Session Storage     | Cookies                 |
| :-------- | :------- | :------------------------- |
| `The storage capacity of local storage is 5MB/10MB` | `The storage capacity of session storage is 5MB` | `The storage capacity of Cookies is 4KB` |
| `As it is not session-based, it must be deleted via javascript or manually` | `It’s session-based and works per window or tab. This means that data is stored only for the duration of a session, i.e., until the browser (or tab) is closed` | `Cookies expire based on the setting and working per tab and window ` |
| `The client  can only read local storage` | `The client can only read local storage` | `Both clients and servers can read and write the cookies` |
| `There is no transfer of data to the server` | `There is no transfer of data to the server` | `Data transfer to the server is exist` |

# Object

## Object() constructor
The Object() constructor turns the input into an object. Its behavior depends on the input's type.

```js
//creating object
new Object()
new Object(value)
//Another method to create object
Object()
Object(value)
```

**Note:** 
Object() can be called with or without new keyword, but sometimes with different effects.

## Return Value
When the Object() constructor itself is called or constructed, its return value is an object:

- If the value is null or undefined, it creates and returns an empty object.

- If the value is an object already, it returns the value.

- Otherwise, it returns an object of a type that corresponds to the given value. For example, passing a BigInt primitive returns a BigInt wrapper object.

# Creating a new Object

```js
const o = new Object();
o.foo = 42;

console.log(o);
// { foo: 42 }

```
**Using Object given undefined and null types**

```js
const o = new Object();  //empty object
const o = new Object(undefined); // this is also empty object
const o = new Object(null); // this is also a empty object 
```
**Check types of variable**
```js
// all var in java with new keyword return a object
const numberObj = new Number(1);
console.log(typeof numberObj); // "object"
```

**Prototype**
https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/constructor

```js
const o1 = {};
o1.constructor === Object; // true

const o2 = new Object();
o2.constructor === Object; // true

const a1 = [];
a1.constructor === Array; // true

const a2 = new Array();
a2.constructor === Array; // true

const n = 3;
n.constructor === Number; // true
```

# Object Methods

## 1. Object.assign()
The Object.assign() method is used to copy the values and properties from one or more source objects to a target object

```js
const target = { a: 1, b: 2 };
const source = { b: 4, c: 5 };

const returnedTarget = Object.assign(target, source);

console.log(target);
// Expected output: Object { a: 1, b: 4, c: 5 }

console.log(returnedTarget === target);
// Expected output: true
```
```js
const obj1 = { a: 1 };
const new_obj = Object.assign({}, obj1);
 
// Displaying the target object 
console.log(new_obj);   
```

**Cloning an object**
```JS
const obj = { a: 1 };
const copy = Object.assign({}, obj);
console.log(copy); // { a: 1 }
```

**Merging objects**
```JS
const o1 = { a: 1 };
const o2 = { b: 2 };
const o3 = { c: 3 };

const obj = Object.assign(o1, o2, o3);
console.log(obj); // { a: 1, b: 2, c: 3 }
console.log(o1); // { a: 1, b: 2, c: 3 }, target object itself is changed.
```
## Object.create()
The Object.create() static method creates a new object, using an existing object as the prototype of the newly created object.

```JS
const person = {
  isHuman: false,
  printIntroduction: function () {
    console.log(`My name is ${this.name}. Am I human? ${this.isHuman}`);
  },
};

const me = Object.create(person);

me.name = 'Matthew'; // "name" is a property set on "me", but not on "person"
me.isHuman = true; // Inherited properties can be overwritten

me.printIntroduction();
// Expected output: "My name is Matthew. Am I human? true"
```

## Accessing Object
```js
objectName.methodName() //assessing methods
name = person.fullName; //accessing another property
person["lastName"];
```

# Object.values()
The Object.values() method accepts an object and returns its own enumerable property’s values as an array.

**Enumerable property** An enumerable property in JavaScript means that a property can be viewed if it is iterated using the for…in loop or Object.keys() method.
```js
const person = {
    firstName: 'John',
    lastName: 'Doe',
    age: 25
};

const profile = Object.values(person);

console.log(profile);

//output [ 'John', 'Doe', 25 ]
```

## Object.seal()
The Object.seal() static method seals an object. A sealed object has a fixed set of properties: new properties cannot be added, existing properties cannot be removed, their enumerability and configurability cannot be changed, and its prototype cannot be re-assigned.

**Syntax**
```JS
Object.seal(obj)
```

**Examle**
```js
const object1 = {
  property1: 42,
};

const obj = Object.seal(object1);
object1.property1 = 33;
console.log(object1.property1);
// Expected output: 33

delete object1.property1; // Cannot delete when sealed
console.log(object1.property1);
// Expected output: 33

object1 === obj; // true
Object.isSealed(obj); // true
```

## Object.keys()
The Object.keys() static method returns an array of a given object's own enumerable string-keyed property names.

```js
const object1 = {
  a: 'somestring',
  b: 42,
  c: false,
};

console.log(Object.keys(object1));
// Expected output: Array ["a", "b", "c"]   
```

## Object.defineProperties()
The Object.defineProperties() static method defines new or modifies existing properties directly on an object, returning the object.

**Syntax**
```js
Object.defineProperties(obj, props)
```

```js
const object1 = {};

Object.defineProperties(object1, {
  property1: {
    value: 42,
    writable: true,
  },
  property2: {},
});

console.log(object1.property1);
// Expected output: 42

```
## Object.defineProperty()
The Object.defineProperty() static method defines a new property directly on an object, or modifies an existing property on an object, and returns the object.
```js
const object1 = {};

Object.defineProperty(object1, 'property1', {
  value: 42,
  writable: false,
});

object1.property1 = 77;
// Throws an error in strict mode

console.log(object1.property1);
// Expected output: 42
```

## Object.entries()
The Object.entries() static method returns an array of a given object's own enumerable string-keyed property key-value pairs.

```js
const object1 = {
  a: 'somestring',
  b: 42,
};

for (const [key, value] of Object.entries(object1)) {
  console.log(`${key}: ${value}`);
}

// Expected output:
// "a: somestring"
// "b: 42"
```

```js
const obj = { foo: "bar", baz: 42 };
console.log(Object.entries(obj)); // [ ['foo', 'bar'], ['baz', 42] ]

const arrayLike = { 0: "a", 1: "b", 2: "c" };
console.log(Object.entries(arrayLike)); // [ ['0', 'a'], ['1', 'b'], ['2', 'c'] ]

const randomKeyOrder = { 100: "a", 2: "b", 7: "c" };
console.log(Object.entries(randomKeyOrder)); // [ ['2', 'b'], ['7', 'c'], ['100', 'a'] ]
```

## Object.freeze()

The Object.freeze() static method freezes an object. Freezing an object prevents extensions and makes existing properties non-writable and non-configurable. A frozen object can no longer be changed: new properties cannot be added, existing properties cannot be removed, their enumerability, configurability, writability, or value cannot be changed, and the object's prototype cannot be re-assigned. freeze() returns the same object that was passed in.

```js
const obj = {
  prop: 42,
};

Object.freeze(obj);

obj.prop = 33;
// Throws an error in strict mode

console.log(obj.prop);
// Expected output: 42
```

# Object.isFrozen()
The Object.isFrozen() static method determines if an object is frozen.
```js
const object1 = {
  property1: 42,
};

console.log(Object.isFrozen(object1));
// Expected output: false

Object.freeze(object1);

console.log(Object.isFrozen(object1));
// Expected output: true
```

## Object.fromEntries()
The Object.fromEntries() static method transforms a list of key-value pairs into an object.

```js
const entries = new Map([
  ['foo', 'bar'],
  ['baz', 42],
]);

const obj = Object.fromEntries(entries);

console.log(obj);
// Expected output: Object { foo: "bar", baz: 42 }
```
## Object.groupBy()
The Object.groupBy() static method groups the elements of a given iterable according to the string values returned by a provided callback function. The returned object has separate properties for each group, containing arrays with the elements in the group.

**Syntax**
```js
Object.groupBy(items, callbackFn)
```

**Example**
```js
const inventory = [
  { name: "asparagus", type: "vegetables", quantity: 5 },
  { name: "bananas", type: "fruit", quantity: 0 },
  { name: "goat", type: "meat", quantity: 23 },
  { name: "cherries", type: "fruit", quantity: 5 },
  { name: "fish", type: "meat", quantity: 22 },
];
```
```js
const result = Object.groupBy(inventory, ({ type }) => type);

/* Result is:
{
  vegetables: [
    { name: 'asparagus', type: 'vegetables', quantity: 5 },
  ],
  fruit: [
    { name: "bananas", type: "fruit", quantity: 0 },
    { name: "cherries", type: "fruit", quantity: 5 }
  ],
  meat: [
    { name: "goat", type: "meat", quantity: 23 },
    { name: "fish", type: "meat", quantity: 22 }
  ]
}
*/
```

```js
function myCallback({ quantity }) {
  return quantity > 5 ? "ok" : "restock";
}

const result2 = Object.groupBy(inventory, myCallback);

/* Result is:
{
  restock: [
    { name: "asparagus", type: "vegetables", quantity: 5 },
    { name: "bananas", type: "fruit", quantity: 0 },
    { name: "cherries", type: "fruit", quantity: 5 }
  ],
  ok: [
    { name: "goat", type: "meat", quantity: 23 },
    { name: "fish", type: "meat", quantity: 22 }
  ]
}
*/
```
# Object.hasOwn()
The Object.hasOwn() static method returns true if the specified object has the indicated property as its own property. If the property is inherited, or does not exist, the method returns false.
```js
const object1 = {
  prop: 'exists',
};

console.log(Object.hasOwn(object1, 'prop'));
// Expected output: true

console.log(Object.hasOwn(object1, 'toString'));
// Expected output: false

console.log(Object.hasOwn(object1, 'undeclaredPropertyValue'));
// Expected output: false
```

## Object.is()
The Object.is() static method determines whether two values are the same value.
```js
console.log(Object.is('1', 1));
// Expected output: false

console.log(Object.is(NaN, NaN));
// Expected output: true

console.log(Object.is(-0, 0));
// Expected output: false

const obj = {};
console.log(Object.is(obj, {}));
// Expected output: false
```

# Json
- JSON stands for JavaScript Object Notation

- JSON is a lightweight format for storing and transporting data

- JSON is often used when data is sent from a server to a web page

- JSON is "self-describing" and easy to understand

## # convert object to json and json to abject 

## json.parse()
this is help to convert text into a JavaScript object:

`Imagine we received this text from a web server:`
```js
'{"name":"John", "age":30, "city":"New York"}'
```

Use the JavaScript function JSON.parse() to convert text into a JavaScript object:
```js
const obj = JSON.parse('{"name":"John", "age":30, "city":"New York"}');

console.log(obj.age); //30
```

## Array as JSON
When using the JSON.parse() on a JSON derived from an array, the method will return a JavaScript array, instead of a JavaScript object.

Example
```js
const text = '["Ford", "BMW", "Audi", "Fiat"]';
const myArr = JSON.parse(text);

comsole.log(myArr[0]) //ford
```

## JSON.parse()
JavaScript has a built in function for converting JSON strings into JavaScript objects:


## JSON.stringify()
Its converting an object into a JSON string:

`You can receive pure text from a server and use it as a JavaScript object.`

## JSON Data Type
**Valid Data Types**
In JSON, values must be one of the following data types:

- a string
- a  number
- an object (JSON object)
- an array
- a boolean
- null

**JSON values cannot be one of the following data types:**

- a function
- a date
- undefined


## JSON Numbers
Numbers in JSON must be an integer or a floating point.

Example
```js
{"age":30}
```
## JSON Objects
Values in JSON can be objects.

Example
```js
{
"employee":{"name":"John", "age":30, "city":"New York"}
}
```

## JSON Arrays
Values in JSON can be arrays.

Example
```js
{
"employees":["John", "Anna", "Peter"]
}
```

```js
{
"name":"John",
"age":30,
"cars":["Ford", "BMW", "Fiat"]
}

const myObj = JSON.parse(myJSON);
// myObj.cars[0];
```

## JSON Booleans
Values in JSON can be true/false.

Example
```js
{"sale":true}
```
## JSON null
Values in JSON can be null.

Example
```js
{"middlename":null}
```

object reference:- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/Object
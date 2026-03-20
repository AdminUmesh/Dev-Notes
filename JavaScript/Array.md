# JavaScript Array Functions

## 1. `map()`

Creates a new array by transforming each element.

### Syntax

```javascript
array.map((item, index, array) => {
  return newValue;
});
```

### Example

```javascript
const numbers = [1, 2, 3];
const doubled = numbers.map(num => num * 2);
console.log(doubled); // [2, 4, 6]
```

### Key points

* Returns a new array
* Does not modify the original array
* Used when you want to transform data

---

## 2. `forEach()`

Runs a function for each array element.

### Syntax

```javascript
array.forEach((item, index, array) => {
  // do something
});
```

### Example

```javascript
const numbers = [1, 2, 3];
numbers.forEach(num => {
  console.log(num);
});
```

### Key points

* Does not return a useful new array
* Mostly used for printing, updating, or side effects
* Does not break or stop like a normal loop

---

## 3. `filter()`

Creates a new array with only the elements that match a condition.

### Syntax

```javascript
array.filter((item, index, array) => {
  return condition;
});
```

### Example

```javascript
const numbers = [1, 2, 3, 4, 5];
const evenNumbers = numbers.filter(num => num % 2 === 0);
console.log(evenNumbers); // [2, 4]
```

### Key points

* Returns a new array
* Keeps only matching elements
* Very useful for searching and condition-based selection

---

## 4. `find()`

Returns the first element that matches a condition.

### Example

```javascript
const users = [
  { id: 1, name: 'Umesh' },
  { id: 2, name: 'Rahul' }
];

const user = users.find(u => u.id === 2);
console.log(user); // { id: 2, name: 'Rahul' }
```

### Key points

* Returns only the first matching element
* Returns `undefined` if nothing matches

---

## 5. `findIndex()`

Returns the index of the first matching element.

### Example

```javascript
const numbers = [10, 20, 30, 40];
const index = numbers.findIndex(num => num === 30);
console.log(index); // 2
```

### Key points

* Returns index number
* Returns `-1` if not found

---

## 6. `reduce()`

Reduces the array to a single value.

### Syntax

```javascript
array.reduce((accumulator, currentValue) => {
  return updatedAccumulator;
}, initialValue);
```

### Example

```javascript
const numbers = [1, 2, 3, 4];
const sum = numbers.reduce((acc, num) => acc + num, 0);
console.log(sum); // 10
```

### Key points

* Returns one final value
* Used for sum, total, grouping, counting, object building
* One of the most powerful array methods

---

## 7. `some()`

Checks whether at least one element matches the condition.

### Example

```javascript
const numbers = [1, 3, 5, 8];
const hasEven = numbers.some(num => num % 2 === 0);
console.log(hasEven); // true
```

### Key points

* Returns `true` or `false`
* Stops as soon as it finds one matching element

---

## 8. `every()`

Checks whether all elements match the condition.

### Example

```javascript
const numbers = [2, 4, 6];
const allEven = numbers.every(num => num % 2 === 0);
console.log(allEven); // true
```

### Key points

* Returns `true` only if all elements match
* Returns `false` if one element fails

---

## 9. `includes()`

Checks whether an array contains a specific value.

### Example

```javascript
const fruits = ['apple', 'banana', 'mango'];
console.log(fruits.includes('banana')); // true
```

### Key points

* Returns `true` or `false`
* Useful for checking simple values

---

## 10. `sort()`

Sorts the array.

### Example

```javascript
const numbers = [40, 10, 100, 5];
numbers.sort((a, b) => a - b);
console.log(numbers); // [5, 10, 40, 100]
```

### Key points

* Changes the original array
* For numbers, always use compare function
* Can sort strings, numbers, and objects

---

## 11. `slice()`

Returns a shallow copy of part of an array.

### Example

```javascript
const numbers = [10, 20, 30, 40, 50];
const result = numbers.slice(1, 4);
console.log(result); // [20, 30, 40]
```

### Key points

* Does not modify original array
* End index is not included

---

## 12. `splice()`

Adds, removes, or replaces elements in the original array.

### Example

```javascript
const fruits = ['apple', 'banana', 'mango'];
fruits.splice(1, 1, 'orange');
console.log(fruits); // ['apple', 'orange', 'mango']
```

### Key points

* Modifies original array
* Very useful but should be used carefully

---

## 13. `concat()`

Merges arrays.

### Example

```javascript
const a = [1, 2];
const b = [3, 4];
const result = a.concat(b);
console.log(result); // [1, 2, 3, 4]
```

### Key points

* Returns a new array
* Does not modify originals

---

## 14. `join()`

Joins all array elements into a string.

### Example

```javascript
const words = ['I', 'love', 'JavaScript'];
const sentence = words.join(' ');
console.log(sentence); // I love JavaScript
```

### Key points

* Returns a string
* Useful for formatting output

---

## 15. `push()` and `pop()`

### `push()`

Adds element to the end.

```javascript
const arr = [1, 2];
arr.push(3);
console.log(arr); // [1, 2, 3]
```

### `pop()`

Removes the last element.

```javascript
const arr = [1, 2, 3];
arr.pop();
console.log(arr); // [1, 2]
```

---

## 16. `shift()` and `unshift()`

### `shift()`

Removes the first element.

```javascript
const arr = [1, 2, 3];
arr.shift();
console.log(arr); // [2, 3]
```

### `unshift()`

Adds element at the beginning.

```javascript
const arr = [2, 3];
arr.unshift(1);
console.log(arr); // [1, 2, 3]
```

---

## Quick Difference Table

| Method      | Returns New Array? | Modifies Original? | Main Use                   |
| ----------- | ------------------ | ------------------ | -------------------------- |
| `map()`     | Yes                | No                 | Transform each item        |
| `forEach()` | No                 | No                 | Run code for each item     |
| `filter()`  | Yes                | No                 | Select matching items      |
| `find()`    | No                 | No                 | Get first matching item    |
| `reduce()`  | No                 | No                 | Convert array to one value |
| `sort()`    | No                 | Yes                | Sort items                 |
| `slice()`   | Yes                | No                 | Copy part of array         |
| `splice()`  | No                 | Yes                | Add/remove/replace items   |

---

## `map()` vs `forEach()`

### `map()`

Use when you want a new array.

```javascript
const nums = [1, 2, 3];
const result = nums.map(n => n * 2);
console.log(result); // [2, 4, 6]
```

### `forEach()`

Use when you only want to perform an action.

```javascript
const nums = [1, 2, 3];
nums.forEach(n => console.log(n));
```

---

## Best Practice Notes

* Use `map()` for transformation
* Use `filter()` for conditions
* Use `find()` for one result
* Use `reduce()` for totals and advanced logic
* Avoid using `forEach()` when you need a returned array
* Be careful with methods that change the original array like `sort()` and `splice()`

---

## Practice Questions

1. Use `map()` to convert `[1, 2, 3]` into `[10, 20, 30]`
2. Use `filter()` to get numbers greater than 5 from `[2, 6, 8, 1]`
3. Use `find()` to get the first even number from `[1, 3, 4, 6]`
4. Use `reduce()` to find the sum of `[5, 10, 15]`
5. What is the difference between `slice()` and `splice()`?

---

## Final Summary

JavaScript array functions help you write cleaner and shorter code.

The most common methods are:

* `map()` for changing data
* `forEach()` for running code on each item
* `filter()` for selecting data
* `find()` for getting one item
* `reduce()` for getting a single final value
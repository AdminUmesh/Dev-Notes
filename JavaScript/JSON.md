
# JSON in JavaScript
---

## 1. What is JSON?

JSON (JavaScript Object Notation) is a lightweight, text-based data-interchange format.
In JavaScript, JSON is commonly used to exchange data between browser and server.

---

## 2. Sample JSON

```json
{
  "id": 1,
  "name": "Umesh",
  "active": true,
  "skills": ["C#", "Angular", "SQL"]
}
```

---

## 3. Parse JSON String → JavaScript Object

```js
const json = '{ "id": 1, "name": "Umesh", "active": true, "skills": ["C#", "Angular", "SQL"] }';

const obj = JSON.parse(json);

console.log(obj);
```

### Output

```js
{
  id: 1,
  name: "Umesh",
  active: true,
  skills: ["C#", "Angular", "SQL"]
}
```

---

## 4. Access Values from JSON Object

```js
console.log(obj.id);        // 1
console.log(obj.name);      // Umesh
console.log(obj.active);   // true
console.log(obj.skills[0]); // C#
```

### Output

```
1
Umesh
true
C#
```

---

## 5. Convert JavaScript Object → JSON String

```js
const user = {
  id: 2,
  name: "Rahul",
  active: false,
  skills: ["JavaScript", "SQL"]
};

const jsonString = JSON.stringify(user);
console.log(jsonString);
```

### Output

```json
{"id":2,"name":"Rahul","active":false,"skills":["JavaScript","SQL"]}
```

---

## 6. Pretty Print JSON

```js
const prettyJson = JSON.stringify(user, null, 2);
console.log(prettyJson);
```

### Output

```json
{
  "id": 2,
  "name": "Rahul",
  "active": false,
  "skills": [
    "JavaScript",
    "SQL"
  ]
}
```

---

## 7. Common JSON Mistakes in JavaScript

### ❌ Invalid JSON
```js
{ id: 1, name: "Umesh" }
```

### ✅ Valid JSON
```json
{ "id": 1, "name": "Umesh" }
```

---

## 8. JSON.parse vs JSON.stringify

| Method | Purpose |
|-----|--------|
| JSON.parse | JSON string → Object |
| JSON.stringify | Object → JSON string |

---

## 9. When JSON Is Used in JavaScript

- API requests / responses
- Frontend–backend communication
- Configuration files
- LocalStorage / SessionStorage

---

## 10. Final Summary

- JSON is text-based and language-independent
- JavaScript can directly parse and generate JSON
- Use `JSON.parse()` to read JSON
- Use `JSON.stringify()` to send/store JSON

---

END OF NOTES

# constructor vs ngOnInit in Angular

This document explains **constructor vs ngOnInit** in Angular in a **practical, interview‑ready way**.

---

## 1. Execution Order

```text
constructor()  →  ngOnInit()
```

- `constructor` **always runs first**
- `ngOnInit` runs **after Angular initializes the component**

---

## 2. constructor

### When does it run?
- When the component class is created
- Runs on:
  - Page load
  - Page refresh
  - Route navigation
  - Component recreation

### Purpose
✔ Dependency Injection  
✔ Simple variable initialization

### What NOT to do
❌ API calls  
❌ Access `@Input()`  
❌ DOM operations

### Example
```ts
constructor(private userService: UserService) {
  console.log('constructor called');
}
```

---

## 3. ngOnInit

### When does it run?
- Runs once after:
  - Inputs are set
  - First change detection

### Purpose
✔ API calls  
✔ Initial data load  
✔ Subscriptions  
✔ Access `@Input()`

### Example
```ts
ngOnInit() {
  console.log('ngOnInit called');
  this.loadData();
}
```

---

## 4. Page Refresh Behavior

| Scenario | constructor | ngOnInit |
|--------|------------|----------|
| Page refresh | ✅ | ✅ |
| Route navigation | ✅ | ✅ |
| Component reuse | ❌ | ❌ |

Both run again because the component is recreated.

---

## 5. @Input() Example (Very Important)

### ❌ Wrong
```ts
@Input() userId!: number;

constructor() {
  console.log(this.userId); // undefined
}
```

### ✅ Correct
```ts
ngOnInit() {
  console.log(this.userId); // correct value
}
```

---

## 6. Best Practice Rule

> **constructor = inject things**  
> **ngOnInit = run things**

---

## 7. Summary Table

| Feature | constructor | ngOnInit |
|------|------------|----------|
| Runs first | ✅ | ❌ |
| Dependency Injection | ✅ | ❌ |
| API calls | ❌ | ✅ |
| Access @Input | ❌ | ✅ |
| Runs once per lifecycle | ❌ | ✅ |

---

## 8. Interview One‑Liner

> Constructor is used for dependency injection,  
> ngOnInit is used for component initialization logic.

---

✅ End of document

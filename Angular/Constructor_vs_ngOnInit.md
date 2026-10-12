# 1. constructor
A constructor is a special method of a TypeScript class that runs automatically when an object of that class is created.

`It is commonly used to inject dependencies such as services into a component.`

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

# 2. ngOnInit
ngOnInit() is a lifecycle hook that runs once after Angular initializes a component's input properties. 

`It is commonly used to initialize component data, call APIs, and load data from services.`

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

## Execution Order

```text
constructor()  →  ngOnInit()
```

- `constructor` **always runs first**
- `ngOnInit` runs **after Angular initializes the component**

---

## Page Refresh Behavior

| Scenario | constructor | ngOnInit |
|--------|------------|----------|
| Page refresh | ✅ | ✅ |
| Route navigation | ✅ | ✅ |
| Component reuse | ❌ | ❌ |

Both run again because the component is recreated.

---

## @Input() Example (Very Important)

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

## Interview One‑Liner

> Constructor is used for dependency injection,  
> ngOnInit is used for component initialization logic.
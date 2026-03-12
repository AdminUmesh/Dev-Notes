# 🔹 Angular Lifecycle Hooks

Every Angular component goes through a lifecycle — from **creation → rendering → change detection → destruction**.  
Angular provides **lifecycle hooks** to let developers tap into these phases and execute custom logic.  

---

## Common Lifecycle Hooks

### 1. constructor()
- ⚡ Not an Angular lifecycle hook (it’s a TypeScript/JavaScript class feature).
- Called when the class is instantiated.
- Used only for **simple initialization** like **Dependency Injection (DI)**.
- ❌ Avoid heavy logic (like API calls).

```ts
constructor(private service: MyService) {
  console.log('Constructor called');
}
```

**2. ngOnChanges(changes: SimpleChanges)**

Called before ngOnInit and whenever an @Input() property changes.

Receives a SimpleChanges object containing previous & current values.
```ts
ngOnChanges(changes: SimpleChanges) {
  console.log('ngOnChanges:', changes);
}
```

**3. ngOnInit()**

Called once after the first ngOnChanges.

Best place for initialization logic (API calls, fetching data, subscriptions).

At this point, Angular has set up all input properties.
```ts
ngOnInit() {
  console.log('ngOnInit: Component initialized');
}
```

**4. ngDoCheck()**

Runs after ngOnChanges & ngOnInit.

Used for custom change detection when Angular’s default isn’t enough.

**5. ngAfterContentInit()**

Runs once after content is projected into the component (<ng-content>).

**6. ngAfterContentChecked()**

Runs after ngAfterContentInit and every time projected content is checked.

**7. ngAfterViewInit()**

Called once after the component’s view & child views are initialized.

Often used to access @ViewChild elements.

**8. ngAfterViewChecked()**

Runs after ngAfterViewInit and after every subsequent change detection cycle.

**9. ngOnDestroy()**

Called just before Angular destroys the component.

- Best place for cleanup:

- Unsubscribe from Observables

- Remove event listeners

- Clear timers
```ts
ngOnDestroy() {
  console.log('Component destroyed');
}
```

### Constructor vs ngOnInit

**constructor()**

Used only for DI & lightweight setup.

Angular hasn’t yet set input properties.

**ngOnInit()**

Component is ready.

Use for API calls, data loading, event subscriptions.

```
constructor(private userService: UserService) {
  // ✅ Only light setup here
}

ngOnInit() {
  // ✅ Heavy logic here
  this.userService.getUsers().subscribe(data => this.users = data);
}
```

### 🔄Lifecycle Execution Order (When Component is Created)

- **constructor() →**- ** class instantiated (dependencies injected)

- **ngOnChanges() →** if @Input() values are set/changed

- **ngOnInit()**

- **ngDoCheck()**

- **ngAfterContentInit()**

- **ngAfterContentChecked()**

- **ngAfterViewInit()**

- **ngAfterViewChecked()**

### 👉 On Component Destroy:
**9. ngOnDestroy()**

# 📌 Key Takeaway

- **constructor =** "Class is created"

- **ngOnInit =** "Component is ready, start business logic"

- **ngOnChanges =** "React to @Input property changes"

- **ngOnDestroy =** "Clean up resources"


---


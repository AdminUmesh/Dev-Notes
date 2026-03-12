# Angular Component Lifecycle

In Angular, components go through a series of steps from creation to destruction.  
You can hook into these steps using **lifecycle hooks**.

---

## 1. Creation Phase

### `constructor()`
- Called when the component is **created**.
- Good for **dependency injection**.
- Avoid complex logic here.

---

### `ngOnChanges(changes: SimpleChanges)`
- Called **before ngOnInit** and **whenever input (`@Input`) properties change**.
- Useful for reacting to changes in bound input data.
```ts
ngOnChanges(changes: SimpleChanges) {
  console.log('Changes detected:', changes);
}
```

## ngOnInit()
Called once after the first ngOnChanges.

Best place for initialization logic like API calls.

```ts
ngOnInit() {
  this.loadData();
}
```

## 2. Change Detection & View Phase
`ngDoCheck()`
Called during every change detection cycle.

Allows custom change detection logic (use sparingly — can be performance heavy).

`ngAfterContentInit()`
Called once after the component’s content (ng-content) is projected.

`ngAfterContentChecked()`
Called after projected content has been checked by change detection.

`ngAfterViewInit()`
Called once after the component’s view and child views are initialized.

Good for DOM-related logic.

`ngAfterViewChecked()`
Called after the component’s view (and child views) have been checked.

3. Destruction Phase
`ngOnDestroy()`
- Called just before Angular destroys the component.

- Best place to:

  - Unsubscribe from observables

  - Clear timers/intervals

  - Remove event listeners

```ts
ngOnDestroy() {
  this.sub.unsubscribe();
}
```

## Lifecycle Flow Diagram
```scss
constructor()
ngOnChanges()
ngOnInit()
ngDoCheck()
ngAfterContentInit()
ngAfterContentChecked()
ngAfterViewInit()
ngAfterViewChecked()
ngOnDestroy()
```

## Angular Component Lifecycle – Quick Summary

| Hook | Purpose | Called |
|------|---------|--------|
| `constructor` | Setup, Dependency Injection (DI) | Component creation |
| `ngOnChanges` | React to input (`@Input`) changes | Before/on every change |
| `ngOnInit` | Initialization logic | Once after first change |
| `ngDoCheck` | Custom change detection | Every change cycle |
| `ngAfterContentInit` | Projected content initialization | Once |
| `ngAfterContentChecked` | Projected content checked | After every check |
| `ngAfterViewInit` | View initialization | Once |
| `ngAfterViewChecked` | View checked | After every check |
| `ngOnDestroy` | Cleanup before destruction | Before destroy |
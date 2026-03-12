# Routing 
 routing is the process of navigating between different views or components within a single-page application by interpreting the browser's URL and displaying the corresponding content in the router-outlet, without making a full-page reload from the server.


**Basic Example**
```ts
// app-routing.module.ts
const routes: Routes = [
    { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];
```
**Then in HTML**
```html
<a routerLink="/home">Home</a>
<a routerLink="/about">About</a>

<router-outlet></router-outlet>
```

# Types of Routing in Angular

## 1. Eager Loading
- All feature modules and their components are loaded at application startup.

- Faster initial navigation, but increases the initial bundle size.

**Example:**

```ts
// .ts file
const routes: Routes = [
  { path: 'products', component: ProductsComponent },
  { path: 'cart', component: CartComponent }
];
```

**Pros:** Simple, no async loading.
**Cons:** Slower startup for large apps.

## 2. Lazy Loading
- Modules are loaded only when their route is accessed.

- Great for large applications to improve initial load time.

**Example:**
```ts
// .ts file
const routes: Routes = [
  { path: 'products', loadChildren: () => import('./products/products.module').then(m => m.ProductsModule) }
];
```
**Pros:** Smaller initial bundle size, faster startup.
**Cons:** First-time load of a lazy module may be slightly slower.


## 3. Preloading
- Combines lazy loading with background loading.

- Angular loads lazy modules in the background after the app starts so that they are ready when the user navigates to them.

**Example:**
```ts
// .ts file
RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules });
```
**Pros:** Faster navigation after startup.
**Cons:** Slightly increases network usage at start.


## 4. Auxiliary Routing (Named Router Outlets)
- Allows loading multiple routes in the same view using named outlets.

- Useful for side panels, popups, chat widgets.

**Example:**
```ts
// .ts file
{ path: 'chat', component: ChatComponent, outlet: 'sidebar' }
```
```html
<!-- In HTML -->
<router-outlet name="sidebar"></router-outlet>
```

## 5. Wildcard Routing
Handles unknown or invalid URLs (404 page).

```ts
// .ts file
{ path: '**', component: PageNotFoundComponent }
```

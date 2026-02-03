# Tailwind CSS

## What is Tailwind CSS?
Tailwind CSS is a utility-first CSS framework that lets you build UI directly in HTML using predefined classes.

---

## Setup
```bash
npm install -D tailwindcss postcss autoprefixer
npx tailwindcss init -p
```

Add to `tailwind.config.js`
```js
content: ["./src/**/*.{html,ts,tsx,jsx}"],
```

Add to `index.css`
```css
@tailwind base;
@tailwind components;
@tailwind utilities;
```

---

## Buttons
```html
<button class="bg-blue-600 text-white px-4 py-2 rounded">
  Save
</button>
```

Hover & Active:
```html
<button class="bg-green-600 hover:bg-green-700 active:bg-green-800 text-white px-4 py-2 rounded">
  Submit
</button>
```

---

## Margin & Padding
```html
<div class="m-4">Margin</div>
<div class="px-6 py-2">Padding</div>
```

---

## Border & Radius
```html
<div class="border-2 border-blue-500 rounded-lg p-4">
  Box
</div>
```

---

## Font & Text
```html
<p class="text-xl font-bold text-center">Heading</p>
```

---

## Width & Height (NEW – VERY IMPORTANT)

### Fixed Width / Height
```html
<div class="w-64 h-32 bg-gray-200">Fixed Size</div>
```

### Percentage Width
```html
<div class="w-1/2 bg-blue-200">50% Width</div>
<div class="w-full bg-green-200">100% Width</div>
```

### Screen Based
```html
<div class="w-screen h-screen bg-gray-100">
  Full Screen
</div>
```

### Min / Max Width
```html
<div class="min-w-[200px] max-w-md bg-yellow-200">
  Min & Max Width
</div>
```

---

## Centering
```html
<div class="flex items-center justify-center h-screen">
  <div class="p-6 bg-white shadow rounded">Center</div>
</div>
```

---

## Flexbox
```html
<div class="flex justify-between items-center">
  <div>Left</div>
  <div>Right</div>
</div>
```

---

## Grid
```html
<div class="grid grid-cols-3 gap-4">
  <div class="bg-gray-200">1</div>
  <div class="bg-gray-200">2</div>
  <div class="bg-gray-200">3</div>
</div>
```

---

## Table
```html
<table class="w-full border">
  <tr>
    <th class="border p-2">ID</th>
    <th class="border p-2">Name</th>
  </tr>
</table>
```

---

## Navbar
```html
<nav class="bg-blue-600 text-white flex justify-between px-6 py-3">
  <span class="font-bold">MyApp</span>
  <div class="flex gap-4">
    <a>Home</a>
    <a>About</a>
  </div>
</nav>
```

---

## Footer
```html
<footer class="bg-gray-800 text-white text-center p-4">
  © 2026 Company
</footer>
```

---

## Card
```html
<div class="max-w-sm border rounded shadow p-4">
  <h2 class="font-bold text-lg">Title</h2>
  <p>Description</p>
</div>
```

---

## Responsive Design
```html
<div class="bg-red-300 sm:bg-green-300 md:bg-blue-300 lg:bg-purple-300">
  Responsive Box
</div>
```

---

## Practice Suggestions
- Login Page
- Dashboard
- CRUD Table
- Responsive Layout

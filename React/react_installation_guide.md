# React Installation and New Page Setup Guide

## ✅ Step 1: Install Node.js

React requires Node.js and npm to manage packages and run the
development server.

Download and install Node.js from the official website:\
🔗 https://nodejs.org

Verify the installation:

``` bash
node -v
npm -v
```

------------------------------------------------------------------------

## ✅ Step 2: Create a New React Project

The recommended way to create a modern React app is using **Vite**.

Run the following command:

``` bash
npm create vite@latest my-react-app
```

When prompted:

Select a framework → **React**\
Select a variant → **TypeScript** (recommended) or JavaScript

Navigate into the project folder:

``` bash
cd my-react-app
```

------------------------------------------------------------------------

## ✅ Step 3: Install Dependencies

Install all required packages:

``` bash
npm install
```

------------------------------------------------------------------------

## ✅ Step 4: Run the Development Server

Start the development server:

``` bash
npm run dev
```

Visit the app in your browser:

🔗 http://localhost:5173

------------------------------------------------------------------------

## ✅ Step 5: Understand Basic Project Structure

Inside the project folder you will see:

    my-react-app
    │
    ├── node_modules
    ├── public
    ├── src
    │   ├── assets
    │   ├── App.tsx
    │   ├── main.tsx
    │   └── index.css
    ├── package.json
    └── vite.config.ts

Important files:

-   **main.tsx** → Application entry point
-   **App.tsx** → Root component
-   **index.css** → Global styles

------------------------------------------------------------------------

## ✅ Step 6: Create a New Page (Component)

Create a folder:

    src/pages

Create a new file:

    src/pages/About.tsx

Example component:

``` tsx
function About() {
  return (
    <div>
      <h1>About Page</h1>
      <p>This is the About page.</p>
    </div>
  );
}

export default About;
```

------------------------------------------------------------------------

## ✅ Step 7: Install React Router

React Router is used for navigation between pages.

Install it using npm:

``` bash
npm install react-router-dom
```

------------------------------------------------------------------------

## ✅ Step 8: Configure Routing

Open:

    src/App.tsx

Add the following code:

``` tsx
import { BrowserRouter, Routes, Route } from "react-router-dom";
import About from "./pages/About";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<About />} />
        <Route path="/about" element={<About />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
```

------------------------------------------------------------------------

## ✅ Step 9: Add Navigation Link

You can add links for navigation using `Link`.

Example:

``` tsx
import { Link } from "react-router-dom";

function Navbar() {
  return (
    <div>
      <Link to="/">Home</Link>
      <Link to="/about">About</Link>
    </div>
  );
}

export default Navbar;
```

------------------------------------------------------------------------

## ✅ Step 10: Test the Application

Run the application:

``` bash
npm run dev
```

Open your browser and navigate to:

    http://localhost:5173/about

------------------------------------------------------------------------

## 🎯 Typical React Project Folder Structure (Recommended)

    src
    │
    ├── components
    ├── pages
    ├── services
    ├── hooks
    ├── models
    ├── routes
    ├── assets
    ├── App.tsx
    └── main.tsx

Explanation:

-   **components** → reusable UI components
-   **pages** → page-level components
-   **services** → API calls
-   **hooks** → custom React hooks
-   **models** → TypeScript interfaces/types
-   **routes** → routing configuration

------------------------------------------------------------------------

## 🚀 Basic React Development Flow

    Component Created
           ↓
    User Interaction
           ↓
    State Updated (useState)
           ↓
    API Call (fetch / axios)
           ↓
    Data Returned
           ↓
    UI Updated

------------------------------------------------------------------------

## 🎉 Your React App is Ready!

You now know how to:

✔ Install React\
✔ Create a project\
✔ Run the development server\
✔ Create pages (components)\
✔ Add routing\
✔ Navigate between pages

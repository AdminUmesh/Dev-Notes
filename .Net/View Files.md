# Razor Views (`.cshtml`) in ASP.NET Core MVC

A `.cshtml` file is a **Razor View**.

It mainly contains:

1. **HTML** — defines the UI.
2. **Razor syntax** — allows C# code to be used inside HTML.

Razor uses the **`@` symbol** to switch from HTML to C#.

---

## 1. Basic Razor Syntax

```cshtml
<h1>Welcome to the site!</h1>

<p>Current time: @DateTime.Now</p>
```

Here:

* `<h1>` and `<p>` → HTML
* `@DateTime.Now` → C# expression

---

## 2. C# Code in Razor

You can write multiple lines of C# using `@{ }`.

```cshtml
@{
    var name = "Umesh";
    var age = 25;
}

<h2>Hello @name</h2>
<p>Age: @age</p>
```

### If Condition

```cshtml
@if (Model.IsActive)
{
    <p>The user is active.</p>
}
else
{
    <p>The user is not active.</p>
}
```

### Foreach Loop

```cshtml
<ul>
    @foreach (var item in Model.Items)
    {
        <li>@item.Name</li>
    }
</ul>
```

---

## 3. HTML Helpers

HTML Helpers are methods that help generate HTML elements using C#.

```cshtml
@Html.DisplayFor(model => model.Name)
```

```cshtml
@Html.TextBoxFor(model => model.Name)
```

### Tag Helpers

In modern ASP.NET Core MVC, **Tag Helpers** are also commonly used.

```cshtml
<input asp-for="Name" />

<label asp-for="Name"></label>
```

> **Remember:** HTML Helpers and Tag Helpers both help generate HTML from Razor/C# data.

---

# 4. View Folder Structure

Views are normally organized according to controllers.

```text
Views/
│
├── Home/
│   ├── Index.cshtml
│   ├── About.cshtml
│   └── Contact.cshtml
│
├── Account/
│   ├── Login.cshtml
│   └── Register.cshtml
│
└── Shared/
    ├── _Layout.cshtml
    ├── _LoginPartial.cshtml
    └── _Header.cshtml
```

For example:

```text
HomeController
      ↓
Views/Home/
      ↓
Index.cshtml
About.cshtml
Contact.cshtml
```

---

# 5. Shared Views

The `Views/Shared` folder contains views/components that can be used by **multiple controllers or views**.

Example:

```text
Views/
└── Shared/
    ├── _Layout.cshtml
    ├── _Header.cshtml
    └── _Footer.cshtml
```

Common shared files include:

* `_Layout.cshtml`
* `_Header.cshtml`
* `_Footer.cshtml`
* `_LoginPartial.cshtml`
* Other reusable partial views

---

# 6. Layout View

A **Layout View** is the common structure/template of your website.

For example:

```text
--------------------------------
|           Header             |
--------------------------------
|                              |
|        Page Content          |
|                              |
--------------------------------
|           Footer             |
--------------------------------
```

Instead of writing the header and footer in every page, we put them in `_Layout.cshtml`.

## `_Layout.cshtml`

```cshtml
<!DOCTYPE html>
<html>
<head>
    <title>@ViewData["Title"]</title>
</head>

<body>

    <header>
        <h1>My Website</h1>
    </header>

    <main>
        @RenderBody()
    </main>

    <footer>
        <p>© 2026 My Application</p>
    </footer>

</body>
</html>
```

The important part is:

```cshtml
@RenderBody()
```

`@RenderBody()` is where the **content of the current view** is inserted.

---

# 7. `_ViewStart.cshtml`

`_ViewStart.cshtml` is used to specify the **default layout** for views.

Typical structure:

```text
Views/
├── _ViewStart.cshtml
├── Home/
│   └── Index.cshtml
└── Shared/
    └── _Layout.cshtml
```

## `_ViewStart.cshtml`

```cshtml
@{
    Layout = "_Layout";
}
```

Now views under `Views` will normally use `_Layout.cshtml`.

The flow is:

```text
_ViewStart.cshtml
       ↓
Layout = "_Layout"
       ↓
Home/Index.cshtml
       ↓
Uses _Layout.cshtml
```

---

# 8. Changing the Layout for One View

You can override the default layout in a particular view.

```cshtml
@{
    Layout = "_CustomLayout";
}
```

That view will use `_CustomLayout.cshtml` instead of the default layout.

## No Layout

If you don't want to use any layout:

```cshtml
@{
    Layout = null;
}
```

> **Important:** `@page` is not required in a normal MVC View.

`@page` is primarily associated with **Razor Pages**.

### MVC

```text
Controller
     ↓
View (.cshtml)
```

### Razor Pages

```text
Razor Page (.cshtml)
     ↓
PageModel
```

---

# 9. Partial Views

A **Partial View** is a small, reusable piece of UI.

For example, if multiple pages need the same header:

```text
_Header.cshtml
```

You can create:

```text
Views/
└── Shared/
    └── _Header.cshtml
```

## `_Header.cshtml`

```cshtml
<div class="header">

    <h1>Welcome to My Website</h1>

    <nav>
        <a href="/">Home</a>
        <a href="/About">About</a>
        <a href="/Contact">Contact</a>
    </nav>

</div>
```

---

# 10. Rendering a Partial View

In modern ASP.NET Core, a common approach is:

```cshtml
<partial name="_Header" />
```

You may also encounter:

```cshtml
@await Html.PartialAsync("_Header")
```

Older MVC code may use:

```cshtml
@Html.Partial("_Header")
```

The basic idea is:

> A parent view can include a reusable Partial View.

---

# 11. Partial Views with ViewData

You can pass additional data to a Partial View using `ViewData`.

### Parent View

```cshtml
@{
    ViewData["HeaderTitle"] = "My Website";
}

<partial name="_Header" />
```

### `_Header.cshtml`

```cshtml
<h1>@ViewData["HeaderTitle"]</h1>
```

The flow is:

```text
Parent View
     ↓
ViewData
     ↓
Partial View
```

---

# 12. Passing a Model to a Partial View

Instead of `ViewData`, you can pass a strongly typed model.

### Parent View

```cshtml
<partial name="_Header" model="Model.Header" />
```

### `_Header.cshtml`

```cshtml
@model HeaderViewModel

<h1>@Model.Title</h1>
```

Using a model is usually cleaner when the Partial View requires structured data.

---

# 13. Rendering Partial Views Dynamically

Sometimes you don't know which Partial View should be displayed until runtime.

For example:

```cshtml
@{
    var partialName = Model.IsAdmin
        ? "_AdminMenu"
        : "_UserMenu";
}

<partial name="@partialName" />
```

If:

```text
Model.IsAdmin = true
```

then:

```text
_AdminMenu
```

is rendered.

Otherwise:

```text
_UserMenu
```

is rendered.

### Simple Definition

> **Dynamic Partial Rendering means choosing which Partial View to render at runtime.**

---

# 14. Advantages of Partial Views

## 1. Reusability

Write the UI once and reuse it.

```text
_Header.cshtml
      ↓
 ┌────┼────┐
 ↓    ↓    ↓
Home Account Dashboard
```

## 2. Cleaner Views

Instead of one huge `.cshtml` file:

```text
Index.cshtml
 ├── Header
 ├── Menu
 ├── User Information
 ├── Table
 └── Footer
```

you can break the UI into smaller Partial Views.

## 3. Maintainability

If the header changes, you only modify:

```text
_Header.cshtml
```

instead of changing every page.

## 4. Consistency

The same Partial View can be used throughout the application, keeping the UI consistent.

---

# 15. Partial View vs Layout View

This is an important interview question.

| Layout View                                 | Partial View                                   |
| ------------------------------------------- | ---------------------------------------------- |
| Defines the common **page structure**       | Defines a reusable **small UI section**        |
| Usually contains header, navigation, footer | Usually contains menu, table, form, card, etc. |
| Uses `@RenderBody()`                        | Does not use `@RenderBody()`                   |
| Usually `_Layout.cshtml`                    | Usually `_Header.cshtml`, `_Menu.cshtml`, etc. |
| Provides the overall page template          | Provides a reusable UI component               |

## Easy Way to Remember

### Layout = Whole Page Structure

```text
_Layout.cshtml
 ├── Header
 ├── Navigation
 ├── @RenderBody()
 └── Footer
```

### Partial = Small Reusable Section

```text
_Header.cshtml
_Menu.cshtml
_UserCard.cshtml
_ProductTable.cshtml
```

---

# 16. Layout and Partial Views Together

In real applications, Layouts and Partial Views are often used together.

Example:

```text
Views/
│
├── _ViewStart.cshtml
│
├── Home/
│   └── Index.cshtml
│
└── Shared/
    ├── _Layout.cshtml
    ├── _Header.cshtml
    └── _Footer.cshtml
```

The flow is:

```text
                 _ViewStart.cshtml
                        │
                        ↓
                  _Layout.cshtml
                  /            \
                 ↓              ↓
          _Header.cshtml    _Footer.cshtml
                 │
                 ↓
             @RenderBody()
                 │
                 ↓
           Home/Index.cshtml
```

### In simple terms

> **`_Layout.cshtml` provides the overall page structure, while Partial Views provide small reusable sections inside that structure.**

---

# Quick Revision

| Concept             | Meaning                                     |
| ------------------- | ------------------------------------------- |
| `.cshtml`           | Razor View file                             |
| Razor               | Allows C# inside HTML                       |
| `@`                 | Indicates Razor/C# syntax                   |
| `_Layout.cshtml`    | Common page structure                       |
| `@RenderBody()`     | Location where the current View is inserted |
| `_ViewStart.cshtml` | Defines the default Layout                  |
| Partial View        | Small reusable UI component                 |
| `Views/Shared`      | Common Views, Layouts and Partial Views     |
| `ViewData`          | Passes additional data to a View/Partial    |
| Dynamic Partial     | Selects a Partial View at runtime           |
| Layout              | Whole page/template                         |
| Partial             | Small reusable section                      |

---

# Most Important Difference

```text
                    ASP.NET Core MVC
                           │
                           ↓
                    Controller Action
                           │
                           ↓
                      View (.cshtml)
                           │
              ┌────────────┴────────────┐
              ↓                         ↓
        Layout View              Partial Views
       (_Layout.cshtml)        (_Header.cshtml)
              │                 (_Menu.cshtml)
              │                 (_Footer.cshtml)
              ↓
        @RenderBody()
              │
              ↓
       Current View Content
```

**Remember this one line:**

> **Layout controls the overall page structure; Partial Views divide reusable UI sections into smaller components.**

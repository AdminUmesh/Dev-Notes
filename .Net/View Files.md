# Structure of a View:
A view in ASP.NET Core MVC is typically a .cshtml file, which stands for C# HTML (C# Razor). Razor allows you to embed C# code directly into HTML markup. 

## A `.cshtml` file typically consists of:
1. HTML content.
2. Razor syntax for dynamic data and logic.

## The Razor View File (.cshtml)
The Razor syntax uses special symbols to differentiate between HTML markup and C# code.
`Razor used @ Symbol to indicate C# code inside HTML.`

```cshtml
<h1>Welcome to the site!</h1>
<p>Current time: @DateTime.Now</p>
```
**C#code block**
```csharp
@foreach (var item in Model.Items)
{
    <p>@item.Name</p>
}
```
**`HTML Helpers:` ASP.NET Core provides helper methods to generate common HTML tags with C# code.**
```cshtml
Example: @Html.DisplayFor(model => model.Property)
Example: @Html.TextBoxFor(model => model.Name)
```
**Conditional Logic:** You can use standard C# conditional logic within a Razor view.
```csharp
@if (Model.IsActive)
{
    <p>The user is active.</p>
}
else
{
    <p>The user is not active.</p>
}
```
**Loops:** You can use C# loops to iterate over collections in Razor views.
```cshtml
<ul>
  @foreach (var item in Model.Items)
  {
      <li>@item.Name</li>
  }
</ul>
```

# Naming Convention:
```markdown
/Views
    /Home
        - Index.cshtml
        - About.cshtml
        - Contact.cshtml
    /Account
        - Login.cshtml
        - Register.cshtml
````

# Shared Files in ASP.NET MVC
A Shared View typically refers to views that are shared across different controllers. These views are often placed in the `Views/Shared/ folder.`

### Characteristics:
- A shared view can be used across multiple controllers.
- It can include layout or partial views that should be accessible to the entire application.
- Common views or components (like _Layout.cshtml, _LoginPartial.cshtml) that need to be accessed globally are placed in the Views/Shared/ folder.

## 1. _Layout Page in Shared:
A Layout View is a template that defines the common structure of your application, such as the header, footer, sidebar, or navigation menu. It is used to provide a consistent look and feel across multiple pages of the site.

**_Layout.cshtml Example:**
```html
<!-- .cshtml file -->
<!DOCTYPE html>
<html>
<head>
    <title>@ViewBag.Title</title>
    <link rel="stylesheet" href="~/Content/styles.css" />
</head>
<body>
    <header>
        <nav>
            @Html.Partial("_Navigation")  <!-- Include navigation menu -->
        </nav>
    </header>

    <main>
        @RenderBody()  <!-- Content of the individual view will be inserted here -->
    </main>

    <footer>
        <p>&copy; 2025 My Application</p>
    </footer>
</body>
</html>
```
### Apply the Custom Layout 
```csharp
@page //this will make a page Razor pages (treat as Razor pages)

@{
    Layout = "_CustomLayout";  // Specifies the custom layout for this view
}
```

### Not Using Any Layout
```csharp
@page

@{
    Layout = null;  // Disables layout for this view
}
```
# `_ViewStart.cshtml` (Defines the Default Layout):

This file is usually located in the Views/ folder. if you mention a layout in this view it kame this layout as default for all pages.

```csharp
@{
    Layout = "_Layout";  // This defines the default layout for all views
}
```

### Home/Index.cshtml (Uses Default Layout):

If you don't specify a custom layout in the Index.cshtml, it will automatically use the default layout defined in _ViewStart.cshtml.

```html
<h1>Welcome to the Home Page</h1>
<p>This page uses the default layout.</p>
```

# Partial Views:
A Partial View in ASP.NET Core MVC is a view component that can be reused across multiple views within your application.
`Partial views are typically used to render small sections of a page, such as a header, footer, navigation menu, or any reusable UI component.`

### Key Features of Partial Views:
- **Reusability:** A partial view allows you to reuse the same HTML across multiple views, making your code more maintainable and consistent.
- **Modularization:** It helps in breaking down a page into smaller chunks that are easier to manage and maintain.
- **Separation of Concerns:** Partial views help in separating different parts of the UI from the rest of the page, which promotes cleaner code.

### 1. Creating a Partial View
Let's say you have a partial view called `_Header.cshtml` for the header section of your website.

```html
<!-- Views/Shared/_Header.cshtml -->
<div class="header">
    <h1>Welcome to My Website</h1>
    <nav>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/About">About</a></li>
            <li><a href="/Contact">Contact</a></li>
        </ul>
    </nav>
</div>
```

### 2. Rendering a Partial View in Another View
To render the partial view within a parent view, you use `@Html.Partial()` or `@Html.RenderPartial()`.

**Using @Html.Partial():**
```html
<!-- Views/Home/Index.cshtml -->
@{
    ViewData["Title"] = "Home Page";
}

@Html.Partial("_Header")  <!-- This renders the partial view _Header.cshtml -->

<h2>Home Page Content</h2>
<p>Welcome to the home page!</p>
```
The above code will include the header defined in _Header.cshtml within the Index.cshtml view.

**Using @Html.RenderPartial():**
```html
<!-- Views/Home/Index.cshtml -->
@{
    ViewData["Title"] = "Home Page";
}

@{ Html.RenderPartial("_Header"); }  <!-- Renders the partial view _Header.cshtml -->

<h2>Home Page Content</h2>
<p>Welcome to the home page!</p>
```

## Difference Between `@Html.Partial()` and `@Html.RenderPartial()`:
- `@Html.Partial():` Returns a string, so it can be used in assignments, if needed.
- `@Html.RenderPartial():` Writes the output directly to the response stream and does not return a value.

## Later
- **Partial Views with ViewData ?**
- **Rendering Partial Views Dynamically ?**
- **Advantages of Using Partial Views ?**
- **Partial Views vs Layout Views ?**
# Routing
routing is the mechanism that maps incoming HTTP requests to controller actions.

**ASP.NET Core mainly supports two routing systems:**

## A.Conventional Routing
- Uses a predefined URL pattern to map requests to controllers and actions.

- Common in MVC-style apps.

- Defined in Program.cs or Startup.cs (depending on .NET Core version).

```c#
app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");
```

**Pros:** Simple for standard MVC apps.
**Cons:** Not as flexible for REST-style APIs.

## B.Attribute Routing
- Routes are defined directly on controllers and actions using attributes.

- Gives more control over URL structure.

```c#
[Route("products")]
public class ProductsController : Controller
{
    [Route("list")]
    public IActionResult List() { ... }

    [Route("{id:int}")]
    public IActionResult Details(int id) { ... }
}
```
**Pros:** Perfect for REST APIs, clear mapping.
**Cons:** Can become hard to manage in large projects if not organized.
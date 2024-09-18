# Handle HTTP request in C#

## Basic API Controller Syntax 
```c#
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;

[Route("api/[controller]")]
[ApiController]
public class ProductsController : ControllerBase
{
    private static List<Product> products = new List<Product>
    {
        new Product { Id = 1, Name = "Product1", Price = 10.0M },
        new Product { Id = 2, Name = "Product2", Price = 20.0M }
    };

    // GET api/products
    [HttpGet]
    public ActionResult<IEnumerable<Product>> GetProducts()
    {
        return Ok(products);
    }

    // GET api/products/1
    [HttpGet("{id}")]
    public ActionResult<Product> GetProduct(int id)
    {
        var product = products.Find(p => p.Id == id);
        if (product == null) return NotFound();
        return Ok(product);
    }

    // POST api/products
    [HttpPost]
    public ActionResult<Product> CreateProduct([FromBody] Product product)
    {
        product.Id = products.Count + 1; // Simple ID assignment
        products.Add(product);
        return CreatedAtAction(nameof(GetProduct), new { id = product.Id }, product);
    }

    // PUT api/products/1
    [HttpPut("{id}")]
    public ActionResult UpdateProduct(int id, [FromBody] Product updatedProduct)
    {
        var product = products.Find(p => p.Id == id);
        if (product == null) return NotFound();

        product.Name = updatedProduct.Name;
        product.Price = updatedProduct.Price;
        return NoContent();
    }

    // DELETE api/products/1
    [HttpDelete("{id}")]
    public ActionResult DeleteProduct(int id)
    {
        var product = products.Find(p => p.Id == id);
        if (product == null) return NotFound();

        products.Remove(product);
        return NoContent();
    }
}
```

## About [Route("api/[controller]")]

**Route Attribute:** The [Route] attribute is used to define a route template for the controller or action method.

**"api/":** This part of the route specifies that the API endpoints will be prefixed with "api/". It helps to organize routes for API controllers separately from other types of controllers.

**[controller]:** This is a placeholder that gets replaced by the name of the controller class minus the "Controller" suffix. For example, if your controller is named ProductsController, the placeholder will be replaced with "products", resulting in a final route of "api/products".

**Example:**
- If your controller is named ProductsController, the route defined by [Route("api/[controller]")] means that the base URL for all actions in this controller will start with http://localhost:5000/api/products.

- If you have an action method defined with [HttpGet], it will be accessed at http://localhost:5000/api/products.

## About [ApiController]
The [ApiController] attribute in ASP.NET Core provides several benefits that enhance the development of web APIs. 

- **Automatic Model Validation:** When a controller is marked with [ApiController], model binding and validation are performed automatically. If the model state is invalid (e.g., missing required fields), the framework will return a 400 Bad Request response without you needing to check it explicitly.

- **Parameter Binding:** It simplifies the way parameters are bound from the request. For instance, it automatically infers from the request body, query string, or route data without needing to specify [FromBody], [FromQuery], etc., in many cases.

- **onsistent Responses:** It helps standardize responses for certain status codes. For example, when returning a 404 Not Found, you can simply return NotFound() instead of constructing a more complex response.

- **Attribute** Routing:** Controllers marked with [ApiController] support attribute routing out of the box. This makes it easier to create RESTful routes using attributes like [HttpGet], [HttpPost], etc.

# Received Data in the Request Body & Request Headers

## Receiving Data in the Request Body
You can use the [FromBody] attribute to indicate that the data should be read from the request body.

```c#
public ActionResult<Product> CreateProduct([FromBody] Product product)
    {
        if (!ModelState.IsValid)
        {
            return BadRequest(ModelState);
        }
    }
```

# Receiving Data from Request Headers
To access request headers, you can use the Request.Headers collection. Here’s how you can read headers in a controller:

```c#
[HttpPost]
public ActionResult<Product> CreateProduct([FromBody] Product product)
{
    // Read a specific header
    if (Request.Headers.TryGetValue("X-Custom-Header", out var customHeaderValue))
    {
        // Use the header value as needed
        // For example, log it or process it
        Console.WriteLine($"Custom Header Value: {customHeaderValue}");
    }

    // Proceed with your logic...
    if (!ModelState.IsValid)
    {
        return BadRequest(ModelState);
    }

    // Typically add the product to a database here
    return CreatedAtAction(nameof(GetProduct), new { id = product.Id }, product);
}
```
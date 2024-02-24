# Using Parameter

.cshtml Page
```html
<form asp-action="SubmitFormData" method="post">
    <div>
        <label>Name:</label>
        <input type="text" name="name" />
    </div>
    <div>
        <label>Roll No:</label>
        <input type="text" name="rollNo" />
    </div>
    <div>
        <button type="submit">Submit</button>
    </div>
</form>
```

.cs Page
```cs
[HttpPost]
public String SubmitFormData(string name, string rollNo)
{
    return name + " " + rollNo;
}
```

# Using Request (method 1)

.cshtml Page
```html
<form asp-action="SubmitFormData" method="post">
    <div>
        <label>Name:</label>
        <input type="text" name="name" />
    </div>
    <div>
        <label>Roll No:</label>
        <input type="text" name="rollNo" />
    </div>
    <div>
        <button type="submit">Submit</button>
    </div>
</form>
```

.cs Page
```cs
[HttpPost]
public async Task<IActionResult> SubmitFormData()
{
    var form = await HttpContext.Request.ReadFormAsync();
    string name = form["name"];
    //string rollNo = form["rollNo"];

    if (name != "")
    {
        return Ok(name);
    }
    else
    {
         return BadRequest("Name is not provided or is empty.");
    }
 }
```

# Using Request (method 2)

.cshtml Page
```html
<form asp-action="SubmitFormData" asp-controller="Home" method="post">
    <div>
        <label>Name:</label>
        <input type="text" name="name" />
    </div>
    <div>
        <label>Roll No:</label>
        <input type="text" name="rollNo" />
    </div>
    <div>
        <button type="submit">Submit</button>
    </div>
</form>
```

.cs Page
```cs
[HttpPost]
public async Task<IActionResult> SubmitFormData()
{
var form = await HttpContext.Request.ReadFormAsync();
string name = form["name"];
string rollNo = form["rollNo"];

if (!string.IsNullOrEmpty(name) && !string.IsNullOrEmpty(rollNo))
{
    //var data = new { Name = name, RollNo = rollNo };
    //return Ok(data);
    // data will return in object formate
    var data = new { name, rollNo }; // Create an anonymous object with name and rollNo properties
    return Ok(data); // Return the object as JSON
    return Ok(data.name); //this will return only name as string
}
else
{
    return BadRequest("Name or roll number is not provided or is empty.");
}
 }
```
**Output**
{
    "name": "Umesh",
    "rollNo": "4545"
}

# Using FormCollection

.cshtml Page
```html
<form asp-action="SubmitFormData" method="post">
    <div>
        <label>Name:</label>
        <input type="text" name="name" />
    </div>
    <div>
        <label>Roll No:</label>
        <input type="text" name="rollNo" />
    </div>
    <div>
        <button type="submit">Submit</button>
    </div>
</form>
```

.cs Page
```cs
[HttpPost]
public IActionResult SubmitFormData(IFormCollection formCollection)
{
    string name = formCollection["name"];
    string rollNo = formCollection["rollNo"];

    string result = $"Name: {name}, Roll No: {rollNo}";
    return Content(result);

    //In ASP.NET Core, the Content method is a convenient way to return plain text or content directly from a controller action. It creates a ContentResult object with the specified content and returns it to the client.
}
```
**Output**
Name: Umesh, Roll No: 4545

# Using Models

ViewToController.cshtml Page
```html
<form asp-action="SubmitFormData" asp-controller="Home" method="post">
    <div>
        <label>Name:</label>
        <input type="text" name="name" />
    </div>
    <div>
        <label>Roll No:</label>
        <input type="text" name="rollNo" />
    </div>
    <div>
        <button type="submit">Submit</button>
    </div>
</form>
```
SubmitFormData.cshtml Page
```html
@model Mixed_Project.Controllers.MyModal

<p>Name: @Model.Name</p>
<p>Roll No: @Model.RollNo</p>
```

.cs Page
```cs
namespace Mixed_Project.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult ViewToController()
        {
            return View();
        }

        [HttpPost]
        public ActionResult SubmitFormData(MyModal ModalItems)
        {
            string name = ModalItems.Name;
            string rollNo = ModalItems.RollNo;

            MyModal formData = new MyModal
            {
                Name = name,
                RollNo = rollNo
            };

            return View(formData);
        }
    }

    public class MyModal
    {
        public string? Name { get; set; }
        public string? RollNo { get; set; }
    }
}

```
**Output**
Name: Umesh, Roll No: 4545

# Using Get Request

ViewToController.cshtml
```html
<div>
    <section>
    @using (Html.BeginForm("SubmitFormData", "Home", FormMethod.Get))
    {
        <div>
            <label>Name:</label>
            <input type="text" name="name" />
        </div>
        <div>
            <label>Roll No:</label>
            <input type="text" name="rollNo" />
        </div>
        <div>
            <button type="submit">Submit</button>
        </div>
    }
    </section>
</div>
```

YourPageName.cshtml
```html
@model Mixed_Project.Controllers.MyModal

<p>Name: @Model.Name</p>
<p>Roll No: @Model.RollNo</p>
<p>Emp: @Model.emp</p>
```

.cs Page
```cs
[HttpGet] // This attribute specifies that the action responds to GET requests
public IActionResult SubmitFormData(string name, string rollNo)
{
    // Process the submitted data (name and rollNo) here
    // For demonstration purposes, just returning a view with the submitted data
    return View("YourPageName", new MyModal { Name = name, RollNo = rollNo });
}
```

modelclass
```cs
public class MyModal
{
    public string? Name { get; set; }
    public string? RollNo { get; set; }

    public string emp { get; set; }
}
```
**Output**
Name: Umesh
Roll No: 101

# Using JavaScript (Post Method)

ViewToController.cshtml
```html
<html>
    <head>

    </head>

    <body>
    <form id="formData">
        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" />
        </div>
        <div>
            <label for="rollNo">Roll No:</label>
            <input type="text" id="rollNo" name="rollNo" />
        </div>
        <div>
            <button type="submit">Submit</button>
        </div>
    </form>
    </body>

    <script>
    document.getElementById('formData').addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent the default form submission

        var formData = new FormData(this); // Create FormData object with form data

        // AJAX POST request
        fetch('/Home/SubmitFormData', {
            method: 'POST',
            body: formData
        })
            .then(response => response.text())
            .then(data => {
                // Handle the response from the server
                console.log(data); // Log the response for demonstration
            })
            .catch(error => console.error('Error:', error));
    });
    </script>
</html>
```

.cs Page
```cs
[HttpPost]
public IActionResult SubmitFormData(string name, string rollNo)
{
    // Process the submitted data (name and rollNo) here
    // For demonstration purposes, just returning a string response
    return Content("Data received successfully: Name - " + name + ", Roll No - " + rollNo);
}
```
**Output**
Name: Umesh
Roll No: 101

# Using JavaScript (Get Method)

ViewToController.cshtml
```html
<html>
    <head>

    </head>

    <body>
    <form id="formData">
    <div>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" />
    </div>
    <div>
        <label for="rollNo">Roll No:</label>
        <input type="text" id="rollNo" name="rollNo" />
    </div>
    <div>
        <button type="submit">Submit</button>
    </div>
    </form>
    </body>

    <script>
    document.getElementById('formData').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission

    var name = document.getElementById('name').value;
    var rollNo = document.getElementById('rollNo').value;

    // Construct the query string
    var queryString = 'name=' + encodeURIComponent(name) + '&rollNo=' + encodeURIComponent(rollNo);

    // AJAX GET request
    fetch('/Home/SubmitFormData?' + queryString)
    .then(response => response.text())
    .then(data => {
        // Handle the response from the server
        console.log(data); // Log the response for demonstration
    })
    .catch(error => console.error('Error:', error));
});
    </script>
</html>
```

.cs Page
```cs
[HttpGet]
public IActionResult SubmitFormData(string name, string rollNo)
{
     // Process the submitted data (name and rollNo) here
     // For demonstration purposes, just returning a string response
     return Content("Data received successfully: Name - " + name + ", Roll No - " + rollNo);
}
```
**Output**
Name: Umesh
Roll No: 101

# Recomendation
for edit detail and delete visit github CRUD-dotnet repo
```html
 @Html.ActionLink("Edit", "Edit", new { item.ID, item.Name, item.Email}, new{@class="btn btn-success"}) 
                    @Html.ActionLink("Details", "Delete", new { id = item.ID }, new { @class = "btn btn-info" })
                    @Html.ActionLink("Delete", "Delete", new { id = item.ID }, new{@class="btn btn-danger"})
```
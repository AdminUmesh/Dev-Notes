# Using Strongly Typed Models

**Model Class**
```c#
public class Record
{
    public int Id { get; set; }
    public string  RecordName { get; set; }
    public string  RecordDetail { get; set; }

}
```

**Controller Class**
```c#
public class Record
{
    public ActionResult Index()
{
    Record rec = new Record
    {
        Id = 101,
        RecordName = "Bouchers",
        RecordDetail = "The basic stocks"
    };
    ViewBag.Message = rec;
    return View();
}
}
```

**View Page**
```html
@using PassDatainMVC.Models

@{
    ViewBag.Title = "Index";
}

<h3>Passing Data From Controller to View using ViewBag</h3>
@{
    var data = ViewBag.Message;
}
<h3>Id: @data.Id</h3>
<h3>RecordName: @data.RecordName</h3>
<h3>RecordDetail: @data.RecordDetail</h3>
```

# Using ViewBag
**Model Class**
```c#
public class Record
{
    public int Id { get; set; }
    public string  RecordName { get; set; }
    public string  RecordDetail { get; set; }

}
```

**Controller Class**
```c#
public ActionResult Index()
{
    Record rec = new Record
    {
        Id = 101,
        RecordName = "Bouchers",
        RecordDetail = "The basic stocks"
    };
    ViewData["Message"] = rec;
    return View();
}
```

**View Page**
```html
@using PassDatainMVC.Models
@{
    ViewBag.Title = "Index";
}
<h3>Passing Data From Controller To View using ViewData</h3>
@{
    var data = (Record)ViewData["Message"];
}
<h3>Id: @data.Id</h3>
<h3>RecordName: @data.RecordName</h3>
<h3>RecordDetail: @data.RecordDetail</h3>
```

# Using object of the model class to the View
```c#
public class Record
{
    public int Id { get; set; }
    public string  RecordName { get; set; }
    public string  RecordDetail { get; set; }

}
```

**Controller Class**
```c#
public ActionResult Index()
{
    Record rec = new Record
    {
        Id = 101,
        RecordName = "Bouchers",
        RecordDetail = "The basic stocks"
    };
    return View(rec);
}
```

**View Page**
```html
@using PassDatainMVC.Models
@model PassDatainMVC.Models.Record
@{
    ViewBag.Title = "Index";
}
<h3>Passing Data From Controller To View using Model Class Object</h3>

<h3>Id: @Model.Id</h3>
<h3>RecordName: @Model.RecordName</h3>
<h3>RecordDetail: @Model.RecordDetail</h3>
```

# Using TempData
**Controller page**
```cs
public ActionResult CheckTempData()
{
    TempData["data"] = "I'm temprory data to used in subsequent request";
    return RedirectToAction("Index");
}
```
```html
<h3>Hey! @TempData["data"]</h3>
```

# Using ViewData
**Controller page**
```cs
public ActionResult CheckViewData()
{
    ViewData["data"] = "I'm ViewData used to pass data to the view";
    return RedirectToAction("Index");
}
```

**View Page**
```html
<h3>Hey! @(ViewData["data"])</h3>
```

# Using JavaScript (Inline Script:Method)
**Controller page**
```cs
public IActionResult Index()
{
    ViewBag.Message = "Hello from ViewBag!";
    return View();
}
```

**View Page**
```js
<script>
    var message = "@ViewBag.Message";
    console.log(message);
</script>
```

# Using JavaScript (JSON Serialization:Method)
**Controller page**
```cs
public IActionResult Index()
{
    var model = new { Message = "Hello from JSON!" };
    ViewBag.JsonData = JsonConvert.SerializeObject(model);
    return View();
}
```

**View Page**
```js
<script>
   <script>
    var jsonData = @Html.Raw(ViewBag.JsonData);
    console.log(jsonData.Message);
</script>
```

# Using JavaScript (AJAX Requests:Method)
**Controller page**
```cs
public IActionResult GetData()
{
    var model = new { Message = "Hello from AJAX!" };
    return Json(model);
}
```

**View Page**
```js
<script>
    $.get("/ControllerName/GetData", function(data) {
        console.log(data.Message);
    });
</script>
```

# Difference Between ViewBag, ViewData & TempData 

**ViewBag:-** ViewBag is used to send data from controller to view.
- Object formate
- ViewBag.key = Value
- store data + there corresponding data type
- Work on Same Request (Only use same Controllers' ViewPage)

**ViewData:-** ViewBag is used to send data from controller to view.
- Dictionary formate
- ViewData["key"] = Value
- Only data can store (not dataType)
- Work on Same Request (Only use same Controllers' ViewPage)

**TempData:-** ViewBag is used to send data from controller to view.
- Dictionary formate
- TempData["key"] = Value
- Only data can store (not dataType)
- Work on Same as well as different Request (Can be used on different Controllers' ViewPage)
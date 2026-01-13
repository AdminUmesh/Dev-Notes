## Add Swagger Version and Title

```c#
// Add this in Program.cs for swagger visiblity
builder.Services.AddSwaggerGen(c =>{
    c.SwaggerDoc("v1", new OpenApiInfo { Title = "Aimil Worksheet API", Version = "v1" });
});

// This will only show on swagger (Not worked in API)
```

# Install Package for Version
`Microsoft.AspNetCore.Mvc.Versioning`

## Add below code for version
```c#
// Here the Program.cs setting which you have to use in API
builder.Services.AddApiVersioning(option =>
{
    option.DefaultApiVersion = new ApiVersion(1, 0); 
    option.AssumeDefaultVersionWhenUnspecified = true;
    option.ReportApiVersions = true;
    option.ApiVersionReader = ApiVersionReader.Combine(
       new QueryStringApiVersionReader("api-version"),
       new HeaderApiVersionReader("X-Version"),
       new MediaTypeApiVersionReader("ver"));
});
```

## Add versioning in controller
```c#
[Authorize]
[ApiVersion("1.0")] // you can mannually set version in controller like this
[Route("v{v:apiVersion}/api/[controller]/[action]")] // otherwise this will get auto by Program.cs implementation
[ApiController]
```

## Additional Swagger Setting
```c#
[Authorize]
[Route("v{v:apiVersion}/api/[controller]/[action]")]
[ApiExplorerSettings(IgnoreApi = false)] // this setting decide is this controller visible in swagger or not
[ApiController]
```
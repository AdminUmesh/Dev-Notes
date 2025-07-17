
# Serilog
Serilog is a structured logging library for .NET.
It allows you to log rich, queryable logs to files, databases, cloud, and other destinations in a clean, efficient, and flexible way.

⚡ Unlike traditional text-based logging, Serilog stores structured data (like key-value pairs), making it easier to filter, search, and analyze logs.

## 🔧 Serilog Integration in ASP.NET Core

This guide explains how to integrate **Serilog** into a .NET Core Web API project, configure it with `appsettings.json`, and log messages to a text file.

### 1. 📦 Install Required NuGet Packages

Run the following commands in your terminal:

```bash
dotnet add package Serilog.AspNetCore
dotnet add package Serilog.Sinks.File
dotnet add package Serilog.Settings.Configuration
```

---

### 2. 🧠 Configure `Program.cs`

```csharp
using Serilog;

var builder = WebApplication.CreateBuilder(args);

// 🔧 Add Serilog configuration from appsettings.json
Log.Logger = new LoggerConfiguration()
    .ReadFrom.Configuration(builder.Configuration)
    .Enrich.FromLogContext()
    .CreateLogger();

builder.Host.UseSerilog(); // Replace default logger with Serilog

builder.Services.AddControllers();
var app = builder.Build();

app.UseAuthorization();
app.MapControllers();
app.Run();
```

---

### 3. 📄 Create `appsettings.json`

```json
{
  "Serilog": {
    "MinimumLevel": {
      "Default": "Information",
      "Override": {
        "Microsoft": "Warning",
        "System": "Warning"
      }
    },
    "WriteTo": [
      {
        "Name": "Console"
      },
      {
        "Name": "File",
        "Args": {
          "path": "Logs/log.txt",
          "rollingInterval": "Day"
        }
      }
    ]
  }
}
```

---

### 4. ✏️ Example Log Statement in a Controller

```csharp
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

[ApiController]
[Route("[controller]")]
public class WeatherForecastController : ControllerBase
{
    private readonly ILogger<WeatherForecastController> _logger;

    public WeatherForecastController(ILogger<WeatherForecastController> logger)
    {
        _logger = logger;
    }

    [HttpGet]
    public IEnumerable<string> Get()
    {
        _logger.LogInformation("Fetching weather forecast at {time}", DateTime.UtcNow);
        return new[] { "Sunny", "Cloudy", "Rainy" };
    }
}
```

---

## 📂 Logs Output

- Logs are stored in the `/Logs` folder.
- A new file is created each day (rolling interval set to `Day`).
- Example file: `log20250715.txt`

---

# ✅ Why Use Serilog?

| Feature              | Benefit                                         |
|----------------------|--------------------------------------------------|
| Structured logging   | Easily searchable & filterable logs (JSON-based) |
| Multiple sinks       | Log to file, console, SQL, Seq, Elasticsearch, etc. |
| Asynchronous logging | Minimal performance hit                         |
| Rolling file support | Auto-manages log file size/duration             |
| Easy configuration   | Fluent API or JSON settings                     |

---

# 🛠 How Serilog Works

Serilog has three main parts:

1. **Logger Configuration** – Setup how and where logs will be written.
2. **Log Events** – Use `.Information()`, `.Error()`, etc. in your code.
3. **Sinks** – Targets where logs are written (e.g., file, console, database).

## ✅ Benefits of Using Serilog

- Structured logging support
- Configurable via `appsettings.json`
- Supports multiple sinks (console, file, Seq, Elasticsearch)
- Great integration with .NET ecosystem

---

## 📌 Tips

- Use `"rollingInterval": "Minute"` during development/testing to view real-time logs.
- In production, prefer `"Day"` or `"Hour"` to avoid too many small files.

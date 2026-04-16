# Azure Blob Storage 

## What is Azure Blob Storage?
Cloud storage for unstructured data: images, videos, PDFs, backups.

## Benefits
- Scalable
- Cost effective
- Secure
- Fast access
- Public/private access
- Easy .NET integration

## What Can We Do?
- Upload
- Download
- Delete
- Public URL
- Private access with SAS
- Store file URL in DB

## Create Resource
1. Azure Portal
2. Search `Storage account`
3. Create
4. Open storage account
5. Containers
6. + Container

## Public / Private
**Public:** Anyone with URL can view  
**Private:** Auth/API required

## Get Key
Storage Account → Access keys → Copy Connection String

## appsettings.json
{
  "AzureBlobStorage": {
    "ConnectionString": "your_connection_string",
    "ContainerName": "public-images"
  }
}

## NuGet
dotnet add package Azure.Storage.Blobs

## BlobService.cs
```c#
using Azure.Storage.Blobs;

namespace AzureBlob.Services
{
    public class BlobService
    {
        private readonly string _connectionString = "YOUR_CONNECTION_STRING";
        private readonly string _containerName = "public-images";

        public BlobService(IConfiguration configuration)
        {
            _connectionString = configuration["AzureBlobStorage:ConnectionString"];
            _containerName = configuration["AzureBlobStorage:ContainerName"];
        }

        public async Task<string> UploadFileAsync(IFormFile file)
        {
            var client = new BlobContainerClient(_connectionString, _containerName);
            await client.CreateIfNotExistsAsync();

            var blob = client.GetBlobClient(file.FileName);

            using (var stream = file.OpenReadStream())
            {
                await blob.UploadAsync(stream, overwrite: true);
            }

            return blob.Uri.ToString();
        }

        public async Task<Stream?> GetFileAsync(string fileName)
        {
            var container = new BlobContainerClient(_connectionString, _containerName);
            var blob = container.GetBlobClient(fileName);

            if (!await blob.ExistsAsync())
                return null;

            var response = await blob.DownloadStreamingAsync();
            return response.Value.Content;
        }
    }
}
```
## Controller
```c#
using Microsoft.AspNetCore.Mvc;
using AzureBlob.Services;

namespace AzureBlob.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class UploadController : ControllerBase
    {
        private readonly BlobService _blobService;

        public UploadController(BlobService blobService)
        {
            _blobService = blobService;
        }

        [HttpPost]
        public async Task<IActionResult> Upload(IFormFile file)
        {
            var url = await _blobService.UploadFileAsync(file);
            return Ok(new { imageUrl = url });
        }

        [HttpGet("{fileName}")]
        public async Task<IActionResult> GetFile(string fileName)
        {
            var stream = await _blobService.GetFileAsync(fileName);

            if (stream == null)
                return NotFound("File not found");

            return File(stream, "application/octet-stream", fileName);
        }
    }
}
```

## Program.cs
builder.Services.AddScoped<BlobService>();

## **Best Practice**
Store files in Blob, save URL in database.

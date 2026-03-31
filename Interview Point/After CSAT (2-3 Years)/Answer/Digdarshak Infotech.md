# FTP in .NET 8 

## 1. What is FTP?
**FTP (File Transfer Protocol)** is a standard network protocol used to transfer files between a client and a server over a network.

### Key Points:
- Works on TCP/IP
- Default Port: **21**
- Used for uploading/downloading files
- Supports authentication (username/password)
- Two modes: **Active** and **Passive**

---

## 2. Where FTP is Used
- File uploads from applications (reports, logs)
- Downloading data from external servers
- Website deployment (legacy hosting)
- Data exchange between systems

---

## 3. Important Note for .NET 8 ⚠️
- `FtpWebRequest` is **deprecated**
- Not recommended for new development
- Use modern libraries like **FluentFTP**

---

## 4. Install FluentFTP (Recommended)
Run in Package Manager Console:

```
Install-Package FluentFTP
```

---

## 5. How to Call FTP in .NET 8

### 5.1 Connect to FTP Server

```csharp
using FluentFTP;

var client = new FtpClient("ftp.example.com", "username", "password");
client.Connect();
```

---

### 5.2 Upload File

```csharp
client.UploadFile(@"C:\local\file.txt", "/remote/file.txt");
```

---

### 5.3 Download File

```csharp
client.DownloadFile(@"C:\local\file.txt", "/remote/file.txt");
```

---

### 5.4 List Files

```csharp
foreach (var item in client.GetListing("/"))
{
    Console.WriteLine(item.FullName);
}
```

---

### 5.5 Disconnect

```csharp
client.Disconnect();
```

---

## 6. Async Example (Recommended)

```csharp
using FluentFTP;

var client = new FtpClient("ftp.example.com", "username", "password");

await client.ConnectAsync();
await client.UploadFileAsync(@"C:\local\file.txt", "/remote/file.txt");
await client.DisconnectAsync();
```

---

## 7. Common Issues

### 1. Passive Mode
```csharp
client.Config.DataConnectionType = FtpDataConnectionType.AutoPassive;
```

### 2. SSL Connection
```csharp
client.EncryptionMode = FtpEncryptionMode.Explicit;
client.ValidateCertificate += (control, e) => e.Accept = true;
```

### 3. Timeout Issues
```csharp
client.ConnectTimeout = 10000;
```

---

## 8. Summary

- FTP is used for file transfer between systems
- .NET 8 does NOT recommend `FtpWebRequest`
- Use **FluentFTP**
- Prefer async methods for performance

---

## 9. Best Practice

- Always handle exceptions
- Use async methods
- Secure credentials
- Prefer FTPS (FTP over SSL)

---
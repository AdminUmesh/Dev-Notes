
# Visual Studio & .NET SDK Installation Guide (Which Version to Install)

Author: Umesh Kumar Singh  
Purpose: Clear guidance on **which Visual Studio version and .NET SDK to install** based on project type, plus SDK verification and local HTTPS setup.

---

## 1. Visual Studio Versions Overview

| Visual Studio Version | Status | Architecture |
|----------------------|--------|--------------|
| Visual Studio 2019 | Legacy | 32-bit |
| Visual Studio 2022 | Current / Recommended | 64-bit |
| Visual Studio 2026 | ❌ Not released | — |

> ⚠ **Visual Studio 2026 does not exist yet.**  
> Use **VS 2022** for all modern .NET development.

---

## 2. Which Visual Studio to Install (MOST IMPORTANT)

### Based on Project / Target Framework

| Project Type / .NET Version | Visual Studio to Install |
|-----------------------------|--------------------------|
| .NET Framework 4.5 – 4.8 | VS 2019 ✅ or VS 2022 ✅ |
| .NET 5 | VS 2019 ✅ (last supported) |
| .NET 6 | VS 2022 ✅ |
| .NET 7 | VS 2022 ✅ |
| .NET 8 (LTS) | VS 2022 ✅ (Recommended) |
| .NET 9 | VS 2022 ✅ |
| .NET 10 (future) | VS 2022 (updated) ✅ |
| Legacy WinForms / WPF (.NET Framework) | VS 2019 / VS 2022 |

### Simple Rule
- **Old / Legacy projects** → VS 2019  
- **Modern & new projects** → **VS 2022 (always)**

---

## 3. Differences Between VS 2019 and VS 2022

| Feature | VS 2019 | VS 2022 |
|-------|---------|---------|
| Architecture | 32-bit | **64-bit** |
| Max .NET support | .NET 5 | .NET 6 → .NET 10 |
| Performance | Limited (RAM bound) | Much faster |
| Large solutions | ❌ Struggles | ✅ Handles easily |
| Recommended today | ❌ No | ✅ Yes |

---

## 4. Install Visual Studio 2022 (Recommended)

### Download
https://visualstudio.microsoft.com/downloads/

Choose:
- **Visual Studio 2022 (Community / Professional / Enterprise)**

### Required Workloads
✔ ASP.NET and web development  
✔ .NET desktop development  
✔ Data storage and processing  

### Important Individual Components
✔ .NET SDK 8.x (x64)  
✔ IIS Express  
✔ SQL Server Data Tools (SSDT)  

Restart Windows after installation.

---

## 5. Install .NET SDK (Very Important)

### What is SDK?
- SDK = **Build + Run + CLI tools**
- Runtime alone is **NOT sufficient** for development

### Download SDKs
https://dotnet.microsoft.com/download

Recommended:
```
.NET 8 SDK (LTS) — Windows x64
```

❌ Do NOT install x86  
❌ Do NOT install runtime only

---

## 6. How to Check SDK is Installed or Not

Open **Command Prompt**:
```cmd
dotnet --list-sdks
```

Example output:
```
8.0.204
10.0.102
```

If nothing shows:
- SDK not installed
- Or wrong architecture (x86)

### Check current SDK in use
```cmd
dotnet --version
```

---

## 7. SDK Folder Location (Important)

| Architecture | Location |
|-------------|----------|
| x64 | C:\Program Files\dotnet\sdk |
| x86 | C:\Program Files (x86)\dotnet\sdk |

Visual Studio **uses x64 only**.

---

## 8. Local HTTPS / SSL Certificate (ASP.NET Core)

ASP.NET Core uses HTTPS by default for local development.

### Why HTTPS is Needed
- Secure cookies
- Authentication
- Browser security
- Modern API standards

### Trust HTTPS Developer Certificate
Run **CMD as Administrator**:
```cmd
dotnet dev-certs https --clean
dotnet dev-certs https --trust
```

✔ Click **Yes** when Windows asks for trust

This prevents:
- “Unable to connect to web server https”
- Browser SSL warnings

---

## 9. When HTTPS Certificate Popup Appears
You may see:
```
Trust ASP.NET Core HTTPS development certificate?
```

✔ Always click **YES**  
It is safe and local-only.

---

## 10. Common Mistakes to Avoid

❌ Installing x86 SDK on 64-bit Windows  
❌ Using VS 2019 for .NET 8+ projects  
❌ Installing Runtime instead of SDK  
❌ Ignoring HTTPS certificate trust  

---

## 11. Recommended Setup (2025+)

| Tool | Recommendation |
|----|----------------|
| Visual Studio | **2022 (Latest)** |
| .NET SDK | **8 LTS (x64)** |
| Legacy support | VS 2019 (only if needed) |
| HTTPS | Enabled & trusted |

---

## 12. One-Line Summary

> Use Visual Studio 2022 with .NET 8 LTS for all modern development; Visual Studio 2019 is only for legacy .NET Framework or .NET 5 projects.

---

Happy Coding 🚀

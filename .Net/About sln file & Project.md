## How to Create .snl file

**Step-1:**
Open file path in Command Prompt

**Step-2:**
type
```cmd
dotnet new sln --name "EnterAnyFileName"
```
`now your .sln file is created on desire location`

## How to Add a Project to a Solution (.sln)
1. Right‑click the **Solution** in Solution Explorer  
2. Select **Add → New Project** or **Add → Existing Project**
3. Select template or existing `.csproj`
4. Click **Create / Open**

**Note:**if you have more than one project in your .sln file, then right click on any project and then click `set as startup project`
rebuilt project

---

## How to Remove a Project from a Solution
1. Right‑click project name
2. Click **Remove**
> This removes it only from solution, not from disk.

#### Fully Delete
1. Right‑click → **Remove**
2. Delete the folder manually in File Explorer

---

## How to Add Reference Between Projects
1. Right‑click **Dependencies** (or References) on target project
2. Choose **Add Project Reference**
3. Check the project you want to reference
4. Click **OK**

---

## How to Remove a Project Reference
1. Expand **Dependencies**
2. Expand **Projects**
3. Right‑click referenced project → **Remove**
---

## How to Set Startup Project
1. Right‑click the project you want as startup
2. Click **Set as Startup Project**

#### Multiple Startup Projects
1. Right‑click **Solution**
2. **Properties → Startup Project**
3. Select **Multiple startup projects**
4. Set actions to **Start**

---

## Additional Useful Settings

| Setting | How to access |
|---------|--------------|
| Change .NET Version | Right‑click Project → **Properties → Application → Target framework** |
| Enable/Disable NuGet restore | Right‑click Solution → **Restore NuGet Packages** |
| Add NuGet Package | Right‑click project → **Manage NuGet Packages** |
| Rebuild Solution | Menu → **Build → Rebuild Solution** |
| Clean Solution | **Build → Clean Solution** |
| Reload Unloaded Project | Right‑click **(unloaded)** project → **Reload** |
| Open project folder | Right‑click project → **Open Folder in File Explorer** |
| Open .sln in editor | Right‑click solution → **Open Folder in File Explorer**; edit `.sln` manually |

---

## Useful Commands

```sh
dotnet build
dotnet clean
dotnet restore
dotnet run --project ProjectName
```

---

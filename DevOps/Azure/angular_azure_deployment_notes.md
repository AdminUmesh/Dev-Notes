# 🚀 Angular Deployment on Azure App Service (Step-by-Step)

## 📌 Overview
This guide explains how to deploy an Angular application to Azure using App Service and ZIP upload.

---

# 🧱 Step 1: Create Resource Group

1. Go to Azure Portal
2. Click **Resource Groups**
3. Click **Create**
4. Enter:
   - Name: `practice1`
   - Region: your nearest region
5. Click **Review + Create → Create**

---

# 🌐 Step 2: Create Web App (Angular Hosting)

1. Click **Create Resource**
2. Search **Web App**
3. Click **Create**

### Fill details:
- Resource Group → `practice1`
- Name → unique (e.g., `umesh-angular-app`)
- Publish → Code
- Runtime → Node 20 or Node 22
- OS → Windows
- Region → same as backend

4. Select pricing plan (Free or Basic)
5. Click **Review + Create → Create**

---

# ⚙️ Step 3: Build Angular Project

Open terminal in Angular project:

```bash
ng build --configuration production
```

👉 Output folder:
```
dist/your-project-name
```

---

# 📁 Step 4: Prepare Build Folder

Go inside:
```
dist/your-project-name
```

Ensure it contains:
- index.html
- main.js
- assets/

---

# ⚠️ Step 5: Add Routing Fix (IMPORTANT)

Create file **web.config** inside the folder:

```xml
<configuration>
  <system.webServer>
    <rewrite>
      <rules>
        <rule name="Angular Routes" stopProcessing="true">
          <match url=".*" />
          <conditions logicalGrouping="MatchAll">
            <add input="{REQUEST_FILENAME}" matchType="IsFile" negate="true" />
            <add input="{REQUEST_FILENAME}" matchType="IsDirectory" negate="true" />
          </conditions>
          <action type="Rewrite" url="/" />
        </rule>
      </rules>
    </rewrite>
  </system.webServer>
</configuration>
```

---

# 📦 Step 6: Create ZIP File

👉 Select ALL files inside folder (NOT the folder itself)

❌ Wrong:
```
your-project-name/
```

✅ Correct:
```
index.html
main.js
assets/
web.config
```

👉 Right-click → Compress to ZIP

---

# ☁️ Step 7: Upload to Azure

1. Go to your Web App → `umesh-angular-app`
2. Click **Deployment Center**
3. Under **Source**, select:
   👉 **Public files (new)**

4. Upload your ZIP file

---

# ⏳ Step 8: Wait for Deployment

- Azure will extract files automatically
- Takes ~10–30 seconds

---

# 🌍 Step 9: Run Your App

Open:

```
https://your-app-name.azurewebsites.net
```

🎉 Your Angular app is live!

---

# ⚠️ Common Issues

## ❌ Blank Page
- Check `web.config` exists

## ❌ Routing Not Working
- Missing rewrite rule

## ❌ API Not Working
- Update API URL in:

```ts
environment.prod.ts
```

```ts
apiUrl: 'https://your-api-url'
```

## ❌ CORS Error
Fix in backend (.NET):

```csharp
builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowAll",
        policy => policy.AllowAnyOrigin()
                        .AllowAnyHeader()
                        .AllowAnyMethod());
});

app.UseCors("AllowAll");
```

---

# 🎯 Summary

- Build Angular → `ng build`
- Add `web.config`
- Zip files (not folder)
- Upload via Deployment Center
- Open Azure URL

---

🚀 Angular App Successfully Hosted on Azure!

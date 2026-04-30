# 🚀 .NET 8 API Deployment on Azure (Visual Studio Direct Publish)

## 📌 Step 1: Create Resource Group
1. Go to Azure Portal
2. Click **Resource Groups**
3. Click **Create**
4. Enter name (e.g., `practice1`)
5. Select region
6. Click **Review + Create → Create**

---

## 📌 Step 2: Create Web App (App Service)
1. Click **Create Resource**
2. Search **Web App**
3. Click **Create**

Fill details:
- Resource Group → `practice1`
- Name → unique (e.g., `umesh-api-demo123`)
- Publish → Code
- Runtime → .NET 8 (LTS)
- OS → Windows
- Region → nearest

4. Select pricing plan (Free F1 recommended for practice)
5. Click **Review + Create → Create**

---

## 📌 Step 3: Publish from Visual Studio

1. Right-click project → **Publish**
2. Select **Azure**
3. Select **Azure App Service (Windows)**
4. Sign in to Azure
5. Select your Resource Group → `practice1`
6. Select your Web App
7. Click **Next**

---

## 📌 Step 4: API Management Step

- When prompted:
  👉 Select **Skip this step**

8. Click **Next**
9. Click **Finish**
10. Click **Publish**

---

## 📌 Step 5: Verify Deployment

Open browser:
```
https://your-app-name.azurewebsites.net/swagger
```

---

## ⚠️ Important Notes

- Root URL `/` may show 404 → This is normal for APIs
- Always test `/swagger` or `/api/...`

---

# 🔧 How to Change Connection String (Azure)

## ❌ Do NOT edit appsettings.json on Azure

Azure uses **Environment Variables** instead.

---

## ✅ Steps

1. Go to Azure Portal
2. Open your **Web App**
3. Click **Environment variables**
4. Go to **Application settings**
5. Click **+ Add**

---

## 📝 Add Connection String

```
Name: ConnectionStrings__DefaultConnection
Value: Server=YOUR_AWS_ENDPOINT;Database=YourDB;User Id=USER;Password=PASSWORD;
```

---

## ⚠️ Important Rules

- Use `__` (double underscore), NOT `:`
- Replace `localhost` with actual DB server
- Click **Apply → Apply**
- Restart App Service

---

## 🧪 Test Again

```
https://your-app-name.azurewebsites.net/api/your-endpoint
```

---

# 🎯 Common Mistakes

- Using `localhost` in Azure ❌
- Not restarting after config change ❌
- Wrong DB credentials ❌
- Swagger not enabled in production ❌

---

# ✅ Summary

- Resource Group → container
- Web App → hosting service
- Publish → deploy code
- Environment Variables → override config

---

🚀 Your API is now live on Azure!

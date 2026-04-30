# 🌐 Custom Domain Setup for Angular App on Azure (Step-by-Step)

## 🎯 Goal
Map your Azure hosted Angular app to your custom domain:

```
https://umeshsingh.xyz → Azure App Service → Angular App
```

---

# 🧠 Understanding the Problem

Your domain was previously pointing to GitHub Pages:

```
A @ → 185.199.xxx.xxx (GitHub)
CNAME www → adminumesh.github.io
```

👉 You need to remove these and point to Azure.

---

# 🚨 Step 1: Remove Old DNS Records (GitHub)

Delete these records:

```
A @ → 185.199.108.153
A @ → 185.199.109.153
A @ → 185.199.110.153
A @ → 185.199.111.153
```

Also delete:

```
CNAME www → adminumesh.github.io
```

---

# ✅ Step 2: Add Azure DNS Records

## 🔹 A Record (Main Domain)

```
Type: A
Name: @
Value: 52.140.106.224
TTL: 1/2 Hour
```

---

## 🔹 TXT Record (Verification)

```
Type: TXT
Name: asuid
Value: 021807AA20707878554E11B2459D57F6D8333B2B3777DBB9F6BFB59EF69821A5
```

---

## 🔹 CNAME Record (Optional for www)

```
Type: CNAME
Name: www
Value: umesh-angular-app-cqdygxf4b0f6cvf7.centralindia-01.azurewebsites.net
```

---

# ⏳ Step 3: Wait for DNS Propagation

- Usually 5–15 minutes
- Sometimes up to 1 hour

---

# ☁️ Step 4: Add Domain in Azure

1. Go to Azure Portal
2. Open your Web App
3. Click **Custom Domains**
4. Click **+ Add custom domain**

Enter:

```
umeshsingh.xyz
```

Then:
- Click **Validate**
- Click **Add**

---

# 🔒 Step 5: Enable HTTPS (SSL)

1. Choose:
   - App Service Managed Certificate (Free)
2. Select:
   - TLS Type: SNI SSL
3. Wait ~5 minutes

---

# 🧪 Step 6: Test Your Domain

Open:

```
https://umeshsingh.xyz
```

And:

```
https://umeshsingh.xyz/user/dashboard
```

---

# 🎯 Final Architecture

```
User → Domain (umeshsingh.xyz) → Azure App Service → Angular App
```

---

# ⚠️ Common Mistakes

- ❌ Not removing GitHub DNS records
- ❌ Missing TXT record (verification fails)
- ❌ Using wrong IP address
- ❌ Not waiting for DNS propagation
- ❌ Not enabling HTTPS

---

# 🚀 Result

Your Angular app is now:
- 🌐 Live on custom domain
- 🔐 Secured with HTTPS
- ⚡ Production ready

---


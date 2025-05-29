
# What is RDLC?

**RDLC** stands for **Report Definition Language Client-side**.

It’s a report format developed by Microsoft and used to design and display reports within .NET applications such as **WinForms**, **ASP.NET**, and with some adjustments, even in **.NET Core**.

---

## 🧾 What is it used for?

RDLC files are used to create **local reports** that are:
- **Processed and rendered on the client-side** (no need for a report server).
- Useful for **embedding reporting features directly into the application**.

### Key Features:
- Supports **tables**, **charts**, **labels**, and more.
- Can use **DataTables**, **Lists**, or other objects as a data source.
- Ideal for **printing**, **exporting**, or **viewing structured data**.

---

## 🗣️ How to Explain in an Interview

You can say:

> "RDLC is a Microsoft report format used for creating client-side reports in .NET applications. It allows developers to design and display reports within the app itself, without needing a report server. RDLC reports take data from the app and format it into tables, charts, and other visual components for printing or viewing."

---

## 🎯 RDLC vs SSRS

| Feature | RDLC | SSRS |
|--------|------|------|
| Processing | Client-side | Server-side |
| Requires Report Server | ❌ No | ✅ Yes |
| Deployment | Inside App | On Report Server |
| Use Case | Lightweight, app-embedded | Enterprise-level, centralized reports |

---

## ✅ Summary

RDLC is great when you need:
- **Lightweight, local reporting**
- **No dependency on SQL Server Reporting Services**
- **Easy integration with .NET applications**

# Azure Database

## Azure Supports Multiple Database Types

### Relational Databases
- Azure SQL Database → Managed SQL Server database (PaaS)
- SQL Server on Azure VM (IaaS)
- Azure Database for MySQL
- Azure Database for PostgreSQL

# 1. Azure SQL Database

### How to Created
- Create `SQL Server` (logical server) → container
- Create `SQL Database` → actual database

## 1.1. Step-by-Step Creation

1. Create SQL Server (logical server)
2. Create SQL Database under it
3. Configure firewall 
4. Connect using SSMS

---

## 1.2. BACPAC File

### What is BACPAC?
- A file containing:
  - Database schema
  - Data

### Why use it?
- Migration (local → Azure)
- Backup & restore

### Steps:
1. Export from local DB (SSMS)
2. Import into Azure SQL

---

## 1.3. Firewall Configuration

### What is Firewall?
- Security layer that controls access to database

### Why Important?
- Prevents unauthorized access
- Only allowed IPs can connect

### What you did:
- Enabled public access
- Added your IP
- Allowed Azure services

---

## 1.4. Common Issues

- Cannot connect → Firewall issue
- Login failed → Wrong credentials
- Empty DB → Data not imported

---

## 1.5. Key Concepts

- Azure SQL Database = PaaS
- Logical server = container
- Same T-SQL as SQL Server

# 2. SQL Server on Azure VM

## 2.1: Search Correct Resource

Search: `SQL Server 2022 on Windows Server 2022` (or 2025 version)

## 2.2: Create VM

**Fill basics:**

- Resource Group → new/existing
- VM Name → sqlvm1
- Region → Central India
- Username → admin user
- Password → strong password

## 2.3: Size

Choose small size for practice:(avoid expensive size)

B1ms / B2s / low-cost option

## 2.4: SQL Settings

During setup Azure may ask:

SQL connectivity → Public / Private
SQL Authentication login
Port → 1433

Use simple test config.

## 2.5: Create

👉 Review + Create

**After Creation You’ll get:**

- VM public IP
- RDP access
- SQL Server running inside VM

## 2.6: How to Use
Use:👉 Remote Desktop Connection

`IP address + Windows username/password`

**Inside VM**
Open:
- SSMS
- SQL Server Configuration
- Restore DB

# How to Get Public IP

1. Microsoft Azure Portal
2. Open All Resources
3. **Click:** `myVm-ip`

🔥 Connect with Remote Desktop (RDP) and enjoy

# 3. Azure Database for MySQL
Managed MySQL Server + Databases

## How to Created
### 3.1 Open Azure Portal
Search: `Azure Database for MySQL`

Choose: `Azure Database for MySQL flexible server`

### 3.2 Create Resource

### 3.3 Fill Basics?
Enter:

- Subscription
- Resource Group → create/select
- Server name → unique **Ex:-** `umeshmysql123`
- Region → Central India
- MySQL version → latest/default
- Workload type → Development / Burstable

**Admin Credentials**
Admin username
Password
Confirm password

**Networking**

Choose: `public access` 
(easy for learning)

### 3.4 Review + Create

## Finally: Connect to MySQL 🔥

- MySQL Workbench
- .NET app
- SQLyog

### Enter
**Server:** `umeshmysql123.mysql.database.azure.com`
**User:** `adminuser`
**Password** ``
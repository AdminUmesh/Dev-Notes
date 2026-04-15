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


## 2. Step-by-Step Creation

1. Create SQL Server (logical server)
2. Create SQL Database under it
3. Configure firewall
4. Connect using SSMS

---

## 3. BACPAC File

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

## 4. Firewall Configuration

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

## 5. Common Issues

- Cannot connect → Firewall issue
- Login failed → Wrong credentials
- Empty DB → Data not imported

---

## 6. Key Concepts

- Azure SQL Database = PaaS
- Logical server = container
- Same T-SQL as SQL Server



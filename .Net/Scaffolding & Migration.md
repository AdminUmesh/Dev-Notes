# Scaffolding
Scaffolding a DbContext in .NET Core is a process used to generate entity classes and a DbContext class based on an existing database schema. This is particularly useful when you want to work with an existing database using Entity Framework Core (EF Core). 

## Required Tools:
**Before scaffolding, you need to install the necessary tools and packages:**

```powershell
#Add Required Packages
dotnet add package Pomelo.EntityFrameworkCore.MySql

dotnet add package Microsoft.EntityFrameworkCore.Tools
```

## Command Usage:
**The basic command structure for scaffolding is:**

```powershell
Scaffold-DbContext "Server=localhost;User ID=root;Password=admin@123;Database=csatcloud_id" MySql.EntityFrameworkCore -OutputDir Models -f -Context CsatcloudTestingContext
```
## Explaination
1. **Scaffold-DbContext:** This is the command used to generate entity classes and a database context based on an existing database.

2. **"Server=localhost;User ID=root;Password=admin@123;Database=csatcloud_id":** This is the connection string that specifies how to connect to the MySQL database.

3. **MySql.EntityFrameworkCore:** This indicates that the MySQL provider for Entity Framework Core is being used to generate the context and models.

4. **-OutputDir Models:** This specifies the output directory where the generated entity classes will be placed. In this case, they will be stored in a folder named "Models."

5. **-f:** This flag stands for "force." It tells the tool to overwrite any existing files in the output directory.

6. **-Context CsatcloudTestingContext:** This specifies the name of the generated DbContext class. In this case, it will be named CsatcloudTestingContext.

**How can we generate only entity classes**
```powershell
Scaffold-DbContext "Server=localhost;User ID=root;Password=admin@123;Database=csatcloud_id" MySql.EntityFrameworkCore -OutputDir Models -f --No-Context
```
`--No-Context: This option tells the command to generate only the entity classes and not the DbContext class.`

**How can we generate only DbContext**
```powershell
Scaffold-DbContext "Server=localhost;User ID=root;Password=admin@123;Database=csatcloud_id" MySql.EntityFrameworkCore -OutputDir Models -f --No-Models
```
`--No-Models: This option tells the command to generate only the DbContext class and not the entity classes.`

# Migrations
Migrations, on the other hand, are a way to manage changes to your database schema over time. When you modify your model (e.g., adding a new property or creating a new entity), migrations help you apply these changes to the database. Here’s how it works:

## Add Migration:
When you create a migration, EF Core generates a set of instructions (in code) that describe how to transform the current database schema to match your updated model.

```powershell
# if you have only one DbContext
Add-Migration MigrationName
```
1. **Add-Migration Testing: This part creates a new migration named "Testing."**

```powershell
# if you have more than one DbContext
Add-Migration Testing -Context CsatcloudTestingContext
```
1. **Add-Migration Testing:** This part creates a new migration named "Testing."

2. **-Context CsatcloudTestingContext:** This specifies which DbContext to use when creating the migration. In this case, it's the CsatcloudTestingContext you defined earlier.

**Above command create two files in Migration Folder**

## Update Database:
 You then apply these migrations to the database, which updates its schema without losing existing data.

```powershell
# if You have one DbContext
Update-Database

# if You have Multiple DbContext
Update-Database -Context CsatcloudTestingContext

```
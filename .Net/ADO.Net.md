# What is ADO.Net

`the ADO.net classes are located into System.Data.dll and integrated with XML classes located into System.Xml.dll`
ADO.Net is a database access technology that created by Microsoft, ADO.net framework that can access any kind of data source. `ADO.net provides a bridge between the front-end controls and the backend database`

# What is DataProvider
Data provider is used to connect Database to DotNet Application, it is used for connecting to a database, executing commands, and retrieving results.
`The .Net Application can not directly execute our SQL code. so we use DataProvider`

- `SQLClient` DataProvider is used to connect Application with SQL Server. Its present in `System.Data.SQLClient` namespace

- `OracleClient` DataProvider is used to connect Application with Oracle. Its present in  `System.Data.OracleClient` namespace

- OleDb DataProvider is used to connect Application with MsAccess. Its present in `System.Data.OleDb` namespace

- Odbc DataProvider is used to connect Application with Configure database. Its present in `System.Data.Odbc` namespace

**All above Data Provider have Some Common classes.**
1. Connection Class- maintain connection with database
2. Command Class-for execute queiry
3. DataReader Class- used to read data of desire queiry
4. DataAdapter Class- in a table can work with different datasets

**How to use Connection Class with diffenent database Providers**
- For SQLClient -`SQLConnection`
- For OracleClient -`OracleConnection`
- For OleDb  -`OleDbConnection`
- For Odbc  -`OdbcConnection`

**What type of application used ADO.Net**
- ASP.NET web form application
- Windows form application
- asp.net mvc application
-  asp.net web api application  

## Componet of ADO.net
-  connection, Command, DataReader, DataAdapter, DataSet and DataView are the component of ADO.Net that are used to perform database operation.

**adio.net has two main comonents that are used for accessing and manipulatind data.**
1. Data provider (connection, Command, DataReader, DataAdapter)
2. DataSet

**Connection database**
```cs
using System.Data // for ConnectionState
using System.Data.sqlClint;  // for SqlConnection
namespace{
   string cs="data source=DEV-UMESH\SQLEXPRESS; Initial Catalog=Cabsdata_210014; UID=sa; Password=master@123";
   string cs="data source=DEV-UMESH\SQLEXPRESS; Initial Catalog=Cabsdata_210014; Integrated Security=True";
   SqlConnection con =new SqlConnection(cs);
   try{
       con.Open();
       if(con.state == ConnectionState.Open){
           Console.Writeline("Connection has  been created Successfully");
        }   
    }catch(SqlException ex){
        Console.Writeline(ex.message);
    }
    finally
    {
        con.Close();
    }
}
```

**Using block is used to close the connection automatically. we don't need to call close() method explicitly**
```cs
using (SqlConnection con = new SqlConnection(cs)){
    try{
        con.Open();
       if(con.state == ConnectionState.Open){
           Console.Writeline("Connection has  been created Successfully");
        }  
    }catch(SqlException ex){
        Console.Writeline(ex.message);
    }
}
```

## Constructors of SQLConnection Class of ADO.Net
- SqlConnection()
- SqlConnection(String)
- SqlConnection(String, SqlCredential)

## ConnectionString
- when we connect of an object is instantiated, the controctor takes a connection string that contains the information about the database server, server type, databse name, cnnection type, and database user credentials.

**a connection string is usually stored in web.config file or app.config file of an application.**
```cs
<connectionStrings>
    <add name="210014" connectionString="data source=DEV-UMESH\SQLEXPRESS; Initial Catalog=Cabsdata_210014; UID=sa; Password=master@123; connect timeout=0" providerName="System.Data.sqlClient"/>
</connectionStrings>
```

# SqlCommand Class in ADO.Net

it is used to store and execute the SQL Statement against the SQL Server database.
- ADO.Net-> SqlClint (ddataProvider)->SqlCommand (like insert, Update and Delete)

**Procedure for retrive data from database**
 1. Connect to the Database
 2. Prepare an Sql Command
 3. Execute the command
 4. Retrieve the results and display in the application.

 **Way of use SqlCommand Class in ADO.Net**

 - SqlCommand();
 - SqlCommand(string CmdTextQuery);
 - SqlCommand(string CmdTextQuery, SqlConnectuon Connection);
 - SqlCommand(string CmdTextQuery, SqlConnectuon Connection, SqlTransaction transaction);
 - SqlCommand(string CmdTextQuery, SqlConnectuon Connection, SqlTransaction transaction, SqlCommandColumnEncryptionSetting columnEncryptionSetting);

 ## Method Of SqlCommand Class
 1. **ExecuteReader()**
     this is used when we need to select, read data from database. (Its return the object of SqlDataReader, so you must have to ceate a abject of sqlDataReader for get the return)
     `it is used when the T-SQL statement returns more than a single value. For Example:- if the query returns rows of data like all employee detail;`
     ```cs
     // SqlCommand(string CmdTextQuery, SqlConnectuon Connection);
     CommandText = "select * from Rms_Dynamic_Rate";

     SqlCommand cmd = new SqlCommand(CommandText, con);
     SqlDataReader dr = cmd.ExecuteReader();

     while(dr.read()){
        Console.WriteLine("MyId= " +dr["Id"]);
     }
     ```

     ```cs
     // SqlCommand();
     using System.Configuration;
     try{
        conn = new SqlConnection(ConfigurationManager.ConnectionStrings["ConnectionString_name"].ConnectionString);
        conn.Open();

        SqlCommand cmd = new SqlCommand();
        // Use cmd.CommantType for storeprosedure
        cmd.CommandText = "select * from Rms_Dynamic_Rate";
        cmd.Connection = conn;
        SqlDataReader dr = cmd.ExecuteReader();

        while(dr.read()){
        Console.WriteLine("MyId= " +dr["Id"]);
        }
    }
     ```
     
 2. **ExecuteNonQuery()**
 this is used when we insert, Update, Delete some value in databse.
```cs
String name="Umesh";
string id="1";
string query= "insert into chut values(@name, @id)";

SqlCommant cmd= new SqlCommand(query, con);
cmd.Parameters.AddWithValue("@name",name);
cmd.Parameters.AddWithValue("@id",id);

con.open();
int rowEffect = cmd.ExecuteNonQuery();

if(rowEffect>0){
    Console.WritelLine("Data has been inserted successfully");
}
```

 3. **ExecuteScalar()**
 this is used when we run select command With Aggregate function[count(), Min(), Max(), Avg(), Sum()] or Scalar Function 
 `It is used when the query returns a single(scalar) value. For Example- queries that return the total number of rows in table or the employee who gets highest salary etc.`

 ```cs
string query= "Select max(salary) from employee";
SqlCommant cmd= new SqlCommand(query, con);

con.open();
int rowEffect = (int)cmd.ExecuteScalar();

if(rowEffect>0){
    Console.WritelLine("Data has been inserted successfully");
}
```
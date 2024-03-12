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

# SqlDataReader Class
The DataReader object in C# ADO.Net allows you to retrieve data from database in read-only and forward-only mode.
`it meands you can only read and display data but can't update or delete data. and only you can retrieve data like 1st row then 2nd row. its not possible to retrieve 2nd row to 1st.`

- if you want to make modification in retrieve data you need to use DataAdapter instead of DataREader

```cs
// SqlCommand(string CmdTextQuery, SqlConnectuon Connection);
CommandText = "select * from Rms_Dynamic_Rate";

SqlCommand cmd = new SqlCommand(CommandText, con);
SqlDataReader dr = cmd.ExecuteReader();

     while(dr.read()){
        Console.WriteLine("MyId= " + dr["Id"] + "Name=" + dr["name"]);
        //or
        Console.WriteLine("MyId= {0} Name={1}, dr["Id"],dr["name"]");
     }
```

- Object of SqlDataReader cannot be created using new keyword. like `SqlDataReader dr = new SqlDataReader()`

Ex:- You can make it like
```cs
SqlDataReader dr = cmd.ExecuteReader(); // this will return SqlDataReader Instance.
```

- ExecuteReader() method of SqlCommand returns an object of SqlDataReader.
- SqlDataReader is read-only. it means it not possible to change the data using SqlDataReader
- you can also need to open and close the connection explicitly. or either use it under using keyword 
```cs  
using (SqlDataReader reader = command.ExecuteReader())
{
    // it will closed the SqlDataReader connection automatically
}
```


## Different Data Provider with their respective DataReader Class
- For SQLClint-->SqlDataReader
- For OracleClient-->OracleDataReader
- For OleDb-->OleDbDataReader
- For Odbc-->OdbcDataReader

## Property of SqlDataReader Class

1. **FieldCount (int)-** return number of columns in the current row.

2. **HasRows (boolean)-** return true if System.Data.SqlClient.SqlDataReader contains one or more rows. else return false.

4. **IsClosed-** it rtrieve the boolean value that indicates whether the specified System.Data.SqlClient.SqlDataReader instance has been closed or not

5. **Item[String]-** it get the value of the specified column in its native formate given the column name.

6. **Item[int32]-** it gets the value of the specified column in its native formate given the column ordinal

## Methods of SqlDataReader Class
**Read():** it returns true if the System.Data.SqlClient.SqlDataReader has next record. if returns false;

**GetName(int i)** it gets the name of the specified column. Here, Parameter i is the zero-based column ordinal;

**NextResult():** it advances the data reader to the next result when reading the results of batch Transact-SQL statement.
```cs
CommandText = "select * from Rms_Dynamic_Rate; select * from Ryms;"; // two queries from diffrent taables

SqlCommand cmd = new SqlCommand(CommandText, con);
SqlDataReader dr = cmd.ExecuteReader();

    while(dr.read())
    {
    Console.WriteLine("{0} {1} {2}", dr[0], dr[1], dr[2]); // retrieve only 1st, 2nd and 3rd columns from 1st query
    }
    Console.WriteLine("-------------");

    if(dr.NextResult())
    {    
    //check if have any other result in SqlDataReader
    while(dr.read())
    {
       Console.WriteLine("{0} {1} {2}", dr[0], dr[1], dr[2]); // retrieve only 1st, 2nd and 3rd columns from 2ndst query
    }
    }
```

# SqlDataAdapter Class in ADO.Net
SqlDataAdapter class is found in the System.Data.SqlClient namespace. it works as a bridge between DataSet/DataTable and DataBase. `In SqlDataAdapter there's no need to open and close connection like SqlCommand.`

## 1. Connected Data Access
1. **SqlDataReader**

## 2. Dis-Connected Data Access
1. **DataSet**

DataSet Present in System.Data namespace. it store table and relationship and its common for all data provider
`Database stored table in Hardisk but dataset store table in web-server`

```cs
// SqlCommand(string CmdTextQuery, SqlConnectuon Connection);
CommandText = "select * from Rms_Dynamic_Rate";
SqlConnection con = new SqlConnection(ConnectionStrong);
SqlDataAdapter sda = new SqlDataAdapter(CommandText, con)

DataSet ds = new DataSet();
sda.Fill(ds); //fill all sda data into ds and close the connection automatically

foreach(DataRow row in ds.Tables["employee_tb1"]){
    //if we have two or more table then use ds.Tables["Table_name"]
}

foreach(DataRow row in ds.Tables[0].Rows){  //Tables[0] it means we want to get the value of 0th index table (if we have only one table)
    Console.Writeline("{0} {1}", row[0], row[1]);
}
```

2. **DataTable**

if we work with single table then we have to work with DataTable for better approach
```cs
// SqlCommand(string CmdTextQuery, SqlConnectuon Connection);
CommandText = "select * from Rms_Dynamic_Rate";
SqlConnection con = new SqlConnection(ConnectionStrong);
SqlDataAdapter sda = new SqlDataAdapter(CommandText, con)

DataTable dt = new DataTable();
sda.Fill(dt); //fill all sda data into ds and close the connection automatically

foreach(DataRow row in dt.Rows){  //Tables[0] it means we want to get the value of 0th index table (if we have only one table)
    Console.Writeline("{0} {1}", row[0], row[1]);
}
```
**Fill Method of SqlDataAdapter**
- it opens the connection to the database, executes the SQL command, fills the dataset and data tables with the data, and close the connection.
-  this method handles the opening and closing connections automatecally for us.

# DataTable in ADO.Net
Datatable represents relational data into tabular form.
DataTable in C# is similar to tables in SQL
You can also be used with DataSet also

we can create table schema by adding columns and constraints to the table.
After defining table schema, we can add  rows to the table 
Datatable is a combination of DataColumn and DataRow

## Properties of DataColumn in ADO.Net
- Caption
- DataType
- AllowDBNull
- MaxLength
- PrimaryKey-> Properties of DataTable
- AutoIncrement
  1. AutoIncrementSeed-> Starting Value
  2. AutoIncrementStep-> Increment Value

  ```C#
  DataTable employees = new DataTable("employees"); // Create a new DataTable
  DataColumn id = new DataColumn("Id"); // Create a DataColumn named "Id"

  id.Caption = "Emp_id"; // Create a DataColumn named "Id"
  id.DataType= typeof(int); //or id.DataType =System.Type.GetType("System.Int32");
  id.AllowDBNull = false; // is Null allowed or not
  id.AutoIncrement = true; // Enable auto-increment for the column
  id.AutoIncrementSeed = 10; // Start auto increment from
  id.AutoIncrementStep = 1;  //increment by 1 means 10,11,12
   
  employees.Column.Add(id);// Add the column to the DataTable

  //Step-2 for creaing column
  DataColumn id = new DataColumn("Id")
  {
    Caption = "Emp_id";
    DataType= typeof(int);
    AllowDBNull = false;
  }
  employees.Column.Add(id);
  
  // Adding a Column of String Type
  DataColumn name = new DataColumn("name");
  name.Caption = "Emp_Name";
  name.DataType= typeof(int);
  name.MaxLength =50; // Max-length of name
  name.AllowDBNull = false;
  name.DefaultValue="Umesh"; //Default value of name if input is empty
  name.Unique=true; // make a column qnique

  employees.Column.Add(name);

  // Create a new DataRow and assign values to it
  DataRow row1 = employees.NewRow();
  row["id"]=1; //Assign values to the columns using the column names
  row["name"]="Male";
  employees.Rows.Add(row1); // Add the DataRow to the DataTable
  //or
  employees.Rows.Add(2, "Anum", "Female"); // Add the DataRow and assign value
  // Set the primary key for the DataTable
  employees.PrimaryKey= new DataColumn[] {id}; // Use "id" as the primary key

  foreach (DataRow row in employees.Row)
  {
    Console.Writeline(row["id"] + " " + row["name"]);
  }
  
  ```

  # Copy() & Clone() Methods of DataTable in ADO.Net

  - **DataTable.Copy()** return a DataTable with the structure and data of the DataTable

  - **DataTable.Clone()** only returns the structure of the DataTable, not the rows or data of the DataTable.

  ```c#
  DataTable employees = new DataTable("employees");
  foreach (DataRow row in employees.Row)
  {
    Console.Writeline(row["id"] + " " + row["name"]);
  }


  DataTable CopyDataTable = employees.Copy();  // Copy all structure and data of employee table
  foreach (DataRow row in CopyDataTable.Row)
  {
    Console.Writeline(row["id"] + " " + row["name"]); // return same result as employees data table
  }


  DataTable CloneDataTable = employees.Clone();  // Copy only structure not data of employee table
  if(CloneDataTable.Rows.Count > 0){
    // this will nevet true because Clone() never store data
  }else{
    // Console.WriteLine("Rows Not Found");
    CloneDataTable.Rows.Add(1, "Umesh");
    CloneDataTable.Rows.Add(1, "Subham"); // you can add data because your structure is available
  }

  foreach (DataRow row in CloneDataTable.Row)
  {
    Console.Writeline(row["id"] + " " + row["name"]); // return above added result of CloneDataTable
  }
  ```

  # DataSet
   `go google or Youtube`
   - **Resouce:-** https://youtu.be/kkoct1QkGRo?si=Dn_uL5AF6FNiVt5x
  # DataSet vs DataTable
  `go google or Youtube`
  - **Resouce:-** https://youtu.be/Sq_cQNz-33Y?si=Lq2nXKQqCA4GVMZa
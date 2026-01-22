## API
install NuGet package `Dapper`

```c#
using Dapper;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient;
using System.Data;
using DapperPractice.Models;

namespace DapperPractice.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class ConnectionController : ControllerBase
    {
        // ✅ Dummy Connection String (Change DB name if needed)
        private readonly string _connectionString =
            "Data Source=localhost;Initial Catalog=Practice;Integrated Security=True;TrustServerCertificate=True;";

        // ===============================
        // 1️⃣ GET ALL EMPLOYEES
        // ===============================
        [HttpGet("get-all")]
        public IActionResult GetAllEmployees()
        {
            using IDbConnection con = new SqlConnection(_connectionString);

            var employees = con.Query<Employee>(
                "SELECT * FROM Employees"
            );

            return Ok(employees);
        }

        // ===============================
        // 2️⃣ GET EMPLOYEE BY ID
        // ===============================
        [HttpGet("get-by-id/{id}")]
        public IActionResult GetEmployeeById(int id)
        {
            using IDbConnection con = new SqlConnection(_connectionString);

            var employee = con.QueryFirstOrDefault<Employee>(
                "SELECT * FROM Employees WHERE Id = @Id",
                new { Id = id }
            );

            if (employee == null)
                return NotFound("Employee not found");

            return Ok(employee);
        }

        // ===============================
        // 3️⃣ INSERT EMPLOYEE
        // ===============================
        [HttpPost("insert")]
        public IActionResult InsertEmployee(Employee emp)
        {
            try
            {
                using IDbConnection con = new SqlConnection(_connectionString);

                string sql = @"INSERT INTO Employees (Name, Salary)
                       VALUES (@Name, @Salary)";

                int rows = con.Execute(sql, emp);

                return Ok(new
                {
                    Success = true,
                    Message = "Employee Inserted",
                    RowsAffected = rows
                });
            }
            catch (SqlException ex)
            {
                // SQL-specific error
                return StatusCode(500, new
                {
                    Success = false,
                    ErrorType = "SQL Error",
                    ex.Number,        // SQL Error Code
                    ex.Message        // Actual DB error
                });
            }
            catch (Exception ex)
            {
                // Any other error
                return StatusCode(500, new
                {
                    Success = false,
                    ErrorType = "General Error",
                    ex.Message
                });
            }
        }

        // ===============================
        // 4️⃣ UPDATE EMPLOYEE
        // ===============================
        [HttpPut("update")]
        public IActionResult UpdateEmployee(Employee emp)
        {
            using IDbConnection con = new SqlConnection(_connectionString);

            string sql = @"UPDATE Employees
                           SET Name = @Name, Salary = @Salary
                           WHERE Id = @Id";

            int rows = con.Execute(sql, emp);

            return Ok(new
            {
                Message = "Employee Updated",
                RowsAffected = rows
            });
        }

        // ===============================
        // 5️⃣ DELETE EMPLOYEE
        // ===============================
        [HttpDelete("delete/{id}")]
        public IActionResult DeleteEmployee(int id)
        {
            using IDbConnection con = new SqlConnection(_connectionString);

            int rows = con.Execute(
                "DELETE FROM Employees WHERE Id = @Id",
                new { Id = id }
            );

            return Ok(new
            {
                Message = "Employee Deleted",
                RowsAffected = rows
            });
        }

        // ===============================
        // 6️⃣ GET COUNT (ExecuteScalar)
        // ===============================
        [HttpGet("count")]
        public IActionResult GetEmployeeCount()
        {
            using IDbConnection con = new SqlConnection(_connectionString);

            int count = con.ExecuteScalar<int>(
                "SELECT COUNT(*) FROM Employees"
            );

            return Ok(new { TotalEmployees = count });
        }

        // ===============================
        // 7️⃣ GET MAX SALARY
        // ===============================
        [HttpGet("max-salary")]
        public IActionResult GetMaxSalary()
        {
            using IDbConnection con = new SqlConnection(_connectionString);

            int maxSalary = con.ExecuteScalar<int>(
                "SELECT ISNULL(MAX(Salary),0) FROM Employees"
            );

            return Ok(new { MaxSalary = maxSalary });
        }

        // ===============================
        // 8️⃣ MULTIPLE RESULT SETS
        // ===============================
        [HttpGet("multiple-result")]
        public IActionResult GetMultipleResult()
        {
            using IDbConnection con = new SqlConnection(_connectionString);

            string sql = @"SELECT * FROM Employees;
                           SELECT COUNT(*) FROM Employees;";

            using var multi = con.QueryMultiple(sql);

            var employees = multi.Read<Employee>().ToList();
            var count = multi.Read<int>().FirstOrDefault();

            return Ok(new
            {
                Employees = employees,
                TotalCount = count
            });
        }
    }
}
```

## Model
```c#
namespace DapperPractice.Models
{
    public class Employee
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public int Salary { get; set; }
    }
}
```

## View
```html
@{
    ViewData["Title"] = "Dapper Practice";
}

<h2>Dapper Practice – MVC Test UI</h2>
<hr />

<!-- ===================== INSERT ===================== -->
<h4>Insert Employee</h4>
<input id="name" placeholder="Name" />
<input id="salary" placeholder="Salary" />
<button onclick="insertEmployee()">Insert</button>

<hr />

<!-- ===================== GET ALL ===================== -->
<h4>Get All Employees</h4>
<button onclick="getAll()">Get All</button>

<hr />

<!-- ===================== GET BY ID ===================== -->
<h4>Get Employee By Id</h4>
<input id="getId" placeholder="Employee Id" />
<button onclick="getById()">Get</button>

<hr />

<!-- ===================== UPDATE ===================== -->
<h4>Update Employee</h4>
<input id="updId" placeholder="Id" />
<input id="updName" placeholder="Name" />
<input id="updSalary" placeholder="Salary" />
<button onclick="updateEmployee()">Update</button>

<hr />

<!-- ===================== DELETE ===================== -->
<h4>Delete Employee</h4>
<input id="delId" placeholder="Id" />
<button onclick="deleteEmployee()">Delete</button>

<hr />

<!-- ===================== COUNT ===================== -->
<h4>Total Employees</h4>
<button onclick="getCount()">Get Count</button>

<hr />

<!-- ===================== MAX SALARY ===================== -->
<h4>Max Salary</h4>
<button onclick="getMaxSalary()">Get Max Salary</button>

<hr />

<!-- ===================== MULTIPLE RESULT ===================== -->
<h4>Multiple Result Set</h4>
<button onclick="getMultiple()">Get Multiple</button>

<hr />

<h3>Result</h3>
<pre id="result" style="background:#f4f4f4;padding:10px;"></pre>

<script>
    const baseUrl = "https://localhost:7129/api/Connection";

    function showResult(data) {
        document.getElementById("result").innerText =
            JSON.stringify(data, null, 2);
    }

    async function getAll() {
        const res = await fetch(`${baseUrl}/get-all`);
        showResult(await res.json());
    }

    async function getById() {
        const id = document.getElementById("getId").value;
        const res = await fetch(`${baseUrl}/get-by-id/${id}`);
        showResult(await res.json());
    }

    async function insertEmployee() {
        const data = {
            name: document.getElementById("name").value,
            salary: parseInt(document.getElementById("salary").value)
        };

        const res = await fetch(`${baseUrl}/insert`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        showResult(await res.json());
    }

    async function updateEmployee() {
        const data = {
            id: parseInt(document.getElementById("updId").value),
            name: document.getElementById("updName").value,
            salary: parseInt(document.getElementById("updSalary").value)
        };

        const res = await fetch(`${baseUrl}/update`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        showResult(await res.json());
    }

    async function deleteEmployee() {
        const id = document.getElementById("delId").value;

        const res = await fetch(`${baseUrl}/delete/${id}`, {
            method: "DELETE"
        });

        showResult(await res.json());
    }

    async function getCount() {
        const res = await fetch(`${baseUrl}/count`);
        showResult(await res.json());
    }

    async function getMaxSalary() {
        const res = await fetch(`${baseUrl}/max-salary`);
        showResult(await res.json());
    }

    async function getMultiple() {
        const res = await fetch(`${baseUrl}/multiple-result`);
        showResult(await res.json());
    }
</script>
```

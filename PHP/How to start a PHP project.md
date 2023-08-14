# To Start a PHP Project

- [@Reference](https://github.com/AdminUmesh/MVC-Login-Signup)

# Know about .htaccess file
Hypertext access is a configuration file of apache server. This is a hidden file with the help of this file we can achived many task like
- Redirecting www URLs to non-www (vice-versa).
- Redirect http URLs to https.
- Removing trailing slash from URLs.
- Redirect one page to anothor.
- Creating SEO friendly URL.
- Setting Custom 404 page.
- Increse the speed of website.

```
RewriteEngine On
RewriteCond %{REQUEST_FILENAME} -s [OR]
RewriteCond %{REQUEST_FILENAME} -l [OR]
RewriteCond %{REQUEST_FILENAME} -d
RewriteRule ^.*$ - [NC,L]
RewriteRule ^(.+)$ index.php?uri=$1 [QSA,L]
```

# index.php file
The index.php file is the skeleton of the website. Every page of a website is start from this file.

# MVC Structure
The Model-View-Controller (MVC) framework is an architectural/design pattern that separates an application into three main logical components Model, View, and Controller.

# MVC Setup (Routing)
```PHP
  <?php
class Controller extends route
{

    public static function content(): void
    {
        $URL = self::processURL();
        $Updated_Mode = $URL['Updated_Mode'];
        $page = $Updated_Mode . '.php';
        $control = $Updated_Mode;
        if (isset($control) && in_array($control, self::$viewPage)) {
            require __DIR__ . './../view/' . $page;
        } elseif (isset($control) && in_array($control, self::$modelPage)) {
            require __DIR__ . './../model/' . $page;
        } else {
        }
    }

    private static function getURL(): array
    {
        $data = explode('=', $_SERVER['QUERY_STRING']);
        return isset($data[1]) ? explode('/', $data[1]) : ["login"];
    }


    private static function processURL(): array
    {
        $var = sizeof(self::getURL());
        $Mode = self::getURL()[($var - 1)] ?? '';
        $Updated_Mode = !in_array($Mode, self::$viewPage) ? (!in_array($Mode, self::$indexPage) ? (!in_array($Mode, self::$modelPage) ? 'error' : $Mode) : $Mode) : $Mode;
        if ($Updated_Mode == 'error') {
            $Mode = self::getURL()[($var - 2)] ?? '';
            $Updated_Mode = !in_array($Mode, self::$viewPage) ? (!in_array($Mode, self::$indexPage) ? (!in_array($Mode, self::$modelPage) ? 'error' : $Mode) : $Mode) : $Mode;
            return [
                'Updated_Mode' => $Updated_Mode,
            ];
        }
        return [
            'Updated_Mode' => $Updated_Mode,
        ];
    }
}
class route
{
    protected static $viewPage = ['insert', 'admin_insert', 'register', 'error', 'dbform', 'welcome', 'login', 'admin', 'update'];
    protected static $modelPage = ['registerdata', 'logindata', 'dbconnection', 'logout', 'updatedata', 'deletedata'];
    protected static $indexPage = [''];
}
?>
<?php
?>
```

# Download XAMPP Control Panel
XAMPP is an easy to install Apache distribution containing MariaDB, PHP, and Perl. Just download and start the installer.

# File Structure in XXAMP
To start a Project open C-->XAMPP-->htdocs-->Create your project directory here.

# Create MySQL Database Connection with PHP
```PHP
<?php
// Database credentials
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "login";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Close the connection
mysqli_close()
?>
```

# How to Check(open) Database
Start MySQL in XAMPP Control Panel and open URL given below.
```bash
  http://localhost/phpmyadmin/
```

# How to check project
Start Apache in XAMPP Control Panel and open URL  http://localhost/"Your Project name".
```bash
  http://localhost/"Your Project Name".
```

# Use a PHP file in another PHP file
```PHP
<?php 
        include("another_file.php");
        include_once("another_file.php");
?>
```
```PHP
<?php 
        require("another_file.php");
        require_once("another_file.php");
?>
```

# remove .php from SEO(url)
Check Routing(MVC Setup)

# CMS Reference
1. CS-Cart
2. OpenCart
# <p style="text-align: center;">PHP Basic</p>

**PHP**
- PHP is a widely-used, open source scripting language
- PHP files can contain text, HTML, CSS, JavaScript, and PHP code

**What Can PHP Do?**
1. PHP can generate dynamic page content
2. PHP can create, open, read, write, delete, and close files on the server
3. PHP can collect form data
4. PHP can send and receive cookies
5. PHP can add, delete, modify data in your database

**Why PHP?**
1. PHP runs on various platforms (Windows, Linux, Unix, Mac OS X, etc.)
2. PHP is compatible with almost all servers used today (Apache, IIS, etc.)
3. PHP supports a wide range of databases
4. PHP is free. Download it from the official PHP resource: www.php.net.

**Case Sensetive**
In PHP, keywords (e.g. if, else, while, echo, etc.), classes, functions, and user-defined functions are not case-sensitive.

**Comments**
`// This is a single-line comment`
`/*This is a multiple-lines comment
*/`


## PHP VARIABLE
A variable is a container which hold the value while php program is executed.

1. A variable starts with the $ sign $name
2. A variable name start with a letter or the underscore character [NOT NUMERIC]
4. A variable name can only contain alpha-numeric characters and underscores (A-z, 0-9, and _ )
5. Variable names are case-sensitive ($age and $AGE are two different variables)

**Variables Scope**

**local**  A variable declared within a function has a LOCAL SCOPE and can only be accessed within that function:
**global** A variable declared outside a function has a GLOBAL SCOPE and can only be accessed outside a function ($this)
**static** Normally, when a function is completed/executed, all of its variables are deleted. However, sometimes we want a local variable NOT to be deleted to do this, use the static keyword when declare the variable:

**Global Keyword**
The global keyword is used to access a global variable from within a function.

```php
<?php
$x = 5;
$y = 10;

function myTest() {
  global $x, $y;
  $y = $x + $y;
}

myTest();
echo $y; // outputs 15
?>
```

- PHP also stores all global variables in an array called $GLOBALS[index]. The index holds the name of the variable. This array is also accessible from within functions and can be used to update global variables directly.

**The example above can be rewritten like this**
```php
<?php
$x = 5;
$y = 10;

function myTest() {
  $GLOBALS['y'] = $GLOBALS['x'] + $GLOBALS['y'];
}

myTest();
echo $y; // outputs 15
?>
```


## PHP echo & print statement
They both are used to output data to the screen.

- echo has no return value while print has a return value of 1 so it can be used in expressions. 
- echo can take multiple parameters while print can take one argument. 
- echo is marginally faster than print.

- The echo statement can be used with or without parentheses: echo or echo().
- The print statement can be used with or without parentheses: print or print()

- https://www.w3schools.com/php/php_echo_print.asp


## DATA TYPE
Which type of value a variable have

**PHP supports the following data types:**

1. **String-** A string is a sequence of characters
2. **Integer-** An integer data type is a non-decimal number (either positive and negative)
3. **Float-** (floating point numbers - also called double)
4. **Boolean**
5. **Array**

6. **Object-** an object is an instance of a class.If you create a __construct() function, PHP will automatically call this function when you create an object from a class.

7. **NULL-** A variable of data type NULL is a variable that has no value assigned to it.
Tip: If a variable is created without a value, it is automatically assigned a value of NULL.

8. **Resource-** The special resource type is not an actual data type. It is the storing of a reference to functions and resources external to PHP.
A common example of using the resource data type is a database call.

**return datatype and value**
var_dump($xyz);


## STRING
A string is a sequence of characters

**strlen()** - Return the Length of a String
```php
<?php
echo strlen("Hello world!"); // outputs 12
?>
```

**str_word_count()** - Count Words in a String
```php
<?php
echo str_word_count("Hello world!"); // outputs 2
?>
```

**strrev()** - Reverse a String
```php
<?php
echo strrev("Hello world!"); // outputs !dlrow olleH
?>
```

**strpos()** - Search For a Text Within a String
```php
<?php
echo strpos("Hello world!", "world"); // outputs 6
?>
```

**str_replace()** - Replace Text Within a String
```php
<?php
echo str_replace("world", "Dolly", "Hello world!"); // outputs Hello Dolly!
?>
```

**chr()**	Returns a character from a specified ASCII value

**echo()**	Outputs one or more strings

**explode()**	Breaks a string into an array

**join()**	Alias of implode()

**ltrim()**	Removes whitespace or other characters from the left side of a string

**md5()**	Calculates the MD5 hash of a string

**md5_file()**	Calculates the MD5 hash of a file

**rtrim()**	Removes whitespace or other characters from the right side of a string

**str_word_count()**	Count the number of words in a string

**strcasecmp()**	Compares two strings (case-insensitive)

**strchr()**	Finds the first occurrence of a string inside another string (alias of strstr())

**strcmp()**	Compares two strings (case-sensitive)

**strcoll()**	Compares two strings (locale based string comparison)

**strcspn()**	Returns the number of characters found in a string before any part of some specified characters are found

**strlen()**	Returns the length of a string

**strpos()**	Returns the position of the first occurrence of a string inside another string (case-sensitive)

**strrchr()**	Finds the last occurrence of a string inside another string

**strrev()**	Reverses a string

**strripos()**	Finds the position of the last occurrence of a string inside another string (case-insensitive)

**strrpos()**	Finds the position of the last occurrence of a string inside another string (case-sensitive)

**strspn()**	Returns the number of characters found in a string that contains only characters from a specified charlist

**strtolower()**	Converts a string to lowercase letters

**strtoupper()**	Converts a string to uppercase letters

**substr()**	Returns a part of a string

**substr_count()**	Counts the number of times a substring occurs in a string

**substr_replace()**	Replaces a part of a string with another string

**trim()**	Removes whitespace or other characters from both sides of a string


## NUMBER
**INTEGERS**
Integers can be specified in three formats: decimal (10-based), hexadecimal (16-based - prefixed with 0x) or octal (8-based - prefixed with 0)

**Predefined constants for integers:**
1. PHP_INT_MAX - The largest integer supported
2. PHP_INT_MIN - The smallest integer supported
3. PHP_INT_SIZE -  The size of an integer in bytes

**Functions to check if the type of a variable is integer:**
1. is_int()
2. is_integer() - alias of is_int()
3. is_long() - alias of is_int()

```php
<?php
$x = 5985;
var_dump(is_int($x));

$x = 59.85;
var_dump(is_int($x));
?>
```

**FLOAT**
**Predefined constants for float:**
PHP_FLOAT_MAX - The largest representable floating point number
PHP_FLOAT_MIN - The smallest representable positive floating point number

**Check if the type of a variable is float:**
is_float()
is_double() - alias of is_float()

**INFINITY**
A numeric value that is larger than PHP_FLOAT_MAX is considered infinite.

**Check if a numeric value is finite or infinite:**
is_finite()
is_infinite()

**NaN**
NaN stands for Not a Number.

- NaN is used for impossible mathematical operations.

**Check if a value is not a number:**
is_nan()

**Numerical Strings**
The PHP is_numeric() function can be used to find whether a variable is numeric. The function returns true if the variable is a number or a numeric string, false otherwise

```php
<?php
// Check if the variable is numeric   
$x = 5985;
var_dump(is_numeric($x));     //true

$x = "59.85" + 100;
var_dump(is_numeric($x));     //true

$x = "Hello";
var_dump(is_numeric($x));     //false
?>
```

**Casting Strings and Floats to Integers**
Sometimes you need to cast a numerical value into another data type. The (int), (integer), or intval() function are often used to convert a value to an integer.

- Ex:- Cast float and string to integer:
```php
<?php
// Cast float to int
$x = 23465.768;
$int_cast = (int)$x;
echo $int_cast;             //23465
?>
```

## MATH
math functions allows you to perform mathematical tasks on numbers.

**pi() Function**
The pi() function returns the value of PI:
```php
<?php
echo(pi()); // returns 3.1415926535898
?>
```

**min() and max()**
The min() and max() functions can be used to find the lowest or highest value in a list of arguments.

```php
<?php
echo(min(0, 150, 30, 20, -8, -200));  // returns -200
echo(max(0, 150, 30, 20, -8, -200));  // returns 150
?>
```

**abs()**
The abs() function returns the absolute (positive) value of a number:

```php
<?php
echo(abs(-6.7));  // returns 6.7
?>
```

**sqrt()**
The sqrt() function returns the square root of a number:

```php
<?php
echo(sqrt(64));  // returns 8
?>
```

**round()**
The round() function rounds a floating-point number to its nearest integer:

```php
<?php
echo(round(0.60));  // returns 1
echo(round(0.49));  // returns 0
?>
```

**Random Numbers**
The rand() function generates a random number:

```php
<?php
echo(rand(10, 100));
?>
```

## CONSTAINTS
Constants are like variables except that once they are defined they cannot be changed or undefined

- To create a constant, use the **define()** function
**EX:-** define(name, value, case-insensitive)

**Create case-sensitive constant:**
```php
<?php
define("GREETING", "Welcome to W3Schools.com!");
echo GREETING;
?>
```

**Create case-insensitive constants:**
```php
<?php
define("GREETING", "Welcome to W3Schools.com!", true);
echo greeting;
?>
```


## OPERATORS
Operators are special symbols that perform specific opration between operands and then return a result.

**Arithmetic operators**
Arithmetic operators are used with numeric values to perform common arithmetical operations, such as addition, subtraction, multiplication etc.

| Oprator |      Name      | Example |Result|
| --------| ---------------|---------|------|
|    +    |    Addition    | `$x + $y` | `Sum of $x and $y` |	
|    -	  |   Subtraction	 | `$x - $y` | `Difference of $x and $y`	|
|    *	  |  Multiplication| `$x * $y` | `Product of $x and $y`	|
|    /	  |    Division	   | `$x / $y` | `Quotient of $x and $y`	|
|    %	  |     Modulus	   | `$x % $y` | `Remainder of $x divided by $y`	|
|    **	  | Exponentiation | `$x ** $y`| `Result of raising $x to the $y'th` |

**Assignment operators**
Assignment operator is used to assign a value in in a variable using (=, +=, %=) sign

**Comparison operators**
The PHP comparison operators are used to compare two values (number or string):

| Oprator |      Name      |   Example   |Result|
| --------| ---------------| ----------- |------|
|==	      | Equal	         | `$x == $y` |	 `Returns true if $x is equal to $y`	|
|===      | Identical	     | `$x === $y`	|  `Returns true if $x is equal to $y, and they are of the same type`	|
|!=	      | Not equal	     | `$x != $y`	|  `Returns true if $x is not equal to $y`	|
|<>	      |Not equal	     | `$x <> $y`	|  `Returns true if $x is not equal to $y`	|
|!==	    |Not identical   | `$x !== $y`|`Returns true if $x is not equal to $y, or they are not of the same type`	|
|>	      | Greater than   | `$x > $y`	 | `Returns true if $x is greater than $y`	|
|<	      |Less than	     | `$x < $y`	 | `Returns true if $x is less than $y`	|
|<=>	    | Spaceship	     | `$x <=> $y` | `Returns an integer less than, equal to, or greater than zero depending on if $x is less than, equal to, or greater than $y. Introduced in PHP 7.`|

**Increment/Decrement operators**
The PHP increment/decrement operators are used to increment/decrement a variable's value

| Oprator |      Name      | Description|
| --------| ---------------|------------|
|++$x	    | Pre-increment	 | `Increments $x by one, then returns $x`	|
|$x++	    | Post-increment | `Returns $x, then increments $x by one` |	
|--$x	    | Pre-decrement  | `Decrements $x by one, then returns $x`	|
| $x--	  |  Post-decrement| `Returns $x, then decrements $x by one` |

**Logical operators**
The PHP logical operators are used to combine conditional statements

| Oprator |     Name   |    Example |Result|
| --------| -----------|------------|------|
|  and	  |    And	   |  `$x and $y`	| `True if both $x and $y are true`	|
|  or	    |    Or	     | `$x or $y`	  | `True if either $x or $y is true`	
|  xor    | 	Xor	     | `$x xor $y`	| `True if either $x or $y is true, but not both`	|
|  &&	    |    And	   | `$x && $y`	  | `True if both $x and $y are true`	|
|  `||`	  |    `Or`	     | `$x || $y`	  | `True if either $x or $y is true`	|
|  !	    |    Not	   |  `!$x`	      | `True if $x is not true`	|

**String operators**
PHP has two operators that are specially designed for strings

| Oprator |      Name                |    Example        | Result                        |
| --------| -------------------------| --------------    | ----------------------------- |
| **.**	  | Concatenation	           |   `$txt1 . $txt2` | `Concatenation of $txt1 and $txt2`	|
| **.=**  | Concatenation assignment |  `$txt1 .= $txt2` |  `Appends $txt2 to $txt1`	|

**Array operators**
The PHP array operators are used to compare arrays

| Oprator |      Name      | Example     |       Result     |
| --------| ---------------|-----------  |------------------|
| +	      |    Union	     | `$x + $y`	 | `Union of $x and $y` |	
| ==	    |    Equality	   | `$x == $y`	 | `Returns true if $x and $y have the same key/value pairs`	|
| ===	    |    Identity	   | `$x === $y` | `Returns true if $x and $y have the same key/value pairs in the same order and of the same types`	|
| !=	    |    Inequality	 | `$x != $y`	 |  `Returns true if $x is not equal to $y` |	
| <>	    |    Inequality	 | `$x <> $y`	 |  `Returns true if $x is not equal to $y`	|
| !==	    |   Non-identity | `$x !== $y` |  `Returns true if $x is not identical to $y` |

**Conditional assignment operators**
The PHP conditional assignment operators are used to set a value depending on conditions:

| Oprator |      Name      |            Example         |Result|
| --------| ---------------|----------------------------|------|
| **?:**	|     Ternary 	 | `$x = expr1 ? expr2 : expr3`|	`Returns the value of $x.The value of $x is expr2 if expr1 = TRUE.The value of $x is expr3 if expr1 = FALSE`  |
| **??**	| Null coalescing |  `$x = expr1 ?? expr2`	   | `Returns the value of $x.The value of $x is expr1 if expr1 exists, and is not NULL.If expr1 does not exist, or is NULL, the value of $x is expr2.Introduced in PHP 7` |


## CONDITIONAL STATEMENT
Conditional statements are used to perform different actions based on different conditions.

- **if statement** - executes some code if one condition is true
- **if...else statement** - executes some code if a condition is true and another code if that condition is false
- **if...elseif...else statement** - executes different codes for more than two conditions
- **switch statement** - selects one of many blocks of code to be executed

```php
if (condition) {
  code to be executed if this condition is true;
} elseif (condition) {
  code to be executed if first condition is false and this condition is true;
} else {
  code to be executed if all conditions are false;
}
```

**Switch Statement**
Use the switch statement to select one of many blocks of code to be executed.

```php
switch (n) {
  case label1:
    code to be executed if n=label1;
    break;
  case label2:
    code to be executed if n=label2;
    break;
  case label3:
    code to be executed if n=label3;
    break;
    ...
  default:
    code to be executed if n is different from all labels;
}
```

# LOOPS
Loops are used to execute the same block of code again and again, as long as a certain condition is true.

**while** - loops through a block of code as long as the specified condition is true
**do...while** - loops through a block of code once, and then repeats the loop as long as the specified condition is true
**for** - loops through a block of code a specified number of times
**foreach** - loops through a block of code for each element in an array

**While**
The while loop - Loops through a block of code as long as the specified condition is true.

```php
while (condition is true) {
  code to be executed;
}
```

**do while**
The do...while loop will always execute the block of code once, it will then check the condition, and repeat the loop while the specified condition is true.

```php
do {
  code to be executed;
} while (condition is true);
```

**for**
The for loop - Loops through a block of code a specified number of times.

```php
for (init counter; test counter; increment counter) {
  code to be executed for each iteration;
}
```

**foreeach**
The foreach loop - Loops through a block of code for each element in an array.

```php
foreach ($array as $value) {
  code to be executed;
}
```

**Break and Continue**
- The break statement can also be used to jump out of a loop.

- The continue statement breaks one iteration (in the loop), if a specified condition occurs, and continues with the next iteration in the loop.


## PHP Functions
A function is a block of statements that can be used repeatedly in a program.

- A function will not execute automatically when a page loads.
- A function will be executed by a call to the function.

**Why function**
1. Decrease LOC
2. Readablity
3. Repeatation

**Function Arguments**
An argument is just like a variable. In which Information can be passed to functions through arguments. 

- Arguments are specified after the function name, inside the parentheses. You can add as many arguments as you want, just separate them with a comma.

```php
<?php
function familyName($fname, $year) {
  echo "$fname Refsnes. Born in $year <br>";
}
```

**Strict declaration**
PHP is loosely type language so it automatically associates a data type to the variable, depending on its value. Since the data types are not set in a strict sense, you can do things like adding a string to an integer without causing an error.

```php
<?php
function addNumbers(int $a, int $b) {
  return $a + $b;
}
echo addNumbers(5, "5 days");
// since strict is NOT enabled "5 days" is changed to int(5), and it will return 10
?>
```

- To specify strict we need to set declare(strict_types=1);. This must be on the very first line of the PHP file.

```php
<?php declare(strict_types=1); // strict requirement

function addNumbers(int $a, int $b) {
  return $a + $b;
}
echo addNumbers(5, "5 days");
// since strict is enabled and "5 days" is not an integer, an error will be thrown
?>
```

**Default Argument Value**
The following example shows how to use a default parameter. If we call the function setHeight() without arguments it takes the default value as argument:

Example
```php
<?php declare(strict_types=1); // strict requirement
function setHeight(int $minheight = 50) {
  echo "The height is : $minheight <br>";
}

setHeight(350);
setHeight(); // will use the default value of 50
setHeight(135);
setHeight(80);
?>
```

**Returning values**
To let a function return a value, use the return statement:

```php
<?php declare(strict_types=1); // strict requirement
function addNumbers(float $a, float $b) : float {
  return $a + $b;
}
echo addNumbers(1.2, 5.2);
?>
```

**Return Type Declarations**
PHP 7 also supports Type Declarations for the return statement. Like with the type declaration for function arguments, by enabling the strict requirement, it will throw a "Fatal Error" on a type mismatch.

To declare a type for the function return, add a colon ( : ) and the type right before the opening curly ( { )bracket when declaring the function.

```php
<?php declare(strict_types=1); // strict requirement
function addNumbers(float $a, float $b) : float {
  return $a + $b;
}
echo addNumbers(1.2, 5.2);
?>
```

**Passing Arguments by Reference**
In PHP, arguments are usually passed by value, which means that a copy of the value is used in the function and the variable that was passed into the function cannot be changed.

When a function argument is passed by reference, changes to the argument also change the variable that was passed in. To turn a function argument into a reference, the & operator is used:

Example
Use a pass-by-reference argument to update a variable:

```php
<?php
function add_five(&$value) {
  $value += 5;
}

$num = 2;
add_five($num);
echo $num;
?>
```

## ARRAY
An array is a special variable, which can hold more than one value at a time.

**Create an Array in PHP**
array() function is used to create an array.

```php
<?php
$array = array(
    "foo" => "bar",
    "bar" => "foo",
);

// Using the short array syntax
$array = [
    "foo" => "bar",
    "bar" => "foo",
];
?>
```

**Indexed arrays** - Arrays with a numeric index

```php
$cars = array("Volvo", "BMW", "Toyota");

//Manually
$cars[0] = "Volvo";
$cars[1] = "BMW";           
$cars[2] = "Toyota";
```

**Associative arrays** - Arrays with named keys
Associative arrays are arrays that use named keys that you assign to them.

**Two ways to create an associative array:** 
```php
$age = array("Peter"=>"35", "Ben"=>"37", "Joe"=>"43");

// Another way
$age['Peter'] = "35";
$age['Ben'] = "37";
$age['Joe'] = "43";
The named keys can then be used in a script:
```

**Example**
```php
<?php
$age = array("Peter"=>"35", "Ben"=>"37", "Joe"=>"43");
echo "Peter is " . $age['Peter'] . " years old.";
?>
```

**Loop Through an Associative Array**
To loop through and print all the values of an associative array, you could use a foreach loop, like this:

```php
<?php
$age = array("Peter"=>"35", "Ben"=>"37", "Joe"=>"43");

foreach($age as $x => $x_value) {
  echo "Key=" . $x . ", Value=" . $x_value;
  echo "<br>";
}
?>
```

**Multidimensional arrays** - Arrays containing one or more arrays:-
A multidimensional array is an array containing one or more arrays.

**Two-dimensional Arrays**
A two-dimensional array is an array of arrays (a three-dimensional array is an array of arrays of arrays).

|Name  | Stock | Sold |
|------|-------|------|
| Volvo|	 22	 |  18  |
| BMW	 |   15	 |  13  |
| Saab |	 5	 |   2  |

- We can store the data from the table above in a two-dimensional array, like this:

```php
$cars = array )
  array("Volvo",22,18),
  array("BMW",15,13),
  array("Saab",5,2),
  array("Land Rover",17,15)
$);
```

**Sorting Arrays**
The elements in an array can be sorted in alphabetical or numerical order, descending or ascending.

**sort()** - sort arrays in ascending order
**rsort()** - sort arrays in descending order
**asort()** - sort associative arrays in ascending order, according to the value
**ksort()** - sort associative arrays in ascending order, according to the key
**arsort()** - sort associative arrays in descending order, according to the value
**krsort()** - sort associative arrays in descending order, according to the key


## Global Variables - Superglobals
"superglobals" are predefine variables. It is always accessible, regardless of scope - and we can access them from any function, class or file.

The PHP superglobal variables are:
```php
$$GLOBALS
$_SERVER
$_REQUEST
$_POST
$_GET
$_FILES
$_ENV
$_COOKIE
$$_SESSION
```

- https://www.w3schools.com/php/php_superglobals.asp

## Regular Expressions
A regular expression is a sequence of characters that forms a search pattern. When you search for data in a text, you can use this search pattern to describe what you are searching for.

- A regular expression can be a single character, or a more complicated pattern.

```php
$exp = "/w3schools/i";
```
- **"/"** is the delimiter, w3schools is the pattern that is being searched for, and **"i"** is a modifier that makes the search case-insensitive.

**Regular Expression Functions**

**preg_match()**	Returns 1 if the pattern was found in the string and 0 if not
**preg_match_all()**	Returns the number of times the pattern was found in the string, which may also be 0
**preg_replace()**	Returns a new string where matched patterns have been replaced with another string

- https://www.w3schools.com/php/php_regex.asp
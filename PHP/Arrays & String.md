# ARRAY
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
$cars = array (
  array("Volvo",22,18),
  array("BMW",15,13),
  array("Saab",5,2),
  array("Land Rover",17,15)
$);
```

```php
<?php

// Defining a multidimensional array
$favorites = array(
	array(
		"name" => "Dave Punk",
		"mob" => "5689741523",
		"email" => "davepunk@gmail.com",
	),
	array(
		"name" => "Monty Smith",
		"mob" => "2584369721",
		"email" => "montysmith@gmail.com",
	),
	array(
		"name" => "John Flinch",
		"mob" => "9875147536",
		"email" => "johnflinch@gmail.com",
	)
);
// Accessing elements
echo "Dave Punk email-id is: " . $favorites[0]["email"], "\n";
echo "John Flinch mobile number is: " . $favorites[2]["mob"];

?>
```

# Arrays Function in PHP

## Creation and Initialization:

**array():** Create an array.
**[]:** Short syntax for creating an array (available in PHP 5.4 and later).
**range():** Create an array containing a range of elements.

## Manipulation:

**count():** Count the number of elements in an array.
**array_push():** Add one or more elements to the end of an array.
**array_pop():** Remove the last element from an array.
**array_shift():** Remove the first element from an array.
**array_unshift():** Prepend one or more elements to the beginning of an array.
**array_merge():** Merge two or more arrays.
**array_slice():** Extract a portion of an array.
**array_splice():** Remove a portion of the array and return it.

## Search and Check:

**in_array():** Checks if a value exists in an array.
**array_search():** Searches an array for a given value and returns the corresponding key if successful.
**array_key_exists():** Checks if a key exists in an array.
**array_keys():** Return all the keys or a subset of the keys of an array.
**array_values():** Return all the values of an array.

## Sorting:

**sort():** Sort an array.
**rsort():** Sort an array in reverse order.
**asort():** Sort an associative array by values.
**ksort():** Sort an associative array by keys.
**arsort():** Sort an associative array in reverse order, according to values.
**krsort():** Sort an associative array in reverse order, according to keys.

## Iteration:

**foreach():** Loop through each key/value pair in an array.
Transformation:

**array_map():** Applies a callback function to each element of an array.
**array_filter():** Filters elements of an array using a callback function.
**array_reduce():** Iteratively reduce an array to a single value using a callback function.

## Other:

**array_reverse():** Return an array with elements in the reverse order.
**array_unique():** Remove duplicate values from an array.
**array_flip():** Exchanges all keys with their associated values in an array.
**array_rand():** Pick one or more random keys from an array.


# STRING
A string is a sequence of characters

**1. Single-quoted strings:**
Single-quoted strings are simple and straightforward. The contents are taken literally, and variables inside the string are not parsed.
```php
$string = 'This is a single-quoted string.';
```

**2. Double-quoted strings:**
Double-quoted strings allow variable interpolation, meaning variables within the string are evaluated and their values are included.
```php
$variable = 'value';
$string = "This is a double-quoted string with a $variable.";
```

**3. Heredoc:**
Heredoc syntax provides a way to declare strings that span multiple lines and can include variables.
```php
$variable = 'value';
$string = <<<EOT
This is a heredoc string with a $variable.
It can span multiple lines.
EOT;
```

## String Function in PHP

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

# Character

**1. Accessing Characters:**
Using square brackets to access individual characters by index:
```php
$str = "Hello, World!";
$firstChar = $str[0]; // Access the first character
```

**2. String Functions:**
Returns a portion of a string based on the starting index and length.
```php
$substring = substr($str, 7, 5); // Starting at index 7, take 5 characters
```

**3. Iterating Through Characters:**
Using a loop to iterate through each character in a string:
```php
for ($i = 0; $i < strlen($str); $i++) {
    $char = $str[$i];
    // Do something with $char
}
```

**4. Converting Case:**
```php
$lowercase = strtolower($str);
$uppercase = strtoupper($str);
```

# ASCII Value
ASCII is an acronym that stands for American Standard Code for Information Interchange. In ASCII, a specific numerical value is given to different characters and symbols

**ASCII Value Of A-Z =>**(65-90) **a-z =>**(97-122) **0-9 =>**(48-57)

`In PHP, you can use the ord() function to get the ASCII value of a character.`
```php
<?php
$char = 'A';
$asciiValue = ord($char);

echo "The ASCII value of '$char' is $asciiValue";
```

**With String**
```php
<?php

$string = "Hello";
$asciiValues = [];

for ($i = 0; $i < strlen($string); $i++) {
    $asciiValues[] = ord($string[$i]);
}

echo "ASCII values of each character in '$string': " . implode(', ', $asciiValues);

// Output: ASCII values of each character in 'Hello': 72, 101, 108, 108, 111
```

This code initializes an empty array **$asciiValues** and uses a loop to iterate through each character in the string, obtaining its ASCII value with **ord()** and storing it in the array. Finally, the ASCII values are echoed to the screen using **implode()** to join them into a string.
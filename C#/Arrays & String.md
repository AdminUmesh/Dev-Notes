# Arrays
Arrays are used to store multiple values in a single variable, instead of declaring separate variables for each value.

**To create an array of integers, you could write:**

```C#
int[] myNum = { 10, 20, 30, 40 };
// Or
int[] arr = new int[5];
```

## Multi-Dimensional Arrays
A multidimensional array is an array of arrays.

```C#
int[][] myNumbers = new int[][]
{
    new int[] {1, 2, 3, 4},
    new int[] {5, 6, 7}
};

Console.WriteLine(myNumbers[1][2]); // Outputs 7
```

**Loop Through a Multi-Dimensional Array**
We can also use a for loop inside another for loop to get the elements of a two-dimensional array (we still have to point to the two indexes):

```C#
using System;

public class MainClass
{
    public static void Main(string[] args)
    {
        int[][] myNumbers = new int[][]
        {
            new int[] {1, 2, 3, 4},
            new int[] {5, 6, 7}
        };

        for (int i = 0; i < myNumbers.Length; ++i)
        {
            for (int j = 0; j < myNumbers[i].Length; ++j)
            {
                Console.WriteLine(myNumbers[i][j]);
            }
        }
    }
}
```

# String
Strings are the type of objects that can store the character of values and in C#, characters are stored using Unicode encoding and every character is stored in 16 bits.

# string vs System.String
`In C#, the string keyword is an alias for String; therefore, String and string are equivalent. It's recommended to use the provided alias string as it works even without using System;.`

# Declaring and initializing strings
```c#
// Declare without initializing.
string message1;

// Initialize to null.
string? message2 = null;

// Initialize as an empty string.
// Use the Empty constant instead of the literal "".
string message3 = System.String.Empty;

// Initialize with a regular string literal.
string oldPath = "c:\\Program Files\\Microsoft Visual Studio 8.0";

// Initialize with a verbatim string literal.
string newPath = @"c:\Program Files\Microsoft Visual Studio 9.0";

// Use System.String if you prefer.
System.String greeting = "Hello World!";

// In local variables (i.e. within a method body)
// you can use implicit typing.
var temp = "I'm still a strongly-typed System.String!";

// Use a const string to prevent 'message4' from
// being used to store another string value.
const string message4 = "You can't get rid of me!";

// Use the String constructor only when creating
// a string from a char*, char[], or sbyte*. See
// System.String documentation for details.
char[] letters = { 'A', 'B', 'C' };
string alphabet = new string(letters); 
```
## There are two ways to create a string in C#:

1. Without new Keyword (String Literal):
```c#
string str1 = "Hello, world!";
```
2. With new Keyword (String Constructor):
```c#
string str2 = new string("Hello, world!");
```

# Immutability of strings
String objects are immutable: they can't be changed after they've been created. All of the String methods and C# operators that appear to modify a string actually return the results in a new string object. In the following example, when the contents of s1 and s2 are concatenated to form a single string, the two original strings are unmodified. The += operator creates a new string that contains the combined contents. That new object is assigned to the variable s1, and the original object that was assigned to s1 is released for garbage collection because no other variable holds a reference to it.
```c#
string s1 = "A string is more ";
string s2 = "than the sum of its chars.";

// Concatenate s1 and s2. This actually creates a new
// string object and stores it in s1, releasing the
// reference to the original object.
s1 += s2;

System.Console.WriteLine(s1);
// Output: A string is more than the sum of its chars.
```

# Quoted string literals
Quoted string literals start and end with a single double quote character (") on the same line. Quoted string literals are best suited for strings that fit on a single line and don't include any escape sequences. A quoted string literal must embed escape characters, as shown in the following example:

```c#
string columns = "Column 1\tColumn 2\tColumn 3";
//Output: Column 1        Column 2        Column 3

string rows = "Row 1\r\nRow 2\r\nRow 3";
/* Output:
    Row 1
    Row 2
    Row 3
*/

string title = "\"The \u00C6olean Harp\", by Samuel Taylor Coleridge";
//Output: "The Æolean Harp", by Samuel Taylor Coleridge
```

# Verbatim string literals
Verbatim string literals are more convenient for multi-line strings, strings that contain backslash characters, or embedded double quotes. Verbatim strings preserve new line characters as part of the string text. Use double quotation marks to embed a quotation mark inside a verbatim string. The following example shows some common uses for verbatim strings:
```c#
string title = "\"The \u00C6olean Harp\", by Samuel Taylor Coleridge";
//Output: "The Æolean Harp", by Samuel Taylor Coleridge

string filePath = @"C:\Users\scoleridge\Documents\";
//Output: C:\Users\scoleridge\Documents\

string text = @"My pensive SARA ! thy soft cheek reclined
    Thus on mine arm, most soothing sweet it is
    To sit beside our Cot,...";
/* Output:
My pensive SARA ! thy soft cheek reclined
    Thus on mine arm, most soothing sweet it is
    To sit beside our Cot,...
*/

string quote = @"Her name was ""Sara.""";
//Output: Her name was "Sara."
```

# Raw string literals
Beginning with C# 11, you can use raw string literals to more easily create strings that are multi-line, or use any characters requiring escape sequences. Raw string literals remove the need to ever use escape sequences. You can write the string, including whitespace formatting, how you want it to appear in output. A raw string literal:

- Starts and ends with a sequence of at least three double quote characters ("""). You're allowed more than three consecutive characters to start and end the sequence in order to support string literals that contain three (or more) repeated quote characters.

- Single line raw string literals require the opening and closing quote characters on the same line.

- Multi-line raw string literals require both opening and closing quote characters on their own line.

- In multi-line raw string literals, any whitespace to the left of the closing quotes is removed from all lines of the raw string literal.

- In multi-line raw string literals, whitespace following the opening quote on the same line is ignored.

- In multi-line raw string literals, whitespace only lines following the opening quote are included in the string literal.

**The following examples demonstrate these rules:**

```c#
string singleLine = """Friends say "hello" as they pass by.""";
string multiLine = """
    "Hello World!" is typically the first program someone writes.
    """;
string embeddedXML = """
       <element attr = "content">
           <body style="normal">
               Here is the main text
           </body>
           <footer>
               Excerpts from "An amazing story"
           </footer>
       </element >
       """;
// The line "<element attr = "content">" starts in the first column.
// All whitespace left of that column is removed from the string.

string rawStringLiteralDelimiter = """"
    Raw string literals are delimited 
    by a string of at least three double quotes,
    like this: """
    """";
```

**The following examples demonstrate the compiler errors reported based on these rules:**
```c#
// CS8997: Unterminated raw string literal.
var multiLineStart = """This
    is the beginning of a string 
    """;

// CS9000: Raw string literal delimiter must be on its own line.
var multiLineEnd = """
    This is the beginning of a string """;

// CS8999: Line does not start with the same whitespace as the closing line
// of the raw string literal
var noOutdenting = """
    A line of text.
Trying to outdent the second line.
    """;
```

The first two examples are invalid because multiline raw string literals require the opening and closing quote sequence on its own line. The third example is invalid because the text is outdented from the closing quote sequence.

You should consider raw string literals when you're generating text that includes characters that require escape sequences when using quoted string literals or verbatim string literals. Raw string literals will be easier for you and others to read because it will more closely resemble the output text. For example, consider the following code that includes a string of formatted JSON:
```c#
string jsonString = """
{
  "Date": "2019-08-01T00:00:00-07:00",
  "TemperatureCelsius": 25,
  "Summary": "Hot",
  "DatesAvailable": [
    "2019-08-01T00:00:00-07:00",
    "2019-08-02T00:00:00-07:00"
  ],
  "TemperatureRanges": {
    "Cold": {
      "High": 20,
      "Low": -10
    },
    "Hot": {
      "High": 60,
      "Low": 20
    }
            },
  "SummaryWords": [
    "Cool",
    "Windy",
    "Humid"
  ]
}
""";
```
Compare that text with the equivalent text in the sample on JSON deserialization, which doesn't make use of this new feature.

# String escape sequences
| Escape sequence | Character name     | Unicode encoding                       |
| :-------- | :------- | :-------------------------------- |
| `\'`      | `Single quote` | `0x0027` |
| `\"`      | `Double quote` | `0x0022` |
| `\`      | `Backslash` | `0x005C` |
| `\0`      | `Null` | `0x0000` |
| `\a`      | `Alert` | `0x0007` |
| `\b`      | `Backspace` | `0x0008` |
| `\f`      | `Form feed` | `0x000C` |
| `\n`      | `New line` | `0x000A` |
| `\r`      | `Carriage return` | `0x000D` |
| `\t`      | `Horizontal tab` | `0x0009` |
| `\v`      | `Vertical tab` | `0x000B` |
	
# Format strings
A format string is a string whose contents are determined dynamically at run time. Format strings are created by embedding interpolated expressions or placeholders inside of braces within a string. Everything inside the braces ({...}) will be resolved to a value and output as a formatted string at run time. There are two methods to create format strings: string interpolation and composite formatting.

# String interpolation
Interpolated strings are identified by the $ special character and include interpolated expressions in braces. If you're new to string interpolation, see the String interpolation - C# interactive tutorial for a quick overview.

Use string interpolation to improve the readability and maintainability of your code. String interpolation achieves the same results as the String.Format method, but improves ease of use and inline clarity.
```c#
var jh = (firstName: "Jupiter", lastName: "Hammon", born: 1711, published: 1761);
Console.WriteLine($"{jh.firstName} {jh.lastName} was an African American poet born in {jh.born}.");
Console.WriteLine($"He was first published in {jh.published} at the age of {jh.published - jh.born}.");
Console.WriteLine($"He'd be over {Math.Round((2018d - jh.born) / 100d) * 100d} years old today.");

// Output:
// Jupiter Hammon was an African American poet born in 1711.
// He was first published in 1761 at the age of 50.
// He'd be over 300 years old today.
```

Beginning with C# 10, you can use string interpolation to initialize a constant string when all the expressions used for placeholders are also constant strings.

Beginning with C# 11, you can combine raw string literals with string interpolations. You start and end the format string with three or more successive double quotes. If your output string should contain the { or } character, you can use extra $ characters to specify how many { and } characters start and end an interpolation. Any sequence of fewer { or } characters is included in the output. The following example shows how you can use that feature to display the distance of a point from the origin, and place the point inside braces:

```c#
int X = 2;
int Y = 3;

var pointMessage = $$"""The point {{{X}}, {{Y}}} is {{Math.Sqrt(X * X + Y * Y)}} from the origin.""";

Console.WriteLine(pointMessage);
// Output:
// The point {2, 3} is 3.605551275463989 from the origin.
```

# Verbatim string interpolation
C# also allows verbatim string interpolation, for example across multiple lines, using the $@ or @$ syntax.

To interpret escape sequences literally, use a verbatim string literal. An interpolated verbatim string starts with the $ character followed by the @ character. You can use the $ and @ tokens in any order: both $@"..." and @$"..." are valid interpolated verbatim strings.
```c#
var jh = (firstName: "Jupiter", lastName: "Hammon", born: 1711, published: 1761);
Console.WriteLine($@"{jh.firstName} {jh.lastName}
    was an African American poet born in {jh.born}.");
Console.WriteLine(@$"He was first published in {jh.published}
at the age of {jh.published - jh.born}.");

// Output:
// Jupiter Hammon
//     was an African American poet born in 1711.
// He was first published in 1761
// at the age of 50.
```

# Composite formatting
The String.Format utilizes placeholders in braces to create a format string. This example results in similar output to the string interpolation method used above.
```c#
var pw = (firstName: "Phillis", lastName: "Wheatley", born: 1753, published: 1773);
Console.WriteLine("{0} {1} was an African American poet born in {2}.", pw.firstName, pw.lastName, pw.born);
Console.WriteLine("She was first published in {0} at the age of {1}.", pw.published, pw.published - pw.born);
Console.WriteLine("She'd be over {0} years old today.", Math.Round((2018d - pw.born) / 100d) * 100d);

// Output:
// Phillis Wheatley was an African American poet born in 1753.
// She was first published in 1773 at the age of 20.
// She'd be over 300 years old today.
```

# Substrings
A substring is any sequence of characters that is contained in a string. Use the Substring method to create a new string from a part of the original string. You can search for one or more occurrences of a substring by using the IndexOf method. Use the Replace method to replace all occurrences of a specified substring with a new string. Like the Substring method, Replace actually returns a new string and doesn't modify the original string. For more information, see How to search strings and How to modify string contents.

```c#
string s3 = "Visual C# Express";
System.Console.WriteLine(s3.Substring(7, 2));
// Output: "C#"

System.Console.WriteLine(s3.Replace("C#", "Basic"));
// Output: "Visual Basic Express"

// Index values are zero-based
int index = s3.IndexOf("C");
// index = 7
```

# Accessing individual characters
You can use array notation with an index value to acquire read-only access to individual characters, as in the following example:
```c#
string s5 = "Printing backwards";

for (int i = 0; i < s5.Length; i++)
{
    System.Console.Write(s5[s5.Length - i - 1]);
}
// Output: "sdrawkcab gnitnirP"
```
If the String methods don't provide the functionality that you must have to modify individual characters in a string, you can use a StringBuilder object to modify the individual chars "in-place", and then create a new string to store the results by using the StringBuilder methods. In the following example, assume that you must modify the original string in a particular way and then store the results for future use:
```c#
string question = "hOW DOES mICROSOFT wORD DEAL WITH THE cAPS lOCK KEY?";
System.Text.StringBuilder sb = new System.Text.StringBuilder(question);

for (int j = 0; j < sb.Length; j++)
{
    if (System.Char.IsLower(sb[j]) == true)
        sb[j] = System.Char.ToUpper(sb[j]);
    else if (System.Char.IsUpper(sb[j]) == true)
        sb[j] = System.Char.ToLower(sb[j]);
}
// Store the new string.
string corrected = sb.ToString();
System.Console.WriteLine(corrected);
// Output: How does Microsoft Word deal with the Caps Lock key?
```

# Null strings and empty strings

An empty string is an instance of a System.String object that contains zero characters. Empty strings are used often in various programming scenarios to represent a blank text field. You can call methods on empty strings because they're valid System.String objects. Empty strings are initialized as follows:
```c#
string s = String.Empty;
```
By contrast, a null string doesn't refer to an instance of a System.String object and any attempt to call a method on a null string causes a NullReferenceException. However, you can use null strings in concatenation and comparison operations with other strings. The following examples illustrate some cases in which a reference to a null string does and doesn't cause an exception to be thrown:
```C#
string str = "hello";
string? nullStr = null;
string emptyStr = String.Empty;

string tempStr = str + nullStr;
// Output of the following line: hello
Console.WriteLine(tempStr);

bool b = (emptyStr == nullStr);
// Output of the following line: False
Console.WriteLine(b);

// The following line creates a new empty string.
string newStr = emptyStr + nullStr;

// Null strings and empty strings behave differently. The following
// two lines display 0.
Console.WriteLine(emptyStr.Length);
Console.WriteLine(newStr.Length);
// The following line raises a NullReferenceException.
//Console.WriteLine(nullStr.Length);

// The null character can be displayed and counted, like other chars.
string s1 = "\x0" + "abc";
string s2 = "abc" + "\x0";
// Output of the following line: * abc*
Console.WriteLine("*" + s1 + "*");
// Output of the following line: *abc *
Console.WriteLine("*" + s2 + "*");
// Output of the following line: 4
Console.WriteLine(s2.Length);
```

# Using StringBuilder for fast string creation
String operations in .NET are highly optimized and in most cases don't significantly impact performance. However, in some scenarios such as tight loops that are executing many hundreds or thousands of times, string operations can affect performance. The StringBuilder class creates a string buffer that offers better performance if your program performs many string manipulations. The StringBuilder string also enables you to reassign individual characters, something the built-in string data type doesn't support. This code, for example, changes the content of a string without creating a new string:
```c#
System.Text.StringBuilder sb = new System.Text.StringBuilder("Rat: the ideal pet");
sb[0] = 'C';
System.Console.WriteLine(sb.ToString());
//Outputs Cat: the ideal pet
```
In this example, a StringBuilder object is used to create a string from a set of numeric types:

```c#
var sb = new StringBuilder();

// Create a string composed of numbers 0 - 9
for (int i = 0; i < 10; i++)
{
    sb.Append(i.ToString());
}
Console.WriteLine(sb);  // displays 0123456789

// Copy one character of the string (not possible with a System.String)
sb[0] = sb[9];

Console.WriteLine(sb);  // displays 9123456789
```

# Strings, extension methods and LINQ
Because the String type implements IEnumerable<T>, you can use the extension methods defined in the Enumerable class on strings. To avoid visual clutter, these methods are excluded from IntelliSense for the String type, but they're available nevertheless. You can also use LINQ query expressions on strings. For more information, see LINQ and Strings.

**1. String Literal**
To make C# more memory efficient (because no new objects are created if it exists already in the string constant pool). 

```C#
String demoString = “GeeksforGeeks”;
```

**2. Using new Keyword**
In such a case, JVM will create a new string object in normal (non-pool) heap memory and the literal “Welcome” will be placed in the string constant pool. The variable s will refer to the object in the heap (non-pool).

```C#
String demoString = new String (“GeeksforGeeks”);
```

**Other Example**
```c#
// Creating a string using string literal
string str1 = "Hello";

// Creating a string using the new keyword
string str2 = new string("World");

// Concatenating strings
string result = str1 + " " + str2; // Result: "Hello World"

// Getting the length of a string
int length = result.Length; // Length: 11

// Accessing characters in a string
char firstChar = result[0]; // First character: 'H'

// Substring extraction
string subStr = result.Substring(6); // Substring from index 6: "World"

```
## CharSequence Interface
CharSequence Interface is used for representing the sequence of Characters in C#.

**1. String**
String is an immutable class which means a constant and cannot be changed once created and if wish to change.
```
String str= "geeks"; // Store  in String Constaint Pool
           or
String str= new String("geeks") //Heap area or SCP
```
- Stored in Heap area and String Constaint Pool(SCP)
- Immutable Object
- if we change string lot of times it will consume more memory.(because each time its create new object)
- slow
- use when data is not or less change.

**Why string objects are immutable in C#?**
Because C# uses the concept of string literal. Suppose there are 5 reference variables, all refer to one object “Sachin”. If one reference variable changes the value of the object, it will be affected by all the reference variables. That is why string objects are immutable in C#.

**Why C# uses the concept of string literal?**
To make C# more memory efficient (because no new objects are created if it exists already in the string constant pool). 

**C# String Class Methods**
The C#.lang.String class provides a lot of built-in methods that are used to manipulate string in C#.

1. **str.toLowerCase()** => return given string in lowercase
2. **str.toUpperCase()** => return given string in uppercase
3. **str.trim()** => The String class trim() method eliminates white spaces before and after the String.
4. **startsWith("A")  && endsWith("A")** => The method startsWith() checks whether the String starts with the letters passed as arguments and endsWith() method checks whether the String ends with the letters passed as arguments. (return boolean)
5. **charAt(2)** => The String class charAt() method returns a character at specified index.
6. **length()** => The String class length() method returns length of the specified String.
7. **Integer.parseInt(str)** => This Methods used to convert a String to an Int in C#
8. **convert int to string**
- toString() => Integer.toString(110)
- valueOf() => String.valueOf(10)
9. **s1.replace("C#","Kava")** => //replaces all occurrences of "C#" to "Kava" 
10. **str.indexOf('g') && str.indexOf("Goa")** => This method returns the index within this string of the first occurrence of the specified character or -1, if the character does not occur.
11. **str.indexOf('g', 13) && str.indexOf("Goa", 13)** => start searching after 13th index
12. **Str.substring(10)** => The substring begins with the character at the specified index and extends to the end of this string. Endindex of the substring starts from 1 and not from 0.
13. **Str.substring(10, 16)** => return new string start from 10 to 15 index
14. **str.isEmpty()** => return true if string not contains any value nor a single space.
15. **name.contains("about")** => return true if String str is like "tell me about yourself". //case sensetive
16. **str1.concat(str2)** => hello and world becomes helloworld. `you can also use + sign`
17. **str.equals("hello")** => compare two string and return boolean. //case sensetive

**StringBuffer** vs **StringBuilder**
C# StringBuilder class is used to create mutable (modifiable) String. The C# StringBuilder class is same as StringBuffer class except that it is non-synchronized.

| **StringBuffer** | **StringBuilder** |
| ---------------- | ----------------- |
| heap area        | heap area |
| mutable object   | mutable object |
| consume less memory | consume less memory |
| all method are sychronised thus its thread safe. | non sychronised method nor thread safe. |
| fast as compare to string | fast as compare to stringBuffer |
| use when data is changing frequently. | use when data is changing frequently. |

# Character
In programming, especially in the context of languages like C# or C#, a char (short for "character") is a data type used to represent a single character. Characters can include letters, digits, punctuation marks, whitespace characters (such as spaces and tabs), and special characters like newline characters.

In C# and C#, the char data type is a 16-bit unsigned integer that represents Unicode characters. It can store any character from the Unicode character set, which includes characters from various languages and scripts, as well as symbols and emojis.

**Creating a Character object:**
- char ch='a';

The char data type in C# represents a single Unicode character. It's a value type and not an object, so it doesn't have methods like in C#. However, C# provides static methods in the char struct to perform similar operations:

<!-- The Character class is immutable like String class i.e once it’s object is created, it cannot be changed. -->
1. **Character.isDigit('A')** => return true if given char is a number
2. **Character.isLetter('A')** => return true if given char is a Letter
3. **Character.isWhitespace('A')** => return true if given char is a whiteSpace
4. **Character.isUpperCase('A')** => return true if given char is in uppercase
5. **Character.isLowerCase('A')** => return true if given char is in lowercse
6. **Character.toUpperCase('a')** => return given char in uppercase
7. **Character.toLowerCase('A')** => return given char in lowercase
8. **Character.toString('x')** => return given char in string


char.IsDigit('A'): Returns true if the given character is a digit.
char.IsLetter('A'): Returns true if the given character is a letter.
char.IsWhiteSpace('A'): Returns true if the given character is a white space.
char.IsUpper('A'): Returns true if the given character is uppercase.
char.IsLower('A'): Returns true if the given character is lowercase.
char.ToUpper('a'): Returns the uppercase equivalent of the given character.
char.ToLower('A'): Returns the lowercase equivalent of the given character.
char.ToString('x'): Converts the given character to its string representation.

```c#
char myChar = 'A';

bool isDigit = char.IsDigit(myChar); // false
bool isLetter = char.IsLetter(myChar); // true
bool isWhiteSpace = char.IsWhiteSpace(myChar); // false
bool isUpper = char.IsUpper(myChar); // true
bool isLower = char.IsLower(myChar); // false

char upperChar = char.ToUpper(myChar); // 'A'
char lowerChar = char.ToLower(myChar); // 'a'

string charString = myChar.ToString(); // "A"
```

# ASCII value
ASCII is an acronym that stands for American Standard Code for Information Interchange. In ASCII, a specific numerical value is given to different characters and symbols

**There are 3 ways to print ASCII value or code of a specific character**

1. Using brute force Method 
 `simply assign the character to a new variable of integer type.`
```c#
using System;

class Program
{
    static void Main(string[] args)
    {
        char ch = 'A';
        int ascii = (int)ch;
        Console.WriteLine("The ASCII value is " + ascii);
    }
}

```

2. Using the type-casting Method
`Type-casting in C# is a way to cast a variable into another datatype which means holding a value of another datatype occupying lesser bytes.`
```c#
using System;

class Program
{
    static void Main(string[] args)
    {
        char ch = 'A';
        Console.WriteLine("The ASCII value is " + (int)ch);
    }
}
```

3. Using the format specifier Method
`We have stored the value of the given character inside a formal specifier by specifying the character to be an int. Hence, the ASCII value of that character is stored inside the format specifier.`
```C#
using System;

class Program
{
    static void Main(string[] args)
    {
        char character = 'A';
        string formatSpecifier = $"{(int)character}";
        Console.WriteLine("The ASCII value is " + formatSpecifier);
    }
}

```

**ASCII Value Of**
**A-Z** =>(65-90)
**a-z** =>(97-122)
**0-9** =>(48-57)

## Boxing And Unboxing
Boxing and unboxing are important concepts in C#. The C# Type System contains three data types: `Value Types (int, char, etc), Reference Types (object) and Pointer Types.` Basically, Boxing converts a Value Type variable into a Reference Type variable, and Unboxing achieves the vice-versa. Boxing and Unboxing enable a unified view of the type system in which a value of any type can be treated as an object.

## Boxing In C#
- The process of converting a Value Type variable (char, int etc.) to a Reference Type variable (object) is called Boxing.
- Boxing is an implicit conversion process in which object type (super type) is used.
- Value Type variables are always stored in Stack memory, while Reference Type variables are stored in Heap memory.

**Example :**
```c#
int num = 23; // 23 will assigned to num
Object Obj = num; // Boxing
```

## Unboxing In C#
- The process of converting a Reference Type variable into a Value Type variable is known as Unboxing.
- It is an explicit conversion process.

**Example :**
```c#
int num = 23;         // value type is int and assigned value 23
Object Obj = num;    // Boxing
int i = (int)Obj;    // Unboxing
```
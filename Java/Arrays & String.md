# Arrays
Arrays are used to store multiple values in a single variable, instead of declaring separate variables for each value.

**To create an array of integers, you could write:**
```java
int[] myNum = {10, 20, 30, 40};
or
int arr []= new int[5];
```
## Multi-Dimensional Arrays
A multidimensional array is an array of arrays.
```java
int[][] myNumbers = { {1, 2, 3, 4}, {5, 6, 7} };
System.out.println(myNumbers[1][2]); // Outputs 7
```
**Loop Through a Multi-Dimensional Array**
We can also use a for loop inside another for loop to get the elements of a two-dimensional array (we still have to point to the two indexes):
```java
public class Main {
  public static void main(String[] args) {
    int[][] myNumbers = { {1, 2, 3, 4}, {5, 6, 7} };
    for (int i = 0; i < myNumbers.length; ++i) {
      for(int j = 0; j < myNumbers[i].length; ++j) {
        System.out.println(myNumbers[i][j]);
      }
    }
  }
}
```

# String
Strings are the type of objects that can store the character of values and in Java, every character is stored in 16 bits.

## There are two ways to create a string in Java: 

**1. String Literal**
To make Java more memory efficient (because no new objects are created if it exists already in the string constant pool). 

```java
String demoString = “GeeksforGeeks”;
```
**2. Using new Keyword**
In such a case, JVM will create a new string object in normal (non-pool) heap memory and the literal “Welcome” will be placed in the string constant pool. The variable s will refer to the object in the heap (non-pool).

```java
String demoString = new String (“GeeksforGeeks”);
```

## CharSequence Interface
CharSequence Interface is used for representing the sequence of Characters in Java.

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

**Why string objects are immutable in Java?**
Because java uses the concept of string literal. Suppose there are 5 reference variables, all refer to one object “Sachin”. If one reference variable changes the value of the object, it will be affected by all the reference variables. That is why string objects are immutable in Java.

**Why Java uses the concept of string literal?**
To make Java more memory efficient (because no new objects are created if it exists already in the string constant pool). 

**Java String Class Methods**
The java.lang.String class provides a lot of built-in methods that are used to manipulate string in Java.

1. **str.toLowerCase()** => return given string in lowercase
2. **str.toUpperCase()** => return given string in uppercase
3. **str.trim()** => The String class trim() method eliminates white spaces before and after the String.
4. **startsWith("A")  && endsWith("A")** => The method startsWith() checks whether the String starts with the letters passed as arguments and endsWith() method checks whether the String ends with the letters passed as arguments. (return boolean)
5. **charAt(2)** => The String class charAt() method returns a character at specified index.
6. **length()** => The String class length() method returns length of the specified String.
7. **Integer.parseInt(str)** => This Methods used to convert a String to an Int in Java
8. **convert int to string**
- toString() => Integer.toString(110)
- valueOf() => String.valueOf(10)
9. **s1.replace("Java","Kava")** => //replaces all occurrences of "Java" to "Kava" 
10. **str.indexOf('g') && str.indexOf("Goa")** => This method returns the index within this string of the first occurrence of the specified character or -1, if the character does not occur.
11. **str.indexOf('g', 13) && str.indexOf("Goa", 13)** => start searching after 13th index
12. **Str.substring(10)** => The substring begins with the character at the specified index and extends to the end of this string. Endindex of the substring starts from 1 and not from 0.
13. **Str.substring(10, 16)** => return new string start from 10 to 15 index
14. **str.isEmpty()** => return true if string not contains any value nor a single space.
15. **name.contains("about")** => return true if String str is like "tell me about yourself". //case sensetive
16. **str1.concat(str2)** => hello and world becomes helloworld. `you can also use + sign`
17. **str.equals("hello")** => compare two string and return boolean. //case sensetive

**StringBuffer** vs **StringBuilder**
Java StringBuilder class is used to create mutable (modifiable) String. The Java StringBuilder class is same as StringBuffer class except that it is non-synchronized.

| **StringBuffer** | **StringBuilder** |
| ---------------- | ----------------- |
| heap area        | heap area |
| mutable object   | mutable object |
| consume less memory | consume less memory |
| all method are sychronised thus its thread safe. | non sychronised method nor thread safe. |
| fast as compare to string | fast as compare to stringBuffer |
| use when data is changing frequently. | use when data is changing frequently. |

# Character

**Creating a Character object:**
- Character ch = new Character('a');
- char ch='a';

The Character class is immutable like String class i.e once it’s object is created, it cannot be changed.
1. **Character.isDigit('A')** => return true if given char is a number
2. **Character.isLetter('A')** => return true if given char is a Letter
3. **Character.isWhitespace('A')** => return true if given char is a whiteSpace
4. **Character.isUpperCase('A')** => return true if given char is in uppercase
5. **Character.isLowerCase('A')** => return true if given char is in lowercse
6. **Character.toUpperCase('a')** => return given char in uppercase
7. **Character.toLowerCase('A')** => return given char in lowercase
8. **Character.toString('x')** => return given char in string

# ASCII value
ASCII is an acronym that stands for American Standard Code for Information Interchange. In ASCII, a specific numerical value is given to different characters and symbols

**There are 4 ways to print ASCII value or code of a specific character**

1. Using brute force Method 
 `simply assign the character to a new variable of integer type.`
```java
public class GFG {
	public static void main(String[] args)
	{
		char ch = 'A';
		int ascii = ch;
		System.out.println("The ASCII value is" + ascii);
	}
}
```
2. Using the type-casting Method
`Type-casting in java is a way to cast a variable into another datatype which means holding a value of another datatype occupying lesser bytes.`
```java
import java.util.*;
public class GFG {
	public static void main(String[] args)
	{
		char ch = 'A';

		System.out.println("The ASCII value is " + (int)ch);
	}
}
```
3. Using the format specifier Method
`We have stored the value of the given character inside a formal specifier by specifying the character to be an int. Hence, the ASCII value of that character is stored inside the format specifier.`
```java
import java.util.Formatter;

public class GFG {
	public static void main(String[] args)
	{
		char character = '}';
		Formatter formatSpecifier = new Formatter();
		formatSpecifier.format("%d", (int)character);

		// Print the corresponding ASCII value
		System.out.println(
			"The ASCII value is" + formatSpecifier);
	}
}
```
4. Using Byte class Method
`search on google`

**ASCII Value Of**
**A-Z** =>(65-90)
**a-z** =>(97-122)
**0-9** =>(48-57)

## Wrapper Classes
Wrapper classes provide a way to use primitive data types (int, boolean, etc..) as objects.

| **Premitive Data type** | **Wrapper Class** |
| ---------------- | ----------------- |
| byte       | Byte |
| short   | Short |
| int | Integer |
| char | Character |

Sometimes you must use wrapper classes, for example when working with Collection objects, such as ArrayList, where primitive types cannot be used (the list can only store objects):

**Creating Wrapper Objects**
To create a wrapper object, use the wrapper class instead of the primitive type. To get the value, you can just print the object:
```java
public class Main {
  public static void main(String[] args) {
    Integer myInt = 5;
    Double myDouble = 5.99;
    Character myChar = 'A';
    System.out.println(myInt);
    System.out.println(myDouble);
    System.out.println(myChar);
  }
}
```
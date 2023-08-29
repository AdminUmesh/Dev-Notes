# <p style="text-align: center;">Java Keywords</p>
*****

## super keyword
Super Keyword refer the object of super class, it is used when we want to call the super class variable, method and constructor through sub class object.

- Whenever the super class and sub class varieble and method name both are same than it can be used only.

- To avoid the confusion between super class and sub class variebles and methods that have same name we should use super keyword.

**Call a super class object**
```java
class A{
int a =20;
}
class B extends A{
int a =10;
void show(){
// Output: 10 (Give priority of sub class)
System.out.println(a);
// Output: 20 (its call super classs)    
System.out.println(super.a);
}
}
public class Main{
  public static void main(String[] args) {
    B b=new B();
    b.show();
  }
}
```

**Call a super class method()**

```java
class A{
void show(){
System.out.println("This is class A");
}
}
class B extends A{
void show(){
// to call super class method
super.show();
System.out.println("This is class B");
}
}
public class Main{
  public static void main(String[] args) {
    B b=new B();
    b.show();
  }
}
/* Output:
This is class A
This is class B
*/
```
**Call a super class constructor**

- In case of default constructor java compiler automatically add super keyword and call both constructor. `So you may be add or not both are accepted`

- but in parameterised constructor it's compulsary to add a super keyword.
```java
class A{
A(int a){
System.out.println("constructor A " + a);
}
}
class B extends A{
B(){
super(100);
System.out.println("constructor B");
}
}
public class Main{
  public static void main(String[] args) {
    B b=new B();
  }
}
/* Output:
constructor A 100
constructor B
*/
```

## final Keyword
The final keyword can be used to prevent class inheritance or to prevent method overriding.`final is a modifier which provide restriction in java.`

**final veriable:-** Once we declare a varieble as a final we can't perform re-assignment.

```java
class Test{
  public static void main(String [] args){
    // variable assign in capital letter for better understanding.
    final int A=5;
    A=20;       // Can't possible to reassign.
  }
}
```
**final method:-** When we declare a method as final it can't be overriden to our extended class.

**final class:-** When we declare a class as final it can't be extended or inherited to sub classes.

## this Keyword
1. The `this keyword` refers to the current object, and is only available inside constructor or methods.

```java
class A{
  void show(){
    System.out.println(this);
  }
  public static void main(String [] args){
    A r=new A();
    System.out.println(this);
    // Output: A@7a1105d (A unique reference Number)
    r.show();
    // Output: A@7a1105d (Same unique reference Number)

    // Current object and this both are print same unique number. So its proove `this keyword` refers to the current object.
  }
}
```

2. Whenever the name of instance and local varieble are same then our runtime invironment JVM gets confused that which one is local variable and which one is instance variable, to avoid this problem we should use this keyword.

```java
class A{
  int a;
  A(int a){
    this.a=a;  // if we use `a` insted of `this.a` its print only 0 (default int value)
  }
  void show(){
    System.out.println(a);
  }

  public static void main(String [] args){
    A r=new A(100);
    r.show();
    // Output: 100
  }
}
```

3. It is also useed when we want to call a default constructor of its own class.

```java
class A{
  A(){
    System.out.println("Default Constructor");
  }
  A(int a){
    this();  // its call default constructor
    System.out.println(a);
  }

  public static void main(String [] args){
    A r=new A(100);
    // Output: Default Constructor 100
  }
}
```
4. It's also call a parametorised constructor of its own class.

```java
class A{
  A(){
    this(50);  // Call below parametorised constructor
  }
  A(int a){
    System.out.println(a);
  }

  public static void main(String [] args){
    A r=new A();
  }
}

// Output: 100
```

## new Keyword
- New Kewwords create new object and it is used to allocate dynamic memory at runtime

- In java object memory create in Heap area.

- The Java new keyword is used to create an instance of the class. In other words, it instantiates a class by allocating memory for a new object and returning a reference to that memory. We can also use the new keyword to create the array object.

**Uses**
1. Call instance variable and non static method.
2. Call constructor
3. To create array

## instanceof
You can use the instanceof keyword to check if an object belongs to a specific class or not.

```Java
import java.io.*;

class GFG {
    public static void main(String[] args)
    {
 
        // Creating object of class inside main()
        GFG object = new GFG();
 
        // Returning instanceof
        System.out.println(object instanceof GFG);
    }
}

/* Output
true */
```
## Package
a package arrange number of classes, interfaces and sub-package of same type into a particular group.

**1. Pre-define package**
The package which are already created by java developer are called pre-define package.

- **java.lang**
It is the default package of java, its Provides classes that are fundamental to the design of the Java programming language. `Ex- System, String, Object, Integer etc..`

- **java.util**
This package is used to import data structure in java. `Ex- LinkedList, Stack, Vector, Hashset, TreeSet etc..`

- **java.io**
IO stands for Input/Output. This package is very usefull to perform input/output operation on file. `Ex- file, fileWriter, FileReader, etc..`

- **java.applet**
This packasge mainly use to develop GUI releted application. Applet program are web related program created at server but excuted at client matchine. `Ex- Applet`

- **java.awt**
awt stands for Abstract Window Tool kit. It is also used to develop GUI applicatin. The only deffence between applet & awt program is awt Programs are stand alone programs (Creation and execution are in same system) & it contain main() methon unlike applet. `Ex- Frame, Button, Textfeild etc..`

- **java.net**
Used when work on network. `Ex- URl, InetAddress, URLConnection and so on.`

- **java.SQL**
Used when doing database related work. It provide some pre-define Interfaces `like- Connection, Statement, ResultSet etc..`

- **javax.Swing**
Used to create GUI Application and in this available all java.awt features, also some extra features are `JFrame, JButton, JTextFeild etc..`
Swing is plateform independent where awt is plateform dependent.

```java
import java.lang.System; // Lang is a ByDefault package
import java.lang.String; // you may be import or not

import java.io.File;
import java.io.IOExeption;

import java.util.Scanner;

class fileReader{
  public static void main(String[]args){
    try{
      File r=new File("c:\\Users\\lenovo\\Desktop\\LC.txt");
      Scanner sc=new Scanner(r);
      while(sc.hasNextLine()){
        System.out.println(sc.nextLine());
      }
    }
    catch(IOException e)
    {
     System.out.println("Exception Handled"); 
    }
  }
}

// Output: Real all LC.text file data line by line
```
**2. User-define package**
The package which are created by java user for personal use is called User-define package. `Syntax:- package package-name;`

**Rule**
1. package statement must be define on first line of the program.

2. The way of compilation of these classes would be defferent. `Ex- javac -d . classname.java`

**Access Modifier**
| Access Modifier | Within class | Within Package |  Within package by subclass | Outside Package |
| ----------------| ------------ |----------------| --------------------------- | --------------- |
| **Private**     |     Yes      |       No       |             No              |        No       |
| **Default**     |     Yes      |      Yes       |             No              |        No       |
| **Protected**   |     Yes      |      Yes       |             Yes             |        No       |
| **Public**      |     Yes      |      Yes       |             Yes             |        No       |

**A.java**
```java
package akhilesh;
public class A{
  public void show(){
    System.out.println("This is Class A");
  }
}
```
**C.java**
```java
package ankush;
public class B{
  public void show2 extends(){
    System.out.println("This is Class B");
  }
}
public class C{
  public static void main(String [] args){
    A r=new A();
    r.show();
  }
}

// Output: This is class A
```

**How to Run**
**javac -d . A.java** first package class name
**javac -d . C.java** second package class name
**java ankush.c** main method package.class name

**Advantage**
**1. Reusablity -** We can use it in same or different project by import the package
**2. Security -** We can provide security by access modifier.
**3. Fast Searching -** We can search it by package, not whole class a time.
**4. Naming conflecting -** we can give same class name in different package.
**5. Hiding -** without source code give a package to other friend

**Dis-advantage**
We can't pass parameter to package.

## @annotation
Annotations are used to provide supplemental information about a program. Annotations start with ‘@’.

**@override**
Used to mark overriden elements in the child class.


# <p style="text-align: center;">Java Exeptions</p>
*****

An Exception is unexpected/unwanted, abnormal Situation that occured at runtime called exeption.

**Exeption handling**
In Exception handling we should have an alternative source through which we have handle the exception.

## Exeption hierarchy
Throwable class is the super or root class of java exeption  hierarchy which contains two sub classes that is 

**1. Exeption**
- Runtime Exeption
  1. Arthmetic Exeption 
  2. NullPointer Exeption
  3. NumberFormate Exeption
  4. IndexOutOfBond Exeption
     - ArrayIndex Out Of Bond Exeption
     - StringIndex Out Of Bond Exeption
- IO Exeption 
  1. EOF Exeption
  2. File Not Found Exeption
- SQL Exeption
- Interrupted Exeption
- Classs not Found Exeption

**2. Error**
- StackOverFlow 
- OutOfMemory
- IO Error
- Linkage Error

## Java Provides Some mechanism to work with exeption

**1. try**
Whenever we write a statement and if the statement is error suspecting stetment or risky code then put that code in try block. `try is a block that contain only risky code.`

**2. catch**
The main purpose of catch block is to handle the exeption which are throws by try block. `It is used to handle the exeption.`

```java
class Main{
  public static void main(String [] args){
    String str = null;

    // put riskey code here
    try{
      System.out.println(str.toUpperCase());
    }

    // Handle Exeption here
    catch(NullPointerException n){
      System.out.println("Print catch block");
    }
  }
}

```

**3. throws** 
Java throws keyword is used in the method signature to declare an exception which might be thrown by the function while the execution of the code

**4. throw**
throw keyword is used to throw the user define or customized exception object to the JVM explicity.

**Ex-1**
```java
public class Main {

  // throws ArithmeticException is hint that this method can have Exeptions
  static int Devide(int x, int y) throws ArithmeticException { 
  return x/y;
  }
 
 public static void main(String[] args) { 
 try{
   int z= Devide(15,3); 
   System.out.println(z);
   }

   // Exception keyword send the type of Exeption in e
   catch(Exception e){
   System.out.println(e);
   }
 } 
}
```

**Ex-2**
```java
public class Main {
  static void checkAge(int age) { 

    // Create self Exeption
    if (age < 18) {
      throw new ArithmeticException("Access denied - You must be at least 18 years old."); 
    } else {
      System.out.println("Access granted - You are old enough!"); 
    }
 } 
 
 public static void main(String[] args) { 

  // Handle Exception here
 try{
   checkAge(15); 
   }
   catch(Exception e){
    System.out.println(e);
   }
 } 
}
```
**5. finally**
The finally block executes whether exception rise or not and whether exception handled or not.

**Code Example:-**

**Syntex:-**
```java
class FinnalyClass{
try{
  // Statement 1;
  // Statement 2;
  // Stetment 3;
}
catch(Exeption e){
  // Statement 4;
}
finally{
  // Statement 5;
}
  // Statement 6;
}
```
**Multiple Casses on basis of above example**

**Case-1:** `If No Exeption in try`
Execute Statement- 1,2,3,5,6   **(Normal termination)**- means all code executed successfully

**Case-2:** `if Exeption in Statement-2`
Execute Statement- 1,4,5,6  **(Normal termination)**- means all code executed successfully

**Case-3:** `if Exeption in Statement-2 but not match in catch`
Execute Statement- 1,5 **(Abnormal termination)**- means all code not executed successfully

**Case-4:** `if Exeption in Statement-2 and also in catch`
Execute Statement- 1,5 **(Abnormal termination)**- means all code not executed successfully

**Case-5:** `if Exeption in Statement-2, catch and finally block`
Execute Statement- 1 **(abnormal termination)** means all code not executed successfully

## Multiple try catch block
```java
class Main{
  public static void main(String [] args){
    
    // ArithmeticExeption Exeption
    try{
      int a=10, b=0, c;
      c=a/b;
      System.out.println(c);
    }

    // Handle ArithmeticExeption Exeption here
    catch(ArithmeticExeption a){
      System.out.println("can't devided by zero");
    }

    // ArrayIndexOutOfBounds Exeption
    try{
      int [] arr={5,8,9,6,4};
      System.out.println(arr[8]);
    }

    // Handle ArrayIndexOutOfBounds Exeption here
    catch(ArrayIndexOutOfBoundsExeption b){
      System.out.println("Can't access array more than length");
    }
  }
}
```

## Multiple catch block
in multiple catch block you can pass multiple number of catch block for single or multiple try.

```java
class Main{
  public static void main(String [] args){
    
    try{
      // ArithmeticExeption Exeption
      int a=10, b=0, c;
      c=a/b;
      System.out.println(c);

      // NullPointer Exception
      String str = null;
      System.out.println(str.toUpperCase());

      // ArrayIndexOutOfBounds Exeption
      int [] arr={5,8,9,6,4};
      System.out.println(arr[8]);
    }

    // Handle Exeption here
    catch(NullPointerException n){
      System.out.println("Null is not a string");
    }

    // Handle ArrayIndexOutOfBounds Exeption here
    catch(ArrayIndexOutOfBoundsExeption b){
      System.out.println("Can't access array more than length");
    }

    // Handle ArithmeticExeption Exeption here
    catch(ArithmeticExeption a){
      System.out.println("can't devided by zero");
    }

    // Handle Anytype of Exeption
    catch(Exeption e){
      System.out.println("if not match upper then handle here");
    }
  }
}
```
## Nested try block
A try block which contain inside another try block called nested try block.

**Syntax:-**
```java
class FinnalyClass{
try{
   // Statement 1;
    try{
         // Statement 2;
         // Statement 3;
       }
    catch(Exeption e)
       {
         // Statement 4;
       }
}
catch(Exeption e){
  // Statement 5;
}
  // Statement 6;
}
```
**Multiple Casses on basis of above example**

**Case-1:** `If No Exeption in try`
Execute Statement- 1,2,3,6   **(Normal termination)**- means all code executed successfully

**Case-2:** `if Exeption in Statement-2`
Execute Statement- 1,4,6  **(Normal termination)**- means all code executed successfully

**Case-3:** `if Exeption in Statement-1`
Execute Statement- 5,6 **(Abnormal termination)**- means all code not executed successfully

## Nested catch block
An catch block which contain inside another catch block is called nested catch block.

**Syntax:-**
```java
class FinnalyClass{
try{
   // Statement 1;
   // Statement 2;
   }
catch(Exeption e)
{
  // Statement 3;
     try{
         // Statement 4;
        }
    catch(Exeption e)
        {
         // Statement 5;
        }
}
  // Statement 6;
}
```
**Multiple Casses on basis of above example**

**Case-1:** `If No Exeption in try`
Execute Statement- 1,2,6   **(Normal termination)**- means all code executed successfully

**Case-2:** `if Exeption in Statement-1`
Execute Statement- 3,4,6  **(Normal termination)**- means all code executed successfully

**Case-3:** `if Exeption in Statement-1 and Statement-4`
Execute Statement- 3,5,6 **(Abnormal termination)**- means all code not executed successfully

## Checked vs Unchecked Exeption

-  The exeption which are checked by compiler for smooth execution of program at runtime are called runtime exeption. `The exeption which are not checked by compiler and it directly tacken care by JVM`.

- Checked exepiton are commonly occured exeption so the compiler takes very much care about these exeption. `Unckecked exeption are rarely occured exeption so the compiler doesn't take very much care about these exeption.`

**Exeption hierarchy**
Throwable class is the super or root class of java exeption  hierarchy which contains two sub classes that is 

**1. Unchecked Exeption**

**Exeption**
- Runtime Exeption
  1. Arthmetic Exeption 
  2. NullPointer Exeption
  3. NumberFormate Exeption
  4. IndexOutOfBond Exeption
     - ArrayIndex Out Of Bond Exeption
     - StringIndex Out Of Bond Exeption

**Error**
- StackOverFlow 
- OutOfMemory
- IO Error
- Linkage Error

**2. Checked Exeption**

**Exeption**
- IO Exeption 
  1. EOF Exeption
  2. File Not Found Exeption
- SQL Exeption
- Interrupted Exeption
- Classs not Found Exeption

**Reference**
[Click here](https://youtu.be/nY37l7NTiII?si=MiP1Nwl9yn_B4WPR)
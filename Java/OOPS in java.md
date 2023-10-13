# <p style="text-align: center;">OOPS IN Java</p>
*****

Oops is a way of programming that help us to make code simple, reusable, and Secure.


**Benefits of OOP**
1. Simple and faster
2. Reusable
3. Easier to debug
4. Securely protects sensitive information through encapsulation


## Classes
Class is a template for objects, and an object is an instance of a class.


Everything in Java is associated with classes and objects, along with its attributes and methods.

| Pre Define Class | User Define Class |
| -----------------| ------------------|
|      Scanner     |       Dog         |
|      Console     |        A          |
|      System      |       Test        |
|      String      |       Demo        |

**Benifits of class**
A class is used to bind data as well as methods together as a single unit.

## Object
Object is an instance of class that executes the class. Ones the object is created it takes up space like other varieble in memory.

- Objects of a class are created using the new keyword.

```Java
public class Main {
  int x = 5;

  public static void main(String[] args) {
    // Created Object
    Main myObj = new Main();
    System.out.println(myObj.x);
  }
}
```
**You can create multiple objects of one class:**
```Java
public class Main {
  int x = 5;

  public static void main(String[] args) {
    Main myObj1 = new Main();  // Object 1
    Main myObj2 = new Main();  // Object 2
    System.out.println(myObj1.x);
    // Output 5
    System.out.println(myObj2.x);
    // Output 5
  }
}
```

**You can also create an object of a class and access it in another class.**
```Java
public class Main {
  int x = 5;
}
class Second {
  public static void main(String[] args) {
    Main myObj = new Main();
    System.out.println(myObj.x);
    // Output 5
  }
}
```

## Access Modifiers
Properties and methods can have access modifiers which control where they can be accessed.

**There are three access modifiers:**
1. **public -** The access level of a public modifier is everywhere. It can be accessed from within the class, outside the class, within the package and outside the package.
2. **protected -** The access level of a protected modifier is within the package and outside the package through child class. If you do not make the child class, it cannot be accessed from outside the package.
3. **private -** the property or method can only be accessed within the class
4. **Default -** The access level of a default modifier is only within the package. It cannot be accessed from outside the package. `If you do not specify any access level, it will be the default.`


## COSTRUCTOR

**Define constructor?**
Constructor is a special type of method whose name is same as class name. `java create constructor by default (When we not create construcor manually) then we have to create object of class `

- The main purpose of constructor is initialize the object.

- Every java class has a constructor.

- Constructor is automatically called at the time of object creation.

- Constructor never contain any return type including void.

```Java
class A {
  int abc;
  String name;

  A() {
    abc = 0;
    name = null;
  }
  void show() {
    System.out.println(abc + " " + name);
  }
}

class B {
  public static void main() {
    A ref = new A();
    ref.show();
  }
}
```
**1. Default Constructor**
a constuctor which does not have any parameter is called default constructor. `default constructor only return default value`

**2. Parameterised Constructor**
a constructor in which we have pass one or more parameret is called parameterised constructor.

**3. Copy Constructor**
When we pass object reference in constructor that is called copy constructor.

```java
class A{
  int a; String b;
  A(){
    a=10; b="learn codding";
    System.out.println(a + "" + b)
  }
  // copy constructor
  A(A ref){
    // set value of previous a method in current a
    a= ref.a;

    // set value of previous b method in current b
    b= ref.b;
    System.out.println(a + "" + b)
  }
}
class B{
  // Create object
  A r=new A();

  // pass object as reference
  A r2=new A(r);
}

// Output (Print 2 times same result )
```
**4. Private Constructor**
It is possible to make a constructor private in java `using private keyword` before constructor name. `it helps us avoid to acceess constructor contains in other class.`

- We can't make object of a private constructor in other class.

**Constructor Overloading**
Whenever we have more constructor in our class then we called it constructor overloading
```java
class ConstructorOverloading{
  int a; int b; int c;

  ConstructorOverloading(){
    a=10; b=15; c=25;
  }

  ConstructorOverloading(int x){
    a=10;
  }

  ConstructorOverloading(int x, int y){
    a=10; b=15;
  } 
}
class Test{
  public static void main(String [] args){
    ConstructorOverloading first = new ConstructorOverloading();
    ConstructorOverloading first2 = new ConstructorOverloading(10);
    ConstructorOverloading first3 = new ConstructorOverloading(10,20);
    //print a variable 
    System.out.println(first.a);
  }
}
```
## INHERITANCE
When we construct a new class from existing class such a way that the new class access all the features and properties from existing class called inheritance.

- Defined using the extends keyword.
- Protected properties or methods can be accessed within the class and by classes derived from that class.
- its provide code reusablity
- method ovveriding only possible through inheritance.

1. **Single/simple Inheritance**

Single inheritance support only one supper and one sub class

```Java 
class Super
{
  System.out.println("one one super class");
}
class Sub extends Super{
  System.out.println("only one sub class");
}

```

2. **Multilevel Inheritance**

In this type of inheritance, we will have more than 2 classes and a parent class will be inherited by a child class then that child class will be inherited by the another child class.

```Java
class Super
{
  System.out.println("This is super class");
}
class Sub1 extends Super{
  System.out.println("This is sub class");
}
class Sub2 extends Sub1{
  System.out.println("This is another sub class");
}

```


3. **Hierarchical Inheritance**
In this type of inheritance, a parent class is direct inherited by more than one child class.

```Java
class Super
{
  System.out.println("This is super class");
}
class Sub1 extends Super{
  System.out.println("This is sub class");
}
class Sub2 extends Super{
  System.out.println("This is another sub class");
}
```

4. **Multiple Inheritance**

```Java
class Super1
{
  System.out.println("This is super class");
}
class Supper2{
  System.out.println("This is sub class");
}
class Sub extends Super1, Super2{
  System.out.println("This is another sub class");
}
```

Java doesn't supports Multiple Inheritance. `Java Support multiple inheritance using interface`

**Why java does not support multiple inheritance?**
Whenever a subclass want to inherite feature and property of two or more supper classes, that have same methods java compiler can't decide which class method it should inherit. `then there might be achance of memory dublication`

## ABSTRACT CLASSES
Abstract class is a restricted (Secure) class that can't be used to create object/instance. to access it, it must be inherited from another class

- abstract class declare using **abstract** Keyword
- it may or may not contain abstract method.
- to use abstract class you have to  inherit it from another class.
- If a class contains at least one abstract method then compulsory should declare a class as abstract

```Java
// Abstract class
abstract class Sunstar {
	abstract void printInfo();
}

// Abstraction performed using extends
class Employee extends Sunstar {
	void printInfo() {
		String name = "avinash";
		int age = 21;
		float salary = 222.2F;

		System.out.println(name);
		System.out.println(age);
		System.out.println(salary);

	}

}

// Base class
class Base {
	public static void main(String args[]) {
		Sunstar s = new Employee();
		s.printInfo();
	}
}

```
**Real life use**
Let's in a university application we make a class in which we declare fee report of all student, and we want only authorised person can see the fee detail Then we must have to make this class abstract. So that developer can't call directlly via make a object of this class.

## Abstract Method
A abstract method is declare using abstract keyword. Can only be used in an abstract class, and it does not have a body. The body is provided by the subclass (inherited from).

- it does not contain any body {} `Ex-  abstract void test();`
- abstract method must be ovveriden in sub class otherwise it also become a abstract class.
- whenever action are common but implimentation are different then we should use abstract method.
  **free time visit learn codding for example of abstract class and method**

## INTERFACES
Interface is just like a class which contains only abstact method.

`Another way to achieve abstraction in Java, is with interfaces. An interface is a completely "abstract class" that is used to group related methods with empty bodies:`

- Interface class declare using **Interface** keyword.
- To achieve interface in Java use **implements** keyword.
- Interface methods do not have a body - the body is provided by the "implement" class
- Interface is works like a abstract class so we can't implement as well as create object of Interface.
- Interface variable are bydefault public + static + final.
- Interface methods are bydefault public + abstract

```Java
interface Animal {
  public void animalSound(); // interface method (does not have a body)
  public void sleep(); // interface method (does not have a body)
}

// Pig "implements" the Animal interface
class Pig implements Animal {
  public void animalSound() {
    // The body of animalSound() is provided here
    System.out.println("The pig says: wee wee");
  }
  public void sleep() {
    // The body of sleep() is provided here
    System.out.println("Zzz");
  }
}

class Main {
  public static void main(String[] args) {
    Pig myPig = new Pig();  // Create a Pig object
    myPig.animalSound();
    myPig.sleep();
  }
}
```

**Why and when we use Interface?**
1. To achieve security - hide certain details and only show the important details of an object (interface).

2. Java does not support "multiple inheritance" (a class can only inherit from one superclass). However, it can be achieved with interfaces, because the class can implement multiple interfaces. Note: To implement multiple interfaces, separate them with a comma (see example below).


## STATIC METHOD AND PROPERTY
- Declared with the **static** keyword:

- Static methods can be called directly - without creating an instance of the class first.

- The static keyword in Java is used for memory management mainly. We can apply static keyword with variables, methods, blocks and nested classes.

- we can't access a static member in non static static directly. `We can access it with classname`

```Java
class Adder{  
  static int add(int a,int b){
  return a+b;
  }  
  static int add(int a,int b,int c){
  return a+b+c;
  }  
}  
class TestOverloading1{  
public static void main(String[] args){  
System.out.println(Adder.add(11,11));  
System.out.println(Adder.add(11,11,11));  
}}  
```
**Why is the Java main method static?**
It is because the object is not required to call a static method. If it were a non-static method, JVM creates an object first then call main() method that will lead the problem of extra memory allocation.

## ITERABLE or ITERATOR
An iterable is any value which can be looped through with a foreach() loop

- Iterators are used in Collection framework in Java to retrieve elements one by one.

```Java
for(Item item: customDataStructure) {
    // do stuff
}
```

## ENCAPTULATION
Encaptulation is a mechanism through which we can wrapping the data member and member method of class in a single unit called encaptulation. `(With the help of encaptulation we can access the private variable outside the class.)`

`Encaptulation is a programming technique that binds the class member togrther and prevent them from being access by another classs.`

- Declare the class varieble as privet.
- Declare the class method as public

```Java
class Welcome {
  private int value;
  
  public void SetValue(int value){
  this.value=value;
  }
  
  public int GetValue(){
  return this.value;
  }
  
}
class Main{
  public static void main(String [] args){
  Welcome obj = new Welcome();
  // Update $value
  obj.SetValue(100);
  // Access $value
  int MyValue=obj.GetValue();
  // Print MyValue
  System.out.println(MyValue);
  }
  }
```

**Advantages of Encapsulation:**

- **Data Hiding and Abstraction:**
Unnecessary details, internal representation and implementation are hidden from the end-users for protection of data structure and the Class. Data access is prohibited from members of other classes by creating private methods.

**Note:**
Encapsulation is used to hide internal views from the client.

- **Data security:**
Encapsulation helps in making data very robust and secured, as the data and member functions are wrapped together to form an object. All the tasks are done inside without any external worry and it also makes life very easy.


## POLYMORPHISM
Polymorphism is a concept in which a class containg more than one method with the same name but behaviour of each method is different is called polymorphism. 

**Usually, polymorphism is of two types**

1. **Compile time polymorphism** (Example of method overloading)
A polymorphism which is exists at the time of compilation is called **`compile time`** or **`early binding`** or **`static polymorphism`**.

- **Method Overloading:-** Whenever a class contain more than one method with same name and different types of parameters called method overloading.
```java
public class Sum {
	// Overloaded sum(). This sum takes two int parameters
	public int sum(int x, int y)
  {
    return (x + y);
  }

	// Overloaded sum(). This sum takes three int parameters
	public int sum(int x, int y, int z)
	{
		return (x + y + z);
	}

	// Overloaded sum(). This sum takes two double
	// parameters
	public double sum(double x, double y)
	{
		return (x + y);
	}

	// Driver code
	public static void main(String args[])
	{
		Sum s = new Sum();
		System.out.println(s.sum(10, 20));
		System.out.println(s.sum(10, 20, 30));
		System.out.println(s.sum(10.5, 20.5));
	}
}
```

2. **Run time polymorphism** Example of method overriding
A polymorphism which is exists at run time is called **`run time`** or **`late binding`** or **`dynamic polymorphism`**.

**Method Overriding**
Whenever we writting method in super and sub classes in such a way thats method name and parameter must be same called method overiding.
 
```java
class Supper {
	public int sum()
  {
    System.out.println("Supper class");
  }
}
class Sub extends Super{
  @override
  // super.sum();   // its call supper class's sum() method
  public int sum()
  {
    // super.sum();
    System.out.println("Sub class");
  }
}
Class Main{
  public static void main(String [] args){
    Sub s=new Sub();
    s.sum()          // Output: Sub Class
  }
}
```
## ABSTRACTION
Data abstraction is the process of hiding certain details and showing only essential information to the user.
`Abstraction can be achieved with either abstract classes or interfaces`

**Ex:** A car is viewed as a car rather than its individual components.

```Java
abstract class Animal {
  // Abstract method (does not have a body)
  public abstract void animalSound();
  // Regular method
  public void sleep() {
    System.out.println("Zzz");
  }
}

// Subclass (inherit from Animal)
class Pig extends Animal {
  public void animalSound() {
    // The body of animalSound() is provided here
    System.out.println("The pig says: wee wee");
  }
}

class Main {
  public static void main(String[] args) {
    Pig myPig = new Pig(); // Create a Pig object
    myPig.animalSound();
    myPig.sleep();
  }
}
```

## Binding
Connecting a method call to the method body is known as binding.

**1. Static binding**
The binding which can be resolved at compile time by compiler is called Static binding. `Ex- Private,static...`

- compile time polymorephism.
- method overloading.

**2. Dynamic binding**
The binding which can be resolve at run time by JVM is known as dynamic binding or late binding.

- Run time polymorephysm
- Method Overriding

## Call by Reference vs Call by Value
 
**1. Call by value:-**
Call by Value means calling a method with a parameter as value. Through this, the argument value is passed to the parameter.

**Ex-1**
```java
class Operation{  
 int data=50;  
  
 void change(int data){  
 data=data+100;//changes will be in the local variable only  
 }  
     
 public static void main(String args[]){  
   Operation op=new Operation();  
  
   System.out.println("before change "+op.data);  
   op.change(500);  
   System.out.println("after change "+op.data);  
  
 }  
}  
/* Output:  before change 50
            after change 50 */
```

**Ex-2**
```java
class Operation2{  
 int data=50;  
  
 void change(Operation2 op){  
 op.data=op.data+100;//changes will be in the instance variable  
 }  
     
    
 public static void main(String args[]){  
   Operation2 op=new Operation2();  
  
   System.out.println("before change "+op.data);  
   op.change(op);//passing object  
   System.out.println("after change "+op.data);  
  
 }  
}  
/* Output:before change 50
       after change 150	*/
```
**2. Call by Reference:-**
While Call by Reference means calling a method with a parameter as a reference. Through this, the argument reference is passed to the parameter. `Java does not support call by reference.`
# OOPS in C#
OOP (Object-Oriented Programming) is a way of programming that helps us to make code simple, reusable, and secure.

**Benefits of OOP**
1. Simple and faster
2. Reusable
3. Easier to debug
4. Securely protects sensitive information through encapsulation

## Classes
A class is a template for objects, and an object is an instance of a class.

Everything in C# is associated with classes and objects, along with its attributes and methods.

|Predefined Class|	User Defined Class|
|----------------|--------------------|
|Console	     |      Dog|
|String|	A|
|System	|Test|

**Benefits of Class:** A class is used to bind data as well as methods together as a single unit.

## Object
An object is an instance of a class that executes the class. Once the object is created, it takes up space in memory like other variables.

- Objects of a class are created using the new keyword.
```csharp
public class Main {
    int x = 5;

    public static void Main(string[] args) {
        // Created Object
        Main myObj = new Main();
        Console.WriteLine(myObj.x);
    }
}
```
**You can create multiple objects of one class:**

```csharp
public class Main {
    int x = 5;

    public static void Main(string[] args) {
        Main myObj1 = new Main();  // Object 1
        Main myObj2 = new Main();  // Object 2
        Console.WriteLine(myObj1.x);
        // Output 5
        Console.WriteLine(myObj2.x);
        // Output 5
    }
}
```

**You can also create an object of a class and access it in another class:**

```csharp
public class Main {
    public int x = 5;
}

class Second {
    public static void Main(string[] args) {
        Main myObj = new Main();
        Console.WriteLine(myObj.x);
        // Output 5
    }
}
```

## Access Modifiers
Properties and methods can have access modifiers that control where they can be accessed.

**There are four access modifiers:**

1. **public:** The access level of a public modifier is everywhere. It can be accessed from within the class, outside the class, within the package, and outside the package.
2. **protected:** The access level of a protected modifier is within the package and outside the package through a derived class.
3. **private:** The property or method can only be accessed within the class.
4. **internal:** The access level of an internal modifier is only within the assembly. It cannot be accessed from outside the assembly.

## Constructor
**Define Constructor:**
A constructor is a special type of method whose name is the same as the class name. C# creates a constructor by default when we do not create one manually.

- The main purpose of a constructor is to initialize the object.
- Every C# class has a constructor.
- The constructor is automatically called at the time of object creation.
- A constructor never contains any return type, including void.

```csharp
public class A {
    public int abc;
    public string name;

    public A() {
        abc = 0;
        name = null;
    }

    public void Show() {
        Console.WriteLine($"{abc} {name}");
    }
}

class B {
    public static void Main(string[] args) {
        A refObj = new A();
        refObj.Show();
    }
}
```

**Types of Constructors:**

**1. Default Constructor:** A constructor that does not have any parameters.

**2. Parameterized Constructor:** A constructor that takes one or more parameters.

**3. Copy Constructor:** A constructor that passes an object reference in the constructor.

```csharp
public class A {
    public int a;
    public string b;

    public A() {
        a = 10;
        b = "Learn Coding";
        Console.WriteLine($"{a} {b}");
        //Output: 10 Learn Coding
    }

    // Copy constructor
    public A(A refObj) {
        a = refObj.a;
        b = refObj.b;
        Console.WriteLine($"{a} {b}");
        //Output: 10 Learn Coding
    }
}

class B {
    public static void Main(string[] args) {
        A r = new A(); // Create object
        A r2 = new A(r); // Pass object as reference
    }
}
```

**4. Private Constructor**
 It is possible to make a constructor private in C# using private keyword before constructor name. `it helps us avoid to acceess constructor contains in other class.`

- We can't make object of a private constructor in other class. (which means objects of the class cannot be created directly from outside the class.)
```C#
public class MyClass
{
    // Private constructor
    private MyClass()
    {
        // Constructor logic
        Console.WriteLine("Private constructor called");
    }

    // Static method to create an instance
    public static MyClass CreateInstance()
    {
        return new MyClass();
    }
}

class Program
{
    static void Main()
    {
        // MyClass obj = new MyClass(); // This will cause a compilation error
        MyClass obj = MyClass.CreateInstance(); // Correct way to create an instance
    }
}
```

### Constructor Overloading:
When we have more than one constructor in our class, it's called constructor overloading. (With different parameters)

```csharp
public class ConstructorOverloading {
    int a, b, c;

    public ConstructorOverloading() {
        a = 10; b = 15; c = 25;
    }

    public ConstructorOverloading(int x) {
        a = x;
    }

    public ConstructorOverloading(int x, int y) {
        a = x; b = y;
    }

    public static void Main(string[] args) {
        ConstructorOverloading first = new ConstructorOverloading();
        ConstructorOverloading first2 = new ConstructorOverloading(10);
        ConstructorOverloading first3 = new ConstructorOverloading(10, 20);
        Console.WriteLine(first.a);
    }
}
```

## Inheritance
Inheritance allows one class to acquire the properties and methods of another class.

- Defined using the : base keyword in C#.
- Provides code reusability.
- Method overriding is only possible through inheritance.

1. **Single Inheritance**
Single inheritance support only one supper and one sub class
```csharp
public class Super {
    public void Print() {
        Console.WriteLine("This is the superclass");
    }
}

public class Sub : Super {
    public void Display() {
        Console.WriteLine("This is the subclass");
    }
}
```

2. **Multilevel Inheritance**
In this type of inheritance, we will have more than 2 classes and a parent class will be inherited by a child class then that child class will be inherited by the another child class.
```csharp
public class Super {
    public void Print() {
        Console.WriteLine("This is the superclass");
    }
}

public class Sub1 : Super {
    public void Display() {
        Console.WriteLine("This is subclass 1");
    }
}

public class Sub2 : Sub1 {
    public void Show() {
        Console.WriteLine("This is subclass 2");
    }
}
```

3. **Hierarchical Inheritance**
 In this type of inheritance, a parent class is direct inherited by more than one child class.
```csharp
public class Super {
    public void Print() {
        Console.WriteLine("This is the superclass");
    }
}

public class Sub1 : Super {
    public void Display() {
        Console.WriteLine("This is subclass 1");
    }
}

public class Sub2 : Super {
    public void Show() {
        Console.WriteLine("This is subclass 2");
    }
}
```

4. **Multiple Inheritance:**
C# does not support multiple inheritance directly. However, it supports multiple inheritance through interfaces.
```c#
class A
{
    public void Display() { Console.WriteLine("A Display"); }
}

class B
{
    public void Display() { Console.WriteLine("B Display"); }
}

class C : A, B  // Error: Cannot have multiple base classes
{
}
// this is not possible in C#
```
## Why C# does not support multiple inheritance?
When a class inherits from multiple classes, there may be methods with the same name in both base classes. This leads to ambiguity—it's unclear which method should be called. For example:

## Abstract Classes
Abstract class is a restricted (Secure) class that can't be used to create object/instance. to access it, it must be inherited from another class

- An abstract class is declared using the abstract keyword.
- It may or may not contain abstract methods.
- If a class contains at least one abstract method, it must be declared as abstract.

```csharp
abstract class Sunstar {
    public abstract void PrintInfo();
}

class Employee : Sunstar {
    public override void PrintInfo() {
        string name = "John";
        int age = 30;
        float salary = 2500.5F;
        Console.WriteLine($"{name}, {age}, {salary}");
    }
}

class Base {
    public static void Main(string[] args) {
        Sunstar s = new Employee();
        s.PrintInfo();
    }
}
```
**Real life use:** Let's in a university application we make a class in which we declare fee report of all student, and we want only authorised person can see the fee detail Then we must have to make this class abstract. So that developer can't call directlly via make a object of this class.

## Abstract Method in C#:
- An abstract method is a method that is declared using the abstract keyword. It does not have a body (implementation). The body of the abstract method must be provided in the derived class.

- Abstract methods can only be declared within abstract classes.

- The derived class that inherits from the abstract class must provide an implementation for the abstract method unless the derived class is also abstract. If the derived class does not override the abstract method, it will also become an abstract class.

```c#
public abstract class Animal
{
    // Abstract method: No implementation in the base class
    public abstract void MakeSound();
}
```

## Interface
An interface in C# is similar to a class but  implicitly  contains abstract methods(This means that the interface does not provide the body of the methods, but you cannot declare a method in an interface using the abstract keyword). `interfaces are used.To achieve abstraction in C#`

- An interface class is declared using the interface keyword.
- To achieve interface implementation in C#, use the : syntex.
- Interface methods are implicitly public and abstract.
- Interface variables are public, static, and readonly by default.
- If a class implements an interface, it must provide implementations for all the methods defined in that interface.

```csharp
interface IAnimal {
    void AnimalSound();  // Interface method (does not have a body)
    void Sleep();        // Interface method (does not have a body)
}

class Pig : IAnimal {
    public void AnimalSound() {
        Console.WriteLine("The pig says: wee wee");
    }

    public void Sleep() {
        Console.WriteLine("Zzz");
    }
}

class MainClass {
    public static void Main(string[] args) {
        Pig myPig = new Pig();
        myPig.AnimalSound();
        myPig.Sleep();
    }
}
```
**Why and when we use Interface?**
1. **To Achieve Security -** An interface allows you to hide certain implementation details and expose only the essential methods and properties. This helps in encapsulating the logic, making the system more secure and easier to maintain.

2. **To Achieve Multiple Inheritance -** C# does not support multiple inheritance (i.e., a class cannot inherit from multiple classes). However, you can implement multiple interfaces in a class. This allows you to inherit functionality from multiple sources without the complexities of multiple inheritance. Note: To implement multiple interfaces in C#, separate them with a comma (see example below).

**Example and Clearification**
```C#
// Example of Class
public class Truck 
{
    public void Start()
    {
        Console.WriteLine("Truck engine is starting...");
    }

    public void Stop()
    {
        Console.WriteLine("Truck engine is stopping...");
    }
}

public class Car
{
    public void Start()
    {
        Console.WriteLine("Car engine is starting...");
    }

    public void Stop()
    {
        Console.WriteLine("Car engine is stopping...");
    }
}

public class MainClass
{
    public static void Main(string[] args)
    {
        Truck truck = new Truck();
        truck.Start();
        truck.Stop();

        Car car = new Car();
        car.Start();
        car.Stop();
    }
}
```
```C#
// Example of Interface
public interface IEngine
{
    void Start();
    void Stop();
}

public class Truck : IEngine
{
    public void Start()
    {
        Console.WriteLine("Truck engine is starting...");
    }

    public void Stop()
    {
        Console.WriteLine("Truck engine is stopping...");
    }
}

public class Car : IEngine
{
    public void Start()
    {
        Console.WriteLine("Car engine is starting...");
    }

    public void Stop()
    {
        Console.WriteLine("Car engine is stopping...");
    }
}

public class Bus : IEngine
{
    public void Start()
    {
        Console.WriteLine("Bus engine is starting...");
    }

    public void Stop()
    {
        Console.WriteLine("Bus engine is stopping...");
    }
}

public class MainClass
{
    public static void Main(string[] args)
    {
        IEngine vehicle;

        vehicle = new Truck();
        vehicle.Start();
        vehicle.Stop();

        vehicle = new Car();
        vehicle.Start();
        vehicle.Stop();

        vehicle = new Bus();
        vehicle.Start();
        vehicle.Stop();
    }
}
```
### Interface Key Point
**Decoupling Code**
Interfaces allow you to define functionality without tying your code to a specific implementation, which supports loose coupling and flexibility (e.g., using dependency injection).

**Multiple Inheritance**
A class can implement multiple interfaces, which is useful because C# does not support multiple inheritance of classes.

**access modifiers**
Where  a Class have access modifiers (public, private, etc.). But interface are public by default, cannot have access modifiers.

## Static Methods and Properties
- Declared with the **static** keyword:

- Static methods can be called directly - without creating an instance of the class first.

- We can apply static keyword with variables, methods, properties, constructors (static constructors), and nested classes.

```csharp
class Adder {
    public static int Add(int a, int b) {
        return a + b;
    }

    public static int Add(int a, int b, int c) {
        return a + b + c;
    }
}

class TestOverloading {
    public static void Main(string[] args) {
        Console.WriteLine(Adder.Add(11, 11));
        Console.WriteLine(Adder.Add(11, 11, 11));
    }
}
```

## ITERABLE or ITERATOR in C#:
An iterable is any object that can be looped through using a foreach loop.

- Iterators are used in the .NET collection framework to retrieve elements one by one.
```csharp
foreach (var item in customDataStructure) {
    // do stuff
}
```
`In C#, the foreach loop is used to iterate over collections that implement the IEnumerable interface, which makes them` iterable.

## Encapsulation
Encapsulation is the mechanism that binds the data and methods together in a single unit. `It also restricts direct access to the object's data by other class.`

Encapsulation allows access to private variables indirectly by providing controlled access through public methods (also called getters and setters) or properties. This means that the private variables themselves cannot be accessed directly from outside the class, but their values can be accessed or modified using public methods or properties.

- Declare the class varieble as privet.
- Declare the class method as public

```csharp
class Welcome {
    private int value;

    public void SetValue(int value) {
        this.value = value;
    }

    public int GetValue() {
        return this.value;
    }
}

class MainClass {
    public static void Main(string[] args) {
        Welcome obj = new Welcome();
        obj.SetValue(100);
        int myValue = obj.GetValue();
        Console.WriteLine(myValue);
    }
}
```

### Advantages of Encapsulation:
**Data Hiding:** The balance field is private, and cannot be accessed directly outside the BankAccount class.

**Control:** The Deposit and Withdraw methods control how money can be added or subtracted from the account, preventing invalid operations (like withdrawing more than the balance).

**Security:** The balance cannot be tampered with directly, and only the controlled public methods can alter it.

### Conclusion:
Encapsulation improves data security, code maintainability, and reduces complexity. It allows you to safeguard data integrity, enforce business rules, and provide a clear interface for interacting with the class.

## Polymorphism
Polymorphism is a concept in which a class containg more than one method with the same name but behaviour of each method is different is called polymorphism. 

**Compile-time Polymorphism (Method Overloading)(Static Polymorphism)**
A polymorphism which is exists at the time of compilation is called **`compile time`** or **`early binding`** or **`static polymorphism`**.

- **Method Overloading:-** Whenever a class contain more than one method with same name and different types of parameters called method overloading.

```csharp
public class Sum {
    public int Sum(int x, int y) {
        return x + y;
    }

    public int Sum(int x, int y, int z) {
        return x + y + z;
    }

    public double Sum(double x, double y) {
        return x + y;
    }

    public static void Main(string[] args) {
        Sum s = new Sum();
        Console.WriteLine(s.Sum(10, 20));
        Console.WriteLine(s.Sum(10, 20, 30));
        Console.WriteLine(s.Sum(10.5, 20.5));
    }
}
```

**Run-time Polymorphism (Method Overriding)(Dynamic Dispatch)(Dynamic Polymorphism)**
A polymorphism which is exists at run time is called **`run time`** or **`late binding`** or **`dynamic polymorphism`**.

- **Method Overriding-** Whenever we writting method in super and sub classes in such a way thats method name and parameter must be same called method overiding.
```csharp
public class Super {
    public virtual void Print() {
        Console.WriteLine("Super class");
    }
}

public class Sub : Super {
    public override void Print() {
        Console.WriteLine("Sub class");
    }
}

class MainClass {
    public static void Main(string[] args) {
        Super obj = new Sub();
        obj.Print();
    }
}
```
**Declaring an Object Superclass Type and Creating an Instance of Subclass**
```c#
Super obj = new Sub();
obj.Print(); // Output: "Sub class"

//if you make Object type as super class you can call super or sub class both
```

**Declaring an Object Superclass Type and Creating an Instance of Superclass**
```c#
Super obj = new Super();
obj.Print(); // Output: "Super class"

//if you make Object type as super class you can call super or sub class both
```

**Declaring an Object Subclass Type and Creating an Instance of Subclass**
```c#
Sub obj = new Sub();
obj.Print(); // Output: "Sub class"

//if you make Object type as Sub class you can only call sub class method
```

**Declaring an Object Subclass Type and Creating an Instance of Superclass**
```C#
Sub obj = new Super();
obj.Print(); 
//error CS0266: Cannot implicitly convert type 'Super' to 'Sub'. An explicit conversion exists (are you missing a cast?)

//if you make Object type as Sub class you can only call sub class method
```
**Call Super Class using sub class**
You can explicitly call the method of the superclass from the subclass using the `base` keyword.
```csharp
public class Super {
    public virtual void Print() {
        Console.WriteLine("Super class");
    }
}

public class Sub : Super
{
    public override void Print()
    {
        Console.WriteLine("Sub class");
        base.Print(); // Call the Super class method
    }
}

class MainClass {
    public static void Main(string[] args) {
        Super obj = new Sub();
        obj.Print();
    }
}

//output
//Sub class
//Super class
```

**Call super class with object and instance of Sub Class**
```c#
public class Super {
    public virtual void Print() {
        Console.WriteLine("Super class");
    }
}

public class Sub : Super {
    public override void Print() {
        Console.WriteLine("Sub class");
        // base.Print(); // This calls the Super class method inside Sub.
    }
}

class MainClass {
    public static void Main(string[] args) {
        Sub obj = new Sub();  // Instantiate Sub class

        // Call the Super class Print method directly
        ((Super)obj).Print();  // Cast to Super and call Print

        // Alternatively, the following would call the Sub's overridden method:
        // obj.Print(); 
    }
}
//Output
//Super class
```

## Abstraction
Data abstraction is the process of hiding certain details and showing only essential information to the user.
`Abstraction can be achieved with either abstract classes or interfaces`

```csharp
using System;

// Abstract class Animal
public abstract class Animal
{
    // Abstract method (does not have a body)
    public abstract void AnimalSound();

    // Regular method (with implementation)
    public void Sleep()
    {
        Console.WriteLine("Zzz");
    }
}

// Subclass (inherit from Animal)
public class Pig : Animal
{
    // Implementing the abstract method
    public override void AnimalSound()
    {
        // The body of AnimalSound() is provided here
        Console.WriteLine("The pig says: wee wee");
    }
}

class Program
{
    static void Main(string[] args)
    {
        // Create a Pig object
        Pig myPig = new Pig();
        
        // Call the overridden method
        myPig.AnimalSound();
        
        // Call the inherited method
        myPig.Sleep();
    }
}
```

# Binding in C#
Binding in C# refers to the process of connecting a method call to the method body. 

**In C#, there are two types of binding:**

### 1. Static Binding (Early Binding)
Static binding occurs when the method call is resolved at compile time by the compiler. This is also known as early binding.

**Characteristics:**
- Occurs at compile time.
- Used for method overloading and static methods.
- Commonly used with private, static, and final (in C# it's sealed) methods.

```csharp
// Example of Static Binding:
class Example
{
    private static void Display()
    {
        Console.WriteLine("Static Binding Example");
    }

    public static void Main(string[] args)
    {
        Display(); // Static method call resolved at compile time
    }
}
```
**Key Points:**

- The compiler resolves the method call during the compilation process.
- C# resolves method overloading statically, i.e., the compiler decides which method to call based on the method signature.

### 2. Dynamic Binding (Late Binding)
Dynamic binding occurs when the method call is resolved at runtime by the JIT compiler. This is known as late binding.

**Characteristics:**
- Occurs at runtime.
- Used with method overriding.
- Allows runtime polymorphism.

```csharp
// Example of Dynamic Binding:
using System;

class Animal
{
    public virtual void Sound()
    {
        Console.WriteLine("Animal Sound");
    }
}

class Dog : Animal
{
    public override void Sound() // Method overriding
    {
        Console.WriteLine("Dog Bark");
    }
}

class Program
{
    static void Main(string[] args)
    {
        Animal myAnimal = new Dog();
        myAnimal.Sound(); // Dynamic Binding at runtime
    }
}
```
**Key Points:**

- The method call is resolved during runtime based on the actual object type.
- C# uses the virtual and override keywords to enable dynamic binding.

# Call by Value vs. Call by Reference in C#

## 1. Call by Value
In Call by Value, a copy of the actual parameter's value is passed to the method. Modifications made to the parameter inside the method do not affect the original argument.

**Characteristics:**
- A copy of the value is passed.
- Changes made to the parameter inside the method do not reflect in the caller's variable.
- Used for value types (e.g., int, float, double, etc.).

```csharp
//Example of Call by Value in C#:
using System;

class Program
{
    static void ChangeValue(int num)
    {
        num = num + 100; // Modifies local copy
    }

    static void Main(string[] args)
    {
        int value = 50;
        Console.WriteLine("Before change: " + value);
        ChangeValue(value);
        Console.WriteLine("After change: " + value); // No change
    }
}
```
**Output:**
```mathematica
Before change: 50
After change: 50
```


## 2. Call by Reference
In Call by Reference, the method receives a reference (or memory address) to the argument, which means any changes made to the parameter inside the method will affect the original argument.

**Characteristics:**
- The reference (memory address) is passed.
- Changes made to the parameter will affect the original argument.
- Used for reference types (e.g., objects, arrays).
- In C#, Call by Reference is implemented using the ref or out keyword.

```csharp
// Example of Call by Reference in C#:
using System;

class Program
{
    static void ChangeReference(ref int num)
    {
        num = num + 100; // Modifies the original variable
    }

    static void Main(string[] args)
    {
        int value = 50;
        Console.WriteLine("Before change: " + value);
        ChangeReference(ref value);
        Console.WriteLine("After change: " + value); // Changes reflected
    }
}
```
**Output:**
```mathematica
Before change: 50
After change: 150
```

### Important Notes in C#:

- C# does not support Call by Reference directly for primitive data types like int, float, etc., but references to objects are inherently passed by reference.

- To use Call by Reference, C# requires explicit use of the ref or out keywords.

- Call by Value is the default for all value types (primitive types) in C#.

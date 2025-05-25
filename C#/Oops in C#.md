# OOPS in C#
OOP (Object-Oriented Programming) is a way of programming that helps us to make code simple, reusable, and secure.

**Benefits of OOP**
1. Simple and faster
2. Reusable
3. Easier to debug
4. Securely protects sensitive information through encapsulation

**What makes C# Object-Oriented**
C# is a programming language that follows the principles of object-oriented programming (OOP), including concepts like inheritance, polymorphism, encapsulation, and abstraction.

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
2. **protected:** The access level of a protected modifier is within the package/Class and outside the package/Class through a derived class.
3. **private:** The property or method can only be accessed within the class.
4. **internal:** The access level of an internal modifier is only within the assembly. It cannot be accessed from outside the assembly.

**Protected Example:-1**
```C#
using System;

namespace MyNamespace
{
    // Base class with a protected member
    public class Animal
    {
        // Protected member
        protected string name;

        public Animal(string name)
        {
            this.name = name;
        }
    }

    // Another class in the same namespace but not inherited
    public class AnotherClass
    {
        public void DisplayInfo()
        {
            Animal animal = new Animal("Lion");

            // Error: Cannot access 'name' because it is protected
            // Console.WriteLine(animal.name); // This line will cause a compilation error
        }
    }

    // Derived class in the same namespace
    public class Dog : Animal
    {
        public Dog(string name) : base(name) { }

        public void DisplayDogInfo()
        {
            // Accessing the protected member 'name' from the base class
            Console.WriteLine("Dog's Name: " + name);  // This is valid
        }
    }

    class Program
    {
        static void Main()
        {
            // Creating an instance of the derived class Dog
            Dog dog = new Dog("Buddy");
            dog.DisplayDogInfo();  // Output: Dog's Name: Buddy

            // The following line would result in an error in AnotherClass
            // AnotherClass anotherClass = new AnotherClass();
            // anotherClass.DisplayInfo();  // Error: 'name' is inaccessible due to its protection level
        }
    }
}
```
**Protected Example:-2**
```C#
using System;

namespace BaseNamespace
{
    // Base class with a protected member
    public class Animal
    {
        // Protected member
        protected string name;

        public Animal(string name)
        {
            this.name = name;
        }

        // A method to show the name of the animal
        public void ShowInfo()
        {
            Console.WriteLine("Animal Name: " + name);
        }
    }
}

namespace DerivedNamespace
{
    // Derived class in a different namespace
    public class Dog : BaseNamespace.Animal
    {
        public Dog(string name) : base(name)
        {
        }

        public void DisplayDogInfo()
        {
            // Accessing the protected member 'name' from the base class
            Console.WriteLine("Dog's Name: " + name);
        }
    }
}

class Program
{
    static void Main()
    {
        // Creating an instance of the Dog class
        DerivedNamespace.Dog dog = new DerivedNamespace.Dog("Buddy");

        // Accessing the 'name' property through the derived class method
        dog.DisplayDogInfo();  // Output: Dog's Name: Buddy

        // The following line would result in an error because 'name' is protected
        // Console.WriteLine(dog.name);  // Error: 'name' is inaccessible due to its protection level
    }
}
```

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

# Inheritance
Inheritance allows one class to acquire the properties and methods of another class.

- Defined using the : base keyword in C#.
- Provides code reusability.
- Method overriding is only possible through inheritance.

1. **Single Inheritance**
Single inheritance support only one supper and one sub class
```csharp
using System;

public class Super
{
    public void Print()
    {
        Console.WriteLine("This is the superclass");
    }
}

public class Sub : Super
{
    public void Display()
    {
        Console.WriteLine("This is the subclass");
    }
}

class Program
{
    static void Main()
    {
        // Create an instance of the Sub class
        Sub sub = new Sub();

        // Call both the Print method from the superclass and the Display method from the subclass
        sub.Print();    // Calling the Print() method from the Super class
        sub.Display();  // Calling the Display() method from the Sub class
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

## C# Solves It: Use Interfaces Instead
Example with Interfaces:
```csharp
interface ILeft {
    void Show();
}

interface IRight {
    void Show();
}

class Child : ILeft, IRight {
    // One method satisfies both interfaces
    public void Show() {
        Console.WriteLine("Child.Show");
    }
}
```
✅ No ambiguity because interfaces don't provide implementation, so the class Child defines the method explicitly.

# Abstract Classes
Abstract class is a restricted (Secure) class that can't be used to create object/instance. to access it, it must be inherited from another class

- An abstract class is declared using the abstract keyword.
- It may or may not contain abstract methods.
- If a class contains at least one abstract method, it must be declared as abstract class.

```csharp
using System;

abstract class Sunstar {
    // Abstract method
    public abstract void PrintInfo();

    // Non-abstract method (with default implementation)
    public void PrintMessage() {
        Console.WriteLine("This is a non-abstract method in the Sunstar class.");
    }
}

class Employee : Sunstar {
    // Implementing the abstract method
    public override void PrintInfo() {
        string name = "John";
        int age = 30;
        float salary = 2500.5F;
        Console.WriteLine($"{name}, {age}, {salary}");
    }
}

class Base {
    public static void Main(string[] args) {
        // Create an instance of Employee (which is a derived class of Sunstar)
        Sunstar s = new Employee();

        // Call the abstract method PrintInfo
        s.PrintInfo();

        // Call the non-abstract method PrintMessage
        s.PrintMessage();
    }
}
//Output
//John, 30, 2500.5
//This is a non-abstract method in the Sunstar class.

```
**Real life use:** Let's in a university application we make a class in which we declare fee report of all student, and we want only authorised person can see the fee detail Then we must have to make this class abstract. So that developer can't call directlly via make a object of this class.

## Abstract Method in C#:
- An abstract method is a method that is declared using the abstract keyword. It does not have a body (implementation). The body of the abstract method must be provided in the derived class.

- Abstract methods can only be declared within abstract classes.

- A derived class must provide an implementation for all abstract methods of its base class, unless the derived class is itself abstract.

```c#
using System;

abstract class Animal
{
    // Abstract method
    public abstract void Speak();
}

abstract class Mammal : Animal
{
    // Mammal does not implement Speak(), so it is abstract
}

class Dog : Mammal
{
    // Dog implements Speak()
    public override void Speak()
    {
        Console.WriteLine("Woof!");
    }
}

class Program
{
    static void Main()
    {
        // This will cause an error: cannot instantiate Mammal because it is abstract
        // Mammal mammal = new Mammal(); // Error

        Dog dog = new Dog();
        dog.Speak();  // Output: Woof!
    }
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

**Example and Clearification (class vs Interface)**
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
Encapsulation in C# is a concept used to hide the internal workings of a class and expose only the necessary parts through public methods or properties. It enhances data security, provides flexibility for future changes, and makes the system easier to maintain and scale.

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

### Why Do We Use Encapsulation?
**Data Protection (Security):** By restricting direct access to the internal state of an object, we ensure that the object’s state remains consistent. The object is protected from invalid or inconsistent values. For example, you could enforce validation in the setter method to ensure the data is valid before allowing changes.

**Example**
 A bank account should not accept negative values as the balance. we can validate if someone send negative value.

**Abstraction:** Encapsulation allows us to hide the internal workings of a class from the outside world. The user or other classes only need to know how to interact with the object (via its public methods), not how it is implemented internally.

**Control:** The Deposit and Withdraw methods control how money can be added or subtracted from the account, preventing invalid operations (like withdrawing more than the balance).

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

### In C#, abstraction is typically achieved using:

**1.Using Abstract Classes**
```csharp
// Abstract class
public abstract class Animal
{
    // Abstract method (no implementation)
    public abstract void MakeSound();

    // Regular method (with implementation)
    public void Sleep()
    {
        Console.WriteLine("This animal is sleeping.");
    }
}

// Derived class
public class Dog : Animal
{
    // Implementing the abstract method
    public override void MakeSound()
    {
        Console.WriteLine("Woof!");
    }
}

// Usage
public class Program
{
    public static void Main()
    {
        Dog dog = new Dog();
        dog.MakeSound();  // Output: Woof!
        dog.Sleep();      // Output: This animal is sleeping.
    }
}
```
**2.Using Interfaces**
```c#
// Interface
public interface IDriveable
{
    void Drive();
}

// Class implementing the interface
public class Car : IDriveable
{
    public void Drive()
    {
        Console.WriteLine("The car is driving.");
    }
}

// Usage
public class Program
{
    public static void Main()
    {
        Car car = new Car();
        car.Drive();  // Output: The car is driving.
    }
}
```
### Why Do We Use Abstraction?
Abstraction provides several important benefits in software development:

**Simplification of Complex Systems:**
By focusing on essential features and hiding the internal complexities, abstraction makes it easier to understand and use complex systems.
Example: When using a mobile app, you don’t need to know how the phone’s hardware works or how the app processes data. You just interact with the user interface.

**Code Reusability:**
With abstraction, you can reuse abstract classes or interfaces across multiple classes. The abstract class or interface can provide a common structure for all derived classes.

**Increased Security and Safety:**
By exposing only the necessary operations and hiding the rest, abstraction prevents the misuse of parts of a class or system that shouldn’t be accessed.
**Example:** If you have a class that handles bank transactions, abstracting certain methods can ensure that sensitive information or operations are not accessible to unauthorized users.

# Binding in C#
Binding in C# refers to the process of connecting a method call to the method body. `The binding determines which method or code gets executed when a function or method is called.`

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

## 1. Call by Value (Pass by Value)
In Call by Value, a copy of the actual parameter's value is passed to the method. Modifications made to the parameter inside the method do not affect the original argument.

**How it works:**
- A duplicate copy of the argument is passed to the method.
- Modifications made inside the method affect only the copy, not the original value.

**When is it used?**
- This is the default method of passing arguments in C# for value types (like int, double, char, structs, etc.).


```csharp
//Example of Call by Value in C#:
using System;

class Program
{
    static void ChangeValue(int num) //here num is a copy or value
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

**Example of Call by Reference:**
Using `ref` Keyword:
The `ref` keyword allows passing parameters by reference, and it must be explicitly declared in both the method signature and the call.
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

- **Call by Value** is the default for all value types (primitive types) in C#.

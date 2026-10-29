# OOPS in C#
OOP (Object-Oriented Programming) is a way of programming that helps us to make code simple, reusable, and secure.

**Benefits of OOP**
1. Simple and faster
2. Reusable
3. Easier to debug
4. Securely protects sensitive information through encapsulation

**What makes C# Object-Oriented**
C# is object-oriented because it supports the four main principles of OOP, Encapsulation, Inheritance Polymorphism, Abstraction etc.

## Classes
A class is a template for objects, and an object is an instance of a class.

**Predefined Class:** Console, String, System etc
**User Defined Class:** Dog, Test etc

**Benefits of Class:** A class is used to bind data as well as methods together as a single unit.

## Object
An object is an instance of a class that executes the class. Once the object is created, it takes up space in memory like other variables.

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

**There are four (Six) access modifiers:**

1. **public:** The access level of a public modifier is everywhere. It can be accessed from within the class, outside the class, within the assembly, and outside the assembly.
2. **protected:** A protected member can be accessed within its class and by derived classes, even if the derived classes are in another assembly.
3. **private:** The property or method can only be accessed within the class.
4. **internal:** The access level of an internal modifier is only within the assembly. It cannot be accessed from outside the assembly.
5. **protected internal**
6. **private protected**

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
A constructor is a special type of method whose name is the same as the class name. If no constructor is defined, the compiler generates a parameterless constructor

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

**3. Copy Constructor:** A Constructor that initializes a new object using values from another object of the same class..

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
 It is possible to make a constructor private in C# using private keyword before constructor name. `it helps us avoid to access constructor contains in other class.`

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

- Defined using the : symbol in C#.
- Provides code reusability.
- Method overriding is only possible through inheritance.

1. ### Single Inheritance
Single inheritance support only one supper and one Derived class
```csharp
using System;

public class Base
{
    public void Print()
    {
        Console.WriteLine("This is the Base class");
    }
}

public class Derived : Base
{
    public void Display()
    {
        Console.WriteLine("This is the Derived class");
    }
}

class Program
{
    static void Main()
    {
        // Create an instance of the Derived class
        Derived Derived = new Derived();

        // Call both the Print method from the Base class and the Display method from the Derived Class
        Derived.Print();    // Calling the Print() method from the Base class
        Derived.Display();  // Calling the Display() method from the Derived class
    }
}

```

2. ### Multilevel Inheritance
In Multilevel inheritance, we will have more than 2 classes and a parent class will be inherited by a child class then that child class will be inherited by the another child class.
```csharp
public class Base {
    public void Print() {
        Console.WriteLine("This is the Base class");
    }
}

public class Sub1 : Base {
    public void Display() {
        Console.WriteLine("This is Derived Class 1");
    }
}

public class Sub2 : Sub1 {
    public void Show() {
        Console.WriteLine("This is Derived Class 2");
    }
}
```

3. ### Hierarchical Inheritance
 In Hierarchical inheritance, a parent class is direct inherited by more than one child class.
```csharp
public class Base {
    public void Print() {
        Console.WriteLine("This is the Base class");
    }
}

public class Sub1 : Base {
    public void Display() {
        Console.WriteLine("This is Derived class 1");
    }
}

public class Sub2 : Base {
    public void Show() {
        Console.WriteLine("This is Derived class 2");
    }
}
```

4. ### Multiple Inheritance:
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
**Real life use:** Let's in a university application we make a class in which we declare fee report of all student, and we want only authorised person can see the fee detail Then we must have to make this class abstract. Abstract classes are used to define common behavior and force derived classes to implement required functionality.

## Abstract Method in C#:
- An abstract method declared using the abstract keyword. It does not have a body (implementation). The body of the abstract method must be provided in the derived class.

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

# Interface
Interface is similar to a class but it contains abstract methods (This means the interface does not provide the body of the methods)

- interfaces are used.To achieve abstraction in C#
- Interface methods are by default public and abstract.
- you cannot declare a method in an interface using the abstract keyword.
- Interfaces cannot contain instance fields (variables).
- If a class implements an interface, it must provide implementations for all the methods defined in that interface.
- Do not contain Instance Constructors

### **Interfaces are also used for:**

- Multiple inheritance
- Loose coupling
- Dependency Injection
- Testability
- Polymorphism

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

### Why and when we use Interface?
An interface defines a contract that specifies which methods, properties, or other members a class must implement.

1. **Loose coupling: -** A class depends on an abstraction rather than a specific implementation.

2. **Maintainability and flexibility:** We can replace one implementation with another without changing the consuming code, provided the contract remains compatible.

3. **Multiple interface implementation-**  A class can implement multiple interfaces, which C# allows even though it doesn't support multiple class inheritance (see example below).

**Example and Clarification (class vs Interface)**
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
public interface IA_IService
{
    Task<string> CompleteAsync(string prompt);
}

public class GroqService : IA_IService
{
    public async Task<string> CompleteAsync(string prompt)
    {
        // Call the Groq API here.
    }
}

public class OpenAIService : IA_IService
{
    public async Task<string> CompleteAsync(string prompt)
    {
        // Call the OpenAI API here.
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
    }
}
```

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

# ITERABLE or ITERATOR in C#:
An iterable is any object that can be looped through using a foreach loop.

- Iterators are used in the .NET collection framework to retrieve elements one by one.
```csharp
foreach (var item in customDataStructure) {
    // do stuff
}
```
`In C#, the foreach loop is used to iterate over collections that implement the IEnumerable interface, which makes them` iterable.

# Encapsulation
Encapsulation is a concept used to hide the internal workings of a class and expose only the necessary parts through public methods or properties. It enhances data security and makes the system easier to maintain and scale.

Encapsulation allows access to private variables indirectly by providing controlled access through public methods (also called getters and setters) or properties. 
`This means that the private variables themselves cannot be accessed directly from outside the class, but their values can be accessed or modified using public methods or properties.`

- Declare the class variable as private.
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

## Why Do We Use Encapsulation?
**Data Protection (Security):** By restricting direct access to the internal state of an object, we ensure that the object’s state remains consistent. The object is protected from invalid or inconsistent values.

### Encapsulation Clarify (Example)
#### Let's imagine encapsulation doesn't exist

You create a Bank Account:

```c#
class BankAccount
{
    public decimal Balance;
}
```

**Anywhere in the code:**
```c#
BankAccount account = new BankAccount();

account.Balance = 1000;
account.Balance = -5000;
account.Balance = 999999999999;
```

Nobody can stop this.

### Real World Thinking

**A bank should allow:**
- Deposit money
- Withdraw money
- Check balance

**A bank should NOT allow:**

- Set balance to -5000 Directly
- Set balance to 1 crore Directly

So we hide balance:

```c#
class BankAccount
{
    private decimal balance;

    public void Deposit(decimal amount)
    {
        if(amount > 0)
            balance += amount;
    }

    public decimal GetBalance()
    {
        return balance;
    }
}
```
Now:

```c#
BankAccount account = new BankAccount();
account.balance = -5000;
// ❌ Impossible
```

### The actual problem Encapsulation solves

- **Without encapsulation:** Any code can change your object

- **With encapsulation:** Object controls its own data

**Example**
 A bank account should not accept negative values as the balance. we can validate if someone send negative value.

**Abstraction:** Encapsulation allows us to hide the internal workings of a class from the outside world. The user or other classes only need to know how to interact with the object (via its public methods), not how it is implemented internally.

**Control:** The Deposit and Withdraw methods control how money can be added or subtracted from the account, preventing invalid operations (like withdrawing more than the balance).

# Polymorphism
Polymorphism is a concept in which a class containg more than one method with the same name but behaviour of each method is different. or `Polymorphism allows the same interface or method call to behave differently depending on the object.`

### **Compile-time Polymorphism** (Method Overloading)(Static Polymorphism)
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

### **Run-time Polymorphism** (Method Overriding)(Dynamic Dispatch)(Dynamic Polymorphism)
A polymorphism which is exists at run time is called **`run time`** or **`late binding`** or **`dynamic polymorphism`**.

- **Method Overriding-** Whenever we writing method in Base and Derived classes in such a way thats method name and parameter must be same called method overriding.

`Having the same method name and parameters is not enough. The base method must be marked virtual, abstract, or already override, and the derived method must use override.`

```csharp
public class Base {
    public virtual void Print() {
        Console.WriteLine("Base class");
    }
}

public class Derived : Base {
    public override void Print() {
        Console.WriteLine("Derived class");
    }
}

class MainClass {
    public static void Main(string[] args) {
        Base obj = new Derived();
        obj.Print();
    }
}
```
**Declaring an Object Base class Type and Creating an Instance of Derived Class**
```c#
Base obj = new Derived();
obj.Print(); // Output: "Derived class"

//if you make Object type as Base class you can call Base or Derived class both
```

**Declaring an Object Base Class Type and Creating an Instance of Base Class**
```c#
Base obj = new Base();
obj.Print(); // Output: "Base class"

//if you make Object type as Base class you can call Base or Derived class both
```

**Declaring an Object Derived Class Type and Creating an Instance of Derived Class**
```c#
Derived obj = new Derived();
obj.Print(); // Output: "Derived class"

//if you make Object type as Derived class you can only call Derived class method
```

**Declaring an Object Derived Class Type and Creating an Instance of Base Class**
```C#
Derived obj = new Base();
obj.Print(); 
//error CS0266: Cannot implicitly convert type 'Base' to 'Derived'. An explicit conversion exists (are you missing a cast?)

//if you make Object type as Derived class you can only call Derived class method
```
**Call Base Class using Derived class**
You can explicitly call the method of the Base Class from the Derived Class using the `base` keyword.
```csharp
public class Base {
    public virtual void Print() {
        Console.WriteLine("Base class");
    }
}

public class Derived : Base
{
    public override void Print()
    {
        Console.WriteLine("Derived class");
        base.Print(); // Call the Base class method
    }
}

class MainClass {
    public static void Main(string[] args) {
        Base obj = new Derived();
        obj.Print();
    }
}

//output
//Derived class
//Base class
```

**Call Base class with object and instance of Derived Class**
```c#
public class Base {
    public virtual void Print() {
        Console.WriteLine("Base class");
    }
}

public class Derived : Base {
    public override void Print() {
        Console.WriteLine("Derived class");
        // base.Print(); // This calls the Base class method inside Derived.
    }
}

class MainClass {
    public static void Main(string[] args) {
        Derived obj = new Derived();  // Instantiate Derived class

        // Call the Base class Print method directly
        ((Base)obj).Print();  // Cast to Base and call Print

        // Alternatively, the following would call the Derived's overridden method:
        // obj.Print(); 
    }
}
//Output
//Base class
```

# Abstraction
Abstraction is an OOP principle that hides implementation details and exposes only the essential functionality to the user.

`Abstraction can be achieved with either abstract classes or interfaces`

### Benefits:

- Hides unnecessary implementation details. (`The user knows what an object does, without needing to know all the details of how it works internally.`)

- Reduces complexity (`You interact with a simple method instead of dealing with many internal steps`).

- Makes code easier to maintain and extend (`you can change the internal implementation without changing the code that uses the abstraction,as long as the public contract remains the same`).

### In C#, abstraction is typically achieved using:

### 1.Using Abstract Classes
```csharp
// Abstract class
public abstract class Animal
{
    public abstract void MakeSound();

    public void Sleep()
    {
        Console.WriteLine("This animal is sleeping.");
    }
}

// Derived class
public class Dog : Animal
{
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

### 2.Using Interfaces
```c#
// Interface
public interface IDriveable
{
    void Drive();
}

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

** 1. Simplification of Complex Systems:**
Hides internal complexity and shows only the necessary functionality.

**Example:** An IPaymentService interface with a ProcessPayment() method. The calling code does not need to know how the payment is processed internally.

** 2. Code Reusability:**
Abstract classes and interfaces define a common contract that multiple classes can implement or extend.

**Example:** Different payment providers, such as StripePaymentService and PayPalPaymentService, can implement the same IPaymentService interface while providing their own payment-processing logic.

** 3.Increased Security and Safety:**
Abstraction exposes only the required operations, while encapsulation and access modifiers help control access to internal data and implementation details.

**Example:** A banking service exposes a TransferMoney() method instead of allowing callers to directly modify account balances. The service can validate the transaction before updating the balances.

### What Happens If Abstraction Doesn't Exist?

**1. Too Much Complexity**
- Every user of your class must understand all internal details.

**2. Duplicate Code**
- No common contract.
- You'll repeat logic everywhere.

**3. Tight Coupling**

Code becomes:
```c#
CreditCardPayment payment = new CreditCardPayment();
```
instead of:
```c#
IPayment payment;
```
Changing implementations becomes difficult.

# Binding in C#
Binding in C# refers to the process of connecting a method call to the method body. `The binding determines which method or code gets executed when a function or method is called.`

**In C#, there are two types of binding:**

### 1. Static Binding (Early Binding)
Static binding occurs when the method call is resolved at compile time by the compiler. This is also known as early binding.

**Characteristics:**
- Occurs at compile time.
- Used for method overloading and static methods.

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
- Call by Reference is not automatically used for reference types. Objects and arrays are still passed by value unless ref, out, or in is used.
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

- In C#, all parameters are passed by value by default, including object references. To pass a variable itself by reference, use the ref, out, or in keyword..

- To use Call by Reference, C# requires explicit use of the ref or out keywords.

- **Call by Value** is the default for all value types (primitive types) in C#.

```c#
class Person
{
    public string Name;
}

static void Change(Person p)
{
    p = new Person();
    p.Name = "John";
}

static void Main()
{
    Person p = new Person();
    p.Name = "Mike";

    Change(p);

    Console.WriteLine(p.Name);
}

// Output: Mike
```

# Check this
### Abstract Class vs Interface in C#

| Point | Abstract Class | Interface |
|---|---|---|
| 1. Keyword | `abstract class` | `interface` |
| 2. Methods | Can contain abstract and non-abstract methods | Can contain method declarations and default implementations |
| 3. Implementation | Can contain implemented methods | Can contain default method implementations (C# 8+) |
| 4. Fields | Can contain instance fields | Cannot contain instance fields |
| 5. Constructors | Can have constructors | Cannot have instance constructors |
| 6. Object creation | Cannot be instantiated directly | Cannot be instantiated directly |
| 7. Inheritance | A class can inherit only one class | A class can implement multiple interfaces |
| 8. Access modifiers | Supports `public`, `protected`, `private`, etc. | Ordinary interface members are public by default; other modifiers are supported for specific members |
| 9. Properties | Can have properties with backing fields | Can declare properties but cannot store per-instance state |
| 10. State | Can store object state in fields | Cannot store per-instance state |
| 11. Static members | Supports static members | Supports certain static members, including static abstract members in modern C# |
| 12. Purpose | Shares common code and state | Defines a contract or capability |
| 13. Inheritance syntax | `class Dog : Animal` | `class Dog : IFlyable` |
| 14. Multiple inheritance | Cannot inherit multiple classes | Can implement multiple interfaces |
| 15. Best use | Closely related classes that share functionality | Related or unrelated classes that share a common capability |

### Key Difference

- **Abstract class:** Used to share common implementation and state.
- **Interface:** Used to define a contract that classes agree to implement.

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
    }

    // Copy constructor
    public A(A refObj) {
        a = refObj.a;
        b = refObj.b;
        Console.WriteLine($"{a} {b}");
    }
}

class B {
    public static void Main(string[] args) {
        A r = new A(); // Create object
        A r2 = new A(r); // Pass object as reference
    }
}
```

**Constructor Overloading:**
When we have more than one constructor in our class, it's called constructor overloading.

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

4. **Multiple Inheritance:** C# does not support multiple inheritance directly. However, it supports multiple inheritance through interfaces.

## Abstract Classes
An abstract class is a restricted class that can't be instantiated directly. It must be inherited by another class.

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

## Interface
An interface in C# is similar to a class but only contains abstract methods. `interfaces are used.To achieve abstraction in C#`

- Interface methods are implicitly public and abstract.
- Interface variables are public, static, and readonly by default.

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

## Static Methods and Properties
Static methods and properties are declared with the static keyword.
`Static methods can be called directly without creating an instance of the class.`

```csharp
class Adder {
    public static int Add(int a, int b) {
        return a + b;
    }

    public static int Add(int a, int b, int c) {
        return a + b + c;
    }
}

class Test {
    public static void Main(string[] args) {
        Console.WriteLine(Adder.Add(11, 11));
        Console.WriteLine(Adder.Add(11, 11, 11));
    }
}
```

## Encapsulation
Encapsulation is the mechanism that binds the data and methods together in a single unit. `It also restricts direct access to the object's data.`

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
## Polymorphism
Polymorphism allows methods to have different behaviors based on their arguments.

**Compile-time Polymorphism (Method Overloading)**
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

**Run-time Polymorphism (Method Overriding)**
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

## Abstraction
Abstraction is the process of hiding certain details and showing only the essential information.

```csharp
abstract class Animal {
    public abstract void AnimalSound();
    public void Sleep() {
        Console.WriteLine("Zzz");
    }
}

class Pig : Animal {
    public override void AnimalSound() {
        Console.WriteLine("The pig says: wee wee");
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
# <p style="text-align: center;">OOPS IN PHP</p>

Oops is a way of programming that help us to make code simple, reusable, and Secure.


**Benefits of OOP**
1. Simple and faster
2. Reusable
3. Easier to debug
4. Securely protects sensitive information through encapsulation


**What are Classes and Object?**

Class is a template for objects, and an object is an instance of a class.

- A class is defined by using the class keyword, followed by curly braces.
- Objects of a class are created using the new keyword.

```php
<?php
// Creating Class
class Fruit {
  // Properties
  public $name;
  public $color;

  // Methods
  function set_name($name) {
    $this->name = $name;
  }
  function get_name() {
    return $this->name;
  }
}

// Creating Object
$apple = new Fruit();
$banana = new Fruit();
$apple->set_name('Apple');
$banana->set_name('Banana');

echo $apple->get_name();
echo "<br>";
echo $banana->get_name();
?>

/* Output
Apple
Banana */
```

**Benifits of class**

A class is used to bind data as well as methods together as a single unit.


## $this Keyword
The $this keyword refers to the current object, and is only available inside methods.

## instanceof
You can use the instanceof keyword to check if an object belongs to a specific class.

```php
<?php
class Fruit {
  // Properties
  public $name;
  public $color;

  // Methods
  function set_name($name) {
    $this->name = $name;
  }
  function get_name() {
    return $this->name;
  }
}

$apple = new Fruit();
var_dump($apple instanceof Fruit);
?>

/* Output
bool(true) */
```

## Access Modifiers
Properties and methods can have access modifiers which control where they can be accessed.

**There are three access modifiers:**
1. **public -** the property or method can be accessed from everywhere. This is default
2. **protected -** the property or method can be accessed within the class and by classes derived from that class
3. **private -** the property or method can ONLY be accessed within the class

## Class Constants
- Constants cannot be changed once it is declared.
- A class constant is declared inside a class with the **const** keyword.
- We can access a constant from outside the class by using the class name followed by the scope `resolution operator (::)` followed by the constant name, like here:
```php
<?php
class Goodbye {
  const LEAVING_MESSAGE = "Thank you for visiting W3Schools.com!";
}

echo Goodbye::LEAVING_MESSAGE;
?>
```

## COSTRUCTOR AND DESTRUCTOR

**Define constructor?**

If you create a __construct() function, PHP will automatically call this function when you create an object from a class.

- Constructor is a magic method in php

```Notes
>>> PHP magic methods are special methods that are called automatically when certain conditions are met. 

>>> Every magic method starts with a double underscore ( __ ).
```

- construct function starts with two underscores (__)
- https://www.w3schools.com/php/php_oop_constructor.asp

```php
<?php
class Fruit {
  public $name;
  public $color;

  function __construct($name) {
    $this->name = $name; 
  }
  function get_name() {
    return $this->name;
  }
}
$apple = new Fruit("Apple");
echo $apple->get_name();
?>
```

## Define Destructor?
If you create a __destruct() function, PHP will automatically call this function at the end of the script.

- Destructor is a magic method in php
- Destruct function starts with two underscores (__)
- https://www.w3schools.com/php/php_oop_destructor.asp

```php
<?php
class Fruit {
  public $name;
  public $color;

  function __construct($name) {
    $this->name = $name;
  }
  function __destruct() {
    echo "The fruit is {$this->name}.";
  }
}

$apple = new Fruit("Apple");
$?>
```

## INHERITANCE
When we construct a new class from existing class such a way that the new class access all the features and properties from existing class called inheritance.

- Defined using the extends keyword.
- Protected properties or methods can be accessed within the class and by classes derived from that class.

1. **Single Inheritance**

PHP supports Single inheritance. Single inheritance is a concept in PHP in which only one Parent class can be inherited by a single child class.

```php
<?php  
class a  
    {  
        function fun1()  
        {  
            echo "This is function A";  
        }  
    }  
    // Class B extends A
    class b extends a  
    {  
        function fun2()  
        {  
            echo "This is fucion B";  
        }  
    }  
    $obj= new b();  
    $obj->fun1();  
?>

// Output "This is function A"
```

2. **Multilevel Inheritance**

PHP supports Multilevel Inheritance. In this type of inheritance, we will have more than 2 classes. In this type of inheritance, a parent class will be inherited by a child class then that child class will be inherited by the another child class.

```php
<?php  
class a  
    {  
        function fun1()  
        {  
            echo "This is function A";  
        }  
    }  
    // Class B extends A
    class b extends a  
    {  
        function fun2()  
        {  
            echo "This is fucion B";  
        }  
    } 
    // Class C extends B
    class c extends b  
    {  
        function fun2()  
        {  
            echo "This is fucion C";  
        }  
    } 
    $obj= new c();  
    $obj->fun1();  
?>

// Output "This is fuction A"
```


3. **Hierarchical Inheritance**

PHP supports Hierarchical Inheritance. In this type of inheritance, a parent class is direct inherited by more than one child class.

```php
<?php  
class a  
    {  
        function fun1()  
        {  
            echo "This is function A";  
        }  
    }  
    // Class B extends A
    class b extends a  
    {  
        function fun2()  
        {  
            echo "This is fucion B";  
        }  
    } 
    // Class C extends A
    class c extends a 
    {  
        function fun2()  
        {  
            echo "This is fucion C";  
        }  
    } 
    $objC= new c();
    $objB= new c();  
    $objB->fun1();
    $objC->fun1();  
?>

/* Output 
"This is fuction A"
"This is fuction A" */
```

4. **Multiple Inheritance**

PHP doesn't supports Multiple Inheritance.Multiple Inheritance is a feature of an object-oriented concept, where a class can inherit properties of more than one parent class

**Final Keyword**
The final keyword can be used to prevent class inheritance or to prevent method overriding.

- We can't override final class and cannot override a final method.

```php
<?php
final class MyClass {
  public $name = "John";
}

// This code will throw an error
class AnotherClass extends MyClass{};
?>
```
- https://www.w3schools.com/php/php_oop_inheritance.asp


## ABSTRACT CLASSES
Abstract class is a restricted (Secure) class that can't be used to create object/instance. to access it, it must be inherited from another class

- abstract class declare using **abstract** Keyword
- A abstract class have must atlest one abstract method (Without Implemented).

```php
<?php
abstract class ParentClass {
  public $name;
  public/abstract function calculate($a, $b){
    //only declare can't implement
  }
}

class ChildClass extends ParentClass {
  public function Calculate($c, $d) {
    echo $c + $d;
  }
}

$test = new ChildClass();
$test-> Calculate(10, 20);
?>
```

**Abstract Method**
A abstract method is declare using abstract keyword but it don't require implementation.

**Real life use**
Let's in a university application we make a class in which we declare fee report of all student, and we want only authorised person can see the fee detail Then we must have to make this class abstract. So that developer can't call directlly via make a object of this class.

## INTERFACES
Interface is just like a class which contains only abstact method 

- Interface class declare using **Interface** keyword.
- To achieve interface in PHP use **implements** keyword.
- Interface is works like a abstract class so we can't implement as well as create object of Interface.
- we can't use access modifier in method.
- All abstract method declare but not implemented.

```php
<?php
interface Cat {
  public function CatSound();
}

interface Dog {
  public function DogSound();
}

class Animal implements Cat, Dog {
  public function CatSound() {
    echo "Meow";
  }
  
  public function DogSound() {
    echo "Bho Bho";
  }
}

$animal = new Animal();
$animal->CatSound();
$animal->DogSound();

/* Output 
meow
Bho Bho */
?>
```

**Why we use Interface?**

In php we can't inherit many parent classes in a single class, this problem is solved by interface.

**Interface vs astract class**
1. Interfaces cannot have properties, `while abstract classes can`
2. All interface methods must be public, `while abstract class methods is public or protected`
3. All methods in an interface are abstract, so they cannot be implemented in code and the abstract keyword is not necessary `but in abstract class minimum one abstract method is required`


## TRAITS
Traits are used to declare methods that can be used in multiple classes. And the methods can have any access modifier (public, private, or protected).

- Traits are declared with the **trait** keyword.
- To use a trait in a class, use the **use** keyword:

```php
<?php
trait message1 {
  public function msg1() {
    echo "OOP is fun! ";
  }
}

trait message2 {
  public function msg2() {
    echo "OOP reduces code duplication!";
  }
}

class Welcome {
  use message1;
}
class Welcome2 {
  use message1, message2;
}

$obj = new Welcome();
$obj->msg1();
echo "<br>";

$obj2 = new Welcome2();
$obj2->msg1();
$obj2->msg2();

/* Output
OOP is fun!
"OOP is fun!" "OOP reduces code duplication!"
?>
```

**Why Use Trait?**

PHP only support single inheritance: a child class can inherit only from one single parent. so using OOP traits we can solve this problem.


## STATIC METHOD AND PROPERTY
- Declared with the **static** keyword:

- Static methods can be called directly - without creating an instance of the class first.

ClassName::staticMethod();  **Static Property is same as static method**

```php
<?php
class pi {
  public static $value = 3.14159;
}

// Get static property
echo pi::$value;
?>
```

- A static method can be accessed from a method in the same class using the `self keyword and double colon (::)`

```php
<?php
class pi {
  public static $value=3.14159;
  public function staticValue() {
    return self::$value;
  }
}

// Get static property
$pi = new pi();
echo $pi->staticValue();
?>
```

- To call a static method from a child class, use the `parent keyword` inside the child class. Here, the static method can be public or protected.

```php
<?php
class pi {
  public static $value=3.14159;
}

class x extends pi {
  public function xStatic() {
    return parent::$value;
  }
}

// Get value of static property directly via child class
echo x::$value;

// or get value of static property via xStatic() method
$x = new x();
echo $x->xStatic();
?>
```

## NAMESPACE
Namespace allow the same name to be used for more than one class and methods

- Namespaces are declared at the beginning of a file using the **namespace** keyword:

```php
<?php
namespace Html;
?>
```

- This is done with the use keyword:

**Namespace Alias**
It can be useful to give a namespace or class an alias to make it easier to write

```php
<?php
use Html as H;
$table = new H\Table();
$
?>
```

**class Alias**
```php
<?php
use Html\Table as T;
$table = new T();
$
?>
```

## ITERABLES
An iterable is any value which can be looped through with a foreach() loop

- The iterable keyword can be used as a data type of a function argument or as the return type of a function:

```php
<?php
function printIterable(iterable $myIterable) {
  foreach($myIterable as $item) {
    echo $item;
  }
}

$arr = ["a", "b", "c"];
printIterable($arr);
?>
```

**Creating Iterables**

All arrays are iterables, so any array can be used as an argument of a function that requires an iterable

1. **current()** - Returns the element that the pointer is currently pointing to. It can be any data type
2. **key()** - Returns the key associated with the current element in the list. It can only be an integer, float, boolean or string
3. **next()** - Moves the pointer to the next element in the list
4. **rewind()** Moves the pointer to the first element in the list
5. **valid()** - If the internal pointer is not pointing to any element (for example, if next() was called at the end of the list), this should return false. It returns true in any other case

```php
<!DOCTYPE html>
<html>
<body>

<?php
// Create an Iterator
class MyIterator implements Iterator {
  private $items = [];
  private $pointer = 0;

  public function __construct($items) {
    // array_values() makes sure that the keys are numbers
    $this->items = array_values($items);
  }

  public function current() {
    return $this->items[$this->pointer];
  }

  public function key() {
    return $this->pointer;
  }

  public function next() {
    $this->pointer++;
  }

  public function rewind() {
    $this->pointer = 0;
  }

  public function valid() {
    // count() indicates how many items are in the list
    return $this->pointer < count($this->items);
  }
}

// A function that uses iterables
function printIterable(iterable $myIterable) {
  foreach($myIterable as $item) {
    echo $item;
  }
}

// Use the iterator as an iterable
$iterator = new MyIterator(["a", "b", "c"]);
printIterable($iterator);
?>

</body>
</html>
```

-  https://www.w3schools.com/php/php_iterables.asp


## ENCAPTULATION
Encaptulation is a mechanism through which we can wrapping the data member and member method of class in a single unit called encaptulation. `(With the help of encaptulation we can access the private variable outside the class.)`

- Declare the class varieble as privet.
- Declare the class method as public

```php
<?php

class Welcome {
  private $value;
  
  public function SetValue($value){
  $this->value=$value;
  }
  
  public function GetValue(){
  return $this->value;
  }
  
}
$obj = new Welcome();
// Update $value
$obj->SetValue(100);
// Access $value
$MyValue=$obj->GetValue();
// Print MyValue
echo $MyValue;
?>
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

1. **Compile time polymorphism**, which is also known as `function overloading`
2. **Run time polymorphism**, which is also called `function overriding`

**Method Overloding**
Whenever a class contain more than one method with same name different type of parameters called method overloading.

`PHP method overloading also triggers magic methods __call() or __callStatic() when call a undefined method in the class.`

**__call()** – triggered while invoking overloaded methods in the object context.
**__callStatic()** – triggered while invoking overloaded methods in static context.

**Example** `__call & __Static both`

```php
<?php
class GFG {
	
	
	public function __call($name, $arguments) {
		
		echo "Calling object method '$name' "
			. implode(', ', $arguments). "\n";
	}

	
	public static function __callStatic($name, $arguments) {
		
		echo "Calling static method '$name' "
			. implode(', ', $arguments). "\n";
	}
}

// Create new object
$obj = new GFG;

$obj->runTest('in object context');

GFG::runTest('in static context');

?>

/* Output
Calling object method 'runTest' in object context
Calling static method 'runTest' in static context */
```

**Example** `__call`
```php
<?php
class  Shape{

public function ___call($name, $arguments){

  if ($name == 'area'){
    $args_count = count($arguments);
    switch ($args_count){
      case 0:
      return 0;
      break;

      case 1:
      echo "you are entered only one argument";
      // return something;
      break;

      case 2:
      echo "you are entered two argument";
      // return something;
    }
  }
}
}
$obj = new Shape();
$obj->area(5);
$obj->area(5,4);
?>
```

**Example** `__Static`
```php
<?php
class  Shape{

public static function ___callStatic($name, $arguments){
  
  if ($name == 'area'){
    $args_count = count($arguments);
    switch ($args_count){
      case 0:
      return 0;
      break;

      case 1:
      echo "you are entered only one argument";
      // return something;
      break;

      case 2:
      echo "you are entered two argument";
      // return something;
    }
  }
}
}
Shape::area(5);
Shape::area(5, 4);
?>
```

**Property Overloading**
https://www.geeksforgeeks.org/overloading-in-php/
https://youtu.be/zko9wqauy64?si=tV5bg16DmwDKhd8g

**Method Overriding**
Whenever we writting method in super and sub classes in such a way thats method name and parameter must be same called method overiding.

- `method overriding only possible through inheritance.`

```php
<?php 
class base{
	
	public $name = "Parent Class";

	public function calc($a, $b){
		return $a * $b;
	}
		
}

class derived extends base{
	
	public $name = "Child Class";

	public function calc($a, $b){
		return $a + $b;
	}

}

$test = new base();
echo $test->calc(5, 10);
// print base class function and property
$test2 = new derived();
echo $test2->calc(5, 10);
// it gives first periority to derived class, if derived class not found then its call to the base class.
?>
```

## ABSTRACTION
In PHP, Data Abstraction is the property by virtue of which only the essential details are displayed to the user. The trivial or the non-essential units are not displayed to the user. 

**Ex:** A car is viewed as a car rather than its individual components.

`In PHP, abstraction is achieved by interfaces and abstract classes. We can achieve 100% abstraction using interfaces.`

```php
<?php
abstract class Vehicle
{
abstract function start();
}

class Car extends Vehicle{
  public function start (){
    echo ("car starts with key");
  }
}

class Scooter extends Vehicle
{
  public function start (){
    echo ("scooter starts with kick");
  }
}

$car =new Car();
$car->start();

$Scooter =new Scooter();
$Scooter->start();

?> 
```
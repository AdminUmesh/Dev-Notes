# <p style="text-align: center;">OOPS IN PHP</p>

**What is oops?**
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

**Benifits of class**
A class is used to bind data as well as methods together as a single unit.

## $this Keyword
The $this keyword refers to the current object, and is only available inside methods.

## instanceof
You can use the instanceof keyword to check if an object belongs to a specific class <?php

https://www.w3schools.com/php/php_oop_classes_objects.asp

## Access Modifiers
Properties and methods can have access modifiers which control where they can be accessed.

**There are three access modifiers:**
1. **public -** the property or method can be accessed from everywhere. This is default
2. **protected -** the property or method can be accessed within the class and by classes derived from that class
3. **private -** the property or method can ONLY be accessed within the class

**Class Constants**
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

##Define Destructor?
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

**Method Overloding**
Whenever a class contain more than one method with same name different type of parameters called method overloading.

**Method Overriding**
Whenever we writting method in super and sub classes in such a way thats method name and parameter must be same called method overiding.

**Final Keyword**
The final keyword can be used to prevent class inheritance or to prevent method overriding.

- We can't override final class and cannot override a final method.

- https://www.w3schools.com/php/php_oop_inheritance.asp


## ABSTRACT CLASSES
Abstract class is a restricted (Secure) class that can't be used to create object. to access it, it must be inherited from another class

- abstract class declare using **abstract** Keyword
- A abstract class have must atlest one abstract method (Without Implemented).

```php
<?php
abstract class ParentClass {
  public $name;
  public/abstract function calculate($a, $b) { **abstract method not require implementation**
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


## INTERFACES
Interface is just like a class which contains only abstact method 

- Interface class declare using **Interface** keyword.
- To achieve interface in PHP use **implements** keyword.
- Interface is works like a abstract class so we can't implement as well as create object of Interface.
- we can't use access modifier in method.

```php
<?php
interface Animal {
  public function makeSound();
}

class Cat implements Animal {
  public function makeSound() {
    echo "Meow";
  }
}

$animal = new Cat();
$animal->makeSound();
?>
```

**Why we use Interface?**
In php we can't inherit many parent classes in a single class, this problem is solved by interface.

**Interface vs astract class**
1. Interfaces cannot have properties, while abstract classes can
2. All interface methods must be public, while abstract class methods is public or protected
3. All methods in an interface are abstract, so they cannot be implemented in code and the abstract keyword is not necessary


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
/$obj2->msg2();
?>
```

**Why Use Trait?**
PHP only supports single inheritance: a child class can inherit only from one single parent. so using OOP traits we can solve this problem.


## STATIC METHOD AND PROPERTY
- Declared with the **static** keyword:

```php
<?php
class ClassName {
  public static function staticMethod() {
    echo "Hello World!";
  }
}
?>
```
- Static methods can be called directly - without creating an instance of the class first.

ClassName::staticMethod();  **Static Property is same as static method**

- A static method can be accessed from a method in the same class using the self keyword and double colon (::)

- To call a static method from a child class, use the parent keyword inside the child class. Here, the static method can be public or protected.


## NAMESPACE
Namespace allow the same name to be used for more than one class

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

1. current() - Returns the element that the pointer is currently pointing to. It can be any data type
2. key() Returns the key associated with the current element in the list. It can only be an integer, float, boolean or string
3. next() Moves the pointer to the next element in the list
4. rewind() Moves the pointer to the first element in the list
5. valid() If the internal pointer is not pointing to any element (for example, if next() was called at the end of the list), this should return false. It returns true in any other case

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

## POLYMORPHISM

## ABSTRACTION
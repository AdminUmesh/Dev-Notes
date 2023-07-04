-----------------------------------------------------------------------------------------------------
|                                           OOPS IN PHP                                             |
-----------------------------------------------------------------------------------------------------

**What is oops?**
Oops is a way of programming that help us to make code simple, reusable, and Secure.

**Benefits of OOP**
1. Simple and faster
2. Reusable
3. Easier to debug
4. Securely protects sensitive information through encapsulation

**What are Classes and Object?**
Class is a template for objects, and an object is an instance of a class.

>>> A class is defined by using the class keyword, followed by curly braces.
>>> Objects of a class are created using the new keyword.

**Benifits of class**
A class is used to bind data as well as methods together as a single unit.

**$this Keyword**
The $this keyword refers to the current object, and is only available inside methods.

**instanceof**
You can use the instanceof keyword to check if an object belongs to a specific class
<?php

>>> https://www.w3schools.com/php/php_oop_classes_objects.asp

**Access Modifiers**
Properties and methods can have access modifiers which control where they can be accessed.

There are three access modifiers:
1. public - the property or method can be accessed from everywhere. This is default
2. protected - the property or method can be accessed within the class and by classes derived from that class
3. private - the property or method can ONLY be accessed within the class



-----------------------------------------------------------------------------------------------------
|                                        COSTRUCTOR AND DESTRUCTOR                                  |
-----------------------------------------------------------------------------------------------------

**Define constructor?**
If you create a __construct() function, PHP will automatically call this function when you create an object from a class.

>>> Constructor is a magic method in php
>>> construct function starts with two underscores (__)
>>> https://www.w3schools.com/php/php_oop_constructor.asp

**Define Destructor?**
If you create a __destruct() function, PHP will automatically call this function at the end of the script.

>>> Destructor is a magic method in php
>>> Destruct function starts with two underscores (__)
>>> https://www.w3schools.com/php/php_oop_destructor.asp



-----------------------------------------------------------------------------------------------------
|                                              INHERITANCE                                          |
-----------------------------------------------------------------------------------------------------
**What is inheritance?**
When we construct a new class from existing class such a way that the new class access all the features and properties from existing class called inheritance.

>>> Defined using the extends keyword.
>>> Protected properties or methods can be accessed within the class and by classes derived from that class.
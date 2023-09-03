# <p style="text-align: center;">Conditional Statement in Java</p>
We can use the conditions Statement to perform different actions for different decisions.

**Java supports the usual logical conditions from mathematics:**

- Less than: a < b
- Less than or equal to: a <= b
- Greater than: a > b
- Greater than or equal to: a >= b
- Equal to a == b
- Not Equal to: a != b

## Java has the following conditional statements:

**1. if -** It specify a block of code to be executed, if a specified condition is true.
```java
int A=40;
int B=30;

if(A < B){
    System.out.println("A is greater than B");
}
```

**2. else -** It specify a block of code to be executed, if the same condition is false.
```java
int A=40;
int B=30;

if(A < B){
    System.out.println("A is greater than B");
}else{
    System.out.println("B is greater than A");
}
```

**3. else if -** It specify a new condition to test, if the first condition is false.
```java
int A=40;
int B=30;
int C=20;

if(A > B){
    System.out.println("Can't say who one is greatest");
}else if(A > C){
    System.out.println("B is greatest");
}else{
    System.out.println("A is smallest");
}
```
**3. Nested if -** Nested means within. Nested if condition means if-within-if. Nested if condition comes under decision-making statement in Java. There could be infinite if conditions inside an if condition.

**Ex- Print greatest in 3 number using nested if statement**
```java
int a=40;
int b=30;
int c=20;

if(a > b){
    if(a > c){
        System.out.println("a is greatest");
    }else{
        System.out.println("c is greatest");
    }
}else{
    if(b > c){
        System.out.println("b is greatest");
    }else{
        System.out.println("c is greatest");
    }
}
```

**4. switch -** Switch is a multiple choice desicion making selection statement. It is used when we want to select only one case out of multiple case.

```java
int day = 4;
switch (day) {
  case 1:
    System.out.println("Monday");
    break;
  case 2:
    System.out.println("Tuesday");
    break;
  case 3:
    System.out.println("Wednesday");
    break;
  case 4:
    System.out.println("Thursday");
    break;
  case 5:
    System.out.println("Friday");
    break;
  case 6:
    System.out.println("Saturday");
    break;
  case 7:
    System.out.println("Sunday");
    break;
}
// Outputs "Thursday" (day 4)
```


# <p style="text-align: center;">Loops in Java</p>
Loops are known as iteration statement, That is because loop are used to repeat a statement a certain number of times while a condition is fulfiled.

**1. while -** A while loop is a control flow statement that allows code to be executed repeatedly based on a given Boolean condition.

```java
class WhileLoop {
    public static void main (String[] args) {
      int i=0;
      while (i<=10)
      {
        System.out.println(i);
        i++;
      }
    }
}
// Print numbet 1 to 10
```

**2. do-while -** do while loop is similar to while loop with only difference that it checks for condition after executing the statements, and therefore is an example of Exit Control Loop. 
```java
class doWhile {
    public static void main (String[] args) {
      int i=0;
      do
      {
        System.out.println(i);
        i++;
      }while(i<=10);
    }
}
// Print numbet 0 to 10
```

**3. for -** For loop is used to execute a piece of code in certain number of times.
```java
class ForLoop {
    public static void main (String[] args) {
       for (int i=0;i<=10;i++)
       {
         System.out.println(i);
       }
    }
}
// Print numbet 0 to 10
```
**4. for-each -** Switch is a multiple choice desicion making selection statement. It is used when we want to select only one case out of multiple case.


**5. nested -** For-each is another array traversing technique like for loop, while loop, do-while loop.
- `There is also a "for-each" loop, which is used exclusively to loop through elements in an array:`
```java
class Easy
{
    public static void main(String[] args)

    {
        // array declaration
  
        int ar[] = { 10, 50, 60, 80, 90 };
  
        for (int element : ar)
  
            System.out.print(element + " ");
    }
}
// Output: 10 50 60 80 90 
```

There are different combinations of loop using for loop, while loop, do-while loop.
```java
class Nested {
    public static void main (String[] args) {
        for(int i = 0; i < 3; i++){
          for(int j = 0; j < 2; j++){
            System.out.println(i);
          }
          System.out.println();
        }
    }
}
```

**6. Infinite loop -** One of the most common mistakes while implementing any sort of looping is that it may not ever exit, that is the loop runs for infinite time. This happens when the condition fails for some reason.
```java
class InfiniteLoop {
    public static void main (String[] args) {
       while (true)
       {
         // statement
       }
       
    }
}
```

## Break and continue Statement

**Break**
Break statement used to exit the loop irrespective of whenever the condition is true or false.
```java
for (int i = 0; i < 10; i++) {
  if (i == 4) {
    break;
  }
  System.out.println(i);
}
// Output: 0,1,2,3
```

**Continue**
The continue statement is used to immideatly move to the next itteration of the loop.
```java
for (int i = 0; i < 10; i++) {
  if (i == 4) {
    continue;
  }
  System.out.println(i);
}
// Output: 0,1,2,3,5,6,7,8,9
```
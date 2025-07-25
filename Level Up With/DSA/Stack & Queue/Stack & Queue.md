# Stack
The stack is a linear data structure that is used to store the collection of objects. It is based on Last-In-First-Out (LIFO). Java collection framework provides many interfaces and classes to store the collection of objects. One of them is the Stack class that provides different operations such as push, pop, search, etc.

**Push & Pop**
 push and pop. The push operation inserts an element into the stack and pop operation removes an element from the top of the stack. Let's see how they work on stack.

**Empty Stack**
 Empty Stack: If the stack has no element is known as an empty stack. When the stack is empty the value of the top variable is -1.

**Java Stack class**
 In Java, Stack is a class that falls under the Collection framework that extends the Vector class. It also implements interfaces List, Collection, Iterable, Cloneable, Serializable. It represents the LIFO stack of objects. Before using the Stack class, we must import the java.util package.

**Stack Class Constructor**
The Stack class contains only the default constructor that creates an empty stack.

```
public Stack()
```  
**Creating a Stack**
If we want to create a stack, first, import the java.util package and create an object of the Stack class.
```
Stack stk = new Stack(); 
or
Stack<type> stk = new Stack<>(); 
```
## Methods of the Stack Class

**1. empty()-** Return true if stack is empty
**2. push()-** Add item in stack
**3. pop()-** Remove elements from stack 
**4. peek()-** check for which element in on top.
**5. search()-** It returns the object location from the top of the stack. If it returns -1, it means that the object is not on the stack.
**6. size()-** Return length of a stack.

**Example**
[Click here](https://www.javatpoint.com/java-stack)


# Queue
Queue is a type of data structure in the Java programming language that stores elements of the same kind. The components in a queue are stored in a FIFO (First In, First Out) behavior.

- The Queue is an interface in the Java that belongs to Java.util package. It also extends the Collection interface.

**There are two different classes which are used to implement the Queue interface. These classes are:**
1. LinkedList
2. PriorityQueue

**Characteristics of the Java Queue**
- Java Queue obeys the FIFO (First In, First Out) manner. It indicates that elements are entered in the queue at the end and eliminated from the front.

- The deque is also considered thread-safe.

**What is thread-safe**
...........

**Classes that implement the Queue Interface:**

**1. PriorityQueue:** PriorityQueue class which is implemented in the collection framework provides us a way to process the objects based on the priority
```java
// Creating empty priority queue
    Queue<Integer> pQueue = new PriorityQueue<Integer>();
```

**2. LinkedList:** LinkedList is a class which is implemented in the collection framework which inherently implements the linked list data structure. It is a linear data structure where the elements are not stored in contiguous locations and every element is a separate object with a data part and address part.
```java
// Creating empty LinkedList
    Queue<Integer> ll = new LinkedList<Integer>();
```

**3. PriorityBlockingQueue:** It is to be noted that both the implementations, the PriorityQueue and LinkedList are not thread-safe. PriorityBlockingQueue is one alternative implementation if thread-safe implementation is needed.
```java
// Creating empty priority
        // blocking queue
    Queue<Integer> pbq = new PriorityBlockingQueue<Integer>();
```
## Methods of Queue Interface
**1. isEmpty()-** This method is used to check if the queue is empty or not. It returns true if the queue is empty, else false.
**2. add()-** Add item in stack
**3. add(int index, element)-** This method is used to add an element at a particular index in the queue. 
**4. get(int index)-** This method returns elements at the specified index.
**5. set(int index, element)** This method replaces elements at a given index with the new element.
**6. size()-** Return length of a stack.
**7. indexOf(element)-** This method returns the first occurrence of the given element or -1 if the element is not present in the queue.
**8. remove()-** This method is used to remove the element from the front of the queue.
**9. remove(int index)-** This method removes an element from the specified index
**10. contains(element)-** This method is used to check if the queue contains the given element or not. It returns true if the queue contains the element.
**11. lastIndexOf(element)-** This method returns the last occurrence of the given element or -1 if the element is not present in the queue.
**12. equals(element)-**	This method is used to compare the equality of the given element with the elements of the queue.
**13. hashCode()-**	This method is used to return the hashcode value of the given queue.

## Iterating the Queue:-
**1. Traversing using the for loop.**

**2. Inbuilt iterator which can be used to iterate through the queue.**
```java
import java.util.*;
public class GFG {

	public static void main(String args[])
	{
		Queue<String> pq = new PriorityQueue<>();

		pq.add("Geeks");
		pq.add("For");
		pq.add("Geeks");

		Iterator iterator = pq.iterator();

        for (int i = 0; i < 5; i++){
            q.add(i);
        }

		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
	}
}
```

**Advantages of using the Queue interface in Java:**

**1. Order preservation:** The Queue interface provides a way to store and retrieve elements in a specific order, following the first-in, first-out (FIFO) principle.

**2. Flexibility:** The Queue interface is a subtype of the Collection interface, which means that it can be used with many different data structures and algorithms, depending on the requirements of the application.

**3. Thread–safety:** Some implementations of the Queue interface, such as the java.util.concurrent.ConcurrentLinkedQueue class, are thread-safe, which means that they can be accessed by multiple threads simultaneously without causing conflicts.

**4. Performance:** The Queue interface provides efficient implementations for adding, removing, and inspecting elements, making it a useful tool for managing collections of elements in performance-critical applications.

**Disadvantages of using the Queue interface in Java:**

**1. Limited functionality:** The Queue interface is designed specifically for managing collections of elements in a specific order, which means that it may not be suitable for more complex data structures or algorithms.

**2. Size restrictions:** Some implementations of the Queue interface, such as the ArrayDeque class, have a fixed size, which means that they cannot grow beyond a certain number of elements.

**3. Memory usage:** Depending on the implementation, the Queue interface may require more memory than other data structures, especially if it needs to store additional information about the order of the elements.

**4. Complexity:** The Queue interface can be difficult to use and understand for novice programmers, especially if they are not familiar with the principles of data structures and algorithms.

# LinkedList
Linked List is a part of the Collection framework present in java.util package. This class is an implementation of the LinkedList data structure which is a linear data structure where the elements are not stored in contiguous locations and every element is a separate object with a data part and address part. The elements are linked using pointers and addresses. Each element is known as a node. 

## Operations:-
**1. Declare an LinkedList of different Types**
```
LinkedList<Integer> list = new LinkedList<Integer>();
LinkedList<Boolean> list2 = new LinkedList<>();
```
**2. Add Element**
```
list.add(1);
list.addFirst(3);
list.addLast(4);
```
**3. Get Element**
```
list.get(0);
list.getFirst();
list.getLast();
```
**4. Add Element at a specific Index**
```
list.add(1,2);
```
**5. Set Element at a specific Index**
```
list.set(0,0);
```
**6. Delete Element from an Index**
```
list.remove(0);
list.removeFirst();
list.removeLast();
```
**7. Size of the List**
```
list.size()
```
**8. Loop/Iterate on the List**
```
 for(int i=0; i<list.size(); i++) {
    System.out.print(list.get(i) + " ");
    }
```
**9. Sort the List**
```
Collections.sort(list);
```

**hasNext()**
return true if there is a next element

**hasPrevious()**
return true if there is a previous element

**next()**
return the next element

**previous()**
return the previous element

## Example:-
```java
import java.util.LinkedList;
import java.util.Collections;

class LinkedLists {
   public static void main(String args[]) {
    //Declare an LinkedList of different Types
       LinkedList<Integer> list = new LinkedList<Integer>();
       LinkedList<Boolean> list3 = new LinkedList<>();
      
       //add elements
       list.add(1);
       list.add(3);
       list.add(4);
       list.add(5);
       System.out.println(list);

       //to get an element
       int element = list.get(0); // 0 is the index
       System.out.println(element);

       //add element in between
     list.add(1,2); // 1 is the index and 2 is the element to be added
       System.out.println(list);

       //set(update) element
       list.set(0,0);
       System.out.println(list);

       //delete elements
       list.remove(0); // 0 is the index
       System.out.println(list);

       //size of list
       int size = list.size();
       System.out.println(size);

       //Loops on lists
       for(int i=0; i<list.size(); i++) {
           System.out.print(list.get(i) + " ");
       }
       System.out.println();

       //Sorting the list
       list.add(0);
       Collections.sort(list);
       System.out.println(list);
   }
}
```

## Time Complexity
**1. Insert** O(1)
**2. Search** O(n)
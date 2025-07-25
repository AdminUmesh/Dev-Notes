# ArrayList
ArrayList is a part of collection framework and is present in java.util package. It provides us with dynamic arrays in Java. Though, it may be slower than standard arrays but can be helpful in programs where lots of manipulation in the array is needed.

## Operations:-
**1. Declare an ArrayList of different Types**
```
ArrayList<Integer> list = new ArrayList<Integer>();
ArrayList<Boolean> list3 = new ArrayList<>();
```
**2. Add Element**
```
list.add(1);
list.add(3);
list.add(4);
```
**3. Get Element**
```
list.get(0);
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
## Example:-
```java
import java.util.ArrayList;
import java.util.Collections;

class ArrayLists {
   public static void main(String args[]) {
    //Declare an ArrayList of different Types
       ArrayList<Integer> list = new ArrayList<Integer>();
       ArrayList<Boolean> list3 = new ArrayList<>();
      
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
**1. Insert** O(n)
**2. Search** O(1)
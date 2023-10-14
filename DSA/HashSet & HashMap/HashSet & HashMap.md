# HashSet
Java HashSet class implements the Set interface, backed by a hash table which is actually a HashMap instance. 

**Java HashSet Features**
1. Implements Set Interface.
2. The underlying data structure for HashSet is Hashtable.
3. As it implements the Set Interface, duplicate values are not allowed.
4. Objects that you insert in HashSet are not guaranteed to be inserted in the same order. Objects are inserted based on their hash code.
5. NULL elements are allowed in HashSet.

**Internal Working of a HashSet**
All the classes of the Set interface are internally backed up by Map. HashSet uses HashMap for storing its object internally. You must be wondering that to enter a value in HashMap we need a key-value pair, but in HashSet, we are passing only one value. 

**Creating an empty HashSet of string entities**
```java
HashSet<String> hs = new HashSet<String>();
```
## Methods in HashSet

**0. add()-** Adding Elements in HashSet.

**1. add(E e)-**	Used to add the specified element if it is not present, if it is present then return false.

**2. clear()-**	Used to remove all the elements from the set.

**3. contains(Object o)-**	Used to return true if an element is present in a set.

**4. remove(Object o)-** Used to remove the element if it is present in set.

**5. iterator()-**	 Used to return an iterator over the element in the set.

**6. isEmpty()-**	Used to check whether the set is empty or not. Returns true for empty and false for a non-empty condition for set.

**7. size()-**	Used to return the size of the set.


# HashMap
Java HashMap class implements the Map interface which allows us to store key and value pair, where keys should be unique. If you try to insert the duplicate key, it will replace the element of the corresponding key. It is easy to perform operations using the key index like updation, deletion, etc. HashMap class is found in the java.util package.

**Java HashMap Features**
1. Java HashMap contains values based on the key.
2. Java HashMap contains only unique keys.
3. Java HashMap may have one null key and multiple null values.
4. Java HashMap is non synchronized.
5. Java HashMap maintains no order.
6. The initial default capacity of Java HashMap class is 16 with a load factor of 0.75.

**Hierarchy of HashMap class**
As shown in the above figure, HashMap class extends AbstractMap class and implements Map interface.

**Creating an empty HashMap of string entities**
```java
 HashMap<Integer,String> map=new HashMap<Integer,String>();
```
## Methods in HashMap

**1. containsValue(Object value)-**	This method returns true if some value equal to the value exists within the map, else return false.

**2. containsKey(Object key)-**	This method returns true if some key equal to the key exists within the map, else return false.

**3. get(Object key)-**	This method returns the object that contains the value associated with the key.

**4. replace(K key, V oldValue, V newValue)-** It replaces the old value with the new value for a specified key.

**5.  replace(K key, V value)-** It replaces the specified value for a specified key.

**6. isEmpty()-**	This method returns true if the map is empty; returns false if it contains at least one key.

**7. size()-**	Used to return the size of the set.

**8. remove(Object key)-** It is used to delete an entry for the specified key.

**9. remove(Object key, Object value)-** It removes the specified values with the associated specified keys from the map.

**10. put(Object key, Object value)-** 	It is used to insert an entry in the map.

**11. putAll(Map map)-**	It is used to insert the specified map in the map.

**12. clear()-** It is used to remove all of the mappings from this map.
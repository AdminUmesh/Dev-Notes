# Multithreading in Java
Multithreading is a process of executing multiple threads at a same time without dependency of othet thread called multithreading.

A thread is a lightweight sub-process, the smallest unit of processing. Multiprocessing and multithreading, both are used to achieve multitasking.

- Java Multithreading is mostly used in games, animation, etc.

**What is Thread in java**
A thread is a pre-define class which is available in java.lang package. 
`Thread is a basic unit of CPU and it is well known for independent execution.`

## Way to create/Define a thread
**1. By Extending Thread class.**
Java provides a thread class to call threading that has various method calls in order to manage the behavior of threads by providing constructors and methods to perform operations on threads. Ways of creating threads Creating own class which is extending to parent Thread class Implementing the Runnable interface.

```java
class A extends Thread{
    // Thread is pre define class in java
    // Under Thread class run() is predefine method.
    public void run(){
        for(int i=0; i<5; i++){
            System.out.println("Run method working");
            //Thread.sleep(1000); method print a thread after a second
        }
    }
}
class B{
    public static void main(String [] args){
        A t=new A();
        t.start(); //Start method is used to start the run() method
        for(int i=0; i<5; i++){
            System.out.println("Main method working");
            //Thread.sleep(1000); method print a thread after a second
        }
    }
}

// Both run method and main method work together.
// Reference utube @Learn codding
```

**2. By Extending Runable interface**
```java
class A implements Runnable{
    // Runnable is pre define interface in java
    public void run(){
        for(int i=0; i<5; i++){
            System.out.println("Run method working");
        }
    }
}
class B{
    public static void main(String [] args){
        A r=new A();
        Thread t=new Thread(r);
        // start() method only present in Thread class so it must to make object of Thread class.
        t.start();
        for(int i=0; i<5; i++){
            System.out.println("Main method working");
        }
    }
}

// Both run method and main method work together.
// Reference utube @Learn codding
```

**What is thread scheduler im multithreading**
---------

## Methods in Multithreading 
**Reference- Learn Codding**
Sleep()
join()
suspend()
resume()
yield()
stop()
isAlive()
interupt()
setPriority()
getPriority()

**Synchronization in java multithred**
youtube @learn coding

**Advantages of Java Multithreading**
1) It doesn't block the user because threads are independent and you can perform multiple operations at the same time.

2) You can perform many operations together, so it saves time.

3) Threads are independent, so it doesn't affect other threads if an exception occurs in a single thread.

**Multitasking**
Multitasking is a process of executing multiple tasks simultaneously. We use multitasking to utilize the CPU. Multitasking can be achieved in two ways:

1. Process-based Multitasking (Multiprocessing)
2. Thread-based Multitasking (Multithreading)

**1) Process-based Multitasking (Multiprocessing)**
1. Each process has an address in memory. In other words, each process allocates a separate memory area.
2. A process is heavyweight.
3. Cost of communication between the process is high.
4. Switching from one process to another requires some time for saving and loading registers, memory maps, updating lists, etc.

**2) Thread-based Multitasking (Multithreading)**
1. Threads share the same address space.
2. A thread is lightweight.
3. Cost of communication between the thread is low.

# Java File Handling
[Click](https://www.w3schools.com/java/java_files.asp)
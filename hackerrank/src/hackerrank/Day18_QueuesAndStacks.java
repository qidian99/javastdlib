package hackerrank;


/*
 * Queues: First in first out
 * Dequeue the first element before others 
 * Enqueue
 * 
 * The remove() and poll() methods remove and return the head of the queue.
 * The element() and peek() methods return, but do not remove, the head of the queue.
 */
public class Day18_QueuesAndStacks {

}


/*



Java LinkedList class

Java LinkedList class hierarchy
Java LinkedList class uses doubly linked list to store the elements. It provides a linked-list data structure. It inherits the AbstractList class and implements List and Deque interfaces.

The important points about Java LinkedList are:

Java LinkedList class can contain duplicate elements.
Java LinkedList class maintains insertion order.
Java LinkedList class is non synchronized.
In Java LinkedList class, manipulation is fast because no shifting needs to be occurred.
Java LinkedList class can be used as list, stack or queue.

https://www.javatpoint.com/java-linkedlist


Stacks
A stack is a data structure that uses a principle called Last-In-First-Out (LIFO), meaning that the last object added to the stack must be the first object removed from it.
At minimum, any stack, s, should be able to perform the following three operations:
Peek: Return the object at the top of the stack (without removing it).
Push: Add an object passed as an argument to the top of the stack.
Pop: Remove the object at the top of the stack and return it.
The java.util package has a Stack class that implements these methods; check out the documentation (linked above) on the peek(), push(object), and pop() methods.
Queues
A queue is a data structure that uses a principle called First-In-First-Out (FIFO), meaning that the first object added to the queue must be the first object removed from it. You can analogize this to a checkout line at a store where the line only moves forward when the person at the head of it has been helped, and each person in the line is directly behind the person whose arrival immediately preceded theirs.
At minimum, any queue, q, should be able to perform the following two operations:
Enqueue: Add an object to the back of the line.
Dequeue: Remove the object at the head of the line and return it; the element that was previously second in line is now at the head of the line.
The java.util package has a Queue interface that can be implemented by a number of classes, including LinkedList. Much like abstract classes, interfaces cannot be instantiated so we must declare a variable of type Queue and initialize it to reference a new LinkedList object. Check out the documentation (linked above) on the add(object) (enqueue) and remove() (dequeue) methods. You'll learn more about interfaces tomorrow!
*/
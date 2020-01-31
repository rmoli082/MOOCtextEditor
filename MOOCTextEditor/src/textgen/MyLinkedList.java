package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		
		head.next = tail;
		tail.prev = head;
		
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if (element == null) {
			throw new NullPointerException("LinkedList cannot store null pointers");
		}
		
		// TODO: Implement this method
		LLNode<E> node = new LLNode<E>(element);
		
		node.prev = tail.prev;
		node.next = tail;
		node.prev.next = node;
		node.next.prev = node;
		size++;
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if (size == 0 || index <0 || index >= size){
			throw new IndexOutOfBoundsException("The list is empty or the index is out of bounds.");
		}
		
		LLNode<E> node = new LLNode<E>(null);
		node = head.next;
		
		for (int i = 1; i <= index; i++) {
			node = node.next;
		}
		
		return node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (index <0 || index > size ){
			throw new IndexOutOfBoundsException("The index is out of bounds.");
		}
		if (element == null) {
			throw new NullPointerException("Cannot store a null pointer");
		}
		
		LLNode<E> node = new LLNode<E>(element);
		LLNode<E> prevNode = new LLNode<E>(null);
		prevNode = head.next;
		
		for (int i = 1; i <= index; i++) {
			prevNode = prevNode.next;
		}
		node.next = prevNode;
		node.prev = prevNode.prev;
		prevNode.prev = node;
		node.prev.next = node;
		
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (size == 0 || index < 0 || index > size){
			throw new IndexOutOfBoundsException("The index is out of bounds.");
		}
		
		// TODO: Implement this method
		LLNode<E> node = new LLNode<E>(null);
		node = head.next;
		
		for (int i = 1; i <= index; i++) {
			node = node.next;
		}
		
		node.prev.next = node.next;
		node.next.prev = node.prev;
		
		size--;
		
		return node.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (size == 0 || index <0 || index > size){
			throw new IndexOutOfBoundsException("The index is out of bounds.");
		}
		if (element == null) {
			throw  new NullPointerException("Cannot add Null pointer.");
		}
		
		// TODO: Implement this method
		LLNode<E> node = new LLNode<E>(null);
		node = head.next;
		
		for (int i = 1; i <= index; i++) {
			node = node.next;
		}
		
		E dataReturn = node.data;
		node.data = element;
		
		return dataReturn;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}

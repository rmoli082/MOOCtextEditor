/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			list1.remove(11);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		try {
			list1.add(1, null);
			fail("Null Pointer Exception");
		}
		catch (NullPointerException e) {
		
		}
		System.out.println("AddEndTest: ");
		list1.add(45);
        assertEquals("AddEnd: check last element is correct ", (Integer)45, list1.get(list1.size()-1));
		assertEquals("AddEnd: check previous last is correct ", (Integer)42, list1.get(list1.size() - 2));
		assertEquals("AddEnd: check size variable has been updated ", 4, list1.size());
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Size: check the size of the list is correct ", 3, list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		System.out.println("AddAtIndex tests ");
        // TODO: implement this test
		try {
			list1.add(-1, 47);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			list1.add(1, null);
			fail("Null Pointer Exception");
		}
		catch (NullPointerException e) {
		
		}
		try {
			list1.add(11, 55);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		list1.add(1, 47);
		assertEquals("AddAtIndex: check new element is correct ",(Integer) 47, list1.get(1));
		assertEquals("AddAtIndex: check last element is correct ",(Integer) 21, list1.get(2));
		MyLinkedList<Integer> list2 = new MyLinkedList<Integer>();
		list2.add(0, 1);
		assertEquals("AddAtIndex: check element is correct ", (Integer) 1, list2.get(0));
	}
		
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try {
			list1.set(1, null);
			fail("Null Pointer Exception");
		}
		catch (NullPointerException e) {
		
		}
		try {
			list1.set(-1, 47);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			list1.set(11, 47);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
	    // TODO: implement this test
		int a = list1.set(1, 47);
		assertEquals("Set: check new element is correct ", (Integer) 47, list1.get(1));
		assertEquals("Set: check it returns correct ", (Integer) 21, (Integer) a);
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @date 2/8/2024
 * @author Case Riddle
 * @author Sam Nusstein
 * @author Jack Roberts
 **/

public class LinkedListTest {

	@Test
	public void testIsEmpty() {
		LinkedList<String> list = new LinkedList<>();
		assertTrue(list.isEmpty());

		list.addToFront("Element");
		assertFalse(list.isEmpty());
	}

	@Test
	public void testClear() {
		LinkedList<Integer> list = new LinkedList<>();
		list.addToFront(1);
		list.addToBack(2);

		assertFalse(list.isEmpty());

		list.clear();

		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
		
		LinkedList<Integer> list2 = new LinkedList<>();
	    list2.clear();
	    
	    assertTrue(list2.isEmpty());
	    assertEquals(0, list2.size());
	}

	@Test
	public void testSize() {
		LinkedList<Integer> list1 = new LinkedList<>();
		assertEquals(0, list1.size());

		list1.addToFront(1);
		list1.addToBack(2);

		assertEquals(2, list1.size());

		LinkedList<Double> list2 = new LinkedList<>();
		assertEquals(0, list2.size());

		list2.addToFront(1.12);
		list2.addToBack(2.28);

		assertEquals(2, list2.size());
	}

	@Test
	public void testAddToFront() {
		LinkedList<Integer> list = new LinkedList<>();
		list.addToFront(1);
		list.addToFront(2);
		list.addToFront(3);

		assertEquals("3;2;1", list.toString());
	}

	@Test
	public void testContains() {
		LinkedList<String> list = new LinkedList<>();
		list.addToFront("Case");
        list.addToFront("Chase");

        assertTrue(list.contains("Case"));
        assertTrue(list.contains("Chase"));
        
        assertEquals(2, list.size());
	}

	@Test
    public void testRemove() {
        LinkedList<String> list = new LinkedList<>();
        
        list.addToFront("Case");
        list.addToFront("Chase");

        assertTrue(list.remove("Case"));
        assertEquals("Chase", list.toString());
        assertEquals(1, list.size());

        assertTrue(list.remove("Chase"));
        assertTrue(list.isEmpty());
    }
	
	@Test
	public void testAddToBack() {
		LinkedList<Integer> list = new LinkedList<>();
		list.addToBack(1);
		list.addToBack(2);
		list.addToBack(3);

		assertEquals("1;2;3", list.toString());
	}

	@Test
	public void testReverse() {
	    LinkedList<Integer> list1 = new LinkedList<Integer>();
	    list1.addToBack(1);
	    list1.addToBack(2);
	    list1.addToBack(3);

	    LinkedList<Integer> list2 = new LinkedList<>();
	    list2.addToBack(3);
	    list2.addToBack(2);
	    list2.addToBack(1);

	    assertEquals(list1.toString(), "1;2;3");
	    assertEquals(list2.toString(), "3;2;1");

	    list1.reverse();

	    assertEquals(list1.toString(), list2.toString());
	    
	    LinkedList<Integer> list3 = new LinkedList<>();

	    assertEquals(list3.toString(), "");

	    list3.reverse();

	    assertEquals(list3.toString(), "");
	}

	@Test
    public void testToString() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);

        assertEquals("1;2;3", list.toString());
    }
}
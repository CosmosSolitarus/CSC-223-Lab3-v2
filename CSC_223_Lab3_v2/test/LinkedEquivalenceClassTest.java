/**
 * @date 2/8/2024
 * @author Case Riddle
 * @author Sam Nusstein
 * @author Jack Roberts
 **/


import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

class LinkedEquivalenceClassTest {

	Comparator<Integer> comp = new Comparator<Integer>() {
		public int compare(Integer x, Integer y) {
			return Integer.compare(classify(x), classify(y));
		}
		
		private int classify(Integer n) {
			if (n < 0) return 1;
			
			if (n < 12) return 2;
			
			return 3;
		}
	};

	@Test
	void canonicalReturnTest() {
		LinkedEquivalenceClass<Integer> Lec = new LinkedEquivalenceClass<Integer>(comp);
		for (int i = 15; i < 23; i++) {
			Lec.add(i);         
		}

		assertEquals(15, Lec.canonical());

		assertTrue(Lec.demoteAndSetCanonical(16));
		assertEquals(16, Lec.canonical());

		assertTrue(Lec.demoteAndSetCanonical(20));
		assertEquals(20,Lec.canonical());

		assertFalse(Lec.demoteAndSetCanonical(null)); // null test
		assertFalse(Lec.demoteAndSetCanonical(20)); // already canonical
		assertFalse(Lec.demoteAndSetCanonical(30)); // belongs but not contained
		assertFalse(Lec.demoteAndSetCanonical(6)); // does not belong
	}

	@Test
	void isEmptyTest() {
		LinkedEquivalenceClass<Integer> Lec = new LinkedEquivalenceClass<Integer>(comp);
		assertTrue(Lec.isEmpty());

		Lec.add(65);
		Lec.demoteAndSetCanonical(65);
		assertEquals(1, Lec.size());
	}

	@Test
	void clearTest() {
		LinkedEquivalenceClass<Integer> Lec = new LinkedEquivalenceClass<Integer>(comp);
		
		for (int i = 0; i < 7; i++) {
			Lec.add(i);         
		}
		
		assertEquals(7,Lec.size());
		
		Lec.clear();
		assertTrue(Lec.isEmpty());
	}

	@Test
	void clearNonCanonicalTest() {
		LinkedEquivalenceClass<Integer> Lec = new LinkedEquivalenceClass<Integer>(comp);

		for (int i = 0; i < 7; i++) {
			Lec.add(i);         
		}

		assertEquals(0, Lec._canonical);

		Lec.demoteAndSetCanonical(3);
		Lec.clearNonCanonical();

		assertEquals(1, Lec.size());
		assertEquals(3, Lec._canonical);
	}

	@Test
	void sizeTest() {
		LinkedEquivalenceClass<Integer> Lec = new LinkedEquivalenceClass<Integer>(comp);
		
		assertEquals(0, Lec.size());

		// n < 12
		// add an element
		for(int i = 0; i < 10; i++) {
			Lec.add(i);
			assertEquals(i + 1, Lec.size());
		}

		// remove an element
		Lec.remove(7);
		assertEquals(9, Lec.size());

		// add after remove
		Lec.add(10);
		assertEquals(10, Lec.size());

		// demote and set
		Lec.demoteAndSetCanonical(6);
		assertEquals(10, Lec.size());

		// add after demote and set
		Lec.add(11);
		assertEquals(11, Lec.size());

		// clear
		Lec.clear();
		assertEquals(0, Lec.size());

		// add after clear
		Lec.add(-2);
		assertEquals(1, Lec.size());
	}

	@Test
	void addTest() {
		LinkedEquivalenceClass<Integer> Lec1 = new LinkedEquivalenceClass<Integer>(comp);
		LinkedEquivalenceClass<Integer> Lec2 = new LinkedEquivalenceClass<Integer>(comp);
		LinkedEquivalenceClass<Integer> Lec3 = new LinkedEquivalenceClass<Integer>(comp);
		
		// n < 0
		for(int i = -8; i < 0; i++) {
			assertTrue(Lec1.add(i));         
		}

		// n < 12
		for(int i = 0; i < 12; i++) {
			assertTrue(Lec2.add(i));         
		}

		// n >= 12
		for(int i = 12; i < 20; i++) {
			assertTrue(Lec3.add(i));         
		}

		// all fail because do not belong or already in class
		for (int i = -8; i < 20; i++) {
			assertFalse(Lec1.add(i));
			assertFalse(Lec2.add(i));
			assertFalse(Lec3.add(i));
		}
	}

	@Test
	void belongsTest() {
		LinkedEquivalenceClass<Integer> Lec = new LinkedEquivalenceClass<Integer>(comp);
		for (int i = -40; i < -6; i++) {
			Lec.add(i);
		}

		assertTrue(Lec.belongs(-2));
		assertTrue(Lec.belongs(-53));
		assertTrue(Lec.belongs(-13));

		assertFalse(Lec.belongs(0));
		assertFalse(Lec.belongs(2));
		assertFalse(Lec.belongs(24));

		Lec.demoteAndSetCanonical(-13);
		assertTrue(Lec.belongs(-2));
		assertTrue(Lec.belongs(-53));
		assertTrue(Lec.belongs(-13));

		assertFalse(Lec.belongs(0));
		assertFalse(Lec.belongs(2));
		assertFalse(Lec.belongs(24));

		Lec.add(-4);
		assertTrue(Lec.belongs(-4));

		Lec.remove(-4);
		assertTrue(Lec.belongs(-4));
	}

	@Test
	void removeTest() {
		LinkedEquivalenceClass<Integer> Lec = new LinkedEquivalenceClass<Integer>(comp);
		
		for (int i = 0; i < 7; i++) {
			Lec.add(i);         
		}
		
		assertEquals(7, Lec.size());

		for(int i = 0; i < 7; i++) {
			assertTrue(Lec.remove(i));
		}
		
		assertFalse(Lec.remove(1));
		assertEquals(0,Lec.size());
	}

	@Test

	void removeCanonicalTest() {
		LinkedEquivalenceClass<Integer> Lec = new LinkedEquivalenceClass<Integer>(comp);
		for (int i = 0; i < 7; i++) {
			Lec.add(i);         
		}

		for (int i = 1; i < 7; i++) {
			assertTrue(Lec.demoteAndSetCanonical(i));
			assertTrue(Lec.removeCanonical());
			assertEquals(null, Lec._canonical);
		}

		assertEquals(1, Lec.size());
	}

	@Test
	void demoteAndSetCanonicalTest() {
		LinkedEquivalenceClass<Integer> Lec = new LinkedEquivalenceClass<Integer>(comp);

		for (int i = 0; i < 7; i++) {
			Lec.add(i); 
		}

		for(int i = 1; i < 7; i++) {
			assertTrue(Lec.demoteAndSetCanonical(i));
		}

		assertEquals(6,Lec.canonical());
		}

	@Test
	void toStringTest() {
		LinkedEquivalenceClass<Integer> Lec = new LinkedEquivalenceClass<Integer>(comp);
		
		for(int i = 0; i < 7; i++) {
			Lec.add(i);         
		}

		Lec.demoteAndSetCanonical(1);
		assertEquals("{1 | 2;3;4;5;6;0}",Lec.toString());
		
		Lec.demoteAndSetCanonical(2);
		assertEquals("{2 | 3;4;5;6;0;1}",Lec.toString());
	}
}	



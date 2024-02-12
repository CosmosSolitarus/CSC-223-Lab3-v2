/**
 * @date 2/8/2024
 * @author Case Riddle
 * @author Sam Nusstein
 * @author Jack Roberts
 **/
package input.components.LinkedEquivalenceClass;

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
		for(int i=0; i<7;i++) {
			Lec.add(i);         
		}
		assertTrue(Lec.demoteAndSetCanonical(0));
		assertEquals(0,Lec.canonical());

		assertTrue(Lec.demoteAndSetCanonical(6));
		assertEquals(6,Lec.canonical());

		assertThrows(NullPointerException.class, ()->{Lec.demoteAndSetCanonical(88);});
	}

	@Test
	void isEmptyTest() {
		LinkedEquivalenceClass<Integer> Lec = new LinkedEquivalenceClass<Integer>(comp);
		assertTrue(Lec.isEmpty());

		Lec.add(65);
		Lec.demoteAndSetCanonical(65);
		assertTrue(Lec.isEmpty());
	}

	@Test
	void clearTest() {
		LinkedEquivalenceClass<Integer> Lec =new LinkedEquivalenceClass<Integer>(comp);
		for(int i=0; i<7;i++) {
			Lec.add(i);         
		}
		assertEquals(7,Lec.size());
		Lec.clear();
		assertTrue(Lec.isEmpty());
	}

	@Test
	void clearNonCanonicalTest() {
		LinkedEquivalenceClass<Integer> Lec =new LinkedEquivalenceClass<Integer>(comp);
		for(int i=0; i<7;i++) {
			Lec.add(i);         
		}	
		Lec.demoteAndSetCanonical(3);
		Lec.clearNonCanonical();
		assertTrue(Lec.isEmpty());
		assertEquals(3, Lec._canonical);
	}

	@Test
	void sizeTest() {
		LinkedEquivalenceClass<Integer> Lec =new LinkedEquivalenceClass<Integer>(comp);
		for(int i=0; i<7;i++) {
			Lec.add(i);         
		}

		assertEquals(7,Lec.size());
		Lec.add(7);
		assertEquals(8,Lec.size());
		Lec.remove(7);
		assertEquals(7,Lec.size());
		Lec.demoteAndSetCanonical(6);
		assertEquals(6,Lec.size());
		Lec.clear();
		assertEquals(0,Lec.size());

	}

	@Test
	void addTest() {
		LinkedEquivalenceClass<Integer> Lec =new LinkedEquivalenceClass<Integer>(comp);
		for(int i=0; i<20;i++) {
			assertTrue(Lec.add(i));         
		}
		assertThrows(NullPointerException.class, ()->{Lec.add(null);});
	}

	@Test
	void belongsTest() {
		LinkedEquivalenceClass<Integer> Lec =new LinkedEquivalenceClass<Integer>(comp);
		for(int i=0; i<45;i++) {
			Lec.add(i);         
		}

		Lec.demoteAndSetCanonical(1);
		assertTrue(Lec.belongs(7));
		assertTrue(Lec.belongs(43));
		assertTrue(Lec.belongs(3));
		assertFalse(Lec.belongs(2));
		assertFalse(Lec.belongs(44));
		assertFalse(Lec.belongs(0));

		Lec.demoteAndSetCanonical(43);
		assertTrue(Lec.belongs(1));
		assertFalse(Lec.belongs(2));

		Lec.demoteAndSetCanonical(2);
		assertTrue(Lec.belongs(4));
		assertTrue(Lec.belongs(44));
		assertTrue(Lec.belongs(0));
		assertFalse(Lec.belongs(1));
		assertFalse(Lec.belongs(43));
	}

	@Test
	void removeTest() {
		LinkedEquivalenceClass<Integer> Lec =new LinkedEquivalenceClass<Integer>(comp);
		for(int i=0; i<7;i++) {
			Lec.add(i);         
		}
		
		assertEquals(7,Lec.size());
		for(int i=0; i<7;i++) {
			assertTrue(Lec.remove(i));
		}
		
		assertFalse(Lec.remove(1));
		assertEquals(0,Lec.size());
	}

	@Test

	void removeCanonicalTest() {
		LinkedEquivalenceClass<Integer> Lec = new LinkedEquivalenceClass<Integer>(comp);
		for(int i=0; i<7;i++) {
			Lec.add(i);         
		}
		for(int i=0; i<7;i++) {
		assertTrue(Lec.demoteAndSetCanonical(i));
		assertTrue(Lec.removeCanonical());
		}
		assertEquals(0,Lec.size());
		
	}

	@Test
	void demoteAndSetCanonicalTest() {
		LinkedEquivalenceClass<Integer> Lec = new LinkedEquivalenceClass<Integer>(comp);
		for(int i=0; i<7;i++) {
			Lec.add(i);         
		}
		for(int i=0;i<7; i++) {
			assertTrue(Lec.demoteAndSetCanonical(i));
			assertFalse(Lec.contains(i));
		}
		assertEquals(6,Lec.canonical());
		}

	@Test
	void toStringTest() {
		LinkedEquivalenceClass<Integer> Lec = new LinkedEquivalenceClass<Integer>(comp);
		for(int i=0; i<7;i++) {
			Lec.add(i);         
		}
		Lec.demoteAndSetCanonical(1);
		assertEquals("{1 | 6;5;4;3;2;0}",Lec.toString());
		
		Lec.demoteAndSetCanonical(2);
		assertEquals("{2 | 1;6;5;4;3;0}",Lec.toString());
	}
}	



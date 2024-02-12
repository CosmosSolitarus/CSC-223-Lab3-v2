/**
 * @date 2/9/2024
 * @author Case Riddle
 * @author Sam Nusstein
 * @author Jack Roberts
 **/

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

class EquivalenceClassesTest {

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
	void addTest() {
		EquivalenceClasses<Integer> ECS = new EquivalenceClasses<Integer>(comp);

		assertFalse(ECS.add(-4));
		assertFalse(ECS.add(1));
		assertFalse(ECS.add(13));

		for (int i = -40; i < 40; i++) {
			assertTrue(ECS.add(i));
		}

		assertTrue(ECS.add(null));
	}

	@Test
	void containsTest() {
		EquivalenceClasses<Integer> ECS = new EquivalenceClasses<Integer>(comp);

		ECS.add(-4);
		ECS.add(1);
		ECS.add(13);

		for (int i = -40; i < 40; i++) {
			ECS.add(i);
			assertTrue(ECS.contains(i));
		}

		for (int i = -80; i < -40; i++) {
			assertFalse(ECS.contains(i));
		}

		for (int i = 40; i < 80; i++) {
			assertFalse(ECS.contains(i));
		}
	}

	@Test
	void sizeTest() {
		EquivalenceClasses<Integer> ECS = new EquivalenceClasses<Integer>(comp);

		assertEquals(0, ECS.size());

		ECS.add(-4);
		assertEquals(1, ECS.size());

		ECS.add(1);
		assertEquals(2, ECS.size());
		
		ECS.add(13);
		assertEquals(3, ECS.size());

		for (int i = -40; i < 40; i++) {
			ECS.add(i);
		}

		assertEquals(83, ECS.size());
	}

	@Test
	void numClassesTest() {
		EquivalenceClasses<Integer> ECS = new EquivalenceClasses<Integer>(comp);

		assertEquals(0, ECS.numClasses());

		ECS.add(-4);
		assertEquals(1, ECS.numClasses());

		ECS.add(1);
		assertEquals(2, ECS.numClasses());
		
		ECS.add(13);
		assertEquals(3, ECS.numClasses());

		for (int i = -40; i < 40; i++) {
			ECS.add(i);
		}

		assertEquals(3, ECS.numClasses());
	}

	@Test
	void indexOfClassTest() {
		EquivalenceClasses<Integer> ECS = new EquivalenceClasses<Integer>(comp);

		for (int i = -10; i < 20; i++) {
			assertEquals(-1, ECS.indexOfClass(i));
		}

		ECS.add(-4);

		ECS.add(1);
		
		ECS.add(13);

		for (int i = -40; i < 40; i++) {
			ECS.add(i);
		}

		for (int i = -40; i < 0; i++) {
			assertEquals(0, ECS.indexOfClass(i));
		}

		for (int i = 0; i < 12; i++) {
			assertEquals(0, ECS.indexOfClass(i));
		}

		for (int i = 12; i < 40; i++) {
			assertEquals(0, ECS.indexOfClass(i));
		}
	}
}

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
	        return x.compareTo(y);
	    }
	};

	@Test
	void addTest() {
		EquivalenceClasses<Integer> ECS = new EquivalenceClasses<Integer>(comp);
	}

	@Test
	void containsTest() {
		EquivalenceClasses<Integer> ECS = new EquivalenceClasses<Integer>(comp);
	}

	@Test
	void sizeTest() {
		EquivalenceClasses<Integer> ECS = new EquivalenceClasses<Integer>(comp);
	}

	@Test
	void numClassesTest() {
		EquivalenceClasses<Integer> ECS = new EquivalenceClasses<Integer>(comp);

		assertEquals(0, ECS.numClasses());
	}

	@Test
	void indexOfClassTest() {
		EquivalenceClasses<Integer> ECS = new EquivalenceClasses<Integer>(comp);
	}
}

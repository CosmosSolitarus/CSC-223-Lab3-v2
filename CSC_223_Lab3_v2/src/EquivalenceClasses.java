/**
 * Implements a set of equivalence classes. Equivalence classes split
 * up elements in a universe, with no overlapping elements.
 * 
 * @date 2/9/2024
 * @author Case Riddle
 * @author Sam Nusstein
 * @author Jack Roberts
 **/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EquivalenceClasses<T> {

	protected Comparator<T> _comparator;
		
	protected List<LinkedEquivalenceClass<T>> _classes;
		
	
	public EquivalenceClasses(Comparator<T> comp) {
		_comparator = comp;
		_classes = new ArrayList<>();
	}
	
	/**
	 * Adds element to the appropriate equivalence class.
	 * Returns false if a new equivalence class was created.
	 * 
	 * @param element
	 * @return
	 */
	public boolean add(T element) {
		// add element to class if class exists
		for (LinkedEquivalenceClass<T> lec : _classes) {
			if (lec.add(element)) return true;
		}

		// otherwise create new class
		LinkedEquivalenceClass<T> lec = new LinkedEquivalenceClass<T>(_comparator);
		lec.demoteAndSetCanonical(element);
		_classes.add(lec);
		
		return false;
	}

	/**
	 * Check if target occurs in any equivalence class
	 * 
	 * @param target
	 * @return
	 */
	public boolean contains(T target) {
		for (LinkedEquivalenceClass<T> lec : _classes) {
			if (lec.contains(target)) return true;
		}

		return false;
	}

	/**
	 * Counts all elements in all equivalence classes
	 * 
	 * @return
	 */
	public int size() {
		int count = 0;

		for (LinkedEquivalenceClass<T> lec : _classes) {
			count += lec.size();
		}
		
		return count;
	}

	/**
	 * Counts number of equivalence classes
	 * 
	 * @return
	 */
	public int numClasses() {
		return _classes.size();
	}

	/**
	 * Finds the index of the first equivalence class element
	 * occurs in. If element is not found, returns -1.
	 * 
	 * @param element
	 * @return
	 */
	protected int indexOfClass(T element) {
		for (int i = 0; i < _classes.size(); i++) {
			if (_classes.get(i).contains(element)) return i;
		}
		
		return -1;
	}

	public String toString() {
		return null;
	}
}

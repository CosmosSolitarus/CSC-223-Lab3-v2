/**
 * Implements an equivalence class. An equivalence class
 * is a collection of elements sharing some property, and
 * the class does not share elements with other classes.
 * One element is deemed the "canonical" element, which
 * indicates the property shared by all other elements. For
 * example, 2 could be the canonical element of even numbers,
 * while 5 could be the canonical element of odd numbers.
 * 
 * 
 * @date 2/8/2024
 * @author Case Riddle
 * @author Sam Nusstein
 * @author Jack Roberts
 **/

import java.util.Comparator;

public class LinkedEquivalenceClass<T> {

	protected T _canonical;
	protected Comparator<T> _comparator;
	protected LinkedList<T> _rest;

	public LinkedEquivalenceClass(Comparator<T> comp) {
		_canonical = null;
		_comparator = comp;
		_rest = new LinkedList<T>();
	}

	/**
	 * Returns the canonical element
	 * 
	 * @return
	 */
	public T canonical() {
		return _canonical;
	}
	
	/**
	 * Returns whether the class is empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return _rest.isEmpty();
	}

	/**
	 * clears the class and the canonical element
	 */
	public void clear() {
		_rest.clear();
		_canonical = null;
	}
	
	/**
	 * clears the class
	 */
	public void clearNonCanonical() {
		_rest.clear();
	}

	/**
	 * Returns the size of the class + the canonical
	 * @return
	 */
	public int size() {
		return _rest.size() + 1;
	}
	
	/**
	 * Adds element to class if it belongs.
	 * 
	 * Returns true if element belongs and 
	 * is not already in the class. 
	 * 
	 * Returns false if element does not 
	 * belong or is already in the class.
	 * 
	 * @param element
	 * @return
	 */
	public boolean add(T element) {
		if (belongs(element)) {
			_rest.addToBack(element);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns whether target is contained
	 * in the class already
	 * 
	 * @param target
	 * @return
	 */
	public boolean contains(T target) {
		return _rest.contains(target);
	}
	
	/**
	 * Returns whether target should be contained
	 * in the class and is currently not.
	 * 
	 * @param target
	 * @return
	 */
	public boolean belongs(T target) {
		if (_canonical == null) {
			return false;
		} else if (_comparator.compare(_canonical, target) != 0) {
			return false;
		} else if (!target.equals(_canonical)) {
			return false;
		} else if (!contains(target)) {
			return false;
		}
		
		return true;
	}

	/**
	 * Returns whether target is removed from class.
	 * Target is removed if it is in the class;
	 * otherwise false.
	 * 
	 * @param target
	 * @return
	 */
	public boolean remove(T target) {
		return _rest.remove(target);
	}
	
	/**
	 * Returns whether target is removed from class.
	 * Target is removed if it is in the class;
	 * otherwise false.
	 * 
	 * @param target
	 * @return
	 */
	public boolean removeCanonical() {
		return _rest.remove(_canonical);
	}
	
	/**
	 * Moves current canonical element to class and
	 * sets a new canonical element. If canonical is null,
	 * then it is replaced and null is not added to
	 * the class.
	 * 
	 * Returns whether current canonical is null and is
	 * replaced by a valid element. Elements must belong
	 * to the class to be valid, but do not already need
	 * to be in the class.
	 * 
	 * Removes new canonical from class if it is contained
	 * there.
	 * 
	 * @param element
	 * @return
	 */
	public boolean demoteAndSetCanonical(T element) {
		if (!belongs(element)) {
			return false;
		}
		
		if (_canonical == null) {
			_canonical = element;
			_rest.remove(element);
			return true;
		}
		
		_rest.remove(element);
		_rest.addToBack(_canonical);
		_canonical = element;
		return true;
	}
	
	/**
	 * toString method. Example toString:
	 * 
	 * {2 | 4,6,8,10}
	 */
	public String toString() {
		return "{" + _canonical.toString() + " | " + _rest.toString() + "}";
	}
}

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
	 * Returns whether the class and canonical are empty
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return _rest.isEmpty() && _canonical == null;
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
		if (_canonical != null) return _rest.size() + 1;
		
		return _rest.size();
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
		if (isEmpty()) {
			_canonical = element;
			return true;
		}

		if (belongs(element) && !contains(element)) {
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
		if (target == null && _canonical == null) return true;
		
		if (_canonical.equals(target)) return true;
		
		if (_rest.contains(target))	return true;
		
		return false;
	}
	
	/**
	 * Returns whether target should be contained
	 * in the class.
	 * 
	 * @param target
	 * @return
	 */
	public boolean belongs(T target) {
		if (isEmpty()) return false;
		
		if (target == null) return false;

		if (_canonical == null) return _comparator.compare(_rest.first(), target) != 0;
		
		if (_comparator.compare(_canonical, target) != 0) return false;
		
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
		if (target == null && _canonical == null) return true;
		
		if (isEmpty()) return false;

		if (_canonical.equals(target)) {
			_canonical = null;

			if (!isEmpty()) {
				demoteAndSetCanonical(_rest.first());
			}

			return true;
		}
		
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
		if (_canonical == null) return false;
		
		_canonical = null;
		
		return true;
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
		if (isEmpty()) return false;

		if (_canonical == null) {
			_canonical = _rest.first();
			_rest.remove(_rest.first());

			demoteAndSetCanonical(element);

			return true;
		}
		
		if (!belongs(element) || !contains(element)) {
			return false;
		}
		
		if (_canonical.equals(element)) return false;

		_rest.remove(element);
		_rest.addToBack(_canonical);
		_canonical = element;

		return true;
	}
	
	/**
	 * toString method. Example toString:
	 * 
	 * {2 | 4;6;8;10}
	 */
	public String toString() {
		return "{" + _canonical + " | " + _rest.toString() + "}";
	}
}

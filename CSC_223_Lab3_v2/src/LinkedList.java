
/**
 * Methods that initialize and operate a linked list datatype.
 * @date 2/8/2024
 * @author Case Riddle
 * @author Sam Nusstein
 * @author Jack Roberts
 **/

 public class LinkedList<T> {
	protected Node _head;
	protected Node _tail;
	protected int _size;

	protected class Node {
		protected T _data;
		protected Node _next;

		public Node() {
			this(null, null);
		}

		public Node(T data, Node next) {
			_data = data;
			_next = next;
		}
	}

	/**
	 * Creates sentinel nodes and an empty linked list.
	 **/
	public LinkedList() {
		_tail = new Node(null, null);
		_head = new Node(null, _tail);
		_size = 0;
	}

	public boolean isEmpty() {
		return _size == 0;
	}

	public void clear() {
		_head._next = _tail;
		_size = 0;
	}

	public int size() {
		return _size;
	}

	/**
	 * Creates a new node and increments the size.
	 * @param element
	 **/
	public void addToFront(T element) {
		_head._next = new Node(element, _head._next);
		_size++;
	}

	/**
	 * Iterates until the target is reached.
	 * Returns n.
	 * @param target
	 **/
	public boolean contains(T target) {
		for (Node n = _head._next; n != _tail; n = n._next) {
			if (n._data.equals(target)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Iterates until the target is the next element, not until it is reached.
	 * Returns n.
	 * @param target
	 **/
	private Node previous(T target) {		
		for (Node n = _head; n != _tail; n = n._next) {
			if (n._next._data == null || n._next._data.equals(target)) {
				return n;
			}
		}
		
		return null;
	}

	/**
	 * Removes the node containing the specified target value from the linked list.
	 * Updates the reference of the previous node to skip the targeted node.
	 * Decrements size.
	 * @param target
	 */
	public boolean remove(T target) {
		Node n = previous(target);
		
		if (n == null) {
			return false;
		}

		n._next = n._next._next;
		_size--;
		
		return true;
	}

	/**
	 * Obtains the last node in a linked list by iterating from the head.
	 * Continues until reaches the node just before the tail.
	 * @return n
	 **/
	private Node last() {
		return previous(null);
	}

	/**
	 * Obtains the first node in the linked list.
	 * 
	 * @return
	 */
	public T first() {
		if (_size > 0) {
			return _head._next._data;}
		
		return null;
	}
	
	/**
	 * Adds the specified element to the location after the tail.
	 * @param element
	 **/
	public void addToBack(T element) {
	    last()._next = new Node(element, _tail);
	    _size++;
	}
	
	/**
	 * Recursive method that reverses inputs in a list.
	 * Difficulty: S+
	 **/
	public void reverse() {
	    if (_size >= 2) {
	    	reverse(_head._next)._next = _tail;
	    }
	}

	private Node reverse(Node n) {
    	if (n._next._next == _tail) {
	    	_head._next = n._next;
	    } else {
	    	reverse(n._next);
	    }
	    
	    n._next._next = n;
	    
	    return (n);
	}

	/**
	 * Utilizes a public-private paradigm for recursion.
	 **/
	@Override
	public String toString() {
		return toStringRecursive(_head._next);
	}

	private String toStringRecursive(Node node) {
		if (node == _tail) {
			return "";
		}
		
		String result = node._data.toString();

		if (node._next != _tail) {
			result += ";";
		}
		
		result += toStringRecursive(node._next);
		return result;
	}
}
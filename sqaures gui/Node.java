

/**
 * Class for the nodes in this build. each node has a next , prev , and data pointer. since nodes have next and prev,
 * nodes should be used in the context of a doublylinkedlist
 * @author thomas
 * @param <T> type of generic to be used with node object
 */
class Node<T> {
	/**
	 * data placeholder.
	 */
	public T data;
	/**
	 * points to node infront.
	 */
	public Node<T> next;
	/**
	 * points to node behind.
	 */
	public Node<T> prev;

	/**
	 * no arg constructor for node.
	 */
	public Node() {
		
	}

	/**
	 * constructor for node object.
	 * @param data value to be held in node
	 */
	public Node(T data) {
		this.data = data;
	}
}
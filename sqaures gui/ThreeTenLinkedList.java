
import java.util.Comparator;

/**
 * class with static methods sort and is sorted, each work on a nodepair, containing a head and tail of a list.
 * @param <T> generic type to be passed into class
 */
class ThreeTenLinkedList<T> {
	//You may, but are not required to, implement some or
	//all of this generic linked list class to help you with
	//this project. If you do, you MUST use the provided
	//Node class for this linked list class. This means
	//that it must be a doubly linked list (and links in
	//both directions MUST work).

	//Alternatively, you may implement this project using
	//only the Node class itself (i.e. use "bare nodes"
	//in the classes that require linked lists).

	//Either way, you MUST do all your own work. Any other
	//implementations you have done in the past, anything
	//from the book, etc. should not be in front of you,
	//and you certainly shouldn't copy and paste anything
	//from any other source.

	//This is the only class where you are allowed to
	//implement public methods.

	//In "Part 5" of the project, you will also be implementing
	//the following static methods:

	// isSorted(pairs, comp

	/**
	 * WORKING METHOD TO CHECK IF LIST IS SORTED, checks from tail,( end where objects are inserted most recently)
	 * to head, where the first objects have been sorted.
	* @param pairs the doublylinkedlist stack to be checking for if sorting has been done
	 * @param comp the method of comparison to use when sorting linkedlist
	 * @param <X> the generic type of objects to be comparing
	 * @return true if list is sorted in ascending order ( largest near tail end, smallest near head end), false if list
	 * * is not sorted to these requirements
	 * @throws IllegalArgumentException if null or invalid arguements are passed in to method
	 */
	static <X> boolean isSorted(NodePair<X> pairs, Comparator<X> comp) throws IllegalArgumentException {
		//Determine if the provided list is sorted based on the comparator.


		//For an empty linked list (e.g. the head-tail pair contains two nulls)
		//return true (an empty list is sorted).
		if ((pairs.head == null) && (pairs.tail == null)) {
			return true;
		}

		//For a null comparator or null pairs, throw an IllegalArgumentException.
		if ((pairs == null) || (comp == null)) {
			throw new IllegalArgumentException();
		}
		Node parse = pairs.head;
		while(parse.next != null)
		{
			if((comp.compare((X) parse.data, (X) parse.next.data) < 0))
			{
				return false;
			}
			parse = parse.next;
		}


		//< YOUR_CODE_HERE >

		return true; //replace this!
	}

	/**
	 * private in class method, obtains number of numbers in linkedlist "pairs".
	 * @param pairs the linkedlist to find the number of nodes of
	 * @return number of numbers in linkedlist associated with pairs
	 */
	private static int get_size(NodePair pairs) {
		int size = 0;
		if ((pairs.head == null) || (pairs.tail == null)) {// if pairs empty
			return size;
		}
		Node parse = pairs.head;
		while (parse.next != null) {
			size++;
			parse = parse.next;
		}
		return size + 1; // add on additional element bc head (last node) not coevered
	}


	/**
	 * private in class method to remove a desired node from the linkedlist associated with pairs.
	 * @param pairs the head and tail that have an associated linkedlist that will have their elements removed
	 * @param n node n to be removed from list associated with pairs
	 */
	private static void remove(NodePair pairs, Node n) {
		if((pairs.head==null)||(pairs.tail==null))
		{
			return;
		}

		if (n.equals(pairs.tail)) {
			pairs.tail.prev.next = null;
			pairs.tail = pairs.tail.prev;
			//size--;
			return;
		}
		if (n.equals(pairs.head)) {
			pairs.head.next.prev = null;
			pairs.head = pairs.head.next;
			//size--;
			return;
		}


		Node parse = pairs.head;
		while (parse.next != null) {
			if (parse.equals(n)) {
				parse.next.prev = parse.prev;
				parse.prev.next = parse.next;
				//size--;

				return;
			}
			parse = parse.next;
		}
	}

	/**
	 * in class private add method, adds a node to the tail end of the list.
	 * @param pairs the linkedist to have the node added to
	 * @param n node n is added at the end, becomes new tail
	 */
	private static void add(NodePair pairs, Node n) {

		if (get_size(pairs) == 0) {
			pairs.head = n;
			pairs.tail = n;
			//size++;
		} else {
			n.next = pairs.head;// sets the node infront of new node to be tail
			pairs.head.prev = n;// sets the node behind tail to be new node
			pairs.head = n;// sets tail pointer to new node
			//size++;// increments size
		}
	}

	/**
	 * private in class method to add a node at a desired location.
	 * @param pairs linkedlist associated with pairs that will have a node added to it in a desired location
	 * @param n the node n that will have a node inserted (next) after it
	 * @param after the node to be placed in after node n
	 */
	private static void insertAfter(NodePair pairs, Node n, Node after)// n is the node that will have after inserted after it
	{

		if (get_size(pairs) == 0) {
			pairs.head = n;
			pairs.tail = after;
			pairs.head.next = after;
			after.prev = pairs.tail;

			//size= size +2;
			return;
		} else {
			if (pairs.tail == n) {
				pairs.tail.next = after;
				after.prev = pairs.tail;
				pairs.tail = after;
				//size++;
				return;
			}

			after.prev = n;
			//System.out.println("the node n that has something inserted after is "+ after.prev.data);
			//  System.out.println();
			after.next = n.next;
			//  System.out.println("node being inserted has next pointer updated to same next as n is "+after.next.data);
			n.next.prev = after;
			//  System.out.println("node infront of n has prev pointer changed to the node inserted = "+n.next.prev.data);
			// System.out.println("node infront of n" +n.next.data);
			n.next = after;
			//size++;
		}

	}
	//Using the comparator, sort the linked list. It is recommended that
	//you sort by moving *values* around rather than moving nodes.
	//Two simple sorting algorithms which will work well here (and
	//meet the big-O requirements if implemented correctly) are the
	//insertion sort (see textbook Ch8.3) and the selection sort.

	//Insertion sort quick summary: Go to each element in the linked list,
	//shift it "left" into the correct position.
	//Example: 1,3,0,2
	// 1 is at the start of the list, leave it alone
	// 3 is bigger than 1, leave it alone
	// 0 is smaller than 3, move it left: 1,0,3,2
	// 0 is smaller than 1, move it to the left: 0,1,3,2
	// 0 is at the start of the list, stop moving it left
	// 2 is smaller than 3, move it to the left: 0,1,2,3
	// 2 is bigger than 1, stop moving it to the left

	//Selection sort quick summary: Go to each index in the linked list,
	//find the smallest thing from that index and to the "right",
	//and swap it into that index.
	//Example: 1,3,0,2
	// index 0: the smallest thing from index 0 to the end is 0, swap it into the right place: 0,3,1,2
	// index 1: the smallest thing from index 1 to the end is 1, swap it into the right place: 0,1,3,2
	// index 2: the smallest thing from index 2 to the end is 2, swap it into the right place: 0,1,2,3
	// index 3: there is only one item from index 3 to the end, so this is in the right places

	//Regardless of the method you choose, your sort should be a stable sort.
	//This means that if there are two equal values, they do not change their
	//order relative to each other.
	//Example: 1, 2, 1
	//The first "1" (which I'll call "1a") should be sorted before
	//the second "1" (1b), so that the output is "1a, 1b, 2" and
	//never "1b, 1a, 2". The easiest way to test this is to put two
	//equal items in the list, sort, and confirm using == that the
	//correct object is in the correct place.

	//For an empty linked list (e.g. the head-tail pair contains two nulls)
	//return the original pairs back to the user.

	//For a null comparator or null pairs, throw an IllegalArgumentException.

	//O(n^2)

	//< YOUR_CODE_HERE >
	// 2 3 4 1 5
	// go through your list and track the samllest...

	/**
	 * WORKING sort using insertion sort.
	 * @param pairs the head and tial of the linkedlist to be sorted
	 * @param comp the comparison method to use
	 * @param <X> the generic type of objects that will be compared
	 * @return the sorted nodepair object
	 * @throws NullPointerException if null comparator given
	 */
	static <X> NodePair<X> sort(NodePair<X> pairs, Comparator<X> comp) throws NullPointerException
	{
		if(comp == null)
		{
			throw new NullPointerException();
		}
		int size = get_size(pairs);
		if (size <= 1)
		{
			return null;
		}

		Node current = pairs.head.next; // starts at front,
		Node search;
		Node next; // placeholder for current.next

		while (current != null)// while current is not null, meaning list not sorted yet
		{
			next = current.next; // next points to next node of current
			search = current.prev;// search node ssearches the sorted part for the correct place for the new smallest node
			//	(comp.compare((X) pairs.tail.data, (X) pairs.head.data) < 0)
			while ((search != null) && (comp.compare((X) search.data, (X) current.data) < 0)) {// while search has not reached the beginning of the list yet, and search is finding the right spot
				search = search.prev;// traverses backwards
			}
			remove(pairs, current);// node being moved from unsorted part to sorted is removed
			if (search == null)// if beginning of list is reached and no smaller numbers than the one being newly added
			// in are found,
			{
				current.prev = null;// current is added to front of list since nothings smaller
				add(pairs, current);
			} else {        // if its not then its put in after the element smaller than it
				insertAfter(pairs, search, current);
			}
			current = next; // sorted part grows
		}
		return pairs;
	}







	//Which uses the following nested class:

	/**
	 * class containing a head and tail node, acting as a linkedlist. has associated static methods sort and issorted to be used with them.
	 * @param <Y> the generic type of objects to be used with the nodes within the class
	 */
	public static class NodePair<Y>
	{
		//TODO REMOVE FINAL
		private int size=2;
		/**
		 * head node associated with the pair object.
		 */
		public Node<Y> head;
		/**
		 * tail node associatred with the pair.
		 */
		public Node<Y> tail;

		/**
		 * the arg constructor for the class.
		 * @param head the head given will be the head end node of the linkedlist
		 * @param tail the tail node given will be the tail end node of this linkedlist
		 */
		public NodePair(Node<Y> head, Node<Y> tail)
		{
			this.head = head;
			this.tail = tail;
			//TODO REMOVE
			this.head.next = this.tail;
			this.tail.prev = this.head;

		}
		//TODO REMOVE
		public int GetSize()
		{
			return this.size;
		}
		//TODO remove beofr final
		public void add(Node n)
		{
			//int size = get_size(this);
			if(size==0)
			{
				this.head=n;
				this.tail=n;
				size++;
			}
			else

			{
				n.next=this.head;// sets the node infront of new node to be tail
				this.head.prev=n;// sets the node behind tail to be new node
				this.head=n;// sets tail pointer to new node
				size++;// increments size
			}
		}


	}

}
	
	//You may also use the above nested class elsewhere in your
	//project if you'd find that helpful.


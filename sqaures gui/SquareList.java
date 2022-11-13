
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.awt.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 *  A list of squares within a single window.
 *  
 *  <p>Adapterion of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated Fall
 *  2022 by K. Raven Russell.</p>
 */
public class SquareList {
	//You'll need some instance variables probably...
	//< YOUR_CODE_HERE >
	/**
	 * size of the list.
	 */
	private int size;
	/**
	 * tail node placeholder.
	 */
	private Node<Square> tail;
	/**
	 * head node placeholder.
	 */
	private Node<Square> head;
	/**
	 *  Initialize an empty list of squares.
	 */
	public SquareList() {
		size=0;
		//Any initialization code you need.
		
		//O(1)
		
		//< YOUR_CODE_HERE >
	}

	/**
	 * return the head node.
	 * @return node at head of list
	 */
	public Node<Square> getHead() {
		//Returns the head of the list of squares.
		if(size==0)
		{
			return null;
		}
		return this.head;
		//O(1)
		
		//We will use this method to test your
		//linked list implementaiton of this
		//class, so whether or not you are using
		//the generic linked list class or bare
		//nodes, you must still be able to return
		//the appropriate head of the list.
		
		//< YOUR_CODE_HERE >
		
		//return null; //dummy return, replace this!
	}

	/**
	 * return tail node.
	 * @return node at the tail
	 */
	public Node<Square> getTail() {
		//Returns the tail of the list of squares.
		if(size==0)
		{
			return null;
		}
		else
		{
			return this.tail;
		}
		//O(1)
		
		//We will use this method to test your
		//linked list implementaiton of this
		//class, so whether or not you are using
		//the generic linked list class or bare
		//nodes, you must still be able to return
		//the appropriate tail of the list.
		
		//< YOUR_CODE_HERE >
		
		//return null; //dummy return, replace this!
	}

	/**
	 * reutrn number of squares in list.
	 * @return amount of squares in list
	 */
	public int numSquares()
	{
		//Gets the number of squares in the list.
		return this.size;
		//O(1)
		
		//< YOUR_CODE_HERE >
		
		//return -1;
	}

	/**WORKING
	 * adds a square to the end of the list.
	 * @param sq the data value to be inserted into the new node, new node is then placed into the end of the
	 *           list, and the pointers for tail.prev, new.next and tail are updated
	 * @throws IllegalArgumentException if a null value is attemped to be added
	 */
	public void add(Square sq) throws IllegalArgumentException
	{
		if(sq.equals(null))
		{
			throw new IllegalArgumentException();
		}
		Node<Square> newNode = new Node<Square>(sq);
		if(size==0)// if the list is empty
		{
			this.tail= newNode;
			this.head= newNode;
			size++;
		}
		else//if theres already nodes in the list

		{
			newNode.next=this.head;// sets the node infront of new node to be tail
			this.head.prev= newNode;// sets the node behind tail to be new node
			this.head= newNode;// sets tail pointer to new node
			size++;// increments size
		}
		//Add a square to the list. Newly added squares
		//should be at the back end of the list.
		
		//O(1)
		
		//throw IllegalArgumentException for invalid sqares
		
		//< YOUR_CODE_HERE >
	}

	/**
	 * WORKING removal method.
	 * @param n removes node matching n from linked list
	 */
	private void remove(Node n)
	{
		if(n.equals(null))
		{
			return;
		}
		if(tail==null)
		{
			return;
		}
		if(head == null)
		{
			return;
		}


		if(n.equals(this.head))
		{
			if(size==1)
			{

				return;
			}
			head.next.prev=null;
			this.head=head.next;
			size--;
			return;
		}
		Node parse = this.head;
		for(int i = 0 ; i < size-1; i ++)
		{
			if(parse.equals(n))
			{
				parse.next.prev=parse.prev;
				parse.prev.next=parse.next;
				size--;

				return;
			}
			parse=parse.next;
		}
		if(n.equals(this.tail))
		{
			tail.prev.next=null;
			this.tail=tail.prev;
			size--;
			return;
		}
	}


	/**
	 * parses thru list, and if any nodes contain a square with invalid coords they are removed
	 * for some reason the parsing does not cover the head of the list, so manual case added in to check.
	 * @param x the x coordinate matching the nodes for removal
	 * @param y the y coordinate matching the nodes for removal
	 * @return true if any nodes are removed, false if not
	 */
	public boolean handleClick (int x, int y)
	{
		if(this.size==0)
		{
			return false;
		}
		boolean deletion=false;


		Node<Square> parse = getHead();
		while(parse.next!=null)//goes thru list
		{
			if(parse.data.contains(x, y))
			{
				this.remove(parse);
				deletion=true;
			}
			parse=parse.next;
		}
		if(this.tail.data.contains(x, y))
		{
			this.remove(this.tail);
			deletion = true;
		}



		//Deletes all squares from the list that contain the 
		//position (x,y). Returns true if any squares get
		//deleted and returns false otherwise.
		
		//Returns true if any squares were deleted.
		
		//O(n) where n is the size of the list of squares
		
		//< YOUR_CODE_HERE >
		
		return deletion;
	}

	@Override
	public String toString() {

		if(size == 0)
		{
			return "list is empty";
		}
		String s="size of list = "+size+" : ";
		if(size==1)
		{
			s= s+ head.data.toString();
			return s;
		}
		Node<Square> parse = this.head;


		while(parse.next!=null)
		{
			// System.out.println(parse.data);
			s = s +parse.data+" ";
			parse=parse.next;
		}
		return s+" "+ getTail().data;
	}

	/**
	 *  Gets an iterator for the list of squares.
	 *  Squares are returned in the order added.
	 *  
	 *  @return the iterator requested
	 */
	public Iterator<Square> elements() {
		//Note that this method uses your linked list!
		//so if the iterator doesn't work, that's on you...
		
		return new Iterator<Square>() {
			/**
			 *  The current node pointed to by the
			 *  iterator (containing the next value
			 *  to be returned).
			 */
			private Node<Square> current = getHead();
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Square next() {
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				Square ret = current.data;
				current = current.next;
				return ret;
			}
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {
				return (current != null);
			}
		};
	}

	/**
	 * if sq1.id is less than sq2.id (meaning sq1 is younger) , then return negative number,
	 * if sq2 is older , ( id is greater) , then return positive
	 * return of 0 is same age
	 */
	public void sortCreation() {

		Comparator<Square> comp = new Comparator<Square>()
		{
			//sq1.id = 0
			//sq2.id = 1
			public int compare(Square sq1, Square sq2) {
				// negative number means sq1 smaller than sq2
				// positive means sq1 bigger than sq2
				//if sq1.id > sq2.id , return 1
				// if sq1.id< sq2.id , return -1
				if(sq1.id()<sq2.id())
				{
					return -1;
				}
				if(sq1.id()>sq2.id())
				{
					return 1;
				}


				return 0;
			}
		};

		//create a pair of nodes to pass into the sort function
		Node<Square> tmp = this.tail.prev;
		ThreeTenLinkedList.NodePair<Square> pair = new ThreeTenLinkedList.NodePair<>(this.head, this.tail);
		Node<Square> parse = tmp;
		while(parse.prev!=null)
		{
			if(pair.GetSize()==this.size)
			{
				break;
			}
			pair.add(parse);
			parse=parse.prev;
		}
		pair.head.prev=null;

		//call the sort function with the comparator
		ThreeTenLinkedList.NodePair<Square> ret = ThreeTenLinkedList.sort(pair, comp);


		//this is for PART 5 of the project... don't try to do this
		//before you complete ThreeTenLinkedList.sort()!
		//< YOUR_CODE_HERE >

		this.tail = ret.tail;

		this.head = ret.head;



		//Sorts the squares in the window by their creation time
		//(lower ids were created first). This should use your
		//ThreeTenLinkedList.sort() method you write in Part 5,
		//so don't do this until you have (a) read part 5,
		//(b) looked at the example in WindowStack, and (c) are 
		//sure you understand comparators.
		
		//O(n^2)
		
		//< YOUR_CODE_HERE >
	}

	/**
	 * sorts the squarelist in place by location using an in method created comparator. uses sorting algorithim in three
	 * tenlinkedlist.
	 */
	public void sortLoc() {

		Comparator<Square> comp = new Comparator<Square>()
		{

			public int compare(Square sq1, Square sq2) {

				if((sq1.getUpperLeftX() == sq2.getUpperLeftX())&& (sq1.getUpperLeftY()== sq2.getUpperLeftY()))
				{
					return 0;
				}
				// negative number means sq1 smaller than sq2
				// positive means sq1 bigger than sq2
				if (sq2.getUpperLeftX() < sq1.getUpperLeftX())
				{
					return 1;
				}
				else  if (sq2.getUpperLeftX() > sq1.getUpperLeftX())
				{
					return -1;
				}
				else // tiebreaker
				{
					if (sq1.getUpperLeftY() < sq2.getUpperLeftY())
					{
						return -1;
					}

					else
					{
						return 1;
					}
				}
			}
		};
		Node<Square> tmp = this.tail.prev;
		ThreeTenLinkedList.NodePair<Square> pair = new ThreeTenLinkedList.NodePair<>(this.head, this.tail);
		Node<Square> parse = tmp;
		while(parse.prev !=null)
		{
			if(pair.GetSize()==this.size)
			{
				break;
			}
			pair.add(parse);
			parse=parse.prev;
		}
		pair.head.prev=null;
		//call the sort function with the comparator
		ThreeTenLinkedList.NodePair<Square> ret = ThreeTenLinkedList.sort(pair, comp);


		//this is for PART 5 of the project... don't try to do this
		//before you complete ThreeTenLinkedList.sort()!
		//< YOUR_CODE_HERE >

		tail = ret.tail;

		head = ret.head;
		//Sorts the squares in the window by their location
		//in the window. Same rules as sorting the windows
		//in WindowStack. This should use your
		//ThreeTenLinkedList.sort() method you write in Part 5,
		//so don't do this until you have (a) read part 5,
		//(b) looked at the example in WindowStack, and (c) are 
		//sure you understand comparators
		
		//O(n^2)
		
		//< YOUR_CODE_HERE >
	}

	/**
	 * testing method for squarelist.
	 * @param args args to be passed to main when ran in console
	 */
	public static void main(String[] args) {
		Square sq1 = new Square(10,10,5, Color.red);
		Square sq2 = new Square(9,9,4,Color.blue);
		Square sq3 = new Square(8,8,3,Color.black);
		Square sq4 = new Square(7,7,2,Color.yellow);
		SquareList s1 = new SquareList();
		s1.add(sq1);
		s1.add(sq4);
		s1.add(sq3);
		s1.add(sq2);



		System.out.println(s1);
		s1.sortLoc();
		System.out.println(s1);



	}
}

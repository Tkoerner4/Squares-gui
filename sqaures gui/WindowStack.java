
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.awt.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 *  A stack of windows within the window.
 *  
 *  <p>Adapterion of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated Fall
 *  2022 by K. Raven Russell.</p>
 */
public class WindowStack {
	//You'll need some instance variables probably...
	//< YOUR_CODE_HERE >
	/**
	 * pointer to the window that was last selected.
	 */
	private Node<Window> lastSelected;
	/**
	 * number of windows in stack.
	 */
	private int size=0;
	/**
	 * end node placeholder.
	 */
	Node<Window> tail;
	/**
	 * head node placeholder.
	 */
	Node<Window> head;

	/**
	 * creates a new windowstack with no tail or head nodes yet because no items in.
	 */
	public WindowStack() {
		//Any initialization code you need.
		//this.tail = new Node<>();// creates a dummy node for tail
		//this.head = new Node<>(); // creates a dummy node for head
		//head.next=tail;// sets the next of head to tail
		//tail.prev=head;//and sets prev of tail to head
		size=0; // sets size to 2 for dumy head and tail nodes
		//O(1)
		
		//< YOUR_CODE_HERE >
	}

	/**
	 * getter method for head of linkedlist.
	 * @return the node at the head of the stack
	 */
	public Node<Window> getHead() {
		//Returns the head (top) of the stack of windows.
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
		

	}

	/**
	 * getter method for last node of windowstack object.
	 * @return the tail node of windowstack
	 */
	public Node<Window> getTail() {
		//Returns the tail (bottom) of the stack of windows.
		if(size==0)
		{
			return null;
		}
		return this.tail;
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

	/**WORKING
	 * returns amound of windows in stack.
	 * @return size var of windowstack
	 */
	public int numWindows() {
		//Gets the number of windows in the stack.
		
		//O(1)
		
		return this.size;
	}

	/**
	 * method to add a node to the end of the list ( tail).
	 * @param r the data to be placed in the node to be added to the end of the linkedlist
	 * @throws IllegalArgumentException if a null objecg is trying to be added to the node
	 */
	public void add(Window r) throws IllegalArgumentException
	{
		if(r.equals(null))
		{
			throw new IllegalArgumentException();
		}
		//Add a window at the top of the stack.
		Node<Window> newNode = new Node<Window>(r);// new node to be added in

		// if theres no items in stack currently
		if(size==0) {
			this.head= newNode;
			this.tail= newNode;
			// next and prev pointers will not be updated because theres just one node
			size++;// increment size
		}
		else // since the size is not 0 , meaning the list has entries
		{
			newNode.next=this.head;

			this.head.prev= newNode;//saves a copy of head node
			this.head= newNode;

			size++;
		}

		//O(1)
		
		//throw IllegalArgumentException for invalid windows
		
		//Note: the "top" of the stack should
		//be the head of your linked list.
		
		//< YOUR_CODE_HERE >
	}

	/**
	 * HELPER METHOD REMOVE WORKING.
	 * @param n node to be removed, works for middle and end and beginning
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
	* method for handling clicks on the window.
	* @param x the x coordinate where the mouse is clicked
	* @param y the y coordinate where the mouse is clicked
	* @param leftClick boolean for if the mouse is leftclicked or not,
	* @return true if the leftclick was on a window, then handleclick is passed to the window,
	*if not then a new window is created. a rightclick is done on a window, then that window is deleted
	*/
	public boolean handleClick (int x, int y, boolean leftClick)
	{
		//System.out.println("handleclick, sq count = " +this.head.data.getSquares().numSquares());








		//The mouse has been clicked at position (x,y).
		//Left clicks are move windows to the top of the
		//stack or pass the click on to the window at the
		//top. Right clicks remove windows.



		//If none of the windows on the stack were clicked
		//on, just return.


		//For a left click:
		//If the window is not at the top of the stack,
		//move it to the top of the stack without
		//disturbing the order of the other windows.
		//Mark this window as the "selected" window (and
		//ensure the previous selected window is no longer
		//selected).
		if(leftClick==true)
		{
			//If the window is at the top of the stack already,
			//ask the window to handle a click-event (using the
			//Window's handleClick() method).
			if (head.data.contains(x, y))
			{
				if(head.data.getSelected()==false)
				{
					head.data.setSelected(true);
					head.next.data.setSelected(false);
				}
				head.data.handleClick(x, y);

				return true;
			}

			Window temp;

			Node<Window> parse = this.head; // sets parse node to be close to top of stack as possible
			for (int i = 0; i < size - 1; i++)
			{// goes thrulist
				if((parse.data.contains(x,y))&&(parse.data.getSelected()==false)) // upon coming to first window at coordinates
				{

					temp = parse.data;
					Node<Window> w = new Node<Window>(parse.data);

					this.remove(parse);
					this.add(temp);

					this.head.next.data.setSelected(false);
					this.head.data.setSelected(true);

					return true;

				}// if window examind is not at coords

				parse = parse.next;
			}


			if(this.tail.data.contains(x, y))
			{

				Window w =  this.tail.data;
				this.remove(this.tail);
				this.add(w);
				this.head.next.data.setSelected(false);
				this.head.data.setSelected(true);
				//this.head.data.handleClick(x, y);
				return true;
			}

			if(size>=2)
			{
				this.head.next.data.setSelected(false);
			}
			if(size==1)
			{
				this.head.data.setSelected(false);
			}

			return false;// if no window at location is found



		}
		else	//For a right click:

		//Remove the window from the stack completely. The
		//window at the top of the stack should be the
		//selected window.
		{
			if(this.size==1)
			{// if theres only one window left
				if(this.tail.data.contains(x, y))// and the click is at that windows location
				{

				;

					//this.remove(this.tail);
					this.size=0;

					return true;
				}
			}
			if(this.tail.data.contains(x, y))// if location where the mouse is clicked is the tail(first item added)
			{
				this.remove(this.tail);
				return true;
			}
			Node<Window> parse  = this.head;
			while(parse.next !=null)
			{
				if(parse.data.contains(x, y))
				{// starting from the top of the list , iterates thru , and first one found matching the coords is removed
					this.remove(parse);
					return true;
					//break;// if its found then for loop exited
				}
				parse = parse.next;
			}




		}




		//Returns true if the click was handled, false
		//if no window was found.

		//O(n) where n is the number of windows in the stack


		//Details:

		//Find the top-most window on the stack (if any)
		//that contains the given coordinate.

		
		
		//Hint #1: This would be a great time to use helper
		//methods! If you just write one giant method...
		//it'll be much harder to debug...
		
		//Hint #2: Make sure to use the methods you wrote
		//in the Window class. Don't write those again!

		
		//< YOUR_CODE_HERE >
		
		//return deletion; //dummy return, replace this!
		return false;
	}

	/**
	 *  Gets an iterator for the stack of windows.
	 *  Windows are returned from bottom to top.
	 *  
	 *  @return the iterator requested
	 */
	@Override
	public String toString() {
		if(size == 0)
		{
			return "list is empty";
		}
		String s= " ";
		if(size==1)
		{
			s= s+ head.data.toString();
			return s;
		}
		Node<Window> parse = this.head;
		for(int i =0; i < size-1;i++)
		{

			s=s+parse.data.toString()+" ";
			parse=parse.next;

		}
		return "tail = " + s+" "+ this.tail.data+"\n Size = "+this.size+"";
	}
	/**
	 *  Gets an iterator for the stack of windows.
	 *  Windows are returned from bottom to top.
	 *
	 *  @return the iterator requested
	 */
	public Iterator<Window> windows()
	{
		//Note that this method uses your linked list!
		//so if the iterator doesn't work, that's on you...

		return new Iterator<Window>()
		{
			/**
			 *  The current node pointed to by the
			 *  iterator (containing the next value
			 *  to be returned).
			 */

			private Node<Window> current = getTail();
	/**
	* {@inheritDoc}
	*/

	@Override

			public Window next() {
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				Window ret = current.data;
				current = current.prev;
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
	 * using the threetenlinkedlist sort function, uses an in method created comparator to compare each window by size,
	 * and then returned a sorted by size list.
	 */
	public void sortSize() {
		//Sorts the windows in the stack by their area (length x width).
		//MOST of this is done for you, but you still need to assign
		//the returned head and tail back.
		
		//unselect the top window
		this.getHead().data.setSelected(false);
		
		//create a way to compare windows by area
		Comparator<Window> comp = new Comparator<Window>()
		{
			public int compare(Window w1, Window w2) {
				// negative number means w1 smaller than w2
				// positive means w1 bigger than w2
				int size1 = w1.getHeight()*w1.getWidth();
				int size2 = w2.getHeight()*w2.getWidth();
				if(size1>size2)
				{
					return 1;
				}
				if(size2>size1)
				{
					return -1;
				}
				return 0;
			}
		};
		
		//create a pair of nodes to pass into the sort function
		//Node<Window> new_head = new Node<Window>(this.head.data);
		//Node<Window> new_tail = new Node<Window>(this.tail.data);
		Node<Window> tmp = this.tail.prev;
		ThreeTenLinkedList.NodePair<Window> pair = new ThreeTenLinkedList.NodePair<>(this.head,this.tail);

		Node<Window> parse = tmp;
		while(parse.prev != null)
		{
			if(pair.GetSize()==this.size)
			{
				break;
			}
			pair.add(parse);
			parse=parse.prev;
		}
		pair.head.prev=null;
		//while(parse.prev )

		//call the sort function with the comparator
		//System.out.println(ThreeTenLinkedList.isSorted(pair,comp));
		ThreeTenLinkedList.NodePair<Window> ret = ThreeTenLinkedList.sort(pair, comp);
		//System.out.println(ThreeTenLinkedList.isSorted(ret,comp));

		

		//this is for PART 5 of the project... don't try to do this
		//before you complete ThreeTenLinkedList.sort()!
		//< YOUR_CODE_HERE >


		this.head=ret.head;
		this.tail=ret.tail;



		
		//re-select the top of the stack
		this.getHead().data.setSelected(true);
	}

	/**
	 * sorts the windowstack by location , using an in method comparator. sorts the windowstack in place.
	 */
	public void sortLoc() {

		//Sorts the windows in the stack by their upper left
		//corner loction. Left things (bigger-X) are on top
		//of right things (smaller-X). Tie-breaker: lower
		//things (bigger-Y) top of  higher things (smaller-Y).

		//This should use your ThreeTenLinkedList.sort() method you
		//write in Part 5, so don't do this until you have (a) read
		//part 5, (b) looked at the example in sortSize() above, and
		//(c) are sure you understand comparators.

		//O(n^2)
		// IDENTICAL ien every to above function



		//
		// return (w1.getWidth()*w1.getHeight())-(w2.getWidth()*w2.getHeight());
		// with
		this.getHead().data.setSelected(false);
		Comparator<Window> comp = new Comparator<Window>()
		{
			public int compare(Window w1, Window w2)
			{
				// w1 smaller than w2, return -1
				// w1 bigger than w2 return 1
				if (w2.getUpperLeftX() < w1.getUpperLeftX())
				{
					return 1;
				}
				else  if (w2.getUpperLeftX() > w1.getUpperLeftX())
				{
					return -1;
				}
				else // tiebreaker
				{
					if (w1.getUpperLeftY() < w2.getUpperLeftY())
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

		Node<Window> tmp = this.tail.prev;
		ThreeTenLinkedList.NodePair<Window> pair = new ThreeTenLinkedList.NodePair<>(this.head,this.tail);

		Node<Window> parse = tmp;
		while(parse.prev != null)
		{
			if(pair.GetSize()==this.size)
			{
				break;
			}
			pair.add(parse);
			parse=parse.prev;
		}
		pair.head.prev=null;
		//while(parse.prev )

		//call the sort function with the comparator
		//System.out.println(ThreeTenLinkedList.isSorted(pair,comp));
		ThreeTenLinkedList.NodePair<Window> ret = ThreeTenLinkedList.sort(pair, comp);
		//System.out.println(ThreeTenLinkedList.isSorted(pair,comp));



		//this is for PART 5 of the project... don't try to do this
		//before you complete ThreeTenLinkedList.sort()!
		//< YOUR_CODE_HERE >


		this.head=ret.head;
		this.tail=ret.tail;




		//re-select the top of the stack
		this.getHead().data.setSelected(true);
	}

	/**
	 * main testing method for windowstack.
	 * @param args args given when called in terminal to be passed into main()
	 */
	public static void main(String[] args)
	{
		WindowStack test = new WindowStack();
		Window w1 = new Window(10,10,10,10, Color.red);
		Window w2 = new Window(12,12,12,12,Color.red);
		Window w3 = new Window(13,13,13,13,Color.white);
		Window w4 = new Window(14,14,14,14,Color.yellow);
		Window w5 = new Window(15,15,15,15,Color.yellow);


		test.add(w2);
		test.add(w4);
		test.add(w1);
		test.add(w3);
		test.add(w5);


		System.out.println(test);
		test.sortSize();

		System.out.println(test);





	}

}


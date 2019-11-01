package bigNums;

public class Digit 
{
	
	int number;	// data field
	Digit previous;	// reference to previous node
	Digit next;	// reference to next node

	// 	put your code for constructors and methods

	// 	Default Constructor
	Digit()
	{
		number = 0;
		next = null;
		previous = null;
	} 	// End Default Constructor
	
	// 	Integer only Constructor
	Digit(int dig)
	{
		number = dig;
		next = null;
		previous = null;
	}	// End Integer only Constructor
	
	
	//	this method causes trouble 
	//	instead of " iter = new Digit(head); ", use, " iter=new Digit(); iter=head; "
	
	//	Copy Constructor
	Digit(final Digit digit)
	{
	
		this.next = digit.next;
		this.previous=digit.previous;
		this.number = digit.number;
	}	// End Copy Constructor
	
	
	
	//	ignore this for now
	//	Constructor used when only the data and the next reference is provided
	Digit(final int integer, final Digit nextPlace)
	{
		this.number=integer;
		this.next=nextPlace;
		this.previous=nextPlace.previous.previous;
	}	// End Constructor
	
	//	Constructor used when all information is provided
	Digit(final int incomingNum, final Digit incomingNext, final Digit incomingPrev)
	{
		this.number = incomingNum;
		this.next = incomingNext;
		this.previous = incomingPrev;
	}	// End Constructor

}	// End Digit Class

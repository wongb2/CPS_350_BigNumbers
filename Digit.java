package bigNums;

public class Digit {
	
	int number;    // data field
	Digit previous;
	Digit next;

	// put your code for constructors and methods

	Digit()
	{
		number = 0;
		next = null;
		previous = null;
	}
	
	Digit(int dig)
	{
		number = dig;
		next = null;
		previous = null;
	}
	
	
	//this method causes trouble 
	//instead of " iter = new Digit(head); ", use, " iter=new Digit(); iter=head; "
	Digit(final Digit digit)
	{
	
		this.next = digit.next;
		this.previous=digit.previous;
		this.number = digit.number;
	}
	
	
	
	//ignore this for now
	Digit(final int integer, final Digit nextPlace)
	{
		this.number=integer;
		this.next=nextPlace;
		this.previous=nextPlace.previous.previous;
	}
	
	Digit(final int incomingNum, final Digit incomingNext, final Digit incomingPrev)
	{
		this.number = incomingNum;
		this.next = incomingNext;
		this.previous = incomingPrev;
	}

}

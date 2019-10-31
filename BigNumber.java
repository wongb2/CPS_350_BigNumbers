package bigNums;
import java.util.regex.Pattern; //this allows you to split strings

public class BigNumber {
	Digit head;  // reference to the head node
	Digit tail;  // reference to the tail node
	
	final int BASE_10 = 10;

	public void display_littleEnd()
	{
		System.out.println("\n");
		
		Digit iter = new Digit(); iter = this.head;
		
		for(;iter!=tail;iter=iter.next)
			System.out.print(iter.number);
		
			System.out.println(tail.number);
		
	
	}
	
	public void display_bigEnd()
	{	
		System.out.println("\n");
		
		Digit iter = new Digit(); iter=this.tail;
		
		for(;iter!=head;iter=iter.previous)
			System.out.print(iter.number);
		
			System.out.print(head.number);	
	}

	public void simplify()
	{
		//this method gets rid of leading zeros
		while(this.tail.number==0 && this.tail!=this.head)
		{
			this.tail=this.tail.previous;
		}
	}
	public BigNumber(final String strNum)
	{
	    // string number (assumed to be correct) to a doubly linked list

		
		// WE WILL USE LITTLE ENDIAN NOTATION TO MAKE ADDITION / MULT EASIER. THUS, "123" is 3-2-1
		// since you add the least significant digits first, then carry
		
		//create an array of your numbers
		String[] nums = strNum.split("");
		int max_pos = strNum.length()-1; //minus one, since array starts at zero
		
		//create the head node
		//From Devin: Shouldn't this be either 1 or negative 1 (or maybe 0 or 1), regardless, so we can differentiate between
		//negative and positive BigNumbers? I thought he mentioned that in class. In which case, this would need to check 
		//whether the first character in the string (not the one at max_pos) is a "-" or if it's a number, and define the 
		//"number" data field of the head accordingly. (Reason 1 why I want to work on this in person lol).
		
		Digit n = new Digit(Integer.parseInt(nums[max_pos]));
		head = n;
		n.previous=head;
		
		Digit iter = new Digit();
		iter=n;

		//count from the bottom of the array up, add the digits to linked list
		 //minus one, since head already has the last digit
		for(int i=max_pos-1; i>=0; i--)
		{
			//create digit with proper value
			Digit a = new Digit(Integer.parseInt(nums[i]));
			//situate it into chain
			a.next=null;
			//is this statement necessary if the constructor already sets a.next to null?
			a.previous=iter;
			iter.next=a;
			
			//put the tail at the end
			tail=a;	
			//increment the iterator
			iter=iter.next; 
		}
		
		
	}

	  // copy constructor, notice that you need to create a new list which represents
	  // the same number as num
	public BigNumber(final BigNumber num)
	{
		//create the first node in the copy list
		Digit newNum = new Digit(num.head.number);
		this.head = newNum;
		
		//want to ask about this as well
		Digit lead = new Digit(num.head.next);
		Digit follow = new Digit(); follow=this.head;
		
		for(;lead!=null;lead=lead.next)
		{
			Digit n = new Digit(lead.number);
			follow.next=n;
			n.previous=follow;
			tail=n;
			
			follow=follow.next;
		}

			
	}

	  // addition assignment: adding the given number to the current number, +=
	public void add_assign(final BigNumber b)
	{
		Digit sum = new Digit(this.head);
	    
	    // variables needed for loop
	    Digit iter1 = new Digit(this.head);
	    Digit iter2 = new Digit(b.head);
	    
	    
	    int carry = 0;
	    int value = 0;
	    
	    while(iter1!=null || iter2!=null || carry!=0)
	    {
	    	
	    	    	
	    	//compute the value and carry
	    	// there are four cases, which have to do with the fact that our big nums might not be
	    	// the same length. additionally, it's possible to overflow
	    	if(iter1!=null && iter2!=null)
	    		value = iter1.number + iter2.number + carry;
	    	else if(iter1!=null && iter2==null)
	    		value = iter1.number + carry;
	    	else if(iter1==null && iter2!=null)
	    		value = iter2.number + carry;
	    	else
	    		value = carry;
	    	
	    	//compute the value and carry in digit form (both less than 9)
	    	carry = value / BASE_10; // BASE_10 = 9
	    	value = value % BASE_10;
	    	
	    	//assign value
	    	sum.number=value;	
	    	
	    	//increment
	    	if(iter1!=null)
	    		iter1=iter1.next;
	    	if(iter2!=null)
	    		iter2=iter2.next;
	    	
	    	if( iter1!=null || iter2!=null || carry!=0 )
	    	{
	    		if(sum.next==null)
	    		{
	    			// create a new node and link it up
		    		Digit newDigit = new Digit();
		    		sum.next = newDigit;
		    		newDigit.previous=sum;
		    		this.tail = newDigit;
	    		}
		
	    	    sum=sum.next;
	    	    
	    	  //place the correct digit in the sum
	    	    sum.previous.number=value;
	    	}
	    	
	    }
	    
	    
	}

	  // addition: return a + b
	public static BigNumber add(final BigNumber a, final BigNumber b)
	{
		BigNumber c = new BigNumber(a);
	    c.add_assign(b);    // calling the method for +=
	    return c;
	}

	  // Please write your code for the other operations:
	  // -=, -, *=, *, /, /=, %, %=, in the same way.
	
	public void sub_assign(final BigNumber b)
	{
		Digit sum = new Digit(this.head);
		Digit iter1 = new Digit(this.head);
		Digit iter2 = new Digit(b.head);
		

		int value = 0;
		int carry = 0;
		
		while(iter1!=null || iter2!=null)
		{
			//useful numbers
			
			
			//assign value
			if(iter1!=null && iter2!=null)
			{
				int a_val = iter1.number;
				int b_val = iter2.number;
				value = a_val - b_val + carry;
			}
			if(iter1!=null && iter2==null)
			{
				int a_val = iter1.number;
				value = a_val - 0 + carry;
			}	
			if(iter1==null && iter2!=null)
			{
				int b_val = iter2.number;
				value = 0 - b_val + carry;
			}	
			else if (iter1==null && iter2==null)
				value = carry;
			
			if(iter1!=null)
				iter1=iter1.next;
			if(iter2!=null)
				iter2=iter2.next;
				
			
			if(value<0)
			{
				if(iter1!=null || iter2!=null)
				{
					value = 10 + value;
					carry=-1; 	
				}
			}
			
			else
				carry=0;
			
			sum.number=value;
	    	if( iter1!=null || iter2!=null )
	    	{
	    		if(sum.next==null)
	    		{
	    			// create a new node and link it up
		    		Digit newDigit = new Digit();
		    		sum.next = newDigit;
		    		newDigit.previous=sum;
		    		this.tail = newDigit;
	    		}
		
	    	    sum=sum.next;
	    	    
	    	  //place the correct digit in the sum
	    	    sum.previous.number=value;
	    	}
			
			
			
			
		}
		
		
		
	}
	
	public static BigNumber sub(final BigNumber a, final BigNumber b)
	{
		

		//boolean compare = first_smaller_than_second(a,b);
		/*
		if(compare==true)
		{
			BigNumber d = new BigNumber(b);
			d.sub_assign(a);
			d.simplify();
			return d;
		}
		*/
		
		BigNumber c = new BigNumber(a);
		c.sub_assign(b);
		c.simplify();
		return c;
	}
	
	public boolean first_smaller_than_second(final BigNumber a, final BigNumber b)
	{
	
		//if b > a, return true
		
		
		return false;
	}

}

package bigNums;

import java.util.regex.Pattern;

public class RunBigNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//just putting this note here because I think you know something I don't about why we'd use the new String() constructor
		//here instead of making it a string literal and I don't want to forget to ask.
		String s = new String("250");
		BigNumber number = new BigNumber(s);
		//BigNumber copy = new BigNumber(number);
		
		BigNumber diff = new BigNumber("260");
		
		BigNumber outcome = new BigNumber("11");
		//More questions here I want to ask about.
		outcome = BigNumber.sub(number, diff);
		
		outcome.display_bigEnd();
		
		
		
		//number.display_bigEnd();
		//copy.display_bigEnd();
		
	

	}
	
	


}

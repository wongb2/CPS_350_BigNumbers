package bigNums;

import java.util.regex.Pattern;

public class RunBigNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String s = new String("250");
		BigNumber number = new BigNumber(s);
		//BigNumber copy = new BigNumber(number);
		
		BigNumber diff = new BigNumber("260");
		
		BigNumber outcome = new BigNumber("11");
		outcome = BigNumber.sub(number, diff);
		
		outcome.display_bigEnd();
		
		
		
		//number.display_bigEnd();
		//copy.display_bigEnd();
		
	

	}
	
	


}

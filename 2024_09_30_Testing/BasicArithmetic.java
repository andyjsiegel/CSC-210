/**
 * class BasicArithmetic
 * file BasicArithmetic.java
 * 
 * @author Adriana Picoral (adrianaps@arizona.edu) Fall 2024
 *
 *         This class does basic arithmetic operations with 
 *         two integers:
 *         summation, subtraction, multiplication, and
 *         division
 *         
 *         factory method to construct object:
 *         BasicArithmetic myObject = BasicArithmetic.createOperation(int, int, string);
 *         third argument can be "+", "-", "*", or "/"
 *         
 *         call performOperation() to perform actual operation:
 *         myObeject.performOperation()
 * 
 */
public class BasicArithmetic {
	
	private int number1, number2;
	private String operator;
	
	// Generic constructor. Private so use factory method createOperation()
	private BasicArithmetic(int n1, int n2, String o) {
		number1 = n1;
		number2 = n2;
		if (o.equals("+") || o.equals("-") || 
				o.equals("*") || o.equals("/")) operator = o;
		else operator = null;
		
	}
	
	public static BasicArithmetic createOperation(int n1, int n2, String o) {
		return new BasicArithmetic(n1, n2, o);
	}
	
	// performs the actual calculation
	public int performOperation() {
		if (operator == "+") return number1 + number2;
		if (operator == "-") return number1 - number2;
		if (operator == "*") return number1 * number2;
		if (operator == "/") return number1 / number2;
		return 0;
	}

}

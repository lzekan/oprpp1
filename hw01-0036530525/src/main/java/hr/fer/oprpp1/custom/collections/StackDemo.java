package hr.fer.oprpp1.custom.collections;

public class StackDemo {
	
	public static void main(String[] args) {
		
		String[] expression = args[0].split(" ");
		ObjectStack os = new ObjectStack();
		
		for(int i = 0; i < expression.length; i++) {
			
			try {
				
				int number = Integer.parseInt(expression[i]);
				os.push(number); 
				
			} catch(Exception e) {
				
				Integer no1 = (Integer) os.pop();
				Integer no2 = (Integer) os.pop();
				
				if(expression[i].equals("+")) {
					os.push(no2 + no1);
				} else if(expression[i].equals("-")) {
					os.push(no2 - no1);
				} else if(expression[i].equals("*")) {
					os.push(no2 * no1);
				} else if(expression[i].equals("/")) {
					os.push(no2 / no1);
				} else if(expression[i].equals("%")) {
					os.push(no2 % no1);
				}
			}
			
		}
		
		if(os.size() != 1) {
			throw new RuntimeException();
		} else {
			System.out.println(os.pop());
		}
		
		
		
	}

}

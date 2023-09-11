package hr.fer.oprpp1.hw02.prob1;

public class Main {

	public static void main(String[] args) {
		
		Lexer lexer = new Lexer("Janko 3# Ivana26\\\\a 463abc#zzz");
		
		while(lexer.nextToken().getValue() != null) {
			System.out.println(lexer.getToken().toString());
		}

		System.out.println(lexer.getToken().toString());
	}

}

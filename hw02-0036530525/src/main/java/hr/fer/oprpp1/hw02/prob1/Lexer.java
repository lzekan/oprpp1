package hr.fer.oprpp1.hw02.prob1;

public class Lexer {

	private char[] data;
	private Token token;
	private int currentIndex;
	private LexerState state;
	
	public Lexer(String text) {
		
		this.data = text.toCharArray();
		this.currentIndex = 0;
		this.token = null;
		this.state = LexerState.BASIC;
		
	}
	
	public void setState(LexerState state) {
		
		if(state == null) {
			throw new NullPointerException();
		}
		
		this.state = state;
	}
	
	public Token nextToken() { 
		//System.out.println("next");
		
		if(currentIndex > data.length) {
			throw new RuntimeException("Izasa si vanka.");
		}
		
		if(data.length == 0 || currentIndex == data.length) {
			currentIndex++;
			token = new Token(TokenType.EOF, null);
			return getToken();
		}
		
		
		token = null;
		
		
		if(data[currentIndex] == ' ') {
			currentIndex++;
			return nextToken();
		}
		
		if(data[currentIndex] == '\r' || data[currentIndex] == '\n' || data[currentIndex] == '\t') {
			currentIndex++;
			return nextToken();
		}
		
		if(this.state == LexerState.EXTENDED) {
			
			String value = String.valueOf(data[currentIndex++]);
			 
			while(data[currentIndex] != ' ') {
				
				if(data[currentIndex] == '#') {
					
					this.setState(LexerState.BASIC);
					break;
					
				}
				
				value += data[currentIndex++];
			}
			
			token = new Token(TokenType.WORD, value);
			return getToken();
			
		}
		
		
		if(data[currentIndex] == '\\') {
			
			currentIndex++;
			
			if(currentIndex == data.length) {
				throw new RuntimeException("Triba bi bit LexerException prvi if.");
			}
			
			if(data[currentIndex] == 'r' || data[currentIndex] == 'n' || data[currentIndex] == 't') {
				currentIndex++;
				//System.out.println("odi");
				return nextToken();
			}

			
			if(Character.isDigit(data[currentIndex]) || (data[currentIndex] == '\\')) {
				token = new Token(TokenType.WORD, String.valueOf(data[currentIndex]));
				currentIndex++;
			} else {
				throw new RuntimeException("Triba bi bit LexerException drugi if.");
			}

			
			while(Character.isLetter(data[currentIndex]) || (data[currentIndex] == '\\')){
				
				if(data[currentIndex] == '\\') {
					
					if(Character.isDigit(data[currentIndex]) || (data[currentIndex] == '\\')) {
						
						currentIndex++;
						
						if(currentIndex == data.length) {
							return getToken();
						}
						
					} else {
						
						throw new RuntimeException("Ne moze to nakon backslasha.");
						
					}
				
				}
				
				token.setValue(token.getValue().toString() + data[currentIndex++]);
			}
			
			return getToken();
		}
		
		else if(Character.isLetter(data[currentIndex])) {
			
			token = new Token(TokenType.WORD, data[currentIndex++]);
			
			while(Character.isLetter(data[currentIndex]) || (data[currentIndex] == '\\')){
				
				if(data[currentIndex] == '\\') {
					
					if(Character.isDigit(data[currentIndex]) || (data[currentIndex] == '\\')) {
						
						currentIndex++;
						
						
					} else {
						
						throw new RuntimeException("Ne moze to nakon backslasha.");
						
					}
				
				}
				
				token.setValue(token.getValue().toString() + data[currentIndex++]);
				
				if(currentIndex == data.length) {
					break;
				}
			
			}
		
			return getToken();
			
		} else if(Character.isDigit(data[currentIndex])) {
			
			long value = Character.getNumericValue(data[currentIndex++]);
			token = new Token(TokenType.NUMBER, value);
			
			if(currentIndex == data.length) {
				return getToken();
			}
			
			while(Character.isDigit(data[currentIndex])) {
				try {
					token.setValue(Long.valueOf(token.getValue().toString() + data[currentIndex++]));
					
					if(currentIndex == data.length) {
						return getToken();
					}

					
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage()) ;
				}

			}
			
			return getToken();
			
		} else {
			
			token = new Token(TokenType.SYMBOL, data[currentIndex++]);
			
			if(token.getValue().toString().equals('#')) {
				System.out.print("odiodi");
				this.setState(LexerState.EXTENDED);
			}
			
			return getToken();
		
		} 
		

	}
	
	public Token getToken() {
		return this.token;
	}
	
}

package hr.fer.oprpp1.hw02.prob1;

public class Token {
	
	private TokenType type;
	private Object value;
	
	public Token(TokenType type, Object value) {
		this.type = type;
		this.value = value;
	}
	
	public Object getValue() {
		return this.value;
	}
	
	public TokenType getType() {
		return this.type;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public void setType(TokenType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "(" + this.getType() + ", " + this.getValue() + ")";
	}
}

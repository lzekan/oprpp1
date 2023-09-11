package hr.fer.oprpp1.hw04.db;

public class ConditionalExpression {
	
	private IFieldValueGetter fieldGetter;
	private String literal;
	private IComparisonOperator operator;
	
	public ConditionalExpression(IFieldValueGetter value, String literal, IComparisonOperator operator) {
		this.fieldGetter = value;
		this.literal = literal;
		this.operator = operator;
	}
	
	public IFieldValueGetter getFieldGetter() {
		return this.fieldGetter;
	}
	
	public String getStringLiteral() {
		return this.literal;
	}
	
	public IComparisonOperator getComparisonOperator() {
		return this.operator;
	}
	
	public String toString() {
		return fieldGetter + " " + operator + " " + literal;
	}

}

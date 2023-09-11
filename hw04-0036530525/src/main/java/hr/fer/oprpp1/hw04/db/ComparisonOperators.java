package hr.fer.oprpp1.hw04.db;

public enum ComparisonOperators implements IComparisonOperator{
	LESS,
	LESS_OR_EQUALS,
	GREATER,
	GREATER_OR_EQUALS,
	EQUALS,
	NOT_EQUALS,
	LIKE;

	@Override
	public boolean satisfied(String value1, String value2) {
		
		if(ComparisonOperators.this == ComparisonOperators.LESS) {
			return value1.compareTo(value2) < 0;
		} else if(ComparisonOperators.this == ComparisonOperators.LESS_OR_EQUALS) {
			return value1.compareTo(value2) <= 0;
		} else if(ComparisonOperators.this == ComparisonOperators.GREATER) {
			return value1.compareTo(value2) > 0;
		} else if(ComparisonOperators.this == ComparisonOperators.GREATER_OR_EQUALS) {
			return value1.compareTo(value2) >= 0;
		} else if(ComparisonOperators.this == ComparisonOperators.EQUALS) {
			return value1.compareTo(value2) == 0;
		} else if(ComparisonOperators.this == ComparisonOperators.NOT_EQUALS) {
			return value1.compareTo(value2) != 0;
		} else if(ComparisonOperators.this == ComparisonOperators.LIKE) {
			
			if(value2.startsWith("*")) {
				return value1.endsWith(value2.substring(1, value2.length()));
			} else if(value2.endsWith("*")){
				return value1.startsWith(value2.substring(0, value2.length() - 1));
			} else {
				int starIndex = value2.indexOf("*");
				return value1.startsWith(value2.substring(0, starIndex)) && 
						value1.endsWith(value2.substring(starIndex + 1, value2.length()));
			}
			
			
			
		} 
		
		
		
		
		return false;
		

	}
}

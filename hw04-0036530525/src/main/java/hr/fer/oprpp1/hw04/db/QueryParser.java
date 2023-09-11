package hr.fer.oprpp1.hw04.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryParser {
	
	private String line;
	
	public QueryParser(String line) {
		this.line = line.trim();
	}
	
	public boolean isDirectQuery() {
		
		if(line.startsWith("jmbag") && !line.contains(" and ")) {
			return true;
		}
		
		return false;
	}
	
	public String getQueriedJMBAG() {
		
		if(!isDirectQuery()) {
			throw new IllegalStateException();
		}
		
		int index = line.indexOf("jmbag") + 5;
		
		while(line.charAt(index) != '\"') {
			index++;
		}
		
		index++;
		StringBuilder sb = new StringBuilder();
		
		while(line.charAt(index) != '\"') {
			sb.append(line.charAt(index++));
		}
		
		return sb.toString();
	}

	public List<ConditionalExpression> getQuery(){
		
		List<String> listOfQueries = Arrays.asList(line.split(" and "));
		List<ConditionalExpression> listOfExpressions = new ArrayList<>();
		
		for(String query : listOfQueries) {
			
			System.out.println(query);
			
			IFieldValueGetter fieldGetter;
			if(query.contains("jmbag")) {
				fieldGetter = FieldValueGetters.JMBAG;
			} else if (query.contains("firstName")) {
				fieldGetter = FieldValueGetters.FIRST_NAME;
			} else {
				fieldGetter = FieldValueGetters.LAST_NAME;
			}
			
			String literal;
			StringBuilder sb = new StringBuilder();
			int index = query.indexOf('\"') + 1;
			while(query.charAt(index) != '\"') {
				sb.append(query.charAt(index++));
			}
			literal = sb.toString();
			
			IComparisonOperator operator;
			if(query.contains("<=")) {
				operator = ComparisonOperators.LESS_OR_EQUALS;
			} else if(query.contains(">=")) {
				operator = ComparisonOperators.GREATER_OR_EQUALS;
			} else if(query.contains("<>")) {
				operator = ComparisonOperators.NOT_EQUALS;
			} else if(query.contains("<")) {
				operator = ComparisonOperators.LESS;
			} else if(query.contains(">")) {
				operator = ComparisonOperators.GREATER;
			} else if(query.contains("=")) {
				operator = ComparisonOperators.EQUALS;
			} else {
				operator = ComparisonOperators.LIKE;
			}
			
			ConditionalExpression expression = new ConditionalExpression(
					fieldGetter, 
					literal,
					operator
			);
					
			listOfExpressions.add(expression);
			
		}
		
		return listOfExpressions;
		
	}
	
}

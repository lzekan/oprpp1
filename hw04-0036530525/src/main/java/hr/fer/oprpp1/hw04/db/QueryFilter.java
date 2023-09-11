package hr.fer.oprpp1.hw04.db;

import java.util.List;

public class QueryFilter implements IFilter{

	private List<ConditionalExpression> listOfExpressions;
	
	public QueryFilter(List<ConditionalExpression> list) {
		this.listOfExpressions = list;
	}

	@Override
	public boolean accepts(StudentRecord record) {
		
		for(ConditionalExpression expr : listOfExpressions) {
			
			boolean recordSatisfies = expr.getComparisonOperator().satisfied(  
					expr.getFieldGetter().get(record),  
					expr.getStringLiteral());   
			
			
			if(!recordSatisfies) {
				return false;
			} 
		}
		
		return true;
		


		
	}
	
	
	
}

package hr.fer.oprpp1.hw04.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws IOException {
		
		Path path = Paths.get("C:\\Users\\lukaz\\eclipse-workspace\\hw04-0036530525\\src\\main\\java\\hr\\fer\\oprpp1\\hw04\\db\\database.txt");
		List<String> list = Files.readAllLines(path);
		
		StudentDatabase db = new StudentDatabase(list);
//		
//		IFilter filter = (sr) -> sr.getLastName().contains("a"); 
//		
//		for(StudentRecord sr : db.filter(filter)) {
//			System.out.println(sr.toString());
//		}
//		
//		IComparisonOperator oper = ComparisonOperators.LIKE; 
//		System.out.println(oper.satisfied("Abbasdagdsfba", "A*fbaa"));
		
//		ConditionalExpression expr = new ConditionalExpression( 
//				FieldValueGetters.LAST_NAME,  
//				"*osnić",  
//				ComparisonOperators.LIKE );
//		
//		
//		StudentRecord record = db.getMap().get(3);
//		
//		boolean recordSatisfies = expr.getComparisonOperator().satisfied(  
//				expr.getFieldGetter().get(record),  // returns lastName from given record  
//				expr.getStringLiteral()             // returns "Bos*" 
//		);
//		
//		System.out.println(recordSatisfies);
		
		
//		QueryParser qp1 = new QueryParser(" jmbag       =\"0123456789\"    "); 
//		System.out.println("isDirectQuery(): " + qp1.isDirectQuery()); // true 
//		System.out.println("jmbag was: " + qp1.getQueriedJMBAG()); // 0123456789 
//		System.out.println("size: " + qp1.getQuery().size()); // 1
//		
//		QueryParser qp2 = new QueryParser("jmbag=\"0123456789\" and lastName>\"J\""); 
//		System.out.println("isDirectQuery(): " + qp2.isDirectQuery()); // false 
//		//System.out.println(qp2.getQueriedJMBAG()); // would throw! 
//		System.out.println("size: " + qp2.getQuery().size()); // 2
		
		Scanner sc = new Scanner(System.in);
		System.out.print("> query ");
		String query = sc.nextLine();
		
		while(!query.equals("exit")) {
			
			QueryParser qp = new QueryParser(query);
			if(qp.isDirectQuery()) {  
				StudentRecord r = db.forJMBAG(qp.getQueriedJMBAG()); 
				System.out.println(r);
			} else {
				
				 for(StudentRecord r : db.filter(new QueryFilter(qp.getQuery()))) {   
					 System.out.println(r);
				 }
			}
			
			
			
			
			System.out.print("> query ");
			query = sc.nextLine();

		}
		
		
		
	}

}

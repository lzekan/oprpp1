package hr.fer.oprpp1.custom.collections;

public class MainSH {

	public static void main(String[] args) {
		SimpleHashtable<String, Integer> examMarks = new SimpleHashtable<>(2);
		
		examMarks.put("Ivana", 2); 
		examMarks.put("Ante", 2); 
		examMarks.put("Jasna", 2); 
		examMarks.put("Kristina", 5); 
		examMarks.put("Ivana", 5); // overwrites old grade for Ivana
		
		Integer ivanaGrade = examMarks.get("Ivana"); 
		System.out.println("Ivana's exam grade is: " + ivanaGrade); // writes: 5
		
		Integer kristinaGrade = examMarks.get("Kristina"); 
		System.out.println("Kristina's exam grade is: " + kristinaGrade); // writes: 5
		
		System.out.println(examMarks.size());
		
		//examMarks.remove("Kristina");
//		System.out.println(examMarks.size());
//		
//		kristinaGrade = examMarks.get("Kristina"); 
//		System.out.println("Kristina's exam grade is: " + kristinaGrade); // writes: 5
//		
//		System.out.println(examMarks.containsKey("Kristina")); // writes: 5
//		
//		System.out.println(examMarks.toString());
//		System.out.println(examMarks.size());
//		
//		examMarks.clear();
		System.out.println(examMarks.toString());
//		System.out.println(examMarks.size());
//		
//		for(SimpleHashtable.TableEntry<String,Integer> pair : examMarks) {
//			System.out.printf("%s => %d%n", pair.getKey(), pair.getValue()); 
//		}

		for(SimpleHashtable.TableEntry<String,Integer> pair1 : examMarks) { 
			for(SimpleHashtable.TableEntry<String,Integer> pair2 : examMarks) { 
				System.out.printf( "(%s => %d) - (%s => %d)%n", pair1.getKey(), pair1.getValue(), pair2.getKey(), pair2.getValue() ); } }
		


	}

}

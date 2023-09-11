package hr.fer.oprpp1.custom.collections;

public class EGDemo4 {
	
	public static void main(String[] args) {
		
		Collection col = new ArrayIndexedCollection(); 
		
		col.add("Ivo"); 
		col.add("Ana"); 
		col.add("Jasna"); 
		
		ElementsGetter getter = col.createElementsGetter(); 
		getter.getNextElement(); 
		getter.processRemaining(System.out::println);
	}


}

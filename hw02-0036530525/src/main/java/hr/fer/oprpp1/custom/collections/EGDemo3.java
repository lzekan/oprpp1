package hr.fer.oprpp1.custom.collections;


public class EGDemo3 {
	
	public static void main(String[] args) {
		
		Collection col = new LinkedListIndexedCollection(); 
		
		col.add("Ivo"); 
		col.add("Ana"); 
		col.add("Jasna"); 
		
		ElementsGetter getter = col.createElementsGetter(); 
		System.out.println("Jedan element: " + getter.getNextElement()); 
		System.out.println("Jedan element: " + getter.getNextElement()); 
		
		col.clear(); 
		
		System.out.println("Jedan element: " + getter.getNextElement());
	}

}

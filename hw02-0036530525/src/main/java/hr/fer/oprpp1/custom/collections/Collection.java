package hr.fer.oprpp1.custom.collections;


public interface Collection {
	
	public default boolean isEmpty() {
		return this.size() == 0;
	}
	
	public abstract long getModificationCount();
	
	public abstract int size();
	
	public abstract void add(Object value);
	
	public abstract boolean contains(Object value);
	
	public abstract boolean remove(Object value);
	
	public abstract Object[] toArray();
	
	public default void forEach(Processor processor) {
		
		ElementsGetter eg = this.createElementsGetter();
		
		while(eg.hasNextElement()) {
			processor.process(eg.getNextElement());
		}
	}
	
	public default void addAll(Collection other) {

		
		class AddAllProcessor implements Processor{
			
			public void process(Object value) {				
				Object otherArray[] = ((Collection) value).toArray();	
				
				for(int i = 0; i < otherArray.length; i++){
					if(otherArray[i] != null) {
						add(otherArray[i]);
					}
					
				}
			}
			
		}
		
		
		Processor result = new AddAllProcessor();
		result.process(other);
		
	}
	
	public abstract void clear();
	
	public abstract ElementsGetter createElementsGetter();
	
	public abstract Object get(int index);
	
	public default void addAllSatisfying(Collection col, Tester tester) {
		
		ElementsGetter eg = col.createElementsGetter();
		
		while(eg.hasNextElement()) {
			Object nextElement = eg.getNextElement();
			if(tester.test(nextElement)) {
				add(nextElement);
			}
		}
		
	}
	
}

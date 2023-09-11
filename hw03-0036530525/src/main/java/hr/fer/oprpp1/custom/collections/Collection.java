package hr.fer.oprpp1.custom.collections;

public interface Collection<T> {
	
	public default boolean isEmpty() {
		return this.size() == 0;
	}
	
	public abstract long getModificationCount();
	
	public abstract int size();
	
	public abstract void add(T value);
	
	public abstract boolean contains(T value);
	
	public abstract boolean remove(T value);
	
	public abstract T[] toArray();
	
	public default void forEach(Processor<T> processor) {
		
		ElementsGetter<T> eg = this.createElementsGetter();
		
		while(eg.hasNextElement()) {
			processor.process(eg.getNextElement());
		}
	}
	
	public default void addAll(Collection<T> other) {

		
		class AddAllProcessor implements Processor<Collection<T>>{
			
			public void process(Collection<T> value) {

				T otherArray[] = value.toArray();	
				
				for(int i = 0; i < otherArray.length; i++){
					
					if(otherArray[i] != null) {
						add(otherArray[i]);
					}
					
				}
			}
			
		}
		
		
		Processor<Collection<T>> result = new AddAllProcessor();
		result.process(other);
		
	}
	
	public abstract void clear();
	
	public abstract ElementsGetter<T> createElementsGetter();
	
	public abstract T get(int index);
	
	public default void addAllSatisfying(Collection<T> col, Tester<T> tester) {
		
		ElementsGetter<T> eg = col.createElementsGetter();
		
		while(eg.hasNextElement()) {
			
			T nextElement = (T) eg.getNextElement();
			
			if(tester.test(nextElement)) {
				add(nextElement);
			}
		}
		
	}
	
}
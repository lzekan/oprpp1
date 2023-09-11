package hr.fer.oprpp1.custom.collections;

public class Collection {

	protected Collection() {
	
	}
	
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	public int size() {
		return 0;
	}
	
	public void add(Object value) {
		
	}
	
	public boolean contains(Object value) {
		return false;
	}
	
	public boolean remove(Object value) {
		return false;
	}
	
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}
	
	public void forEach(Processor processor) {
	
	}
	
	public void addAll(Collection other) {

		
		class AddAllProcessor extends Processor{
			
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
	
	public void clear() {
		
	}
	
	
	
}

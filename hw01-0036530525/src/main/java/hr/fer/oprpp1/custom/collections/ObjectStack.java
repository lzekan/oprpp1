package hr.fer.oprpp1.custom.collections;

public class ObjectStack {
	
	private ArrayIndexedCollection aic = new ArrayIndexedCollection();

	public boolean isEmpty() {
		return aic.isEmpty();
	}
	
	public int size() {
		return aic.size();
	}
	
	public void push(Object value) {
		aic.add(value);
	}
	
	public Object pop() {
		
		if(this.size() == 0) {
			throw new EmptyStackException("Stog je prazan.");
		}
		
		Object last = aic.get(aic.size() - 1);
		aic.remove(aic.size() - 1);
		return last;
		
	}
	
	public Object peek() {
		
		if(this.size() == 0) {
			throw new EmptyStackException("Stog je prazan.");
		}
		
		return aic.get(aic.size() - 1);

	}

	public void clear() {
		aic.clear();
	}
	
}

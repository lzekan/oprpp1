package hr.fer.oprpp1.custom.collections;

import java.util.ConcurrentModificationException;

public class ElementsGetter<T> {
	
	private Collection<T> col;
	private int position;
	private long savedModificationCount;
	
	public ElementsGetter(Collection<T> col) {
		this.col = col;
		this.position = 0;
		this.savedModificationCount = col.getModificationCount();
	}
	
	public boolean hasNextElement() {
		
		if(savedModificationCount != col.getModificationCount()) {
			throw new ConcurrentModificationException();
		}
			
		return this.position != col.size();
	}
	
	public T getNextElement() {
		
		if(savedModificationCount != col.getModificationCount()) {
			throw new ConcurrentModificationException();
		}

		return hasNextElement() ? col.get(position++) : null;
	}
	
	public void processRemaining(Processor<T> p) {
		
		for(int i = position; i < col.size(); i++) {
			p.process(col.get(i));
		}
	}

}
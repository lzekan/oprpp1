package hr.fer.oprpp1.custom.collections;

import java.util.ConcurrentModificationException;

public class ElementsGetter {
	
	private Collection col;
	private int position;
	private long savedModificationCount;
	
	public ElementsGetter(Collection col) {
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
	
	public Object getNextElement() {
		
		if(savedModificationCount != col.getModificationCount()) {
			throw new ConcurrentModificationException();
		}

		return hasNextElement() ? col.get(position++) : null;
	}
	
	public void processRemaining(Processor p) {
		
		for(int i = position; i < col.size(); i++) {
			p.process(col.get(i));
		}
	}

}

package hr.fer.oprpp1.custom.collections;

public class ArrayIndexedCollection<T> implements List<T>{
	
	private int size = 0;
	private T[] elements;
	private long modificationCount = 0;
	
	private static ElementsGetter eg;
	
	public ArrayIndexedCollection() {
		this(16);
	}
	
	public ArrayIndexedCollection(int initialCapacity) {
		
		if(initialCapacity < 1) {
			throw new IllegalArgumentException();
		}
		
		this.elements = (T[]) new Object[initialCapacity];
		
	}
	
	public ArrayIndexedCollection(Collection<T> other) {
		this(other, 16);
	}
	
	public ArrayIndexedCollection(Collection<T> other, int initialCapacity) {
		
		if(other.equals(null)) {
			throw new NullPointerException();
		}
		
		this.elements = (T[]) new Object[initialCapacity < other.size() ? other.size() : initialCapacity];
		this.addAll(other);
		
	}
	
	public long getModificationCount() {
		return this.modificationCount;
	}
	
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	@Override
	public void add(T value) {
		
		if(value == null) {
			throw new NullPointerException();
		}
		
		for(int i = 0; i < this.elements.length; i++) {
			if(this.elements[i] == null) {
				this.elements[i] = value;
				this.size++;	
				return;
			}
		}
		
		T tempElements[] = this.elements; 
		this.elements = (T[]) new Object[this.elements.length * 2];
	
		for(int i = 0; i < tempElements.length; i++) {
			this.elements[i] = tempElements[i];
		} 
		
		this.add(value);
		this.modificationCount++;
		
	}
	
	@Override
	public T get(int index) {
		
		if(index < 0 || index > this.size - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		return this.elements[index];
		
	}
	
	@Override
	public void clear() {
		for(int i = 0; i < this.elements.length; i++) {
			
			this.elements[i] = null;
		}
		
		this.size = 0;
		this.modificationCount++;
	}
	
	public void insert(T value, int position) {
		
		if(position < 0 || position > this.size) {
			throw new IndexOutOfBoundsException();
		}
		
		boolean isFull = true;
		
		for(int i = 0; i < this.elements.length; i++) {
			if(this.elements[i] == null) {
				isFull = false;
			}
		}
		
		if(isFull) {
			T tempElements[] = this.elements; 
			this.elements = (T[]) new Object[this.elements.length * 2];
		
			for(int i = 0; i < tempElements.length; i++) {
				this.elements[i] = tempElements[i];
			}
		}
		
		for(int i = this.size; i >= position; i--) {
			this.elements[i+1] = this.elements[i]; 
		}
		
		this.elements[position] = value;
	
		this.size++;
		this.modificationCount++;
	}
	
	public int indexOf(T value) {
		if(value.equals(null)) {
			return -1;
		}
		
		for(int i = 0; i < this.size; i++) {
			if(this.elements[i].equals(value)) {
				return i;
			}
		}
		
		return -1;
	}
	
	@Override
	public boolean remove(T value) {

		for(int i = 0; i < this.size; i++) {
			if(this.elements[i].equals(value)) {
				for(int j = i; j < this.size; j++) {
					this.elements[j] = this.elements[j+1];
				}
				
				this.size--;
				this.modificationCount++;
				return true;
			}
		}
		
		return false;
	}
	
	public void remove(int index) {
		
		if(index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		for(int i = index; i < this.size; i++) {
			this.elements[i] = this.elements[i+1];
		}
		
		this.size--;
		this.modificationCount++;
	}
	
	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean contains(T value) {
		
		for(int i = 0; i < this.size; i++) {
			if(this.elements[i].equals(value)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public T[] toArray() {
		return this.elements;
	}
	
//	@Override
//	public void forEach(Processor processor) {
//		for(int i = 0; i < this.size; i++) {
//			processor.process(this.elements[i]);
//		}
//	}
	
	@Override
	public ElementsGetter<T> createElementsGetter() {
		return new ElementsGetter<T>(this);
	}
	
	
	
	
	

}

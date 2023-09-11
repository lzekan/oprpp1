package hr.fer.oprpp1.custom.collections;

public class LinkedListIndexedCollection<T> implements List<T>{

	private static class ListNode<T> {
		T value;
		ListNode<T> previous;
		ListNode<T> next;
	}
	
	private int size = 0;
	private ListNode<T> first;
	private ListNode<T> last;
	
	private long modificationCount = 0;
	private static ElementsGetter eg;
	
	public LinkedListIndexedCollection() {
		this.first = this.last = null;
	}
	
	public LinkedListIndexedCollection(Collection<T> other) {
		this.addAll(other);
		this.size = other.size();
	}
	
	public long getModificationCount() {
		return this.modificationCount;
	}
	
	@Override
	public void add(T value) {
		
		if(value == null) {
			throw new NullPointerException();
		}
		
		ListNode<T> newNode = new ListNode<>();
		newNode.value = value;
		newNode.previous = this.last;
		newNode.next = null;
		
		if(this.size == 0) {
			this.first = this.last = newNode;
		} else {
			this.last.next = newNode;
			this.last = newNode;
		}

		this.size++;
		this.modificationCount++;
	}
	
	@Override
	public T get(int index) {
		
		if(index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode<T> node = first;
	
		for(int i = 1; i <= index; i++) {
			node = node.next;
		}
		
		return node.value;
	
	}
	
	@Override
	public void clear() {
		this.first = this.last = null;
		this.size = 0;
		this.modificationCount++;
	}
	
	public void insert(T value, int position) {
		if(value == null) {
			throw new NullPointerException();
		}
		
		ListNode<T> newNode = new ListNode<>();
		newNode.value = value;
		
		ListNode<T> node = first;
		
		for(int i = 1; i < position; i++) {
			node = node.next;
		}
		
		ListNode<T> nextNode = node.next;
		
		node.next = newNode;
		newNode.previous = node;
		newNode.next = nextNode;
		nextNode.previous = node;
		
		this.size++;
		this.modificationCount++;
	
	}
	
	@Override
	public T[] toArray() {
		
		int counter = 0;
		T array[] = (T[]) new Object[this.size];
		
		ListNode<T> node = first;
		
		while(node != null) {
			array[counter++] = node.value;
			node = node.next;
		}
	
		return array;
		
	}
	
	public int indexOf(T value) {
		
		if(value == null) {
			throw new NullPointerException();
		}
		
		ListNode<T> node = first;
		int index = 0;
		
		while(node.next != null) {
			if(node.value.equals(value)) {
				return index;
			}
			
			index++;
		}
		
		return -1;
		
	}
	
	public void remove(int index) {
	
		
		
		
		
		
		this.size--;
		this.modificationCount++;
		
	}
	
//	@Override
//	public void forEach(Processor processor) {
//		
//		ListNode node = first;
//
//		while(node != null) {
//			processor.process(node.value);
//			node = node.next;
//		}
//		
//	}
	
	@Override 
	public int size() {
		return this.size;
	}
	
	@Override
	public boolean contains(T value) {
		
		ListNode<T> node = first;
		
		while(node != null) {
			if(node.value.equals(value)) {
				return true;
			}
			
			node = node.next;
		}
		
		return false;
	}

	@Override
	public boolean remove(T value) {
		
		this.size--;
		this.modificationCount++;
		return false;
	}

	@Override
	public ElementsGetter<T> createElementsGetter() {
		return new ElementsGetter<T>(this);
	}
	
	
}

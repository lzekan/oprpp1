package hr.fer.oprpp1.custom.collections;

public class LinkedListIndexedCollection extends Collection{

	private static class ListNode {
		Object value;
		ListNode previous;
		ListNode next;
	}
	
	private int size = 0;
	private ListNode first;
	private ListNode last;
	
	public LinkedListIndexedCollection() {
		this.first = this.last = null;
	}
	
	public LinkedListIndexedCollection(Collection other) {
		this.addAll(other);
		this.size = other.size();
	}
	
	@Override
	public void add(Object value) {
		
		if(value == null) {
			throw new NullPointerException();
		}
		
		ListNode newNode = new ListNode();
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
	}
	
	public Object get(int index) {
		
		if(index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode node = first;
	
		for(int i = 1; i <= index; i++) {
			node = node.next;
		}
		
		return node.value;
	
	}
	
	@Override
	public void clear() {
		this.first = this.last = null;
		this.size = 0;
	}
	
	public void insert(Object value, int position) {
		if(value == null) {
			throw new NullPointerException();
		}
		
		ListNode newNode = new ListNode();
		newNode.value = value;
		
		ListNode node = first;
		
		for(int i = 1; i < position; i++) {
			node = node.next;
		}
		
		ListNode nextNode = node.next;
		
		node.next = newNode;
		newNode.previous = node;
		newNode.next = nextNode;
		nextNode.previous = node;
	
	}
	
	@Override
	public Object[] toArray() {
		
		int counter = 0;
		Object array[] = new Object[this.size];
		
		ListNode node = first;
		
		while(node != null) {
			array[counter++] = node.value;
			node = node.next;
		}
	
		return array;
		
	}
	
	public int indexOf(Object value) {
		
		if(value == null) {
			throw new NullPointerException();
		}
		
		ListNode node = first;
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
		
	}
	
	@Override
	public void forEach(Processor processor) {
		
		ListNode node = first;

		while(node != null) {
			processor.process(node.value);
			node = node.next;
		}
		
	}
	
	@Override 
	public int size() {
		return this.size;
	}
	
	@Override
	public boolean contains(Object value) {
		
		ListNode node = first;
		
		while(node != null) {
			if(node.value.equals(value)) {
				return true;
			}
			
			node = node.next;
		}
		
		return false;
	}
	
	
}

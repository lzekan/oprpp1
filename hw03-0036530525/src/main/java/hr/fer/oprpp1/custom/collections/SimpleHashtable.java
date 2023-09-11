package hr.fer.oprpp1.custom.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashtable<K, V> implements Iterable<SimpleHashtable.TableEntry<K, V>>{
	

	private TableEntry<K, V>[] table;
	private int size = 0;
	private int modificationCount = 0;
	
	public static class TableEntry<K, V> {
		
		K key;
		V value;
		TableEntry<K, V> next;
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
		
		public String toString() {
			return key + " = " + value; 
		}
	}

	
	public SimpleHashtable() {
		this.table = new TableEntry[16];
	}
	
	public SimpleHashtable(int capacity) {
		
		if(capacity < 1) {
			throw new IllegalArgumentException();
		}
		
		this.table = new TableEntry[capacity];
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public void clear() {
		table = new TableEntry[table.length];
		size = 0;
	}
	
	public void rearrangeTable() {
		
		TableEntry<K ,V>[] copyTable = table;
		table = new TableEntry[table.length * 2];
		
		size = 0;
			
		for(int i = 0; i < copyTable.length; i++) {
			
			if(copyTable[i] == null) {
				continue;
			}
			
			put(copyTable[i].getKey(), copyTable[i].getValue());
			
			TableEntry<K, V> entry = copyTable[i].next;
			
			while(entry != null) {
				put(entry.getKey(), entry.getValue());
				entry = entry.next;
			}
			
		}
		
	}
	
	public V put(K key, V value) {
		
		if(key == null) {
			throw new NullPointerException();
		}
		
		if((double) size() / table.length > 0.75) {
			rearrangeTable();
		}
		
		int position = Math.abs(key.hashCode() % table.length);
		TableEntry<K, V> nextEntry = table[position];
		
		//System.out.println("Na poziciji " + position + " nalazi se " + nextEntry);
		
		if(nextEntry == null){

			TableEntry<K, V> entry = new TableEntry<>();
			entry.key = key;
			entry.value = value;
			entry.next = null;
			
			table[position] = entry;
			size++;
			
			return null;
			
		} else {
			
			//System.out.println("Usporedujem " + key + " s " + nextEntry.getKey());
			
			if(nextEntry.getKey().equals(key)) {
				V oldValue = nextEntry.getValue();
				nextEntry.setValue(value);
				return oldValue;
			}
			
			while(nextEntry.next != null) {
				
				nextEntry = nextEntry.next;
				//System.out.println("Usporedujem " + key + " s " + nextEntry.getKey());
				
				if(nextEntry.getKey().equals(key)) {
				
					V oldValue = nextEntry.getValue();
					nextEntry.setValue(value);
					return oldValue;
				}
				
			}
			
			TableEntry<K, V> entry = new TableEntry<>();
			entry.key = key;
			entry.value = value;
			entry.next = null;
			
			nextEntry.next = entry;
			size++;
			
			return null;
			
		}
		
	}
	
	public V get(K key) {
		
		int position = Math.abs(key.hashCode() % table.length);
		TableEntry<K, V> nextEntry = table[position];
		
		if(nextEntry == null) {
			
			return null;
			
		} else {
			
			if(nextEntry.getKey().equals(key)) {
				return nextEntry.getValue();
			}
			
			while(nextEntry.next != null) {
				nextEntry = nextEntry.next;
				
				if(nextEntry.getKey().equals(key)) {
					return nextEntry.getValue();
				}
				
			}
			
			return null;
			
		}
		
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean containsKey(K key) {
		
		if(key == null) {
			return false;
		}
		
		int position = Math.abs(key.hashCode() % table.length);
		TableEntry<K, V> nextEntry = table[position];
		
		if(nextEntry == null) {
			
			return false;
			
		} else {
			
			if(nextEntry.getKey().equals(key)) {
				return true;
			}
			
			while(nextEntry.next != null) {
				nextEntry = nextEntry.next;
				
				if(nextEntry.getKey().equals(key)) {
					return true;
				}
				
			}
			
			return false;
			
		}
		
	}
	
	public boolean containsValue(V value) {
		
		for(TableEntry<K, V> entry : table) {
			
			if(entry == null) {
				continue;
			}
			
			if(entry.getValue().equals(value)) {
				return true;
			}
			
			while(entry.next != null) {
				entry = entry.next;
				
				if(entry.getValue().equals(value)) {
					return true;
				}
			}

		}
		
		return false;	
		
	}
	
	public V remove(K key) {
		
		if(key == null) {
			return null;
		}
		
		int position = Math.abs(key.hashCode() % table.length);
		TableEntry<K, V> nextEntry = table[position];
		TableEntry<K, V> previousEntry = table[position];
		
		if(nextEntry == null) {
			
			return null;
			
		} else {
			
			if(nextEntry.getKey().equals(key)) {
				
				V value = nextEntry.getValue();
				table[position] = nextEntry.next;
				size--;
				return value;
				
			}
			
			while(nextEntry.next != null) {
				
				previousEntry = nextEntry;
				nextEntry = nextEntry.next;
				
				if(nextEntry.getKey().equals(key)) {
					
					V value = nextEntry.getValue();
					previousEntry.next = nextEntry.next;
					size--;
					return value;
					
				}
				
			}
			
			return null;
			
		}
		
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder("");
		sb.append("[");
		
		for(int i = 0; i < table.length; i++) {
			
			if(table[i] == null) {
				continue;
			} 
			
			sb.append(table[i].toString() + ", ");
			
			TableEntry<K, V> entry = table[i].next;
			
			while(entry != null) {
				sb.append(entry.toString() + ", ");
				entry = entry.next;
			}
			
		}
		
		sb = size() > 0 ? sb.delete(sb.length() - 2, sb.length()) : sb;
		sb.append("]");
		
		return sb.toString();
		
		
	}
	
	private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry<K, V>>{
		
		int elemCounter = 0;
		int posCounter = 0;
		TableEntry<K, V> entry = null;
		
		public boolean hasNext() {
			return elemCounter != size();
		}
		
		public TableEntry<K, V> next(){
			
			if(elemCounter == 0) {
	
				entry = table[0];
				
				while(entry == null) {
					
					if(posCounter == table.length) {
						throw new NoSuchElementException();
					}
					
					entry = table[posCounter++];
					
				}
				
				elemCounter++;
				return entry;
			}
			
			if(entry.next != null) {
				elemCounter++;
				entry = entry.next;
				return entry;
				
			}
			
			if(posCounter < table.length) {
				
				elemCounter++;
				entry = table[posCounter++];
				while(entry == null) {
					
					if(posCounter + 1 == table.length) {
						throw new NoSuchElementException();
					}
					
					entry = table[posCounter++];
				}
				
				return entry;
				
			} else {
				throw new NoSuchElementException();
			}
			
			
			
			
		}
		
		public void remove() {
			
			SimpleHashtable.this.remove(entry.getKey());
			modificationCount++;
			
		}
	}

	@Override
	public Iterator<SimpleHashtable.TableEntry<K, V>> iterator() {
		// TODO Auto-generated method stub
		return new IteratorImpl();
	}
	

	
	
}

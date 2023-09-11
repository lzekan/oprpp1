package hr.fer.oprpp1.custom.collections;

public class Dictionary<K, V> {
	
	private class DictionaryInput<K, V>{
		
		K key;
		V value;
		
		DictionaryInput(K key, V value){
			this.key = key;
			this.value = value;
		}
		
	}
	
	private ArrayIndexedCollection<DictionaryInput<K, V>> aic;

	public Dictionary() {
		this.aic = new ArrayIndexedCollection<>();	
	}
	
	public boolean isEmpty() {
		return aic.isEmpty();
	}
	
	public int size() {
		return aic.size();
	}
	
	public void clear() {
		aic.clear();
	}
	
	public V put(K key, V value) {
		
		DictionaryInput<K, V> di = new DictionaryInput<>(key, value);
//		di.key = key;
//		di.value = value;
		
		V oldInput = null;
		
		for(int i = 0; i < aic.size(); i++){
			
			if(aic.get(i).key.equals(di.key)) {
				
				oldInput = aic.get(i).value;
				aic.remove(i);
				break;
			
			}
		}
		
		aic.add(di);
		
		return oldInput;
	
	}
	
	public V get(Object key) {
		
		for(int i = 0; i < aic.size(); i++){
			
			if(aic.get(i).key.equals((K) key)) {
				
				return aic.get(i).value;
				
			}
			
		}
		
		return null;
		
	}
	
	public V remove(K key) {
		
		V value;
		
		for(int i = 0; i < aic.size(); i++){
			
			if(aic.get(i).key.equals((K) key)) {
				
				value = aic.get(i).value;
				aic.remove(aic.get(i));
				return value;
				
			}
			
		}
		
		return null;
		
		
	}
	
	
	
}

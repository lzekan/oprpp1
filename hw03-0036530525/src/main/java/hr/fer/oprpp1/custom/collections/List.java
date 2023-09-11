package hr.fer.oprpp1.custom.collections;

public interface List<T> extends Collection<T>{
	
	public void insert(T value, int position);
	public int indexOf(T value);
	public void remove(int index);

}

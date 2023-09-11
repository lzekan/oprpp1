package hr.fer.oprpp1.custom.collections;

public interface List extends Collection{
	
	public void insert(Object value, int position);
	public int indexOf(Object value);
	public void remove(int index);

}

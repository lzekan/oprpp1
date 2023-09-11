package hr.fer.oprpp1.custom.collections;

public class Main {

	public static void main(String[] args) {
		
        Dictionary<Integer, String> dic = new Dictionary<>();
        
        dic.put(Integer.valueOf(1), "Ana");
        dic.put(Integer.valueOf(2), "Ivan");
        dic.put(Integer.valueOf(3), "Pero");
        dic.put(Integer.valueOf(8), "Marko");
        dic.put(Integer.valueOf(9), "Luka");
        
        System.out.println(dic.get(2));
        System.out.println(dic.get(9));
        System.out.println(dic.get(6));
        
        System.out.println(dic.remove(2));
        System.out.println(dic.remove(9));
        System.out.println(dic.remove(6));
        
        System.out.println(dic.get(1));
        System.out.println(dic.get(9));
        System.out.println(dic.get(6));
        
        System.out.print(dic.size());
	}

}

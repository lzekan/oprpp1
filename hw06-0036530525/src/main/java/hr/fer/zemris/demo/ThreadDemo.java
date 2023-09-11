package hr.fer.zemris.demo;

public class ThreadDemo {
	
	public static void main(String[] args) {
		
		Thread dretva = new Thread(new Dretva());
		dretva.start();
		
		

	}

}

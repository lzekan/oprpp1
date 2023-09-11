package hr.fer.zemris.math;

public class Main {

	public static void main(String[] args) {
		
		//Complex c = new Complex(-15, 8);
		//System.out.println(c.power(5));
		
//		for(Complex c1 : c.root(4)) {
//			System.out.println(c1);
//		}
		
		ComplexRootedPolynomial crp = new ComplexRootedPolynomial( new Complex(2,0), 
				new Complex(10, 5), Complex.ONE_NEG, new Complex(5, 6), Complex.IM_NEG ); 
		
		ComplexPolynomial cp = crp.toComplexPolynom(); 
		System.out.println(crp); 
		System.out.println(cp); 
		System.out.println(cp.derive());

	}

}

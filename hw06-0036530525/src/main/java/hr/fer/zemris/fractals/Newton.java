package hr.fer.zemris.fractals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hr.fer.zemris.math.*;

import java.util.concurrent.atomic.AtomicBoolean;

import hr.fer.zemris.java.fractals.viewer.FractalViewer;
import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;

public class Newton {
	
	private static Complex roots[] = new Complex[4];
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to Newton-Raphson iteration-based fractal viewer. \n"
				+ "Please enter at least two roots, one root per line. Enter 'done' when done.");
		
		Scanner sc = new Scanner(System.in);
		String root = "";
		int index = 1;	
		
		System.out.print("Root " + index++ + " > ");
		root = sc.nextLine();
		
		List<Complex> rootsList = new ArrayList<>();
		
		while(!root.equals("done")) {
			System.out.print("Root " + index++ + " > ");
			Complex complex = new Complex();
			
			if(root.equals("1")) {
				complex = Complex.ONE;
			} else if (root.equals("-1")) {
				complex = Complex.ONE_NEG;
			} else if (root.equals("i")) {
				complex = Complex.IM;
			} else if (root.equals("-i")) {
				complex = Complex.IM_NEG;
			} else {
				
				double re, im;
				re = im = 0;
				
				String arg[] = root.split(" ");
				if (arg.length > 3 || arg.length == 2) {
					sc.close();
					throw new IllegalArgumentException();
				}
				
				if(arg.length == 3) {
					re = Integer.parseInt(arg[0]);
					
					if(arg[2].equals("i")) {
						im = 1;
					} else {
						im = Double.parseDouble(arg[2].substring(0, arg[2].length() - 1));
					}
					
					if(arg[1].equals("-")) {
						im = -im;
					}
					
					complex = new Complex(re, im);
					
				} else {
					
					if(arg[0].contains("i")) {
						re = 0;
						im = Double.parseDouble(arg[0].substring(0, arg[0].length() - 1));
					} else {
						re = Double.parseDouble(arg[0]);
						im = 0;
					}
					
					complex = new Complex(re, im);
				}
			
			}
			
			rootsList.add(complex);
			root = sc.nextLine();
			
		}
		
		sc.close();
		
		int indexArray = 0;
		
		for(Complex c : rootsList) {
			
			roots[indexArray++] = c;
			
		}
		
		FractalViewer.show(new Mandelbrot());
		
	}
	
	public static class Mandelbrot implements IFractalProducer{
		
		@Override
		public void produce(double reMin, double reMax, double imMin, double imMax,
				int width, int height, long requestNo, IFractalResultObserver observer, AtomicBoolean cancel) {
			
			
			System.out.println("Zapocinjem izracun...");
			//Complex[] roots = new Complex[] {Complex.ONE, Complex.ONE_NEG, Complex.IM, Complex.IM_NEG};
			int m = 16 * 16 * 16;
			int offset = 0;
			short[] data = new short[width * height];
			ComplexRootedPolynomial crp;
			ComplexPolynomial f = null;
			ComplexPolynomial fd;
			
			for(int y = 0; y < height; y++) {
				if(cancel.get()) break;
				for(int x = 0; x < width; x++) {
					double cre = x / (width-1.0) * (reMax - reMin) + reMin;
					double cim = (height-1.0-y) / (height-1) * (imMax - imMin) + imMin;
					
					Complex c  = new Complex(cre, cim);
					crp = new ComplexRootedPolynomial(c, roots);
					
					//System.out.println(crp.toString());
					f = crp.toComplexPolynom();
					fd = f.derive();
					//System.out.println("COMPLEX POLYNOM KOJI SE ZOVE F JE " + f.toString() + " A NJEGOVA DERIVACIJA JE " + fd.toString());
					Complex zn = new Complex(cre, cim);
					
					int iters = 0;
					Complex znold;
					do {
						znold = new Complex(zn.getRe(), zn.getIm());
						zn = zn.sub(f.apply(zn).divide(fd.apply(zn)));
						iters++;
					} while(iters < m && (zn.sub(znold)).module() > 0.001);
					
					int index = crp.indexOfClosestRootFor(zn, 0.002);
					//System.out.println("-------------------INDEX JE " + index + " ---------------");
					data[offset] = (short) (index + 1);
					offset++;
				}
				//System.out.println(y);
			}
			System.out.println("Racunanje gotovo. Idem obavijestiti promatraca tj. GUI!");
			observer.acceptResult(data, (short) (f.order()+1), requestNo);
			
			
		}
		
	}


	
	
	
}

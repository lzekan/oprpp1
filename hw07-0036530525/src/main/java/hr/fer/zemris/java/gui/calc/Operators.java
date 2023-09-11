package hr.fer.zemris.java.gui.calc;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public class Operators {
	
	public static final DoubleBinaryOperator ADD = (x, y) -> {
		return x + y;
	};
	
	
	public static final DoubleBinaryOperator SUB = (x, y) -> {
		return x - y;
	};
	
	public static final DoubleBinaryOperator MUL = (x, y) -> {
		return x * y;
	};
	
	public static final DoubleBinaryOperator DIV = (x, y) -> {
		return x / y;
	};
	
	public static final DoubleBinaryOperator POW = (x, y) -> {
		return Math.pow(x, y);
	};
	
	public static final DoubleBinaryOperator ROOT = (x, y) -> {
		return Math.pow(x, 1 / y);
	};
	
	public static final DoubleUnaryOperator SIN = x -> {
		return Math.sin(x);
	};
	
	public static final DoubleUnaryOperator COS = x -> {
		return Math.cos(x);
	};
	
	public static final DoubleUnaryOperator TAN = x -> {
		return Math.tan(x);
	};
	
	public static final DoubleUnaryOperator CTG = x -> {
		return 1.0 / Math.tan(x);
	};
	
	public static final DoubleUnaryOperator ASIN = x -> {
		return Math.asin(x);
	};
	
	public static final DoubleUnaryOperator ACOS = x -> {
		return Math.acos(x);
	};
	
	public static final DoubleUnaryOperator ATAN = x -> {
		return Math.atan(x);
	};
	
	public static final DoubleUnaryOperator ACTG = x -> {
		return Math.atan(1.0 / x);
	};
	
	public static final DoubleUnaryOperator LOG = x -> {
		return Math.log10(x);
	};
	
	public static final DoubleUnaryOperator ALOG = x -> {
		return Math.pow(10.0, x);
	};
	
	public static final DoubleUnaryOperator LN = x -> {
		return Math.log(x);
	};
	
	public static final DoubleUnaryOperator ALN = x -> {
		return Math.pow(Math.E, x);
	};

}

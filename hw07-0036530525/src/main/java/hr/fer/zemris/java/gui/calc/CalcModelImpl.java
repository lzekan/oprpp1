package hr.fer.zemris.java.gui.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class CalcModelImpl implements CalcModel{
	
	private String currentNumber;
	private double value;
	private List<CalcValueListener> listeners;
	private boolean editable;
	private boolean positive;
	private double activeOperand;
	
	public CalcModelImpl() {
		currentNumber = "0";
		listeners = new ArrayList<>();
		editable = true;
		positive = true;
		activeOperand = 0;
	}
	
	private void update() {
		for (CalcValueListener listener : listeners) {
			listener.valueChanged(this);
		}
	}

	@Override
	public void addCalcValueListener(CalcValueListener l) {
		listeners.add(l);
		
	}

	@Override
	public void removeCalcValueListener(CalcValueListener l) {
		listeners.remove(l);
		
	}

	@Override
	public double getValue() {
		return this.value;
	}

	@Override
	public void setValue(double value) {
		this.value = value;
		currentNumber = Double.valueOf(value).toString();
		update();
	}

	@Override
	public boolean isEditable() {
		return editable;
	}

	@Override
	public void clear() {
		currentNumber = "0";
		value = 0;
		update();
		editable = true;
		positive = true;
		
	}

	@Override
	public void clearAll() {
		currentNumber = "0";
		value = 0;
		activeOperand = 0;
		editable = true;
		positive = true;
		update();
		
	}

	@Override
	public void swapSign() throws CalculatorInputException {
		if (!editable) {
			throw new CalculatorInputException("Ne možete mijenjati ovaj broj");
		} 
			
		currentNumber = positive ? "-" + currentNumber : currentNumber.substring(1, currentNumber.length());
		positive = !positive;
		value = Double.valueOf(currentNumber == "-0" ? "0" : currentNumber);
		update();
		
	}

	@Override
	public void insertDecimalPoint() throws CalculatorInputException {
		if (!editable)
			throw new CalculatorInputException("Ne možete mijenjati ovaj broj");
		if (currentNumber.contains("."))
			throw new CalculatorInputException("Broj već sadrži decimalnu točku");
		currentNumber += ".";
		update();
		
	}

	@Override
	public void insertDigit(int digit) throws CalculatorInputException, IllegalArgumentException {
		if (currentNumber.equals("0")) {
			currentNumber = Integer.valueOf(digit).toString();
		} else {
			currentNumber += Integer.valueOf(digit).toString();
		}
		
		value = Double.valueOf(currentNumber);
		update();
	}

	@Override
	public boolean isActiveOperandSet() {
		return activeOperand == 0;
	}

	@Override
	public double getActiveOperand() throws IllegalStateException {
		return this.activeOperand;
	}

	@Override
	public void setActiveOperand(double activeOperand) {
		this.activeOperand = activeOperand;
		
	}

	@Override
	public void clearActiveOperand() {
		this.activeOperand = 0;
		
	}

	@Override
	public DoubleBinaryOperator getPendingBinaryOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPendingBinaryOperation(DoubleBinaryOperator op) {
		// TODO Auto-generated method stub
	}

	public String toString() {
		return currentNumber;
	}
}

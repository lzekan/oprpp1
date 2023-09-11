package hr.fer.zemris.java.gui.calc;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import hr.fer.zemris.java.gui.calc.buttons.BtnDigit;
import hr.fer.zemris.java.gui.layouts.CalcLayout;
import hr.fer.zemris.java.gui.layouts.RCPosition;

public class Calculator extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private CalcModel model;
	private static JLabel label = new JLabel("0");
	
	public Calculator(CalcModel model) {
		this.model = model;
		label.setFont(label.getFont().deriveFont(30f));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBackground(Color.YELLOW);
		label.setOpaque(true);
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(650, 400);
		initGUI();
		pack();
	}
	
	private void initGUI() {
		Container cp = getContentPane();
		cp.setLayout(new CalcLayout(3));
		addButtons(cp);
	}
	
	private JLabel button(String text) {
		JLabel l = new JLabel(text, SwingConstants.CENTER);
		l.setBackground(Color.GRAY);
		l.setOpaque(true);
		return l;
	}
	
	private void addButtons(Container cp) {
		JButton dot = new JButton(".");
		dot.addActionListener(a -> {
			try {
				model.insertDecimalPoint();
			}catch (CalculatorInputException e) {
				System.out.println(e.getMessage());
			}
		});
		
		JButton clr = new JButton("clr");
		clr.addActionListener(a -> {
			model.clear();
		});
		
		JButton res = new JButton("res");
		res.addActionListener(a -> {
			model.clearAll();
		});
		
		JButton swap = new JButton("+/-");
		swap.addActionListener(a -> {
			try {
				model.swapSign();
			} catch(CalculatorInputException e) {
				System.out.println(e.getMessage());
			}
		});
		
		cp.add(label, new RCPosition(1,1));
		cp.add(button("="), new RCPosition(1,6));
		cp.add(clr, new RCPosition(1,7));
		cp.add(button("1/x"), new RCPosition(2,1));
		cp.add(button("sin"), new RCPosition(2,2));
		cp.add(new BtnDigit("7", model), new RCPosition(2,3));
		cp.add(new BtnDigit("8", model), new RCPosition(2,4));
		cp.add(new BtnDigit("9", model), new RCPosition(2, 5));
		cp.add(button("/"), new RCPosition(2, 6));
		cp.add(res, new RCPosition(2, 7));
		cp.add(button("log"), new RCPosition(3,1));
		cp.add(button("cos"), new RCPosition(3,2));
		cp.add(new BtnDigit("4", model), new RCPosition(3,3));
		cp.add(new BtnDigit("5", model), new RCPosition(3,4));
		cp.add(new BtnDigit("6", model), new RCPosition(3, 5));
		cp.add(button("*"), new RCPosition(3, 6));
		cp.add(button("push"), new RCPosition(3, 7));
		cp.add(button("ln"), new RCPosition(4,1));
		cp.add(button("tan"), new RCPosition(4,2));
		cp.add(new BtnDigit("1", model), new RCPosition(4,3));
		cp.add(new BtnDigit("2", model), new RCPosition(4,4));
		cp.add(new BtnDigit("3", model), new RCPosition(4, 5));
		cp.add(button("-"), new RCPosition(4, 6));
		cp.add(button("pop"), new RCPosition(4, 7));
		cp.add(button("x^n"), new RCPosition(5,1));
		cp.add(button("ctg"), new RCPosition(5,2));
		cp.add(new BtnDigit("0", model), new RCPosition(5,3));
		cp.add(swap, new RCPosition(5,4));
		cp.add(dot, new RCPosition(5, 5));
		cp.add(button("+"), new RCPosition(5, 6));
		cp.add(button("Inv"), new RCPosition(5, 7));
		

	}
	
	
    public static void main( String[] args )
    {
    	CalcModel calcModel = new CalcModelImpl();
    	
    	CalcValueListener listener = number -> {
    		label.setText(number.toString());
    	};
    	
    	calcModel.addCalcValueListener(listener);
    	SwingUtilities.invokeLater(() -> {
    		new Calculator(calcModel).setVisible(true);
    	});
    
    }

}

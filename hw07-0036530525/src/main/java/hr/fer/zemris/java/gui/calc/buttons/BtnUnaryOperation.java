package hr.fer.zemris.java.gui.calc.buttons;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import hr.fer.zemris.java.gui.calc.CalcModel;

public class BtnUnaryOperation extends JButton{
	
	private static final long serialVersionUID = 1L;
	private CalcModel model;
	
	ActionListener action = (a) -> {
		JButton btn = (JButton)a.getSource();

	};
	
	public BtnUnaryOperation(String text, CalcModel model) {
		
	}

}

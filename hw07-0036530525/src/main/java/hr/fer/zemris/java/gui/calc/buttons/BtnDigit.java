package hr.fer.zemris.java.gui.calc.buttons;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import hr.fer.zemris.java.gui.calc.CalcModel;

public class BtnDigit extends JButton{
	
	private static final long serialVersionUID = 1L;
	private CalcModel model;
	
	ActionListener action = (a) -> {
		JButton btn = (JButton)a.getSource();
		model.insertDigit(Integer.valueOf(btn.getText()));
	};
	
	public BtnDigit(String text, CalcModel model) {
		super(text);
		this.addActionListener(action);
		this.model = model;
	}
	

}

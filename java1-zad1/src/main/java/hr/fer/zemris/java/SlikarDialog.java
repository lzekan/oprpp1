package hr.fer.zemris.java;

import java.awt.GridLayout;

import javax.swing.*;

public class SlikarDialog extends JDialog {

    private final JTextField fieldIme;
    private final JTextField fieldCijena;
    private final JCheckBox fieldOver10Milli;
    private Slikar slikar;
    
    public SlikarDialog(JFrame owner) {
        super(owner, "Dodaj slikara", true);
        setSize(300, 200);
        setLocationRelativeTo(null);

        fieldIme = new JTextField();
        fieldCijena = new JTextField();
        fieldOver10Milli = new JCheckBox("Preko 10 milijuna");

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            String ime = fieldIme.getText();
            double ocjena = Double.parseDouble(fieldCijena.getText());
            boolean over10Milli = fieldOver10Milli.isSelected();
            
            slikar = new Slikar(ime, ocjena, over10Milli);
            SlikarDialog.this.dispose();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> SlikarDialog.this.dispose());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(new JLabel("Ime:"));
        panel.add(fieldIme);
        panel.add(new JLabel("Prosjecna cijena:"));
        panel.add(fieldCijena);
        panel.add(new JLabel());
        panel.add(fieldOver10Milli);
        panel.add(okButton);
        panel.add(cancelButton);
        add(panel);

        setVisible(true);
    }
    
    
    
    
    
    
    public Slikar getSlikar() {
        return slikar;
    }
	
}

package hr.fer.zemris.java;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

public class BazaSlikara {
	
	private List<Slikar> slikari;
	
    public BazaSlikara() {
        slikari = new ArrayList<>();
    }

    public List<Slikar> getSlikari() {
        return slikari;
    }

    public void addSlikar(Slikar slikar) {
        slikari.add(slikar);
    }
	
    public void load(String path) {
        slikari = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        	if(!path.endsWith(".txt")) {
        		throw new IOException();
        	}
        	
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] lineArr = line.split("\t");
                
                String ime = lineArr[0];
                double cijena = Double.parseDouble(lineArr[1]);
                boolean over10Milli = Boolean.parseBoolean(lineArr[2]);
                
                slikari.add(new Slikar(ime, cijena, over10Milli));
            }
            
        } catch (IOException e) {

        }
    }
    
    public void save(String path) {
    	
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (Slikar slikar : slikari) {
                pw.println(slikar.getIme() + "\t" + slikar.getCijena() + "\t" + slikar.isOver10Milli());
            }
        } catch (IOException e) {

        }
    }
	
}

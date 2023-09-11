package hr.fer.zemris.java;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class Main extends JFrame{

    private final BazaSlikara bazaSlikara;
    private final DefaultListModel<String> modelImena;
    private final DefaultListModel<String> modelInfo;
    private final JList<String> listaImena;
    private final JList<String> listaInfo;
    private File currentFile;
	
    public Main() {
    	
    	super("Slikari");
    	setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        bazaSlikara = new BazaSlikara();
        modelImena = new DefaultListModel<>();
        modelInfo = new DefaultListModel<>();
        listaImena = new JList<>(modelImena);
        listaInfo = new JList<>(modelInfo);
        
        setupSaveLoad();
        setupView();

    }
    
    private void setupView() {
    	JButton btnAdd = new JButton("Dodaj slikara");
    	
        btnAdd.addActionListener(e -> {
            SlikarDialog slikarDialog = new SlikarDialog(Main.this);
            Slikar slikar = slikarDialog.getSlikar();
            
            if (slikar != null) {
                bazaSlikara.addSlikar(slikar);
                update();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new CustomLayoutManager());

        JScrollPane leftPanel = new JScrollPane(listaImena);
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        
        JScrollPane rightPanel = new JScrollPane(listaInfo);

        mainPanel.add(leftPanel, "left");
        mainPanel.add(rightPanel, "right");

        add(mainPanel, BorderLayout.CENTER);
        add(btnAdd, BorderLayout.SOUTH);
    }
	
    private void setupSaveLoad() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem saveItem = new JMenuItem("Save");

        loadItem.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(Main.this);
            
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                currentFile = fc.getSelectedFile();
                bazaSlikara.load(currentFile.getPath());
                update();
            }
        });

        saveItem.addActionListener(e -> {
            if (currentFile == null) {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showSaveDialog(Main.this);
                
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    currentFile = fc.getSelectedFile();
                }
            }
            if (currentFile != null) {
                bazaSlikara.save(currentFile.getPath());
            }
        });

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }
    
    private void update() {
    	
        modelImena.clear();
        modelInfo.clear();
        
        for (Slikar slikar : bazaSlikara.getSlikari()) {
            modelImena.addElement(slikar.getIme());
            modelInfo.addElement(slikar.getIme() + " (" + slikar.getCijena() + ", " + 
            		(slikar.isOver10Milli() ? "DA" : "NE") + ")");
        }
    }
    
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Main().setVisible(true));
	}

}

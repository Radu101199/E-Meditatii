import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
class DatePersonaleFrame extends JFrame  {
	
	private boolean flag = false;
	private JLabel numeLabel, numeUtilizatorLabel, parolaLabel, materiiLabel;
	private JTextField numeField, numeUtilizatorField, parolaField;
	private JButton editButon,saveButon;
	private JCheckBox[] materiiCheckBoxes;
	private JPanel materiiCheckBoxPanel;
	private DefaultListModel<String> materiiModel;
	private JList<String> materiiList;
	private JScrollPane materiiListScroller;
	private Utilizator utilizator;
	private ListaElev listaElev;
	private ListaProfesor listaProfesor;
	private SesiuniMeditatieManager s;
	
	DatePersonaleFrame(Utilizator utilizator,ListaElev listaElev, ListaProfesor listaProfesor) {
	    super("Date Personale");
	    
	    this.utilizator = utilizator;
	    this.listaElev = listaElev;
	    this.listaProfesor = listaProfesor;
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    panel.setBackground(new Color(137, 207, 240)); 
	    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

	    numeLabel = new JLabel("Nume");
	    numeLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
	    numeLabel.setForeground(new Color(0,0,0)); 

	    numeField = new JTextField(utilizator.getNume());
	    numeField.setEditable(false);
	    numeField.setBackground(new Color(255,255,255)); 
	    numeField.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1)); 
	    numeField.setFont(new Font("Arial", Font.PLAIN, 16)); 

	    panel.add(numeLabel);
	    panel.add(numeField);

	    numeUtilizatorLabel = new JLabel("Nume Utilizator ");
	    numeUtilizatorLabel.setFont(new Font("Arial", Font.BOLD, 16));
	    numeUtilizatorLabel.setForeground(new Color(0,0,0));

	    numeUtilizatorField = new JTextField(utilizator.getNumeUtilizator());
	    numeUtilizatorField.setEditable(false);
	    numeUtilizatorField.setBackground(new Color(255,255,255));
	    numeUtilizatorField.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
	    numeUtilizatorField.setFont(new Font("Arial", Font.PLAIN, 16));
	    numeUtilizatorField.addKeyListener(new KeyAdapter() {
			  public void keyTyped(KeyEvent e) {
			    if (e.getKeyChar() == ' ') {
			      e.consume();
			    }
			  }
			});

	    panel.add(numeUtilizatorLabel);
	    panel.add(numeUtilizatorField);

	    parolaLabel = new JLabel("Parola ");
	    parolaLabel.setFont(new Font("Arial", Font.BOLD, 16));
	    parolaLabel.setForeground(new Color(0,0,0));

	    parolaField = new JTextField(utilizator.getParola());
	    parolaField.setEditable(false);
	    parolaField.setBackground(new Color(255,255,255));
	    parolaField.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
	    parolaField.setFont(new Font("Arial", Font.PLAIN, 16));

	    panel.add(parolaLabel);
	    panel.add(parolaField);

	    materiiLabel = new JLabel();
	    if(utilizator instanceof Profesor)
	    	materiiLabel.setText("Materii predate");
	    else
	    	materiiLabel.setText("Materii favorite");
	    materiiLabel.setFont(new Font("Arial", Font.BOLD, 16));
	    materiiLabel.setForeground(new Color(0,0,0));

	    String[] materii = {"Matematica", "Informatica", "Limba Romana", "Biologie", "Chimie", "Fizica", "Istorie", "Geografie", "Limba Engleza", "Limba Franceza"};
	    materiiModel = new DefaultListModel<>();
	    if(utilizator instanceof Profesor) {
	        if(((Profesor)utilizator).getMateriiPredate()!=null)
	            materiiModel.addAll(((Profesor)utilizator).getMateriiPredate());
	    }
	    else {
	        if(((Elev)utilizator).getMateriiFavorite()!=null)
	            materiiModel.addAll(((Elev)utilizator).getMateriiFavorite());
	    }
	    materiiList = new JList<>(materiiModel);
	    materiiList.setVisibleRowCount(3);
	    materiiList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    materiiList.setBackground(new Color(255,255,255));
	    materiiList.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));
	    materiiList.setFont(new Font("Arial", Font.PLAIN, 16));

	    materiiListScroller = new JScrollPane(materiiList);
	    materiiListScroller.setPreferredSize(new Dimension(250, 80));

	    materiiCheckBoxPanel = new JPanel();
	    materiiCheckBoxPanel.setLayout(new BoxLayout(materiiCheckBoxPanel, BoxLayout.Y_AXIS));
	    materiiCheckBoxes = new JCheckBox[materii.length];
	    for (int i = 0; i < materii.length; i++) {
	        materiiCheckBoxes[i] = new JCheckBox(materii[i]);
	        materiiCheckBoxes[i].setBackground(new Color(255,255,255));
	        materiiCheckBoxes[i].setForeground(new Color(0,0,0));
	        materiiCheckBoxes[i].setFont(new Font("Arial", Font.PLAIN, 16));
	        materiiCheckBoxPanel.add(materiiCheckBoxes[i]);
	    }
	    materiiCheckBoxPanel.setVisible(false);

	    panel.add(materiiLabel);
	    panel.add(materiiListScroller);
	    panel.add(materiiCheckBoxPanel);

	    JPanel butoanePanel = new JPanel();
	    butoanePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	    butoanePanel.setBackground(new Color(0, 0, 128));
	    
	    editButon = new JButton("Editeaza");
	    editButon.setBackground(new Color(0, 0, 128));
	    editButon.setForeground(Color.WHITE);
	    editButon.setFont(new Font("Arial", Font.BOLD, 16));
	    editButon.setBorder(BorderFactory.createRaisedBevelBorder());
	    EditListener editListener = new EditListener();
	    editButon.addActionListener(editListener);
	    butoanePanel.add(editButon);

	    saveButon = new JButton("Salveaza");
	    saveButon.setBackground(new Color(0, 0, 128));
	    saveButon.setForeground(Color.WHITE);
	    saveButon.setFont(new Font("Arial", Font.BOLD, 16));
	    saveButon.setBorder(BorderFactory.createRaisedBevelBorder());
	    saveButon.setVisible(true);
	    SaveListener saveListener = new SaveListener();
	    saveButon.addActionListener(saveListener);
	    butoanePanel.add(saveButon);

	    panel.add(butoanePanel);
        add(panel);

		
	}


    class EditListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
		
        numeField.setEditable(true);
        numeUtilizatorField.setEditable(true);
        parolaField.setEditable(true);
        materiiCheckBoxPanel.setVisible(true);
        materiiListScroller.setVisible(false);
        editButon.setVisible(false);
        saveButon.setVisible(true);
        flag = true;
        
        for (int i=0;i<materiiCheckBoxes.length;i++){
        	if(utilizator instanceof Profesor) {
        		if(((Profesor)utilizator).getMateriiPredate()!=null){
        			if (((Profesor)utilizator).getMateriiPredate().contains(materiiCheckBoxes[i].getText())){
        				materiiCheckBoxes[i].setSelected(true);
        			}
        		}
        	}
        	else
        		if(((Elev)utilizator).getMateriiFavorite()!=null){
                    if (((Elev)utilizator).getMateriiFavorite().contains(materiiCheckBoxes[i].getText())){
                    	materiiCheckBoxes[i].setSelected(true);
                    }
                }
        	}
	    }
    }
    class SaveListener implements ActionListener {
	JFrame h;
    	public void actionPerformed(ActionEvent e) {
    		Utilizator u;
    		if(utilizator instanceof Profesor) {
    			u = new Profesor(utilizator.getNumeUtilizator(), utilizator.getParola(), utilizator.getNume());
        	}
        	else {
        		u = new Elev(utilizator.getNumeUtilizator(), utilizator.getParola(), utilizator.getNume());
        	}
    	
    	
    	s = new SesiuniMeditatieManager();
    	String oldUsername = utilizator.getNumeUtilizator();
    	if(utilizator instanceof Profesor) {
    		listaProfesor.stergeUtilizator(utilizator);
    		
    	}
    	else {
    		listaElev.stergeUtilizator(utilizator);
    	}
    	 try {
 			((ListaElev)listaElev).listeaza();
 			((ListaProfesor)listaProfesor).listeaza();
 		} catch (ClassNotFoundException | IOException e1) {
 			e1.printStackTrace();
 		}
    	u.setNume(numeField.getText());
        u.setNumeUtilizator(numeUtilizatorField.getText());
        u.setParola(parolaField.getText());
        
    	try {
			s.setNewUsername(oldUsername, u.getNumeUtilizator());
			utilizator.changeProfilePictureFile(oldUsername, numeUtilizatorField.getText());
			if(utilizator instanceof Profesor)
				AdaugaCertificatDialog.SchimbareNumeUtilizatorFolder(((Profesor) utilizator), oldUsername);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        ArrayList<String> materiiSelectate = new ArrayList<String>();
        for (int i = 0; i < materiiCheckBoxes.length; i++){
            if (materiiCheckBoxes[i].isSelected()){
                materiiSelectate.add(materiiCheckBoxes[i].getText());
            }
        }
        materiiModel.clear();
        for (String subject : materiiSelectate) {
        	materiiModel.addElement(subject);
        }
        
        if(u instanceof Elev) 
        	((Elev) u).setMateriiFavorite(materiiSelectate);
        else
        	((Profesor) u).setMateriiPredate(materiiSelectate);
        
        numeField.setEditable(false);
        numeUtilizatorField.setEditable(false);
        materiiCheckBoxPanel.setVisible(false);
        materiiListScroller.setVisible(true);
        saveButon.setVisible(false);
        editButon.setVisible(true);
        
        if(utilizator instanceof Profesor) {
        	listaProfesor.adaugaUtilizator(u);
        	listaProfesor.descarca();

        	
        	try {
        		h = new ProfesorFrame(u,listaElev,listaProfesor);
				dispose();
				h.setSize(600,600);
				h.setLocationRelativeTo(null);
				h.setDefaultCloseOperation(EXIT_ON_CLOSE);
        	} catch (IOException | ParseException e1) {
			e1.printStackTrace();
        	}
        }
        else{
        	listaElev.adaugaUtilizator(u);
            listaElev.descarca();

            try {
            	h = new ElevFrame(u,listaProfesor,listaElev);
				dispose();
				h.setSize(600,600);
				h.setLocationRelativeTo(null);
				h.setDefaultCloseOperation(EXIT_ON_CLOSE);
			} catch (IOException | ParseException e1) {
				e1.printStackTrace();
			}
        }  
		h.pack();
		h.setVisible(true); 
		if(flag == true)
		JOptionPane.showMessageDialog(null, "Datele personale modificate cu succes! ",
				"Information", JOptionPane.INFORMATION_MESSAGE);
    	}
    }
}
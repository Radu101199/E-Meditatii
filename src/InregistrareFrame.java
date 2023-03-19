import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class InregistrareFrame extends JFrame{
	private JButton b1;
	private JLabel imagineLabel;
	private JTextField numeUtilizatorField, numeField;
	private JPasswordField parolaField, parolaConfirmareField;
	private JRadioButton elev, profesor;
	private ButtonGroup grup;
	private Utilizator utilizator;
	private ListaProfesor listaProfesor;
	private ListaElev listaElev;
	private static ArrayList<Object> listaUtilizatori;
	private ControllerButoane cb;
	
	public InregistrareFrame(ListaProfesor listaProfesor, ListaElev listaElev) throws FileNotFoundException, ClassNotFoundException, IOException{ //
		super("Inregistrare");
		this.setVisible(true);
		this.setSize(300,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.listaElev = listaElev;
		this.listaProfesor = listaProfesor;
		listaUtilizatori = new ArrayList<>();
		
		JPanel p = new JPanel(new GridBagLayout());
		JPanel b = new JPanel();
		p.setPreferredSize(new Dimension(300, 600));
		p.setBackground(Color.LIGHT_GRAY);
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		
		imagineLabel = new JLabel();
		try {
		  Image imagine = ImageIO.read(new File("data/inregistrare.png"));
		  imagine = imagine.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		  imagineLabel.setIcon(new ImageIcon(imagine));
		} catch (IOException e) {
		  e.printStackTrace();
		}
		p.add(imagineLabel, c);
		
		numeField = new JTextField("Nume");
		numeField.setPreferredSize(new Dimension(200, 30));
		numeField.setMaximumSize(new Dimension(200, 30));
		numeField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
		numeField.setForeground(Color.LIGHT_GRAY);
		numeField.setFont(new Font("Arial", Font.PLAIN, 16));
		numeField.addFocusListener(new ControllerFocus("Nume"));
		c.gridx = 0;
		c.gridy = 1;
		p.add(numeField, c);
		
		numeUtilizatorField = new JTextField("Utilizator");
		numeUtilizatorField.setPreferredSize(new Dimension(200, 30));
		numeUtilizatorField.setMaximumSize(new Dimension(200, 30));
		numeUtilizatorField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
		numeUtilizatorField.setForeground(Color.LIGHT_GRAY);
		numeUtilizatorField.setFont(new Font("Arial", Font.PLAIN, 16));
		numeUtilizatorField.addFocusListener(new ControllerFocus("Utilizator"));
		numeUtilizatorField.addKeyListener(new KeyAdapter() {
		  public void keyTyped(KeyEvent e) {
		    if (e.getKeyChar() == ' ') {
		      e.consume();
		    }
		  }
		});
		c.gridx = 0;
		c.gridy = 2;
		p.add(numeUtilizatorField, c);
		
		parolaField = new JPasswordField(".....");
		parolaField.setPreferredSize(new Dimension(200, 30));
		parolaField.setMaximumSize(new Dimension(200, 30));
		parolaField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
		parolaField.setForeground(Color.LIGHT_GRAY);
		parolaField.setFont(new Font("Arial", Font.PLAIN, 16));
		parolaField.addFocusListener(new ControllerFocus("....."));
		c.gridx = 0;
		c.gridy = 3;
		p.add(parolaField, c);
		
		parolaConfirmareField = new JPasswordField(".....");
		parolaConfirmareField.setPreferredSize(new Dimension(200, 30));
		parolaConfirmareField.setMaximumSize(new Dimension(200, 30));
		parolaConfirmareField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
		parolaConfirmareField.setForeground(Color.LIGHT_GRAY);
		parolaConfirmareField.setFont(new Font("Arial", Font.PLAIN, 16));
		parolaConfirmareField.addFocusListener(new ControllerFocus("....."));
		c.gridx = 0;
		c.gridy = 4;
		p.add(parolaConfirmareField, c);
		
		elev = new JRadioButton("Elev");
		elev.setFont(new Font("Arial", Font.PLAIN, 16));
		profesor = new JRadioButton("Profesor");
		profesor.setFont(new Font("Arial", Font.PLAIN, 16));
		grup = new ButtonGroup();
		grup.add(elev);
		grup.add(profesor);
		elev.addActionListener(cb);
		profesor.addActionListener(cb);
		b.add(elev);
		b.add(profesor);
		b.setBorder(BorderFactory.createRaisedBevelBorder());
		c.gridx = 0;
		c.gridy = 5;
		p.add(b,c);
		
	
		
		cb=new ControllerButoane();
		b1=new JButton("Confirmare");
		b1.setPreferredSize(new Dimension(100, 30));
		b1.setMaximumSize(new Dimension(100, 30));
		b1.setForeground(new Color(0, 0, 128));
		b1.setFont(new Font("Arial", Font.BOLD, 15));
		c.gridx = 0;
		c.gridy = 6;
		p.add(b1,c);
		b1.addActionListener(cb);
		
		add(p);
		
		((ListaElev)listaElev).listeaza();
		((ListaProfesor)listaProfesor).listeaza();
		listaUtilizatori.addAll(ListaElev.lista);
		listaUtilizatori.addAll(ListaProfesor.lista);
	}
	
	private class ControllerButoane implements ActionListener{
		JFrame f; 
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e){
			if (e.getSource()==b1){
				boolean flag = true;
				if(numeField.getText().equals("Nume")||numeUtilizatorField.getText().equals("Utilizator")||parolaField.getText().equals(".....")||parolaConfirmareField.getText().equals(".....")) {
					JOptionPane.showMessageDialog(null, "Completati fiecare caseta!",
							"Eroare", JOptionPane.ERROR_MESSAGE);
					flag = false;}
				else if(parolaField.getText().toString().compareTo(parolaConfirmareField.getText().toString())!=0)
					{
					JOptionPane.showMessageDialog(null, "Parola nu coincide!",
							"Eroare", JOptionPane.ERROR_MESSAGE);
					flag = false;
					}
				else if(!elev.isSelected()&&!profesor.isSelected()){
					JOptionPane.showMessageDialog(null, "Selectati una din optiuni!",
							"Information", JOptionPane.INFORMATION_MESSAGE);
					flag = false;
					}
				else if(elev.isSelected()){
					utilizator = new Elev(numeUtilizatorField.getText().trim(), parolaField.getText().trim(), numeField.getText());
					for(Object pers : listaUtilizatori) {
						if(((Utilizator)pers).getNumeUtilizator().equals(utilizator.getNumeUtilizator())){
						JOptionPane.showMessageDialog(null, "Acest nume de utilizator exista deja!",
								"Eroare", JOptionPane.ERROR_MESSAGE);
						flag = false;
						break;
						}
					}
					if(flag == true) {
						listaElev.adaugaUtilizator(utilizator);
						listaElev.descarca();
						JOptionPane.showMessageDialog(null, "A fost inregistrat un utilizator nou!",
							"	Information", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else if(profesor.isSelected()) {
						utilizator = new Profesor(numeUtilizatorField.getText().trim(), parolaField.getText().trim(), numeField.getText());
						for(Object pers : listaUtilizatori) {
							if(((Utilizator)pers).getNumeUtilizator().equals(utilizator.getNumeUtilizator())){
								JOptionPane.showMessageDialog(null, "Acest nume de utilizator exista deja!",
										"Eroare", JOptionPane.ERROR_MESSAGE);
								flag = false;
								break;
							}
						}
						if(flag == true) {
							listaProfesor.adaugaUtilizator(utilizator);
							listaProfesor.descarca();
							JOptionPane.showMessageDialog(null, "A fost inregistrat un utilizator nou!",
									"Information", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					if(flag == true) {
						dispose();
						f=new LogareFrame();
						f.pack();
					}
			}
		}
	}
	
	private class ControllerFocus implements FocusListener{
		private String textDefault;
		
		public ControllerFocus(String textDefault) {
			this.textDefault=textDefault;
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			JTextField textField = (JTextField) e.getSource();
			 if (textField.getText().equals(textDefault)) {
			        textField.setText("");
			        textField.setForeground(Color.BLACK);
			      }
		}

		@Override
		public void focusLost(FocusEvent e) {
			 JTextField textField = (JTextField) e.getSource();
		      if (textField.getText().isEmpty()) {
		        textField.setText(textDefault);
		        textField.setForeground(Color.LIGHT_GRAY);
		      }
		}
	}
}


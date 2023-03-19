import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

@SuppressWarnings("serial")
public class LogareFrame extends JFrame{
	
private JButton conectareButon, inregistrareButon;
private JTextField numeUtilizatorField;
private JPasswordField parolaField;
private JLabel imagineLabel;
private Utilizator p,e;
private ListaProfesor listaProfesor;
private ListaElev listaElev;
private ControllerButoane cb;


public LogareFrame(){
	super("E-Meditatii");
	this.setVisible(true);
	this.setSize(300,600);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	
    listaProfesor =new ListaProfesor();
    listaElev = new ListaElev();
    
	JPanel p = new JPanel(new GridBagLayout());
	p.setPreferredSize(new Dimension(300, 500));
	p.setBackground(Color.LIGHT_GRAY);

	imagineLabel = new JLabel();
	try {
	  Image imagine = ImageIO.read(new File("data/login.png"));
	  imagine = imagine.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
	  imagineLabel.setIcon(new ImageIcon(imagine));
	} catch (IOException e) {
	  e.printStackTrace();
	}
	
	GridBagConstraints c = new GridBagConstraints();
	c.fill = GridBagConstraints.HORIZONTAL;
	c.insets = new Insets(10, 10, 10, 10);
	c.gridx = 0;
	c.gridy = 0;
	p.add(imagineLabel, c);

	numeUtilizatorField = new JTextField("Utilizator");
	numeUtilizatorField.setPreferredSize(new Dimension(200, 30));
	numeUtilizatorField.setMaximumSize(new Dimension(200, 30));
	numeUtilizatorField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
	numeUtilizatorField.setForeground(Color.LIGHT_GRAY);
	numeUtilizatorField.setFont(new Font("Arial", Font.PLAIN, 16));
	numeUtilizatorField.addFocusListener(new ControllerFocus("Utilizator"));
	c.gridx = 0;
	c.gridy = 1;
	p.add(numeUtilizatorField, c);

	parolaField = new JPasswordField("Parola");
	parolaField.setPreferredSize(new Dimension(200, 30));
	parolaField.setMaximumSize(new Dimension(200, 30));
	parolaField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
	parolaField.setForeground(Color.LIGHT_GRAY);
	parolaField.setFont(new Font("Arial", Font.PLAIN, 16));
	parolaField.addFocusListener(new ControllerFocus("Parola"));
	c.gridx = 0;
	c.gridy = 2;
	p.add(parolaField, c);
	
	cb = new ControllerButoane();
	conectareButon = new JButton("Conectare");
	conectareButon.setPreferredSize(new Dimension(100, 30));
	conectareButon.setMaximumSize(new Dimension(100, 30));
	conectareButon.setForeground(new Color(0, 0, 128));
	conectareButon.setFont(new Font("Arial", Font.BOLD, 15));
	conectareButon.addActionListener(cb);
	c.gridx = 0;
	c.gridy = 3;
	p.add(conectareButon, c);
	
	inregistrareButon = new JButton("Inregistrare");
	inregistrareButon.setPreferredSize(new Dimension(100, 30));
	inregistrareButon.setMaximumSize(new Dimension(100, 30));
	inregistrareButon.setForeground(new Color(0, 0, 128));
	inregistrareButon.setFont(new Font("Arial", Font.BOLD, 15));
	inregistrareButon.addActionListener(cb);
	c.gridx = 0;
	c.gridy = 4;
	p.add(inregistrareButon, c);
	
	add(p);
}
private class ControllerButoane implements ActionListener{

	private JFrame  g, f;
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent i){
	if (i.getSource()==conectareButon){
		p=listaProfesor.getUtilizator(numeUtilizatorField.getText()+" "+parolaField.getText());
		e=listaElev.getUtilizator(numeUtilizatorField.getText()+" "+parolaField.getText());
		if (p==null&&e==null){
			JOptionPane.showMessageDialog(null, "Nu exista utilizatorul acesta.",
					"Information", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(p.getClass()==Elev.class){
			JOptionPane.showMessageDialog(null, "Bun Venit " + p.getNume() +"!",
			"Information", JOptionPane.INFORMATION_MESSAGE);
			try {
				f = new ElevFrame(e,listaProfesor,listaElev);
				dispose();
				f.setSize(600,600);
				f.setVisible(true);
				f.setLocationRelativeTo(null);
				f.setDefaultCloseOperation(EXIT_ON_CLOSE);
			} catch (IOException | ParseException e1) {
				e1.printStackTrace();
			}
			f.pack();
			
		}
	 	else if(e.getClass()==Profesor.class){
			JOptionPane.showMessageDialog(null, "Bun Venit " + p.getNume() +"!",
			"Information", JOptionPane.INFORMATION_MESSAGE);
			try {
				f = new ProfesorFrame(p,listaElev,listaProfesor);
				dispose();
				f.setSize(600,600);
				f.setVisible(true);
				f.setLocationRelativeTo(null);
				f.setDefaultCloseOperation(EXIT_ON_CLOSE);
			} catch (IOException | ParseException e1) {
				e1.printStackTrace();
			}
			f.pack();
     		
		}
	}
	if (i.getSource()==inregistrareButon){
	  dispose();
	  try {
		g=new InregistrareFrame(listaProfesor,listaElev);
	  }catch (ClassNotFoundException | IOException e) {
		e.printStackTrace();
	  }
	  g.pack();
	  g.setVisible(true);
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

public static void main (String[] args) throws IOException{
	JFrame f=new LogareFrame();
	f.setVisible(true);
	f.setSize(300,500);
	f.setLocationRelativeTo(null);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }




}
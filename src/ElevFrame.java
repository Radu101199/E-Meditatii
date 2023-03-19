import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;

@SuppressWarnings("serial")
public class ElevFrame extends JFrame{
	private JButton b1,b2,b3;
	private JLabel profilPozaLabel;
	private JPanel p;
	private Image newImage;
	private ImageIcon b1Icon, b2Icon, b3Icon;
	private DefaultTableModel model;
	private SesiuniProgramatePanel panel;
	private SesiuniMeditatieManager s;
	private ArrayList<SesiuneMeditatii> sesiuniProgramate, sesiuniAnulate, sesiuniConfirmate;
	private Utilizator utilizator;
	private ListaElev listaElev;
	private ListaProfesor listaProfesor;
	private ControllerMouse cm;
	private ControllerButoane cb;

	public ElevFrame(Utilizator elev, ListaProfesor listaProfesor, ListaElev listaElev) throws IOException, ParseException{
		super(elev.getNume());
		
		utilizator = elev;
		this.listaProfesor = listaProfesor;
		this.listaElev = listaElev;
		s = new SesiuniMeditatieManager();
		if(s.areSesiuniProgramate(utilizator.getNumeUtilizator())){
			sesiuniProgramate = s.sesiuniProgramateArray(utilizator.getNumeUtilizator());
			panel = new SesiuniProgramatePanel(sesiuniProgramate);
			int optiune = JOptionPane.showConfirmDialog(null, panel, "Programari Primite", JOptionPane.OK_OPTION);
			if(optiune == JOptionPane.OK_OPTION) {
				for(int i=0;i<panel.getGroup().length;i++) {
					int butonCounter=0;
					ButtonGroup group = panel.getGroup()[i];
					for(Enumeration<AbstractButton> butoane = group.getElements(); butoane.hasMoreElements();) {
						JRadioButton buton = (JRadioButton) butoane.nextElement();
						if(buton.isSelected() && butonCounter==0) {
							s.setStatus("Acceptata", sesiuniProgramate.get(i));
							sesiuniProgramate.get(i).setStatus("Acceptata");}
						else if(buton.isSelected() && butonCounter == 1){sesiuniProgramate.get(i).setStatus("Respinsa");
							s.setStatus("Respinsa", sesiuniProgramate.get(i));
						}
						butonCounter++;
					}
				}
			}
		}
		
		if(s.areSesiuniAnulate(utilizator.getNumeUtilizator())) {
			sesiuniAnulate = s.sesiuniRespinseArray(utilizator.getNumeUtilizator());
			SesiuniRespinsePanel panel = new SesiuniRespinsePanel(sesiuniAnulate);
			JOptionPane.showConfirmDialog(null, panel, "Sesiuni Respinse", JOptionPane.OK_OPTION);
			s.stergeSesiuniAnulate(sesiuniAnulate);
		}

		p = new JPanel(new GridBagLayout());
		p.setBackground(new Color(137, 207, 240));
		GridBagConstraints gbc = new GridBagConstraints();
		
		cm=new ControllerMouse();
		profilPozaLabel = new JLabel();
		elev.setProfilePicture(elev.getNumeUtilizator(), profilPozaLabel);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(4, 10, 4, 4);
		p.add(profilPozaLabel,gbc);
		profilPozaLabel.addMouseListener(cm);
		
		cb = new ControllerButoane();
		JPanel butoanePanel = new JPanel(new FlowLayout());
		butoanePanel.setBackground(getForeground());
		
		b1 = new JButton();
		b1Icon = new ImageIcon("data/datepersonale.png");
		Image i1 = b1Icon.getImage();
		Image ni1 = i1.getScaledInstance(100, 28, Image.SCALE_SMOOTH);
		b1Icon = new ImageIcon(ni1);
		b1.setPreferredSize(new Dimension(100,30));
		b1.setMaximumSize(new Dimension(100,30));
		b1.setUI(new BasicButtonUI());
		b1.setBackground(new Color(173, 216, 230));
		b1.setBorder(BorderFactory.createRaisedBevelBorder());
		b1.setIcon(b1Icon);
		butoanePanel.add(b1);
		b1.addActionListener(cb);
	
		b2 = new JButton();
		b2Icon = new ImageIcon("data/programaresesiune.png");
		Image i2 = b2Icon.getImage();
		Image ni2 = i2.getScaledInstance(95, 28, Image.SCALE_SMOOTH);
		b2Icon = new ImageIcon(ni2);
		b2.setPreferredSize(new Dimension(100,30));
		b2.setMaximumSize(new Dimension(100,30));
		b2.setUI(new BasicButtonUI());
		b2.setBackground(new Color(173, 216, 230));
		b2.setBorder(BorderFactory.createRaisedBevelBorder());
		b2.setIcon(b2Icon);
		butoanePanel.add(b2);
		b2.addActionListener(cb);
		
		b3 = new JButton();
		b3Icon = new ImageIcon("data/recenzie.png");
		Image i3 = b3Icon.getImage();
		Image ni3 = i3.getScaledInstance(95, 23, Image.SCALE_SMOOTH);
		b3Icon = new ImageIcon(ni3);
		b3.setPreferredSize(new Dimension(100,30));
		b3.setMaximumSize(new Dimension(100,30));
		b3.setUI(new BasicButtonUI());
		b3.setBackground(new Color(173, 216, 230));
		b3.setBorder(BorderFactory.createRaisedBevelBorder());
		b3.setIcon(b3Icon);
		butoanePanel.add(b3);
		b3.addActionListener(cb);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		butoanePanel.setPreferredSize(new Dimension(600,50));
		p.add(butoanePanel, gbc);
		
		JLabel sesiuniViitoareLabel = new JLabel("Sesiuni Viitoare");
		sesiuniViitoareLabel.setFont(new Font("Arial", Font.BOLD, 15));
		sesiuniViitoareLabel.setPreferredSize(new Dimension(sesiuniViitoareLabel.getText().length(),20));
		sesiuniViitoareLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		sesiuniViitoareLabel.setForeground(new Color(0, 0, 128));
		sesiuniViitoareLabel.setPreferredSize(new Dimension(115,20));
		sesiuniViitoareLabel.setBackground(Color.white);
		gbc.fill=GridBagConstraints.RELATIVE;
		gbc.gridx = 1;
		gbc.gridy = 3;
		p.add(sesiuniViitoareLabel, gbc);
		
		sesiuniConfirmate = s.sesiuniConfirmateArray(utilizator.getNumeUtilizator());
		String[][] data = new String[sesiuniConfirmate.size()][7];
		for(int i=0; i<sesiuniConfirmate.size(); i++) {
		    data[i][0] = sesiuniConfirmate.get(i).getPlanificator();
		    data[i][1] = sesiuniConfirmate.get(i).getDestinatar();
		    data[i][2] = sesiuniConfirmate.get(i).getOra();
		    data[i][3] = sesiuniConfirmate.get(i).getData();
		    data[i][4] = sesiuniConfirmate.get(i).getLocatie();
		    data[i][5] = String.valueOf(sesiuniConfirmate.get(i).getPret());
		}
		JPanel southPanel = new JPanel(new BorderLayout());
		model = new DefaultTableModel(data, new String[] {"Planificator", "Destinatar", "Ora", "Data", "Locatie", "Pret", "Edit"});
		JTable tabel = new JTable(model);
		tabel.setGridColor(Color.BLACK);
		tabel.setBorder(BorderFactory.createRaisedBevelBorder());
		tabel.setFont(new Font("Arial", Font.BOLD, 15));
		tabel.setBackground(new Color(0, 0, 139));
		tabel.setForeground(Color.white);
		
		tabel.getColumn("Edit").setCellRenderer(new RandareButon());
		tabel.getColumn("Edit").setCellEditor(new ButonEditareSesiune(
				new JButton("Edit"),sesiuniConfirmate,model));
		JScrollPane sp = new JScrollPane(tabel);
		sp.setPreferredSize(new Dimension(600,200));
		southPanel.add(sp, BorderLayout.SOUTH);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		p.add(southPanel, gbc);

		add(p);
	}
	
	private class ControllerButoane implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFrame d,p,r;
			if (e.getSource()==b1){
				d = new DatePersonaleFrame(utilizator,listaElev,listaProfesor);
				d.setSize(300,480);
				d.setVisible(true);
				d.setLocationRelativeTo(null);
				d.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				dispose();
			}
			if(e.getSource()==b2) {
				try {
					p = new ProgramareSesiuneMeditatiiFrame(listaProfesor,utilizator);
					p.setSize(700,90);
					p.setVisible(true);
					p.setLocationRelativeTo(null);
					p.setDefaultCloseOperation(DISPOSE_ON_CLOSE);;
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
			}
			if(e.getSource()==b3) {
				try {
					r = new ElevCompletareRecenzieFrame((Elev) utilizator);
					r.setSize(300,250);
					r.setVisible(true);
					r.setLocationRelativeTo(null);
					r.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	private class ControllerMouse implements MouseListener{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Alege o noua poza de profil");
	        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagini", "jpg", "png", "gif"));
	        int valReturnata = fileChooser.showOpenDialog(null);
	        if (valReturnata == JFileChooser.APPROVE_OPTION) {
	            File fisierSelectat = fileChooser.getSelectedFile();
	            ImageIcon newIcon = new ImageIcon(fisierSelectat.getAbsolutePath());
	            newImage = newIcon.getImage().getScaledInstance(225, 225, java.awt.Image.SCALE_SMOOTH);
	            profilPozaLabel.setIcon(new ImageIcon(newImage));
	            
	            BufferedImage pozaRotunda = new BufferedImage(newImage.getWidth(null), newImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			    Graphics g = pozaRotunda.createGraphics();
                g.setClip(new Ellipse2D.Float(0, 0, 225, 225));
                g.drawImage(newImage, 0, 0, null);
                ((Graphics2D) g).setStroke(new BasicStroke(5));
			    g.setColor(Color.BLACK);
			    g.drawOval(0, 0, 224, 224);
                g.dispose();
                newImage = pozaRotunda;
	            profilPozaLabel.setIcon(new ImageIcon(newImage));
	            try {
	            	
	            	File folder = new File("PozaProfil");
	        	    if (!folder.exists()) {
	        	        folder.mkdir();
	        	    }
	        	    
	                String numeFisier = "pozadeprofil" + utilizator.getNumeUtilizator()+".png";
	                File f = new File(folder,numeFisier);
	                FileOutputStream fos = new FileOutputStream(numeFisier);
	                ImageIO.write(pozaRotunda, "png", f);
	                Path path = FileSystems.getDefault().getPath("/Users/paunradu/eclipse-workspace/E-Meditatii/pozadeprofil"+utilizator.getNumeUtilizator()+".png");
	                Files.delete(path);
	                fos.close();
	            } 	catch (Exception ex) {
	            	ex.printStackTrace();
	            }
	        }
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
}

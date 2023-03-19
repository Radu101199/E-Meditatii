import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

@SuppressWarnings("serial")
class ProgramareSesiuneMeditatiiFrame extends JFrame {
	
	private JPanel p, subPanelDestinatar, subPanelOra, subPanelData, subPanelLocatie, subPanelPret, subPanelSpatiu;
	private JLabel comboBoxLabel, oraLabel, dataLabel, locatieLabel, pretLabel, spatiuLabel;
	private JButton b1;
	private JTextField dataField,textFieldLocatie,textFieldPret;
	private JComboBox<String> listaDestinatar;
	private DefaultComboBoxModel<String> list;
	private SpinnerDateModel model1;
	private JSpinner timp;
	private Date data;
	private DateFormat hf;
	private SimpleDateFormat sdf;
	private String stringOra,stringData;
	private SesiuniMeditatieManager s;
	private Utilizator utilizator;
	private ControllerButoane cb;
	
	ProgramareSesiuneMeditatiiFrame(ListaUtilizatori lista,Utilizator utilizator) throws IOException, ClassNotFoundException, Exception{
		
		super("Programare");
		this.utilizator=utilizator;
		
		list=new DefaultComboBoxModel<>();
		if(lista instanceof ListaProfesor){
			((ListaProfesor)lista).listeaza();
			for(Object pers : ListaProfesor.lista){
				list.addElement(((Utilizator)pers).getNumeUtilizator());
			}
		}
		else {
			((ListaElev)lista).listeaza();
			for(Object pers : ListaElev.lista) {
				list.addElement(((Utilizator)pers).getNumeUtilizator());
			}
		}
		
		p=new JPanel();
		p.setBackground(new Color(137, 207, 240));
		p.setLayout(new FlowLayout());
	
		textFieldLocatie = new JTextField(10);
		textFieldPret = new JTextField(10);
		
		comboBoxLabel = new JLabel("Destinatar");
		comboBoxLabel.setFont(new Font("Arial", Font.BOLD, 15));
		comboBoxLabel.setForeground(Color.white);
		oraLabel = new JLabel("Ora");
		oraLabel.setFont(new Font("Arial", Font.BOLD, 15));
		oraLabel.setForeground(Color.white);
		dataLabel = new JLabel("Data");
		dataLabel.setFont(new Font("Arial", Font.BOLD, 15));
		dataLabel.setForeground(Color.white);
		locatieLabel = new JLabel("Locatie");
		locatieLabel.setFont(new Font("Arial", Font.BOLD, 15));
		locatieLabel.setForeground(Color.white);
		pretLabel = new JLabel("Pret");
		pretLabel.setFont(new Font("Arial", Font.BOLD, 15));
		pretLabel.setForeground(Color.white);
		spatiuLabel = new JLabel(" ");
		
		
		subPanelDestinatar = new JPanel();
		listaDestinatar = new JComboBox<>(list);
		subPanelDestinatar.setLayout(new BorderLayout());
		subPanelDestinatar.add(comboBoxLabel, BorderLayout.NORTH);
		subPanelDestinatar.add(listaDestinatar, BorderLayout.CENTER);
		subPanelDestinatar.setBorder(BorderFactory.createRaisedBevelBorder());
		subPanelDestinatar.setBackground(new Color(0,0,128));
		p.add(subPanelDestinatar);
		
		subPanelOra = new JPanel();
		model1 = new SpinnerDateModel();
		model1.setCalendarField(Calendar.HOUR_OF_DAY);
		timp = new JSpinner(model1);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(timp, "HH:mm");
		timp.setEditor(editor);
		subPanelOra.setLayout(new BorderLayout());
		subPanelOra.add(oraLabel, BorderLayout.NORTH);
		subPanelOra.add(timp, BorderLayout.CENTER);
		hf = new SimpleDateFormat("HH:mm");
		subPanelOra.setBorder(BorderFactory.createRaisedBevelBorder());
		subPanelOra.setBackground(new Color(0,0,128));
		p.add(subPanelOra);

		subPanelData = new JPanel();
		Calendar cal = Calendar.getInstance();
		data = cal.getTime();
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		stringData = sdf.format(data);
		dataField = new JTextField(stringData);
		subPanelData.setLayout(new BorderLayout());
		subPanelData.add(dataLabel, BorderLayout.NORTH);
		subPanelData.add(dataField, BorderLayout.CENTER);
		subPanelData.setBorder(BorderFactory.createRaisedBevelBorder());
		subPanelData.setBackground(new Color(0,0,128));
		p.add(subPanelData);

		subPanelLocatie = new JPanel();
		subPanelLocatie.setLayout(new BorderLayout());
		subPanelLocatie.add(locatieLabel, BorderLayout.NORTH);
		subPanelLocatie.add(textFieldLocatie, BorderLayout.CENTER);
		subPanelLocatie.setBorder(BorderFactory.createRaisedBevelBorder());
		subPanelLocatie.setBackground(new Color(0,0,128));
		p.add(subPanelLocatie);
		
		subPanelPret = new JPanel();
		subPanelPret.setLayout(new BorderLayout());
		subPanelPret.add(pretLabel, BorderLayout.NORTH);
		subPanelPret.add(textFieldPret, BorderLayout.CENTER);
		subPanelPret.setBorder(BorderFactory.createRaisedBevelBorder());
		subPanelPret.setBackground(new Color(0,0,128));
		p.add(subPanelPret);

		subPanelSpatiu = new JPanel();
		subPanelSpatiu.setLayout(new BorderLayout());
		subPanelSpatiu.add(spatiuLabel,BorderLayout.NORTH);
		cb = new ControllerButoane();
		b1 = new JButton("Programare");
	    b1.setBackground(new Color(0, 0, 128));
	    b1.setForeground(Color.black);
	    b1.setFont(new Font("Arial", Font.BOLD, 16));
	    b1.setBorder(BorderFactory.createRaisedBevelBorder());
		b1.addActionListener(cb);
		subPanelSpatiu.add(b1, BorderLayout.CENTER);
		subPanelSpatiu.setBackground(getForeground());
		p.add(subPanelSpatiu);
		
		add(p);
	}
	
	private class ControllerButoane implements ActionListener{
	
	@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource()==b1) {
				s = new SesiuniMeditatieManager();
				stringOra = hf.format(timp.getValue());
				stringData = dataField.getText();
					try {
						s.programeazaSedinta((String)utilizator.getNumeUtilizator(),(String)list.getSelectedItem(),stringOra,stringData,textFieldLocatie.getText().replaceAll("\\s",""),Integer.parseInt(textFieldPret.getText()),"-");
					} catch (NumberFormatException | ParseException e1) {
						e1.printStackTrace();
					}
			}
		}
	}
}

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings({"serial","unused"})
public class EditareSesiuneDialog extends JDialog {
    private JLabel planificatorField, destinatarField;
    private JTextField dataField, locatieField, pretField;
    private String stringOra,stringData;
    private JButton confirmareButon, anulareButon;
    private SesiuneMeditatii sesiune;
    private SpinnerDateModel mod;
    private JSpinner timp;
	private JTable tabel;
    private DefaultTableModel model;
    private DateFormat hf;
    private SesiuniMeditatieManager sistem;
    private ControllerButoane cb;
    
    public EditareSesiuneDialog(SesiuneMeditatii sesiune,JTable tabel, DefaultTableModel model) throws ParseException{
        this.sesiune = sesiune;
        this.tabel = tabel;
        this.model = model;
        setTitle("Editare Sesiune");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(400,600);

		JPanel formPanel = new JPanel(new GridLayout(7, 2));
		formPanel.setBackground(new Color(137, 207, 240));

		JLabel planificatorLabel = new JLabel("Planificator:");
		planificatorLabel.setFont(new Font("Arial", Font.BOLD, 16));
		formPanel.add(planificatorLabel);

		planificatorField = new JLabel(sesiune.getPlanificator());
		planificatorField.setFont(new Font("Arial", Font.PLAIN, 16));
		planificatorField.setBorder(BorderFactory.createRaisedBevelBorder());
		planificatorField.setOpaque(true);
		planificatorField.setForeground(Color.white);
		planificatorField.setBackground(new Color(0, 0, 128));
		formPanel.add(planificatorField);

		JLabel destinatarLabel = new JLabel("Destinatar:");
		destinatarLabel.setFont(new Font("Arial", Font.BOLD, 16));
		formPanel.add(destinatarLabel);

		destinatarField = new JLabel(sesiune.getDestinatar());
		destinatarField.setFont(new Font("Arial", Font.PLAIN, 16));
		destinatarField.setBorder(BorderFactory.createRaisedBevelBorder());
		destinatarField.setOpaque(true);
		destinatarField.setForeground(Color.white);
		destinatarField.setBackground(new Color(0, 0, 128));
		formPanel.add(destinatarField);

		JLabel oraLabel = new JLabel("Ora:");
		oraLabel.setFont(new Font("Arial", Font.BOLD, 16));
		formPanel.add(oraLabel);

		mod = new SpinnerDateModel();
		mod.setCalendarField(Calendar.HOUR_OF_DAY);
		timp = new JSpinner(mod);
		timp.setFont(new Font("Arial", Font.PLAIN, 16));
		timp.setBorder(BorderFactory.createRaisedBevelBorder());
		timp.setBackground(new Color(0, 0, 128));
		timp.setForeground(new Color(0, 0, 128));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(timp, "HH:mm");
		timp.setEditor(editor);
		String oraPrestabilita = sesiune.getOra();
		hf = new SimpleDateFormat("HH:mm");
		Date oraPrestabilitaDate = hf.parse(oraPrestabilita);
		timp.setValue(oraPrestabilitaDate);
		formPanel.add(timp);

		JLabel dataLabel = new JLabel("Data:");
		dataLabel.setFont(new Font("Arial", Font.BOLD, 16));
		formPanel.add(dataLabel);

		String dataPrestabilita = sesiune.getData();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse(dataPrestabilita);
		stringData = sdf.format(date);
		dataField = new JTextField(stringData);
		dataField.setFont(new Font("Arial", Font.PLAIN, 16));
		dataField.setBorder(BorderFactory.createRaisedBevelBorder());
		dataField.setBackground(new Color(0, 0, 128));
		dataField.setForeground(Color.white);
		formPanel.add(dataField);

		JLabel locatieLabel = new JLabel("Locatie:");
		locatieLabel.setFont(new Font("Arial", Font.BOLD, 16));
		formPanel.add(locatieLabel);

		locatieField = new JTextField(sesiune.getLocatie());
		locatieField.setFont(new Font("Arial", Font.PLAIN, 16));
		locatieField.setBorder(BorderFactory.createRaisedBevelBorder());
		locatieField.setBackground(new Color(0, 0, 128));
		locatieField.setForeground(Color.white);
		formPanel.add(locatieField);

		JLabel pretLabel = new JLabel("Pret:");
		pretLabel.setFont(new Font("Arial", Font.BOLD, 16));
		formPanel.add(pretLabel);

		pretField = new JTextField(String.valueOf(sesiune.getPret()));
		pretField.setFont(new Font("Arial", Font.PLAIN, 16));
		pretField.setBorder(BorderFactory.createRaisedBevelBorder());
		pretField.setBackground(new Color(0, 0, 128));
		pretField.setForeground(Color.white);
		formPanel.add(pretField);

		cb = new ControllerButoane();
		sistem = new SesiuniMeditatieManager();

		anulareButon = new JButton("Anulare");
		anulareButon.setUI(new BasicButtonUI());
		anulareButon.setBackground(new Color(136, 8, 8));
		anulareButon.setFont(new Font("Arial", Font.BOLD, 16));
		anulareButon.setForeground(Color.WHITE);
		anulareButon.setBorder(BorderFactory.createRaisedBevelBorder());
		anulareButon.addActionListener(cb);
		formPanel.add(anulareButon);
		
		confirmareButon = new JButton("Confirmare");
		confirmareButon.setUI(new BasicButtonUI());
		confirmareButon.setBackground(new Color(0, 0, 128));
		confirmareButon.setFont(new Font("Arial", Font.BOLD, 16));
		confirmareButon.setForeground(Color.white);
		confirmareButon.setBorder(BorderFactory.createRaisedBevelBorder());
		confirmareButon.addActionListener(cb);
		formPanel.add(confirmareButon);
		
		add(formPanel);

    }
    private class ControllerButoane implements ActionListener{
    	
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		if(e.getSource()==confirmareButon){
                try {
    				sistem.editareSesiune(sesiune, hf.format(timp.getValue()), dataField.getText(), locatieField.getText().replaceAll("\\s",""), Integer.parseInt(pretField.getText()));
    				model.addRow(new Object[] {sesiune.getPlanificator(), sesiune.getDestinatar(), "Sesiune", "in", "asteptare", "", ""});
                } catch (IOException e1) {
    				e1.printStackTrace();
    				}
                    dispose();
            }
    		if(e.getSource()==anulareButon){
               sesiune.setStatus("Respinsa");
                    try {
    				sistem.setStatus("Respinsa",sesiune);
    				model.addRow(new Object[] {sesiune.getPlanificator(), sesiune.getDestinatar(), "Sesiune", "respinsa", "", "", ""});
    				} catch (IOException e1) {
    				e1.printStackTrace();
    				}
               dispose();
             }
    	}
    }
}
        
               

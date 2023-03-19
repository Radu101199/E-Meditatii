import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings( "serial" )
class SesiuniProgramatePanel extends JPanel {
    
    @SuppressWarnings("unused")
	private ArrayList<SesiuneMeditatii> sesiuni;
    private ButtonGroup[] group;
    
    public SesiuniProgramatePanel(ArrayList<SesiuneMeditatii> sesiuni){
    	this.sesiuni = sesiuni;
    	setLayout(new GridBagLayout());
    	setBackground(Color.LIGHT_GRAY);
    	GridBagConstraints c = new GridBagConstraints();
    	c.gridx = 0;
    	c.gridy = 0;
    	c.gridwidth = 1;
    	c.gridheight = 1;
    	c.anchor = GridBagConstraints.LINE_START;
    	c.insets = new Insets(5, 5, 5, 5);

    	JLabel labelPlanificator = new JLabel("Planificator");
    	labelPlanificator.setFont(new Font("Arial", Font.BOLD, 14));
    	labelPlanificator.setBorder(BorderFactory.createRaisedBevelBorder());
    	labelPlanificator.setBackground(new Color(0, 0, 128));
    	labelPlanificator.setForeground(Color.white);
    	labelPlanificator.setOpaque(true);
    	this.add(labelPlanificator, c);

    	c.gridx = 1;
    	JLabel labelDestinatar = new JLabel("Destinatar");
    	labelDestinatar.setFont(new Font("Arial", Font.BOLD, 14));
    	labelDestinatar.setBorder(BorderFactory.createRaisedBevelBorder());
    	labelDestinatar.setBackground(new Color(0, 0, 128));
    	labelDestinatar.setForeground(Color.white);
    	labelDestinatar.setOpaque(true);
    	this.add(labelDestinatar, c);

    	c.gridx = 2;
    	JLabel labelOra = new JLabel("Ora");
    	labelOra.setFont(new Font("Arial", Font.BOLD, 14));
    	labelOra.setBorder(BorderFactory.createRaisedBevelBorder());
    	labelOra.setBackground(new Color(0, 0, 128));
    	labelOra.setForeground(Color.white);
    	labelOra.setOpaque(true);
    	this.add(labelOra, c);

    	c.gridx = 3;
    	JLabel labelData = new JLabel("Data");
    	labelData.setFont(new Font("Arial", Font.BOLD, 14));
    	labelData.setBorder(BorderFactory.createRaisedBevelBorder());
    	labelData.setBackground(new Color(0, 0, 128));
    	labelData.setForeground(Color.white);
    	labelData.setOpaque(true);
    	this.add(labelData, c);

    	c.gridx = 4;
    	JLabel labelLocatie = new JLabel("Locatie");
    	labelLocatie.setFont(new Font("Arial", Font.BOLD, 14));
    	labelLocatie.setBorder(BorderFactory.createRaisedBevelBorder());
    	labelLocatie.setBackground(new Color(0, 0, 128));
    	labelLocatie.setForeground(Color.white);
    	labelLocatie.setOpaque(true);
    	this.add(labelLocatie, c);

    	c.gridx = 5;
    	JLabel labelPret = new JLabel("Pret");
    	labelPret.setFont(new Font("Arial", Font.BOLD, 14));
    	labelPret.setBorder(BorderFactory.createRaisedBevelBorder());
    	labelPret.setBackground(new Color(0, 0, 128));
    	labelPret.setForeground(Color.white);
    	labelPret.setOpaque(true);
    	this.add(labelPret, c);

    	c.gridx = 6;
    	JLabel labelAccept = new JLabel("Accepta");
    	labelAccept.setFont(new Font("Arial", Font.BOLD, 14));
    	labelAccept.setBorder(BorderFactory.createRaisedBevelBorder());
    	labelAccept.setBackground(new Color(137, 207, 240));
    	labelAccept.setForeground(Color.white);
    	labelAccept.setOpaque(true);
    	this.add(labelAccept, c);

    	c.gridx = 7;
    	JLabel labelRespinge = new JLabel("Respinge");
    	labelRespinge.setFont(new Font("Arial", Font.BOLD, 14));
    	labelRespinge.setBorder(BorderFactory.createRaisedBevelBorder());
    	labelRespinge.setBackground(new Color(136, 8, 8));
    	labelRespinge.setForeground(Color.white);
    	labelRespinge.setOpaque(true);
    	this.add(labelRespinge, c);
    	
    	this.group = new ButtonGroup[sesiuni.size()];
    	for (int i = 0; i < sesiuni.size(); i++) {
    	    final int sessionIndex = i;
    	    this.group[sessionIndex] = new ButtonGroup();
    	    c.gridx = 0;
    	    c.gridy = i + 1;
    	    JLabel planificatorLabel = new JLabel(sesiuni.get(i).getPlanificator());
    	    planificatorLabel.setFont(new Font("Arial", Font.BOLD, 14));
    	    this.add(planificatorLabel, c);
    	    c.gridx = 1;
    	    JLabel destinatarLabel = new JLabel(sesiuni.get(i).getDestinatar());
    	    destinatarLabel.setFont(new Font("Arial", Font.BOLD, 14));
    	    this.add(destinatarLabel, c);
    	    c.gridx = 2;
    	    JLabel dataLabel = new JLabel(sesiuni.get(i).getOra());
    	    dataLabel.setFont(new Font("Arial", Font.BOLD, 14));
    	    this.add(dataLabel, c);
    	    c.gridx=3;
    	    JLabel oraLabel = new JLabel(sesiuni.get(i).getData());
    	    oraLabel.setFont(new Font("Arial", Font.BOLD, 14));
    	    this.add(oraLabel, c);
    	    c.gridx = 4;
    	    JLabel locatieLabel = new JLabel(sesiuni.get(i).getLocatie());
    	    locatieLabel.setFont(new Font("Arial", Font.BOLD, 14));
    	    this.add(locatieLabel, c);
    	    c.gridx = 5;
    	    JLabel pretLabel = new JLabel(String.valueOf(sesiuni.get(i).getPret()));
    	    pretLabel.setFont(new Font("Arial", Font.BOLD, 14));
    	    this.add(pretLabel, c);
    	    JRadioButton butonAcceptare = new JRadioButton();
    	    butonAcceptare.setBorder(BorderFactory.createRaisedBevelBorder());
    	    c.gridx = 6;
    	    this.add(butonAcceptare, c);
    	    this.group[sessionIndex].add(butonAcceptare);
    	    butonAcceptare.addActionListener(new ActionListener(){
    	        public void actionPerformed(ActionEvent e){
    	            if(butonAcceptare.isSelected())
    	                sesiuni.get(sessionIndex).setStatus("Acceptat");
    	        }
    	    });

    	    c.gridx = 7;
    	    JRadioButton butonRespingere = new JRadioButton();
    	    butonRespingere.setBorder(BorderFactory.createRaisedBevelBorder());
    	    this.add(butonRespingere, c);
    	    this.group[sessionIndex].add(butonRespingere);
    	    butonRespingere.addActionListener(new ActionListener(){
    	        public void actionPerformed(ActionEvent e){
    	            if(butonRespingere.isSelected())
    	                sesiuni.get(sessionIndex).setStatus("Respinsa");
    	        }
    	    });

    	}
    }
    
	public ButtonGroup[] getGroup() {
		return group;
	}
}
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class SesiuniRespinsePanel extends JPanel{
	
	    @SuppressWarnings("unused")
		private ArrayList<SesiuneMeditatii> sesiuni;
		
	    public SesiuniRespinsePanel(ArrayList<SesiuneMeditatii> sesiuni){
	    	
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

    	for (int i = 0; i < sesiuni.size(); i++) {
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
    	    
    	}
    }
    
}
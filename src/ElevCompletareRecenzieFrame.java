import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings({"serial","unused"})
public class ElevCompletareRecenzieFrame extends JFrame {
	
	private JLabel selecteaza;
    private JList<String> listaSesiuni;
    private ArrayList<SesiuneMeditatii> sesiuniCompletate;
    private DefaultListModel<String> listModel;
    private RecenzieManager manager;
    private SesiuniMeditatieManager s;
    private Elev elev;
    private ControllerMouse cm;
    
    public  ElevCompletareRecenzieFrame(Elev elev) throws ParseException {
        setTitle("Scrie o Recenzie");
        this.elev = elev;
        manager = new RecenzieManager();
        s = new SesiuniMeditatieManager();
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(137, 207, 240));
        
        listModel = new DefaultListModel<>();
        sesiuniCompletate = s.sesiuniCompletateArray(elev.getNumeUtilizator());
        for(SesiuneMeditatii s : sesiuniCompletate) {
        	listModel.addElement(s.toString());
        }
        listaSesiuni = new JList<>(listModel);
        JScrollPane listaSesiuniScrollPane = new JScrollPane(listaSesiuni);
        listaSesiuniScrollPane.setBackground(Color.DARK_GRAY);
        listaSesiuniScrollPane.setBorder(BorderFactory.createRaisedBevelBorder());
        panel.add(listaSesiuniScrollPane);
        
        selecteaza = new JLabel("Selecteaza una din sesiuni!");
        selecteaza.setFont(new Font("Arial", Font.BOLD, 15));
        selecteaza.setBorder(BorderFactory.createRaisedBevelBorder());
        selecteaza.setBackground(Color.white);
        panel.add(selecteaza);
        cm = new ControllerMouse();
        listaSesiuni.addMouseListener(cm);

        add(panel);
    }
    
    private class ControllerMouse implements MouseListener{
		
		@Override
		public void mouseClicked(MouseEvent evt) {
			@SuppressWarnings("rawtypes")
			JList list = (JList) evt.getSource();
            if (evt.getClickCount() == 2) {
                int index = list.locationToIndex(evt.getPoint());
                String sesiuneSelectata = (String) list.getModel().getElementAt(index);
                Recenzie recenzie = manager.getRecenzieElev();
                dispose();
                if (recenzie != null) {
                	String[] parts = sesiuneSelectata.split(" ");
                	try {
    					s.stergeSesiune(parts[0], parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]));
    				} catch (NumberFormatException e) {
    					e.printStackTrace();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
                    manager.salveazaRecenzie(recenzie,sesiuneSelectata);
                    JOptionPane.showMessageDialog(ElevCompletareRecenzieFrame.this, "Recenzie trimisa cu succes!");
                    dispose();
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
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
    	}
}


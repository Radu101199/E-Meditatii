import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ProfesorVizualizareRecenzieFrame extends JFrame {
	private String profesorUsername;
    private JFrame frame;
    private JList<String> recenzii;
    private RecenzieManager manager;
    private ControllerMouse cm;
    
    public ProfesorVizualizareRecenzieFrame(String profesorUsername) {
        this.profesorUsername = profesorUsername;
        manager = new RecenzieManager();
        recenzii = new JList<String>();
        frame = new JFrame();
        frame.setTitle("Recenzii pentru " + this.profesorUsername);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(137, 207, 240));
        recenzii = manager.displayRecenzii(profesorUsername);
        recenzii.setBorder(BorderFactory.createRaisedBevelBorder());
        JScrollPane recenziiList = new JScrollPane(recenzii);
        recenziiList.setPreferredSize(new Dimension(250, 80));
        recenziiList.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        cm = new ControllerMouse();
        recenzii.addMouseListener((MouseListener) cm);
        panel.add(recenziiList);
        
        JLabel label = new JLabel("Selectati o recenzie!");
        label.setBackground(new Color(0, 0, 128));
        label.setOpaque(true);
        label.setBorder(BorderFactory.createRaisedBevelBorder());
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(200, 30));
        panel.add(label);
        
        add(panel);
    }
    private class ControllerMouse implements MouseListener{
			
			@Override
			public void mouseClicked(MouseEvent e) {
				@SuppressWarnings("rawtypes")
				JList list = (JList) e.getSource();
				if(e.getClickCount()==2) {
					 String selectedFileName = (String) list.getSelectedValue();
	                    manager.displayRecenzie(selectedFileName);
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

        

   
    
    

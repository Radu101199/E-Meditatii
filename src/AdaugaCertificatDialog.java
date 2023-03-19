import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

@SuppressWarnings({"serial","unused"})
public class AdaugaCertificatDialog extends JDialog {

    private JButton adaugaCertificatButon, vizualizareCertificateButon;
    private JPanel butonPanel;
    private JList<String> materiiList;
    private DefaultListModel<String> materiiListModel;
    private File certificatFolder;
    private Profesor profesor;
    private ControllerButoane cb;

    public AdaugaCertificatDialog(Profesor profesor) throws IOException {
        this.profesor = profesor;
        setTitle("Certificari");
        setLayout(new BorderLayout());
        
        certificatFolder = new File("certificari" + profesor.getNumeUtilizator());
        if (!certificatFolder.exists()) {
        	certificatFolder.mkdir();
        }
        materiiListModel = new DefaultListModel<>();
        for (String subject : profesor.getMateriiPredate()) {
        	materiiListModel.addElement(subject);
        }
        materiiList = new JList<>(materiiListModel);
        materiiList.setFont(new Font("Arial", Font.PLAIN, 14));
        materiiList.setBackground(new Color(137, 207, 240));
        JScrollPane scrollPane = new JScrollPane(materiiList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);
        
        cb = new ControllerButoane();
        adaugaCertificatButon = new JButton("Adauga certificat");
        vizualizareCertificateButon = new JButton("Vezi certificat");
        adaugaCertificatButon.addActionListener(cb);
        vizualizareCertificateButon.addActionListener(cb);
        adaugaCertificatButon.setBorder(BorderFactory.createRaisedBevelBorder());
        vizualizareCertificateButon.setBorder(BorderFactory.createRaisedBevelBorder());
        adaugaCertificatButon.setForeground(Color.white);
        vizualizareCertificateButon.setForeground(Color.white);
        
        butonPanel = new JPanel();
        butonPanel.setBackground(new Color(0,0,128));
        butonPanel.add(adaugaCertificatButon);
        butonPanel.add(vizualizareCertificateButon);
        add(butonPanel, BorderLayout.SOUTH);
           
       }
    private class ControllerButoane implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==adaugaCertificatButon) {
				String materie = materiiList.getSelectedValue();
	            if (materie == null) {
	                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Selectati o materie", "Eroare", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	
	            File materieFolder = new File(certificatFolder, materie);
	            if (!materieFolder.exists()) {
	                materieFolder.mkdir();
	            }
	
	            JFileChooser selectareFisier = new JFileChooser();
	            String[] extensiiPermise = {".pdf", ".jpg"};
	            FileNameExtensionFilter filtru = new FileNameExtensionFilter("PDF si JPG fisiere", "pdf", "jpg");
	            selectareFisier.setFileFilter(filtru);
	            
	            int returnVal = selectareFisier.showOpenDialog(JOptionPane.getRootFrame());
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File fisier = selectareFisier.getSelectedFile();
	                String numeFisier = fisier.getName();
	                String extensieFisier = numeFisier.substring(numeFisier.lastIndexOf("."));
	                if (Arrays.asList(extensiiPermise).contains(extensieFisier)){
						try {
						    File sursa = selectareFisier.getSelectedFile();
						    File destinatie = new File(materieFolder, materie + extensieFisier);
						    Files.copy(sursa.toPath(), destinatie.toPath(), StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException ex) {
						    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Eroare la copierea fisierului", "Eroare", JOptionPane.ERROR_MESSAGE);
						}
	                } 
	                else {
	                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Fisierul selectat nu este acceptat. Va rugam sa selectati un fisier cu extensia .pdf sau .jpg", "Eroare", JOptionPane.ERROR_MESSAGE);
	                }
	            }
			}
			if(e.getSource()==vizualizareCertificateButon){
				String materie = materiiList.getSelectedValue();
				if (materie == null){
					JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Selectati o materie", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}

				File materieFolder = new File(certificatFolder, materie);
				if (!materieFolder.exists()){
                  JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Nu exista certificat pentru aceasta materie", "Eroare", JOptionPane.ERROR_MESSAGE);
                  return;
				}
				try {
					Desktop.getDesktop().open(materieFolder);
				} catch (IOException ex) {
                  JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Eroare la deschiderea folderului", "Eroare", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
    	
    }
    public static void SchimbareNumeUtilizatorFolder(Profesor profesor, String usernameVechi){
    	File vechiCertificateFolder = new File("certificari" + usernameVechi);
        File nouCertificateFolder = new File("certificari" + profesor.getNume());
        vechiCertificateFolder.renameTo(nouCertificateFolder);
    }
}
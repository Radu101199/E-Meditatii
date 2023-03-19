import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RecenzieManager {
	private JButton printButon;
	private StringBuilder recenzieText;
	private ControllerPrintButton cpb;
	public Recenzie getRecenzieElev(){
		JPanel recenziePanel = new JPanel();
		recenziePanel.setLayout(new BoxLayout(recenziePanel, BoxLayout.Y_AXIS));
		recenziePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		recenziePanel.setBackground(Color.WHITE);

	    JTextArea recenzieArea = new JTextArea(5, 20);
	    recenzieArea.setLineWrap(true);
	    recenzieArea.setWrapStyleWord(true);
	    recenzieArea.setPreferredSize(new Dimension(200, 100));
	    recenzieArea.setFont(new Font("Arial", Font.PLAIN, 16));
	    recenzieArea.setForeground(Color.BLACK);

	    JLabel recenzieLabel = new JLabel("Recenzie");
	    recenzieLabel.setForeground(Color.BLACK);
	    recenzieLabel.setFont(new Font("Arial", Font.BOLD, 16));
	    recenziePanel.add(recenzieLabel);
	    recenziePanel.add(new JScrollPane(recenzieArea));

	    JTextField notaField = new JTextField();
	    notaField.setPreferredSize(new Dimension(200, 30));
	    notaField.setFont(new Font("Arial", Font.PLAIN, 16));
	    notaField.setForeground(Color.BLACK);
	    
	    JLabel notaLabel = new JLabel("Nota");
	    notaLabel.setForeground(Color.BLACK);
	    notaLabel.setFont(new Font("Arial", Font.BOLD, 16));
	    recenziePanel.add(notaLabel);
	    recenziePanel.add(notaField);

	    JCheckBox recomandCheck = new JCheckBox("Recomand");
	    recomandCheck.setForeground(Color.BLACK);
	    recomandCheck.setFont(new Font("Arial", Font.BOLD, 16));
	    recenziePanel.add(recomandCheck);


        int rezultat = JOptionPane.showConfirmDialog(null, recenziePanel, "Recenzie", JOptionPane.OK_CANCEL_OPTION);
        if (rezultat == JOptionPane.OK_OPTION) {
            String recenzieText = recenzieArea.getText();
            double nota = Double.parseDouble(notaField.getText());
            boolean recomand = recomandCheck.isSelected();
            return new Recenzie(recenzieText, nota, recomand);
        } 
        else {
            return null;
        }
    }
	
	public void salveazaRecenzie(Recenzie recenzie, String sesiune) {
		File folder = new File("recenzii");
	    if (!folder.exists()) {
	        folder.mkdir();
	    }
	    String[] parts = sesiune.split(" ");
        String fileName = parts[0] + "_" + parts[1] + ".txt";

        File file = new File(folder, fileName);
        
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("Recenzie text: \n" + recenzie.getRecenzie());
            bufferedWriter.newLine();
            bufferedWriter.write("Nota: " + recenzie.getNota());
            bufferedWriter.newLine();
            bufferedWriter.write("Recomandare: " + recenzie.isRecomandare());

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
		public JList<String> displayRecenzii(String username){
		    List<String> recenziiFisierNume = new ArrayList<String>();

		    File recenziiFolder = new File("recenzii");
		    File[] recenziiFisiere = recenziiFolder.listFiles();

		    for (File recenzieFisier : recenziiFisiere){
		        String fileName = recenzieFisier.getName();
		        if (fileName.contains(username+"_")||fileName.contains("_"+username)){
		        	recenziiFisierNume.add(fileName);
		        }
		    }
		    JList<String> reviewFilesList = new JList<>(recenziiFisierNume.toArray(new String[0]));
		    
		    return reviewFilesList;
		}

		public void displayRecenzie(String recenzieFisierNume) {
		    File recenzieFile = new File("recenzii/" + recenzieFisierNume);
		    try {
		        FileReader fileReader = new FileReader(recenzieFile);
		        BufferedReader bufferedReader = new BufferedReader(fileReader);

		        recenzieText = new StringBuilder();

		        String line = bufferedReader.readLine();
		        while (line != null) {
		            recenzieText.append(line);
		            recenzieText.append(System.lineSeparator());
		            line = bufferedReader.readLine();
		        }

		        JOptionPane optionPane = new JOptionPane(recenzieText.toString(), JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{});
		        JDialog dialog = optionPane.createDialog(null, "Review");
		        printButon = new JButton("Print");
		        cpb = new ControllerPrintButton();
		        printButon.addActionListener(cpb);
		        dialog.add(printButon, BorderLayout.SOUTH);
		        dialog.setSize(400,400);
		        dialog.setVisible(true);

		        bufferedReader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		
		private class ControllerPrintButton implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==printButon) {
					PrinterJob job = PrinterJob.getPrinterJob();
	                job.setPrintable(new Printable() {
	                    @Override
	                    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
	                        if (pageIndex > 0) {
	                            return NO_SUCH_PAGE;
	                        }

	                        graphics.drawString(recenzieText.toString(), 75, 100);
	                        return PAGE_EXISTS;
	                    }
	                });
	                try {
	                    job.print();
	                } catch (PrinterException ex) {
	                    ex.printStackTrace();
	                }
				}
			}
			
		}
}

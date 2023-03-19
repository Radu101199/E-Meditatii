import java.io.*;
import java.nio.file.Files;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

public class SesiuniMeditatieManager {

	    @SuppressWarnings("deprecation")
		public void programeazaSedinta(String planificator, String destinatar, String ora, String data,String loc, int pret, String status) throws ParseException{
	        SesiuneMeditatii sesiune = new SesiuneMeditatii(planificator, destinatar, ora, data, loc , pret, status);
	        try {
	            FileWriter fw = new FileWriter("/Users/paunradu/eclipse-workspace/E-Meditatii/src/sesiuni.txt", true);
	            BufferedWriter bw = new BufferedWriter(fw);
	            PrintWriter out = new PrintWriter(bw);
	            
	            SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat oraFormat = new SimpleDateFormat("HH:mm");

                Date sesiuneData = dataFormat.parse(sesiune.getData());
                Date sesiuneOra = oraFormat.parse(sesiune.getOra());
                
                
                Calendar sesiuneCal = Calendar.getInstance();
                sesiuneCal.setTime(sesiuneData);
                sesiuneCal.set(Calendar.HOUR_OF_DAY, sesiuneOra.getHours());
                sesiuneCal.set(Calendar.MINUTE, sesiuneOra.getMinutes());
                
                Calendar curentCal = Calendar.getInstance();
                if(curentCal.after(sesiuneCal)) {
                	JOptionPane.showMessageDialog(null, "Va rugam selectati o data valida!", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
                	else if(pret<=0) {
                		JOptionPane.showMessageDialog(null, "Va rugam introduceti un pret valid!", "Eroare", JOptionPane.ERROR_MESSAGE);
                	}
                	else {
                		out.println(sesiune.getPlanificator() + " " + sesiune.getDestinatar() + " " + sesiune.getOra() + " " + sesiune.getData() + " " + sesiune.getLocatie() + " " + sesiune.getPret() + " " + sesiune.getStatus());
	            		out.close();
	            		JOptionPane.showMessageDialog(null, "Sesiune programata cu succes!", "Programare" , JOptionPane.INFORMATION_MESSAGE);
                	}
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public ArrayList<SesiuneMeditatii> sesiuniProgramateArray(String username) {
	        ArrayList<SesiuneMeditatii> sesiuniProgramate = new ArrayList<>();
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader("/Users/paunradu/eclipse-workspace/E-Meditatii/src/sesiuni.txt"));
	            String line;
	            while((line = reader.readLine()) != null) {
	                String[] parts = line.split(" ");
	                if(parts[1].equals(username)&&parts[6].equals("-")) {
	                    sesiuniProgramate.add(new SesiuneMeditatii(parts[0],parts[1],parts[2],parts[3],parts[4],Integer.parseInt(parts[5]),parts[6]));
	                }
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return sesiuniProgramate;
	    }
	    
	    public ArrayList<SesiuneMeditatii> sesiuniRespinseArray(String username) {
	        ArrayList<SesiuneMeditatii> sesiuniRespinse = new ArrayList<>();
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader("/Users/paunradu/eclipse-workspace/E-Meditatii/src/sesiuni.txt"));
	            String line;
	            while((line = reader.readLine()) != null) {
	                String[] parts = line.split(" ");
	                if(parts[0].equals(username)&&parts[6].equals("Respinsa")) {
	                    sesiuniRespinse.add(new SesiuneMeditatii(parts[0],parts[1],parts[2],parts[3],parts[4],Integer.parseInt(parts[5]),parts[6]));
	                }
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return sesiuniRespinse;
	    }
	    
	    @SuppressWarnings("deprecation")
		public ArrayList<SesiuneMeditatii> sesiuniConfirmateArray(String username) throws ParseException {
	        ArrayList<SesiuneMeditatii> sesiuniConfirmate = new ArrayList<>();
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader("/Users/paunradu/eclipse-workspace/E-Meditatii/src/sesiuni.txt"));
	            String line;
	            while((line = reader.readLine()) != null) {
	            	
	                String[] parts = line.split(" ");
	                String sessionDateString = parts[3];
	                String sessionHourString = parts[2];

	                SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
	                SimpleDateFormat oraFormat = new SimpleDateFormat("HH:mm");

	                Date sesiuneData = dataFormat.parse(sessionDateString);
	                Date sesiuneOra = oraFormat.parse(sessionHourString);

	                Calendar sesiuneCal = Calendar.getInstance();
	                sesiuneCal.setTime(sesiuneData);
	                sesiuneCal.set(Calendar.HOUR_OF_DAY, sesiuneOra.getHours());
	                sesiuneCal.set(Calendar.MINUTE, sesiuneOra.getMinutes());

	                Calendar currentCal = Calendar.getInstance();
	                
	                if((parts[0].equals(username)||parts[1].equals(username))&&parts[6].equals("Acceptata")&&!(currentCal.after(sesiuneCal))) {
	                	
	                    sesiuniConfirmate.add(new SesiuneMeditatii(parts[0],parts[1],parts[2],parts[3],parts[4],Integer.parseInt(parts[5]),parts[6]));
	                }
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return sesiuniConfirmate;
	    }
	    
	    @SuppressWarnings("deprecation")
		public ArrayList<SesiuneMeditatii> sesiuniCompletateArray(String username) throws ParseException {
	        ArrayList<SesiuneMeditatii> sesiuniCompletate = new ArrayList<>();
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader("/Users/paunradu/eclipse-workspace/E-Meditatii/src/sesiuni.txt"));
	            String line;
	            while((line = reader.readLine()) != null) {
	            	
	                String[] parts = line.split(" ");
	                if(username.equals(parts[1])||username.equals(parts[0])) {
	                String sessionDateString = parts[3];
	                String sessionHourString = parts[2];

	                SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
	                SimpleDateFormat oraFormat = new SimpleDateFormat("HH:mm");

	                Date sesiuneData = dataFormat.parse(sessionDateString);
	                Date sesiuneOra = oraFormat.parse(sessionHourString);

	                Calendar sesiuneCal = Calendar.getInstance();
	                sesiuneCal.setTime(sesiuneData);
	                sesiuneCal.set(Calendar.HOUR_OF_DAY, sesiuneOra.getHours());
	                sesiuneCal.set(Calendar.MINUTE, sesiuneOra.getMinutes());

	                Calendar currentCal = Calendar.getInstance();

	                if((currentCal.after(sesiuneCal)) && (parts[6].equals("Acceptata"))) {
	                	sesiuniCompletate.add(new SesiuneMeditatii(parts[0],parts[1],parts[2],parts[3],parts[4],Integer.parseInt(parts[5]),parts[6]));
	                } 
	                
	                }

	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return sesiuniCompletate;
	    }
	    
	    public boolean areSesiuniProgramate(String username) {
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader("/Users/paunradu/eclipse-workspace/E-Meditatii/src/sesiuni.txt"));
	            String line;
	            while((line = reader.readLine()) != null) {
	                String[] parts = line.split(" ");
	                if(parts[1].equals(username)&&parts[6].equals("-")) {
	                    reader.close();
	                    return true;
	                }
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    
	    public boolean areSesiuniAnulate(String username) {
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader("/Users/paunradu/eclipse-workspace/E-Meditatii/src/sesiuni.txt"));
	            String line;
	            while((line = reader.readLine()) != null) {
	                String[] parts = line.split(" ");
	                if(parts[0].equals(username) && parts[6].equals("Respinsa")) {
	                    reader.close();
	                    return true;
	                }
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    
	    public void setStatus(String status, SesiuneMeditatii sesiune) throws IOException {
	    	File file = new File("/Users/paunradu/eclipse-workspace/E-Meditatii/src/sesiuni.txt");
	    	List<String> lines = Files.readAllLines(file.toPath());

	    	for (int i = 0; i < lines.size(); i++) {
	    	    String line = lines.get(i);
	    	    String[] parts = line.split(" ");
	    	    if (parts[0].equals(sesiune.getPlanificator()) && parts[1].equals(sesiune.getDestinatar()) && parts[2].equals(sesiune.getOra()) && parts[3].equals(sesiune.getData()) && parts[4].equals(sesiune.getLocatie()) && parts[5].equals(String.valueOf(sesiune.getPret()))) 
	    	    {
	    	        parts[6] = status;
	    	        String newLine = String.join(" ", parts);
	    	        lines.set(i, newLine);
	    	        Files.write(file.toPath(), lines);
	    	        break;
	    	    }
	    	}
	    }
	    
	    @SuppressWarnings("unused")
		public void setNewUsername(String oldUsername, String newUsername) throws IOException {
	    	File file = new File("/Users/paunradu/eclipse-workspace/E-Meditatii/src/sesiuni.txt");
	    	List<String> lines = Files.readAllLines(file.toPath());

	    	for (int i = 0; i < lines.size(); i++) {
	    	    String line = lines.get(i);
	    	    String[] parts = line.split(" ");

	    	    if (parts[0].equals(oldUsername)){
	    	        parts[0] = newUsername;
	    	    }
	    	    if(parts[1].equals(oldUsername)){
					parts[1] = newUsername;
	    	    }
	    	    
	    	    String newLine = String.join(" ", parts);
	    	    lines.set(i, newLine);
	    	    Files.write(file.toPath(), lines);
	    	    break;
	    	    }
	    	}

	    public void editareSesiune(SesiuneMeditatii sesiune, String ora, String data,String loc, int pret) throws IOException {
	    	File file = new File("/Users/paunradu/eclipse-workspace/E-Meditatii/src/sesiuni.txt");
	    	List<String> lines = Files.readAllLines(file.toPath());
	    	
	    	for (int i = 0; i < lines.size(); i++) {
	    	    String line = lines.get(i);
	    	    String[] parts = line.split(" ");

	    	    if (parts[0].equals(sesiune.getPlanificator()) && parts[1].equals(sesiune.getDestinatar()) && parts[2].equals(sesiune.getOra()) && parts[3].equals(sesiune.getData()) && parts[4].equals(sesiune.getLocatie()) && parts[5].equals(String.valueOf(sesiune.getPret()))) {
	    	    
	    	    	parts[0]=sesiune.getDestinatar();
	    	    	parts[1]=sesiune.getPlanificator();
	    	    	parts[2] = ora;
	    	    	sesiune.setOra(ora);
	    	    	parts[3] = data;
	    	    	sesiune.setData(data);
	    	    	parts[4] = loc;
	    	    	sesiune.setLocatie(loc);
	    	    	parts[5] = String.valueOf(pret);
	    	    	sesiune.setPret(pret);
	    	        parts[6] = "-";
	    	        sesiune.setStatus("-");

	    	        String newLine = String.join(" ", parts);
	    	        lines.set(i, newLine);
	    	        Files.write(file.toPath(), lines);
	    	        break;
	    	    }
	    	}
	    }
	    
	    public void stergeSesiuniAnulate(ArrayList<SesiuneMeditatii> sesiuniAnulate) throws IOException {
	        File file = new File("/Users/paunradu/eclipse-workspace/E-Meditatii/src/sesiuni.txt");
	        List<String> lines = Files.readAllLines(file.toPath());
	        for (SesiuneMeditatii sesiune : sesiuniAnulate) {
	            for (int i = 0; i < lines.size(); i++) {
	                String line = lines.get(i);
	                String[] parts = line.split(" ");
	                if (parts[0].equals(sesiune.getPlanificator()) && parts[6].equals("Respinsa")) {
	                    lines.remove(i);
	                    break;
	                }
	            }
	        }
	        Files.write(file.toPath(), lines);
	    }
	    
	    public void stergeSesiune(String planificator, String destinatar, String ora, String data,String loc, int pret) throws IOException {
	        File file = new File("/Users/paunradu/eclipse-workspace/E-Meditatii/src/sesiuni.txt");
	        List<String> lines = Files.readAllLines(file.toPath());
	            for (int i = 0; i < lines.size(); i++) {
	                String line = lines.get(i);
	                String[] parts = line.split(" ");
	                if ((parts[0].equals(planificator) && parts[1].equals(destinatar) && parts[2].equals(ora) && parts[3].equals(data) && parts[4].equals(loc) && parts[5].equals(String.valueOf(pret)))) {
	                    lines.remove(i);
	                    break;
	                }
	            }
	        
	        Files.write(file.toPath(), lines);
	    }

}



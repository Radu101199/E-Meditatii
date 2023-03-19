import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public abstract class Utilizator implements Serializable{
	
	private String nume;
	private String numeUtilizator;
	private String parola;

	public Utilizator(String numeUtilizator, String parola, String nume) {
		this.numeUtilizator = numeUtilizator;
		this.parola = parola;
		this.nume = nume;
	}
	
	public String getNumeUtilizator() {
		return numeUtilizator;
	}

	public String getParola() {
		return parola;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public void setNumeUtilizator(String numeUtilizator) {
		this.numeUtilizator = numeUtilizator;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public void setProfilePicture(String Username, JLabel labelPozaProfil) {
		  File folder = new File("PozaProfil");
		  String numeFisier = "pozadeprofil" + Username + ".png";
		  File profilePicture = new File(folder, numeFisier);

		  if (profilePicture.exists()) {
		    ImageIcon iconPozaProfil = new ImageIcon(profilePicture.getAbsolutePath());
		    labelPozaProfil.setIcon(iconPozaProfil);
		  }
		  else {
		    ImageIcon defaultIcon = new ImageIcon("/Users/paunradu/eclipse-workspace/E-Meditatii/PozaProfil/default.png");
		    labelPozaProfil.setIcon(defaultIcon);
		  }
		}
	
	public void changeProfilePictureFile(String oldUsername, String newUsername) throws IOException {
    	File folder = new File("PozaProfil");

    	String filename = "pozadeprofil" + oldUsername + ".png";
    	String newFileName = "pozadeprofil" + newUsername + ".png";
    	
    	File profilePicture = new File(folder,filename);
    	File userChangedProfilePicture = new File(folder,newFileName);
    	profilePicture.renameTo(userChangedProfilePicture);
    	}

}

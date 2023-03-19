import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Elev extends Utilizator implements Serializable{
	private ArrayList<String> materiiFavorite;
	
	public Elev(String numeUtilizator, String parola, String nume) {
		super(numeUtilizator, parola, nume);
		this.materiiFavorite = new ArrayList<>();
	}
	public ArrayList<String> getMateriiFavorite() {
		return materiiFavorite;
	}
	public void setMateriiFavorite(ArrayList<String> materiiFavorite) {
		this.materiiFavorite = materiiFavorite;
	}
}
	    

    


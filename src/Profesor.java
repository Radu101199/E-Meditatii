import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Profesor  extends Utilizator implements Serializable{
	private ArrayList<String> materiiPredate; 
	
	public Profesor(String numeUtilizator, String parola, String nume) {
		super(numeUtilizator, parola, nume);
		this.materiiPredate = new ArrayList<>();
	}
	
	public ArrayList<String> getMateriiPredate() {
		return materiiPredate;
	}
	public void setMateriiPredate(ArrayList<String> materiiPredate) {
		this.materiiPredate = materiiPredate;
	}
}

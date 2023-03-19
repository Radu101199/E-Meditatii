
public class Recenzie {
	
	private String recenzie;
	private boolean recomandare;
	private double nota;
	
	public Recenzie(String recenzie, double nota, boolean recomandare) {
		this.recenzie = recenzie;
		this.recomandare = recomandare;
		this.nota = nota;
	}
	
	public String getRecenzie() {
		return recenzie;
	}
	
	public void setRecenzie(String recenzie) {
		this.recenzie = recenzie;
	}
	
	public boolean isRecomandare() {
		return recomandare;
	}
	
	public void setRecomandare(boolean recomandare) {
		this.recomandare = recomandare;
	}
	
	public double getNota() {
		return nota;
	}
	
	public void setNota(double nota) {
		this.nota = nota;
	}
	
}

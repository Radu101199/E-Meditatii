import java.io.Serializable;

@SuppressWarnings("serial")
public class SesiuneMeditatii implements Serializable{
	private String planificator;
	private String destinatar;
	private String ora;
	private String data;
	private String locatie;
	private int pret;
	private String status;
	
	public SesiuneMeditatii(String planificator, String destinatar, String ora,String data, String locatie, int pret, String status) {
		this.planificator = planificator;
		this.destinatar = destinatar;
		this.data = data;
		this.locatie = locatie;
		this.ora = ora;
		this.pret = pret;
		this.status = "-";
	}
	public String getPlanificator() {
		return planificator;
	}
	public void setPlanificator(String planificator) {
		this.planificator = planificator;
	}
	public String getDestinatar() {
		return destinatar;
	}
	public void setDestinatar(String destinatar) {
		this.destinatar = destinatar;
	}
	public String getOra() {
		return ora;
	}
	public void setOra(String ora) {
		this.ora = ora;
	}
	public int getPret() {
		return pret;
	}
	public void setPret(int pret) {
		this.pret = pret;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getLocatie() {
		return locatie;
	}
	public void setLocatie(String locatie) {
		this.locatie = locatie;
	}
	@Override
	public String toString() {
		return planificator + " " + destinatar + " " + ora
				+ " " + data + " " + locatie + " " + pret;
	}
	
}

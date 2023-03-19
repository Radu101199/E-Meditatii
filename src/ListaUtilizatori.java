import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public abstract class ListaUtilizatori{
	
	private HashMap<Object,Object> hm;
	protected OperatiiObiecte oo;
 
 
	public ListaUtilizatori(){
		hm=new HashMap<>();
		oo=new OperatiiUtilizator();
	}
 
	public void adaugaUtilizator(Utilizator c){
		if (!hm.containsValue(c)) 
			hm.put(c.getNumeUtilizator()+" "+c.getParola(), c); 
	}

	public Utilizator getUtilizator(String cheie){
		Utilizator c=null; 
		if(hm.containsKey(cheie)) c=(Utilizator)hm.get(cheie); 
		else c=(Utilizator) oo.citesteObiect(cheie);
		return c;	
	}


	public void descarca(){
		Collection<Object> s=hm.values();
		Iterator<Object> i=s.iterator();
		while(i.hasNext()) oo.scrieObiect(i.next()); 
		
		((OperatiiUtilizator)oo).inchide();
		hm.clear();
	}
	public void stergeUtilizator(Utilizator o) {
		oo.stergeObiect(o);
		((OperatiiUtilizator)oo).inchide();
	}

	abstract void listeaza() throws FileNotFoundException, ClassNotFoundException, IOException;

}
	



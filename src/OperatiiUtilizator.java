import java.io.*;
import java.util.*;
public class OperatiiUtilizator implements OperatiiObiecte{
	
	private  ObjectOutputStream oos;
	private  ObjectInputStream ois;
	public String[] nume;
	
	public OperatiiUtilizator(){
	  File f=new File(".");
	  nume=f.list(new FiltruFisier());
	 }
	
	 public void scrieObiect(Object o){
		 if (!(o instanceof Utilizator)) {
			 System.out.println("instanta invalida");
			 return; 
			 }
		 else { 
			 try{
				 if(o.getClass()==Elev.class) {
					 oos=new ObjectOutputStream(
						  new FileOutputStream("elev"+((Utilizator)o).hashCode()+".txt", true));
					 oos.writeObject(o);}
				 if(o.getClass()==Profesor.class) {
					 oos=new ObjectOutputStream(
						  new FileOutputStream("profesor"+((Utilizator)o).hashCode()+".txt", true));
					 oos.writeObject(o);}
			 }catch(IOException io){io.printStackTrace();}
		  catch(Exception e){}
		}
	}
	 
	public Object citesteObiect(String cheie){
	 if (nume.length==0) return null;
	 Utilizator c;
	 try{
		 for (int i=0;i<nume.length;i++){
			 ois=new ObjectInputStream(new FileInputStream(nume[i]));
			 c=(Utilizator)ois.readObject();
			 if (cheie.equals(c.getNumeUtilizator()+" "+c.getParola()))
				 return c;
			 }
	 	}catch(IOException io){io.printStackTrace();} catch(ClassNotFoundException io){io.printStackTrace();}
	  return null;
	 }
	 
	public void inchide(){
	  try{
	if (oos!=null) oos.close(); }catch(IOException io){io.printStackTrace();}
	}
	
	public ArrayList<Object> listeaza(String prefix) throws FileNotFoundException, IOException, ClassNotFoundException{
		ArrayList<Object> rezultat = new ArrayList<Object>();
		Utilizator c;
		
		for(String fisier : nume) {
			if(fisier.startsWith(prefix)) {
			ois=new ObjectInputStream(new FileInputStream(fisier));
			 c=(Utilizator)ois.readObject();
			 rezultat.add(c);}
		}
		return rezultat;
    }
	
	
	@Override
	public void stergeObiect(Utilizator o) {
		Utilizator c;
		String cheie1 = o.getNumeUtilizator()+" "+o.getParola();
		for(int i = 0; i < nume.length; i++) {
			try {
				ois=new ObjectInputStream(new FileInputStream(nume[i]));
				c=(Utilizator)ois.readObject();
				String cheie2 = c.getNumeUtilizator()+" "+c.getParola();
				
				 if(cheie1.equals(cheie2)) {
					 File file = new File(nume[i]);
			         file.delete();
			         ArrayList<String> list = new ArrayList<String>(Arrays.asList(nume));
			         list.remove(nume[i]);
			         nume = list.toArray(new String[0]);
					 break;
				 }
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
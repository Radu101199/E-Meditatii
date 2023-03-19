import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ListaProfesor extends ListaUtilizatori{

	public static ArrayList<Object> lista;
	
	public ListaProfesor(){
		 super();
		 lista = new ArrayList<>();
		 }
	
	@Override
	void listeaza() throws FileNotFoundException, ClassNotFoundException, IOException {
		lista = ((OperatiiUtilizator)oo).listeaza("profesor");
	}
	
}

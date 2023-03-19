import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ListaElev extends ListaUtilizatori{
	
	public static ArrayList<Object> lista;
	
	public ListaElev(){
		 super();
		 lista = new ArrayList<>();
		 }
	
	@Override
	void listeaza() throws FileNotFoundException, ClassNotFoundException, IOException {
		lista = ((OperatiiUtilizator)oo).listeaza("elev");
	}
}

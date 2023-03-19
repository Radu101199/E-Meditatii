import java.io.File;
import java.io.FilenameFilter;
public class FiltruFisier implements FilenameFilter{
	
	public boolean accept(File f, String nume){ 
		
		if((nume.startsWith("elev")||(nume.startsWith("profesor"))) && nume.endsWith(".txt")) 
			return true;
		return false;
	} 
	
}

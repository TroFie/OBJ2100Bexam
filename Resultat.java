 
import java.io.Serializable; 
import java.util.ArrayList; 
 
public class Resultat implements Serializable { 
  
	/** 
	 *  
	 */ 
	private static final long serialVersionUID = 2515115660781010130L; 
	String resultat; 
     
 
	Resultat(){} 
    
	Resultat(String resultat){ 
        setResultat(resultat); 
    } 
 
    public String getResultat() { 
        return resultat; 
    } 
 
    public void setResultat(String resultat) { 
        this.resultat = resultat; 
    } 
 
    public String toString() { 
        return resultat; 
    } 
     
} 

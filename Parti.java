import java.io.Serializable;

public class Parti implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7044072123791457954L;
	String name1;
    String name2;
    String dato;

    Parti(){}
   
    Parti(String name1, String name2, String dato){
        setName(name1, name2, dato);
    }

    public String getName1() {
        return name1;
    }
    
    public String getName2() {
        return name2;
    }
    
    public String getDato() {
        return dato;
    }

    public void setName(String name1, String name2, String dato) {
        this.name1 = name1;
        this.name2 = name2;
        this.dato = dato;
    }

    @Override
    public String toString() {
        return "Parti {name1=" + name1 + ", name2=" + name2 + ", dato=" + dato + "}";
    }
    
}
